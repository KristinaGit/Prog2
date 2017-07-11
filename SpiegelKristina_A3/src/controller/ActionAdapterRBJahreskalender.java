package controller;

import gui.Fenster;
import kalender.Kalender;

import java.awt.event.*;

public class ActionAdapterRBJahreskalender implements ActionListener {
	
	Fenster fenster = null;
	Kalender kalender = null;
	
	public ActionAdapterRBJahreskalender( Fenster f) {
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	public void actionPerformed(ActionEvent event){

		kalender.setModus( 1);
		
		int year = 1900 + fenster.leftComboBoxJahre.getSelectedIndex();
		kalender.generateHolidaysForCurrentYear( year );
		
		StringBuffer wholeyear = new StringBuffer();
		for( int monat = 1; monat <= 12; monat++ ){
			
			String headlineFormat = kalender.getKopfzeileMonatsblatt( year, monat);
			wholeyear.append( headlineFormat);
			String monthStr = kalender.getMonatsblatt( year, monat);
			wholeyear.append(monthStr);
			
			fenster.setCenterTextPane(wholeyear.toString());
			
		}
	
	}
}
