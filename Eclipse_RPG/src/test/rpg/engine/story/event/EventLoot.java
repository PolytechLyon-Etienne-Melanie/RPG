package test.rpg.engine.story.event;

import test.rpg.perso.equipement.Item;

public class EventLoot extends EventDialogue
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7113219565187184622L;
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

	public Item geenerateLoot()
	{
		Item item = Item.getItem(loot);
		return item;
	}
	
	public Item getLootForEditor()
	{
		Item item = Item.getItemForEditor(loot);
		return item;
	}

	public String toString() 
    {
    	return "Loot : " + getLootForEditor();
    }

	public void setLoot(int loot)
	{
		this.loot = loot;
	}
}
