package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JComboBox;

public class JEventPropertyCombo<E extends Enum<E>> extends JEventProperty<E>
{
	private JComboBox<E> comboBox;
	
	public JEventPropertyCombo(Frame frame, Class<E> enumType, E e)
	{
		super(enumType, frame, e);
	}

	@Override
	protected void initComponents(Frame frame)
	{
		comboBox = new JComboBox<E>(type.getEnumConstants());
		this.add(comboBox, BorderLayout.CENTER);
	}

	@Override
	protected E setValue()
	{
		return comboBox.getItemAt(comboBox.getSelectedIndex());
	}

}
