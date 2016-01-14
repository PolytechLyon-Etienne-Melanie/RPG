package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;

public class MenuMain extends Menu
{
	private Command quit;
	private Command loadStory;
	private Command param;
	
	public MenuMain(Game game)
	{
		super(game, "Menu Principal");
	}

	@Override
	protected void initMenu()
	{
		loadStory = new Command("Charger une histoire", "load");
		loadStory.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.loadStory();
			}
		});
		quit = new Command("Quitter le jeu", "quit");
		quit.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				System.exit(0);
			}
		});
		param = new Command("Parametres", "settings");
		param.addObserver(new EventObserver(){
			EventObserver setMenu(Menu menu)
			{
				this.menu = menu;
				return this;
			}
			private Menu menu;
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuParametre(game, menu));
			}
		}.setMenu(this));
		this.addCommand(param);
		this.addCommand(loadStory);
		this.addCommand(quit);
	}

	@Override
	protected void renderMenu()
	{
		// TODO Auto-generated method stub
		
	}
}
