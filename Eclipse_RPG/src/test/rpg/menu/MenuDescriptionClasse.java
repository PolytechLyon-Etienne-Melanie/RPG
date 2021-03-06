package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
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

	
	protected void setCommands() {
		confirmation = new Command("Etes-vous s�r de vouloir prendre ce h�ro ?", "oui");
		confirmation.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				hero = new Personnage(nom, 1, classe);
				game.setHero(hero);
				game.setMenuStory(event);
			}
		});
		retour = new Command("Revenir au personnages", "retour");
		retour.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuChoixClasse(game, event));
			}
		});
		this.addCommand(confirmation);
		this.addCommand(retour);
		
		
	}

	@Override
	protected void initMenu() {
		setCommands();
		
	}

	@Override
	protected void renderMenu() {
		writeLine("Histoire du "+classe.getNom()+" :");
		writeLine(text);
		writeLine("Caract�ristique du h�ro : (Force, Dext�rit�, Sant�, D�fence, Magie)"+classe.getCarac());
		
	}

}
