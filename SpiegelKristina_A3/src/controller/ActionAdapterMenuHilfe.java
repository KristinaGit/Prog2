package controller;

import java.awt.event.*;

import gui.Fenster;

public class ActionAdapterMenuHilfe implements ActionListener  {
	
	/**
	 * Eventhandling Message-Dialog Hilfe an
	 */
	public void actionPerformed( ActionEvent event) {
		
		Fenster f = Fenster.getInstance();
		f.showOptionPaneMenuHilfe();
	}
}