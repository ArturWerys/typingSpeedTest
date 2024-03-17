package tst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestWindowPanels extends JFrame{
	
	public TestWindowPanels() throws HeadlessException {
		super();
		setSize(Constants.windowWidth, Constants.windowHeight);
        
        //
        JPanel panelGorny = new JPanel();
        add(panelGorny, BorderLayout.PAGE_START);
        panelGorny.setBackground(Color.RED);
        
        Dimension panelGornyDim = new Dimension(Constants.windowWidth, (int)(0.18 * Constants.windowHeight));
        panelGorny.setPreferredSize(panelGornyDim);
        
        //
        JPanel panelDolny = new JPanel();
        add(panelDolny, BorderLayout.PAGE_END);
        panelDolny.setBackground(Color.BLUE);
        
        Dimension panelDolnyDim = new Dimension(Constants.windowWidth, (int)(0.2 * Constants.windowHeight));
        panelDolny.setPreferredSize(panelDolnyDim);
        
        //
        JPanel panelLewy = new JPanel();
        add(panelLewy, BorderLayout.WEST);
        panelLewy.setBackground(Color.YELLOW);
        
        Dimension panelLewyDim = new Dimension((int)(0.07 *Constants.windowWidth), Constants.windowHeight);
        panelLewy.setPreferredSize(panelLewyDim);
        
        //
        JPanel panelPrawy = new JPanel();
        add(panelPrawy, BorderLayout.EAST);
        panelPrawy.setBackground(Color.GREEN);
        
        Dimension panelPrawyDim = new Dimension((int)(0.07 *Constants.windowWidth), Constants.windowHeight);
        panelPrawy.setPreferredSize(panelPrawyDim);
        
       
        ///
        
        JPanel panelSrodkowy = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Wypełnienie komórek w obu kierunkach
        

        JPanel panelTekstuZbazyDanych = new JPanel();
        panelTekstuZbazyDanych.setBackground(Color.CYAN);
        gbc.weightx = 1;
        gbc.weighty = 4; // Ten panel zajmie 2/3 dostępnej przestrzeni
        panelSrodkowy.add(panelTekstuZbazyDanych, gbc);
        
        JLabel tekst = new JLabel("Tekst Tekst Tekst");
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

        setVisible(true);
    
       	}
	
}
