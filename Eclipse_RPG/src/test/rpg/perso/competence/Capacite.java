package test.rpg.perso.competence;

import test.rpg.perso.Personnage;

public interface Capacite {

    public Personnage probaReussite(Personnage src);

    public Personnage effet(Personnage cible, Personnage src);
}
