package controller;

import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import gui.Fenster;
import kalender.Kalender;
import kalender.Kalender.StreamTokenH;

public class ActionAdapterRBMonatmitFeiertagen implements ActionListener {
	
	Fenster fenster = null;
	Kalender kalender;
	
	public ActionAdapterRBMonatmitFeiertagen(Fenster f) {
	
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	/**
	 * Eventhandling fuer die Ausgabe von Monatsblatt mit Feiertagen auf der TextPane.
	 */
	public void actionPerformed(ActionEvent event) {

		kalender.setModus(1);
		
		int year = 1900 + fenster.leftComboBoxJahre.getSelectedIndex();
		int month = 1 + fenster.leftComboBoxMonate.getSelectedIndex();
		kalender.generateHolidaysForCurrentYear( year );
		
		String headlineFormat = kalender.getKopfzeileMonatsblatt( year, month);
		fenster.setCenterTextPaneFormatted( headlineFormat, true, false);
		
		ArrayList<Kalender.StreamTokenH> monatsBlatt = kalender.getMonatsblattWithFormatting( year, month);
		
		for( int i = 0; i < monatsBlatt.size(); ++i) {
			fenster.setCenterTextPaneFormatted( monatsBlatt.get(i).token, monatsBlatt.get(i).highlight, true);
		}
	}
		

}
	

