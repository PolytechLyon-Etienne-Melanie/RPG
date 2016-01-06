package test.rpg.perso.effet;

import test.rpg.perso.classe.Caracteristique;

public class Effet {

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
}
