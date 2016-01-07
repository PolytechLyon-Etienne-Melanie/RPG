package test.rpg.engine.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.story.event.EventObserver;

public class ConsoleReader
{
	private ConsoleManager manager;

	public ConsoleReader(ConsoleManager manager)
	{
		this.manager = manager;

		Command showall = new Command(false, "Montrer toutes les commandes disponibles.", "showcommands");
		Command quit = new Command(false, "Quitter le jeu.", "quit");

		showall.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed()
			{
				manager.showAllCommands();
			}
		});
		quit.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed()
			{
				System.exit(0);
			}
		});

		// manager.addCommand(showall);
		manager.addCommand(quit);
	}

	public void read() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Log.d("Start reading");
		manager.getWriter().reader();
		String com = br.readLine();
		Log.d("End reading");
		onCommand(com);
	}

	public void onCommand(String com)
	{
		Command c = findCommand(com);

		if (c != null)
		{
			// System.out.println("commande executée");
			Log.d("Execute command : " + c);
			executeCommand(c);
		} else
		{
			Log.e("Command introuvable");
		}
	}

	private Command findCommand(String com)
	{
		if (com == null)
			return null;

		// System.out.println("test command " + com);
		Iterator<Command> i = manager.getCommands().iterator();
		String c = "";
		Command command = null;

		while (com != c && i.hasNext())
		{
			Command tmp = i.next();
			c = tmp.getCom();
			if (com.equals(c))
				command = tmp;
			// System.out.println("test command " + c);
		}

		return command;
	}

	public void executeCommand(Command com)
	{
		com.actionPerformed();
	}
}
