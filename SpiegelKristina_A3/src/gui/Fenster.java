package gui;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import javax.swing.plaf.metal.*;

import java.util.Observer;
import java.util.Observable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import controller.ActionAdapterCBCalFormat;
import controller.ActionAdapterRBMonatsblatt;
import controller.ActionAdapterRBJahreskalender;
import controller.ActionAdapterRBMonatmitFeiertagen;
import controller.ActionAdapterKalenderjahr;
import controller.ActionAdapterMenuAutorin;
import controller.ActionAdapterMenuHilfe;
import controller.ActionAdapterLookFeel;
import controller.ActionAdapterMenuKalender;
import controller.ActionAdapterMenuJahresplaner;
import controller.ActionAdapterMenuDatei;
import controller.ActionAdapterAuswahlMonat;


public class Fenster {

	JFrame frame;
	Container pane;
	
	String[] monateStrings = { "Januar", "Februar", "Maerz", "April", "Mai", "Juni", "Juli", "August", "September",
			   "Oktober", "November", "Dezember"};
	
	//Membervariable fuer Singleton
	private static Fenster instance = null;
	
	//Methode um Fenster zum Singleton zu machen
	public static Fenster getInstance(){
		
		if( instance == null){
			instance = new Fenster();
		}
		return instance;
	}
	
	
	private JTextPane centerTextPane;
	
	ActionAdapterCBCalFormat aaCBCalFormat;
	ActionAdapterRBMonatsblatt aaRBMonatsblatt;
	ActionAdapterRBJahreskalender aaRBJahreskalender;
	ActionAdapterRBMonatmitFeiertagen aaRBMonatmitFeiertagen;
	ActionAdapterKalenderjahr aaKalenderjahr;
	ActionAdapterMenuAutorin aaMenuAutorin;
	ActionAdapterMenuHilfe aaMenuHilfe;
	ActionAdapterLookFeel aaLookFeel;
	ActionAdapterMenuKalender aaMenuKalender;
	ActionAdapterMenuJahresplaner aaMenuJahresplaner;
	ActionAdapterMenuDatei aaMenuDatei;
	ActionAdapterAuswahlMonat aaAuswahlMonat;
	
	public JMenuItem mntmMenuDateiOpen;
	public JMenuItem mntmMenuDateiSave;
	public JMenuItem mntmMenuDateiClose;
	
	public JMenuItem mntmJahresplanerShow;
	public JMenuItem mntmJahresplanerSave;
	
	public JMenuItem mntmFeiertage;
	public JMenuItem mntmFeiertageOhneSosa;
	
	public JMenuItem mntmWindows;
	public JMenuItem mntmMetal;
	public JMenuItem mntmMotif;
	
	StyledDocument doc = null;
	public StyledDocument jpdoc = null;
	
	public JComboBox<Integer> leftComboBoxJahre;
	public JComboBox<String> leftComboBoxMonate;
 	
