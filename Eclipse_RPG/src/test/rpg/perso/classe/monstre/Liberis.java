package test.rpg.perso.classe.monstre;

import test.rpg.perso.competence.Attaque;
import test.rpg.perso.competence.AttaqueBuff;
import test.rpg.perso.competence.Buff;
import test.rpg.perso.effet.Effet;

public class Liberis extends Monstre
{
	public Liberis()
	{
		super("Liberis", 20, 5, 20, 15, 0);
		this.addCapacite(new Attaque("Frappe sauvage", 2,1,1));
		this.addCapacite(new Buff("Esquive", new Effet().setDef(100).setDuree(1), false));
		this.addCapacite(new AttaqueBuff("Casse-Poigne", 0.5f, 0.5f, 0.5f, "Essouflé", new Effet().setForce(-10).setMagie(-10).setDex(-10).setDuree(2)).setHimself(true));
	}
}
