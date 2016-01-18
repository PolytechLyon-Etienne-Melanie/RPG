package test.rpg.menu.item;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.Entity;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Item;

public class MenuUseItem extends Menu
{
	private Entity src;
	private Entity target;
	private Item item;
	private Personnage perso;
	
	public MenuUseItem(Game game, Menu retour, Item item, Personnage perso)
	{
		super(game, "Utiliser Item", retour);
		this.src = src;
		this.target = target;
		this.item = item;
		this.perso = perso;
	}

	@Override
	protected void initMenu()
	{
		perso.getInventaire().jeterItem(item);
	}

	@Override
	protected void renderMenu()
	{
		writeLine(item.getNom());
	}

}
