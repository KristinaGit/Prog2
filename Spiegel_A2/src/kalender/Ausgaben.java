package kalender;
import java.io.*;

public class Ausgaben  {		

	/** printToSys
	 * -------------------------------------------------------------
	 * @param out (String) String der auf der Konsole auszugeben ist
	 * -------------------------------------------------------------
	 * Ausgabe des übergebenen Strings auf der Konsole
	 */
	public void printToSys( String out){
    	 System.out.println(out);   
		
	 }
	
	public void printToFile ( String filename, String content){
		// TODO: write to file
		try {
			FileWriter fw = new FileWriter( filename);
            fw.write( content);
            fw.close();

        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
  
	}
}
	

