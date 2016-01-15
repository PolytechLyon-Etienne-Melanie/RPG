package test.rpg.perso.competence.mage;

import test.rpg.perso.competence.Buff;
import test.rpg.perso.effet.Effet;

public class MentalControl extends Buff
{
	public MentalControl()
	{
		super("Controle mental", new Effet(-10,-10,0,-10,-10).setDuree(2));
	}
}
