package test.rpg.menu.item;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Armure;
import test.rpg.perso.equipement.Item;

public class MenuEquipArmure extends Menu {
	private Armure armure;
	private Personnage perso;

	public MenuEquipArmure(Game game, Menu retour, Item armure, Personnage perso) 
	{
		super(game, "S'équiper d'une arme", retour);
		this.perso = perso;
		if (armure instanceof Armure)
		{
			this.armure = (Armure) armure;
		}
	}


	@Override
	protected void initMenu() {
		if (armure != null)
		{
			perso.getInventaire().remplacerArmure(armure);
		}
	
	}

	@Override
	protected void renderMenu() {
		if (armure != null)
		{ 
			writeLine("Vous vous êtes équipé de l'armure : " + armure.getNom());
		}
		else 
		{
			writeLine("Ceci n'est pas une armure.");
		}
	}
}
