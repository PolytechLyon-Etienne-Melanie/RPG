package test.rpg.perso;

public class Action {

    private Personnage cible;

    private Personnage src;

	public Personnage getCible() {
		return cible;
	}

	public void setCible(Personnage cible) {
		this.cible = cible;
	}

	public Personnage getSrc() {
		return src;
	}

	public void setSrc(Personnage src) {
		this.src = src;
	}
}
