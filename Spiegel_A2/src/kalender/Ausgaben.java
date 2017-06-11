package kalender;
import java.io.*;

public class Ausgaben  {	
	
	Kalender kalender = new Kalender();
	Ausgaben ausgabe; 
	Eingaben eingabe;
	int calFormatSelection = 0; 
	int modus = 1;

	/**
	 * printToSys()
	 * -------------------------------------------------------------
	 * @param out (String) String der auf der Konsole auszugeben ist
	 * -------------------------------------------------------------
	 * Ausgabe des übergebenen Strings auf der Konsole
	 */
	public void printToSys( String out){
    	 System.out.println(out);   
		
	 }
	
	/**
	 * printToFile()
	 * -------------------------------------------------------------
	 * @param filename (String) String, der den Dateinamen beeinhaltet
	 * @param content  (String) String, der den Inhalt der Datei schreibt
	 * -------------------------------------------------------------
	 * Methode, zum schreiben der Datei
	 */
	public void printToFile ( String filename, String content){
		try {
			FileWriter file = new FileWriter( filename);
            file.write( content);
            file.close();
        } 
		catch (IOException io) {
            //Exeptionhandling
            io.printStackTrace();
        }
  
	}
	
	/**
	 * printMainTextMenu()
	 * -------------------------------------------------------------
	 * Hilfsmethode, um das Menu mit den Strings über die Ausgabe-Klasse auf der Konsole auszugeben
	 */
    public void printMainTextMenu() {
    	
    	printToSys("Willkommen im Kalender-Menue.");
		printToSys("Auswahl:" + "     " + "[1] " + "Kalender fuer das ganze Jahr");
		printToSys("Auswahl:" + "     " + "[2] " + "Ausgabe Monatsblatt");
		printToSys("Auswahl:" + "     " + "[3] " + "Kalender mit Feiertagen fuer das ganze Jahr");
		printToSys("Auswahl:" + "     " + "[4] " + "Ausgabe Monatsblatt mit Feiertagen");
		printToSys("Auswahl:" + "     " + "[5] " + "Kalenderblatt mit Mo beginnend (j/n)");
		printToSys("Auswahl:" + "     " + "[6] " + "Jahresplaner mit Events (j/n)");
		printToSys("Auswahl:" + "     " + "[0] " + "Programm beenden");
    }
}
	

