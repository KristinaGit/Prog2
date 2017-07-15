package controller;

import java.awt.event.*;

import gui.Fenster;
import kalender.Jahresplaner;
import kalender.Kalender;

public class ActionAdapterMenuJahresplaner implements ActionListener  {
	
	
	/**
	 * Eventhandling der Jahresplaner-Option im Menu. Inklusive Speicher-Option.
	 */
	public void actionPerformed( ActionEvent event) {
		
		System.out.println("Out");
		
		Fenster f = Fenster.getInstance();
		Kalender cal = Kalender.getInstance();
		
		if( event.getSource() == (Object) f.mntmJahresplanerShow) {

			int monatVon = f.showJahresplanerVon();
			if( monatVon < 0) {
				System.err.println("ActionAdapterMenuJahresplaner : monatVon has invalid value. Probably user pressed cancel");
				return;
			}
			int monatBis = f.showJahresplanerBis();
			if( monatBis < 0) {
				System.err.println("ActionAdapterMenuJahresplaner : monatBis has invalid value. Probably user pressed cancel");
				return;
			}
			
			if( monatBis < monatVon) {
				System.err.println("ActionAdapterMenuJahresplaner : invalid range");				
				return;
			}
	
			int year = 1900 + f.leftComboBoxJahre.getSelectedIndex();
			Jahresplaner plan = new Jahresplaner( year); 
			cal.setModus(1);
			plan.mitFeiertagen = true;
	
	        String jpText = plan.gibJahresplan( monatVon+1, monatBis+1); // von Jan bis Juni
			f.showJahresplaner( jpText);
		}
		else if( event.getSource() == (Object) f.mntmJahresplanerSave) {
		
			if( f.jpdoc != null) {
				f.saveJahresplaner();
			}
		}
		else {
			assert(false);
		}
		
	}
}