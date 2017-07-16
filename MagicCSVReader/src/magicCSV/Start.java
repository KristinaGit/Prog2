package magicCSV;

import java.util.ArrayList;
import java.util.Iterator;

public class Start {
	
	public static void main(String[] args) {
		
		MagicCSVReader reader = new MagicCSVReader();
		ArrayList<String> list = reader.lies_CSV();
		
		Iterator<String> iter = list.iterator();
		while( iter.hasNext()){
			//iter.next(); sonst ueberspringt es dadurch eine Zeile
			System.out.println(iter.next());
		}
		
		
		
	}

}
