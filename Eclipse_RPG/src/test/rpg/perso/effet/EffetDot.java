package test.rpg.perso.effet;

import test.rpg.perso.Entity;

public class EffetDot extends Effet
{
	public EffetDot(int per, int v)
	{
		super(per, v);
	}
	
	public String update(Entity src)
	{
		super.update(src);
		src.damages(getValeur());
		return src.getNom() + " subit un dot de " + getValeur()+ " dégats.";
	}
	
	public String toString()
	{
		return "Dot : " + valeur + ", Duree : " + permanent;
	}
}
