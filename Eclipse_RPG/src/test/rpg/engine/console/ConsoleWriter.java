package test.rpg.engine.console;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.console.printer.PrintColor;
import test.rpg.engine.console.printer.PrintColorWriter;

public class ConsoleWriter
{
	private ConsoleManager manager;
	private PrintColorWriter out;

	public ConsoleWriter(ConsoleManager manager)
	{
		this(manager, Log.Level.ERROR, System.err);
	}
	
	public ConsoleWriter(ConsoleManager manager, Log.Level l)
	{
		this(manager, l, System.err);
	}

	public ConsoleWriter(ConsoleManager manager, Log.Level l, PrintStream stream)
	{
		this.manager = manager;
		Log.setLevel(l);
		Log.setStreamer(stream);

		try
		{
			out = new PrintColorWriter(stream);
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeLine(String l)
	{
		out.println(PrintColor.GREEN, l);
		/*
		 * Log.write(""); for(int i = 0; i < l.length(); i++) {
		 * Log.writeChar(l.charAt(i)); try { Thread.sleep(100); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
	}

	public void showAllCommands()
	{
		Log.d("Start write all commands");
		Iterator<Command> i = manager.getCommands().iterator();

		while (i.hasNext())
		{
			writeCommand(i.next());
		}
		Log.d("End write all commands");
	}

	private void writeCommand(Command c)
	{
		if(c.isVisible())
		{
			Log.d("write command : " + c);
			this.writeLine("<" + c.getCom() + "> " + c.getDesc().getDialogue());
		}
	}

	public void reader()
	{
		out.print(PrintColor.GREEN, ">> ");
	}
}