	ArrayList<ImageIcon> leftPictureImages;
	ImageIcon leftPicture;
	JLabel leftLabelPicture;
	
	
	// Constructor, Singleton
	private Fenster() {
		
		
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
	    aaMenuAutorin = new ActionAdapterMenuAutorin();
	    aaMenuHilfe = new ActionAdapterMenuHilfe();
	    aaLookFeel = new ActionAdapterLookFeel();
	    aaMenuKalender = new ActionAdapterMenuKalender();
	    aaMenuJahresplaner = new ActionAdapterMenuJahresplaner();
	    aaMenuDatei = new ActionAdapterMenuDatei();
	    aaAuswahlMonat = new ActionAdapterAuswahlMonat();
	    
	    createCenter();
	    createLeft();
	    createRight();
	    createMenu();
	    
	    //Display the window.
	    frame.pack();
	    frame.setVisible(true);
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////
	public void setCenterTextPaneFormatted( String text, boolean highlighted, boolean append) {
		
		int offset = 0;
		if( append) {
			offset = doc.getLength();
		}
		else {
			try {
				doc.remove( 0, doc.getLength());
			}
			catch (BadLocationException ex) {
					// handle exception
			}
		}
		
		try {
			if( ! highlighted) {
				doc.insertString( offset, text, doc.getStyle( "regular"));
			}
			else {
				doc.insertString( offset, text, doc.getStyle( "highlighted"));
			}
		}
		catch (BadLocationException ex) {
			// handle exception
		}
		
	}
    
	///////////////////////////////////////////////////////////////////////////////////
	private void createCenter() {
		
		// center 
		
		centerTextPane = new JTextPane();
		JScrollPane paneScrollPane = new JScrollPane(centerTextPane);
		paneScrollPane.setPreferredSize(new Dimension(500, 750));
		paneScrollPane.setMinimumSize(new Dimension(500, 750));
		
		doc = centerTextPane.getStyledDocument();
		
		Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		
		Style regular = doc.addStyle( "regular", defaultStyle);
		StyleConstants.setFontFamily( regular, "Courier");
		
		Style highlighted = doc.addStyle( "highlighted", defaultStyle);
		StyleConstants.setFontFamily( highlighted, "Courier");
		StyleConstants.setForeground( highlighted, Color.RED);
		
		String text = "Bitte treffen Sie eine Auswahl.";
		try {
			doc.insertString( 0, text, doc.getStyle( "regular"));
		}
		catch (BadLocationException ex) {
			// TODO handle exception
		}
		
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
	    
	    Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH);
	    int year = cal.get(Calendar.YEAR);
	    
	    leftComboBoxMonate = new JComboBox<String>( monateStrings);
	    leftComboBoxMonate.setSelectedIndex( month);
	    leftGridBagC.gridy = 4;
	    leftGridBagC.gridx = 1;
	    leftPanel.add( leftComboBoxMonate, leftGridBagC);
	    leftComboBoxMonate.addActionListener( aaAuswahlMonat);
	    
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
	    leftComboBoxJahre.setSelectedIndex( year - 1900);
	    leftGridBagC.gridy = 4;
	    leftGridBagC.gridx = 2;
	    leftPanel.add( leftComboBoxJahre, leftGridBagC);
	    
	    leftPictureImages = new ArrayList<ImageIcon>();
	    for( int i = 0; i < 12; ++i) {
	    	String fname = "images/" + monateStrings[i] + ".jpg";
	    	leftPictureImages.add( createImageIcon( fname));
	    }
	    
	    leftPicture = new ImageIcon();
	    if( leftPicture != null) {
	        leftLabelPicture = new JLabel("", leftPictureImages.get(leftComboBoxMonate.getSelectedIndex()), JLabel.CENTER);
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
	
	    mntmMenuDateiOpen = new JMenuItem("Open");
	    mntmMenuDateiOpen.addActionListener( aaMenuDatei);
	    mnDatei.add(mntmMenuDateiOpen);
	
	    mntmMenuDateiSave = new JMenuItem("Save");
	    mntmMenuDateiSave.addActionListener( aaMenuDatei);
	    mnDatei.add(mntmMenuDateiSave);
	
	    mntmMenuDateiClose = new JMenuItem("Close");
	    mntmMenuDateiClose.addActionListener( aaMenuDatei);
	    mnDatei.add(mntmMenuDateiClose);
	
	    JMenu mnKalender = new JMenu("Kalender");
	    menuBar.add(mnKalender);
	
	    mntmFeiertage = new JMenuItem("Feiertage");
	    mntmFeiertage.addActionListener( aaMenuKalender);
	    mnKalender.add(mntmFeiertage);
	
	    mntmFeiertageOhneSosa = new JMenuItem("Feiertage, ohne So/Sa");
	    mntmFeiertageOhneSosa.addActionListener( aaMenuKalender);
	    mnKalender.add(mntmFeiertageOhneSosa);
	
	    JMenu mnJahresplaner = new JMenu("Jahresplaner");
	    menuBar.add(mnJahresplaner);
	
	    mntmJahresplanerShow = new JMenuItem("Show");
	    mntmJahresplanerShow.addActionListener( aaMenuJahresplaner);
	    mnJahresplaner.add(mntmJahresplanerShow);
	
	    mntmJahresplanerSave = new JMenuItem("Save");
	    mntmJahresplanerSave.addActionListener( aaMenuJahresplaner);
	    mnJahresplaner.add(mntmJahresplanerSave);
	
	    JMenu mnLookFeel = new JMenu("Look & Feel");
	    menuBar.add(mnLookFeel);
	    
	    mntmWindows = new JMenuItem("Windows");
	    mntmWindows.addActionListener( aaLookFeel);
	    mnLookFeel.add(mntmWindows);
	
	    mntmMetal = new JMenuItem("Metal");
	    mntmMetal.addActionListener( aaLookFeel);
	    mnLookFeel.add(mntmMetal);
	
	    mntmMotif = new JMenuItem("Motif");
	    mntmMotif.addActionListener( aaLookFeel);
	    mnLookFeel.add(mntmMotif);
	
	    JMenu mnInfo = new JMenu("Info");
	    menuBar.add(mnInfo);
	
	    JMenuItem mntmAutorin = new JMenuItem("Autorin");
	    mntmAutorin.addActionListener( aaMenuAutorin);
	    mnInfo.add(mntmAutorin);
	
	    JMenuItem mntmHelp = new JMenuItem("Help");
	    mntmHelp.addActionListener( aaMenuHilfe);
	    mnInfo.add(mntmHelp);
    
    }
    
    public void showOptionFeiertage( String feiertage) {
    	JOptionPane.showMessageDialog(frame, feiertage);
    }
    
    public void showOptionPaneMenuAutorin() {
    	JOptionPane.showMessageDialog(frame, "Kristina Spiegel \nMatrikelnummer: 553423 \nErstelldatum: 14/07/2017 \nVersion: 0.01 (alpha)");
    }    

    public void showOptionPaneMenuHilfe() {
    	JOptionPane.showMessageDialog(frame, "Bitte gesunden Menschenverstand benutzen.");
    }

    public void setLookFeel( int mode) {
    	
    	String modestr = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		if( 1 == mode) {
			modestr = "javax.swing.plaf.metal.MetalLookAndFeel";
		}
		else if( 2 == mode) {
			modestr = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		}

    	try {
    		UIManager.setLookAndFeel( modestr);
    	}
    	catch (ClassNotFoundException e) {
            System.err.println("Couldn't find class for specified look and feel.");
            System.err.println("Did you include the L&F library in the class path?");
            System.err.println("Using the default look and feel.");
        } 
        catch (UnsupportedLookAndFeelException e) {
            System.err.println("Can't use the specified look and feel on this platform.");
            System.err.println("Using the default look and feel.");
        } 
        
        catch (Exception e) {
            System.err.println("Couldn't get specified look and feel for some reason.");
            System.err.println("Using the default look and feel.");
            e.printStackTrace();
        }
    	
    	SwingUtilities.updateComponentTreeUI(frame);
    	frame.pack();
	    frame.setVisible(true);
    }
    
    // TODO
    public int showJahresplanerVon() {
    	
    	String ret = (String) JOptionPane.showInputDialog( frame, "'von' Monat auswaehlen: ", "Test",
    													 JOptionPane.QUESTION_MESSAGE,
    	                    							 null,
    	                    							 monateStrings,
    	                    							"2017");

    	for( int m = 0; m < 12; ++m) {
    		if( monateStrings[m].equals( ret)) {
    			return m;
    		}
    	}
    	
    	assert( false);
    	return -1;
    }
    
    // TODO
    public int showJahresplanerBis() {
    	
    	String ret = (String) JOptionPane.showInputDialog( frame, "'bis' Monat auswaehlen: ", "Test",
    													 JOptionPane.QUESTION_MESSAGE,
    	                    							 null,
    	                    							 monateStrings,
    	                    							"2017");

    	for( int m = 0; m < 12; ++m) {
    		if( monateStrings[m].equals( ret)) {
    			return m;
    		}
    	}
    	
    	assert( false);
    	return -1;
    }
    
    // TODO
    public void showJahresplaner( String jahresplanerText) {
    	
    	JFrame frameJahresplaner = new JFrame("Jahresplaner");
    	frameJahresplaner.setResizable( false);
		
	    Container paneJahresplaner = frameJahresplaner.getContentPane();
	    if (!( paneJahresplaner.getLayout() instanceof BorderLayout)) {
	    	System.out.println("Error: frame does not use BorderLayout");
	    }

	    JTextPane jpTextPane = new JTextPane();
	    jpTextPane.setPreferredSize(new Dimension(3000, 750));
	    jpTextPane.setMinimumSize(new Dimension(3000, 750));
		JScrollPane jppaneScrollPane = new JScrollPane( jpTextPane);
		jppaneScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jppaneScrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jppaneScrollPane.setPreferredSize(new Dimension(1400, 750));
		jppaneScrollPane.setMinimumSize(new Dimension(1400, 750));
    	
		jpdoc = jpTextPane.getStyledDocument();
		
		Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		
		Style jpregular = jpdoc.addStyle( "regular", defaultStyle);
		StyleConstants.setFontFamily( jpregular, "Courier");
		
		try {
			jpdoc.insertString( 0, jahresplanerText, jpdoc.getStyle( "regular"));
		}
		catch (BadLocationException ex) {
			
			// handle exception
		}
		
		paneJahresplaner.add( jppaneScrollPane, BorderLayout.CENTER);
		
	    //Display the window.
		frameJahresplaner.pack();
		frameJahresplaner.setVisible(true);
    }
    
    // TODO
    public void saveJahresplaner() {
    	
    	assert( jpdoc != null);
    	
    	JFileChooser fileChooser = new JFileChooser();
    	if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
    		
    		File file = fileChooser.getSelectedFile();
    		String filename = file.getAbsolutePath();
    	   
    		RTFEditorKit kit = new RTFEditorKit( );
    	            
    		if(!filename.endsWith(".rtf")){
	            filename += ".rtf";
	        }
	        
	        BufferedOutputStream out = null;
	        try {
	            out = new BufferedOutputStream(new FileOutputStream(filename));
	                
	            // Stream schreiben
	            try {
	                kit.write(out, jpdoc, 0, jpdoc.getLength());
	            }
	            catch (BadLocationException e) {
	                e.printStackTrace();
	            }

	        } 
	        catch (IOException e) {
	            System.err.println( "Konnte Datei nicht erstellen" );
	        }
	        finally{
	            try {                  
	                out.close(); // Stream schließen
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
    	}
    }
    
    // TODO
    public void openFileCenterPane() {
    	
    	assert( jpdoc != null);
    	
    	JFileChooser fileChooser = new JFileChooser();
    	if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    		File file = fileChooser.getSelectedFile();
    		String filename = file.getAbsolutePath();
    		
    		if( filename.endsWith(".rtf")){
    			
    			RTFEditorKit kit = new RTFEditorKit( );
    			
    			try {
    				BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename));
    				kit.read( in, doc, 0);
    			}
    			catch (Exception e) {
    				System.err.println("Failed to read file.");
    			}
    			
	        }
    		else if( filename.endsWith(".txt") || filename.endsWith(".csv")) {
    			
    			try {
    				FileReader in = new FileReader(filename);
    			    BufferedReader br = new BufferedReader(in);

    			    // clear center text pane
    			    this.setCenterTextPaneFormatted( "", false, false);
    			    
    			    String line;
    			    while( (line = br.readLine()) != null) {
    			        this.setCenterTextPaneFormatted( line, false, true);
    			        this.setCenterTextPaneFormatted( "\n", false, true);
    			    }
    			    in.close();
    			}
    			catch (Exception e) {
    				System.err.println("Failed to read file.");
        		}
    		}
    		else {
    			JOptionPane.showMessageDialog(frame, "Ungueltiges Dateiformat.");
    		}
    		
    	}
    }
    
    // TODO
    public void saveCenterPane() {
    	
    	JFileChooser fileChooser = new JFileChooser();
    	if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
    		
    		File file = fileChooser.getSelectedFile();
    		String filename = file.getAbsolutePath();
    	   
    		RTFEditorKit kit = new RTFEditorKit( );
    	            
    		if(!filename.endsWith(".rtf")){
	            filename += ".rtf";
	        }
	        
	        BufferedOutputStream out = null;
	        try {
	            out = new BufferedOutputStream(new FileOutputStream(filename));
	                
	            // Stream schreiben
	            try {
	                kit.write(out, doc, 0, doc.getLength());
	            }
	            catch (BadLocationException e) {
	                e.printStackTrace();
	            }

	        } 
	        catch (IOException e) {
	            System.err.println( "Konnte Datei nicht erstellen" );
	        }
	        finally{
	            try {                  
	                out.close(); 
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
    	}
    }
    
    // TODO
    public void setImageMonth() {
    	System.out.println("Handling");
    	leftLabelPicture.setIcon( leftPictureImages.get(leftComboBoxMonate.getSelectedIndex()));
    }
    
    // TODO
    public void terminate() {
    	
    	frame.setVisible(false);
    	frame.dispose();
    }
    
    // from https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    ImageIcon createImageIcon( String path) {
    	java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			ImageIcon icon = new ImageIcon(imgURL);
			icon.setImage( icon.getImage().getScaledInstance( 290, 193, Image.SCALE_DEFAULT));
			return icon;
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
    
}

