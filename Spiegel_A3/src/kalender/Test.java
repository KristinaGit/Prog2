//package gui;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.HeadlessException;
//
//import javax.swing.BorderFactory;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
//
//public class MeinFenster extends JFrame {
//
//	public MeinFenster() throws HeadlessException {
//		this("Mein Fenster");
//	}
//
//
//	public MeinFenster(String title) throws HeadlessException {
//		super(title);
//		
//		// Einstellungen f�r das Fenster
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		//this.setVisible(true);
//		
//		
//		// Men�leiste hinzuf�gen
//		
//		// ContentPane holen
//		Container cpane = this.getContentPane();
//		
//		// Layout f�r die ContentPanel festlegen
//		cpane.setLayout(new BorderLayout());
//		
//		// Panel
//		JPanel pnlNord = new JPanel();
//		JPanel pnlMitte = new JPanel();
//		JPanel pnlSued = new JPanel();
//		JPanel pnlWest = new JPanel();
//		JPanel pnlOst = new JPanel();
//		
//		
//		// alle Panel auf die ContentPane legen
//		cpane.add(pnlNord,BorderLayout.NORTH);
//		cpane.add(pnlMitte,BorderLayout.CENTER);
//		cpane.add(pnlSued,BorderLayout.SOUTH);
//		cpane.add(pnlWest,BorderLayout.WEST);
//		cpane.add(pnlOst,BorderLayout.EAST);
//		
//		
//		// Panel mit R�ndern versehen
//		pnlNord.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
//		pnlMitte.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
//		pnlSued.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
//		pnlWest.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
//		pnlOst.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
//		
//		
//		// Layout f�r die Panel
//		
//		GridBagLayout grid = new GridBagLayout();
//		pnlOst.setLayout(grid);	
//		pnlWest.setLayout(grid);
//		pnlSued.setLayout(grid);
//		
//		
//		// Panel Nord
//		
//		
//		// Panel Sued
//		GridBagConstraints constrSued = new GridBagConstraints();
//
//		JButton butOk = new JButton("OK");
//		
//		constrSued.gridwidth = GridBagConstraints.REMAINDER;
//		constrSued.anchor = GridBagConstraints.EAST;
//		constrSued.gridx = 10;
//		constrSued.gridy = 10;
//		constrSued.fill = GridBagConstraints.BOTH;
//		pnlSued.add(butOk, constrSued);
//		
//		
//		// Panel West
//		GridBagConstraints constrWest = new GridBagConstraints();
//
//		String[] strLabel = { "Jan", "Feb", "Mrz", "Apr","Mai", "Jun" };
//		JRadioButton[] rdbBut = new JRadioButton[strLabel.length];
//		
//		ButtonGroup rdGrup = new ButtonGroup();
//				
//		constrWest.gridx = 10;
//		constrWest.gridy = 10;
//		
//		for(int i = 0; i < rdbBut.length; i++){
//			rdbBut[i] = new JRadioButton(strLabel[i]);
//		
//			rdGrup.add(rdbBut[i]);
//			
//			constrWest.gridy += 10;
//
//			pnlWest.add(rdbBut[i], constrWest);
//		}
//		
//		rdbBut[0].setSelected(true);
//		
//		
//		// Panel East
//		GridBagConstraints constrEast = new GridBagConstraints();
//		
//		final int Max = 10;
//		JCheckBox []  chckBox = new JCheckBox [Max]; // Array-Konstruktor
//		
//		constrEast.gridx = 10;
//		constrEast.gridy = 10;
//		for(int i = 0; i < chckBox.length; i++){
//			
//			chckBox[i] = new JCheckBox("" + i + "     ");
//			
//			constrEast.gridy += 10;
//			
//			pnlOst.add(chckBox[i], constrEast);
//		}
//		
//		
//		//
//		
//		
//		// Panel Mitte
//		
//		
//		
//		
//		// abschlie�ende Dinge
//		this.pack();
//		
//	}//end of construktor
//
//
//
//}// end of classpackage kalender;
//
//public class Test {
//
//}
