package controller;

import java.awt.event.*;

import gui.Fenster;
import kalender.Jahresplaner;
import kalender.Kalender;

public class ActionAdapterMenuJahresplaner implements ActionListener  {
	
	public void actionPerformed( ActionEvent event) {
		
		System.out.println("Out");
		
		Fenster f = Fenster.getInstance();
		Kalender cal = Kalender.getInstance();
		
		if( event.getSource() == (Object) f.mntmJahresplanerShow) {
			
			// TODO: Error handling when return values are not in valid range (e.g. user pressed cancel)
			int monatVon = f.showJahresplanerVon();
			int monatBis = f.showJahresplanerBis();
			
			int year = 1900 + f.leftComboBoxJahre.getSelectedIndex();
			Jahresplaner plan = new Jahresplaner( year); 
			cal.setModus(1);
			plan.mitFeiertagen = true;
	
	        String jpText = plan.gibJahresplan( monatVon+1, monatBis+1); // von Jan bis Juni
			f.showJahresplaner( jpText);
		}
		else if( event.getSource() == (Object) f.mntmJahresplanerSave) {
		
			if( f.jpdoc != null) {
				// TODO
				System.err.println("Jahresplaner::Show has to be called first.");
				f.saveJahresplaner();
			}
		}
		else {
			assert(false);
		}
		
	}
}