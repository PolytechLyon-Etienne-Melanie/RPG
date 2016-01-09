package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import test.rpg.engine.console.printer.PrintColor;

public class JEventPropertyString extends JEventProperty<String>
{
	private JTextPane editorPane;
	/**
	 * @wbp.parser.constructor
	 */
	public JEventPropertyString(Frame frame)
	{
		this(frame, "");
	}

	
	public JEventPropertyString(Frame frame, String field)
	{
		super(frame, field);
	}

	@Override
	protected void initComponents(Frame frame)
	{
		editorPane = new JTextPane();
		editorPane.setFont(new Font("Consolas", Font.PLAIN, 11));
		editorPane.setText(value);
		this.add(editorPane, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		this.add(panel, BorderLayout.SOUTH);
		
		JComboBox<PrintColor> comboBox = new JComboBox<PrintColor>(PrintColor.values());
		panel.add(comboBox);
		
		JButton btnColorier = new JButton("Colorier");
		btnColorier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PrintColor c = comboBox.getItemAt(comboBox.getSelectedIndex());
				editorPane.replaceSelection(c.getAnsiColor() + editorPane.getSelectedText() + PrintColor.LAST.getAnsiColor());
				System.out.println(editorPane.getText());
			}
		});
		panel.add(btnColorier);
	}

	@Override
	protected String setValue()
	{
		return editorPane.getText();
	}
}
