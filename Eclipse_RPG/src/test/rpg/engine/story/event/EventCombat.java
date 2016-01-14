package test.rpg.engine.story.event;

import java.util.ArrayList;
import java.util.List;

import test.rpg.perso.Entity;

public class EventCombat implements Event
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8354302838990576170L;

	private String title;

	private ArrayList<EventEntity> monsters;
	private boolean done;
	
	public EventCombat()
	{
		title = "Default Combat";
		setMonsters(new ArrayList<EventEntity>());
    	done = false;
	}
	
	public EventCombat(String s, List<EventEntity> m)
	{
		title = s;
		setMonsters(m);
	}
	
	public String toString()
	{
		return "Combat : " + title;
	}

	public ArrayList<EventEntity> getMonsters()
	{
		return monsters;
	}

	public void setMonsters(List<EventEntity> monsters)
	{
		this.monsters = new ArrayList<EventEntity>(monsters);
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	
	public boolean isDone()
	{
		return done;
	}
	
	public void done()
	{
		done = true;
	}
}
