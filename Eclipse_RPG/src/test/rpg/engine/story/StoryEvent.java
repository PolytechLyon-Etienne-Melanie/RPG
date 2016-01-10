package test.rpg.engine.story;

import java.io.Serializable;
import java.util.ArrayList;

import test.rpg.engine.story.event.Event;

public class StoryEvent implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1824973033944034556L;
	private ArrayList<Event> events;
    private String event;
    private int id;
    private boolean setClasse;
    
    public StoryEvent(int id) {
    	this.id = id;
    	event = "none";
    	events = new ArrayList<Event>();
    	setSetClasse(false);
    }
    
    public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String toString() 
    {
    	return "Event : "+id +" | " + event;
    }

	public ArrayList<Event> getEvents()
	{
		return events;
	}

	public void setEvents(ArrayList<Event> events)
	{
		this.events = events;
	}

	public int getID()
	{
		return id;
	}

	public boolean isSetClasse()
	{
		return setClasse;
	}

	public void setSetClasse(boolean setClasse)
	{
		this.setClasse = setClasse;
	}
}
