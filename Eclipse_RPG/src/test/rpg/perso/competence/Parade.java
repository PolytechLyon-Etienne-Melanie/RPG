package test.rpg.perso.competence;

import test.rpg.perso.Entity;

public class Parade implements Capacite {

	@Override
	public float probaReussite(Entity src) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String effet(Entity cible, Entity src) {
		// TODO Auto-generated method stub
		return "Bloquage de la prochaine attaque";
	}
	
	public String getName()
	{
		return "Parade";
	}
}
