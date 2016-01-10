package test.rpg.perso.equipement;

import java.util.ArrayList;
import java.util.List;

import test.rpg.perso.effet.Effet;

public class Arme extends Item 
{
	public static final List<Arme> listArme = new ArrayList<Arme>(256);

	private float degat;
    private float maniabilite;
    
    protected Arme(String name, Effet caract, float poids)
	{
		super("Arme", name, caract, poids);
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
