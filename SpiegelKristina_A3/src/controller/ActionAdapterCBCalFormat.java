package controller;

import java.awt.event.*;

import kalender.Kalender;


public class ActionAdapterCBCalFormat implements ActionListener {
	
	
	/**
	 * Eventhandling, mit der der Wochenbeginn von Mo auf So gesetzt wird und umgekehrt
	 */
	public void actionPerformed( ActionEvent event) {

		Kalender kalender = Kalender.getInstance();		
		kalender.toggleFormatSelection();
		
		System.out.println( "Box clicked.");
		
	}
	
}
