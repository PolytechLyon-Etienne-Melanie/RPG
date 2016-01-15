package test.rpg.menu.item;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.Entity;
import test.rpg.perso.equipement.Item;

public class MenuUseItem extends Menu
{
	private Entity src;
	private Entity target;
	private Item item;
	
	public MenuUseItem(Game game, Menu retour, Item item)
	{
		super(game, "Utiliser Item", retour);
		this.src = src;
		this.target = target;
		this.item = item;
	}

	@Override
	protected void initMenu()
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected void renderMenu()
	{
		writeLine(item.getNom());
	}

}
