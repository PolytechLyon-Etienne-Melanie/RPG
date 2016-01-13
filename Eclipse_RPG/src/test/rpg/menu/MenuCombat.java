package test.rpg.menu;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.console.printer.PrintColor;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Entity;
import test.rpg.perso.Personnage;
import test.rpg.perso.competence.Capacite;
import test.rpg.perso.equipement.Consommable;
import test.rpg.perso.equipement.Item;

public class MenuCombat extends Menu
{
	enum State {intro, yourTurn, enemyTurn, fin, distributeXp};
	enum TurnState {chooseAction, listCommands, recapAction}
	enum ActionState {listCapa, listItems}
	
	private int tour;
	private int persoTurn;
	
	private List<Personnage> confrerie;
	private List<Entity> monstres;
	
	private State state;
	private TurnState turnState;
	private ActionState actionState;
	
	private Personnage perso;
	private Capacite capacite;
	
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
		if(turnState == TurnState.chooseAction)
		{
			
		}
		else
		if(turnState == TurnState.listCommands)
		{
			setPersoTurn();
		}
		
	}
	
	private void setPersoTurn()
	{
		perso = confrerie.get(persoTurn);
		
		if(actionState == ActionState.listCapa)
		{
			setCapaPerso();
		}
		else if(actionState == ActionState.listItems)
		{
			setConsoPerso();
		}
		else
		{
			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver(){

				@Override
				public void actionPerformed()
				{
					persoTurn++;
					if(persoTurn >= confrerie.size())
						state = State.enemyTurn;
				}
			});
			this.addCommand(key);
		}
	}
	
	private void setCapaPerso()
	{
		int n = 1;
		Iterator<Consommable> i = perso.getClasse().getCapa().iterator();
		while(i.hasNext())
		{
			Consommable conso = i.next();
			Command comp = new Command("Capacite : " + conso.toString(), ""+ n);
			comp.addObserver(new EventObserver(){
				@Override
				public void actionPerformed()
				{
					setCapacite(capa);
				}
			});
			
			this.addCommand(comp);
			n++;
		}
		
		if(n == 1)
		{
			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver(){

				@Override
				public void actionPerformed()
				{
				}
			});
			this.addCommand(key);
		}
	}
	
	private void setConsoPerso()
	{
		int n = 1;
		Iterator<Capacite> i = perso.getClasse().getCapa().iterator();
		while(i.hasNext())
		{
			Capacite capa = i.next();
			Command comp = new Command("Capacite : " + capa.toString(), ""+ n);
			comp.addObserver(new EventObserver(){
				@Override
				public void actionPerformed()
				{
					setCapacite(capa);
				}
			});
			
			this.addCommand(comp);
			n++;
		}
		
		if(n == 1)
		{
			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver(){

				@Override
				public void actionPerformed()
				{
				}
			});
			this.addCommand(key);
		}
	}
	
	public void setCapacite(Capacite capa)
	{
		this.capacite = capa;
	}
	
	public void setConsommable(Consommable conso)
	{
		
	}
	
	private void executeCapacite()
	{
		
	}
	
	private void useConsommable()
	{
		
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
		writeLine("C'est au tour de " + perso.getNom() + " d'attaquer.", PrintColor.CYAN);
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

	@Override
	protected void initMenu()
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

	@Override
	protected void renderMenu()
	{
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
	}
}
