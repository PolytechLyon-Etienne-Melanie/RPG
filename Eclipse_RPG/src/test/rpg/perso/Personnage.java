package test.rpg.perso;

import java.util.Iterator;

import test.rpg.engine.Game;
import test.rpg.menu.MenuLevelUpScreen;
import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.classe.Classe;
import test.rpg.perso.effet.Effet;
import test.rpg.perso.equipement.Arme;
import test.rpg.perso.equipement.Armure;
import test.rpg.perso.equipement.Inventaire;
import test.rpg.perso.equipement.Item;

public class Personnage extends Entity 
{
	private static final int xpGrow = 10; 
	private int poidsMax;
    private Inventaire inventaire;
    
    private int pointsToAssing;
    private int xp;
    private int xpToNextLevel;

    private Game game;
    
    public Personnage(Game game, String nom, int n, Classe classe) {
		super(nom, n, classe);
		// TODO Auto-generated constructor stub
		this.pointsToAssing = 0;
		xp = 0;
		this.setXpToNextLevel();
		updatePoidsMax();
		inventaire = new Inventaire(this);
		inventaire.setArmreeq(classe.getDefaultArme());
		this.game = game;
	}
    
	public int getPoidsMax() {
		return poidsMax;
	}

	public void setPoidsMax(int poidsMax) {
		this.poidsMax = poidsMax;
	}
	
	private void updatePoidsMax(){
		poidsMax = 10 + this.getCaracteristique().getForce();
	}

    public void appliquerEffet() {
    }

    public Arme equipeArme(Arme arme) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Armure equipeArmure(Armure armure) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int getPoidsInventaire() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Item retirerAventaire(Item obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Item ajouterAvantaire(Item obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void initCaracteristique() {
    }

    public Armure oterArmure(Armure armure) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Arme enleverArme(Arme arme) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void earnXP(int xp)
    {
    	this.xp += xp;
    	checkXP();
    }
    
    private void checkXP()
    {
    	if(xp > this.xpToNextLevel)
    	{
    		xp -= this.xpToNextLevel;
    		this.setXpToNextLevel();
    		levelUp();
    	}
    }
    
    private int setXpToNextLevel()
    {
    	return niveau * Personnage.xpGrow;
    }
    
    private void levelUp()
    {
    	niveau++;
    	this.pointsToAssing += 3;
    	game.setCurrentMenu(new MenuLevelUpScreen(game, this));
    }

	public int getPointsToAssing()
	{
		return pointsToAssing;
	}
	
	private void usePoint(){
		pointsToAssing --;
	}
	
	public void increaseForce() {
		classe.increaseForce();
		usePoint();
		updatePoidsMax();
	}
	
	public void increaseSante() {
		classe.increaseSante();
		usePoint();
		calculSanteMax();
	}
	
	public void increaseDexterite() {
		classe.increaseDex();
		usePoint();
	}
	
	public void increaseMagie() {
		classe.increaseMagie();
		usePoint();
	}
	
	public void increaseDef() {
		classe.increaseDef();
		usePoint();
	}

	public Inventaire getInventaire()
	{
		return inventaire;
	}
	
	public Caracteristique getTotalCarac()
	{
		Caracteristique c = super.getTotalCarac();
		return c.add(inventaire.getArmreeq().getValeurEffet()).add(inventaire.getArmureeq().getValeurEffet());
	}
}
