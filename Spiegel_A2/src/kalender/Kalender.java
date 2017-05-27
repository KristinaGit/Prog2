package kalender;
/*
 * @Dateiname: A2_Spiegel
 * @Compiler:Eclipse / Version neon.3
 * @author: Kristina Spiegel (mit Uebernahme des Interfaces und der bereitgestellten Klassen von Frau Prof. Bannert)
 * @Erstellungsdatum: 18.05.2017
 * @Letzte Aenderung: 25.05.2017
 * @Kalender-Aufgabe A2 aus dem Modul Angewandte Programmierung SoSe 2017
 */

import java.util.ArrayList;
import java.util.HashMap;


public class Kalender implements IKalender {
	/*
	 * Deklarierung der Membervariablen, mit denen die Nutzereingabe und die Ausgabe behandelt wird
	 */
	Ausgaben ausgabe; 
	Eingaben eingabe;
	// Deklarierung der Variable modus  fuer die Kalenderblatt-Ausgabe mit und ohne Feiertage
	int modus = 0;
	
	HashMap<Integer, Event> holidays = new HashMap<Integer, Event>();
	HashMap<Integer, Event> holidaysforcurrent;
	
	KalenderFunktionen calFuncs = new KalenderFunktionen();
	
	 int LINE_LENGTH = 32; 
	 
	/*
	 * @param: int calFormatSelection: Auswahl Kalenderformat europaeisch als Klassenvariable (Eingabe wird bei Input im auswahlMenue() evaluiert)
	 *European: 0
 	 *American: 1
	 */
	 int calFormatSelection = 0; 
	 
	 
	 /*
	  * TODO: Methodendefinition
	  */
	 void generateGeneralHolidays() {
		 holidays.put( 1, new Event("Neujahr"));
		 holidays.put( 6, new Event("Heilige 3 Koenige"));
	   	 holidays.put( 31+14, new Event("Valentinstag"));
	   	 holidays.put( 121, new Event("1. Mai"));
	   	 //holidays.put( , new Event("Muttertag"));
	   	 holidays.put( 227, new Event("Maria Himmelfahrt"));
	   	 holidays.put( 276, new Event("Tag der dt. Einheit"));
	     holidays.put(304, new Event("Reforamtionstag"));
	   	 holidays.put(305, new Event("Allerheiligen"));
	     //holidays.put(, new Event("Bu� und Bettag"));
	   	 //holidays.put(, new Event("1. Advent"));
	   	 //holidays.put(, new Event("2. Advent"));
	   	 //holidays.put(, new Event("3. Advent"));
	   	 //holidays.put(, new Event("4. Advent"));
	   	 holidays.put( 358,new Event("Weihnachten"));
	   	 holidays.put(359, new Event("1. Weihnachtstag"));
	   	 holidays.put(360, new Event("2. Weihnachtstag"));
	   	 holidays.put(365, new Event("Silvester"));
	 }
	 /*
	  * TODO: Methodendefinition
	  */
	 void generateHolidaysForCurrentYear( int year) {
		 
		 //shallowcopy von holidays
		 holidaysforcurrent = (HashMap)holidays.clone();
		 
		 int ostersonntag = calFuncs.ostersonntag(year);
		 holidaysforcurrent.put( ostersonntag-48, new Event("Rosenmontag"));
		 holidaysforcurrent.put( ostersonntag-47, new Event("Faschingsdienstag"));
		 holidaysforcurrent.put( ostersonntag-46, new Event("Aschermittwoch"));
		 holidaysforcurrent.put( ostersonntag-3, new Event("Gruendonnerstag"));
		 holidaysforcurrent.put( ostersonntag-2, new Event("Karfreitag"));
		 holidaysforcurrent.put( ostersonntag, new Event("Ostersonntag"));
		 holidaysforcurrent.put( ostersonntag+1, new Event("Ostermontag"));
		 holidaysforcurrent.put( ostersonntag+39, new Event("Christi Himmelfahrt"));
		 holidaysforcurrent.put( ostersonntag+49, new Event("Pfingstsonntag"));
		 holidaysforcurrent.put( ostersonntag+50, new Event("Pfingstmontag"));
		 holidaysforcurrent.put( ostersonntag+60, new Event("Fronleichnam"));
			// TODO: add remaining variable holidays
			
		 // TODO: does this work with schaltjahr
		 // fuer die Adventssonntage
		 int tagesnummer = calFuncs.tagesnummer( 1, 12, year);
		 int weekday = calFuncs.wochentag_im_jahr( year, tagesnummer);  
		 Integer nradventssonntag = 1;
		 int advent4 = 0;
		 
		 for( int i = 1; i<=24; i++) {
			 if(0 == weekday) {
				 holidaysforcurrent.put( tagesnummer, new Event(nradventssonntag.toString() + ". Adventssonntag"));
				 nradventssonntag++;
				 advent4 = tagesnummer;
			 }
			 weekday = (weekday + 1) % 7;
			 tagesnummer++;
		 }
		 int tagesnummerHeiligAbend = tagesnummer - 1;
		 	if( tagesnummerHeiligAbend == advent4)
		 //advent4 = tagesnummer vom 4. Advent, davon 4*7 Tage (4 Sonntage ) - 4 (Differenz der Tage Sonntag zu Mittwoch rückwärts) subtrahiert
		 		holidaysforcurrent.put( (advent4-32), new Event("Buss- und Bettag"));
		
		 
		 // fuer den Muttertag im Mai
		 tagesnummer = calFuncs.tagesnummer( 1, 5, year);
		 weekday = calFuncs.wochentag_im_jahr( year, tagesnummer);  
		 Integer nrSonntag = 0;
		 
		 for( int i = 1; i<=31; i++) {
			 if(0 == weekday) {
				 nrSonntag++;
				 
			 }
			 if( 2 == nrSonntag) {
				 if(tagesnummer == (ostersonntag+49)) { //fuer den Fall Pfingstsonntag faellt auf den 2. So im Mai
					 holidaysforcurrent.put( tagesnummer-7, new Event("Muttertag"));
				 }	 
				 holidaysforcurrent.put( tagesnummer, new Event("Muttertag"));
				 break;
			 }
			 weekday = (weekday + 1) % 7;
			 tagesnummer++;
		 }	
	 }	
	 
	 
	 
