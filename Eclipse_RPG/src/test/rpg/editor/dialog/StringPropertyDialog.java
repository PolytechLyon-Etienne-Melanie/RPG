package test.rpg.editor.dialog;

import java.awt.Frame;

import test.rpg.editor.dialog.property.JEventPropertyString;
import test.rpg.engine.story.event.EventCombat;

public class StringPropertyDialog  extends PropertyDialog<String>
{
	private JEventPropertyString string;

	public StringPropertyDialog(Frame parent, String t)
	{
		super(parent, "", t, true);
	}

	public StringPropertyDialog(Frame parent, String t, String s)
	{
		super(parent, s, t, false);
	}

	@Override
	protected void addProperties(Frame frame)
	{
		string = new JEventPropertyString(frame, value);
		this.addProperty(getTitle(), string);
	}

	@Override
	protected String setValues()
	{
		return string.getValue();
	}

}
