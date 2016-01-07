package test.rpg.perso;

import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.classe.Classe;

public abstract class Entity {
	
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
	
	public Caracteristique getCaracteristique()
	{
		return classe.getCarac();
	}
	
	public void calculSanteMax()
	{
		santeMax = getCaracteristique().getSante() * niveau;
	}
}
