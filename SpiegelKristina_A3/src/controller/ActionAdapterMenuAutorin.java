package controller;

import java.awt.event.*;

import gui.Fenster;

public class ActionAdapterMenuAutorin implements ActionListener  {
	
	public void actionPerformed( ActionEvent event) {
		
		Fenster f = Fenster.getInstance();
		f.showOptionPaneMenuAutorin();
	}
}