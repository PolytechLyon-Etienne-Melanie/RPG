package test.rpg.menu.item;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.Entity;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Consommable;
import test.rpg.perso.equipement.Item;

public class MenuUseItem extends Menu
{
	private Item item;
	private Personnage perso;
	
	private String s;
	
	public MenuUseItem(Game game, Menu retour, Item item, Personnage perso)
	{
		super(game, "Utiliser Objet", retour);
		this.item = item;
		this.perso = perso;
	}

	@Override
	protected void initMenu()
	{
		if(item instanceof Consommable)
		{
			s = ((Consommable) item).effet(perso, perso);
			perso.getInventaire().jeterItem(item);
			
		}
		else
		{
			s = "Vous ne pouvez pas utiliser cet objet.";
		}
	}

	@Override
	protected void renderMenu()
	{
		writeLine(s);
	}

}
