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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;

import test.rpg.editor.dialog.PropertyDialog;
import test.rpg.engine.console.printer.Log;

public abstract class JEventPropertyList<V> extends JEventProperty<List<V>>
{
	
	public JEventPropertyList(Frame frame)
	{
		this(frame, new ArrayList<V>());
	}

	public JEventPropertyList(Frame frame, List<V> listV)
	{
		super(frame, listV);
		initComponents(frame);
	}

	private DefaultListModel<V> list;

	protected void initComponents(Frame frame)
	{
		list = new DefaultListModel<V>();
		Iterator<V> i = value.iterator();
		while(i.hasNext())
		{
			list.addElement(i.next());
		}
		JList<V> jlist = new JList<V>();
		jlist.setModel(list);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(jlist);
		
		JScrollBar scrollBar = new JScrollBar();
		add(scrollBar, BorderLayout.EAST);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		{
			JButton btnAdd = new JButton("Ajouter");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PropertyDialog<V> dialog = getAddDialog(frame);
					list.addElement(dialog.getValues());
				}
			});
			panel.add(btnAdd);
			
			JButton btnEdit = new JButton("Editer");
			btnEdit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(jlist.getSelectedIndex() != -1)
					{
						 V v = list.getElementAt(jlist.getSelectedIndex()); 
						 PropertyDialog<V> dialog = getEditDialog(frame, v);
						 list.setElementAt(dialog.getValues(), jlist.getSelectedIndex());
					}
				}
			});
			panel.add(btnEdit);
			
			JButton btnDelete = new JButton("Supprimer");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(jlist.getSelectedIndex() != -1)
						 list.removeElementAt(jlist.getSelectedIndex()); 
				}
			});
			panel.add(btnDelete);
		}
	}
	
	protected abstract PropertyDialog<V> getAddDialog(Frame f);
	protected abstract PropertyDialog<V> getEditDialog(Frame f, V v);
	
	@Override
	public List<V> setValue()
	{
		return genList();
	}

	private List<V> genList()
	{
		ArrayList<V> l = new ArrayList<V>();
		for(int i = 0; i < list.size(); i++)
		{
			l.add(list.getElementAt(i));
		}
		return l;
	}
}
