package test.rpg.perso.equipement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.effet.Effet;
import test.rpg.perso.effet.EffetSoin;

public class Item
{
	public static final List<Item> listItem = new ArrayList<Item>();
	private static int ID = 0;

	public static Item lootRandom;
	public static Arme epeeRandom;
	public static Consommable consoRandom;
	public static Armure armureRandom;
	
	public static Arme armeDebut;
	public static Arme epeeBase;
	public static Arme epeeMoyenne;
	public static Arme epeeAvancee;

	public static Arme batonMagiqueBase;
	public static Arme batonMagiqueMoyen;
	public static Arme batonMagiqueExpert;

	public static Arme dagueBase;
	public static Arme dagueMoyenne;
	public static Arme dagueAvancee;

	public static Consommable potionSoin;
	public static Consommable potionForce;
	public static Consommable potionMagie;
	public static Consommable potionDex;
	
	public static Armure armureDebut;
	public static Armure armureBase;
	public static Armure armureMoyenne;
	public static Armure armureAvancee;
	public static Armure capeDeVoleur;
	public static Armure robeDeSorcier;


	
	protected static Random rand = new Random();

	private String nom;
	private float poids;
	private Effet effet;
	private int id;

	private String type;

	public Item(String t, String name, Effet caract, float poids)
	{
		type = t;
		nom = name;
		this.poids = poids;
		id = ID++;
		this.effet = caract;

		listItem.add(this);
	}

	public Item(String t, String name, float poids)
	{
		this(t, name, new Effet(), poids);
	}

	public Item(String name, Effet caract, float poids)
	{
		this("Item", name, caract, poids);
	}

	public Item(String name, float poids)
	{
		this("Item", name, new Effet(), poids);
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

	public String toString()
	{
		return type + " : " + nom + " | Effet :" + effet;
	}

	public static Item getRandomLoot()
	{
		int r = rand.nextInt(listItem.size());
		return listItem.get(r);
	}

	static
	{
		lootRandom = new Item("Loot Random", 0)
		{
			public Item getItem()
			{
				int r = this.getID();
				while (r == this.getID())
				{
					r = rand.nextInt(listItem.size());
				}
				return listItem.get(r);
			}
		};
		epeeRandom = new Arme("Epee al�atoire", new Effet(), 0)
		{
			public Item getItem()
			{
				int r = this.getID();
				while (r == this.getID())
				{
					r = rand.nextInt(listArme.size());
				}
				return listArme.get(r);
			}
		};
		consoRandom = new Consommable("Consommable Random", new Effet(), 0)
		{
			public Item getItem()
			{
				int r = 0;
				Item i = this;
				while (i.getID() == this.getID())
				{
					r = rand.nextInt(listConsommable.size());
					i = listConsommable.get(r);
				}
				return listConsommable.get(r);
			}
		};

		potionSoin = new Consommable("Potion de soin", new EffetSoin(50), 1);
		potionForce = new Consommable("Potion de force", new Effet().setForce(20).setDuree(3), 1);
		potionMagie = new Consommable("Potion de magie", new Effet().setMagie(20).setDuree(3), 1);
		potionDex = new Consommable("Potion de dexterit�", new Effet().setDex(20).setDuree(3), 1);

		armeDebut = new Arme("Noob", new Effet(1,1,0,0,1), 2);
		epeeBase = new Arme("Adria", new Effet().setForce(2), 2);
		epeeMoyenne = new Arme("Asmodan", new Effet().setForce(5), 6);
		epeeAvancee = new Arme("Diablo", new Effet().setForce(10), 18);

		batonMagiqueBase = new Arme("Cyda�", new Effet().setMagie(2), 2);
		batonMagiqueMoyen = new Arme("Auriel", new Effet().setMagie(5), 3);
		batonMagiqueExpert = new Arme("Tyra�l", new Effet().setMagie(10), 5);

		dagueBase = new Arme("Piquant d'Ambre", new Effet(2, 3, 0, 0, 0), 2);
		dagueMoyenne = new Arme("Dague des sept �toiles", new Effet(4, 6, 0, 0, 0), 2);
		dagueAvancee = new Arme("L'appel du Dragon", new Effet(8, 12, 0, 0, 0), 2);
		
		armureDebut = new Armure("BodyNoob", new Effet(0, 0, 1, 1, 0), 2);
		armureBase = new Armure("Le Serment du Sage", new Effet(0, 0, 2, 3, 0), 2);
		armureMoyenne = new Armure("Le P�ril du Gardien", new Effet(0, 0, 6, 9, 0), 5);
		armureAvancee = new Armure("La Fatalit� de Sol", new Effet(0, 0, 18, 27, 0), 10);
		capeDeVoleur = new Armure("La Fatalit� de Sol", new Effet(0, 0, 18, 27, 0), 3);
		robeDeSorcier = new Armure("Les Atours de l'oiseau de Feu", new Effet(0, 0, 9, 10, 5), 3);

	}

	public static Item[] getItems()
	{
		Item[] array = new Item[listItem.size()];
		return listItem.toArray(array);
	}

	public static Item getItem(int loot)
	{
		Iterator<Item> i = listItem.iterator();
		Item item = null;
		int id = -1;
		while (i.hasNext() && id != loot)
		{
			item = i.next();
			id = item.getID();
		}

		if (id == loot)
			return item.getItem();
		else
			return null;
	}
	
	public static Item getItemForEditor(int loot)
	{
		Iterator<Item> i = listItem.iterator();
		Item item = null;
		int id = -1;
		while (i.hasNext() && id != loot)
		{
			item = i.next();
			id = item.getID();
		}

		if (id == loot)
			return item;
		else
			return null;
	}

	public int getID()
	{
		return id;
	}

	public Item getItem()
	{
		return this;
	}
}
