package test.rpg.engine.story;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import test.rpg.editor.factory.EdgeFactory;
import test.rpg.editor.factory.VertexFactory;

public class Story implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5662645318936914681L;
	private DirectedSparseMultigraph<StoryEvent, StoryLink> graph;
	public int eventCount;
	public int linkCount;
	public int startEvent;

	public transient Transformer<StoryLink, String> linkTransformer = new Transformer<StoryLink, String>()
	{
		public String transform(StoryLink link)
		{
			return link.getLink();
		}
	};
	public transient Transformer<StoryEvent, String> eventTransformer = new Transformer<StoryEvent, String>()
	{
		public String transform(StoryEvent e)
		{
			return e.getEvent();
		}
	};
	
	public transient VertexFactory vertexFactory;
	
	public transient EdgeFactory edgeFactory;

	public Story()
	{
		setGraph(new DirectedSparseMultigraph<StoryEvent, StoryLink>());
		eventCount = 0;
		linkCount = 0;
		startEvent = 0;
		
		vertexFactory = new VertexFactory();
		edgeFactory = new EdgeFactory();
	}
	
	public void setStartEvent(int i)
	{
		if(i < 0 || !isValidId(i))
			i = 0;
		startEvent = i;
	}

	public DirectedSparseMultigraph<StoryEvent, StoryLink> getGraph()
	{
		return graph;
	}

	public void setGraph(DirectedSparseMultigraph<StoryEvent, StoryLink> graph)
	{
		this.graph = graph;
	}
	
	public boolean isValidId(int id)
	{
		ArrayList<StoryEvent> events = new ArrayList<StoryEvent>(getGraph().getVertices());
		Iterator<StoryEvent> i = events.iterator();
		int ID = -1;
		StoryEvent event = null;

		while (id != ID && i.hasNext())
		{
			event = i.next();
			ID = event.getID();
		}

		if (ID == id)
			return true;
		else
			return false;
	}

	public Integer getStartId()
	{
		return startEvent;
	}
	
	public Integer getNextEventId()
	{
		int tmp = eventCount;
		eventCount++;
		return tmp;
	}
	
	public Integer getNextLinkId()
	{
		int tmp = linkCount;
		linkCount++;
		return tmp;
	}
}

