package test.rpg.engine.story.event;

import java.io.Serializable;

import test.rpg.engine.story.StoryManager;

public abstract class Event implements Serializable 
{
	private boolean done;
	
	public Event()
	{
		done = false;
	}
	
	public void done()
	{
		done = true;
	}
	
	public boolean isDone()
	{
		return done;
	}
}
