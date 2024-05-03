package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class PreviousResultsPanels extends JFrame{
	
	public PreviousResultsPanels() {
		super();
		SetWindowSize windowSize = new SetWindowSize(this);
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(windowWidth, windowHeight);
        
        JPanel panel = new JPanel();
        
        add(panel);
        
        JLabel label = new JLabel("Tutaj wyświetlą się wcześniejsze wyniki testów.");
     
        panel.add(label);
        
        
        TstMenuBar menuBar = new TstMenuBar(true, this);
        setJMenuBar(menuBar);
        
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
	

}
