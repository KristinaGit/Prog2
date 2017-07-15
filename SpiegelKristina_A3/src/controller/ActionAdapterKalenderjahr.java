package controller;

import java.awt.event.*;
import java.awt.event.ActionListener;
import gui.Fenster;
import kalender.Kalender;

public class ActionAdapterKalenderjahr implements ActionListener{
	
	//TODO Fenster und Konstruktor koennen noch entfernt werden hier
	Fenster fenster = null;
	Kalender kalender = null;
	
	public ActionAdapterKalenderjahr (Fenster f){
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	/**
	 * Gibt das gesamte Kalenderjahr ohne Feiertage aus
	 */
	public void actionPerformed(ActionEvent event){
		
		kalender.setModus( -1);
		int year = 1900 + fenster.leftComboBoxJahre.getSelectedIndex();
		
		StringBuffer wholeyear = new StringBuffer();
		for( int monat = 1; monat <= 12; monat++ ){
			
			String headlineFormat = kalender.getKopfzeileMonatsblatt( year, monat);
			wholeyear.append( headlineFormat);
			String monthStr = kalender.getMonatsblatt( year, monat);
			wholeyear.append(monthStr);
			wholeyear.append( "\n");
		}

		fenster.setCenterTextPaneFormatted( wholeyear.toString(), false, false);
	
	}
}
