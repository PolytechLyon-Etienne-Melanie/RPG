package test.rpg.menu;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.console.printer.PrintColor;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.menu.item.MenuGetLoot;
import test.rpg.perso.Entity;
import test.rpg.perso.Personnage;
import test.rpg.perso.competence.Capacite;
import test.rpg.perso.equipement.Consommable;
import test.rpg.perso.equipement.Item;

public class MenuCombat extends Menu
{
	enum State {intro, yourTurn, enemyTurn, fin, distributeXp, distributeLoot};
	enum TurnState {chooseAction, listCommands, applyAction}
	enum ActionState {useCapa, useItem, pass}
	
	private int tour;
	private int persoTurn;
	
	private List<Personnage> confrerie;
	private List<Entity> monstres;
	
	private State state;
	private TurnState turnState;
	private ActionState actionState;
	
	private Personnage perso;
	private Entity enemy;
	private Capacite capacite;
	private Consommable consommable;
	private Entity target;
	
	private String effet;
	
	private Random rand;
	
	private Map<Integer, Entity> entities;
	
	public MenuCombat(Game game, List<Personnage> confrerie, List<Entity> monstres)
	{
		super(game, "Combat");
		
		tour = 0;
		persoTurn = 0;
		state = State.intro;
		rand = new Random();
		
		this.confrerie = confrerie;
		this.monstres = monstres;
		
		effet = "";
		
		initMap();
	}
	
	private void initMap()
	{
		entities = new HashMap<Integer, Entity>();
		int id = 0;
		Iterator<Personnage> i = confrerie.iterator();
		while(i.hasNext())
		{
			id++;
			Personnage p = i.next();
			p.setId_combat(id);
			entities.put(id, p);
		}
		Iterator<Entity> i2 = monstres.iterator();
		while(i2.hasNext())
		{
			id++;
			Entity e = i2.next();
			e.setId_combat(id);
			entities.put(id, e);
		}
	}

