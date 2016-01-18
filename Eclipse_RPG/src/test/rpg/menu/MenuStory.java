package test.rpg.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.StoryLink;
import test.rpg.engine.story.event.Event;
import test.rpg.engine.story.event.EventCombat;
import test.rpg.engine.story.event.EventDialogue;
import test.rpg.engine.story.event.EventEntity;
import test.rpg.engine.story.event.EventLoot;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.menu.item.MenuGetLoot;
import test.rpg.menu.item.MenuInventaire;
import test.rpg.perso.Entity;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Item;

public class MenuStory extends Menu
{
	private StoryEvent event;
	private Command inventaire;
	
	public MenuStory(Game game, StoryEvent event)
	{
		super(game);
		this.event = event;
	}
	
	protected boolean setDials()
	{
		Iterator<Event> i = event.getEvents().iterator();
		boolean stop = false;
		while(i.hasNext() && !stop)
		{
			Event e = i.next();
			if(e instanceof EventLoot)
			{
				EventLoot el = (EventLoot) e;
				Dialogue diag = new Dialogue(el.getDialogue());
				this.addDial(diag);
				if(!el.isDone())
				{
					this.onLoot(el);
					stop = true;
				}
			}
			else if(e instanceof EventDialogue)
			{
				EventDialogue ed = (EventDialogue) e;
				Dialogue diag = new Dialogue(ed.getDialogue());
				this.addDial(diag);
			}
			else if(e instanceof EventCombat)
			{
				EventCombat ec = (EventCombat) e;
				this.addDial(new Dialogue(ec.getTitle()));
				if(!ec.isDone())
				{
					this.startCombat(ec);
					stop = true;
				}
			}
		}
		
		return stop;
	}
	
	public void generateEndChoice()
	{
		if(game.getHero() != null)
		{
			inventaire = new Command("Accéder à votre inventaire", "invent");
			inventaire.addObserver(new EventObserver(){
				@Override
				public void actionPerformed(String p)
				{
					game.setCurrentMenu(new MenuInventaire(game, game.getHero()));
				}
				
			});
			this.addCommand(inventaire);
		}
		
		ArrayList<StoryLink> links = new ArrayList<StoryLink>(game.getStoryManager().getStory().getGraph().getOutEdges(event));
		if(links.size() == 1)
		{
			KeyObserver key = new KeyObserver();
			key.addObserver(generateObserver(links.get(0)));
			this.addCommand(key);
		}
		else
		{
			Iterator<StoryLink> i = links.iterator();
			int id = -1;
			while (i.hasNext())
			{
				id++;
				StoryLink link = i.next();
				Command com = generateCommand(link, id);
				com.addObserver(generateObserver(link));
				this.addCommand(com);
			}
	
			if (id == -1)
			{
				endStory();
			}
		}
	}

	private void endStory()
	{
		// game.getConsole().getWriter().writeLine(">Quit<");
	}

	private Command generateCommand(StoryLink link, int id)
	{
		return new Command(link.getLink(), "" + id);
	}
	
	public EventObserver generateObserver(StoryLink l)
	{
		return new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				// System.out.println("next event");
				game.getStoryManager().goNextStoryEvent(l);
			}
		};
	}
	
	public void onLoot(EventLoot loot)
	{
		Command combatC = new Command("Appuyez sur <Entrer> pour récupérer l'objet.", "");
		combatC.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuGetLoot(game, loot.geenerateLoot()));
				loot.done();
			}
		});
		
		this.addCommand(combatC);
		//game.getHero().earnXP(e.getXpVal());
	}
	
	public void startCombat(EventCombat combat)
	{
		KeyObserver key = new KeyObserver();
		Command combatC = new Command("Appuyez sur <Entrer> pour commencer le combat.", "");
		combatC.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				List<Personnage> confrerie = new ArrayList<Personnage>();
				List<Entity> monstres = new ArrayList<Entity>();
				confrerie.add(game.getHero());

				Iterator<EventEntity> it = combat.getMonsters().iterator();
				while(it.hasNext())
				{
					Entity en = it.next().genEntity();
					monstres.add(en);
				}
				game.setCurrentMenu(new MenuCombat(game, confrerie, monstres));
				combat.done();
			}
		});
		
		this.addCommand(combatC);
		//game.getHero().earnXP(e.getXpVal());
	}

	public void setEvent(StoryEvent event)
	{
		this.event = event;
	}

	@Override
	protected void initMenu()
	{
		dials = new ArrayList<Dialogue>();
		if(!setDials())
			generateEndChoice();
	}

	@Override
	protected void renderMenu()
	{
		Iterator<Dialogue> i = dials.iterator();
		while (i.hasNext())
		{
			this.writeDialogue(i.next());
		}
	}

	protected void writeDialogue(Dialogue d)
	{
		this.writeLine(d.getDialogue());
	}
}
