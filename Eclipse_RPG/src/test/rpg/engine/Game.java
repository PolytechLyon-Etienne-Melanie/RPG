package test.rpg.engine;

import test.rpg.engine.console.CustomConsole;
import test.rpg.engine.console.printer.JConsole;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.Story;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.StoryManager;
import test.rpg.menu.MenuChoixClasse;
import test.rpg.menu.MenuLoading;
import test.rpg.menu.MenuStory;
import test.rpg.perso.Personnage;

public class Game implements Runnable
{
    private StoryManager story;
    private Menu currentMenu;
    private MenuStory storyMenu;
    public static enum State {loading, running, inpause};
    private State state;
    private boolean debug = true;
    
    private Personnage hero;
    
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
	
	public void returnToStory()
	{
		this.setCurrentMenu(this.storyMenu);
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
    	if(e.isSetClasse() && hero == null)
    		this.setCurrentMenu(new MenuChoixClasse(this, e));
    	else
    	{
    		if(storyMenu == null)
    			storyMenu = new MenuStory(this, e);
    		else
    			storyMenu.setEvent(e);
    		this.setCurrentMenu(storyMenu);
    	}
    }
    
    public void setHero(Personnage p)
    {
    	this.hero = p;
    }
    
    public Personnage getHero()
    {
    	return this.hero;
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
