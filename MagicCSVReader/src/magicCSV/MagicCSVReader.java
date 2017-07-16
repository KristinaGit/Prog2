package magicCSV;

import java.io.*;
import java.util.ArrayList;


public class MagicCSVReader {
	
	public MagicCSVReader(){
	
	}
		
	public ArrayList<String> lies_CSV(){
	
		BufferedReader breader = null;
		ArrayList<String> list = new ArrayList<String>();
		
		try{
			
			breader = new BufferedReader(new InputStreamReader( new FileInputStream( "/Users/kristina/Desktop/Kristina/prog2/SpiegelKristina_A3/testfiles/Geburtstage.csv") ));
		}
		catch(FileNotFoundException e){
			System.err.println("File not found.");
		}
		
		try{
			String geburtstage;
			geburtstage = breader.readLine();
			while( geburtstage != null) {
				list.add(geburtstage);
				geburtstage = breader.readLine();
			}
		}
		catch ( IOException e ){
			System.err.println("IO Exception");
		}
		
		return list;
	}
}