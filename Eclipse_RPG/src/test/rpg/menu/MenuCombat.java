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
	enum TurnState {chooseAction, listCommands, applyAction}
	enum ActionState {useCapa, useItem}
	
	private int tour;
	private int persoTurn;
	
	private List<Personnage> confrerie;
	private List<Entity> monstres;
	
	private State state;
	private TurnState turnState;
	private ActionState actionState;
	
	private Personnage perso;
	private Capacite capacite;
	private Consommable consommable;
	private String actionComment;
	
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
			{
				setAttaquant();
			}
		});
		this.addCommand(key);
	}
	
	private void setAttaquant()
	{
		if(rand.nextBoolean())
		{
			this.state = State.yourTurn;
		}
		else
		{
			this.state = State.enemyTurn;
		}
	}
	
	private void setYourTurn()
	{
		if(turnState == TurnState.chooseAction)
		{
			setPersoChoiceAction();
		}
		else if(turnState == TurnState.listCommands)
		{
			setPersoTurn();
		}
		else
		{
			applyPersoTurn();
		}
		
	}
	
	private void applyPersoTurn()
	{
		if(actionState == ActionState.useCapa)
		{
			executeCapacite();
		}
		else if(actionState == ActionState.useItem)
		{
			useConsommable();
		}
	}
	
	private void executeCapacite()
	{
		
	}
	
	private void useConsommable()
	{
		
	}

	private void setPersoChoiceAction()
	{
		Command capa = new Command("Uiliser une capacitée.", "1");
		capa.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				useCapa();
			}
		});
		this.addCommand(capa);
		
		Command conso = new Command("Uiliser un consommable.", "2");
		conso.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				useConso();
			}
		});
		this.addCommand(conso);
		
		Command nada = new Command("Ne rien faire", "3");
		nada.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				passTurn();
			}
		});
		this.addCommand(nada);
	}
	
	private void useCapa()
	{
		this.actionState = ActionState.useCapa;
	}
	
	private void useConso()
	{
		this.actionState = ActionState.useItem;
	}

	private void setPersoTurn()
	{
		perso = confrerie.get(persoTurn);
		
		if(actionState == ActionState.useCapa)
		{
			setCapaPerso();
		}
		else if(actionState == ActionState.useItem)
		{
			setConsoPerso();
		}
	}
	
	private void setCapaPerso()
	{
		int n = 1;
		Iterator<Consommable> i = perso.getInventaire().getConsommables().iterator();
		while(i.hasNext())
		{
			Consommable conso = i.next();
			Command comp = new Command("Capacite : " + conso.toString(), ""+ n);
			comp.addObserver(new EventObserver(){
				@Override
				public void actionPerformed()
				{
					setConsommable(conso);
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
					passTurn();
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
		this.turnState = TurnState.applyAction;
	}
	
	public void setConsommable(Consommable conso)
	{
		this.consommable = conso;
		this.turnState = TurnState.applyAction;
	}
	
	public void passTurn()
	{
		this.turnState = TurnState.applyAction;
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
	
	private void renderIntro()
	{
		writeLine("Debut du combat", PrintColor.CYAN);
	}
	
	private void renderYourTurn()
	{
		if(turnState == TurnState.chooseAction)
		{
			writeLine("C'est au tour de " + perso.getNom() + " d'attaquer.", PrintColor.CYAN);
			writeLine("Choisisssez une action");
		}
		else if(turnState == TurnState.listCommands)
		{
			writeLine("C'est au tour de " + perso.getNom() + " d'attaquer.", PrintColor.CYAN);
			renderSetPersoTurn();
		}
		else
		{
			writeLine(perso.getNom() + " a fini son tour.", PrintColor.CYAN);
			renderApplyPersoTurn();
		}
		
	}
	
	private void renderApplyPersoTurn()
	{
		if(actionState == ActionState.useCapa)
		{
			renderExecuteCapacite();
		}
		else if(actionState == ActionState.useItem)
		{
			renderUseConsommable();
		}
	}
	
	private void renderExecuteCapacite()
	{
		writeLine("Il a utilisé la capacitée : " + capacite);
		writeLine(actionComment);
	}
	
	private void renderUseConsommable()
	{
		writeLine("Il a utilisé le consommable : " + consommable);
	}

	private void renderSetPersoTurn()
	{
		if(actionState == ActionState.useCapa)
		{
			writeLine("Choisisssez une capacite");
		}
		else if(actionState == ActionState.useItem)
		{
			writeLine("Choisisssez un consommable");
		}
	}
}
