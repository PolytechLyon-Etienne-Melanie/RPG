package test.rpg.engine.story;

import java.io.Serializable;

public class StoryLink  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -370341763732027141L;
	private String link;
    private int id;

    public StoryLink(int id, String l) {
	    this.id = id; 
	    this.link = l;
    }
    
    public StoryLink(int id) {
	    this(id, "");
    }
    
    public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String toString() {
    	return "Link : "+id;
    }
}
