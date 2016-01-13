package test.rpg.perso.classe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import test.rpg.perso.competence.Capacite;
import test.rpg.perso.equipement.Arme;
import test.rpg.perso.equipement.Armure;

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

	public void increaseForce() {
		carac.setForce(carac.getForce()+1);
		
	}
	
	public void increaseDef() {
		carac.setDefense(carac.getDefense()+1);
		
	}
	
	public void increaseDex() {
		carac.setDexterite(carac.getDexterite()+1);
		
	}
	
	public void increaseSante() {
		carac.setSante(carac.getSante()+1);
		
	}
	
	public void increaseMagie() {
		carac.setMagie(carac.getMagie()+1);
		
	}
	
	public Arme getDefaultArme(){
		return Arme.armeDebut;
	}
	
	public Armure getDefaultArmure(){
		return Armure.armureDebut;
		
	}
}