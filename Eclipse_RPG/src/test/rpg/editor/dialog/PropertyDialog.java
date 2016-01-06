package test.rpg.editor.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import test.rpg.editor.dialog.property.JEventProperty;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.story.event.EventCombat;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public abstract class PropertyDialog<E> extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private JTabbedPane tabbedPane;

	protected String title;

	private boolean add;

	protected E value;

	/**
	 * @param b 
	 * @wbp.parser.constructor
	 */
	public PropertyDialog(Frame parent, E edit, String t, boolean a)
	{
		super(parent, true);
		this.add = a;
		this.title = t;
		value = edit;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		initComponents(parent);
	}

	/**
	 * Create the dialog.
	 */
	public void initComponents(Frame frame)
	{
		if (add)
			setTitle("Add | " + title);
		else
			setTitle("Edit | " + title);

		setBounds(100, 100, 450, 269);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPanel.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		contentPanel.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton okButton = new JButton();
		panel.add(okButton);
		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				okButtonHandler(evt);
			}
		});
		okButton.setActionCommand("OK");
		getRootPane().setDefaultButton(okButton);
		if (add)
			okButton.setText("Ajouter");
		else
			okButton.setText("Modifier");
		

		JButton cancelButton = new JButton("Annuler");
		panel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				value = null;
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");

		addProperties(frame);
	}

	protected abstract void addProperties(Frame frame);

	protected void addProperty(String name, JEventProperty<?> prop)
	{
		tabbedPane.add(name, prop);
	}

	private void okButtonHandler(java.awt.event.ActionEvent evt)
	{
		// event.setDialogue(this.editorPane.getText());

		// set event properties
		value = setValues();
		dispose();
	}

	protected abstract E setValues();

	public E getValues()
	{
		this.setVisible(true);
		return value;
	}
}
