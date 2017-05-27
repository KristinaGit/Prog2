package kalender;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Eingaben { 

	Scanner scan;
	
	Ausgaben ausgabe;
	
	/*
	 * Konstruktor
	 * Allokieren der benötigten Resourcen für Eingabe und Ausgabe (eventuelle Fehlermeldungen)
	 */
	Eingaben() {
		scan = new Scanner( System.in);
		ausgabe = new Ausgaben(); //Instanziierung, um die Fehlermeldung auszugeben
	}
	
	/*
	 * Destruktor
	 * Freigabe von stdin 
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() {
		scan.close();
	}
	
	/** readInputInt
	 * ----------------------------
	 * @param query (String) Nutzeranfrage 
	 * @param errmsg (String) Fehlermeldung bei inkorrekter Eingabe
	 * @parem lbound (Integer) untere Schranke fuer Gueltigkeitsbereich der eingelesenen Zahl
	 * @parem rbound (Integer) obere Schranke fuer Gueltigkeitsbereich der eingelesenen Zahl
	 * ----------------------------
	 * @return eingelesene Zahl
	 * ----------------------------
	 */
	public int readInputInt( String query, String errmsg, int lbound, int rbound) {
		
		// auf ungueltigen Wert initialisieren
		int readval = lbound - 1;
		
		do {
			try {
				ausgabe.printToSys( query);
				readval = scan.nextInt();
		
				if( (readval < lbound) || (readval > rbound)){ 
					ausgabe.printToSys( errmsg);
					scan.next();	
				}
			}
			catch (InputMismatchException e){
				ausgabe.printToSys( errmsg);
				scan.next();
			}
			// für alle weiteren Exceptions, die auftreten koennen und Programm beenden sollen.
			catch (Exception e) {
				ausgabe.printToSys( "Unbehandelbare Exception aufgetreten.");
			}
		} while( (readval < lbound) || (readval > rbound));
	
		return readval;
	
	}

	
	/** readInputJN
	* Die Hilfsfunktion implementiert eine  Ja/Nein-Anfrage an den Nutzer
	* und validiert die Eingabe
	* ----------------------------
	* @param query (String) Nutzeranfrage fuer Nutzer
	* @param errmsg (String) Fehlermeldung bei inkorrekter Eingabe
	* 
	* ----------------------------
	* @return boolean returnval ((J || j == true) && ( N || n == false))
	* ----------------------------
	*/
	public boolean readInputJN( String query, String errmsg) {

		// auf ungueltigen Wert initialisieren
		boolean returnval = false;
		boolean validinput = false;
		
		do{
			ausgabe.printToSys( query);
			String input = scan.next();

			if(input.equals("j") || input.equals("J")){
				returnval = true;
				validinput = true;
			}
			else if(input.equals("n") || input.equals("N")) {
				returnval = false;
				validinput = true;
			}
			else{
				ausgabe.printToSys(errmsg);
			}
		}
		while( ! validinput);
			
		return returnval;	
	}

}
