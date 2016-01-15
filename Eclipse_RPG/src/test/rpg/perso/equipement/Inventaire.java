package test.rpg.perso.equipement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import test.rpg.perso.Personnage;

public class Inventaire {
	
	private Arme armeeq;
	private Armure armureeq;
	private List<Item> items;
	private int poidsMax;
	private Personnage perso;
	
	public Inventaire(Personnage perso){
		items = new ArrayList<Item>();
		this.perso = perso;
		setPoidsMax(perso.getPoidsMax());
	}

	public Arme getArmreeq() {
		return armeeq;
	}

	public void setArmreeq(Arme armreeq) {
		this.armeeq = armreeq;
	}

	public Armure getArmureeq() {
		return armureeq;
	}

	public void setArmureeq(Armure armureeq) {
		this.armureeq = armureeq;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item it){
		items.add(it);
	}
	
	public void deletItem(Item it){
		items.remove(it);
		
	}

	public int getPoidsMax() {
		return poidsMax;
	}

	public void setPoidsMax(int poidsMax) {
		this.poidsMax = poidsMax;
	}
	
	public boolean verifPoids(int poidsMax)
	{
		int poidsInventaire = 0;
		Iterator<Item> i = items.iterator();
		while (i.hasNext())
		{
			Item item = i.next();
			poidsInventaire += item.getPoids();
		}
		poidsInventaire += armureeq.getPoids();
		poidsInventaire += armeeq.getPoids();
		
		return poidsInventaire < poidsMax; 
		
	}

	public List<Consommable> getConsommables()
	{
		ArrayList<Consommable> conso = new ArrayList<Consommable>();
		Iterator<Item> i = items.iterator();
		while(i.hasNext())
		{
			Item item = i.next();
			if(item instanceof Consommable)
				conso.add((Consommable)item);
		}
		return conso;
	}
}
