package test.rpg.perso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import test.rpg.engine.console.printer.Log;
import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.classe.Classe;
import test.rpg.perso.classe.monstre.Rodeur;
import test.rpg.perso.effet.Effet;

public class Entity
{
	public static final int xpMount = 10;
	public static final float hpMount = 4;
	protected String nom;
	protected int niveau;
	protected int sante;

	protected int santeMax;
	protected Classe classe;
	
	protected Random rand;
	
	private List<Effet> effets;
	
	private int id_combat;
	
	public Entity(String nom, int n, Classe classe)
	{
		this.nom = nom;
		niveau = n;
		this.classe = classe;
		calculSanteMax();
		sante = santeMax;
		
		rand = new Random();
		
		effets = new ArrayList<Effet>();
		
		id_combat = -1;
	}
	
	public Entity()
	{
		this("Default Entity", 1, new Rodeur());
	}
	
	public boolean isAlive()
	{
		return sante > 0;
	}
	
	public Caracteristique getCaracteristique()
	{
		return classe.getCarac();
	}
	
	public void calculSanteMax()
	{
		santeMax = (int)( 50 + getCaracteristique().getSante() * niveau * hpMount);
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
		Log.d("sante " + sante);
		return sante;
	}

	public int getSanteMax()
	{
		Log.d("sante max " + santeMax);
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
	
	public int getXpVal()
	{
		return niveau * Entity.xpMount + rand.nextInt(Entity.xpMount);
	}

	public Caracteristique getTotalCarac()
	{
		Caracteristique c = new Caracteristique().add(classe.getCarac());
		Iterator<Effet> i = effets.iterator();
		while(i.hasNext())
		{
			c.add(i.next().getC());
		}
		return c;
	}
	
	public void addEffet(Effet e)
	{
		effets.add(e.cloneEffet());
	}

	public String updateEffet()
	{
		String s = "";
		Iterator<Effet> i = effets.iterator();
		while(i.hasNext())
		{
			Effet e = i.next();
			String s2 = e.update(this);
			if(s2 != "")
				s += s2 + " ";
			if(e.getPermanent() < 0)
			{
				i.remove();
			}
		}
		return s;
	}
	
	
	public List<Effet> getEffets()
	{
		return effets;
	}

	public void damages(int dmg)
	{
		this.sante -= dmg;
		if(sante < 0)
			sante = 0;
	}
	
	public void heal(int dmg)
	{
		this.sante += dmg;
		if(sante > santeMax)
			sante = santeMax;
	}

	public int getId_combat()
	{
		return id_combat;
	}

	public void setId_combat(int id_combat)
	{
		this.id_combat = id_combat;
	}
}
