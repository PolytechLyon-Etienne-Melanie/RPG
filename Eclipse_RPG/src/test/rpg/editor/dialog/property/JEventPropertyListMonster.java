package test.rpg.editor.dialog.property;

import java.awt.Frame;
import java.util.ArrayList;

import test.rpg.editor.dialog.EntityPropertyDialog;
import test.rpg.editor.dialog.PropertyDialog;
import test.rpg.engine.story.event.EventEntity;
import test.rpg.perso.Entity;

public class JEventPropertyListMonster extends JEventPropertyList<EventEntity>
{

	public JEventPropertyListMonster(Frame frame)
	{
		super(frame);
	}

	public JEventPropertyListMonster(Frame frame, ArrayList<EventEntity> monsters)
	{
		super(frame, monsters);
	}

	@Override
	protected PropertyDialog<EventEntity> getAddDialog(Frame frame)
	{
		// TODO Auto-generated method stub
		return new EntityPropertyDialog(frame);
	}

	@Override
	protected PropertyDialog<EventEntity> getEditDialog(Frame frame, EventEntity s)
	{
		return new EntityPropertyDialog(frame, s);
	}
}
