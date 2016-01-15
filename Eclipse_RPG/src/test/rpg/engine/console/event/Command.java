package test.rpg.engine.console.event;

public class Command extends ConsoleEvent {

    private Dialogue desc;
    private String com;
    private String param;
    private boolean visible;
    private boolean needParam;
    
    public Command(boolean v, String t, String com, String p)
    {
    	visible = v;
    	desc = new Dialogue(t);
    	this.com = com;
    	if(p != null && p != "")
    	{
    		param = p;
    		needParam = true;
    	}
    	else
    	{	
    		param = "";
    		needParam = false;
    	}
    }
    
    
    public Command(boolean v, String t, String com)
    {
    	this(v, t, com, "");
    }
    
    
    public Command(String t, String com)
    {
    	this(true, t, com);
    }
    
    public Command(String t, String com, String p)
    {
    	this(true, t, com, p);
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
		if(param != "")
			return "<"+com+"> <"+param+">" + desc;
		else
			return "<"+com+"> " + desc;
	}
	
	public boolean isVisible()
	{
		return visible;
	}

	public void setDesc(String string)
	{
		desc.setDial(string);
	}


	public boolean needParam()
	{
		return needParam;
	}


	public String getParam()
	{
		return param;
	}
}
