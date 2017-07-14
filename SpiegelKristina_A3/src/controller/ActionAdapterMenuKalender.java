package controller;

import java.awt.event.*;

import gui.Fenster;
import kalender.Event;
import kalender.Kalender;

public class ActionAdapterMenuKalender implements ActionListener  {
	
	public void actionPerformed( ActionEvent event) {
		
		Fenster f = Fenster.getInstance();
		Kalender kal = Kalender.getInstance();
		
		Integer year = 1900 + f.leftComboBoxJahre.getSelectedIndex();
		kal.generateHolidaysForCurrentYear( year);
		
		Integer cdaynr = 1;
		Integer cmonthnr = 1;
		
		StringBuffer feiertage = new StringBuffer();
		
		if( event.getSource() == (Object) f.mntmFeiertage ) {
			
			// sortierte Ausgabe
			for( int tn = 1; tn < 366; ++tn) {
				
				Event cday = kal.getFeiertagTagesnummer(tn);
				
				if( null != cday) {
					feiertage.append( cdaynr.toString() + "/" + cmonthnr.toString() + "/" + year.toString() + " : ");
					feiertage.append( cday.name);
					feiertage.append("\n");
				}
				
				cdaynr++;
				// +1, damit er 1 Tag nach Monatsende zum naechsten Monat springt
				if( cdaynr == kal.getMonatslaenge( year, cmonthnr)+1) { 
					cdaynr = 1;
					cmonthnr++;
				}
			}
			
		}
		else if( event.getSource() == (Object) f.mntmFeiertageOhneSosa ) {
			
			int weekday = kal.getWochentagNeujahr( year);
			 
			for( int tn = 1; tn < 366; ++tn) {
				
				Event cday = kal.getFeiertagTagesnummer(tn);
				
				if( null != cday) {
					if( ! (6 == weekday || 0 == weekday)) {
						feiertage.append( cdaynr.toString() + "/" + cmonthnr.toString() + "/" + year.toString() + " : ");
						feiertage.append( cday.name);
						feiertage.append("\n");
					}
				}
				
				weekday = (weekday + 1) % 7;
				
				cdaynr++;
				if( cdaynr == kal.getMonatslaenge( year, cmonthnr)+1) {
					cdaynr = 1;
					cmonthnr++;
				}
			}
			
		}
		else {
			assert( false);
		}
		
		f.showOptionFeiertage( feiertage.toString());
	}
}