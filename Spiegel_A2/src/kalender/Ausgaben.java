package kalender;
import java.io.*;

public class Ausgaben  {	
	
	Kalender kalender = new Kalender();
	Ausgaben ausgabe; 
	Eingaben eingabe;
	int calFormatSelection = 0; 
	int modus = 1;

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
		try {
			FileWriter file = new FileWriter( filename);
            file.write( content);
            file.close();
        } 
		catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
  
	}
	
 	public void auswahlMenue() {
		
		eingabe = new Eingaben();
		ausgabe = new Ausgaben();
		
		
		/*
		 *  default: Europaeisches Format
		 */
		calFormatSelection = 0; 
		boolean programmEnde = false;
		
		while( false == programmEnde) {
		
			ausgabe.printToSys("Willkommen im Kalender-Menue.");
			ausgabe.printToSys("Auswahl:" + "     " + "[1] " + "Kalender fuer das ganze Jahr");
			ausgabe.printToSys("Auswahl:" + "     " + "[2] " + "Ausgabe Monatsblatt");
			ausgabe.printToSys("Auswahl:" + "     " + "[3] " + "Kalender mit Feiertagen fuer das ganze Jahr");
			ausgabe.printToSys("Auswahl:" + "     " + "[4] " + "Ausgabe Monatsblatt mit Feiertagen");
			ausgabe.printToSys("Auswahl:" + "     " + "[5] " + "Kalenderblatt mit Mo beginnend (j/n)");
			ausgabe.printToSys("Auswahl:" + "     " + "[6] " + "Jahresplaner mit Events (j/n)");
			ausgabe.printToSys("Auswahl:" + "     " + "[0] " + "Programm beenden");

			int inputMain = eingabe.readInputInt( "Bitte waehlen Sie ihre gewuenschte Option:", 
												  "Ungueltige Eingabe. Bitte geben Sie eine gueltige Zahl ein.",
												  0, 6);

			if(inputMain == 1){
				
				modus = -1;
				int year = kalender.liesJahr();
				
				StringBuffer wholeyear = new StringBuffer();
				for( int monat = 1; monat <= 12; monat++ ){
					String headlineFormat = kalender.getKopfzeileMonatsblatt( year, monat);
					wholeyear.append( headlineFormat);
					String monthStr = kalender.getMonatsblatt( year, monat);
					wholeyear.append(monthStr);
				}
				printToSys(wholeyear.toString());
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append(".txt");
				kalender.saveToFile( defaultFilename.toString(), wholeyear.toString());
				
			}
			else if(inputMain == 2){
				
				modus = -1;
				int year = kalender.liesJahr();
				int month = kalender.liesMonat();
				
				StringBuffer output = new StringBuffer();
				String headlineFormat = kalender.getKopfzeileMonatsblatt( year, month);
				output.append( headlineFormat);
				String monthStr = kalender.getMonatsblatt( year, month);
				output.append(monthStr);
		
				ausgabe.printToSys( output.toString());				
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append("_");
				defaultFilename.append(month);
				defaultFilename.append(".txt");
				kalender.saveToFile( defaultFilename.toString(), output.toString());
			}
			else if(inputMain == 3){
				
				modus = 1; // Kalender mit Feiertagen
				int year = kalender.liesJahr();

				kalender.generateHolidaysForCurrentYear( year);

				StringBuffer wholeyear = new StringBuffer();
				for( int monat = 1; monat <= 12; monat++ ){
					String headlineFormat = kalender.getKopfzeileMonatsblatt( year, monat);
					wholeyear.append( headlineFormat);
					String monthStr = kalender.getMonatsblatt( year, monat);
					wholeyear.append(monthStr);
				}
				ausgabe.printToSys(wholeyear.toString());
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append("_f");			
				defaultFilename.append(".txt");
				kalender.saveToFile( defaultFilename.toString(), wholeyear.toString());			
				
			}
			else if(inputMain == 4) {
				
				modus = 1;
				int year = kalender.liesJahr();
				int month = kalender.liesMonat();

				kalender.generateHolidaysForCurrentYear( year);

				StringBuffer output = new StringBuffer();
				String headlineFormat = kalender.getKopfzeileMonatsblatt( year, month);
				output.append( headlineFormat);
				String monthStr = kalender.getMonatsblatt( year, month);
				output.append(monthStr);
		
				ausgabe.printToSys( output.toString());
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append("_");
				defaultFilename.append(month);
				defaultFilename.append("_");
				defaultFilename.append("f");
				defaultFilename.append(".txt");
				kalender.saveToFile( defaultFilename.toString(), output.toString());
							
			}
			else if(inputMain == 5){
				boolean abfrage = eingabe.readInputJN( "Geben Sie [j] fuer Mo und [n] fuer So ein: " , "Ungueltige Eingabe. Bitte versuchen Sie es erneut: ");
				if( abfrage == true) {
					calFormatSelection = 0;
				}else {
					calFormatSelection = 1;
				}
			}
			else if(inputMain == 6){
				
				Jahresplaner plan = new Jahresplaner(2017); // Jahr
			    
				boolean abfrage = eingabe.readInputJN( "Moechten Sie Events mit ausgegeben haben? Geben Sie [j] fuer Ja und [n] fuer Nein ein: " , "Ungueltige Eingabe. Bitte versuchen Sie es erneut: ");
				if( abfrage == true) {
					modus = 1;
					plan.mitFeiertagen = true;
				}else {
					modus = -1;
					plan.mitFeiertagen = false;
				}
			
		        String tmp = plan.gibJahresplan( 1, 6); // von Jan bis Juni
				ausgabe.printToSys( tmp.toString());;	
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(2017);
				defaultFilename.append("_");
				defaultFilename.append("1");
				defaultFilename.append("_");
				defaultFilename.append("6");
				defaultFilename.append("_");
				defaultFilename.append("jahresplan");	
				
				if( 1 == modus) { // diese Schreibweise vorziehen, anstatt modus == 1, um bei einem Tipfehler Bug zu vermeiden, da es sonst zur Variablen Zuweisung kommen koennte			
					defaultFilename.append("_");
					defaultFilename.append("f");
				}
				defaultFilename.append(".txt");
				kalender.saveToFile( defaultFilename.toString(), tmp.toString());
			}
			else if(inputMain == 0){
				ausgabe.printToSys("Programm wird beendet.");
				programmEnde = true;
			}

		}
	}
}
	

