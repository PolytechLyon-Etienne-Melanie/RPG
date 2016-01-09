package test.rpg.engine.interfaces;

import java.io.IOException;

import test.rpg.engine.console.ConsoleManager;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.printer.Log;

public abstract class CommandSender 
{
	private ConsoleManager console;
	
	public CommandSender()
	{
		console = new ConsoleManager();
	}
	
	protected final void addCommand(Command c)
    {
    	console.addCommand(c);
    }

    protected final void deleteCommand(Command c)
	{
    	console.deleteCommand(c);
	}

    protected final void writeLine(String l)
	{
    	console.getWriter().writeLine(l);
	}
    
    protected final void write(String l)
	{
    	console.getWriter().write(l);
	}
    
    protected final void showCommands()
   	{
       	console.showAllCommands();
   	}
    
    public final void beginRead()
   	{
       	try
		{
			console.getReader().read();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	}
    
    protected final void clearWindow()
    {
    	Log.clearConsole();
    }
}
