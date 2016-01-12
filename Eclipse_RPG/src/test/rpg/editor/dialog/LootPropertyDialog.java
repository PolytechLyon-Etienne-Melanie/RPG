package test.rpg.editor.dialog;

import java.awt.Frame;

import test.rpg.editor.dialog.property.JEventPropertyComboList;
import test.rpg.editor.dialog.property.JEventPropertyString;
import test.rpg.engine.story.event.EventLoot;
import test.rpg.perso.equipement.Item;

public class LootPropertyDialog extends PropertyDialog<EventLoot>
{
	private JEventPropertyString string;
	private JEventPropertyComboList<Item> combo;

	public LootPropertyDialog(Frame parent, EventLoot d)
	{
		super(parent, d, "Dialogue | Loot", false);
	}

	public LootPropertyDialog(Frame parent)
	{
		super(parent, new EventLoot(), "Dialogue", true);
	}

	@Override
	protected void addProperties(Frame frame)
	{
		string = new JEventPropertyString(frame, value.getDialogue());
		this.addProperty("Texte", string);
		combo = new JEventPropertyComboList<Item>(frame, Item.getItems(), value.getLootForEditor());
		this.addProperty("Item", combo);
	}

	@Override
	protected EventLoot setValues()
	{
		value.setDialogue(string.getValue());
		value.setLoot(combo.getValue().getID());
		return value;
	}
}
