package test.rpg.engine.console.event;

import test.rpg.engine.Game;
import test.rpg.engine.story.event.EventObserver;

public class CommandBackStory extends Command
{
	public CommandBackStory(Game game)
	{
		super("Retour.", "");
		this.addObserver(new EventObserver(){
			@Override
			public void actionPerformed(String p)
			{
				game.returnToStory();
			}
		});
	}

}
