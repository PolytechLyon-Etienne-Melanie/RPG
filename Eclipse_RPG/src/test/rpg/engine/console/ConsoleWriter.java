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
		this(manager, System.err);
	}

	public ConsoleWriter(ConsoleManager manager, PrintStream stream)
	{
		this.manager = manager;
		Log.setStreamer(stream);

		if(stream instanceof PrintColorWriter)
			out = (PrintColorWriter)stream;
		else
			out = new PrintColorWriter(stream);
	}

	public void writeLine(String l)
	{
		writeLine(l, PrintColor.WHITE);
		/*
		 * Log.write(""); for(int i = 0; i < l.length(); i++) {
		 * Log.writeChar(l.charAt(i)); try { Thread.sleep(100); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
	}
	
	public void write(String l)
	{
		write(l, PrintColor.WHITE);
		/*
		 * Log.write(""); for(int i = 0; i < l.length(); i++) {
		 * Log.writeChar(l.charAt(i)); try { Thread.sleep(100); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */
	}
	
	public void writeLine(String l, PrintColor color)
	{
		out.println(color, l);
	}
	
	public void write(String l, PrintColor color)
	{
		out.print(color, l);
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
			write("<" + c.getCom() + "> ", PrintColor.CYAN);
			writeLine(c.getDesc().getDialogue());
		}
	}

	public void reader()
	{
		out.printN(PrintColor.GREEN, ">> ");
	}
}
