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
	private final String descGuerrier = "Arwed fut l'un des derniers enfants à naître de deux parents humains, il connu les dernière heures de gloire de Sollisha durant son enfance avant que sa vie ne toune au cauchemard. Du haut de ses 6 ans, il a vu son père se faire happer par l'Ombre peut après que sa mère eut disparu dans d'étranges circonstances. Depuis ce jour, Arwed érpouve une profonde haine contre les Rodeurs, les Eplorées et les Liberis. Il a ensuite été recueilli par les defenseurs de la cité dont faisait parti son père. Du fait de son importante stature et de son endurance, il préféra rapidement la hache à la faux que les défenseur arbordent fièrement. Arward fut élévé dans un esprit guerrier et ayant pour seul but de restaurer la gloire d'antant de sa cité. Ces dernires mois, après la mutation inattendu des Rodeurs, une nouvelle rumeur apparut suggérant que Sol n'était pas morte mais seulement captive quelque part dans la cité. Il n'en fallut pas plus à Arwed pour décider de partie à sa recherche.";
	private final String descMage = "Sunilda a présenté de grand pouvoirs magique dès sa plus tendre enfance. Ses parents, non magiques, effrayés de voir leur nouveau né faire voler ses couvertures et son biberon la confière au vieux mage de la forêt du Feu Soudain et ne revinrent jamais prendre de ses nouvelles ou même la voir. Le vieux magicien ressenti avant même de l'avoir vu le potentiel magique de la petite fille et accepta de l'élever et de la former comme sa propre fille. Sunilda grandit baignée dans les formules magiques, les légendes racontant la grandeur d'antant de Sollisha et la splendeur oubliée de la forêt du Feu Soudain. Son père adoptif mourru l'année de ses 16 ans, elle décida alors de reprendre ses travaux sur la mystérieuse disparition de Sol et la chute de la cité. Elle se mit à rêver secrètement de rétablir la situation dans la cité et dans la forêt. Alors, lorsqu'elle entendit parler des rumeurs selon lesquelles Sol était retenue captive quelque part dans la cité elle décida de partir à sa recherche, quittant sa fôret tranquille pour le tumulte de la cité. ";
	private final String descAssassin = "Elrond, enfant des bois, est né avec d'étranges oreilles pointues. Petit, son caractère coquin et sournois l'amena à développer discrétion et dextérité pour commettre d'innombrable bêtises et tours de passe-passe. C'est donc sans surprise qu'il se fit rapidement remarqué par la grande guilde des Voleurs de Fenwick. Différents membres de la guilde lui conseillèrent fortement des les rejoindre dès ses 16 ans pour approfondir ses dons et les utiliser à bon escient. Aujourd'hui, Voleur confirmé, il ne manque plus qu'à Elrond de découvrir un des nombreux trésors secrets se trouvant sur le continant pour prétendre au titre de Maître des Voleurs. Et, bien que certains Voleurs passent leur vie à les chercher sans succès, Elrond est persuadé d'y arriver. Lorsque l'histoire de Sol et de son supposé trésor caché parvinrent à ses gracieuses orailles pointues, il su qu'il n'aura plus pareil occasion dans vie. Il décida alors de faire son sac et de partir pour la Cité des Gémissements. ";
	
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
		writeLine("Trois choix s'offre à vous :");
		
	}

}
