package controller;

import java.awt.event.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import gui.Fenster;
import kalender.Event;
import kalender.Kalender;

public class ActionAdapterMenuKalender implements ActionListener  {
	
	public void actionPerformed( ActionEvent event) {
		
		Fenster f = Fenster.getInstance();
		Kalender kal = Kalender.getInstance();
		
		int year = 1900 + f.leftComboBoxJahre.getSelectedIndex();
		kal.generateHolidaysForCurrentYear( year);
		
		StringBuffer feiertage = new StringBuffer();
		
		if( event.getSource() == (Object) f.mntmFeiertage ) {
			
			// sortierte Ausgabe
			for( int tn = 1; tn < 366; ++tn) {
				
				Event cday = kal.getFeiertagTagesnummer(tn);
				
				if( null != cday) {
					feiertage.append( cday.name);
					feiertage.append("\n");
				}
			}
			
		}
		else if( event.getSource() == (Object) f.mntmFeiertageOhneSosa ) {
			
			int weekday = kal.getWochentagNeujahr( year);
			 
			for( int tn = 1; tn < 366; ++tn) {
				
				Event cday = kal.getFeiertagTagesnummer(tn);
				
				if( null != cday) {
					if( ! (6 == weekday || 0 == weekday)) {
						feiertage.append( cday.name);
						feiertage.append("\n");
					}
				}
				
				weekday = (weekday + 1) % 7;
			}
			
		}
		else {
			assert( false);
		}
		
		f.showOptionFeiertage( feiertage.toString());
	}
}