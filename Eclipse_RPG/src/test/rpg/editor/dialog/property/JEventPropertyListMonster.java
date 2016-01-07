package test.rpg.editor.dialog.property;

import java.awt.Frame;
import java.util.ArrayList;

import test.rpg.editor.dialog.EntityPropertyDialog;
import test.rpg.editor.dialog.PropertyDialog;
import test.rpg.editor.dialog.StringPropertyDialog;
import test.rpg.perso.Entity;

public class JEventPropertyListMonster extends JEventPropertyList<Entity>
{

	public JEventPropertyListMonster(Frame frame)
	{
		super(frame);
	}

	public JEventPropertyListMonster(Frame frame, ArrayList<Entity> monsters)
	{
		super(frame, monsters);
	}

	@Override
	protected PropertyDialog<Entity> getAddDialog(Frame frame)
	{
		// TODO Auto-generated method stub
		return new EntityPropertyDialog(frame);
	}

	@Override
	protected PropertyDialog<Entity> getEditDialog(Frame frame, Entity s)
	{
		return new EntityPropertyDialog(frame, s);
	}
}
