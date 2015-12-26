/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpg_game;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
import rpg_game.GraphView.MyLink;

/**
 *
 * @author kifkif
 */
public class GraphView 
{
    public DirectedSparseMultigraph<MyNode, MyLink> g;
    public MyNode n1;
    public MyNode n2;
    public MyNode n3;
    public MyNode n4;
    public MyNode n5;
    public int edgeCount;
    public int nodeCount;
    
    public Transformer<MyLink, Double> capTransformer;
    public Map<MyLink, Double> edgeFlowMap = new HashMap<MyLink, Double>();
    public Factory<MyLink> edgeFactory;
    public Factory<MyNode> vertexFactory;
    
    public GraphView()
    {
        edgeCount = 0;
        nodeCount = 0;
        
        g = new DirectedSparseMultigraph<MyNode, MyLink>();
        // Create some MyNode objects to use as vertices
        n1 = new MyNode(); 
        n2 = new MyNode(); 
        n3 = new MyNode();
        n4 = new MyNode(); 
        n5 = new MyNode(); 
        // note n1-n5 declared elsewhere.
        // Add some directed edges along with the vertices to the graph
        g.addEdge(new MyLink(2.0, 48),n1, n2, EdgeType.DIRECTED); // This method
        g.addEdge(new MyLink(2.0, 48),n2, n3, EdgeType.DIRECTED);
        g.addEdge(new MyLink(3.0, 192), n3, n5, EdgeType.DIRECTED);
        g.addEdge(new MyLink(2.0, 48), n5, n4, EdgeType.DIRECTED); // or we can use
        g.addEdge(new MyLink(2.0, 48), n4, n2); // In a directed graph the
        g.addEdge(new MyLink(2.0, 48), n3, n1); // first node is the source
        g.addEdge(new MyLink(10.0, 48), n2, n5);// and the second the destination
        
        capTransformer =
        new Transformer<MyLink, Double>(){
         public Double transform(MyLink link) {
         return link.capacity;
         }
         };
         edgeFlowMap = new HashMap<MyLink, Double>();
         // This Factory produces new edges for use by the algorithm
         edgeFactory = new Factory<MyLink>() {
         public MyLink create() {
         return new MyLink(1.0, 1.0);
         }
         };
         
         vertexFactory = new Factory<MyNode>() {
         public MyNode create() {
         return new MyNode();
         }
         };
        
    }
    
    class MyNode {
        int id; // good coding practice would have this as private
        public MyNode() {
        this.id = nodeCount++;
        }
        public String toString() { // Always a good idea for debuging
        return "V"+id; // JUNG2 makes good use of these.
        }
    }

    class MyLink {
        double capacity; // should be private
        double weight; // should be private for good practice
        int id;

        public MyLink(double weight, double capacity) {
        this.id = edgeCount++; // This is defined in the outer class.
        this.weight = weight;
        this.capacity = capacity;
        }
        public String toString() { // Always good for debugging
        return "E"+id;
        }
    }

        

}
