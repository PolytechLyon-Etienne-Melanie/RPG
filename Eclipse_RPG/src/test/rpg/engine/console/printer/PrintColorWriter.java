package test.rpg.engine.console.printer;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PrintColorWriter extends PrintWriter
{
	//private static final String ANSI_RESET = "\u001B[0m";

	public PrintColorWriter(PrintStream out) throws UnsupportedEncodingException
	{
		super(new OutputStreamWriter(out), true);
	}

	public void println(PrintColor color, String string)
	{
		//print(color.getAnsiColor());
		print(string);
		println(/*ANSI_RESET*/);
		flush();
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
		//print(color.getAnsiColor());
		print(string);
		//print(ANSI_RESET);
		flush();
	}
}
