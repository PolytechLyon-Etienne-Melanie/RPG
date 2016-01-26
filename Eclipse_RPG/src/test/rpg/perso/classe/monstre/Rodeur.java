package test.rpg.perso.classe.monstre;

import test.rpg.perso.competence.Attaque;
import test.rpg.perso.competence.AttaqueBuff;
import test.rpg.perso.competence.Buff;
import test.rpg.perso.effet.Effet;

public class Rodeur extends Monstre 
{
	public Rodeur()
	{
		super("Rodeur", 28, 5, 20, 15, 10);
		this.addCapacite(new Attaque("Griffe", 2,1,1));
		this.addCapacite(new Buff("Camouflage", new Effet().setDef(100).setDuree(1), false));
		this.addCapacite(new AttaqueBuff("Rugissement", 0.9f, 0.5f, 0.5f, "Peur", new Effet().setForce(-10).setMagie(-10).setDex(-10).setDuree(3)).setHimself(false));
	}
}
