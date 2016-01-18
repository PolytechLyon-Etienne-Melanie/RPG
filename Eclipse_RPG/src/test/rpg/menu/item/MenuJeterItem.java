package test.rpg.menu.item;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Item;

public class MenuJeterItem extends Menu{
	
	private Item item;
	private Personnage perso;
	
	public MenuJeterItem(Game game, Menu retour, Item item, Personnage perso) {
		super(game, "Jeter item", retour);
		this.item = item;
		this.perso = perso;
		
	}

	@Override
	protected void initMenu() {
		perso.getInventaire().jeterItem(item);
		
	}

	@Override
	protected void renderMenu() {
		writeLine("Vous venez de jeter : " + item.getNom());
		
	}

}
