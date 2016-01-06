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
	
	public Arme epeeBase = new Arme("Adria", new Effet().setForce(2), 2);
	public Arme epeeMoyenne = new Arme("Asmodan", new Effet().setForce(5), 6);
	public Arme epeeAvancee = new Arme("Diablo", new Effet().setForce(10), 18);
	
	public Arme batonMagiqueBase = new Arme ("Cydaé", new Effet().setMagie(2),2);
	public Arme batonMagiqueMoyen = new Arme ("Auriel", new Effet().setMagie(5),3);
	public Arme batonMagiqueExpert = new Arme ("Tyraël", new Effet().setMagie(10),5);
	
	public Arme dagueBase = new Arme("Piquant d'Ambre", new Effet(2,3,0,0,0), 2);
	public Arme dagueMoyenne = new Arme("Dague des sept étoiles", new Effet(4,6,0,0,0), 2);
	public Arme dagueAvancee = new Arme("L'appel du Dragon", new Effet(8,12,0,0,0), 2);
	
		
}
