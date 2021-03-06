package test.rpg.engine.console;

import java.util.ArrayList;

import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.printer.Log;

public class ConsoleManager
{

	private ArrayList<Command> commands;

	private ConsoleWriter writer;
	private ConsoleReader reader;

	/*
	 * private Game game; private boolean onMainMenu; private State state;
	 */

	// private enum State{writing, reading};
	private static ConsoleManager instance;
	
	public static ConsoleManager getInstance()
	{
		if(instance == null)
		{
			instance = new ConsoleManager();
		}
		
		return instance;
	}
	
	private ConsoleManager()
	{
		commands = new ArrayList<Command>();

		writer = new ConsoleWriter(this);
		reader = new ConsoleReader(this);
	}

	public void addCommand(Command c)
	{
		Log.d("Adding new command : " + c);
		commands.add(c);
	}

	public void deleteCommand(Command c)
	{
		Log.d("Deleting command : " + c);
		commands.remove(c);
	}

	// Getters
	public ArrayList<Command> getCommands()
	{
		return commands;
	}

	public ConsoleWriter getWriter()
	{
		return writer;
	}

	public ConsoleReader getReader()
	{
		return reader;
	}

	public void showAllCommands()
	{
		writer.showAllCommands();
	}

	public void resetCommands()
	{
		commands = new ArrayList<Command>();
	}
}
