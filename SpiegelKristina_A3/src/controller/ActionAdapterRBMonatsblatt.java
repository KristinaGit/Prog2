package controller;

import java.awt.event.*;

import gui.Fenster;
import kalender.Kalender;

public class ActionAdapterRBMonatsblatt implements ActionListener  {

	Fenster fenster;
	Kalender kalender;
	
	public ActionAdapterRBMonatsblatt( Fenster f) {
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	/**
	 * Eventhandling von Monatsblatt mit Ausgabe auf der TextPane ohne Feiertage.
	 */
	public void actionPerformed( ActionEvent event) {
		
		kalender.setModus( -1);
		
		int year = 1900 + fenster.leftComboBoxJahre.getSelectedIndex();
		int month = 1 + fenster.leftComboBoxMonate.getSelectedIndex();
		
		StringBuffer output = new StringBuffer();
		String headlineFormat = kalender.getKopfzeileMonatsblatt( year, month);
		output.append( headlineFormat);
		String monthStr = kalender.getMonatsblatt( year, month);
		output.append(monthStr);
		
		fenster.setCenterTextPaneFormatted( output.toString(), false, false);
	}
	
}
