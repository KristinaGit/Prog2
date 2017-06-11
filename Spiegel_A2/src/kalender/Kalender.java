package kalender;
/*
 * @Dateiname: A2_Spiegel
 * @Compiler:Eclipse / Version neon.3
 * @author: Kristina Spiegel (mit Uebernahme des Interfaces und der bereitgestellten Klassen von Frau Prof. Bannert)
 * @Erstellungsdatum: 18.05.2017
 * @Letzte Aenderung: 11.06.2017
 * @Kalender-Aufgabe A2 aus dem Modul Angewandte Programmierung SoSe 2017
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class Kalender implements IKalender {
	/*
	 * Deklarierung der Membervariablen, mit denen die Nutzereingabe und die Ausgabe behandelt wird
	 */
	Ausgaben ausgabe; 
	Eingaben eingabe;
	// Deklarierung der Variable modus  fuer die Kalenderblatt-Funktion mit und ohne Feiertagen
	int modus = 1;
	
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
	  * Default-Konstruktor (mit Klassennamen ohne Argumente), um sicher zu stellen, dass feste Feiertage genau(!) 1x initialisiert werden
	  */
	 public Kalender() { 
		 generateGeneralHolidays();
	 }
	 
	 /**
	  *  void generateGeneralHolidays()
	  * -----------------------------------------------------------------------------
	  * Hilfsmethode zum Befuellen der HashMap mit festen Feiertagen
	  */
	 
	 void generateGeneralHolidays() {
		 
		 holidays.put( 1, new Event("Neujahr"));
		 holidays.put( 6, new Event("Heilige 3 Koenige"));
	   	 holidays.put( 45, new Event("Valentinstag"));
	   	 holidays.put( 121, new Event("1. Mai"));
	   	 holidays.put( 227, new Event("Maria Himmelfahrt"));
	   	 holidays.put( 276, new Event("Tag der dt. Einheit"));
	     holidays.put( 304, new Event("Reformationstag"));
	   	 holidays.put( 305, new Event("Allerheiligen"));     
	   	 holidays.put( 358,new Event("Heiligabend"));
	   	 holidays.put( 359, new Event("1. Weihnachtstag"));
	   	 holidays.put( 360, new Event("2. Weihnachtstag"));
	   	 holidays.put( 365, new Event("Silvester"));
	 }
	 
	 /**
	  *  void generateHolidayForCurrentYear()
	  * -----------------------------------------------------------------------------
	  * @param: int
	  * -----------------------------------------------------------------------------
	  * Hilfsmethode zum Befuellen der HashMap mit Osterabhaengigen Feiertagen und denen, die durch das Schaltjahr beeinflusst werden
	  */
	 void generateHolidaysForCurrentYear( int year) {
	 
		 //shallowcopy von holidays
		 boolean pruefSchaltjahr= calFuncs.istSchaltjahr(year);
		 if( ! pruefSchaltjahr) {
			 holidaysforcurrent = (HashMap)holidays.clone();
		 }
		 else {
			 int feblast = calFuncs.tagesnummer( 29, 02, year);
			 
			 // we actually need a deep copy here
			 holidaysforcurrent = new HashMap<Integer, Event>();
			 
			 Iterator it = holidays.entrySet().iterator();
			 while( it.hasNext()) {
				 Map.Entry me = (Map.Entry)it.next();
				 Integer key = (Integer) me.getKey();
				 if( key > feblast) {
					 holidaysforcurrent.put( key+1, (Event) me.getValue());
				 }
				 else {
					 holidaysforcurrent.put( key, (Event) me.getValue());
				 }
			 }
		 }
		 
		 
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
			
			
		
		  // fuer die Adventssonntage
		 int tagesnummer = calFuncs.tagesnummer( 24, 12, year);
		 int weekday = calFuncs.wochentag_im_jahr( year, tagesnummer);  
		 Integer nradventssonntag = 4;
		 int advent4 = 0;
		 
		 while( nradventssonntag > 0){
			 if(0 == weekday) {
				 holidaysforcurrent.put( tagesnummer, new Event(nradventssonntag.toString() + ". Adventssonntag"));
				 if(4 == nradventssonntag){
					 advent4 = tagesnummer; // 4. Advent, beim ersten ! Durchlauf speichern
				 }
				 nradventssonntag--;
			 }
			 weekday--;
			 if( weekday < 0) {
				 weekday = 6;
			 }
			 tagesnummer--;
		 }
		 int tagesnummerHeiligAbend = calFuncs.tagesnummer( 24, 12, year);
		 
		 // im Falle einer HashMap Collision von 4. Advent und HeiligAbend, wird Element hierfuer ueberschrieben und gibt beide Feiertage aus
		 if( advent4 == tagesnummerHeiligAbend) {
			 holidaysforcurrent.put( tagesnummerHeiligAbend, new Event("Heiligabend / 4. Adventssonntag"));
		 }
		 
		 // fuer den Buss- und Bettag
		 tagesnummer = calFuncs.tagesnummer( 1, 11, year);
		 // ?: int weekdayBettag = calFuncs.wochentag_im_jahr( year, tagesnummer); 
		 if( tagesnummerHeiligAbend == advent4){
			 //advent4 = tagesnummer vom 4. Advent, davon 4*7 Tage (4 Sonntage ) - 4 (Differenz der Tage Sonntag zu Mittwoch rueckwaerts) subtrahiert
		 	 holidaysforcurrent.put( (advent4-32), new Event("Buss- und Bettag"));
		 } else{
		 	holidaysforcurrent.put( (advent4-32), new Event("Buss- und Bettag"));
		 }
		
		 
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
	 
	 
	 
	 /**
	  * StringBuffer formatMonth
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
	
	 
	 /**
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
		
	 /** 
	  * String getWochentagsname()
	  * ------------------------------------------------------------------------------------
	  * @param: int monat
	  * ------------------------------------------------------------------------------------
	  * @return: String strmonth
	  * ------------------------------------------------------------------------------------
	  * Hilfsmethode switch fuer die Rueckgabe des Monatsnamen als String
	  */
	 public String getWochentagsname( int wochentag){
		 
		String strwochentag = null;
		
		switch (wochentag){
			
			case 0: {
				strwochentag = new String("SO");
				break; }
			case 1: {
				strwochentag = new String("MO");
				break; }
			case 2: {
				strwochentag = new String("DI");
				break; }
			case 3: {
				strwochentag = new String("MI");
				break; }
			case 4: {
				strwochentag = new String("DO");
				break; }
			case 5: {
				strwochentag = new String("FR"); 
				break; }
			case 6: {
				strwochentag = new String("SA");
				break; }
			default:
				System.err.println("Ungueltige Eingabe");	
		}
		
		return strwochentag;
	 }
			
	 /**
	  * int getMonatslaenge()
	  * ------------------------------------------------------------------------------------
	  * @param: int monat
	  * @param: int jahr
	  * ------------------------------------------------------------------------------------
	  * @return: int daysInMonth
	  * ------------------------------------------------------------------------------------
	  * Hilfsmethode switch fuer die unterschiedlichen Monatslaengen
	  */
	 public int getMonatslaenge( int jahr, int monat) {

		boolean pruefSchaltjahr = calFuncs.istSchaltjahr(jahr);
		int daysInMonth = 31;
		
		// fuer Monate mit 30 Tagen (April, Juni, September, November)		 
		if(monat == 4 || monat == 6 || monat == 9 || monat == 11){
			daysInMonth = 30;			
		}
		else if(monat == 2){
			daysInMonth = 28;
			if(pruefSchaltjahr){ //geschachteltes if, da es sonst das naechste Statement nicht ueberpruefen wuerde
				daysInMonth = 29;
			}
		}
		
		return daysInMonth;
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
		
		int daysInMonth = getMonatslaenge( jahr, monat);

		//fuer die Ausgabe der Feiertage im jeweiligen Monat
		ArrayList<String> holidaysinmonth = new ArrayList<String>();
		
		for ( int daynumber = 1; daynumber <= daysInMonth; daynumber++) {
			
			
			 	if (modus == 1){
				
				Event cday = holidaysforcurrent.get(tagesnummer); //holt sich aus HashMap das Element zur Tagesnummer; cday = currentday
				
				boolean pruefFeiertag = true; 
				if( null == cday) {
					pruefFeiertag = false;
				}
				else {
					StringBuffer line = new StringBuffer();
					if( daynumber < 10) {
						line.append( "0");
					}
					line.append( daynumber);
					line.append( ". : ");
					line.append( cday.name);
					holidaysinmonth.add( line.toString()); 
				}
				
				if( pruefFeiertag) { 
					
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
										 1582, 9999); //1582 Start fuer gregor. Kalender, 9999 ,damit Format nicht gest�rt wird, wenn User >10000 
	
		return year;	
		
	}
	/**
	  *public void saveToFile()
	  * ------------------------------------------------------------
	  * @param: String fileName
	  * @param: String output
	  * ------------------------------------------------------------------------------------
	  * Hilfsmethode, um abzufragen, ob und wenn ja welchen Dateinamen der Nutzer speichern moechte
	  */
  public void saveToFile(String fileName, String output){
		
		boolean saveToFile = eingabe.readInputJN("Moechten Sie die Ansicht in einer Datei abspeichern?\nBitte geben Sie [j] fuer Ja und [n] fuer Nein ein: ",
				"Ungueltige Eingabe. Bitte versuchen Sie es erneut: ");
		
		if( saveToFile == true){				 
			
			boolean useCustomFileName = eingabe.readInputJN("Moechten Sie fuer die Datei einen Namen waehlen?\nBitte geben Sie [j] fuer Ja und [n] fuer Nein ein: ", 
					"Ungueltige Eingabe. Bitte versuchen Sie es erneut: ");
			
			if( useCustomFileName == true) {
				fileName = eingabe.readInputString("Bitte geben Sie den Dateinamen ein: ", 
					"Ungueltige Eingabe. Bitte versuchen Sie es erneut!");						
			}

			ausgabe.printToFile(fileName, output);
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
		
			ausgabe.printMainTextMenu();

			int inputMain = eingabe.readInputInt( "Bitte waehlen Sie ihre gewuenschte Option:", 
												  "Ungueltige Eingabe. Bitte geben Sie eine gueltige Zahl ein.",
												  0, 6);

			if(inputMain == 1){
				
				modus = -1;
				int year = liesJahr();
				
				StringBuffer wholeyear = new StringBuffer();
				for( int monat = 1; monat <= 12; monat++ ){
					String headlineFormat = getKopfzeileMonatsblatt( year, monat);
					wholeyear.append( headlineFormat);
					String monthStr = getMonatsblatt( year, monat);
					wholeyear.append(monthStr);
				}
				ausgabe.printToSys(wholeyear.toString());
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append(".txt");
				saveToFile( defaultFilename.toString(), wholeyear.toString());
				
			}
			else if(inputMain == 2){
				
				modus = -1;
				int year = liesJahr();
				int month = liesMonat();
				
				StringBuffer output = new StringBuffer();
				String headlineFormat = getKopfzeileMonatsblatt( year, month);
				output.append( headlineFormat);
				String monthStr = getMonatsblatt( year, month);
				output.append(monthStr);
		
				ausgabe.printToSys( output.toString());				
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append("_");
				defaultFilename.append(month);
				defaultFilename.append(".txt");
				saveToFile( defaultFilename.toString(), output.toString());
			}
			else if(inputMain == 3){
				
				modus = 1; // Kalender mit Feiertagen
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
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append("_f");			
				defaultFilename.append(".txt");
				saveToFile( defaultFilename.toString(), wholeyear.toString());			
				
			}
			else if(inputMain == 4) {
				
				modus = 1;
				int year = liesJahr();
				int month = liesMonat();

				generateHolidaysForCurrentYear( year);

				StringBuffer output = new StringBuffer();
				String headlineFormat = getKopfzeileMonatsblatt( year, month);
				output.append( headlineFormat);
				String monthStr = getMonatsblatt( year, month);
				output.append(monthStr);
		
				ausgabe.printToSys( output.toString());
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(year);
				defaultFilename.append("_");
				defaultFilename.append(month);
				defaultFilename.append("_");
				defaultFilename.append("f");
				defaultFilename.append(".txt");
				saveToFile( defaultFilename.toString(), output.toString());
							
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
				
				//Abfrage Jahr fuer Jahresplaner
				int planJahr = eingabe.readInputInt( "Bitte geben Sie eine gueltige Jahreszahl ein.", 
						 "Ungueltige Eingabe. Bitte geben Sie eine gueltige Jahresszahl ein.", 
						 1582, 9999); //1582 Start fuer gregor. Kalender, 9999 ,damit Format nicht gest�rt wird, wenn User >10000 
				
				// Agfrage Jahresplaner - Range, Integer benutzt, da er int nicht genommen hat
				Integer planVon = eingabe.readInputInt( "Bitte geben Sie ein, mit welchem Monat der Jahresplan starten soll.", 
						  "Ungueltige Eingabe. Bitte geben Sie eine gueltige Monatszahl ein.",
						  1, 12); 
				Integer planBis = eingabe.readInputInt( "Bitte geben Sie ein, mit welchem Monat der Jahresplan enden soll.", 
						  "Ungueltige Eingabe. Bitte geben Sie eine gueltige Monatszahl ein.",
						  1, 12);
				
				Jahresplaner plan = new Jahresplaner(planJahr); // Jahr
		
				boolean abfrage = eingabe.readInputJN( "Moechten Sie Events mit ausgegeben haben? Geben Sie [j] fuer Ja und [n] fuer Nein ein: " , "Ungueltige Eingabe. Bitte versuchen Sie es erneut: ");
				if( abfrage == true) {
					modus = 1;
					plan.mitFeiertagen = true;
				}else {
					modus = -1;
					plan.mitFeiertagen = false;
				}
				
		        String tmp = plan.gibJahresplan( planVon, planBis); // von Jan bis Juni
				ausgabe.printToSys( tmp.toString());;	
				
				StringBuffer defaultFilename = new StringBuffer();
				defaultFilename.append(planJahr);
				defaultFilename.append("_");
				defaultFilename.append(planVon);
				defaultFilename.append("_");
				defaultFilename.append(planBis);
				defaultFilename.append("_");
				defaultFilename.append("jahresplan");	
				
				if( 1 == modus) { // diese Schreibweise vorziehen, anstatt modus == 1, um bei einem Tippfehler Bug zu vermeiden, da es sonst zur Variablen Zuweisung kommen koennte			
					defaultFilename.append("_");
					defaultFilename.append("f");
				}
				defaultFilename.append(".txt");
				saveToFile( defaultFilename.toString(), tmp.toString());
			}
			else if(inputMain == 0){
				ausgabe.printToSys("Programm wird beendet.");
				programmEnde = true;
			}

		}
	}
 }
 	

	

 