package test.rpg.perso.classe;

import test.rpg.perso.competence.Attaque;
import test.rpg.perso.competence.AttaqueBuff;
import test.rpg.perso.competence.Buff;
import test.rpg.perso.effet.Effet;

public class Assassin extends Classe
{
	public Assassin()
	{
		super("Assassin", 30, 50, 10, 10, 0);
		this.addCapacite(new Attaque("Attaque sournoise", 0.0f,2.5f,0.5f));
		this.addCapacite(new Buff("Camouflage", new Effet().setDef(50).setDex(10).setDuree(1), false));
		this.addCapacite(new AttaqueBuff("Rush", 0.5f, 1.2f, 0.0f, "Destabilisé", new Effet().setForce(-5).setMagie(-5).setDex(-5).setDuree(2)).setHimself(false));
	}
}

