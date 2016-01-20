package test.rpg.perso.effet;

import test.rpg.perso.Entity;
import test.rpg.perso.classe.Caracteristique;

public class Effet {
	
	private String name;
	
	protected Caracteristique c;

    protected int valeur;
    
    protected int permanent;
	
	public Effet(int per, int val, Caracteristique c)
	{
		this.c = c;
		valeur = val;
		permanent = per;
		name = "";
	}
	
	public Effet()
	{
		this(0,0,0,0,0);
	}
	

	public Effet(int per, int v)
	{
		this(per, v, new Caracteristique());
	}
	
	public Effet(int v)
	{
		this(-1, v, new Caracteristique());
	}
	
	
	public Effet(Caracteristique c)
	{
		this(-1, 0, c);
	}
	
	public Effet(int f, int dex, int s, int def, int m)
	{
		this(new Caracteristique(f, dex, s, def, m));
	}
	
	public void setName(String n)
	{
		name = n;
	}

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
	
	public Effet setDef(int d)
	{
		c.setDefense(d);
		return this;
	}
	
	public Effet setForce(int f)
	{
		c.setForce(f);
		return this;
	}
	
	public Effet setMagie(int f)
	{
		c.setMagie(f);
		return this;
	}
	
	public Effet setDex(int f)
	{
		c.setDexterite(f);
		return this;
	}
	
	public String toString()
	{
		String r = "";
		if(name != "")
			r += name + " | ";
		if(valeur != 0)
			r += "Valeur: " + valeur;
		if(permanent != -1)
			r += ", Durée: " + permanent;
		return r + ", " + c;
	}

	public Effet setDuree(int i)
	{
		this.permanent = i;
		return this;
	}
	
	public String update(Entity src)
	{
		this.permanent -= 1;
		return "";
	}

	public Effet cloneEffet()
	{
		Effet e = new Effet(permanent, valeur, c.cloneCarac());
		e.setName(name);
		return e;
	}
}
