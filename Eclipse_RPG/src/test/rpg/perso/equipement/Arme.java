package test.rpg.perso.equipement;

import test.rpg.perso.effet.Effet;

public class Arme extends Item 
{
	private float degat;
    private float maniabilite;
    
    public Arme(String name, Effet caract, float poids)
	{
		super(name, caract, poids);
	}

    public float getDegat()
	{
		return degat;
	}

	public void setDegat(float degat)
	{
		this.degat = degat;
	}

	public float getManiabilite()
	{
		return maniabilite;
	}

	public void setManiabilite(float maniabilite)
	{
		this.maniabilite = maniabilite;
	}
}
