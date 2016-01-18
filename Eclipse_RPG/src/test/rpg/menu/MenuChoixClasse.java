package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.StoryEvent;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.classe.Assassin;
import test.rpg.perso.classe.Guerrier;
import test.rpg.perso.classe.Mage;

public class MenuChoixClasse extends Menu 
{

	private Command guerrier;
	private Command mage;
	private Command assassin;
	private final String descGuerrier = "Arwed fut l'un des derniers enfants � na�tre de deux parents humains, il connu les derni�re heures de gloire de Sollisha durant son enfance avant que sa vie ne toune au cauchemard. Du haut de ses 6 ans, il a vu son p�re se faire happer par l'Ombre peut apr�s que sa m�re eut disparu dans d'�tranges circonstances. Depuis ce jour, Arwed �rpouve une profonde haine contre les Rodeurs, les Eplor�es et les Liberis. Il a ensuite �t� recueilli par les defenseurs de la cit� dont faisait parti son p�re. Du fait de son importante stature et de son endurance, il pr�f�ra rapidement la hache � la faux que les d�fenseur arbordent fi�rement. Arward fut �l�v� dans un esprit guerrier et ayant pour seul but de restaurer la gloire d'antant de sa cit�. Ces dernires mois, apr�s la mutation inattendu des Rodeurs, une nouvelle rumeur apparut sugg�rant que Sol n'�tait pas morte mais seulement captive quelque part dans la cit�. Il n'en fallut pas plus � Arwed pour d�cider de partie � sa recherche.";
	private final String descMage = "Sunilda a pr�sent� de grand pouvoirs magique d�s sa plus tendre enfance. Ses parents, non magiques, effray�s de voir leur nouveau n� faire voler ses couvertures et son biberon la confi�re au vieux mage de la for�t du Feu Soudain et ne revinrent jamais prendre de ses nouvelles ou m�me la voir. Le vieux magicien ressenti avant m�me de l'avoir vu le potentiel magique de la petite fille et accepta de l'�lever et de la former comme sa propre fille. Sunilda grandit baign�e dans les formules magiques, les l�gendes racontant la grandeur d'antant de Sollisha et la splendeur oubli�e de la for�t du Feu Soudain. Son p�re adoptif mourru l'ann�e de ses 16 ans, elle d�cida alors de reprendre ses travaux sur la myst�rieuse disparition de Sol et la chute de la cit�. Elle se mit � r�ver secr�tement de r�tablir la situation dans la cit� et dans la for�t. Alors, lorsqu'elle entendit parler des rumeurs selon lesquelles Sol �tait retenue captive quelque part dans la cit� elle d�cida de partir � sa recherche, quittant sa f�ret tranquille pour le tumulte de la cit�. ";
	private final String descAssassin = "Elrond, enfant des bois, est n� avec d'�tranges oreilles pointues. Petit, son caract�re coquin et sournois l'amena � d�velopper discr�tion et dext�rit� pour commettre d'innombrable b�tises et tours de passe-passe. C'est donc sans surprise qu'il se fit rapidement remarqu� par la grande guilde des Voleurs de Fenwick. Diff�rents membres de la guilde lui conseill�rent fortement des les rejoindre d�s ses 16 ans pour approfondir ses dons et les utiliser � bon escient. Aujourd'hui, Voleur confirm�, il ne manque plus qu'� Elrond de d�couvrir un des nombreux tr�sors secrets se trouvant sur le continant pour pr�tendre au titre de Ma�tre des Voleurs. Et, bien que certains Voleurs passent leur vie � les chercher sans succ�s, Elrond est persuad� d'y arriver. Lorsque l'histoire de Sol et de son suppos� tr�sor cach� parvinrent � ses gracieuses orailles pointues, il su qu'il n'aura plus pareil occasion dans vie. Il d�cida alors de faire son sac et de partir pour la Cit� des G�missements. ";
	
	private StoryEvent event;
	
	public MenuChoixClasse(Game game, StoryEvent event)
	{
		super(game);
		this.event = event;
	}

	
	protected void setCommands() {
		guerrier = new Command("Arwed le Guerrier", "1");
		guerrier.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuDescriptionClasse(game, new Guerrier(), "Arwed", descGuerrier, event));
			}
		});
		
		mage = new Command("Sunilda la Magicienne", "2");
		mage.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuDescriptionClasse(game, new Mage(), "Sunilda", descMage, event));
			}
		});
		
		assassin = new Command("Elrond le Voleur", "3");
		assassin.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.setCurrentMenu(new MenuDescriptionClasse(game, new Assassin(), "Sunilda", descAssassin, event));
			}
		});
		this.addCommand(guerrier);
		this.addCommand(mage);
		this.addCommand(assassin);
	}

	@Override
	protected void initMenu() {
		setCommands();
		
	}

	@Override
	protected void renderMenu() {
		writeLine("Il est temps de choisir votre classe !");
		writeLine("Trois choix s'offre � vous :");
		
	}

}
