package test.rpg.editor.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import test.rpg.engine.story.StoryLink;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class EdgePropertyDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private StoryLink edge;
	private JEditorPane dtrpnLink;
	
	public EdgePropertyDialog(java.awt.Frame parent, StoryLink edge) {
        super(parent, true);
        this.edge = edge;
        
        
        initComponents();
    }
	
	/**
	 * Create the dialog.
	 */
	public void initComponents()
	{
		setTitle("Edit | " + edge.toString());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblLink = new JLabel("Link :");
		
		dtrpnLink = new JEditorPane();
		dtrpnLink.setText(edge.getLink());
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblLink)
					.addContainerGap(399, Short.MAX_VALUE))
				.addComponent(dtrpnLink, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblLink)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dtrpnLink, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
				        okButtonHandler(evt);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	private void okButtonHandler(java.awt.event.ActionEvent evt) {
        edge.setLink(this.dtrpnLink.getText());
        dispose();
    }
}
