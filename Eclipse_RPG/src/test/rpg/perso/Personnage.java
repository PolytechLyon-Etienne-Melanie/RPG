package test.rpg.perso;

import test.rpg.engine.Game;
import test.rpg.menu.MenuLevelUpScreen;
import test.rpg.perso.classe.Classe;
import test.rpg.perso.equipement.Arme;
import test.rpg.perso.equipement.Armure;
import test.rpg.perso.equipement.Item;

public class Personnage extends Entity 
{
	private static final int xpGrow = 10; 
	private int poidsMax;
    private int santeMax;
    
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
		
		this.game = game;
	}
    
	public int getPoidsMax() {
		return poidsMax;
	}

	public void setPoidsMax(int poidsMax) {
		this.poidsMax = poidsMax;
	}

	public int getSanteMax() {
		return santeMax;
	}

	public void setSanteMax(int santeMax) {
		this.santeMax = santeMax;
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

    public void calculSanteMax() {
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
}
