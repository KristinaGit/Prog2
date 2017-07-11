package gui;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;

import controller.ActionAdapterCBCalFormat;
import controller.ActionAdapterRBMonatsblatt;
import controller.ActionAdapterRBJahreskalender;
import controller.ActionAdapterRBMonatmitFeiertagen;
import controller.ActionAdapterKalenderjahr;

public class Fenster {

	JFrame frame;
	Container pane;
	
	private JTextPane centerTextPane;
	
	ActionAdapterCBCalFormat aaCBCalFormat;
	ActionAdapterRBMonatsblatt aaRBMonatsblatt;
	ActionAdapterRBJahreskalender aaRBJahreskalender;
	ActionAdapterRBMonatmitFeiertagen aaRBMonatmitFeiertagen;
	ActionAdapterKalenderjahr aaKalenderjahr;
	
	public JComboBox<Integer> leftComboBoxJahre;
	public JComboBox<String> leftComboBoxMonate;
	
	public Fenster() {
		
		frame = new JFrame("Kalender");
		frame.setResizable( false);
		
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    pane = frame.getContentPane();
	    if (!( pane.getLayout() instanceof BorderLayout)) {
	    	System.out.println("Error: frame does not use BorderLayout");
	    }
	    
	    aaCBCalFormat = new ActionAdapterCBCalFormat();
	    aaRBMonatsblatt = new ActionAdapterRBMonatsblatt( this);
	    aaRBJahreskalender = new ActionAdapterRBJahreskalender( this);
	    aaRBMonatmitFeiertagen = new ActionAdapterRBMonatmitFeiertagen(this);
	    aaKalenderjahr = new ActionAdapterKalenderjahr( this);
	    		
	    createCenter();
	    createLeft();
	    createRight();
	    createMenu();
	    
	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	public void setCenterTextPane( String text) {
		centerTextPane.setText( text);
	}
    
	///////////////////////////////////////////////////////////////////////////////////
	private void createCenter() {
		
		// center 
		
		centerTextPane = new JTextPane();
		JScrollPane paneScrollPane = new JScrollPane(centerTextPane);
		paneScrollPane.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		paneScrollPane.setPreferredSize(new Dimension(500, 750));
		paneScrollPane.setMinimumSize(new Dimension(500, 750));
		
		centerTextPane.setText( "Monatsblatt");
		
		centerTextPane.setForeground( Color.red);
		
		pane.add( paneScrollPane, BorderLayout.CENTER);
	}
	
    ///////////////////////////////////////////////////////////////////////////////////
	private void createLeft() {
		
	    JPanel leftPanel = new JPanel( new GridBagLayout());
	    leftPanel.setPreferredSize(new Dimension(300, 750));
	    pane.add( leftPanel, BorderLayout.LINE_START);
	    GridBagConstraints leftGridBagC = new GridBagConstraints();
	    leftGridBagC.anchor = GridBagConstraints.WEST;
	    leftGridBagC.ipady = 10;
	    leftGridBagC.ipadx = 10;
	
	
	    JLabel leftText1 = new JLabel( "Bitte auswaehlen:");
	    leftGridBagC.gridy = 2;
	    leftGridBagC.gridx = 1;
	    leftPanel.add( leftText1, leftGridBagC);
	
	    JLabel leftText2 = new JLabel( "Monat:");
	    leftGridBagC.gridy = 3;
	    leftGridBagC.gridx = 1;
	    leftGridBagC.insets = new Insets( 0, 75, 0, 0);
	    leftPanel.add( leftText2, leftGridBagC);
	    leftGridBagC.insets = new Insets( 0,0,0,0);
	    
	    String[] monateStrings = { "Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August", "September",
	    						   "Oktober", "November", "Dezember"};
	    leftComboBoxMonate = new JComboBox<String>( monateStrings);
	    leftComboBoxMonate.setSelectedIndex(6);
	    leftGridBagC.gridy = 4;
	    leftGridBagC.gridx = 1;
	    leftPanel.add( leftComboBoxMonate, leftGridBagC);
	    
	    
	    JLabel leftText3 = new JLabel( "Jahr:");
	    leftGridBagC.gridy = 3;
	    leftGridBagC.gridx = 2;
	    leftGridBagC.insets = new Insets( 0, 50, 0, 0);
	    leftPanel.add( leftText3, leftGridBagC);
	    leftGridBagC.insets = new Insets( 0,0,0,0);
	    
	    ArrayList<Integer> jahreList = new ArrayList<Integer>();
	    for( Integer i = 1900; i < 2100; ++i) {
	    	jahreList.add( i);
	    }
	    leftComboBoxJahre = new JComboBox( jahreList.toArray());
	    leftComboBoxJahre.setSelectedIndex( 117);
	    leftGridBagC.gridy = 4;
	    leftGridBagC.gridx = 2;
	    leftPanel.add( leftComboBoxJahre, leftGridBagC);
	    
	    ImageIcon leftPicture = createImageIcon( "images/Juli.jpg");
	    if( leftPicture != null) {
	    	leftPicture.setImage( leftPicture.getImage().getScaledInstance( 290, 193, Image.SCALE_DEFAULT));
	    	JLabel leftLabelPicture = new JLabel("", leftPicture, JLabel.CENTER);
	    	leftGridBagC.gridy = 5;
		    leftGridBagC.gridx = 1;
		    leftGridBagC.gridwidth = 2;
	    	leftPanel.add(leftLabelPicture, leftGridBagC);
	    	leftGridBagC.gridwidth = 1;
	    }
	    
	    JLabel leftText4 = new JLabel( "Wochenbeginn mit So / Mo:");
	    leftGridBagC.gridy = 6;
	    leftGridBagC.gridx = 1;
	    leftPanel.add( leftText4, leftGridBagC);
	    
	    JCheckBox leftCheckBoxBeginn = new JCheckBox( "Format wechseln?");
	    leftCheckBoxBeginn.setSelected( false);
	    leftGridBagC.gridy = 7;
	    leftGridBagC.gridx = 1;
	    leftCheckBoxBeginn.addActionListener( aaCBCalFormat);
	    leftPanel.add( leftCheckBoxBeginn, leftGridBagC);
	}    
	    
    ///////////////////////////////////////////////////////////////////////////////////
    private void createRight() {
	    	
	    JPanel rightPanel = new JPanel( new GridBagLayout());
	    pane.add( rightPanel, BorderLayout.LINE_END);
	    GridBagConstraints leftGridBagC = new GridBagConstraints();
	    leftGridBagC.anchor = GridBagConstraints.WEST;
	    leftGridBagC.ipady = 10;
	    leftGridBagC.ipadx = 10;
	    
	    JLabel rightText = new JLabel( "Aktion waehlen:");
	    leftGridBagC.gridy = 2;
	    leftGridBagC.gridx = 1;
	    rightPanel.add( rightText, leftGridBagC);
	    
	    JRadioButton rightFirstButton = new JRadioButton( "Kalender fuer das ganze Jahr");
	    rightFirstButton.setActionCommand( "Kalender fuer das ganze Jahr");
	    rightFirstButton.setSelected(true);
	    rightFirstButton.addActionListener( aaKalenderjahr);
	    
	
	    JRadioButton rightSecondButton = new JRadioButton( "Monatsblatt");
	    rightSecondButton.setActionCommand( "Monatsblatt");
	    rightSecondButton.addActionListener( aaRBMonatsblatt);
	
	    JRadioButton rightThirdButton = new JRadioButton( "Jahreskalender mit Feiertagen");
	    rightThirdButton.setActionCommand( "Jahreskalender mit Feiertagen");
	    rightThirdButton.addActionListener( aaRBJahreskalender );
	
	    JRadioButton rightFourthButton = new JRadioButton( "Monatsblatt mit Feiertagen");
	    rightFourthButton.setActionCommand( "Monatsblatt mit Feiertagen");
	    rightFourthButton.addActionListener( aaRBMonatmitFeiertagen );
	
	 
	    //Group the radio buttons.
	    ButtonGroup rightButtoGroup = new ButtonGroup();
	    rightButtoGroup.add( rightFirstButton);
	    rightButtoGroup.add( rightSecondButton);
	    rightButtoGroup.add( rightThirdButton);
	    rightButtoGroup.add( rightFourthButton);
	    
	    leftGridBagC.gridy = 3;
	    leftGridBagC.gridx = 1;
	    rightPanel.add( rightFirstButton, leftGridBagC);
	    leftGridBagC.gridy = 4;
	    leftGridBagC.gridx = 1;
	    rightPanel.add( rightSecondButton, leftGridBagC);
	    leftGridBagC.gridy = 5;
	    leftGridBagC.gridx = 1;
	    rightPanel.add( rightThirdButton, leftGridBagC);
	    leftGridBagC.gridy = 6;
	    leftGridBagC.gridx = 1;
	    rightPanel.add( rightFourthButton, leftGridBagC);
    
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////
    private void createMenu() {
    
	    // Menu 
	    JMenuBar menuBar = new JMenuBar();
	    frame.add(menuBar, BorderLayout.PAGE_START);
	
	    JMenu mnDatei = new JMenu("Datei");
	    menuBar.add(mnDatei);
	
	    JMenuItem mntmNewMenuItem = new JMenuItem("Open");
	    mnDatei.add(mntmNewMenuItem);
	
	    JMenuItem mntmClose = new JMenuItem("Save");
	    mnDatei.add(mntmClose);
	
	    JMenuItem mntmOpen = new JMenuItem("Close");
	    mnDatei.add(mntmOpen);
	
	    JMenu mnKalender = new JMenu("Kalender");
	    menuBar.add(mnKalender);
	
	    JMenuItem mntmFeiertage = new JMenuItem("Feiertage");
	    mnKalender.add(mntmFeiertage);
	
	    JMenuItem mntmFeiertageOhneSosa = new JMenuItem("Feiertage, ohne So/Sa");
	    mnKalender.add(mntmFeiertageOhneSosa);
	
	    JMenu mnJahresplaner = new JMenu("Jahresplaner");
	    menuBar.add(mnJahresplaner);
	
	    JMenuItem mntmShow = new JMenuItem("Show");
	    mnJahresplaner.add(mntmShow);
	
	    JMenuItem mntmSave = new JMenuItem("Save");
	    mnJahresplaner.add(mntmSave);
	
	    JMenu mnLookFeel = new JMenu("Look & Feel");
	    menuBar.add(mnLookFeel);
	
	    JMenuItem mntmWindows = new JMenuItem("Windows");
	    mnLookFeel.add(mntmWindows);
	
	    JMenuItem mntmMetal = new JMenuItem("Metal");
	    mnLookFeel.add(mntmMetal);
	
	    JMenuItem mntmMotif = new JMenuItem("Motif");
	    mnLookFeel.add(mntmMotif);
	
	    JMenu mnInfo = new JMenu("Info");
	    menuBar.add(mnInfo);
	
	    JMenuItem mntmAutorin = new JMenuItem("Autorin");
	    mnInfo.add(mntmAutorin);
	
	    JMenuItem mntmHelp = new JMenuItem("Help");
	    mnInfo.add(mntmHelp);
    
    }


    // from https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    ImageIcon createImageIcon( String path) {
    	java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
    
}

