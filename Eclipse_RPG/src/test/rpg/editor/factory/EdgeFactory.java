package test.rpg.editor.factory;

import java.io.Serializable;

import org.apache.commons.collections15.Factory;

import test.rpg.engine.console.printer.Log;
import test.rpg.engine.story.Story;
import test.rpg.engine.story.StoryLink;

public class EdgeFactory implements Factory<StoryLink>, Serializable
{
	private static Story str;
	
	public EdgeFactory()
	{
	}
	
	public StoryLink create()
	{
		return new StoryLink(str.getNextLinkId());
	}
	
	public static void setStory(Story s)
	{
		str = s;
	}
};
