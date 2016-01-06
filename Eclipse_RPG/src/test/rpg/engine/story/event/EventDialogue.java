package test.rpg.engine.story.event;

public class EventDialogue implements Event {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7540809332983615821L;
	private String dialogue;
    
    public EventDialogue(String s)
    {
    	dialogue = s;
    }
    
    public EventDialogue()
    {
    	this("");
    }
    
    public String toString() 
    {
    	return "Dialogue : " + dialogue;
    }

	public String getDialogue()
	{
		return dialogue;
	}

	public void setDialogue(String dialogue)
	{
		this.dialogue = dialogue;
	}
}
