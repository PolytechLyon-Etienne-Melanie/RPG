package test.rpg.engine.console.printer;

import java.awt.Color;

public enum PrintColor
{
	ERASE("/cl/0"), 
	BLACK("/cl/1"), 
	RED("/cl/2"), 
	GREEN("/cl/3"), 
	YELLOW("/cl/4"), 
	BLUE("/cl/5"), 
	PURPLE("/cl/6"), 
	CYAN("/cl/7"), 
	WHITE("/cl/8"),
	LAST("/cl/9");

	private String ansiColor;

	PrintColor(String ansiColor)
	{
		this.ansiColor = ansiColor;
	}

	public String getAnsiColor()
	{
		return ansiColor;
	}

	public static Color getColor(char c)
	{
		switch(c)
		{
			case '1':
				return Color.black;
			case '2':
				return Color.red;
			case '3':
				return Color.green;
			case '4':
				return Color.yellow;
			case '5':
				return Color.blue;
			case '6':
				return Color.pink;
			case '7':
				return Color.cyan;
			case '8':
				return Color.white;
			default :
				return Color.black;
		}
	}
	
	public static String getHash()
	{
		return "/cl/";
	}
}
