package test.rpg.editor.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.event.Event;
import test.rpg.engine.story.event.EventCombat;
import test.rpg.engine.story.event.EventDialogue;
import test.rpg.engine.story.event.EventLoot;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class VertexPropertyDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private StoryEvent event;
	private JButton btnDelete;
	private JButton btnEdit;
	private JComboBox<SEvent> comboBox;
	private Frame frame;
	private JTextField textField;
	private DefaultListModel<Event> list;
	
	private enum SEvent{
		Dialogue,
		Loot,
		Combat;
	};
	
	public VertexPropertyDialog(Frame parent, StoryEvent e) {
        super(parent, true);
        this.event = e;
        this.frame = parent;
        initComponents();
    }
	
	/**
	 * Create the dialog.
	 */
	public void initComponents()
	{
		setTitle("Edit | " + event.toString());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblLink = new JLabel("Events :");
		
		JPanel panel = new JPanel();
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setText(event.getEvent());
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblLink)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLink)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
		);
		panel.setLayout(new BorderLayout(0, 0));
		list = new DefaultListModel<Event>();
		
		Iterator<Event> i = event.getEvents().iterator();
		while(i.hasNext())
		{
			Event e = i.next();
			list.addElement(e);
		}
		
		JList<Event> jlist = new JList<Event>();
		jlist.setModel(list);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(jlist);
		
		JScrollBar scrollBar = new JScrollBar();
		panel.add(scrollBar, BorderLayout.EAST);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnDelete = new JButton("Delete");
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if(jlist.getSelectedIndex() != -1)
						 list.removeElementAt(jlist.getSelectedIndex()); 
					}
				});
				
				btnEdit = new JButton("Edit");
				btnEdit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(jlist.getSelectedIndex() != -1)
						{
							Event ev = list.getElementAt(jlist.getSelectedIndex());
							PropertyDialog<? extends Event> dialog = null;
							 if(ev instanceof EventLoot)
							{
								dialog = new LootPropertyDialog(frame, (EventLoot)ev);
							}
							 else if(ev instanceof EventDialogue)
							{
								dialog = new DialoguePropertyDialog(frame, (EventDialogue)ev);
							}
							else if(ev instanceof EventCombat)
							{
								dialog = new CombatPropertyDialog(frame, (EventCombat)ev);
							}
							
							if(dialog != null)
							{
								Event eve = dialog.getValues();
								if(eve != null)
									list.setElementAt(eve, jlist.getSelectedIndex());
							}
						}
					}
				});
			}
			JButton okButton = new JButton("OK");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
			        okButtonHandler(evt);
				}
			});
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton btnAdd = new JButton("Add");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					PropertyDialog<? extends Event> dialog = null;
					if(comboBox.getItemAt(comboBox.getSelectedIndex()) == SEvent.Dialogue)
					{
						dialog = new DialoguePropertyDialog(frame);
						
					}
					else if(comboBox.getItemAt(comboBox.getSelectedIndex()) == SEvent.Combat)
					{
						dialog = new CombatPropertyDialog(frame);
					}
					else if(comboBox.getItemAt(comboBox.getSelectedIndex()) == SEvent.Loot)
					{
						dialog = new LootPropertyDialog(frame);
					}
					if(dialog != null)
						list.addElement(dialog.getValues());
				}
			});
			
			comboBox = new JComboBox<SEvent>(SEvent.values());
			
			buttonPane.add(comboBox);
			buttonPane.add(btnAdd);
			buttonPane.add(btnDelete);
			buttonPane.add(btnEdit);
			buttonPane.add(okButton);
		}
	}
	
	private void okButtonHandler(java.awt.event.ActionEvent evt) {
        event.setEvent(this.textField.getText());
        ArrayList<Event> events = new ArrayList<Event>();
		for(int i = 0; i < list.getSize(); i++)
		{
			events.add(list.getElementAt(i));
		}
        event.setEvents(events);
        dispose();
    }
}
