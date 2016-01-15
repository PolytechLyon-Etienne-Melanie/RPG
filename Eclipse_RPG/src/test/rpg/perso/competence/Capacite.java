package test.rpg.perso.competence;

import test.rpg.perso.Entity;

public interface Capacite 
{
	public String getName();
	
    public float probaReussite(Entity src);

    public String effet(Entity cible, Entity src);
}
