package test.rpg.perso.competence;

import test.rpg.perso.Entity;
import test.rpg.perso.effet.Effet;

public class AttaqueBuff extends Attaque
{
	private Buff buff;
	private boolean himself;
	
	public AttaqueBuff(String name, float f, float d, float m, String buff_name, Effet e)
	{
		super(name, f, d, m);
		buff = new Buff(buff_name, e, false);
		setHimself(true);
	}
	
	public String effet(Entity cible, Entity src) 
	{
		String s = super.effet(cible, src);
		if(himself)
			s += " " + buff.effet(src, src);
		else
			s += " " + buff.effet(cible, src);
		return s;
	}

	public AttaqueBuff setHimself(boolean himself)
	{
		this.himself = himself;
		buff.setTargeted(!himself);
		return this;
	}
	
	@Override
	public boolean isTargeted()
	{
		return true;
	}
	
	public String toString()
	{
		return super.toString() + " | " + buff.toString();
	}
}
