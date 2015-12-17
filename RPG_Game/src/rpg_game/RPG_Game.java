/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg_game;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author kifkif
 */
public class RPG_Game {

    /**
     * @param args the command line arguments
     */
    
            
    public static void main(String[] args) 
    {
        GraphView sgv = new GraphView();
        // Layout<V, E>, VisualizationViewer<V,E>
        Layout<Integer, String> layout = new StaticLayout(sgv.g);
        layout.setSize(new Dimension(300,300));
        VisualizationViewer<Integer,String> vv =
       new VisualizationViewer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(350,350));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        // Create a graph mouse and add it to the visualization viewer
        //
        EditingModalGraphMouse gm =
       new EditingModalGraphMouse(vv.getRenderContext(), sgv.vertexFactory, sgv.edgeFactory);
        vv.setGraphMouse(gm);

        JFrame frame = new JFrame("Editing Graph Viewer 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);

        // Let's add a menu for changing mouse modes
        JMenuBar menuBar = new JMenuBar();
        JMenu modeMenu = gm.getModeMenu(); // Obtain mode menu from the mouse
        modeMenu.setText("Mouse Mode");
        modeMenu.setIcon(null); // I'm using this in a main menu
        modeMenu.setPreferredSize(new Dimension(80,20)); // Change the size 
        menuBar.add(modeMenu);
        frame.setJMenuBar(menuBar);
        gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
        frame.pack();
        frame.setVisible(true); 
    }

}
