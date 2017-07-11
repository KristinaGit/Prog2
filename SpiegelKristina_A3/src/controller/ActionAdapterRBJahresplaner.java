package controller;

import gui.Fenster;
import kalender.Jahresplaner;

import java.awt.event.*;

public class ActionAdapterRBJahresplaner implements ActionListener {
	
	Fenster fenster;
	
	public ActionAdapterRBJahresplaner( Fenster f) {
		fenster = f;
	}
	
	public void actionPerformed(ActionEvent event){
		
		int jahr = 2017;
		int von = 3;
		int bis = 7;
		
		Jahresplaner jahresplaner = new Jahresplaner( jahr);
		String jplan = jahresplaner.gibJahresplan(von, bis);
		
		fenster.setCenterTextPane( jplan);
	}
	
}
