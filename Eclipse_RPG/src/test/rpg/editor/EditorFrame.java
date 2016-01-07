package test.rpg.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import test.rpg.editor.dialog.IntPropertyDialog;
import test.rpg.editor.factory.EdgeFactory;
import test.rpg.editor.factory.VertexFactory;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.story.Story;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.StoryLink;
import test.rpg.engine.story.StorySerializer;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class EditorFrame extends JFrame
{

	private JPanel contentPane;
	private final Action action_quit = new SwingAction_Quit();
	private final Action action_load = new SwingAction_Load();
	private final Action action_save = new SwingAction_Save();
	
	private Story sgv;
	private VisualizationViewer<Integer, String> vv;

	public EditorFrame(Story s)
	{
		sgv = s;
		Layout<Integer, String> layout = new FRLayout(sgv.getGraph());
		layout.setSize(new Dimension(700, 500));
		vv = new VisualizationViewer<Integer, String>(layout);
		vv.setPreferredSize(new Dimension(800, 600));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		initComponents();
	}
	
	public EditorFrame()
	{
		this(new Story());
	}

	private void initComponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);

		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir");
		mntmOuvrir.setAction(action_load);
		mnFichier.add(mntmOuvrir);

		JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
		mntmEnregistrer.setAction(action_save);
		mnFichier.add(mntmEnregistrer);

		JSeparator separator = new JSeparator();
		mnFichier.add(separator);

		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setAction(action_quit);
		mnFichier.add(mntmQuitter);
		
		EditingModalGraphMouse gm = new EditingModalGraphMouse(vv.getRenderContext(), sgv.vertexFactory, sgv.edgeFactory);
		vv.setGraphMouse(gm);
		VertexFactory.setStory(sgv);

		JMenu modeMenu = gm.getModeMenu(); // Obtain mode menu from the mouse
		modeMenu.setText("Mouse Mode");
		modeMenu.setIcon(null); // I'm using this in a main menu
		modeMenu.setPreferredSize(new Dimension(80, 20)); // Change the size
		menuBar.add(modeMenu);

		gm.setMode(ModalGraphMouse.Mode.EDITING);

		JMenu mnNewMenu = new JMenu("Action");
		JMenuItem mnNewMenuItem = new JMenuItem("RePaint");
		mnNewMenuItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				repaintGraph();
			}
		});
		mnNewMenu.add(mnNewMenuItem);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmSetStart = new JMenuItem("Set Start");
		ActionListener listener = new ActionListener()
		{
			private Frame frame;
			ActionListener setFrame(Frame frame)
			{
				this.frame = frame;
				return this;
			}
			public void actionPerformed(ActionEvent evt)
			{
				IntPropertyDialog intdiag = new IntPropertyDialog(frame, "Start ID", sgv.getStartId());
				int id = intdiag.getValues();
				sgv.setStartEvent(id);
			}
		}.setFrame(this);
		mntmSetStart.addActionListener(listener);
		mnNewMenu.add(mntmSetStart);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(vv);

		PopupVertexEdgeMenuMousePlugin myPlugin = new PopupVertexEdgeMenuMousePlugin();
		// Add some popup menus for the edges and vertices to our mouse plugin.
		JPopupMenu edgeMenu = new MyMouseMenus.EdgeMenu(this);
		JPopupMenu vertexMenu = new MyMouseMenus.VertexMenu(this);
		myPlugin.setEdgePopup(edgeMenu);
		myPlugin.setVertexPopup(vertexMenu);
		gm.remove(gm.getPopupEditingPlugin()); // Removes the existing popup
												// editing plugin

		gm.add(myPlugin); // Add our new plugin to the mouse
		vv.setGraphMouse(gm);
	}
	
	private void setStory(Story s)
	{
		sgv = s;
		VertexFactory.setStory(sgv);
		this.repaintGraph();
	}
	
	private void repaintGraph()
	{
		Layout<Integer, String> layout = new FRLayout(sgv.getGraph());
		layout.setSize(new Dimension(700, 500));
		vv.setGraphLayout(layout);
		vv.repaint();
	}
	
	private class SwingAction_Quit extends AbstractAction {
		public SwingAction_Quit() {
			putValue(NAME, "Quitter");
			putValue(SHORT_DESCRIPTION, "Quitter l'application");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class SwingAction_Load extends AbstractAction {
		public SwingAction_Load() {
			putValue(NAME, "Ouvrir");
			putValue(SHORT_DESCRIPTION, "Ouvrir une \"story\"");
		}
		public void actionPerformed(ActionEvent e) 
		{
			Story s = null;
			
			try
			{
				s = StorySerializer.unserialize();
				Log.d(s.eventCount);
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(s != null)
			{
				setStory(s);
			}
		}
	}
	private class SwingAction_Save extends AbstractAction {
		public SwingAction_Save() {
			putValue(NAME, "Enregistrer");
			putValue(SHORT_DESCRIPTION, "Enregistrer la \"story\"");
		}
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				StorySerializer.serialize(sgv);
			} catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
