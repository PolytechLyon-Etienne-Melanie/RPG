package test.rpg.engine.story.event;

import java.util.Random;

import test.rpg.perso.equipement.Item;

public class EventLoot extends EventDialogue
{
	private int loot;
	
	public EventLoot(String d, int loot)
	{
		super(d);
		this.setLoot(loot);
	}
	
	public EventLoot(int loot)
	{
		super();
		this.setLoot(loot);
	}
	
	public EventLoot(String d)
	{
		super(d);
	}
	
	public EventLoot()
	{
		super();
	}

	public Item getLoot()
	{
		Item item = null;
		if(loot == 0)
			item = Item.getRandomLoot();
		else
			item = Item.getItem(loot);
		return item;
	}

	public String toString() 
    {
    	return "Loot : " + getLoot();
    }

	public void setLoot(int loot)
	{
		this.loot = loot;
	}
}
