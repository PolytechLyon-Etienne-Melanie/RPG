package test.rpg.perso.competence;

import test.rpg.perso.Entity;

public class Soin implements Capacite {

    public void rajoutVIe() {
    }

    @Override
	public float probaReussite(Entity src) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String effet(Entity cible, Entity src) {
		// TODO Auto-generated method stub
		return "Soin :";
	}
	
	public String getName()
	{
		return "Soin";
	}
}
