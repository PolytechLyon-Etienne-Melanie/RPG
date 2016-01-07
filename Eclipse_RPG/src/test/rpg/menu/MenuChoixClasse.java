package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.classe.Guerrier;

public class MenuChoixClasse extends Menu 
{

	private Command guerrier;
	private Command mage;
	private Command voleur;
	private final String descGuerrier = "desc guerrier";
	
	public MenuChoixClasse(Game game)
	{
		super(game);
	}

	@Override
	protected void setDials() {
		this.addDial(new Dialogue("Il est temps de choisir votre classe !"));
		this.addDial(new Dialogue("Trois choix s'offre à vous :"));
		
	}

	@Override
	protected void setCommands() {
		guerrier = new Command("Arwed le guerrier", "1.");
		guerrier.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuDescriptionClasse(game, new Guerrier(), descGuerrier));
			}
		});
		
		mage = new Command("Sunilda la magicienne", "2.");
		mage.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.loadStory();
			}
		});
		
		voleur = new Command("Voleur", "3.");
		voleur.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.loadStory();
			}
		});
		
	}

}
