package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.JComboBox;

public class JEventPropertyComboList<E> extends JEventProperty<E>
{
	private JComboBox<E> comboBox;
	
	public JEventPropertyComboList(Frame frame, E[] l,  E e)
	{
		super(frame, e);
		initComponents(frame, l);
	}

	@Override
	protected void initComponents(Frame frame)
	{}
	
	protected void initComponents(Frame frame, E[] list)
	{
		comboBox = new JComboBox<E>(list);
		comboBox.setSelectedItem(value);
		this.add(comboBox, BorderLayout.CENTER);
	}

	@Override
	protected E setValue()
	{
		return comboBox.getItemAt(comboBox.getSelectedIndex());
	}
}
