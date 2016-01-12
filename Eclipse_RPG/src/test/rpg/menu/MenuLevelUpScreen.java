package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Personnage;

public class MenuLevelUpScreen extends Menu
{
	private Personnage perso;
	
	public MenuLevelUpScreen(Game game, Personnage p)
	{
		super(game, "Level up");
		this.perso = p;
	}

	@Override
	protected void setDials()
	{
		this.addDial(new Dialogue("LEVEL UP"));
	}

	@Override
	protected void setCommands()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuLevelUp(game, perso));
			}
		});
		this.addCommand(key);
	}
}
