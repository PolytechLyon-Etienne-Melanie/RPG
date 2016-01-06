package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JPanel;

public abstract class JEventProperty<V> extends JPanel
{
	protected V value;
	
	public JEventProperty(Frame frame, V value)
	{
		super();
		setLayout(new BorderLayout(0, 0));
		this.value = value;
		initComponents(frame);
	}
	
	protected abstract void initComponents(Frame frame);
	protected abstract V setValue();
	
	public V getValue()
	{
		value = setValue();
		return value;
	}
}
