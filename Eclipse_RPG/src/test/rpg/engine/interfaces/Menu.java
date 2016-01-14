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
	protected boolean init;

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
		init = true;
	}

	private void init()
	{
		resetCommands();
		setRetourCom();
		initMenu();
	}

	protected abstract void initMenu();

	private void setRetourCom()
	{
		if (retour != null)
		{
			Command back = new Command("Retour au menu précédent", "back");
			back.addObserver(new EventObserver()
			{
				@Override
				public void actionPerformed(String p)
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

	public void render()
	{
		Log.d("init menu");
		init();
		Log.d("render menu");
		write(PrintColor.ERASE.getAnsiColor());
		if (!game.isDebug())
			clearWindow();
		writeSeparator(title);
		renderMenu();
		writeSeparator("Commands");
		writeCommands();
	}
	
	protected abstract void renderMenu();

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
