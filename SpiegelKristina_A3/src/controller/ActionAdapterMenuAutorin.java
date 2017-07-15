package controller;

import java.awt.event.*;

import gui.Fenster;

public class ActionAdapterMenuAutorin implements ActionListener  {
	
	
	/**
	 * Eventhandling fuer den MessageDialog zur Autor-Info
	 */
	public void actionPerformed( ActionEvent event) {
		
		Fenster f = Fenster.getInstance();
		f.showOptionPaneMenuAutorin();
	}
}