package test.rpg.engine.exception;

public class StoryEventNotFoundException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7660094133394814868L;
	private int id;
	
	public StoryEventNotFoundException(int id)
	{
		super("StoryEvent with id : " + id + " not found in graph.");
		this.id = id;
	}

	public int getId()
	{
		return id;
	}
}
