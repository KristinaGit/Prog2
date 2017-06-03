package kalender;

import java.util.LinkedList;

public class Jahresplaner {

    private KalenderFunktionen kf = new KalenderFunktionen();
    private LinkedList <LinkedList<String>> planliste = new LinkedList <> ();
    private int jahr = 2017; // Default aktuelles Jahr
    private int tag = 1;
    
    Kalender kalender = new Kalender();
    
    public Jahresplaner(int jahr) {

    }

    /**
     * der angegebene Monat für den Jahresplan wird zusammengebaut in der Form
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

    	StringBuffer zeile = new StringBuffer();
        zeile.append(kalender.getMonatsname(monat));
    	zeile.append(" ");
    	zeile.append(jahr);
    	zeile.append("\n");
    	monatInZeilen.add(zeile.toString());
    	
    	for( int daynumber = 1; daynumber <= daysInMonth; daynumber++) {
    		
    		zeile = new StringBuffer();
        	
	    	zeile.append(tagesnummer);
	    	zeile.append("|");
	    	zeile.append(kalender.getWochentagsname(wochentag));
	    	zeile.append("|");
	    	zeile.append(daynumber);
	    	zeile.append("\n");
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
    
    public String getKopfzeileJahresplan(int jahr, int monat){
    	
    	String monthName = kalender.getMonatsname(monat);  
    	
    	StringBuffer kopfZeile = new StringBuffer();
    	
    	kopfZeile.append(monthName);
    	kopfZeile.append("  ");
    	kopfZeile.append(jahr);
    	kopfZeile.append("          "); //10 Leerzeichen
    	
    		
        return kopfZeile.toString();
    	}

    /**
     * Der Jahresplan für die angegebenen Monate wird als String zurück gegeben.
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
    	
    	
    	return null;
    }
}