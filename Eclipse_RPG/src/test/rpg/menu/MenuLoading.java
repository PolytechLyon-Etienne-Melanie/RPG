package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;

public class MenuLoading extends Menu
{

	public MenuLoading(Game game)
	{
		super(game);
	}

	@Override
	protected void initMenu()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuMain(game));
			}
		});
		this.addCommand(key);
	}

	@Override
	protected void renderMenu()
	{
		writeLine("____________________  ________     _____                .__        ");
		writeLine("\\______   \\______   \\/  _____/    /     \\ _____    ____ |__|____   ");
		writeLine(" |       _/|     ___/   \\  ___   /  \\ /  \\\\__  \\  /    \\|  \\__  \\  ");
		writeLine(" |    |   \\|    |   \\    \\_\\  \\ /    Y    \\/ __ \\|   |  \\  |/ __ \\_");
		writeLine(" |____|_  /|____|    \\______  / \\____|__  (____  /___|  /__(____  /");
		writeLine("        \\/                  \\/          \\/     \\/     \\/        \\/ ");
	}
	
}
