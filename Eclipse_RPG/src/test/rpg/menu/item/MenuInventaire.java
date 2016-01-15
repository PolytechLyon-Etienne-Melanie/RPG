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
	
	public MenuInventaire(Game game, Inventaire invent )
	{
		super(game);
		inventaire = invent;
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
		writeLine("_________________________________________________________________________________________________________________________________________________________");
		writeLine("  _|      _|              _|                              _|_|_|                                              _|                _|                      ");         
		writeLine("  _|      _|    _|_|    _|_|_|_|  _|  _|_|    _|_|          _|    _|_|_|    _|      _|    _|_|    _|_|_|    _|_|_|_|    _|_|_|      _|  _|_|    _|_|   ");          
		writeLine("  _|      _|  _|    _|    _|      _|_|      _|_|_|_|        _|    _|    _|  _|      _|  _|_|_|_|  _|    _|    _|      _|    _|  _|  _|_|      _|_|_|_|  "); 
		writeLine("    _|  _|    _|    _|    _|      _|        _|              _|    _|    _|    _|  _|    _|        _|    _|    _|      _|    _|  _|  _|        _|         "); 
		writeLine("       _|        _|_|        _|_|  _|          _|_|_|      _|_|_|  _|    _|      _|        _|_|_|  _|    _|      _|_|    _|_|_|  _|  _|          _|_|_|  ");
		writeLine("_________________________________________________________________________________________________________________________________________________________");
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
	} //commande sorti + regler pb armure + refaire titre (pas beau) + command equiper/jeter/ ...

}
