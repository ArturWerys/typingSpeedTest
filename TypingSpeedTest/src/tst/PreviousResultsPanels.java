package tst;

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
		SetWindowSize windowSize = new SetWindowSize();
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(windowWidth, windowHeight);
        
        JPanel panel = new JPanel();
        
        add(panel);
        
        JLabel label = new JLabel("Tutaj wyświetlą się wcześniejsze wyniki testów.");
     
        panel.add(label);
        
        
        JMenuBar menuBar;
		JMenu menu;
		
		JMenuItem goBack;

	    // Tworzenie paska menu
		menuBar = new JMenuBar();
	    
		//Dodawanie menu:
		menu = new JMenu("Menu");
		menuBar.add(menu);

	
		goBack = new JMenuItem("Powrót do ekranu startowego");
		menu.add(goBack);
		goBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
		        WelcomeWindowPanels welcomeWindowPanel = new WelcomeWindowPanels();
		        welcomeWindowPanel.setVisible(true);// Pokaż nowe okno
				dispose();
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
        
        
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
	

}
