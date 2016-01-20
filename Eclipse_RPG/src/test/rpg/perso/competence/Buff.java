package test.rpg.perso.competence;

import test.rpg.perso.Entity;
import test.rpg.perso.effet.Effet;

public class Buff implements Capacite
{
	private Effet effet;
	private String name;
	private boolean targeted;
	
	public Buff(String n, Effet e, boolean c)
	{
		this.effet = e;
		name = n;
		effet.setName(name);
		targeted = c;
	}
	
	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public float probaReussite(Entity src)
	{
		return 0;
	}

	@Override
	public String effet(Entity cible, Entity src)
	{
		if(targeted)
		{
			cible.addEffet(effet);
			return cible.getNom() + " possède maintenant le l'effet : " + effet;
		}
		else
		{
			src.addEffet(effet);
			return src.getNom() + " possède maintenant le l'effet : " + effet;
		}
	}
	
	public void setTargeted(boolean t)
	{
		targeted = t;
	}

	@Override
	public boolean isTargeted()
	{
		return targeted;
	}
	
	public String toString()
	{
		return effet.toString();
	}
}
