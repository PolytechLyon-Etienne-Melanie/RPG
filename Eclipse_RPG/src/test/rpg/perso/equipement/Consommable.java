package test.rpg.perso.equipement;

import java.util.ArrayList;
import java.util.List;

import test.rpg.perso.Entity;
import test.rpg.perso.effet.Effet;
import test.rpg.perso.effet.EffetSoin;

public class Consommable extends Item
{
	public static final List<Consommable> listConsommable = new ArrayList<Consommable>(256);

	public Consommable(String name, Effet caract, float poids, String ef)
	{
		super("Consommable", name, caract, poids);
		listConsommable.add(this);
		if(effet != null)
			effet.setName(ef);
	}

	public static Consommable getRandomConso()
	{
		int r = rand.nextInt(listConsommable.size());
		return listConsommable.get(r);
	}

	public String effet(Entity cible, Entity src)
	{
		Effet e = this.getEffet();
		if (e instanceof EffetSoin)
		{
			cible.heal(e.getValeur());
			return cible.getNom() + " restaure " + e.getValeur() + " points de vie.";
		} else
		{
			cible.addEffet(this.getEffet());
			return cible.getNom() + " possède maintenant l'effet " + this.getEffet();
		}
	}
}
