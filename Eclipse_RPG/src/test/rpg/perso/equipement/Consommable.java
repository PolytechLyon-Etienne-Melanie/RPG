package test.rpg.perso.equipement;

import java.util.ArrayList;
import java.util.List;

import test.rpg.perso.Entity;
import test.rpg.perso.effet.Effet;

public class Consommable extends Item 
{
	public static final List<Consommable> listConsommable = new ArrayList<Consommable>(256);
	
    public Consommable(String name, Effet caract, float poids)
	{
		super("Consommable", name, caract, poids);
		listConsommable.add(this);
	}

	public static Consommable getRandomConso()
	{
		int r = rand.nextInt(listConsommable.size());
		return listConsommable.get(r);
	}
	
	public String effet(Entity cible, Entity src)
	{
		cible.addEffet(this.getEffet());
		return cible + " possède maintenant l'effet " + this.getEffet();
	}
}
