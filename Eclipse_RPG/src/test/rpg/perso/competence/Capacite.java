package test.rpg.perso.competence;

import test.rpg.perso.Personnage;

public interface Capacite 
{
	public String getName();
	
    public float probaReussite(Personnage src);

    public void effet(Personnage cible, Personnage src);
}
