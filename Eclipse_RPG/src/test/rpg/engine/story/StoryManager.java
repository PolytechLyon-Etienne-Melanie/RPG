package test.rpg.engine.story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.exception.StoryEventNotFoundException;
import test.rpg.engine.interfaces.CommandSender;
import test.rpg.engine.story.event.Event;
import test.rpg.engine.story.event.EventDialogue;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.menu.MenuMain;

public class StoryManager
{
	// VAR
	private Story story;
	private Game game;

	public Story getStory()
	{
		return story;
	}

	private boolean loadNewEvent;
	private StoryEvent event;
	private int eventID;

	// CONSTRUCTOR
	public StoryManager(Game game)
	{
		this.game = game;
	}

	public StoryManager(Game game, Story s)
	{
		this.game = game;
		story = s;
		eventID = 0;
	}

	public StoryManager(Game game, Story s, int id)
	{
		this.game = game;
		story = s;
		eventID = id;
	}

	// METHODS
	public void initStory()
	{
		Log.d("Init Story");
		if (story == null)
		{
			Log.d("load new Story");
			try
			{
				story = StorySerializer.unserialize();
				if (story == null)
				{
					game.setCurrentMenu(new MenuMain(game));
				}
				else
				{
					eventID = story.getStartId();
				}
			} catch (ClassNotFoundException | IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}

	public void startStory()
	{
		if(story != null)
			startStory(eventID);
	}

	public void startStory(int id)
	{
		Log.d("Start Story at "+id);
		try
		{
			setStoryEvent(getStoryEventByID(id));
		} catch (StoryEventNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void updateStoryEvent()
	{
		Log.d("Update Story");
		if (loadNewEvent)
		{
			Log.d("Enter on new Event : " + event);
			loadNewEvent = false;
			Log.d("Start generate event");

			Log.d("End generate event");
		}
		Log.d("End Update Story");
	}

	public void setStoryEvent(StoryEvent e)
	{
		Log.d("Set Story event : " + e);
		event = e;
		loadNewEvent = true;
		game.setMenuStory(e);
	}

	public StoryEvent getStoryEventByID(int id) throws StoryEventNotFoundException
	{
		ArrayList<StoryEvent> events = new ArrayList<StoryEvent>(story.getGraph().getVertices());
		Iterator<StoryEvent> i = events.iterator();
		int ID = -1;
		StoryEvent event = null;

		while (ID != id && i.hasNext())
		{
			event = i.next();
			ID = event.getID();
		}

		if (ID == id)
			return event;
		else
			throw new StoryEventNotFoundException(id);
	}

	public void goNextStoryEvent(StoryLink l)
	{
		this.setStoryEvent(story.getGraph().getDest(l));
	}

	// GETTERS / SETTERS
}
