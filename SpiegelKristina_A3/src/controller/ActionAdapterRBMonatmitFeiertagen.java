package controller;

import java.awt.event.*;
import java.awt.event.ActionListener;
import gui.Fenster;
import kalender.Kalender;

public class ActionAdapterRBMonatmitFeiertagen implements ActionListener {
	
	Fenster fenster = null;
	Kalender kalender;
	
	public ActionAdapterRBMonatmitFeiertagen(Fenster f) {
	
		fenster = f;
		kalender = Kalender.getInstance();
	}
	
	public void actionPerformed(ActionEvent event) {

		kalender.setModus(1);
		
		int year = 1900 + fenster.leftComboBoxJahre.getSelectedIndex();
		int month = 1 + fenster.leftComboBoxMonate.getSelectedIndex();
		
		StringBuffer output = new StringBuffer();
		String headlineFormat = kalender.getKopfzeileMonatsblatt( year, month);
		output.append( headlineFormat);
		String monthStr = kalender.getMonatsblatt( year, month);
		output.append(monthStr);
		
		fenster.setCenterTextPane( output.toString());
	}
		
		

}
	

