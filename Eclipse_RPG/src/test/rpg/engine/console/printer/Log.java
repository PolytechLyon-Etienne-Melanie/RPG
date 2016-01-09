package test.rpg.engine.console.printer;

import java.io.PrintStream;

public class Log 
{
	public static PrintStream outErr = System.err;
    public static boolean MULTILINE = false;
    public static boolean CONTEXTUALIZED = false;
    public static Level level = Level.DEBUG;
    
    public static void v(Object toLog) {
        logErr(Level.VERBOSE, toLog);
    }

    public static void d(Object toLog) {
        logErr(Level.DEBUG, toLog);
    }

    public static void i(Object toLog) {
        logErr(Level.INFO, toLog);
    }

    public static void w(Object toLog) {
        logErr(Level.WARNING, toLog);
    }

    public static void e(Object toLog) {
        logErr(Level.ERROR, toLog);
    }

    public static void f(Object toLog) {
        logErr(Level.FATAL, toLog);
        System.exit(-1);
    }
    
    public static void setLevel(Level l) {
    	i("<<- Log set to " + l + " ->>");
        level = l;
    }

    private static void logErr(Level level, Object toLog) {
        if(level.compareTo(Log.level) < 0)
            return;
        if(toLog instanceof Exception)
        {
            logErr(level, (Exception) toLog);
            return;
        }
        String message = "" + toLog;
        
        outErr.println(level + ""
                + (CONTEXTUALIZED ? caller() : "")
                + (message.length() > 20 && MULTILINE ? "\n\t" : " ")
                + message.replaceAll("\n", "\n\t")
        );
    }

    private static void logErr(Level level, Exception exception) {
        outErr.println(level + ""
                + (CONTEXTUALIZED ? caller() : "") + " "
                + (CONTEXTUALIZED ? exception : exception.getLocalizedMessage()));
    }

    private static StackTraceElement caller() {
        for (StackTraceElement element : new Exception().getStackTrace()) {
            if (!element.getClassName().equals(Log.class.getName())) {
                return element;
            }
        }
        throw new RuntimeException("Method should be called from outside of Log class.");
    }

    public static enum Level {
        VERBOSE,
        DEBUG,
        INFO,
        WARNING,
        ERROR,
        FATAL;
        
    	
    	private PrintColor color;
    	Level(PrintColor c)
    	{
    		color = c;
    	}
    	
    	Level()
    	{
    		this(PrintColor.RED);
    	}
    	
    	public PrintColor color()
    	{
    		return color;
    	}
    	
        private String offset;
        static {
            int max = 0;
            for(Level level : values()) {
                int lenght = level.name().length() ;
                max = ((max < lenght) ? lenght : max);
            }
            for(Level level : values())
            {
                level.offset = String.format("%" + (max - level.name().length()+1) +"s", "");
            }
        }
        

        @Override
        public String toString() {
        	
            return "[" + name() + ']' + offset;
        }
    }

	public static void setStreamer(PrintStream stream)
	{
		if(stream != null)
			outErr = stream;
	}
	
	public final static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}

	public static boolean debug()
	{
		return level == Level.DEBUG;
	}

	public static void switchDebug()
	{
		if(debug())
			level = Level.ERROR;
		else
			level = Level.DEBUG;
	}
}
