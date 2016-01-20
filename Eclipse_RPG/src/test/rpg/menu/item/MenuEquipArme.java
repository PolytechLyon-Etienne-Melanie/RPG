package test.rpg.menu.item;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Arme;
import test.rpg.perso.equipement.Item;

public class MenuEquipArme extends Menu {
	
	private Arme arme;
	private Personnage perso;

	public MenuEquipArme(Game game, Menu retour, Item arme, Personnage perso) 
	{
		super(game, "S'équiper d'une arme", retour);
		this.perso = perso;
		if (arme instanceof Arme)
		{
			this.arme = (Arme) arme;
		}
	}

	@Override
	protected void initMenu() {
		if (arme != null)
		{
			perso.getInventaire().remplacerArme(arme);
		}
		
	}

	@Override
	protected void renderMenu() {
		if (arme != null)
		{ 
			writeLine("Vous vous êtes équipé de l'arme : " + arme.getNom());
		}
		else 
		{
			writeLine("Ceci n'est pas une arme.");
		}
	}
	
	

}
