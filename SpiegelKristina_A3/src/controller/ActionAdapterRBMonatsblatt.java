package controller;

import java.awt.event.*;
import java.util.Observable;

import gui.Fenster;
import kalender.Kalender;

public class ActionAdapterRBMonatsblatt extends Observable implements ActionListener  {

	Fenster fenster;
	Kalender kalender;
	
	public ActionAdapterRBMonatsblatt( Fenster f) {
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	public void actionPerformed( ActionEvent event) {
		
		kalender.setModus( -1);
		
		StringBuffer output = new StringBuffer();
		String headlineFormat = kalender.getKopfzeileMonatsblatt( 2017, 7);
		output.append( headlineFormat);
		String monthStr = kalender.getMonatsblatt( 2017, 7);
		output.append(monthStr);
		
		fenster.setCenterTextPane( output.toString());
		
		setChanged();
		notifyObservers();
	}
	
}
