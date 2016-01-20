package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;

public class MenuEnd extends Menu
{
	private boolean win;

	public MenuEnd(Game game, boolean win)
	{
		super(game);
		this.win = win;
	}

	@Override
	protected void initMenu()
	{
		Command menu = new Command("Appuyezsur <Entrer> pour retourner au menu principale.", "");
		menu.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuMain(game));
			}
		});

		this.addCommand(menu);
	}

	@Override
	protected void renderMenu()
	{
		if (win)
		{
			writeLine("___________.__        ");
			writeLine("\\_   _____/|__| ____  ");
			writeLine(" |    __)  |  |/    \\ ");
			writeLine(" |     \\   |  |   |  \\");
			writeLine(" \\___  /   |__|___|  /");
			writeLine("     \\/            \\/ ");
		} else
		{
			writeLine("   ________                        ________                     ");
			writeLine("  /  _____/_____    _____   ____   \\_____  \\___  __ ___________ ");
			writeLine(" /   \\  ___\\__  \\  /     \\_/ __ \\   /   |   \\  \\/ // __ \\_  __ \\");
			writeLine(" \\    \\_\\  \\/ __ \\|  Y Y  \\  ___/  /    |    \\   /\\  ___/|  | \\/");
			writeLine("  \\______  (____  /__|_|  /\\___  > \\_______  /\\_/  \\___  >__|   ");
			writeLine("         \\/     \\/      \\/     \\/          \\/          \\/       ");
		}
	}

}
