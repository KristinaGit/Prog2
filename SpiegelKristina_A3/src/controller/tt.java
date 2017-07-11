package controller;

import gui.Fenster;
import kalender.Kalender;

import java.awt.event.*;

public class ActionAdapterRBJahreskalender implements ActionListener {
	
	Fenster fenster = null;
	Kalender kalender = null;
	
	public ActionAdapterRBJahreskalender( Fenster f) {
		fenster = f;
		kalender = kalender.getInstance();
	}
	
	public void actionPerformed(ActionEvent event){
		
		/*int jahr = 2017;
		int von = 5;
		int bis = 7;
		
		Jahresplaner jahresplaner = new Jahresplaner( jahr);
		String jplan = jahresplaner.gibJahresplan(von, bis);
		
		fenster.setCenterTextPane( jplan);*/
		
		int year = kalender.liesJahr();
		
		StringBuffer wholeyear = new StringBuffer();
		for( int monat = 1; monat <= 12; monat++ ){
			String headlineFormat = kalender.getKopfzeileMonatsblatt( year, monat);
			wholeyear.append( headlineFormat);
			String monthStr = kalender.getMonatsblatt( year, monat);
			wholeyear.append(monthStr);
			
			Kalender jahreskalender = Kalender.getInstance();
			
			fenster.setCenterTextPane(wholeyear.toString());
			
		}
	
	}
}
