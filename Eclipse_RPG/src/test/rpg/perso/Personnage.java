package test.rpg.perso;

import test.rpg.perso.equipement.Arme;
import test.rpg.perso.equipement.Armure;
import test.rpg.perso.equipement.Item;

public class Personnage {

    private String name;

    private int niveau;

    private int poidsMax;

    private int santeMax;

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
}
