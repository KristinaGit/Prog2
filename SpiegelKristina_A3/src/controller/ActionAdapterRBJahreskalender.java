package controller;

import gui.Fenster;
import kalender.Kalender;

import java.awt.event.*;
import java.util.ArrayList;

public class ActionAdapterRBJahreskalender implements ActionListener {
	
	Fenster fenster = null;
	Kalender kalender = null;
	
	
	public ActionAdapterRBJahreskalender( Fenster f) {
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	/**
	 * Formatierte Ausgabe des Jahreskalenders mit Feiertagen.
	 */
	public void actionPerformed(ActionEvent event){

		kalender.setModus( 1);
		
		int year = 1900 + fenster.leftComboBoxJahre.getSelectedIndex();
		kalender.generateHolidaysForCurrentYear( year );
		
		fenster.setCenterTextPaneFormatted( "", false, false);
		
		for( int monat = 1; monat <= 12; monat++ ){
			
			String headlineFormat = kalender.getKopfzeileMonatsblatt( year, monat);
			fenster.setCenterTextPaneFormatted( headlineFormat, false, true);
			
			ArrayList<Kalender.StreamTokenH> monatsBlatt = kalender.getMonatsblattWithFormatting( year, monat);
			
			for( int i = 0; i < monatsBlatt.size(); ++i) {
				fenster.setCenterTextPaneFormatted( monatsBlatt.get(i).token, monatsBlatt.get(i).highlight, true);
			}
			
		}
	
	}
}
