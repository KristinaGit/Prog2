package start;
import java.util.HashMap;
import java.util.LinkedList;

import kalender.Event;
import kalender.Jahresplaner;
import kalender.Kalender;

public class Start
{
    public static void main(String[] args) {
      
    	//(new Kalender()).auswahlMenue();
    	
    	int monat = 1;
    	
		Jahresplaner plan = new Jahresplaner(2017); // Jahr
		LinkedList<String> monatplan = plan.baueMonat(monat);
	    
        String tmp = plan.gibJahresplan(1, 6); // von Jan bis Juni
        
        System.out.println(tmp);

		//ausgabe.printToSys( tmp.toString());;

    }
}