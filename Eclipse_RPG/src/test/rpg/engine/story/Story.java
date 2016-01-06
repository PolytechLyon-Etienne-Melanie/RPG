package test.rpg.engine.story;

import java.io.Serializable;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.StoryLink;

public class Story implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5662645318936914681L;
	private DirectedSparseMultigraph<StoryEvent, StoryLink> graph;
	private int eventCount;
	private int linkCount;

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
	public transient Factory<StoryLink> edgeFactory = new Factory<StoryLink>()
	{
		public StoryLink create()
		{
			return new StoryLink(linkCount++);
		}
	};
	public transient Factory<StoryEvent> vertexFactory = new Factory<StoryEvent>()
	{
		public StoryEvent create()
		{
			return new StoryEvent(eventCount++);
		}
	};

	public Story()
	{
		setGraph(new DirectedSparseMultigraph<StoryEvent, StoryLink>());
		eventCount = 0;
		linkCount = 0;
	}

	public DirectedSparseMultigraph<StoryEvent, StoryLink> getGraph()
	{
		return graph;
	}

	public void setGraph(DirectedSparseMultigraph<StoryEvent, StoryLink> graph)
	{
		this.graph = graph;
	}
}

