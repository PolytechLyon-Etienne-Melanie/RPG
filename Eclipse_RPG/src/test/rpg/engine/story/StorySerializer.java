package test.rpg.engine.story;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import test.rpg.engine.console.printer.Log;

public class StorySerializer
{
	public static void serialize(Story s) throws IOException
	{
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileFilter() {

			   public String getDescription() {
			       return "RPG Story (*.str)";
			   }

			   public boolean accept(File f) {
			       if (f.isDirectory()) {
			           return true;
			       } else {
			           String filename = f.getName().toLowerCase();
			           return filename.endsWith(".str") || filename.endsWith(".str") ;
			       }
			   }
			};
		chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(new File("/home/me/Documents"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        File fichier =  chooser.getSelectedFile();

			 // ouverture d'un flux sur un fichier
			ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(fichier)) ;
			Log.d(s.eventCount);
			 // sérialization de l'objet
			oos.writeObject(s) ;
			
			oos.close();
	    }
	}
	
	public static Story unserialize() throws IOException, ClassNotFoundException
	{
		Log.d("start unserialize story");
		JFileChooser chooser = new JFileChooser(){
			  @Override
			  protected JDialog createDialog( Component parent ) throws HeadlessException {
			    JDialog jDialog = super.createDialog( parent );
			    jDialog.setAlwaysOnTop( true );
			    return jDialog;
			  };};
		FileFilter filter = new FileFilter() {

			   public String getDescription() {
			       return "RPG Story (*.str)";
			   }

			   public boolean accept(File f) {
			       if (f.isDirectory()) {
			           return true;
			       } else {
			           String filename = f.getName().toLowerCase();
			           return filename.endsWith(".str") || filename.endsWith(".str") ;
			       }
			   }
			};
		chooser.setFileFilter(filter);
	    chooser.setCurrentDirectory(new File("/home/me/Documents"));
	    int retrival = chooser.showOpenDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        File fichier =  chooser.getSelectedFile();

	       ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fichier)) ;
			
	        // désérialization de l'objet
	       Story s = null;
	       Object f = ois.readObject();
	       if(f instanceof Story)
	    	   s = (Story) f;
	       ois.close();
	       Log.d("end unserialize story");
	       Log.d(s.eventCount);
	       return s;
	    }
	    Log.d("end unserialize story (no story)");
		return null;
		
	}
}
