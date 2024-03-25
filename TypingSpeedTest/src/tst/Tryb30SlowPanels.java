package tst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Tryb30SlowPanels extends JFrame{
	
	public Tryb30SlowPanels() throws HeadlessException {
		super();		
		
		//Kod ustawiający automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize();
		int windowWidth = windowSize.getAutoWindowWidth();
	    int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(windowWidth, windowHeight);
        
        //
        JPanel panelGorny = new JPanel();
        add(panelGorny, BorderLayout.PAGE_START);
        panelGorny.setBackground(ThemeColors.BACKGROUND);
        
        Dimension panelGornyDim = new Dimension(windowWidth, (int)(0.18 * windowHeight));
        panelGorny.setPreferredSize(panelGornyDim);
        
        //
        JPanel panelDolny = new JPanel();
        add(panelDolny, BorderLayout.PAGE_END);
        panelDolny.setBackground(ThemeColors.BACKGROUND);
        
        Dimension panelDolnyDim = new Dimension(windowWidth, (int)(0.2 * windowHeight));
        panelDolny.setPreferredSize(panelDolnyDim);
        
        //
        JPanel panelLewy = new JPanel();
        add(panelLewy, BorderLayout.WEST);
        panelLewy.setBackground(ThemeColors.BACKGROUND);
        
        Dimension panelLewyDim = new Dimension((int)(0.07 * windowWidth), windowHeight);
        panelLewy.setPreferredSize(panelLewyDim);
        
        //
        JPanel panelPrawy = new JPanel();
        add(panelPrawy, BorderLayout.EAST);
        panelPrawy.setBackground(ThemeColors.BACKGROUND);
        
        Dimension panelPrawyDim = new Dimension((int)(0.07 * windowWidth), windowHeight);
        panelPrawy.setPreferredSize(panelPrawyDim);
        
       
        ///
        
        JPanel panelSrodkowy = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Wypełnienie komórek w obu kierunkach
        

        JPanel panelTekstuZbazyDanych = new JPanel();
        panelTekstuZbazyDanych.setBackground(ThemeColors.TEXT_FIELD_BACKGROUND);
        gbc.weightx = 1;
        gbc.weighty = 4; // Ten panel zajmie 2/3 dostępnej przestrzeni
        panelSrodkowy.add(panelTekstuZbazyDanych, gbc);
        
        JLabel tekst = new JLabel("Tryb 30 slow");
        panelTekstuZbazyDanych.add(tekst);
        
        
        
        /////
        
        JPanel panelDoWprowadzaniaTekstu = new JPanel();
        gbc.gridy = 1;
        gbc.weighty = 1; // Ten panel zajmie 1/3 dostępnej przestrzeni
        panelSrodkowy.add(panelDoWprowadzaniaTekstu, gbc);
        
        
        JTextField tekstDoWprowadzenia = new JTextField(20);
        panelDoWprowadzaniaTekstu.add(tekstDoWprowadzenia);
        
        
        
        // Dodanie panelu głównego do ramki
        
        add(panelSrodkowy);

        // Menu - Artur
        
        JMenuBar menuBar;
		JMenu menu;
		
		JMenuItem powrot;

	    // Tworzenie paska menu
		menuBar = new JMenuBar();
	    
		//Dodawanie menu:
		menu = new JMenu("Menu");
		menuBar.add(menu);

	
		powrot = new JMenuItem("Powrót do ekranu startowego");
		menu.add(powrot);
		powrot.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
		        WelcomeWindowPanels welcomeWindowPanel = new WelcomeWindowPanels();
		        welcomeWindowPanel.setVisible(true);// Pokaż nowe okno
				Tryb30SlowPanels.this.dispose();
			}
		});
		
		
    	setJMenuBar(menuBar);
    	
    	menu.addSeparator();
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	
			}
			
		});
		menu.add(exit);
        
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    
       	}
	
}
