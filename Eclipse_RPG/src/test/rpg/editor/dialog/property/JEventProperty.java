package test.rpg.editor.dialog.property;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JPanel;

public abstract class JEventProperty<V> extends JPanel
{
	protected Class<V> type;
	protected V value;
	
	public JEventProperty(Frame frame, V value)
	{
		super();
		setLayout(new BorderLayout(0, 0));
		this.value = value;
		initComponents(frame);
	}
	
	public JEventProperty(Class<V> type, Frame frame, V value)
	{
		super();
		setLayout(new BorderLayout(0, 0));
		this.value = value;
		this.type = type;
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
