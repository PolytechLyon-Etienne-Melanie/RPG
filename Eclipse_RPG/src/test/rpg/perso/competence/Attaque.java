package test.rpg.perso.competence;

import test.rpg.perso.Entity;
import test.rpg.perso.classe.Caracteristique;

public class Attaque implements Capacite {

	private float ration_force, ration_dex, ration_magie;
	private String name;
	public Attaque(String name, float f, float d, float m)
	{
		this.ration_force = f;
		this.ration_dex = d;
		this.ration_magie = m;
		this.name = name;
	}
	
	public Attaque(float f, float d, float m)
	{
		this("Attaque", f, d, m);
	}
	
	@Override
	public float probaReussite(Entity src) {
		return 0;
	}

	@Override
	public String effet(Entity cible, Entity src) 
	{
		Caracteristique c = src.getTotalCarac();
		int d_f = c.getForce();
		int d_d = c.getDexterite();
		int d_m = c.getMagie();
		float tot_damages = ration_force*d_f + ration_dex*d_d + ration_magie*d_m;
		int dmg = (int)tot_damages - cible.getTotalCarac().getDefense();
		cible.damages(dmg);
		return "Degats avant reduction : " + tot_damages + " | Degats infligés à " + cible.getNom() + " : " + dmg;
	}
	
	public String getName()
	{
		return name;
	}
}
