package test.rpg.engine.console.printer;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.Vector;
import java.awt.Cursor;
import javax.swing.text.*;
import javax.swing.*;

public class JConsole extends JScrollPane
		implements Runnable, KeyListener, MouseListener, ActionListener, PropertyChangeListener
{
	private final static String CUT = "Cut";
	private final static String COPY = "Copy";
	private final static String PASTE = "Paste";

	private OutputStream outPipe;
	private InputStream inPipe;
	private InputStream in;
	private PrintStream out;

	public InputStream getInputStream()
	{
		return in;
	}

	public Reader getIn()
	{
		return new InputStreamReader(in);
	}

	public PrintStream getOut()
	{
		return out;
	}

	public PrintStream getErr()
	{
		return out;
	}

	private int cmdStart = 0;
	private Vector history = new Vector();
	private String startedLine;
	private int histLine = 0;

	private JPopupMenu menu;
	private JTextPane text;
	private DefaultStyledDocument doc;

	private String allText;

	final int SHOW_AMBIG_MAX = 10;

	// hack to prevent key repeat for some reason?
	private boolean gotUp = true;

	public JConsole()
	{
		this(null, null);
	}

	public JConsole(InputStream cin, OutputStream cout)
	{
		super();

		text = new JTextPane(doc = new DefaultStyledDocument())
		{
			public void cut()
			{
				if (text.getCaretPosition() < cmdStart)
				{
					super.copy();
				} else
				{
					super.cut();
				}
			}

			public void paste()
			{
				forceCaretMoveToEnd();
				super.paste();
			}
		};

		Font font = new Font("Consolas", Font.PLAIN, 14);
		text.setText("");
		allText = "";
		text.setFont(font);
		text.setMargin(new Insets(7, 5, 7, 5));
		text.addKeyListener(this);
		text.setBackground(Color.black);
		setViewportView(text);

		// create popup menu
		menu = new JPopupMenu("JConsole	Menu");
		menu.add(new JMenuItem(CUT)).addActionListener(this);
		menu.add(new JMenuItem(COPY)).addActionListener(this);
		menu.add(new JMenuItem(PASTE)).addActionListener(this);

		text.addMouseListener(this);

		// make sure popup menu follows Look & Feel
		UIManager.addPropertyChangeListener(this);


		outPipe = cout;
		if (outPipe == null)
		{
			outPipe = new PipedOutputStream();
			try
			{
				in = new PipedInputStream((PipedOutputStream) outPipe);
			} catch (IOException e)
			{
				print("Console internal	error (1)...", Color.red);
			}
		}

		inPipe = cin;
		if (inPipe == null)
		{
			PipedOutputStream pout = new PipedOutputStream();
			out = new PrintColorWriter(pout, true);
			try
			{
				inPipe = new BlockingPipedInputStream(pout);
			} catch (IOException e)
			{
				print("Console internal error: " + e);
			}
		}

		// Start the inpipe watcher
		new Thread(this).start();

		requestFocus();
	}

	public void requestFocus()
	{
		super.requestFocus();
		text.requestFocus();
	}

	public void keyPressed(KeyEvent e)
	{
		type(e);
		gotUp = false;
	}

	public void keyTyped(KeyEvent e)
	{
		type(e);
	}

	public void keyReleased(KeyEvent e)
	{
		gotUp = true;
		type(e);
	}

	private synchronized void type(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case (KeyEvent.VK_ENTER):
			if (e.getID() == KeyEvent.KEY_PRESSED)
			{
				if (gotUp)
				{
					enter();
					resetCommandStart();
					text.setCaretPosition(cmdStart);
				}
			}
			e.consume();
			text.repaint();
			break;

		case (KeyEvent.VK_UP):
			if (e.getID() == KeyEvent.KEY_PRESSED)
			{
				historyUp();
			}
			e.consume();
			break;

		case (KeyEvent.VK_DOWN):
			if (e.getID() == KeyEvent.KEY_PRESSED)
			{
				historyDown();
			}
			e.consume();
			break;

		case (KeyEvent.VK_LEFT):
		case (KeyEvent.VK_BACK_SPACE):
		case (KeyEvent.VK_DELETE):
			if (text.getCaretPosition() <= cmdStart)
			{
				e.consume();
			}
			break;

		case (KeyEvent.VK_RIGHT):
			forceCaretMoveToStart();
			break;

		case (KeyEvent.VK_HOME):
			text.setCaretPosition(cmdStart);
			e.consume();
			break;

		case (KeyEvent.VK_U): 
			if ((e.getModifiers() & InputEvent.CTRL_MASK) > 0)
			{
				replaceRange("", cmdStart, textLength());
				histLine = 0;
				e.consume();
			}
			break;

		case (KeyEvent.VK_ALT):
		case (KeyEvent.VK_CAPS_LOCK):
		case (KeyEvent.VK_CONTROL):
		case (KeyEvent.VK_META):
		case (KeyEvent.VK_SHIFT):
		case (KeyEvent.VK_PRINTSCREEN):
		case (KeyEvent.VK_SCROLL_LOCK):
		case (KeyEvent.VK_PAUSE):
		case (KeyEvent.VK_INSERT):
		case (KeyEvent.VK_F1):
		case (KeyEvent.VK_F2):
		case (KeyEvent.VK_F3):
		case (KeyEvent.VK_F4):
		case (KeyEvent.VK_F5):
		case (KeyEvent.VK_F6):
		case (KeyEvent.VK_F7):
		case (KeyEvent.VK_F8):
		case (KeyEvent.VK_F9):
		case (KeyEvent.VK_F10):
		case (KeyEvent.VK_F11):
		case (KeyEvent.VK_F12):
		case (KeyEvent.VK_ESCAPE):

			break;

		case (KeyEvent.VK_C):
			if (text.getSelectedText() == null)
			{
				if (((e.getModifiers() & InputEvent.CTRL_MASK) > 0) && (e.getID() == KeyEvent.KEY_PRESSED))
				{
					append("^C");
				}
				e.consume();
			}
			break;

		case (KeyEvent.VK_TAB):
			if (e.getID() == KeyEvent.KEY_RELEASED)
			{
				String part = text.getText().substring(cmdStart);
			}
			e.consume();
			break;

		default:
			if ((e.getModifiers() & (InputEvent.CTRL_MASK | InputEvent.ALT_MASK | InputEvent.META_MASK)) == 0)
			{
				forceCaretMoveToEnd();
			}

			if (e.paramString().indexOf("Backspace") != -1)
			{
				if (text.getCaretPosition() <= cmdStart)
				{
					e.consume();
					break;
				}
			}

			break;
		}
	}


	private void resetCommandStart()
	{
		cmdStart = textLength();
	}

	private void append(String string)
	{
		text.setText("");
		allText += string;

		String[] parts = allText.split("(?=" + PrintColor.getHash() + ")");
		for (int i = 0; i < parts.length; i++)
		{

			String t = parts[i];
			if (t.length() > 4 && t.substring(0, 4).equals("/cl/"))
			{
				t = parts[i].substring(5);
				if ((parts[i]).charAt(4) == '0')
				{
					clear();
				} else if ((parts[i]).charAt(4) == '9')
				{
					setPrevStyle();
				} else
				{
					Color color = PrintColor.getColor((parts[i]).charAt(4));
					setStyle(color);
				}
			}
			int slen = textLength();
			text.select(slen, slen);
			text.replaceSelection(t);
		}
	}

	private String replaceRange(Object s, int start, int end)
	{
		String st = s.toString();
		text.select(start, end);
		text.replaceSelection(st);
		// text.repaint();
		return st;
	}

	private void forceCaretMoveToEnd()
	{
		if (text.getCaretPosition() < cmdStart)
		{
			// move caret first!
			text.setCaretPosition(textLength());
		}
		text.repaint();
	}

	private void forceCaretMoveToStart()
	{
		if (text.getCaretPosition() < cmdStart)
		{
			// move caret first!
		}
		text.repaint();
	}

	private void enter()
	{
		String s = getCmd();

		if (s.length() == 0)
			s = "\n";
		else
		{
			history.addElement(s);
			s = s + "\n";
		}

		// append("\n");
		histLine = 0;
		acceptLine(s);
		text.repaint();
	}

	private String getCmd()
	{
		String s = "";
		try
		{
			s = text.getText(cmdStart, textLength() - cmdStart);
		} catch (BadLocationException e)
		{
			System.out.println("Internal JConsole Error: " + e);
		}
		return s;
	}

	private void historyUp()
	{
		if (history.size() == 0)
			return;
		if (histLine == 0) 
			startedLine = getCmd();
		if (histLine < history.size())
		{
			histLine++;
			showHistoryLine();
		}
	}

	private void historyDown()
	{
		if (histLine == 0)
			return;

		histLine--;
		showHistoryLine();
	}

	private void showHistoryLine()
	{
		String showline;
		if (histLine == 0)
			showline = startedLine;
		else
			showline = (String) history.elementAt(history.size() - histLine);

		replaceRange(showline, cmdStart, textLength());
		text.setCaretPosition(textLength());
		text.repaint();
	}

	String ZEROS = "000";

	private void acceptLine(String line)
	{
		StringBuffer buf = new StringBuffer();
		int lineLength = line.length();
		for (int i = 0; i < lineLength; i++)
		{
			char c = line.charAt(i);
			if (c > 127)
			{
				String val = Integer.toString(c, 16);
				val = ZEROS.substring(0, 4 - val.length()) + val;
				buf.append("\\u" + val);
			} else
			{
				buf.append(c);
			}
		}
		line = buf.toString();

		if (outPipe == null)
			print("Console internal	error: cannot output ...", Color.red);
		else
			try
			{
				outPipe.write(line.getBytes());
				outPipe.flush();
			} catch (IOException e)
			{
				outPipe = null;
				throw new RuntimeException("Console pipe broken...");
			}
		// text.repaint();
	}

	public void println(Object o)
	{
		print(String.valueOf(o) + "\n");
		text.repaint();
	}

	public void print(final Object o)
	{
		invokeAndWait(new Runnable()
		{
			public void run()
			{
				String l = String.valueOf(o);
				append(l);
				resetCommandStart();
				text.setCaretPosition(cmdStart);
			}
		});
	}

	public void clear()
	{
		int slen = textLength();
		text.select(0, slen);
		text.replaceSelection("");
	}

	public void println()
	{
		print("\n");
		text.repaint();
	}

	public void error(Object o)
	{
		print(o, Color.red);
	}

	public void println(Icon icon)
	{
		print(icon);
		println();
		text.repaint();
	}

	public void print(final Icon icon)
	{
		if (icon == null)
			return;

		invokeAndWait(new Runnable()
		{
			public void run()
			{
				text.insertIcon(icon);
				resetCommandStart();
				text.setCaretPosition(cmdStart);
			}
		});
	}

	public void print(Object s, Font font)
	{
		print(s, font, null);
	}

	public void print(Object s, Color color)
	{
		print(s, null, color);
	}

	public void print(final Object o, final Font font, final Color color)
	{
		invokeAndWait(new Runnable()
		{
			public void run()
			{
				AttributeSet old = getStyle();
				setStyle(font, color);
				append(String.valueOf(o));
				resetCommandStart();
				text.setCaretPosition(cmdStart);
				setStyle(old, true);
			}
		});
	}

	public void print(Object s, String fontFamilyName, int size, Color color)
	{

		print(s, fontFamilyName, size, color, false, false, false);
	}

	public void print(final Object o, final String fontFamilyName, final int size, final Color color,
			final boolean bold, final boolean italic, final boolean underline)
	{
		invokeAndWait(new Runnable()
		{
			public void run()
			{
				AttributeSet old = getStyle();
				setStyle(fontFamilyName, size, color, bold, italic, underline);
				append(String.valueOf(o));
				resetCommandStart();
				text.setCaretPosition(cmdStart);
				setStyle(old, true);
			}
		});
	}

	private AttributeSet setStyle(Font font)
	{
		return setStyle(font, null);
	}

	private Color lastColor;
	private Color currentColor;

	private AttributeSet setStyle(Color color)
	{
		// System.out.println(color);
		if (currentColor == null)
			currentColor = Color.black;
		lastColor = currentColor;
		currentColor = color;
		return setStyle(null, color);
	}

	private AttributeSet setPrevStyle()
	{
		currentColor = lastColor;
		return setStyle(null, lastColor);
	}

	private AttributeSet setStyle(Font font, Color color)
	{
		if (font != null)
			return setStyle(font.getFamily(), font.getSize(), color, font.isBold(), font.isItalic(),
					StyleConstants.isUnderline(getStyle()));
		else
			return setStyle(null, -1, color);
	}

	private AttributeSet setStyle(String fontFamilyName, int size, Color color)
	{
		MutableAttributeSet attr = new SimpleAttributeSet();
		if (color != null)
			StyleConstants.setForeground(attr, color);
		if (fontFamilyName != null)
			StyleConstants.setFontFamily(attr, fontFamilyName);
		if (size != -1)
			StyleConstants.setFontSize(attr, size);

		setStyle(attr);

		return getStyle();
	}

	private AttributeSet setStyle(String fontFamilyName, int size, Color color, boolean bold, boolean italic,
			boolean underline)
	{
		MutableAttributeSet attr = new SimpleAttributeSet();
		if (color != null)
			StyleConstants.setForeground(attr, color);
		if (fontFamilyName != null)
			StyleConstants.setFontFamily(attr, fontFamilyName);
		if (size != -1)
			StyleConstants.setFontSize(attr, size);
		StyleConstants.setBold(attr, bold);
		StyleConstants.setItalic(attr, italic);
		StyleConstants.setUnderline(attr, underline);

		setStyle(attr);

		return getStyle();
	}

	private void setStyle(AttributeSet attributes)
	{
		setStyle(attributes, false);
	}

	private void setStyle(AttributeSet attributes, boolean overWrite)
	{
		text.setCharacterAttributes(attributes, overWrite);
	}

	private AttributeSet getStyle()
	{
		return text.getCharacterAttributes();
	}

	public void setFont(Font font)
	{
		super.setFont(font);

		if (text != null)
			text.setFont(font);
	}

	private void inPipeWatcher() throws IOException
	{
		byte[] ba = new byte[256]; // arbitrary blocking factor
		int read;
		while ((read = inPipe.read(ba)) != -1)
		{
			print(new String(ba, 0, read));
			//text.repaint();
		}

		println("Console: Input	closed...");
	}

	public void run()
	{
		try
		{
			inPipeWatcher();
		} catch (IOException e)
		{
			print("Console: I/O Error: " + e + "\n", Color.red);
		}
	}

	public String toString()
	{
		return "BeanShell console";
	}

	public void mouseClicked(MouseEvent event)
	{
	}

	public void mousePressed(MouseEvent event)
	{
		if (event.isPopupTrigger())
		{
			menu.show((Component) event.getSource(), event.getX(), event.getY());
		}
	}

	public void mouseReleased(MouseEvent event)
	{
		if (event.isPopupTrigger())
		{
			menu.show((Component) event.getSource(), event.getX(), event.getY());
		}
		text.repaint();
	}

	public void mouseEntered(MouseEvent event)
	{
	}

	public void mouseExited(MouseEvent event)
	{
	}

	public void propertyChange(PropertyChangeEvent event)
	{
		if (event.getPropertyName().equals("lookAndFeel"))
		{
			SwingUtilities.updateComponentTreeUI(menu);
		}
	}

	public void actionPerformed(ActionEvent event)
	{
		String cmd = event.getActionCommand();
		if (cmd.equals(CUT))
		{
			text.cut();
		} else if (cmd.equals(COPY))
		{
			text.copy();
		} else if (cmd.equals(PASTE))
		{
			text.paste();
		}
	}


	private void invokeAndWait(Runnable run)
	{
		if (!SwingUtilities.isEventDispatchThread())
		{
			try
			{
				SwingUtilities.invokeAndWait(run);
			} catch (Exception e)
			{
				// shouldn't happen
				e.printStackTrace();
			}
		} else
		{
			run.run();
		}
	}

	public static class BlockingPipedInputStream extends PipedInputStream
	{
		boolean closed;

		public BlockingPipedInputStream(PipedOutputStream pout) throws IOException
		{
			super(pout);
		}

		public synchronized int read() throws IOException
		{
			if (closed)
				throw new IOException("stream closed");

			while (super.in < 0)
			{ // While no data */
				notifyAll(); // Notify any writers to wake up
				try
				{
					wait(100);
				} catch (InterruptedException e)
				{
					throw new InterruptedIOException();
				}
			}
			
			int ret = buffer[super.out++] & 0xFF;
			if (super.out >= buffer.length)
				super.out = 0;
			if (super.in == super.out)
				super.in = -1;
			return ret;
		}

		public void close() throws IOException
		{
			closed = true;
			super.close();
		}
	}
	 

	public void setWaitFeedback(boolean on)
	{
		if (on)
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		else
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	private int textLength()
	{
		return text.getDocument().getLength();
	}
}