package test.rpg.editor.dialog;

import java.awt.Frame;

import test.rpg.editor.dialog.property.JEventPropertyList;
import test.rpg.editor.dialog.property.JEventPropertyListMonster;
import test.rpg.editor.dialog.property.JEventPropertyString;
import test.rpg.engine.story.event.EventCombat;

public class CombatPropertyDialog extends PropertyDialog<EventCombat>
{
	private JEventPropertyString title;
	private JEventPropertyList<String> monsters;
	
	public CombatPropertyDialog(Frame parent)
	{
		super(parent, new EventCombat(), "Combat", true);
	}

	public CombatPropertyDialog(Frame frame, EventCombat ev)
	{
		super(frame, ev, "Combat", false);
	}

	@Override
	protected void addProperties(Frame frame)
	{
		title = new JEventPropertyString(frame, value.getTitle());
		this.addProperty("Titre", title);
		monsters = new JEventPropertyListMonster(frame, value.getMonsters());
		this.addProperty("Monstres", monsters);
	}

	@Override
	protected EventCombat setValues()
	{
		return new EventCombat(title.getValue(), monsters.getValue());
	}
}