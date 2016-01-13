package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.StoryEvent;
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
	private StoryEvent event;
	public MenuDescriptionClasse(Game game, Classe c, String nom, String text, StoryEvent event) {
		super(game);
		classe = c;
		this.nom = nom;
		this.text = text;
		this.event = event;
	}

	@Override
	protected void setDials() {
		this.addDial(new Dialogue("Histoire du "+classe.getNom()+" :"));
		this.addDial(new Dialogue(text));
		this.addDial(new Dialogue("Caractéristique du héro : (Force, Dextérité, Santé, Défence, Magie)"+classe.getCarac())); 
	}

	@Override
	protected void setCommands() {
		confirmation = new Command("Etes-vous sûr de vouloir prendre ce héro ?", "oui");
		confirmation.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				hero = new Personnage(game, nom, 1, classe);
				game.setHero(hero);
				game.setMenuStory(event);
			}
		});
		retour = new Command("Revenir au personnages", "retour");
		retour.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuChoixClasse(game, event));
			}
		});
		this.addCommand(confirmation);
		this.addCommand(retour);
		
		
	}

}
