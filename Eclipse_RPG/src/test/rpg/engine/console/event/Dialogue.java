package test.rpg.engine.console.event;

public class Dialogue extends ConsoleEvent
{
    private String dialogue;
    
    public Dialogue(String d)
    {
    	dialogue = d;
    }

    public String getDialogue() 
    {
        return dialogue;
    }
    
    public String toString()
    {
    	return dialogue;
    }

	public void setDial(String string)
	{
		dialogue = string;
	}
}
