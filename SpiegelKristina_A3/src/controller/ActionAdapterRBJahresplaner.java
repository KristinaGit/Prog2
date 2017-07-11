package controller;

import gui.Fenster;
import kalender.Jahresplaner;
import kalender.Kalender;

import java.awt.event.*;

public class ActionAdapterRBJahresplaner implements ActionListener {
	
	Fenster fenster;
	Kalender kalender;
	// Jahresplaner jahresplaner;
	// int von;
	// int bis;
	
	
	public ActionAdapterRBJahresplaner( Fenster f) {
		fenster = f;
		kalender = Kalender.getInstance();
		// jahresplaner = kalender.gibJahresplan();
	}
	
	public void actionPerformed(ActionEvent event){
		
		// jahresplaner.gibJahresplan(von, bis) ;
		// fenster.setCenterTextPane(jahresplan.toString());
		
		int jahr = 2017;
		int von = 3;
		int bis = 7;
		
		Jahresplaner jahresplaner = new Jahresplaner( jahr);
		String jplan = jahresplaner.gibJahresplan(von, bis) ;
		
		fenster.setCenterTextPane( jplan);
	}
	
}
