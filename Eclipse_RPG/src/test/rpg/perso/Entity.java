package test.rpg.perso;

import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.classe.Classe;
import test.rpg.perso.classe.monstre.Rodeur;

public class Entity {
	
	protected String nom;
	protected int niveau;
	protected int sante;

	protected int santeMax;
	protected Classe classe;
	
	public Entity(String nom, int n, Classe classe)
	{
		this.nom = nom;
		niveau = n;
		this.classe = classe;
		calculSanteMax();
		sante = santeMax;
	}
	
	public Entity()
	{
		this("Default Monster", 1, new Rodeur());
	}
	
	public Caracteristique getCaracteristique()
	{
		return classe.getCarac();
	}
	
	public void calculSanteMax()
	{
		santeMax = getCaracteristique().getSante() * niveau;
	}
	
	public String getNom()
	{
		return nom;
	}

	public int getNiveau()
	{
		return niveau;
	}

	public int getSante()
	{
		return sante;
	}

	public int getSanteMax()
	{
		return santeMax;
	}

	public Classe getClasse()
	{
		return classe;
	}
	
	@Override
	public String toString()
	{
		return "Nom : " + nom + " | Niveau : " + niveau + " | Classe : " + classe.getNom();
	}
}
