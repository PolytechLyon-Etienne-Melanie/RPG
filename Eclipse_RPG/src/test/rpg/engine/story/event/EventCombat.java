package test.rpg.engine.story.event;

import java.util.ArrayList;
import java.util.List;

public class EventCombat implements Event
{
	private String title;

	private ArrayList<String> monsters;
	
	public EventCombat()
	{
		title = "Default Combat";
		setMonsters(new ArrayList<String>());
	}
	
	public EventCombat(String s, List<String> m)
	{
		title = s;
		setMonsters(m);
	}
	
	public String toString()
	{
		return "Combat : " + title;
	}

	public ArrayList<String> getMonsters()
	{
		return monsters;
	}

	public void setMonsters(List<String> monsters)
	{
		this.monsters = new ArrayList<String>(monsters);
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
