package test.rpg.perso.equipement;

import test.rpg.perso.effet.Effet;

public class Armure extends Item 
{
	private float resistance;
	
    public Armure(String name, Effet caract, float poids)
	{
		super(name, caract, poids);
	}
    
	public float getResistance()
	{
		return resistance;
	}

	public void setResistance(float resistance)
	{
		this.resistance = resistance;
	}
}
