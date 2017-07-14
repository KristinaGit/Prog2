package controller;

import java.awt.event.*;
import java.awt.event.ActionListener;
import gui.Fenster;

public class ActionAdapterAuswahlMonat implements ActionListener{
	
	public void actionPerformed(ActionEvent event){
		
		Fenster f = Fenster.getInstance();
		f.setImageMonth();
	}
}
