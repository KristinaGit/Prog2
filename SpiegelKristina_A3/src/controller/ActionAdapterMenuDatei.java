package controller;

import java.awt.event.*;

import gui.Fenster;

public class ActionAdapterMenuDatei implements ActionListener  {
	
	public void actionPerformed( ActionEvent event) {
		
		Fenster f = Fenster.getInstance();
		
		if( event.getSource() == (Object) f.mntmMenuDateiOpen ) {
			f.openFileCenterPane();
		}
		else if( event.getSource() == (Object) f.mntmMenuDateiSave ) {
			f.saveCenterPane();
		}
		else if( event.getSource() == (Object) f.mntmMenuDateiClose ) {
			f.terminate();
		}
		else {
			assert( false);
		}
	}
}