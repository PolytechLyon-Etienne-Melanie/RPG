package test.rpg.engine.console.printer;

import java.io.OutputStream;
import java.io.PrintStream;


public class PrintColorWriter extends PrintStream
{
	private final boolean gotColor;
	private final String def = PrintColor.BLACK.getAnsiColor();
	
	public PrintColorWriter(OutputStream stream, boolean color)
	{
		super(stream);
		gotColor = color;
	}

	public PrintColorWriter(OutputStream stream)
	{
		this(stream, false);
	}

	public void println(PrintColor color, String string)
	{
		print(color, string);
		println("");
	}

	public void green(String string)
	{
		println(PrintColor.GREEN, string);
	}

	public void red(String string)
	{
		println(PrintColor.RED, string);
	}

	public void print(PrintColor color, String string)
	{
		if(gotColor)
			print(color.getAnsiColor());
		print(string);
		if(gotColor)
			print(def);
	}
	
	public void printN(PrintColor color, String string)
	{
		if(gotColor)
			print(color.getAnsiColor());
		print(string);
	}
}
