package controller;

import java.awt.event.*;

import kalender.Kalender;


public class ActionAdapterCBCalFormat implements ActionListener {
	
	public void actionPerformed( ActionEvent event) {

		Kalender kalender = Kalender.getInstance();		
		kalender.toggleFormatSelection();
		
		System.out.println( "Box clicked.");
		
	}
	
}
