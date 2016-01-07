package test.rpg.engine.story.event;

import java.util.ArrayList;
import java.util.List;

import test.rpg.perso.Entity;

public class EventCombat implements Event
{
	private String title;

	private ArrayList<Entity> monsters;
	
	public EventCombat()
	{
		title = "Default Combat";
		setMonsters(new ArrayList<Entity>());
	}
	
	public EventCombat(String s, List<Entity> m)
	{
		title = s;
		setMonsters(m);
	}
	
	public String toString()
	{
		return "Combat : " + title;
	}

	public ArrayList<Entity> getMonsters()
	{
		return monsters;
	}

	public void setMonsters(List<Entity> monsters)
	{
		this.monsters = new ArrayList<Entity>(monsters);
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
}
