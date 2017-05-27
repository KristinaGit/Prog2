package kalender;

import java.util.HashMap;

public class Event {
	
	
	//periodisch: daily = 1; weekly = 2; annually = 3;
	int occurance = 0; 
	
	public String name;
	public String date;

	public Event() {	}
	
	public Event( String n) {	
		name = n;
	}

	
}

