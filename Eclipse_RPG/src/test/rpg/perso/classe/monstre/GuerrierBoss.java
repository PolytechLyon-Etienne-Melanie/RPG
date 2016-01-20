package test.rpg.perso.classe.monstre;

import test.rpg.perso.classe.Guerrier;
import test.rpg.perso.competence.Attaque;
import test.rpg.perso.competence.AttaqueBuff;
import test.rpg.perso.competence.Buff;
import test.rpg.perso.effet.Effet;

public class GuerrierBoss extends Guerrier
{
	public GuerrierBoss()
	{
		super();
		this.addCapacite(new Attaque("Heurtoir", 1.5f, 0.6f, 0.5f));
		this.addCapacite(new Attaque("Frappe du colosse", 1.2f, 1, 0.6f));
		this.addCapacite(new AttaqueBuff("Coup de bouclier", 0.5f, 0.5f, 0, "Bouclier", new Effet().setDef(35).setDuree(2)).setHimself(true));
	}
}
