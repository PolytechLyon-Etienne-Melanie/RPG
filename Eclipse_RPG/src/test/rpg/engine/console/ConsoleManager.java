package test.rpg.engine.console;

import java.io.IOException;
import java.util.ArrayList;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
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

	public ConsoleManager()
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
