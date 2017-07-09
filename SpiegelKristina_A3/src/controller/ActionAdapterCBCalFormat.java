package controller;

import java.awt.event.*;

import kalender.Kalender;


public class ActionAdapterCBCalFormat implements ActionListener {

	Kalender kalender = new Kalender();
	
	public void actionPerformed( ActionEvent event) {
		
		kalender.toggleFormatSelection();
		
		System.out.println( "Box clicked.");
		
	}
	
}
