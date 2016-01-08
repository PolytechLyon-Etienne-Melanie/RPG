package test.rpg.menu;

import java.util.ArrayList;
import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.StoryLink;
import test.rpg.engine.story.event.Event;
import test.rpg.engine.story.event.EventCombat;
import test.rpg.engine.story.event.EventDialogue;
import test.rpg.engine.story.event.EventEntity;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Entity;

public class MenuStory extends Menu
{
	private StoryEvent event;
	public MenuStory(Game game, StoryEvent event)
	{
		super(game);
		this.event = event;
	}
	
	@Override
	protected void setDials()
	{
		Iterator<Event> i = event.getEvents().iterator();
		while(i.hasNext())
		{
			Event e = i.next();
			if(e instanceof EventDialogue)
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
				}
			}
		}
	}

	@Override
	protected void setCommands()
	{
		generateEndChoice();
	}
	
	public void generateEndChoice()
	{
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
		return new Command(link.getLink(), "choice" + id);
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
	
	public void startCombat()
	{
		
	}
}
