package test.rpg.engine.story.event;

import test.rpg.perso.Entity;
import test.rpg.perso.classe.Classe;
import test.rpg.perso.classe.Guerrier;
import test.rpg.perso.classe.monstre.Rodeur;

public class EventEntity implements Event
{
	public enum ClasseE {
		RODEUR,
		GUERRIER
	};
	protected String nom;
	protected int niveau;
	protected ClasseE classe;
	
	public EventEntity(String nom, int n, ClasseE c)
	{
		this.nom = nom;
		niveau = n;
		classe = c;
	}
	
	public EventEntity()
	{
		this("Default Entity", 1, ClasseE.RODEUR);
	}
	
	public Entity genEntity()
	{
		return new Entity(nom, niveau, getClasse());
	}
	
	public String getNom()
	{
		return nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public int getNiveau()
	{
		return niveau;
	}

	public void setNiveau(int niveau)
	{
		this.niveau = niveau;
	}

	public void setClasse(ClasseE classe)
	{
		this.classe = classe;
	}
	
	public ClasseE getClasseE()
	{
		return classe;
	}

	public Classe getClasse()
	{
		Classe c = null;
		switch(classe)
		{
			case RODEUR:
				c = new Rodeur();
				break;
			case GUERRIER:
				c = new Guerrier();
				break;
		default:
			break;
		}
		return c;
	}
	
	@Override
	public String toString()
	{
		return "Nom : " + nom + " | Niveau : " + niveau + " | Classe : " + classe;
	}
}
