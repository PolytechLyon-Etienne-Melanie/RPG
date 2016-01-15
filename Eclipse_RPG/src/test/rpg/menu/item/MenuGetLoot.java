package test.rpg.menu.item;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.CommandBackStory;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Item;

public class MenuGetLoot extends Menu
{
	private Item item;
	private Personnage perso;
	private boolean looted;
	
	public MenuGetLoot(Game game, Item i)
	{
		super(game, "Loot");
		this.item = i;
		perso = game.getHero();
		looted = false;
	}

	@Override
	protected void initMenu()
	{
		if(!looted)
		{
			perso.getInventaire().addItem(item);
			looted = true;
		}
		this.addCommand(new CommandBackStory(game));
	}

	@Override
	protected void renderMenu()
	{
		// TODO Auto-generated method stub
		writeLine("Vous optenez : "+item+".");
		writeLine("L'item est rangé dans l'inventaire de " + perso.getNom());
	}
}
