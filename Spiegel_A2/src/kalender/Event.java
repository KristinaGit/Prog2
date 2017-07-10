package kalender;

import java.util.HashMap;

public class Event {
	
	
	//periodisch: daily = 1; weekly = 2; annually = 3;
	Integer occurance = 0; 
	
	public String name;
	public String date;

	public Event() {	}
	
	// constructor, copy
	public Event( Event other) {	
		name = new String( other.name);
		date = new String( other.date);
		// int is special type so we can just do assignment
		occurance = new Integer( other.occurance);
	}
	
	
	public Event( String n) {	
		name = n;
	}

	
}

