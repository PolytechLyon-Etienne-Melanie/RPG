package test.rpg.perso.competence;

import test.rpg.perso.Entity;
import test.rpg.perso.effet.Effet;

public class Buff implements Capacite
{
	private Effet effet;
	private String name;
	
	public Buff(String n, Effet e)
	{
		this.effet = e;
		name = n;
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
		System.out.println(effet);
		return cible.getNom() + " possède maintenant le buff " + effet + " pour une durée de " + effet.getPermanent();
	}
}
