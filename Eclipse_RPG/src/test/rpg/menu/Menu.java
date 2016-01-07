package test.rpg.menu;


import java.util.ArrayList;
import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.interfaces.CommandSender;

public abstract class Menu extends CommandSender 
{
	protected Game game;
	protected String title;
    protected ArrayList<Dialogue> dials;
    
    public Menu(Game game)
	{
		super();
		this.game = game;
		dials = new ArrayList<Dialogue>();
		title = "Default title menu";
	}
    
    public void init()
    {
    	setDials();
		setCommands();
    }

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
	
	protected abstract void setDials();
	
	protected abstract void setCommands();
	
	public void render()
	{
		Log.d("render menu");
		if(!game.isDebug())
			clearWindow();
		writeSeparator(title);
		writeMenu();
		writeSeparator("Commands");
		writeCommands();
		writeSeparator("Input");
	}
	
	protected void writeMenu()
	{
		Iterator<Dialogue> i = dials.iterator();
		while(i.hasNext())
		{
			this.writeDialogue(i.next());
		}
	}
	
	protected void writeDialogue(Dialogue d)
	{
		this.writeLine(d.getDialogue());
	}
	
	protected void writeSeparator()
	{
		this.writeLine("+--------------------------------------------------------------------------+");
	}
	
	protected void writeSeparator(String t)
	{
		this.writeLine("+--| "+t+" |-----------------------------------------------------------------------+");
	}

	protected void writeCommands()
	{
		showCommands();
	}
	
	protected void addDial(Dialogue d)
	{
		this.dials.add(d);
	}
}
