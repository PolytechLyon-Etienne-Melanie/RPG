package test.rpg.engine.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.console.printer.PrintColor;
import test.rpg.engine.story.event.EventObserver;

public abstract class Menu extends CommandSender
{
	protected Game game;
	protected String title;
	protected ArrayList<Dialogue> dials;
	private Menu retour;

	public Menu(Game game)
	{
		this(game, "Menu");
	}

	public Menu(Game game, String name)
	{
		this(game, name, null);
	}

	public Menu(Game game, String name, Menu retour)
	{
		super();
		this.game = game;
		title = name;
		this.retour = retour;
	}

	public void init()
	{
		resetCommands();
		setRetourCom();
		dials = new ArrayList<Dialogue>();
		setDials();
		setCommands();
	}

	private void setRetourCom()
	{
		if (retour != null)
		{
			Command back = new Command("Retour au menu précédent", "back");
			back.addObserver(new EventObserver()
			{
				@Override
				public void actionPerformed()
				{
					game.setCurrentMenu(retour);
				}
			});
			this.addCommand(back);
		}
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	protected abstract void setDials();

	protected abstract void setCommands();

	public void render()
	{
		Log.d("render menu");
		write(PrintColor.ERASE.getAnsiColor());
		if (!game.isDebug())
			clearWindow();
		writeSeparator(title);
		writeMenu();
		writeSeparator("Commands");
		writeCommands();
	}

	protected void writeMenu()
	{
		Iterator<Dialogue> i = dials.iterator();
		while (i.hasNext())
		{
			this.writeDialogue(i.next());
		}
	}

	protected void writeDialogue(Dialogue d)
	{
		this.writeLine(d.getDialogue());
	}

	protected void writeSeparator()
	{
		this.writeLine("+--------------------------------------------------------------------------+", PrintColor.BLUE);
	}

	protected void writeSeparator(String t)
	{
		writeSeparator();
		this.writeLine("+--| " + t + " |--+", PrintColor.BLUE);
		writeSeparator();
	}

	protected void writeCommands()
	{
		showCommands();
	}

	protected void addDial(Dialogue d)
	{
		this.dials.add(d);
	}
}
