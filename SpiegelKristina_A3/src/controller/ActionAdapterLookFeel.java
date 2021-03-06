package controller;

import java.awt.event.*;

import gui.Fenster;

public class ActionAdapterLookFeel implements ActionListener  {
	
	
	/**
	 * Eventhandling fuer das Look and Feel. Fensterdesign in Windows, Metal oder Motif moeglich.
	 */
	public void actionPerformed( ActionEvent event) {
		
		Fenster f = Fenster.getInstance();
		
		if( event.getSource() == (Object) f.mntmWindows ) {
			f.setLookFeel( 0);
		}
		else if( event.getSource() == (Object) f.mntmMetal ) {
			f.setLookFeel( 1);
		}
		else if( event.getSource() == (Object) f.mntmMotif ) {
			f.setLookFeel( 2);
		}
		else {
			assert( false);
		}
	}
}