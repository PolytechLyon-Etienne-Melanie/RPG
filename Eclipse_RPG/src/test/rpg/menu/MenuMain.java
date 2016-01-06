package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.Menu;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.story.event.EventObserver;

public class MenuMain extends Menu
{
	private Command quit;
	private Command loadStory;
	
	public MenuMain(Game game)
	{
		super(game);
	}

	@Override
	protected void setDials()
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void setCommands()
	{
		loadStory = new Command("Charger une histoire", "load");
		loadStory.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.loadStory();
			}
		});
		quit = new Command("Quitter le jeu", "quit");
		quit.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				System.exit(0);
			}
		});
		this.addCommand(loadStory);
		this.addCommand(quit);
	}

}
