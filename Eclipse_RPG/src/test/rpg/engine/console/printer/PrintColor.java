package test.rpg.engine.console.printer;

public enum PrintColor
{
	BLACK("\u001B[30m"), 
	RED("\u001B[31m"), 
	GREEN("\033[32m"), 
	YELLOW("\u001B[33m"), 
	BLUE("\u001B[34m"), 
	PURPLE("\u001B[35m"), 
	CYAN("\u001B[36m"), 
	WHITE("\u001B[37m");

	private String ansiColor;

	PrintColor(String ansiColor)
	{
		this.ansiColor = ansiColor;
	}

	public String getAnsiColor()
	{
		return ansiColor;
	}
}
