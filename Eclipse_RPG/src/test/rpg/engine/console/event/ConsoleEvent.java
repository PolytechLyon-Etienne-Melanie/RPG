package test.rpg.engine.console.event;

import java.util.ArrayList;
import java.util.Iterator;

import test.rpg.engine.story.event.EventObserver;

public abstract class ConsoleEvent {
	private ArrayList<EventObserver> observers;
	
	public ConsoleEvent()
	{
		observers = new ArrayList<EventObserver>();
	}
	
    public void actionPerformed() 
    {
    	Iterator<EventObserver> i = observers.iterator();
    	while(i.hasNext())
    	{
    		i.next().actionPerformed();
    	}
    }
    
    public void addObserver(EventObserver obs)
    {
    	observers.add(obs);
    }
}
