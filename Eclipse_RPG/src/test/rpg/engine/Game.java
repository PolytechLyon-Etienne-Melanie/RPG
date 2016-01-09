package test.rpg.engine;

import test.rpg.engine.console.CustomConsole;
import test.rpg.engine.console.printer.JConsole;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.Story;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.StoryManager;
import test.rpg.menu.MenuLoading;
import test.rpg.menu.MenuStory;

public class Game implements Runnable
{
    private StoryManager story;
    private Menu currentMenu;
    public static enum State {loading, running, inpause};
    private State state;
    private boolean debug = false;
    
    public static void main(String[] args) 
    {
    	JConsole console = new JConsole();
    	CustomConsole cconsole = new CustomConsole(console);
    	cconsole.setVisible(true);
    	
    	System.setErr(console.getOut());
    	System.setIn(console.getInputStream());
    	
    	Game game = new Game();
    	Thread thread = new Thread(game);
        thread.start();
    	//game.run(); 
        //System.exit(0);
    }
    
    public Game()
    {
    	story = new StoryManager(this);
    }
    
    public Game(Story s)
    {
    	story = new StoryManager(this, s);
    }
    
    public Game(Story s, int id)
    {
    	story = new StoryManager(this, s, id);
    }

    public StoryManager getStoryManager()
	{
		return story;
	}

	public Menu getCurrentMenu()
	{
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu)
	{
		this.currentMenu = currentMenu;
		currentMenu.init();
	}

	public void initGame() 
	{
		Log.d("Init Game");
		state = State.loading;
		setCurrentMenu(new MenuLoading(this));
    }

    public void startGame() 
    {
    	state = State.running;
    }
    
    public void setMenuStory(StoryEvent e)
    {
    	this.setCurrentMenu(new MenuStory(this, e));
    }
    
    public void updateGame()
    {
    	Log.d("Update Game");
    	story.updateStoryEvent();
    	this.currentMenu.render();
    	this.currentMenu.beginRead();
    }
    
    protected Menu getInitMenu()
    {
    	return null;
    }
    
    @Override
	public void run()
	{
    	initGame();
        startGame();
        
    	while(state == State.running)
    	{
    		updateGame();
    	}
	}

	public void loadStory()
	{
		story.initStory();
		story.startStory();
	}

	public boolean isDebug()
	{
		Log.d("get debug mode");
		return debug;
	}

	public void setDebug(boolean debug)
	{
		this.debug = debug;
	}
}
