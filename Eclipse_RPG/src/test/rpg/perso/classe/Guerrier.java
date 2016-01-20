package test.rpg.perso.classe;

import test.rpg.perso.competence.Attaque;
import test.rpg.perso.competence.AttaqueBuff;
import test.rpg.perso.competence.Buff;
import test.rpg.perso.effet.Effet;

public class Guerrier extends Classe
{
	public Guerrier()
	{
		super("Guerrier", 25, 10, 30, 25, 0);
		this.addCapacite(new Attaque("Charge", 1.5f, 0.5f, 0));
		this.addCapacite(new Buff("Maitrise du blocage", new Effet().setDef(10).setDuree(2), false));
		this.addCapacite(new AttaqueBuff("Frappe héroique", 2.5f, 0.5f, 0, "Fatigue", new Effet().setDef(-15).setDuree(2)).setHimself(true));
	}
}