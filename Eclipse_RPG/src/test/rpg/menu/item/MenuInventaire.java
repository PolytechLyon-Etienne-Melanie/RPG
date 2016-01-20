package test.rpg.menu.item;

import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Personnage;
import test.rpg.perso.equipement.Inventaire;
import test.rpg.perso.equipement.Item;

public class MenuInventaire extends Menu{
	
	private Inventaire inventaire;
	private Command equipeArme;
	private Command equipeArmure;
	private Command jeterItem;
	
	private Personnage perso;
	
	public MenuInventaire(Game game, Personnage perso)
	{
		super(game);
		inventaire = perso.getInventaire();
		this.perso = perso;
	}

	protected void setCommands(){
	}
	
	@Override
	protected void initMenu() 
	{
		Command back = new Command("Retour a l'histoire", "retour");
		back.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				game.returnToStory();
			}
		});
		
		Command use = new Command("Utiliser item", "use", "n° item");
		use.addObserver(new EventObserver()
		{
			@Override
			public void actionPerformed(String p)
			{
				useItem(p);
			}
		});
		this.addCommand(use);
		
		equipeArme = new Command("Voulez-vous équiper une arme ?", "arme", "n°");
		equipeArme.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{

				equipArme(p);
			}
		});
		
		equipeArmure = new Command("Voulez-vous équiper une armure ?", "armure", "n°");
		equipeArmure.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				equipArmure(p);
			}
		});
		
		jeterItem = new Command("Voulez-vous vous débarrasser d'un objet ?", "item", "n°");
		jeterItem.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				jeterItems(p);
			}
		});
		
		this.addCommand(equipeArme);
		this.addCommand(equipeArmure);
		this.addCommand(jeterItem);
		this.addCommand(back);
	}
	

	private void useItem(String p)
	{
		int i = this.getCommandParamInt(p);
		try
		{
			Item item = inventaire.getItems().get(i);
			game.setCurrentMenu(new MenuUseItem(game, this, item, perso));
		}
		catch(IndexOutOfBoundsException e)
		{
			Log.e("Not a valid Target");
		}
	}
	
	private void equipArme(String p)
	{
		int i = this.getCommandParamInt(p);
		try
		{
			Item item = inventaire.getItems().get(i);
			game.setCurrentMenu(new MenuEquipArme(game, this, item, perso));
		}
		catch(IndexOutOfBoundsException e)
		{
			Log.e("Not a valid Target");
		}
	}
	
	private void equipArmure(String p)
	{
		int i = this.getCommandParamInt(p);
		try
		{
			Item item = inventaire.getItems().get(i);
			game.setCurrentMenu(new MenuEquipArmure(game, this, item, perso));
		}
		catch(IndexOutOfBoundsException e)
		{
			Log.e("Not a valid Target");
		}
	}
	
	private void jeterItems(String p)
	{
		int i = this.getCommandParamInt(p);
		try
		{
			Item item = inventaire.getItems().get(i);
			game.setCurrentMenu(new MenuJeterItem(game, this, item, perso));
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
		
		writeLine("Vous êtes équipé de l'arme suivante :"+inventaire.getArmreeq());
		
		writeLine("");
		writeLine("Vous êtes équipé de l'armure suivante :" +inventaire.getArmureeq());
	}

}
