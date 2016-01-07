package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JComboBox;

import test.rpg.engine.story.event.EventEntity;

public class JEventPropertyClasse extends JEventProperty<EventEntity.ClasseE>
{
	private JComboBox<EventEntity.ClasseE> comboBox;
	private Class<EventEntity.ClasseE> enumType;
	
	public JEventPropertyClasse(Frame frame, Class<EventEntity.ClasseE> enumType, EventEntity.ClasseE e)
	{
		super(frame, e);
		this.enumType = enumType;
	}

	@Override
	protected void initComponents(Frame frame)
	{
		comboBox = new JComboBox<EventEntity.ClasseE>(EventEntity.ClasseE.class.getEnumConstants());
		this.add(comboBox, BorderLayout.CENTER);
	}

	@Override
	protected EventEntity.ClasseE setValue()
	{
		return comboBox.getItemAt(comboBox.getSelectedIndex());
	}
}
