package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Personnage;
import test.rpg.perso.classe.Classe;

public class MenuDescriptionClasse extends Menu{
	
	private Classe classe;
	private String nom;
	private String text;
	private Command confirmation;
	private Command retour;
	private Personnage hero;

	public MenuDescriptionClasse(Game game, Classe c, String nom, String text) {
		super(game);
		classe = c;
		this.nom = nom;
		this.text = text;
	}

	@Override
	protected void setDials() {
		this.addDial(new Dialogue("Histoire du "+classe.getNom()+" :"));
		this.addDial(new Dialogue(text));
		this.addDial(new Dialogue("Caract�ristique du h�ro : (Force, Dext�rit�, Sant�, D�fence, Magie)"+classe.getCarac())); 
	}

	@Override
	protected void setCommands() {
		confirmation = new Command("Etes-vous s�r de vouloir prendre ce h�ro ?", "oui");
		confirmation.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				//hero = new Personnage(nom, 1, classe); //help... comment on cr�e un pero ? ^^'
			}
		});
		retour = new Command("Revenir au personnages", "retour");
		retour.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuChoixClasse(game)); //c'est juste ?...
			}
		});
		this.addCommand(confirmation);
		this.addCommand(retour);
		// creer commande retour + creer new perso
		
		
	}

}
