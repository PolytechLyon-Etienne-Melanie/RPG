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
	
	protected void setDials()
	{
		Iterator<Event> i = event.getEvents().iterator();
		while(i.hasNext())
		{
			Event e = i.next();
			if(e instanceof EventLoot)
			{
				EventLoot el = (EventLoot) e;
				Dialogue diag = new Dialogue(el.getDialogue());
				this.addDial(diag);
				Item item = el.geenerateLoot();
				Dialogue loot = new Dialogue("Loot : "+ item);
				this.addDial(loot);
				this.onLoot(item);
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
				Iterator<EventEntity> it = ec.getMonsters().iterator();
				while(it.hasNext())
				{
					Entity en = it.next().genEntity();
					Dialogue diag = new Dialogue(en.toString());
					this.addDial(diag);
					this.startCombat(en);
				}
				//this.startCombat();
			}
		}
	}
	
	public void generateEndChoice()
	{
		inventaire = new Command("Charger une histoire", "load");
		inventaire.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuInventaire(game, game.getHero().getInventaire()));
			}
			
		});
		
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
			public void actionPerformed()
			{
				// System.out.println("next event");
				game.getStoryManager().goNextStoryEvent(l);
			}
		};
	}
	
	public void onLoot(Item loot)
	{
		
	}
	
	public void startCombat(Entity e)
	{
		Command combat = new Command("Voules-vous augmenter la magie de votre héro ?", "5");
		combat.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				List<Personnage> confrerie = new ArrayList<Personnage>();
				List<Entity> monstres = new ArrayList<Entity>();
				confrerie.add(game.getHero());
				monstres.add(e);
				game.setCurrentMenu(new MenuCombat(game, confrerie, monstres));
			}
		});
		
		this.addCommand(combat);
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
		setDials();
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
