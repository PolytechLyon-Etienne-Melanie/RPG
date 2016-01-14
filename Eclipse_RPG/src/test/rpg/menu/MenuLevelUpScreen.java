package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.console.event.KeyObserver;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Personnage;

public class MenuLevelUpScreen extends Menu
{
	private Personnage perso;
	
	public MenuLevelUpScreen(Game game, Personnage p)
	{
		super(game, "Level up");
		this.perso = p;
	}


	protected void setCommands()
	{
		KeyObserver key = new KeyObserver();
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuLevelUp(game, perso));
			}
		});
		this.addCommand(key);
	}

	@Override
	protected void initMenu() {
		setCommands();
		
	}

	@Override
	protected void renderMenu() {
		writeLine(" /$$                                     /$$       /$$   /$$");          
		writeLine("| $$                                    | $$      | $$  | $$");          
		writeLine("| $$        /$$$$$$  /$$    /$$ /$$$$$$ | $$      | $$  | $$  /$$$$$$"); 
		writeLine("| $$       /$$__  $$|  $$  /$$//$$__  $$| $$      | $$  | $$ /$$__  $$");
		writeLine("| $$      | $$$$$$$$ \\  $$/$$/| $$$$$$$$| $$      | $$  | $$| $$  \\ $$");
		writeLine("| $$      | $$_____/  \\  $$$/ | $$_____/| $$      | $$  | $$| $$  | $$");
		writeLine("| $$$$$$$$|  $$$$$$$   \\  $/  |  $$$$$$$| $$      |  $$$$$$/| $$$$$$$/");
		writeLine("|________/ \\_______/    \\_/    \\_______/|__/       \\______/ | $$____/ ");
		writeLine("                                                            | $$");      
		writeLine("                                                            | $$");      
		writeLine("                                                            |__/");
		
	}
}
