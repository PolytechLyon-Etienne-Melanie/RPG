package test.rpg.editor.factory;

import java.io.Serializable;

import org.apache.commons.collections15.Factory;

import test.rpg.engine.console.printer.Log;
import test.rpg.engine.story.Story;
import test.rpg.engine.story.StoryEvent;

public class VertexFactory implements Factory<StoryEvent>, Serializable
{
	private static Story str;
	
	public VertexFactory()
	{}
	
	public StoryEvent create()
	{
		return new StoryEvent(str.getNextEventId());
	}
	
	public static void setStory(Story s)
	{
		str = s;
	}
};
