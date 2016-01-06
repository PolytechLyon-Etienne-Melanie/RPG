package test.rpg.engine.console.event;

public class Command extends ConsoleEvent {

    private Dialogue desc;
    private String com;
    
    public Command(String t, String com)
    {
    	desc = new Dialogue(t);
    	this.com = com;
    }
    
	public Dialogue getDesc()
	{
		return desc;
	}

	public void setDesc(Dialogue desc)
	{
		this.desc = desc;
	}

	public String getCom()
	{
		return com;
	}

	public void setCom(String com)
	{
		this.com = com;
	}
	
	public String toString()
	{
		return "<"+com+"> " + desc;
	}
}
