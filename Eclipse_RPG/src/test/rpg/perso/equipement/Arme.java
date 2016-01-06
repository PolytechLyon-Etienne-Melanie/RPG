package test.rpg.perso.equipement;

import test.rpg.perso.effet.Effet;

public class Arme extends Item 
{
	public static Arme[] list = new Arme[256];
	private static int id = 0;
	
	private float degat;
    private float maniabilite;
    
    protected Arme(String name, Effet caract, float poids)
	{
		super(name, caract, poids);
		list[id] = this;
		id++;
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
	
	public Arme epee = new Arme("Epee", new Effet().setForce(5), 5);
}
