package test.rpg.perso.classe;

import test.rpg.perso.competence.Attaque;
import test.rpg.perso.competence.AttaqueBuff;
import test.rpg.perso.competence.Buff;
import test.rpg.perso.effet.Effet;

public class Mage extends Classe
{
	public Mage()
	{
		super("Mage", 0, 30, 15, 15, 40);
		this.addCapacite(new Attaque("Boule de feu", 0, 0.5f, 1.8f));
		this.addCapacite(new Buff("Concentration", new Effet(10,10,0,-5,20).setDuree(1), false));
		this.addCapacite(new AttaqueBuff("Nova de givre", 0, 0.1f, 0.8f, "Gelé", new Effet(-20,-20,0,-10,-20).setDuree(2)).setHimself(false));
	}
}

