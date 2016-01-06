package test.rpg.perso.classe;

public class Caracteristique {

    private int force, dexterite, sante, defense;
    
	public int getForce() {
		return force;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public int getDexterite() {
		return dexterite;
	}

	public void setDexterite(int dexterite) {
		this.dexterite = dexterite;
	}

	public int getSante() {
		return sante;
	}

	public void setSante(int sante) {
		this.sante = sante;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public Caracteristique(int f, int dex, int s, int def)
	{
		force = f;
		dexterite = dex;
		sante = s;
		defense = def;
	}
}
