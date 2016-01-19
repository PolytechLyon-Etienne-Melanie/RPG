package test.rpg.menu;

import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.printer.Log;
import test.rpg.engine.console.printer.PrintColor;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.Entity;
import test.rpg.perso.Personnage;
import test.rpg.perso.effet.Effet;

public class MenuPerso extends Menu
{
	Personnage perso;
	
	public MenuPerso(Game game)
	{
		super(game, "Personnages");
		perso = game.getHero();
		perso.setId_combat(0);
	}

	@Override
	protected void initMenu()
	{
		Command key = new Command("Appuyez sur <Entrer> pour retourner à l'histoire.", "");
		key.addObserver(new EventObserver(){

			@Override
			public void actionPerformed(String param)
			{
				game.returnToStory();
			}
		});
	}

	@Override
	protected void renderMenu()
	{
		renderEntity(perso);
	}
	
	private void renderEntity(Entity entity)
	{
		writeLine("<" + entity.getId_combat() + "> " + entity.getNom() + " | Niveau : " + entity.getNiveau() + " | "
				+ entity.getClasse().getNom());
		String life = "::::::::::::::::::::::::::::::::::::";
		String notlife = "                                    ";
		float ratio = (float) (entity.getSante()) / (float) (entity.getSanteMax());
		Log.d(ratio);
		int l = (int) (life.length() * ratio);
		Log.d(l);
		PrintColor color = PrintColor.GREEN;
		if (ratio < 0.2)
			color = PrintColor.RED;
		else if (ratio < 0.5)
			color = PrintColor.YELLOW;

		writeLine("|" + life.substring(0, l) + notlife.substring(l) + "| " + entity.getSante() + "/"
				+ entity.getSanteMax(), color);
		renderEffets(entity);
	}

	private void renderEffets(Entity entity)
	{
		Iterator<Effet> i = entity.getEffets().iterator();
		while (i.hasNext())
		{
			Effet e = i.next();
			writeLine(" - " + e.toString());
		}
	}
}
