package controller;

import java.awt.event.*;
import java.awt.event.ActionListener;
import gui.Fenster;
import kalender.Kalender;

public class ActionAdapterKalenderjahr implements ActionListener{
	
	Fenster fenster = null;
	Kalender kalender = null;
	
	public ActionAdapterKalenderjahr (Fenster f){
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	public void actionPerformed(ActionEvent event){
		
		kalender.setModus( -1);
		int year = 1900 + fenster.leftComboBoxJahre.getSelectedIndex();
		
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
