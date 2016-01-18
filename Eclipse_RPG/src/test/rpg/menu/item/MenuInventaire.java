package test.rpg.menu.item;

import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.equipement.Inventaire;
import test.rpg.perso.equipement.Item;

public class MenuInventaire extends Menu{
	
	private Inventaire inventaire;
	private Command retour;
	private Command equipeArme;
	private Command equipeArmure;
	private Command utiliserPotion;
	
	public MenuInventaire(Game game, Inventaire invent )
	{
		super(game);
		inventaire = invent;
	}

	protected void setCommands(){
		retour = new Command("Retourner au jeu.", "exit");
		retour.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.returnToStory();
			}
		});
		
		equipeArme = new Command("Voulez-vous équiper une arme ?", "Arme");
		equipeArme.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				//comment on gère le choix de l'item à équiper ?
			}
		});
		
		equipeArmure = new Command("Voulez-vous équiper une armure ?", "Armure");
		equipeArmure.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				//à compléter
			}
		});
		
		utiliserPotion = new Command("Voulez-vous utiliser une potion ?", "Potion");
		utiliserPotion.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				//à compléter
			}
		});
		
		this.addCommand(retour);
		this.addCommand(equipeArme);
		this.addCommand(equipeArmure);
		this.addCommand(utiliserPotion);
	}
	
	@Override
	protected void initMenu() 
	{
		Command back = new Command("Retour a l'histoire", "back");
		back.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				game.returnToStory();
			}
		});
		this.addCommand(back);
		
		Command use = new Command("Utiliser item", "use", "item");
		use.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				useItem(p);
			}
		});
		this.addCommand(use);
		
	}
	

	private void useItem(String p)
	{
		int i = this.getCommandParamInt(p);
		try
		{
			Item item = inventaire.getItems().get(i);
			game.setCurrentMenu(new MenuUseItem(game, this, item));
		}
		catch(IndexOutOfBoundsException e)
		{
			Log.e("Not a valid Target");
		}
	}

	@Override
	protected void renderMenu() {
		writeLine(" _____________________________________________________________________________________");
		writeLine("| __      __   _              _____                      _        _                  |");         
		writeLine("| \\ \\    / /  | |            |_   _|                    | |      (_)             _   |");          
		writeLine("|  \\ \\  / /__ | |_ _ __ ___    | |  _ ____   _____ _ __ | |_ __ _ _ _ __ ___    (_)  |"); 
		writeLine("|   \\ \\/ / _ \\| __| '__/ _ \\   | | | '_ \\ \\ / / _ \\ '_ \\| __/ _` | | '__/ _ \\        |"); 
		writeLine("|    \\  / (_) | |_| | |  __/  _| |_| | | \\ V /  __/ | | | || (_| | | | |  __/    _   |");
		writeLine("|     \\/ \\___/ \\__|_|  \\___| |_____|_| |_|\\_/ \\___|_| |_|\\__\\__,_|_|_|  \\___|   (_)  |");
		writeLine("|____________________________________________________________________________________|");
		writeLine("");
		
		writeLine("Vous possedez les items suivant : ");
		Iterator<Item> i = inventaire.getItems().iterator();
		int id = 0;
		while (i.hasNext())
		{
			Item item = i.next();
			write("<"+id+"> ");
			writeLine(item.toString());	
			id++;
		}
		writeLine("");
		
		writeLine("Vous êtes équipé de l'arme siuvante :"+inventaire.getArmreeq());
		
		writeLine("");
		writeLine("Vous êtes équipé de l'armure suivante :" +inventaire.getArmureeq());
	}	// command equiper/jeter/ ...

}
