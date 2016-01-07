package test.rpg.editor.dialog;

import java.awt.Frame;

import test.rpg.editor.dialog.property.JEventPropertyInt;
import test.rpg.editor.dialog.property.JEventPropertyString;

public class IntPropertyDialog extends PropertyDialog<Integer>
{
	private JEventPropertyInt intProp;

	public IntPropertyDialog(Frame parent, String t)
	{
		super(parent, 0, t, true);
	}

	public IntPropertyDialog(Frame parent, String t, int s)
	{
		super(parent, s, t, false);
	}

	@Override
	protected void addProperties(Frame frame)
	{
		intProp = new JEventPropertyInt(frame, value);
		this.addProperty(getTitle(), intProp);
	}

	@Override
	protected Integer setValues()
	{
		return intProp.getValue();
	}
}
