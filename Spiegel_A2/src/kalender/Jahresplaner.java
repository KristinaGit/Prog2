package kalender;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.ListIterator;

public class Jahresplaner {

    private KalenderFunktionen kf = new KalenderFunktionen();
    private LinkedList <LinkedList<String>> planliste = new LinkedList <> ();
    private int jahr = 2017; // Default aktuelles Jahr
    private int tag = 1;
    private final int columnWidth = 40; //Konstante fuer eine Spaltenbreite einer Zeile des Jahresplaners
    
    Kalender kalender = new Kalender();
    
    public Jahresplaner(int jahr) {
    	kalender.generateHolidaysForCurrentYear( jahr);
    }

    /**
     * der angegebene Monat fuer den Jahresplan wird zusammengebaut in der Form
     * Mai 2017        
     * Mo|01|            |121
     * Di|02|            |122
     * Mi|03|            |123
     *        usw.
     * Di|30|            |150
     * Mi|31|            |151
     * Jede Zeile wird als String in einer LinkedList<String> abgespeichert
     * @param monat (int) - 1= jan bis 12= dez
     * @return LinkedList<String> der "Monatsplan" - Container mit allen Zeilen des Monats
     */
    public LinkedList<String> baueMonat( int monat){
    	
    	LinkedList<String> monatInZeilen = new LinkedList<>();
    	
    	int tagesnummer = kf.tagesnummer(tag, monat, jahr);
    	int wochentag = kf.wochentag_im_jahr(monat, tagesnummer);
    	int daysInMonth = kalender.getMonatslaenge(wochentag, monat);

    	monatInZeilen.add( getKopfzeileJahresplan(jahr, monat));
    	
    	for( int daynumber = 1; daynumber <= daysInMonth; daynumber++) {
    		
    		StringBuffer zeile = new StringBuffer();
        	
	    	//zeile.append(tagesnummer);
	    	//zeile.append("|");
	    	zeile.append(kalender.getWochentagsname(wochentag));
	    	zeile.append("|");
	    		if( daynumber < 10) {
	    			zeile.append( "0");
	    		}
	    	zeile.append(daynumber);
	    	zeile.append("|");
	    	
	    	int numWhitespaces = columnWidth - zeile.length() - 2 - 6;
	    	
	    	//holt sich aus HashMap das Element zur Tagesnummer
	    	Event cday = kalender.holidaysforcurrent.get(tagesnummer); 
			if( null != cday) {
				numWhitespaces = numWhitespaces - cday.name.length() -1;
				if( numWhitespaces < 0) {
					StringBuffer str = new StringBuffer(cday.name);
					str.delete( str.length() + numWhitespaces, str.length());
					zeile.append( str.toString());
					numWhitespaces = 1;
				}
				else {
					zeile.append(" ");
					zeile.append( cday.name);
				}
			}
			for( int i = 0; i < numWhitespaces; i++) {
	    		zeile.append(" ");
	    	}
	    	
	    	zeile.append("|");
	    		
	    	if(tagesnummer < 10){
    			zeile.append("  ");
    			zeile.append(tagesnummer);
    		}
	    	else if( tagesnummer < 100){
    			zeile.append(" ");
    			zeile.append(tagesnummer);
    		}else{
    			zeile.append(tagesnummer);
    		}
	    	zeile.append("    ");
	    	
	    	monatInZeilen.add(zeile.toString());
	    	
	    	wochentag = (wochentag + 1) % 7;	
	    	tagesnummer++;
    	}
    	
    	return monatInZeilen;
    }
    
    /*
     * public getKopfzeileJahresplan(int jahr, int monat)
     * ------------------------------------------------
     * @param int jahr
     * @param int monat
     * ------------------------------------------------
     * Hilfsmethode fuer die Kopfzeile des Jahresplaner
     */
    
    public String getKopfzeileJahresplan(int jahr, int monat) {
    	  
    	StringBuffer kopfZeile = new StringBuffer();
    	
		String monthName = kalender.getMonatsname(monat);
		
    	kopfZeile.append(monthName);
    	kopfZeile.append("  ");
    	kopfZeile.append(jahr);
    	
    	int numWhitespaces = columnWidth - kopfZeile.length();
    	for( int i = 0; i < numWhitespaces; ++i) {
    		kopfZeile.append(" ");
    	}
    	
    	return kopfZeile.toString();
    }

    /**
     * Der Jahresplan fuer die angegebenen Monate wird als String zurück gegeben.
     * Hinweis zur Implementierung: 
     * Die Monatspläne der angegebenen Monate werden in einer
     * Container-Klasse LinkedList <LinkedList<String>> der "Planliste" zusammengefasst.
     * Damit erhält man eine 2-dimensionale Datenstruktur.
     * Um den String zusammenzubauen, wird die Datenstruktur so durchlaufen, dass jeweils
     * die ersten Zeilen aller Monate nebeneinander ausgegeben werden. 
     * Der zurückgegebene String sollte folgendes Format haben:
     * Januar 2017            Februar 2017            Maerz 2017            
     * So|01|            |1    Mi|01|            |32    Mi|01|            |60    
     * Mo|02|            |2    Do|02|            |33    Do|02|            |61    
     * Di|03|            |3    Fr|03|            |34    Fr|03|            |62    
     * Mi|04|            |4    Sa|04|            |35    Sa|04|            |63    
     * Do|05|            |5    So|05|            |36    So|05|            |64    
     * Fr|06|            |6    Mo|06|            |37    Mo|06|            |65    
     * usw.
     *
     * @param von (int) - 1= jan bis 12= dez
     * @param bis (int) - 1= jan bis 12= dez
     * @return String - der Jahresplan
     */
    public String gibJahresplan(int von, int bis) {
    	
    	StringBuffer jahresPlan = new StringBuffer();
    	//StringBuffer fuer den Whitespace, der die Leerzeichen nach der Jahreszahl zum naechsten Monat dynamisch anlegen soll.
    	StringBuffer columnWidthWS = new StringBuffer();
    	//prueft, ob es noch Whitespace aufzufuellen gibt, indem es die Konstante columnWidth vergleicht und bei kleinerem Wert entsprechten " " anhaengt
    	for( int i = 0; i < columnWidth; i++) {
    		columnWidthWS.append( " " );
    	}
    	// prueft die Monate, die angezeigt werden sollen, um diese dann unter Aufruf von baueMonat() hinzu zufuegen
    	for(int i = von; i <= bis; i++){
    		planliste.add( baueMonat(i));
    	}
    	
    	//ArrayList anlegen, um die Iteratoren jeder einzelnen (je wie hoch von bis gesetzt ist) LinkedList abzuspeichern
    	ArrayList< ListIterator<String> > iterList = new ArrayList< ListIterator<String> >();
    	ListIterator< LinkedList<String> > planlistIter = planliste.listIterator();
    	while( planlistIter.hasNext()) {
    		//holt sich fuer jede LinkedList mit add einen Iterator, die Anzahl der Iteratoren erhaelt er aus planliste durch planliste.add(baueMonat(i)
    		iterList.add( planlistIter.next().listIterator());
    	}
    	
    	boolean isempty = false;
    	while( ! isempty) {
    		isempty = true;
	    	for( int i = 0; i < iterList.size(); ++i) {
	    		if( iterList.get(i).hasNext()) {
	    			isempty = false;
	    			jahresPlan.append( iterList.get(i).next());
	    		}
	    		else {
	    			jahresPlan.append( columnWidthWS.toString());
	    		}
	    	}
	    	jahresPlan.append("\n");
    	}
 
    	return(jahresPlan.toString());  	
    }
}