	private void setIntro()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed(String p)
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
			this.turnState = TurnState.chooseAction;
		}
		else
		{
			this.state = State.enemyTurn;
		}
		this.state = State.yourTurn;
		this.turnState = TurnState.chooseAction;
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
			setRetourChoice();
		}
		else
		{
			applyPersoTurn();
		}
		
	}
	
	private void setRetourChoice()
	{
		Command back = new Command("Retour", "back");
		back.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				retour();
			}
		});
		this.addCommand(back);
	}
	
	private void retour()
	{
		turnState = TurnState.chooseAction;
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
		else if(actionState == ActionState.pass)
		{
			setEndTurnCommand();
		}
	}
	
	private void setEndTurnCommand()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed(String p)
			{
				nextTurn();
			}
		});
		this.addCommand(key);
	}

	private void nextTurn()
	{
		persoTurn++;
		if(persoTurn >= confrerie.size())
		{
			persoTurn = 0;
			state = State.enemyTurn;
			this.turnState = TurnState.chooseAction;
		}
	}
	
	private void executeCapacite()
	{
		this.effet = this.capacite.effet(this.target, this.perso);
		setEndTurnCommand();
	}
	
	private void useConsommable()
	{
		setEndTurnCommand();
	}

	private void setPersoChoiceAction()
	{
		perso = confrerie.get(persoTurn);
		
		Command capa = new Command("Uiliser une capacitée.", "1");
		capa.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				useCapa();
			}
		});
		this.addCommand(capa);
		
		Command conso = new Command("Uiliser un consommable.", "2");
		conso.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				useConso();
			}
		});
		this.addCommand(conso);
		
		Command nada = new Command("Ne rien faire", "3");
		nada.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				passTurn();
			}
		});
		this.addCommand(nada);
	}
	
	private void useCapa()
	{
		this.actionState = ActionState.useCapa;
		this.turnState = TurnState.listCommands;
	}
	
	private void useConso()
	{
		this.actionState = ActionState.useItem;
		this.turnState = TurnState.listCommands;
	}

	private void setPersoTurn()
	{
		if(actionState == ActionState.useCapa)
		{
			setCapaPerso();
		}
		else if(actionState == ActionState.useItem)
		{
			setConsoPerso();
		}
	}
	
	private void setConsoPerso()
	{
		int n = 1;
		Iterator<Consommable> i = perso.getInventaire().getConsommables().iterator();
		while(i.hasNext())
		{
			Consommable conso = i.next();
			Command comp = new Command("Consommable   " + conso.toString(), ""+ n);
			comp.addObserver(new EventObserver(){
				@Override
				public void actionPerformed(String p)
				{
					setConsommable(conso, p);
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
				public void actionPerformed(String p)
				{
					passTurn();
				}
			});
			this.addCommand(key);
		}
	}
	
	private void setCapaPerso()
	{
		int n = 1;
		Iterator<Capacite> i = perso.getClasse().getCapa().iterator();
		while(i.hasNext())
		{
			Capacite capa = i.next();
			Command comp = new Command("Capacite   " + capa.getName(), ""+ n, "cible");
			comp.addObserver(new EventObserver(){
				@Override
				public void actionPerformed(String p)
				{
					setCapacite(capa, p);
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
				public void actionPerformed(String p)
				{
				}
			});
			this.addCommand(key);
		}
	}
	
	public void setCapacite(Capacite capa, String p)
	{
		try
		{
			int i = Integer.parseInt(p);
			this.target = entities.get(i);
			this.capacite = capa;
			this.turnState = TurnState.applyAction;
		}
		catch(NumberFormatException  e)
		{
			Log.e("Not a number, retry");
		}
		catch(IndexOutOfBoundsException e)
		{
			Log.e("Not a valid Target");
		}
	}
	
	public void setConsommable(Consommable conso, String p)
	{
		try
		{
			int i = Integer.valueOf(p);

			this.target = monstres.get(i);
			this.consommable = conso;
			this.turnState = TurnState.applyAction;
		}
		catch(NumberFormatException  e)
		{
			Log.e("Not a number, retry");
		}
	}
	
	public void passTurn()
	{
		this.turnState = TurnState.applyAction;
		this.actionState = ActionState.pass;
	}
	
	private void setEnemyTurn()
	{
		if(turnState == TurnState.chooseAction)
		{
			this.enemy = this.monstres.get(this.persoTurn);
			setEnemyAction();
			
			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver(){
	
				@Override
				public void actionPerformed(String p)
				{
					turnState = TurnState.applyAction;
				}
			});
			this.addCommand(key);
		}
		else if(turnState == TurnState.applyAction)
		{
			applyEnemyAction();
			
			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver(){
	
				@Override
				public void actionPerformed(String p)
				{
					nextEnemyTurn();
				}
			});
			this.addCommand(key);
		}
	}
	
	private void nextEnemyTurn()
	{
		persoTurn++;
		if(persoTurn >= monstres.size())
		{
			persoTurn = 0;
			state = State.yourTurn;
			this.turnState = TurnState.chooseAction;
		}
	}
	
	private void setEnemyAction()
	{
		capacite = enemy.getClasse().getCapa().get(0);
		target = chooseRandomTarget();
	}
	
	private Entity chooseRandomTarget()
	{
		return confrerie.get(0);
	}
	
	private void applyEnemyAction()
	{
		this.effet = this.capacite.effet(this.target, this.perso);
	}

	private void setFin()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed(String p)
			{
				distributeXP();
				game.setCurrentMenu(new MenuGetLoot(game, Item.getRandomLoot()));
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
		if(turnState == TurnState.chooseAction)
		{
			writeLine("C'est le tour de " + enemy.getNom(), PrintColor.CYAN);
		}
		else if(turnState == TurnState.applyAction)
		{
			writeLine(enemy.getNom() + " a attaqué " + target.getNom(), PrintColor.CYAN);
			writeLine("Il a utilisé la capacitée   " + capacite);
			writeLine(effet);
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
		else if(state == State.fin)
			setFin();
	}

	@Override
	protected void renderMenu()
	{
		renderConfrerie();
		renderEnemy();
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
		else if(turnState == TurnState.applyAction)
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
		writeLine("Il a utilisé la capacitée   " + capacite);
		writeLine(effet);
	}
	
	private void renderUseConsommable()
	{
		writeLine("Il a utilisé le consommable   " + consommable);
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
	
	private void renderConfrerie()
	{
		writeLine("--> Confrerie :");
		Iterator<Personnage> i = confrerie.iterator();
		while(i.hasNext())
		{
			Personnage p = i.next();
			writeLine("<" + p.getId_combat() + "> " + p.getNom() + " | " + p.getNiveau() + " | " + p.getClasse().getNom());
			String life = "::::::::::::::::::::::::::::::::::::";
			String notlife = "                                    ";
			float ratio = (float)(p.getSante()) / (float)(p.getSanteMax());
			Log.d(ratio);
			int l = (int)(life.length() * ratio);
			Log.d(l);
			PrintColor color = PrintColor.GREEN;
			if(ratio < 0.5)
				color = PrintColor.YELLOW;
			else if(ratio < 0.2)
				color = PrintColor.RED;
			writeLine("|" + life.substring(0, l) + notlife.substring(l)+ "|", color);
		}
		writeLine("");
	}
	
	private void renderEnemy()
	{
		writeLine("--> Enemies :");
		Iterator<Entity> i = monstres.iterator();
		while(i.hasNext())
		{
			Entity p = i.next();
			writeLine("<" + p.getId_combat() + "> " + p.getNom() + " | " + p.getNiveau() + " | " + p.getClasse().getNom());
			String life = "::::::::::::::::::::::::::::::::::::";
			String notlife = "                                    ";
			float ratio = (float)(p.getSante()) / (float)(p.getSanteMax());
			Log.d(ratio);
			int l = (int)(life.length() * ratio);
			Log.d(l);
			PrintColor color = PrintColor.GREEN;
			if(ratio < 0.2)
				color = PrintColor.RED;
			else if(ratio < 0.5)
				color = PrintColor.YELLOW;
			
			writeLine("|" + life.substring(0, l) + notlife.substring(l)+ "|", color);
		}
		writeLine("");
	}
}
