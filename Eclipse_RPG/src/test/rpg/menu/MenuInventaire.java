package test.rpg.menu;

import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.interfaces.Menu;
import test.rpg.perso.equipement.Inventaire;
import test.rpg.perso.equipement.Item;

public class MenuInventaire extends Menu{
	
	private Inventaire inventaire;
	
	public MenuInventaire(Game game, Inventaire invent )
	{
		super(game);
		inventaire = invent;
		
		
	}

	protected void setCommands(){
		
	}
	
	
	@Override
	protected void initMenu() {
		setCommands();
		
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
		while (i.hasNext())
		{
			Item item = i.next();
			writeLine(item.toString());	
			
		}
		writeLine("");
		
		writeLine("Vous êtes équipé de l'arme siuvante :"+inventaire.getArmreeq());
		
		writeLine("");
		writeLine("Vous êtes équipé de l'armure suivante :" +inventaire.getArmureeq());
	} //commande sorti + regler pb armure + refaire titre (pas beau) + command equiper/jeter/ ...

}
