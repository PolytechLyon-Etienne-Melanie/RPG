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
import test.rpg.perso.effet.Effet;
import test.rpg.perso.equipement.Consommable;
import test.rpg.perso.equipement.Item;

public class MenuCombat extends Menu
{
	enum State
	{
		intro, yourTurn, enemyTurn, fin, distributeXp, distributeLoot
	};

	enum TurnState
	{
		chooseAction, listCommands, applyAction, updateEffet
	}

	enum ActionState
	{
		useCapa, useItem, pass
	}

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

	private boolean win;

	public MenuCombat(Game game, List<Personnage> confrerie, List<Entity> monstres)
	{
		super(game, "Combat");

		persoTurn = 0;
		state = State.intro;
		rand = new Random();

		this.confrerie = confrerie;
		this.monstres = monstres;

		effet = "";
		win = true;
		initMap();
	}

	private void initMap()
	{
		entities = new HashMap<Integer, Entity>();
		int id = 0;
		Iterator<Personnage> i = confrerie.iterator();
		while (i.hasNext())
		{
			id++;
			Personnage p = i.next();
			p.setId_combat(id);
			entities.put(id, p);
		}
		Iterator<Entity> i2 = monstres.iterator();
		while (i2.hasNext())
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
		key.addObserver(new EventObserver()
		{

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
		if (rand.nextBoolean())
		{
			this.state = State.yourTurn;
			this.turnState = TurnState.chooseAction;
		} else
		{
			this.state = State.enemyTurn;
		}
		this.state = State.yourTurn;
		this.turnState = TurnState.chooseAction;
	}

	private void setYourTurn()
	{
		if (turnState == TurnState.chooseAction)
		{
			setPersoChoiceAction();
		} else if (turnState == TurnState.listCommands)
		{
			setPersoTurn();
			setRetourChoice();
		} else if (turnState == TurnState.applyAction)
		{
			applyPersoTurn();
		} else if (turnState == TurnState.updateEffet)
		{
			updatePersoEffet();
		}

	}

	private void setRetourChoice()
	{
		Command back = new Command("Retour", "back");
		back.addObserver(new EventObserver()
		{
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
		if (actionState == ActionState.useCapa)
		{
			executeCapacite();
		} else if (actionState == ActionState.useItem)
		{
			useConsommable();
		} else if (actionState == ActionState.pass)
		{
			setEffectCommand();
		}
	}

	private void updatePersoEffet()
	{
		effet = perso.updateEffet();
		setEndTurnCommand();
	}

	private void setEndTurnCommand()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver()
		{

			@Override
			public void actionPerformed(String p)
			{
				nextTurn();
			}
		});
		this.addCommand(key);
	}

	private void setEffectCommand()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver()
		{

			@Override
			public void actionPerformed(String p)
			{
				applyEffect();
			}
		});
		this.addCommand(key);
	}

	private void applyEffect()
	{
		this.turnState = TurnState.updateEffet;
	}

	private void nextTurn()
	{
		persoTurn++;
		if (persoTurn >= confrerie.size())
		{
			persoTurn = 0;
			state = State.enemyTurn;
			this.turnState = TurnState.chooseAction;
		} else if (!confrerie.get(persoTurn).isAlive())
			nextEnemyTurn();

		verifWin();
	}

	private void verifWin()
	{
		boolean alive = false;
		Iterator<Entity> i = monstres.iterator();
		while (i.hasNext())
		{
			Entity p = i.next();
			if (p.isAlive())
				alive = true;
		}

		if (!alive)
		{
			win = true;
			this.state = State.fin;
		}

	}

	private void executeCapacite()
	{
		this.effet = this.capacite.effet(this.target, this.perso);
		setEffectCommand();
	}

	private void useConsommable()
	{
		this.effet = this.consommable.effet(this.perso, this.perso);
		setEffectCommand();
	}

