package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;

import test.rpg.editor.dialog.PropertyDialog;

public class JEventPropertyInt extends JEventProperty<Integer>
{

	private JEditorPane editorPane;
	public JEventPropertyInt(Frame frame)
	{
		this(frame, 1);
	}

	public JEventPropertyInt(Frame frame, int i)
	{
		super(frame, i);
	}

	@Override
	protected void initComponents(Frame frame)
	{
		editorPane = new JEditorPane();
		editorPane.setText(value.toString());
		this.add(editorPane, BorderLayout.CENTER);
	}

	@Override
	protected Integer setValue()
	{
		return Integer.valueOf(editorPane.getText());
	}
}
