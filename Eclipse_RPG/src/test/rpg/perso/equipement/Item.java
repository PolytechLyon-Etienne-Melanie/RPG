package test.rpg.perso.equipement;

import java.util.List;

import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.effet.Effet;

public class Item {

    private String nom;
    private float poids;
    private Effet effet;

    public Item(String name, Effet caract, float poids) {
    	nom = name;
    	this.poids = poids;
    }

    public Caracteristique getValeurEffet() 
    {
    	return effet.getC();
    }

    public void setEffet(Effet effect) 
    {
    	effet = effect;
    }
    
    public Effet getEffet() 
    {
    	return effet;
    }

	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public float getPoids()
	{
		return poids;
	}

	public void setPoids(float poids)
	{
		this.poids = poids;
	}
}
