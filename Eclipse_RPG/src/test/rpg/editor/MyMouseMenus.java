package test.rpg.editor;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import test.rpg.editor.dialog.EdgePropertyDialog;
import test.rpg.editor.dialog.VertexPropertyDialog;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.StoryLink;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class MyMouseMenus
{

	public static class EdgeMenu extends JPopupMenu
	{
		// private JFrame frame;
		public EdgeMenu(final JFrame frame)
		{
			super("Edge Menu");
			// this.frame = frame;
			this.add(new DeleteEdgeMenuItem<StoryLink>());
			this.addSeparator();
			this.add(new LinkDisplay());
			this.addSeparator();
			this.add(new EdgePropItem(frame));
		}

	}

	public static class EdgePropItem extends JMenuItem implements EdgeMenuListener<StoryLink>, MenuPointListener
	{
		StoryLink edge;
		VisualizationViewer visComp;
		Point2D point;

		public void setEdgeAndView(StoryLink edge, VisualizationViewer visComp)
		{
			this.edge = edge;
			this.visComp = visComp;
		}

		public void setPoint(Point2D point)
		{
			this.point = point;
		}

		public EdgePropItem(final JFrame frame)
		{
			super("Edit Edge Properties...");
			this.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					EdgePropertyDialog dialog = new EdgePropertyDialog(frame, edge);
					dialog.setLocation((int) point.getX() + frame.getX(), (int) point.getY() + frame.getY());
					dialog.setVisible(true);
				}

			});
		}
	}

	public static class LinkDisplay extends JMenuItem implements EdgeMenuListener<StoryLink>
	{
		public void setEdgeAndView(StoryLink l, VisualizationViewer visComp)
		{
			this.setText("Link = " + l.getLink());
		}
	}

	public static class EventDisplay extends JMenuItem implements VertexMenuListener<StoryEvent>
	{
		@Override
		public void setVertexAndView(StoryEvent e, VisualizationViewer visView)
		{
			this.setText("Event = " + e.getEvent());
		}
	}

	public static class VertexMenu extends JPopupMenu
	{
		public VertexMenu(final JFrame frame)
		{
			super("Vertex Menu");
			this.add(new DeleteVertexMenuItem<StoryEvent>());
			this.addSeparator();
			this.add(new EventDisplay());
			this.add(new VextexCheckBox());
			this.addSeparator();
			this.add(new VertexPropItem(frame));
		}
	}

	public static class VertexPropItem extends JMenuItem implements VertexMenuListener<StoryEvent>, MenuPointListener
	{
		StoryEvent edge;
		VisualizationViewer visComp;
		Point2D point;

		public void setVertexAndView(StoryEvent e, VisualizationViewer visComp)
		{
			this.edge = e;
			this.visComp = visComp;
		}

		public void setPoint(Point2D point)
		{
			this.point = point;
		}

		public VertexPropItem(final JFrame frame)
		{
			super("Edit Vertex Properties...");
			this.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					VertexPropertyDialog dialog = new VertexPropertyDialog(frame, edge);
					dialog.setLocation((int) point.getX() + frame.getX(), (int) point.getY() + frame.getY());
					dialog.setVisible(true);
				}

			});
		}
	}
	
	public static class VextexCheckBox extends JCheckBoxMenuItem implements VertexMenuListener<StoryEvent> {
		StoryEvent v;
        
        public VextexCheckBox() {
            super("Set Classe");
            this.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    v.setSetClasse(isSelected());
                }
                
            });
        }
        public void setVertexAndView(StoryEvent v, VisualizationViewer visComp) {
            this.v = v;
            this.setSelected(v.isSetClasse());
        }
        
    }
}
