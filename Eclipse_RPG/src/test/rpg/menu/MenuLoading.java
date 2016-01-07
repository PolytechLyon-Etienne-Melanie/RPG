package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.Menu;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
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
		this.addDial(new Dialogue("  |\\_/|        ****************************    (\\_/)"));
		this.addDial(new Dialogue(" / @ @ \\       *  \"Purrrfectly pleasant\"  *   (='.'=)"));
		this.addDial(new Dialogue("( > º < )      *       Poppy Prinz        *   (\")_(\")"));
		this.addDial(new Dialogue(" `>>x<<´       *   (pprinz@example.com)   *"));
		this.addDial(new Dialogue(" /  O  \\       ****************************"));
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
