package start;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

//import kalender.Kalender;

public class Start {
//	public Start(){
//
//}
    private static void createAndShowKalenderGui(){
    	
    	JFrame kalender = new JFrame("Kalender");
    	kalender.setDefaultCloseOperation(kalender.EXIT_ON_CLOSE);
    	
    	JPanel kalPanel = new JPanel();
    	kalender.setContentPane(kalPanel);
    	
    	JPanel contentPane = new JPanel(new FlowLayout());	
    	
    	JButton fileButton = new JButton("Datei");
    	JButton calButton = new JButton("Kalender");
    	JButton planButton = new JButton("Jahresplaner");
    	JButton lafButton = new JButton("Look & Feel");
    	JButton infoButton = new JButton("Info");
    	
    	kalender.getContentPane().add(fileButton);
    	kalender.getContentPane().add(calButton);
    	kalender.getContentPane().add(planButton);
    	kalender.getContentPane().add(lafButton);
    	kalender.getContentPane().add(infoButton);
    	
    	kalender.pack();
    	kalender.setVisible(true);
    	
    
    }
    
	public static void main(String[] args) {
		// Aufgrund von Thread-Sicherheit sollte die Methode vom 
		// event-dispatching Thread aufgerufen werden
//	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//	            public void run() {
//	                createAndShowGUI();

//		Kalender kalender = new Kalender();
//		kalender.setVisible(true);
//		kalender.setLocation(600, 600);
//	}

}
}
	
