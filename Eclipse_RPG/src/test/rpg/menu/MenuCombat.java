package test.rpg.menu;

import java.util.List;
import java.util.Random;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.console.printer.PrintColor;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Entity;
import test.rpg.perso.Personnage;

public class MenuCombat extends Menu
{
	enum State {intro, yourTurn, enemyTurn, fin};
	private int tour;
	private int persoTurn;
	
	private List<Personnage> confrerie;
	private List<Entity> monstres;
	private State state;
	
	private Random rand;
	
	public MenuCombat(Game game, List<Personnage> confrerie, List<Entity> monstres)
	{
		super(game, "Combat");
		
		tour = 0;
		persoTurn = 0;
		state = State.intro;
		rand = new Random();
		
		this.confrerie = confrerie;
		this.monstres = monstres;
	}

	@Override
	protected void setDials()
	{}

	@Override
	protected void setCommands()
	{}
	
	public void init()
	{
		if(state == State.intro)
		{
			setIntro();
		}
		else if(state == State.yourTurn)
		{
			setYourTurn();
		}
		else if(state == State.enemyTurn)
		{
			setEnemyTurn();
		}
		else
			setFin();
	}
	
	private void setIntro()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed()
			{}
		});
		this.addCommand(key);
	}
	
	private void setYourTurn()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed()
			{
				state = State.fin;
			}
		});
		this.addCommand(key);
	}
	
	private void setEnemyTurn()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed()
			{
				state = State.fin;
			}
		});
		this.addCommand(key);
	}
	
	private void setFin()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed()
			{
				distributeXP();
				game.setCurrentMenu(new MenuLevelUpScreen(game, confrerie.get(0)));
			}
		});
		this.addCommand(key);
	}
	
	private void distributeXP()
	{
		confrerie.get(0).earnXP(monstres.get(0).getXpVal());
	}
	
	public void render()
	{
		Log.d("render menu combat");
		write(PrintColor.ERASE.getAnsiColor());
		if (!game.isDebug())
			clearWindow();
		writeSeparator(title);
		
		if(state == State.intro)
		{
			renderIntro();
		}
		else if(state == State.yourTurn)
		{
			renderYourTurn();
		}
		else if(state == State.enemyTurn)
		{
			renderEnemyTurn();
		}
		else
			renderFin();
		
		writeSeparator("Commands");
		writeCommands();
	}

	private void renderFin()
	{
		writeLine("Fin du combat", PrintColor.CYAN);
	}

	private void renderEnemyTurn()
	{
		writeLine("c'est le tour de votre adversaire", PrintColor.CYAN);
	}

	private void renderYourTurn()
	{
		writeLine("c'est a votre tour", PrintColor.CYAN);
	}

	private void renderIntro()
	{
		if(rand.nextBoolean())
		{
			writeLine("Vous commencez le combat !", PrintColor.CYAN);
			this.state = State.yourTurn;
		}
		else
		{
			writeLine("Votre adversaire commence le combat !", PrintColor.CYAN);
			this.state = State.enemyTurn;
		}
	}
}
