package test.rpg.perso.equipement;

import test.rpg.perso.Entity;
import test.rpg.perso.competence.Capacite;
import test.rpg.perso.effet.Effet;
import test.rpg.perso.effet.EffetSoin;

public class LivreCompetence extends Consommable
{
	private Capacite capa;
	public LivreCompetence(String name, Capacite capa)
	{
		super(name, null, 2, null);
		this.capa = capa;
	}

	public String effet(Entity cible, Entity src)
	{
		cible.getClasse().addCapacite(capa);
		return cible.getNom() + " a appris la compétence " + capa.getName() + ".";
	}
}
