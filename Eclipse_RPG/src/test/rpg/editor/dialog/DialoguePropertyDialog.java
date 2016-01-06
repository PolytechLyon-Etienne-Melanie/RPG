package test.rpg.editor.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Element;

import test.rpg.editor.dialog.property.JEventPropertyString;
import test.rpg.engine.story.StoryLink;
import test.rpg.engine.story.event.Event;
import test.rpg.engine.story.event.EventDialogue;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class DialoguePropertyDialog extends PropertyDialog<EventDialogue>
{
	private JEventPropertyString string;

	public DialoguePropertyDialog(Frame parent, EventDialogue d)
	{
		super(parent, d, "Dialogue", false);
	}

	public DialoguePropertyDialog(Frame parent)
	{
		super(parent, new EventDialogue(), "Dialogue", true);
	}

	@Override
	protected void addProperties(Frame frame)
	{
		string = new JEventPropertyString(frame, value.getDialogue());
		this.addProperty("Texte", string);
	}

	@Override
	protected EventDialogue setValues()
	{
		value.setDialogue(string.getValue());
		return value;
	}
}
