package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Personnage;

public class MenuLevelUp extends Menu
{
	private Personnage perso;
	
	public MenuLevelUp(Game game, Personnage perso)
	{
		super(game, "Level up : Stat choix");
		this.perso = perso;
	}

	@Override
	protected void setDials()
	{
		this.addDial(new Dialogue("Vous pouvez assigner " + perso.getPointsToAssing() + " points de compétence."));
	}

	@Override
	protected void setCommands()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed()
			{
				game.returnToStory();
			}
		});
		this.addCommand(key);
	}

}