	private void setPersoChoiceAction()
	{
		perso = confrerie.get(persoTurn);

		Command capa = new Command("Uiliser une capacitée.", "1");
		capa.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				useCapa();
			}
		});
		this.addCommand(capa);

		Command conso = new Command("Uiliser un consommable.", "2");
		conso.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				useConso();
			}
		});
		this.addCommand(conso);

		Command nada = new Command("Ne rien faire", "3");
		nada.addObserver(new EventObserver()
		{
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
		if (actionState == ActionState.useCapa)
		{
			setCapaPerso();
		} else if (actionState == ActionState.useItem)
		{
			setConsoPerso();
		}
	}

	private void setConsoPerso()
	{
		int n = 1;
		Iterator<Consommable> i = perso.getInventaire().getConsommables().iterator();
		while (i.hasNext())
		{
			Consommable conso = i.next();
			Command comp = new Command("Consommable : " + conso.toString(), "" + n);
			comp.addObserver(new EventObserver()
			{
				@Override
				public void actionPerformed(String p)
				{
					setConsommable(conso, p);
				}
			});
			this.addCommand(comp);

			n++;
		}

		if (n == 1)
		{
			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver()
			{

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
		while (i.hasNext())
		{
			Capacite capa = i.next();
			Command comp = null;
			if (capa.isTargeted())
				comp = new Command(" -> " + capa, "" + n, "n° cible");
			else
				comp = new Command(" -> " + capa, "" + n);
			comp.addObserver(new EventObserver()
			{
				@Override
				public void actionPerformed(String p)
				{
					setCapacite(capa, p);
				}
			});

			this.addCommand(comp);
			n++;
		}

		if (n == 1)
		{
			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver()
			{

				@Override
				public void actionPerformed(String p)
				{
					passTurn();
				}
			});
			this.addCommand(key);
		}
	}

	public void setCapacite(Capacite capa, String p)
	{
		try
		{
			if (capa.isTargeted())
			{
				int i = Integer.parseInt(p);
				this.target = entities.get(i);
				this.capacite = capa;
				this.turnState = TurnState.applyAction;
			} else
			{
				this.capacite = capa;
				this.turnState = TurnState.applyAction;
			}
		} catch (NumberFormatException e)
		{
			Log.e("Not a number, retry");
		} catch (IndexOutOfBoundsException e)
		{
			Log.e("Not a valid Target");
		}
	}

	public void setConsommable(Consommable conso, String p)
	{
		this.consommable = conso;
		this.turnState = TurnState.applyAction;
	}

	public void passTurn()
	{
		this.turnState = TurnState.applyAction;
		this.actionState = ActionState.pass;
	}

	private void setEnemyTurn()
	{
		if (turnState == TurnState.chooseAction)
		{
			this.enemy = this.monstres.get(this.persoTurn);
			setEnemyAction();

			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver()
			{

				@Override
				public void actionPerformed(String p)
				{
					turnState = TurnState.applyAction;
				}
			});
			this.addCommand(key);
		} else if (turnState == TurnState.applyAction)
		{
			applyEnemyAction();

			KeyObserver key = new KeyObserver();
			key.addObserver(new EventObserver()
			{

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
		if (persoTurn >= monstres.size())
		{
			persoTurn = 0;
			state = State.yourTurn;
		}
		this.turnState = TurnState.chooseAction;
		verifLoose();
	}

	private void verifLoose()
	{
		boolean alive = false;
		Iterator<Personnage> i = confrerie.iterator();
		while (i.hasNext())
		{
			Personnage p = i.next();
			if (p.isAlive())
				alive = true;
		}

		if (!alive)
		{
			win = false;
			this.state = State.fin;
		}

	}

	private void setEnemyAction()
	{
		List<Capacite> cap = enemy.getClasse().getCapa();
		capacite = cap.get(rand.nextInt(cap.size()));
		target = chooseRandomTarget();
	}

	private Entity chooseRandomTarget()
	{
		return confrerie.get(0);
	}

	private void applyEnemyAction()
	{
		if(!enemy.isAlive())
		{
			effet = enemy.getNom() + " ne peut pas attaquer.";
		}
		else
		{
			this.effet = this.capacite.effet(this.target, this.enemy);
			enemy.updateEffet();
		}
	}

	private void setFin()
	{

		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				if (win)
				{
					state = State.distributeXp;
				} else
					game.setCurrentMenu(new MenuEnd(game, false));
			}
		});
		this.addCommand(key);
	}

	private void distributeXP()
	{
		Iterator<Entity> i = monstres.iterator();
		int xp =0;
		while(i.hasNext())
		{
			xp += i.next().getXpVal();
		}
		
		confrerie.get(0).earnXP(xp);
		effet = perso.getNom() + " a gagné " + xp + " points d'experience";
		if (perso.getPointsToAssing() > 0)
			effet += " et viens de progresser de 1 niveau.";
		else
			effet += ".";

		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuGetLoot(game, Item.getRandomLoot()));
			}
		});
		this.addCommand(key);
	}

	private void renderFin()
	{
		writeLine("Fin du combat.", PrintColor.CYAN);
		if (win)
			writeLine("Vous avez gagné.");
		else
			writeLine("Vous avez perdu.");
	}

	private void renderEnemyTurn()
	{
		if (turnState == TurnState.chooseAction)
		{
			writeLine("C'est le tour de " + enemy.getNom(), PrintColor.CYAN);
		} else if (turnState == TurnState.applyAction)
		{
			if(enemy.isAlive())
			{
				writeLine(enemy.getNom() + " a attaqué " + target.getNom(), PrintColor.CYAN);
				writeLine("Compétence utilisée : " + capacite.getName());
			}
			writeLine(effet);
		}
	}

	@Override
	protected void initMenu()
	{
		if (state == State.intro)
		{
			setIntro();
		} else if (state == State.yourTurn)
		{
			setYourTurn();
		} else if (state == State.enemyTurn)
		{
			setEnemyTurn();
		} else if (state == State.fin)
			setFin();
		else if (state == State.distributeXp)
			distributeXP();
	}

	@Override
	protected void renderMenu()
	{
		renderConfrerie();
		renderEnemy();
		if (state == State.intro)
		{
			renderIntro();
		} else if (state == State.yourTurn)
		{
			renderYourTurn();
		} else if (state == State.enemyTurn)
		{
			renderEnemyTurn();
		} else if (state == State.fin)
			renderFin();
		else if (state == State.distributeXp)
		{
			writeLine(effet);
		}
	}

	private void renderIntro()
	{
		writeLine("Debut du combat", PrintColor.CYAN);
	}

	private void renderYourTurn()
	{
		if (turnState == TurnState.chooseAction)
		{
			writeLine("C'est au tour de " + perso.getNom() + " d'attaquer.", PrintColor.CYAN);
			writeLine("Choisisssez une action");
		} else if (turnState == TurnState.listCommands)
		{
			writeLine("C'est au tour de " + perso.getNom() + " d'attaquer.", PrintColor.CYAN);
			renderSetPersoTurn();
		} else if (turnState == TurnState.applyAction)
		{
			writeLine(perso.getNom() + " a fini son tour.", PrintColor.CYAN);
			renderApplyPersoTurn();
		} else if (turnState == TurnState.updateEffet)
		{
			if (effet != "")
			{
				writeLine("Mise à jour des effets de " + perso.getNom() + ":");
				writeLine(effet);
			}
		}
	}

	private void renderApplyPersoTurn()
	{
		if (actionState == ActionState.useCapa)
		{
			renderExecuteCapacite();
		} else if (actionState == ActionState.useItem)
		{
			renderUseConsommable();
		}
	}

	private void renderExecuteCapacite()
	{
		writeLine("Compétence utilisée : " + capacite.getName());
		writeLine(effet);
		if (target != null && target.getSante() <= 0)
		{
			writeLine(target.getNom() + " a succombé.");
		}
	}

	private void renderUseConsommable()
	{
		writeLine("Objet utilisé : " + consommable.getNom());
		writeLine(effet);
	}

	private void renderSetPersoTurn()
	{
		if (actionState == ActionState.useCapa)
		{
			writeLine("Choisisssez une compétence :");
		} else if (actionState == ActionState.useItem)
		{
			writeLine("Choisisssez un objet :");
		}
	}

	private void renderConfrerie()
	{
		writeLine("--> Confrerie :");
		Iterator<Personnage> i = confrerie.iterator();
		while (i.hasNext())
		{
			Personnage p = i.next();
			renderEntity(p);
		}
		writeLine("");
	}

	private void renderEnemy()
	{
		writeLine("--> Enemies :");
		Iterator<Entity> i = monstres.iterator();
		while (i.hasNext())
		{
			Entity p = i.next();
			renderEntity(p);
		}
		writeLine("");
	}

	private void renderEntity(Entity entity)
	{
		write("<" + entity.getId_combat() + "> ", PrintColor.PURPLE);
		writeLine(entity.getNom() + " | Niveau : " + entity.getNiveau() + " | " + entity.getClasse().getNom());
		writeLine("Stats classe: " + entity.getClasse().getCarac());
		String life = "::::::::::::::::::::::::::::::::::::";
		String notlife = "                                    ";
		float ratio = (float) (entity.getSante()) / (float) (entity.getSanteMax());
		Log.d(ratio);
		int l = (int) (life.length() * ratio);
		Log.d(l);
		PrintColor color = PrintColor.GREEN;
		if (ratio < 0.2)
			color = PrintColor.RED;
		else if (ratio < 0.5)
			color = PrintColor.YELLOW;

		writeLine("|" + life.substring(0, l) + notlife.substring(l) + "| " + entity.getSante() + "/"
				+ entity.getSanteMax(), color);
		renderEffets(entity);
	}

	private void renderEffets(Entity entity)
	{
		Iterator<Effet> i = entity.getEffets().iterator();
		while (i.hasNext())
		{
			Effet e = i.next();
			writeLine(" - " + e.toString(), PrintColor.YELLOW);
		}
	}
}
