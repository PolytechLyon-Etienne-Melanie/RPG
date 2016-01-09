package test.rpg.menu;

import test.rpg.engine.Game;
import test.rpg.engine.console.event.Command;
import test.rpg.engine.console.event.Dialogue;
import test.rpg.engine.interfaces.Menu;
import test.rpg.engine.story.event.EventObserver;
import test.rpg.perso.classe.Assassin;
import test.rpg.perso.classe.Guerrier;
import test.rpg.perso.classe.Mage;

public class MenuChoixClasse extends Menu 
{

	private Command guerrier;
	private Command mage;
	private Command assassin;
	private final String descGuerrier = "Arwed fut l'un des derniers enfants à naître de deux parents humains, il connu les dernière heures de gloire de Sollisha durant son enfance avant que sa vie ne toune au cauchemard. Du haut de ses 6 ans, il a vu son père se faire happer par l'Ombre peut après que sa mère eut disparu dans d'étranges circonstances. Depuis ce jour, Arwed érpouve une profonde haine contre les Rodeurs, les Eplorées et les Liberis. Il a ensuite été recueilli par les defenseurs de la cité dont faisait parti son père. Du fait de son importante stature et de son endurance, il préféra rapidement la hache à la faux que les défenseur arbordent fièrement. Arward fut élévé dans un esprit guerrier et ayant pour seul but de restaurer la gloire d'antant de sa cité. Ces dernires mois, après la mutation inattendu des Rodeurs, une nouvelle rumeur apparut suggérant que Sol n'était pas morte mais seulement captive quelque part dans la cité. Il n'en fallut pas plus à Arwed pour décider de partie à sa recherche.";
	private final String descMage = "Sunilda a présenté de grand pouvoirs magique dès sa plus tendre enfance. Ses parents, non magiques, effrayés de voir leur nouveau né faire voler ses couvertures et son biberon la confière au vieux mage de la forêt du Feu Soudain et ne revinrent jamais prendre de ses nouvelles ou même la voir. Le vieux magicien ressenti avant même de l'avoir vu le potentiel magique de la petite fille et accepta de l'élever et de la former comme sa propre fille. Sunilda grandit baignée dans les formules magiques, les légendes racontant la grandeur d'antant de Sollisha et la splendeur oubliée de la forêt du Feu Soudain. Son père adoptif mourru l'année de ses 16 ans, elle décida alors de reprendre ses travaux sur la mystérieuse disparition de Sol et la chute de la cité. Elle se mit à rêver secrètement de rétablir la situation dans la cité et dans la forêt. Alors, lorsqu'elle entendit parler des rumeurs selon lesquelles Sol était retenue captive quelque part dans la cité elle décida de partir à sa recherche, quittant sa fôret tranquille pour le tumulte de la cité. ";
	private final String descAssassin = "desc assassin";
	
	public MenuChoixClasse(Game game)
	{
		super(game);
	}

	@Override
	protected void setDials() {
		this.addDial(new Dialogue("Il est temps de choisir votre classe !"));
		this.addDial(new Dialogue("Trois choix s'offre à vous :"));
		
	}

	@Override
	protected void setCommands() {
		guerrier = new Command("Arwed le guerrier", "1.");
		guerrier.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuDescriptionClasse(game, new Guerrier(), "Arwed", descGuerrier));
			}
		});
		
		mage = new Command("Sunilda la magicienne", "2.");
		mage.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuDescriptionClasse(game, new Mage(), "Sunilda", descMage));
			}
		});
		
		assassin = new Command("Voleur", "3.");
		assassin.addObserver(new EventObserver(){
			@Override
			public void actionPerformed()
			{
				game.setCurrentMenu(new MenuDescriptionClasse(game, new Assassin(), "Sunilda", descAssassin));
			}
		});
		this.addCommand(guerrier);
		this.addCommand(mage);
		this.addCommand(assassin);
	}

}
