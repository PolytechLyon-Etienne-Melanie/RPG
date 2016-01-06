package test.rpg.editor;

import edu.uci.ics.jung.visualization.VisualizationViewer;

public interface EdgeMenuListener<E> {
    /**
     * Used to set the edge and visulization component.
     * @param e 
     * @param visComp 
     */
     void setEdgeAndView(E e, VisualizationViewer visView); 
    
}