package test.rpg.perso;

import test.rpg.perso.classe.Caracteristique;
import test.rpg.perso.classe.Classe;
import test.rpg.perso.equipement.Inventaire;

public class Personnage extends Entity 
{
	private static final int xpGrow = 10; 
	private int poidsMax;
    private Inventaire inventaire;
    
    private int pointsToAssing;
    private int xp;

	private int xpToNextLevel;
    
    public Personnage(String nom, int n, Classe classe) {
		super(nom, n, classe);
		// TODO Auto-generated constructor stub
		this.pointsToAssing = 0;
		xp = 0;
		xpToNextLevel = this.setXpToNextLevel();
		updatePoidsMax();
		inventaire = new Inventaire(this);
		inventaire.setArmreeq(classe.getDefaultArme());
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
    	xpToNextLevel = this.setXpToNextLevel();
    	calculSanteMax();
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
	
	public int getXp()
	{
		return xp;
	}

	public int getXpToNextLevel()
	{
		return xpToNextLevel;
	}
	
	public void restaureHp()
	{
		this.sante = santeMax;
	}
}
