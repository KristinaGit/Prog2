package kalender;

import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.imageio.ImageIO;

public class KalenderJFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KalenderJFrame frame = new KalenderJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public KalenderJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		getContentPane().add(textPane);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{82, 35, 21, 0};
		gbl_panel.rowHeights = new int[]{60, 14, 14, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblBitteAuswhlen = new JLabel("Bitte auswaehlen");
		GridBagConstraints gbc_lblBitteAuswhlen = new GridBagConstraints();
		gbc_lblBitteAuswhlen.insets = new Insets(0, 0, 5, 5);
		gbc_lblBitteAuswhlen.gridx = 0;
		gbc_lblBitteAuswhlen.gridy = 1;
		panel.add(lblBitteAuswhlen, gbc_lblBitteAuswhlen);
		
		JLabel lblMonat = new JLabel("Monat");
		GridBagConstraints gbc_lblMonat = new GridBagConstraints();
		gbc_lblMonat.anchor = GridBagConstraints.EAST;
		gbc_lblMonat.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonat.gridx = 0;
		gbc_lblMonat.gridy = 2;
		panel.add(lblMonat, gbc_lblMonat);
		
		JLabel label = new JLabel("Jahr");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 2;
		gbc_label.gridy = 2;
		panel.add(label, gbc_label);
		
		
		// Array fuer dieJComboBox
        String monthList[] = {"Januar", "Februar",
            "Maerz", "April", "Mai",
            "Juni", "Juli", "August",
            "September", "Oktober", "November",
            "Dezember"};
        
		JComboBox comboBox = new JComboBox(monthList);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 3;
		panel.add(comboBox, gbc_comboBox);
		

		// Array fuer die JComboBox
        ArrayList<String> yearList = new ArrayList<String>();
        for(Integer i = 1990; i < 2030; i++) {
        	yearList.add(i.toString());
        }
        
		JComboBox comboBox_Year = new JComboBox( yearList.toArray());
		GridBagConstraints gbc_comboBox_Year = new GridBagConstraints();
		gbc_comboBox_Year.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Year.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Year.gridx = 2;
		gbc_comboBox_Year.gridy = 3;
		panel.add(comboBox_Year, gbc_comboBox_Year);
		
		 
 
        
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
