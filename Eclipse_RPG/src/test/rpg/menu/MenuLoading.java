package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
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
	protected void setDials()
	{
		this.addDial(new Dialogue("____________________  ________     _____                .__        "));
		this.addDial(new Dialogue("\\______   \\______   \\/  _____/    /     \\ _____    ____ |__|____   "));
		this.addDial(new Dialogue(" |       _/|     ___/   \\  ___   /  \\ /  \\\\__  \\  /    \\|  \\__  \\  "));
		this.addDial(new Dialogue(" |    |   \\|    |   \\    \\_\\  \\ /    Y    \\/ __ \\|   |  \\  |/ __ \\_"));
		this.addDial(new Dialogue(" |____|_  /|____|    \\______  / \\____|__  (____  /___|  /__(____  /"));
		this.addDial(new Dialogue("        \\/                  \\/          \\/     \\/     \\/        \\/ "));
		
	}

	@Override
	protected void setCommands()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuMain(game));
			}
		});
		this.addCommand(key);
	}
	
}
