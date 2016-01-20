package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void renderMenu()
	{
		// TODO Auto-generated method stub
		
	}

}
