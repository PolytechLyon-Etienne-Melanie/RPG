package test.rpg.perso.equipement;

import test.rpg.perso.Personnage;

public class Equipe {

    private String name;

    private int nbMaxPerso;

    public Personnage ajouterPerso(Personnage perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Personnage retirerPerso(Personnage perso) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getNbMaxPerso()
	{
		return nbMaxPerso;
	}

	public void setNbMaxPerso(int nbMaxPerso)
	{
		this.nbMaxPerso = nbMaxPerso;
	}
}
