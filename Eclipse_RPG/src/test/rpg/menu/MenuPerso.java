package test.rpg.menu;

import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
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
		this.addCommand(key);
	}

	@Override
	protected void renderMenu()
	{
		renderPerso(perso);
	}
	
	private void renderPerso(Personnage entity)
	{
		write("<" + entity.getId_combat() + "> ", PrintColor.PURPLE);
		writeLine(entity.getNom() + " | Niveau : " + entity.getNiveau() + " | "
				+ entity.getClasse().getNom());
		writeLine("Stats classe: " + entity.getClasse().getCarac());
		String life = "::::::::::::::::::::::::::::::::::::";
		String notlife = "                                    ";
		
		float ratio = (float) (entity.getXp()) / (float) (entity.getXpToNextLevel());
		int l = (int) (life.length() * ratio);
		writeLine("XP : |" + life.substring(0, l) + notlife.substring(l) + "| " + entity.getXp() + "/"
				+ entity.getXpToNextLevel(), PrintColor.YELLOW);
		
		ratio = (float) (entity.getSante()) / (float) (entity.getSanteMax());
		l = (int) (life.length() * ratio);
		PrintColor color = PrintColor.GREEN;
		if (ratio < 0.2)
			color = PrintColor.RED;
		else if (ratio < 0.5)
			color = PrintColor.YELLOW;
		
		writeLine("PV : |" + life.substring(0, l) + notlife.substring(l) + "| " + entity.getSante() + "/"
				+ entity.getSanteMax(), color);
		renderEffets(entity);
	}

	private void renderEffets(Entity entity)
	{
		Iterator<Effet> i = entity.getEffets().iterator();
		while (i.hasNext())
		{
			Effet e = i.next();
			writeLine(" - " + e.toString(), PrintColor.YELLOW);
		}
	}
}
