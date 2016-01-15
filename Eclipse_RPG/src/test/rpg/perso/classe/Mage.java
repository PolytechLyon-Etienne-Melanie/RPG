package test.rpg.perso.classe;

import test.rpg.perso.competence.mage.FireBall;
import test.rpg.perso.competence.mage.MentalControl;

public class Mage extends Classe
{
	public Mage()
	{
		super("Mage", 0, 30, 15, 15, 40);
		this.addCapacite(new FireBall());
		this.addCapacite(new MentalControl());
	}
}

