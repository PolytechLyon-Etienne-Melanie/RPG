package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Personnage;

public class MenuLevelUp extends Menu
{
	private Personnage perso;
	private Command addForce;
	private Command addDex;
	private Command addDef;
	private Command addMagie;
	private Command addSante;


	
	public MenuLevelUp(Game game, Personnage perso)
	{
		super(game, "Stat choix");
		this.perso = perso;
	}


	protected void setCommands()
	{
		addForce = new Command("Voules-vous augmenter la force de votre héro ?", "1");
		addForce.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseForce();
				next();
			}
		});
		
		addDex = new Command("Voules-vous augmenter la dextérité de votre héro ?", "2");
		addDex.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseDexterite();
				next();
			}
		});
		
		addDef = new Command("Voules-vous augmenter la défense de votre héro ?", "3");
		addDef.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseForce();
				next();
			}
		});
		
		addSante = new Command("Voules-vous augmenter la santé de votre héro ?", "4");
		addSante.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseSante();
				next();
			}
		});
		
		addMagie = new Command("Voules-vous augmenter la magie de votre héro ?", "5");
		addMagie.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseMagie();
				next();
			}
		});
		
		this.addCommand(addForce);
		this.addCommand(addDef);
		this.addCommand(addDex);
		this.addCommand(addSante);
		this.addCommand(addMagie);
	}
	
	private void next(){
		if (perso.getPointsToAssing() == 0)
		{
			game.returnToStory();
		}
	}

	@Override
	protected void initMenu() {
		setCommands();
		
	}

	@Override
	protected void renderMenu() {	
		writeLine("Félicitation !! Vous avez gagné un niveau !");
		writeLine("Vous pouvez assigner " + perso.getPointsToAssing() + " points de compétences.");
		writeLine("Points de compétences actuels : " + perso.getCaracteristique());
		
	}

}
