//package kalender;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.UnsupportedLookAndFeelException;
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Image;
//import java.awt.HeadlessException;
//import javax.swing.BorderFactory;
//import javax.swing.ButtonGroup;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JRadioButton;
//import javax.swing.ImageIcon;
//
//
//
//
//public class Kalender {
//
//	JFrame kalFrame = new JFrame("Kalender");
//	kalFrame.setVisible(true);
//	kalFrame.setSize(600, 600);
//	kalFrame.setDefaultCloseOperation(JFrameEXIT_ON_CLOSE);
//
//}
//
//
//
//	// Attribute
//	
//	public static final String[] strActionLabel = { "Januar", "Februar", "März",
//			"April","Mai", "Juni",
//			"Juli", "August", "September"};
//	
//	private final String[] strLabel = { "Jan", "Feb", "Mrz", "Apr","Mai", "Jun",
//			"Jul", "Aug", "Sep"};
//	
//	private JRadioButton[] rdbBut = new JRadioButton[strLabel.length];
//	private ButtonGroup rdGrup = new ButtonGroup();
//	
//	private JLabel picLabel = new JLabel();
//	
//	// Konstruktor
//	public MeinFenster() throws HeadlessException {
//		this("Mein Fenster");
//	}
//
//
//	public MeinFenster(String title) throws HeadlessException {
//		super(title);
//		
//		// Einstellungen für das Fenster
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		//this.setVisible(true);
//		
//		
//		// Menüleiste hinzufügen
//		
//		// ContentPane holen
//		Container cpane = this.getContentPane();
//		
//		// Layout für die ContentPanel festlegen
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
//		// Panel mit Rändern versehen
//		pnlNord.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
//		pnlMitte.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
//		pnlSued.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1));
//		pnlWest.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
//		pnlOst.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
//		
//		
//		// Layout für die Panel
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
//		// Listener anbinden
//		butOk.addActionListener(new ActionAdapaterOK( this ));
//		
//		// Panel West
//		GridBagConstraints constrWest = new GridBagConstraints();
//				
//		constrWest.gridx = 10;
//		constrWest.gridy = 10;
//		
//		for(int i = 0; i < rdbBut.length; i++){
//			rdbBut[i] = new JRadioButton(strLabel[i]);
//		
//			rdGrup.add(rdbBut[i]);
//			
//			// ein ActionCommand mitgegeben
//			rdbBut[i].setActionCommand(strActionLabel[i]);
//			
//			constrWest.gridy += 10;
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
//			pnlOst.add(chckBox[i], constrEast);
//			
//			// ActionCommand 
//			chckBox[i].setActionCommand(""+(i+1));
//			
//			// Listener anbinden
//			chckBox[i].addActionListener(new ActionAdapaterChk(this));
//		}
//		
//		
//		//
//		
//		
//		// Panel Mitte mit Image
//		
//		ImageIcon icon = new ImageIcon("./meineBilder/Juni.jpg");
//	
//		icon.setImage(icon.getImage()
//				.getScaledInstance(-1, 400, Image.SCALE_DEFAULT));
//
//		picLabel.setIcon( icon);
//		
//		pnlMitte.add(picLabel);
//		
//		
//		// abschließende Dinge
//		this.pack();
//		
//	}//end of construktor
//
//
//	public void auslesenRadioButton(){
//		int i = 0;
//		
//		// den ausgewählten Radiobutton ermitteln
//		for( ; i < rdbBut.length; i++){
//			boolean sel = rdbBut[i].isSelected();
//			if( sel == true )
//				break;
//		}
//		
//		// Testausgabe
//		System.err.println("Debug der "
//		       + i +" button ausgewaehlt " 
//				+ rdbBut[i].getText()
//				+ " " + rdbBut[i].getActionCommand()
//				);
//		
//		
//		// Bild austauschen
//		
//		ImageIcon icon = new ImageIcon("./meineBilder/"+
//				rdbBut[i].getActionCommand() +".jpg");
//	
//		icon.setImage(icon.getImage()
//				.getScaledInstance(-1, 400, Image.SCALE_DEFAULT));
//
//		picLabel.setIcon( icon);
//		
//	}
//
//}// end of class
