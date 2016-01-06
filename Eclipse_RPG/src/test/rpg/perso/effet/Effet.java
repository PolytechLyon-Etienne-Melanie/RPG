package test.rpg.perso.effet;

import test.rpg.perso.classe.Caracteristique;

public class Effet {

	public Effet(int per, int val, Caracteristique c)
	{
		this.c = c;
		valeur = val;
		permanent = per;
	}
	
	public Effet()
	{
		this(0,0,0,0);
	}
	
	public Effet(Caracteristique c)
	{
		this(-1, 0, c);
	}
	
	public Effet(int f, int dex, int s, int def)
	{
		this(new Caracteristique(f, dex, s, def));
	}
	
    private Caracteristique c;

    private int valeur;
    
	private int permanent;

    public Caracteristique getC()
	{
		return c;
	}

	public void setC(Caracteristique c)
	{
		this.c = c;
	}

	public int getValeur()
	{
		return valeur;
	}

	public void setValeur(int valeur)
	{
		this.valeur = valeur;
	}

	public int getPermanent()
	{
		return permanent;
	}

	public void setPermanent(int permanent)
	{
		this.permanent = permanent;
	}
	
	public Effet setForce(int f)
	{
		c.setForce(f);
		return this;
	}
}
