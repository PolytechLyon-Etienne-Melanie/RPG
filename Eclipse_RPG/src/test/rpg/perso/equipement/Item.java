package test.rpg.perso.equipement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.effet.Effet;

public class Item 
{
	public static final List<Item> listItem = new ArrayList<Item>();
	private static int ID = 0;
	
	public static Arme epeeBase;
	public static Arme epeeMoyenne;
	public static Arme epeeAvancee;
	
	public static Arme batonMagiqueBase;
	public static Arme batonMagiqueMoyen;
	public static Arme batonMagiqueExpert;
	
	public static Arme dagueBase;
	public static Arme dagueMoyenne;
	public static Arme dagueAvancee;
	
	
	private static Random rand = new Random();
	
	private String nom;
    private float poids;
    private Effet effet;
    private int id;
    
    private String type;

    public Item(String t, String name, Effet caract, float poids) {
    	type = t;
    	nom = name;
    	this.poids = poids;
    	id = ID++;
    	
    	listItem.add(this);
    }
    
    public Item(String name, Effet caract, float poids) {
    	this("Item", name, caract, poids);
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
		System.out.println(listItem.size());
		int r = rand.nextInt(listItem.size());
		return listItem.get(r);
	}
	
	
	static
	{
		epeeBase = new Arme("Adria", new Effet().setForce(2), 2);
		epeeMoyenne = new Arme("Asmodan", new Effet().setForce(5), 6);
		epeeAvancee = new Arme("Diablo", new Effet().setForce(10), 18);

		batonMagiqueBase = new Arme ("Cydaé", new Effet().setMagie(2),2);
		batonMagiqueMoyen = new Arme ("Auriel", new Effet().setMagie(5),3);
		batonMagiqueExpert = new Arme ("Tyraël", new Effet().setMagie(10),5);
		
		dagueBase = new Arme("Piquant d'Ambre", new Effet(2,3,0,0,0), 2);
		dagueMoyenne = new Arme("Dague des sept étoiles", new Effet(4,6,0,0,0), 2);
		dagueAvancee = new Arme("L'appel du Dragon", new Effet(8,12,0,0,0), 2);
	}


	public static Item[] getItems()
	{
		Item[] array = new Item[listItem.size()];
		return listItem.toArray(array);
	}

	public static Item getItem(int loot)
	{
		Iterator<Item> i = listItem.iterator();
		Item item = null;;
		int id = -1;
		while(i.hasNext() && id != loot)
		{
			item = i.next();
			id = item.getID();
		}
		
		if(id == loot)
			return item;
		else
			return null;
	}

	public int getID()
	{
		return id;
	}
}
