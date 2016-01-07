package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.Menu;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.perso.classe.Classe;

public class MenuDescriptionClasse extends Menu{
	
	private Classe classe;
	private String text;

	public MenuDescriptionClasse(Game game, Classe c, String text) {
		super(game);
		classe = c;
		this.text = text;
	}

	@Override
	protected void setDials() {
		this.addDial(new Dialogue("Histoire du "+classe.getNom()+" :"));
		this.addDial(new Dialogue(text));
		// ajouter caract de la classe classe.getCarac()
	}

	@Override
	protected void setCommands() {
		// creer commande retour + creer new perso
		
		
	}

}
