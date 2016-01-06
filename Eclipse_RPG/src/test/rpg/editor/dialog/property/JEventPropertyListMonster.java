package test.rpg.editor.dialog.property;

import java.awt.Frame;
import java.util.ArrayList;

import test.rpg.editor.dialog.PropertyDialog;
import test.rpg.editor.dialog.StringPropertyDialog;

public class JEventPropertyListMonster extends JEventPropertyList<String>
{

	public JEventPropertyListMonster(Frame frame)
	{
		super(frame);
	}

	public JEventPropertyListMonster(Frame frame, ArrayList<String> monsters)
	{
		super(frame, monsters);
	}

	@Override
	protected PropertyDialog<String> getAddDialog(Frame frame)
	{
		// TODO Auto-generated method stub
		return new StringPropertyDialog(frame, "Monstre");
	}

	@Override
	protected PropertyDialog<String> getEditDialog(Frame frame, String s)
	{
		return new StringPropertyDialog(frame, "Monstre", s);
	}
}
