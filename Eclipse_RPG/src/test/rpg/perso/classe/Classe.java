package test.rpg.perso.classe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import test.rpg.perso.competence.Capacite;

public class Classe
{
	private Caracteristique carac;
	private String nom;
	private List<Capacite> capa;
	
	public Classe(String nom, int f, int dex, int s, int def, int m)
	{
		this.nom = nom;
		carac = new Caracteristique(f, dex, s, def, m);
		capa = new ArrayList<Capacite>();
	}
	
	public void addCapacite(Capacite capa)
	{
		this.capa.add(capa);
	}

	public Caracteristique getCarac() {
		return carac;
	}

	public void setCarac(Caracteristique carac) {
		this.carac = carac;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Capacite> getCapa() {
		return capa;
	}

	public void setCapa(List<Capacite> capa) {
		this.capa = capa;
	}

}
