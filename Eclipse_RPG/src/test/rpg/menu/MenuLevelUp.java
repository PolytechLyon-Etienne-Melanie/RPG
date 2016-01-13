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
		super(game, "Level up : Stat choix");
		this.perso = perso;
	}

	@Override
	protected void setDials()
	{
		this.addDial(new Dialogue("Vous pouvez assigner " + perso.getPointsToAssing() + " points de comp�tences."));
		this.addDial(new Dialogue("Points de comp�tences actuels : " + perso.getCaracteristique()));
	}

	@Override
	protected void setCommands()
	{
		addForce = new Command("Voules-vous augmenter la force de votre h�ro ?", "oui");
		addForce.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseForce();
				next();
			}
		});
		
		addDex = new Command("Voules-vous augmenter la dext�rit� de votre h�ro ?", "oui");
		addDex.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseDexterite();
				next();
			}
		});
		
		addDef = new Command("Voules-vous augmenter la d�fense de votre h�ro ?", "oui");
		addDef.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseForce();
				next();
			}
		});
		
		addSante = new Command("Voules-vous augmenter la sant� de votre h�ro ?", "oui");
		addSante.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				perso.increaseSante();
				next();
			}
		});
		
		addMagie = new Command("Voules-vous augmenter la magie de votre h�ro ?", "oui");
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

}