	 /*StringBuffer formatMonth
	  * ----------------------------------------------------------------------------------
	  * @param stryear (String)  Jahreszahl als String
	  * @param month (String) Monat als String
	  * ----------------------------------------------------------------------------------
	  * @return: StringBuffer  String(Buffer) mit obere Kopfzeile
	  * ----------------------------------------------------------------------------------
	  * Formatierung der oberen Kopfzeile
	 */
	 StringBuffer formatMonth( String stryear, String strmonth) { 
	
		  StringBuffer strbuf = new StringBuffer(); 

		  // Anzahl der Sterne in der Kopfzeile
		  int star = (LINE_LENGTH - (strmonth.length() + 7)) / 2; //7: 4 Zeichen fuer Jahreszahl und insg. 3 Leerzeichen dazw.
		
		  for( int i = 0; i < star; ++i){
		    strbuf.append( "*");
		  }
		
		  strbuf.append( " " + strmonth + " ");
		  strbuf.append( stryear + " ");
		
		  /* ueberpruefen ob die Laenge, gleichlang gesplittet werden kann
		  * Beachte: diese Methode diese Methode geht davon aus (!), das die Zeilenlaenge gerade ist
		  *7: Jahreslaenge=4 plus 3 Leerzeichen
		  */
		  if( 1 == (LINE_LENGTH - (strmonth.length() + 7)) % 2) { //hier nun deshalb: ueberpruefe, ob Zeilenlaenge ungerade ist und fuege noch ein * hinzu
		    star = star + 1;
		  }
		
		  for( int i = 0; i < star; ++i) {
		    strbuf.append( "*");
		  }
		  
		  return strbuf; 
	}	
	
	 
	 /*
	  * String getMonatsname()
	  * ------------------------------------------------------------------------------------
	  * @param: int monat
	  * ------------------------------------------------------------------------------------
	  * @return: String strmonth
	  * ------------------------------------------------------------------------------------
	  * Hilfsmethode switch fuer die Rueckgabe des Monatsnamen als String
	  */
	 public String getMonatsname( int monat){
		 
		String strmonth = null;
		
		switch (monat){
			
			case 1: {
				strmonth = new String("Januar");
				break; }
			case 2: {
				strmonth = new String("Februar");
				break; }
			case 3: {
				strmonth = new String("Maerz");
				break; }
			case 4: {
				strmonth = new String("April");
				break; }
			case 5: {
				strmonth = new String("Mai");
				break; }
			case 6: {
				strmonth = new String("Juni"); 
				break; }
			case 7: {
				strmonth = new String("Juli");
				break; }
			case 8: {
				strmonth = new String("August");
				break; }
			case 9: {
				strmonth = new String("September");
				break; }
			case 10: {
				strmonth = new String("Oktober");
				break; }
			case 11: {
				strmonth = new String("November");
				break; }
			case 12: {
				strmonth = new String("Dezember");
				break; }
			default:
				System.err.println("Ungueltige Eingabe");	
		}
		
		return strmonth;
	 }
			
			
	public String getMonatsblatt(int jahr, int monat) {
		
		
		StringBuffer monatsBlatt = new StringBuffer();
		
		// Konstruktoraufruf fuer KalenderFunktion
		
		 //2. Funktionsaufruf calFuncs.tagesnummer gibt int fuer Tagesnummer zurueck,
		 //da bei n Fehlermeldung kam und man spart einen separaten Funktionsaufruf
		 
		int tagesnummer = calFuncs.tagesnummer( 1, monat, jahr);
		int weekday = calFuncs.wochentag_im_jahr(jahr, tagesnummer);  
		boolean pruefSchaltjahr = calFuncs.istSchaltjahr(jahr);
		
		String abstand = "     "; // 5 Leerzeichen		
		
		// Selection europaeisches oder amerikanisches Kalenderformat
		 
		if(calFormatSelection == 0){
			if( 0 == weekday) {
				weekday = 6; 
			}
			else {
				weekday--;
			}
		}
		for(int i = 0; i < weekday; i++){
			monatsBlatt.append(abstand);
		}
		weekday++;
		
		int daynumber = 1;
		int daysInMonth = 31;
		
		/*
		 *  fuer Monate mit 30 Tagen (April, Juni, September, November)
		 */
		if(monat == 4 || monat == 6 || monat == 9 || monat == 11){
			daysInMonth = 30;			
		}
		else if(monat == 2){
			daysInMonth = 28;
			if(pruefSchaltjahr){ //geschachteltes if, da es sonst das naechste Statement nicht ueberpruefen wuerde
				assert( false); // , "Schaltjahr: Weihnachten etc. muss angepasst werden"
				daysInMonth = 29;
			}
		}

		ArrayList<String> holidaysinmonth = new ArrayList<String>();
		
		for (daynumber = 1; daynumber <= daysInMonth; daynumber++) {
			
			/*um fuehrende 0 bei Tagen von 1-9 hinzu
			 * zufuegen
			 */
			if (modus == 1){
				
				Event cday = holidaysforcurrent.get(tagesnummer); //holt sich aus HashMap das Element zur Tagesnummer; cday = currentday
				
				boolean pruefFeiertag = true; 
				if( null == cday) {
					pruefFeiertag = false;
				}
				else {
					StringBuffer line = new StringBuffer();
					line.append( daynumber);
					line.append( ". : ");
					line.append( cday.name);
					holidaysinmonth.add( line.toString()); 
				}
				
				if( pruefFeiertag) { //TODO
					
					if (daynumber <= 9) {
						monatsBlatt.append("0");
						monatsBlatt.append(daynumber);
						monatsBlatt.append("*");
					}
					else{	
						monatsBlatt.append(daynumber);
						monatsBlatt.append("*");
					}
					monatsBlatt.append("  ");
					
				// wenn es kein Feiertag ist, soll kein * hinzugefuegt werden!
				}else {
					if (daynumber <= 9) {
						monatsBlatt.append("0");
					}
					monatsBlatt.append(daynumber);
					monatsBlatt.append("   ");
				}
			}
			// modus == 0
			else {
				if (daynumber <= 9) {
					monatsBlatt.append("0");
				}
				monatsBlatt.append(daynumber);
				monatsBlatt.append("   ");
			}

			if (weekday % 7 == 0) {
				monatsBlatt.append("\n");
			}
			/*
			 *weekday muss synchron mit daynumber hochzaehlen, damit die Zeilenumbrueche stimmen
			 */
			weekday++; 
			tagesnummer++;
		}
		
		monatsBlatt.append("\n");

		if(modus == 1){
			monatsBlatt.append("\n" + "Im " + getMonatsname( monat) + " gibt es folgende Feiertage: " + "\n");		
			for( int i = 0; i < holidaysinmonth.size(); i++) {
				monatsBlatt.append( holidaysinmonth.get(i));
				monatsBlatt.append("\n");
			} 
			monatsBlatt.append("\n");
		}
		
		return monatsBlatt.toString();
	}

	
	public String getKopfzeileMonatsblatt(int jahr, int monat) {
	
		StringBuffer stryear = new StringBuffer();
		stryear.append(jahr); 
		
		String strmonth = getMonatsname( monat);
		StringBuffer str = formatMonth( stryear.toString(), strmonth); //str als Stringbuffer-Ergebnis muss mit .toString() wieder in String umgewandelt werden
		
		 if(calFormatSelection == 0){
				
			str.append("\n");
			str.append("MO");
			str.append("   ");
			str.append("DI");
			str.append("   ");
			str.append("MI");
			str.append("   ");
			str.append("DO");
			str.append("   ");
			str.append("FR");
			str.append("   ");
			str.append("SA");
			str.append("   ");
			str.append("SO");
		
		
		} else if(calFormatSelection == 1){
		
			str.append("\n");
			str.append("SO");
			str.append("   ");
			str.append("MO");
			str.append("   ");
			str.append("DI");
			str.append("   ");
			str.append("MI");
			str.append("   ");
			str.append("DO");
			str.append("   ");
			str.append("FR");
			str.append("   ");
			str.append("SA");
			str.append("   ");
		
		}
		str.append("\n");
		
		/*
		 * Methode toString() wird verwendet, um StringBuffer wieder in String formatiert werden, da return-Wert String erfordert
		 */
		return str.toString(); 
		
	}
	 
	
	public void zeigeMonat(int jahr, int monat) {
		
		 String outputMonth = getMonatsblatt(jahr, monat);
		 ausgabe.printToSys( outputMonth); //muss hier stehen, um Ausgabe zu veranlassen
	}

	
	public void zeigeJahr(int jahr) {
		
		for(int i = 1; i <=12; i++){
			zeigeMonat(jahr, i);
		}
	}

	
	public int liesMonat() {
	
		int month = eingabe.readInputInt( "Bitte geben Sie eine gueltige Monatszahl ein.", 
										  "Ungueltige Eingabe. Bitte geben Sie eine gueltige Monatszahl ein.",
										  1, 12); //Nutzeraufforderung. Fehlermeldung auf Konsole nach geworfener Exception. 1,12= Intervall f�r den G�ltigkeitsbereich
		return month;
	}
 
	
	
