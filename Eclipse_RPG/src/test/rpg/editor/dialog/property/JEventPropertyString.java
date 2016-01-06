package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JEditorPane;

import test.rpg.engine.console.printer.Log;

public class JEventPropertyString extends JEventProperty<String>
{
	private JEditorPane editorPane;
	public JEventPropertyString(Frame frame)
	{
		this(frame, "");
	}

	public JEventPropertyString(Frame frame, String field)
	{
		super(frame, field);
	}

	@Override
	protected void initComponents(Frame frame)
	{
		editorPane = new JEditorPane();
		editorPane.setText(value);
		this.add(editorPane, BorderLayout.CENTER);
	}

	@Override
	protected String setValue()
	{
		return editorPane.getText();
	}
}
