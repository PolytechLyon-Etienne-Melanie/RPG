package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;

public class MenuParametre extends Menu
{

	private Command debug;
	private Command erase;

	public MenuParametre(Game game, Menu retour)
	{
		super(game, "Menu Principal", retour);
	}
	
	private void switchDebug()
	{
		Log.switchDebug();
		if(Log.debug())
		{
			debug.setDesc("Cacher le DEBUG.");
			game.setDebug(true);
		}
		else
		{
			debug.setDesc("Afficher le DEBUG.");
			game.setDebug(false);
		}
	}
	
	private void switchErase()
	{
		game.setErase(!game.isErase());
	}

	@Override
	protected void initMenu()
	{
		String c = "";
		if(Log.debug())
		{
			c = "Cacher le DEBUG.";
		}
		else
		{
			c = "Afficher le DEBUG.";
		}
		
		debug = new Command(c, "debug");
		debug.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				switchDebug();
			}
		});
		this.addCommand(debug);
		
		
		String e = "";
		if(Log.debug())
		{
			e = "Desactiver Erase";
		}
		else
		{
			e = "Activer Erase";
		}
		erase = new Command(e, "erase");
		erase.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				switchErase();
			}
		});
		this.addCommand(erase);
	}

	@Override
	protected void renderMenu()
	{
	}
}