	public int liesJahr() {
		
		int year = eingabe.readInputInt( "Bitte geben Sie eine gueltige Jahreszahl ein.", 
										 "Ungueltige Eingabe. Bitte geben Sie eine gueltige Jahresszahl ein.", 
										 1582, 9999); //1582 Start f�r gregor. Kalender, 9999 ,damit Format nicht gest�rt wird, wenn User >10000 
	
		return year;	
		
	}
	
 	public void auswahlMenue() {
		
		eingabe = new Eingaben();
		ausgabe = new Ausgaben();
		modus = 1; //TODO
		
		generateGeneralHolidays();

//		// TODO: put in separate function
//	   	holidays.put( 1, new Event("Neujahr"));
//	   	holidays.put( 6, new Event("Heilige 3 Koenige"));
//	   	holidays.put( 31+14, new Event("Valentinstag"));
	   	
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
				int year = liesJahr();
			
				generateHolidaysForCurrentYear( year);
				
				StringBuffer wholeyear = new StringBuffer();
				for( int monat = 1; monat <= 12; monat++ ){
					String headlineFormat = getKopfzeileMonatsblatt( year, monat);
					wholeyear.append( headlineFormat);
					String monthStr = getMonatsblatt( year, monat);
					wholeyear.append(monthStr);
				}
				ausgabe.printToSys(wholeyear.toString());
				
			}
			else if(inputMain == 2){
				
				// modus = -1;
				int year = liesJahr();
				int month = liesMonat();

				generateHolidaysForCurrentYear( year);
				
				StringBuffer output = new StringBuffer();
				String headlineFormat = getKopfzeileMonatsblatt( year, month);
				output.append( headlineFormat);
				String monthStr = getMonatsblatt( year, month);
				output.append(monthStr);
		
				ausgabe.printToSys( output.toString());
			}
			else if(inputMain == 3){
				
				int modus = 1; // Kalender mit Feiertagen
				int year = liesJahr();
				
				StringBuffer wholeyear = new StringBuffer();
				for( int monat = 1; monat <= 12; monat++ ){
					String headlineFormat = getKopfzeileMonatsblatt( year, monat);
					wholeyear.append( headlineFormat);
					String monthStr = getMonatsblatt( year, monat);
					wholeyear.append(monthStr);
				}
				ausgabe.printToSys(wholeyear.toString());
			}
			else if(inputMain == 4) {
				
				modus = 1;
				int year = liesJahr();
				int month = liesMonat();

				StringBuffer output = new StringBuffer();
				String headlineFormat = getKopfzeileMonatsblatt( year, month);
				output.append( headlineFormat);
				String monthStr = getMonatsblatt( year, month);
				output.append(monthStr);
		
				ausgabe.printToSys( output.toString());
			}
			else if(inputMain == 5){
				boolean abfrage = eingabe.readInputJN( "Geben Sie [j] fuer Mo und [n] fuer So ein: " , "Ungueltige Eingabe. Bitte versuchen Sie es erneut: ");
				if( abfrage == true) {
					calFormatSelection = 0;
				}else {
					calFormatSelection = 1;
				}
			}
//			else if(inputMain == 6){
//				boolean abfrage = eingabe.readInputJN( "Geben Sie [j] ein, wenn Sie mit Events und [n], wenn Sie keine Events angezeigt haben moechten: " , "Ungueltige Eingabe. Bitte versuchen Sie es erneut: ");
//				if( abfrage == true) {
//					withEvents = 0;
//				}else {
//					noEvents = 1;
			}
		}
 }

  
//				
//			}else if(inputMain == 0){
//				ausgabe.printToSys("Programm wird beendet.");
//				programmEnde = true;
//			}

	//	}
//	}
	

 