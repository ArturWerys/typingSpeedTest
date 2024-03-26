package tst;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Words30Panels extends JFrame{
	
	public Words30Panels() throws HeadlessException {
		super();		
		
		//Kod ustawiający automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize();
		int windowWidth = windowSize.getAutoWindowWidth();
	    int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(windowWidth, windowHeight);
        
        //
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.PAGE_START);
        northPanel.setBackground(ThemeColors.BACKGROUND);
        
        Dimension panelGornyDim = new Dimension(windowWidth, (int)(0.18 * windowHeight));
        northPanel.setPreferredSize(panelGornyDim);
        
        //
        JPanel southPanel = new JPanel();
        add(southPanel, BorderLayout.PAGE_END);
        southPanel.setBackground(ThemeColors.BACKGROUND);
        
        Dimension southPanelDim = new Dimension(windowWidth, (int)(0.2 * windowHeight));
        southPanel.setPreferredSize(southPanelDim);
        
        //
        JPanel westPanel = new JPanel();
        add(westPanel, BorderLayout.WEST);
        westPanel.setBackground(ThemeColors.BACKGROUND);
        
        Dimension westPanelDim = new Dimension((int)(0.07 * windowWidth), windowHeight);
        westPanel.setPreferredSize(westPanelDim);
        
        //
        JPanel eastPanel = new JPanel();
        add(eastPanel, BorderLayout.EAST);
        eastPanel.setBackground(ThemeColors.BACKGROUND);
        
        Dimension eastPanelDim = new Dimension((int)(0.07 * windowWidth), windowHeight);
        eastPanel.setPreferredSize(eastPanelDim);
        
       
        ///
        
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Wypełnienie komórek w obu kierunkach
        
        
        DrawDatabaseText dataBaseTextPanel = new DrawDatabaseText();
        gbc.weightx = 1;
        gbc.weighty = 4; // Ten panel zajmie 2/3 dostępnej przestrzeni
        centerPanel.add(dataBaseTextPanel, gbc);
        
        
        /////
        
        JPanel inputPanel = new JPanel();
        gbc.gridy = 1;
        gbc.weighty = 1; // Ten panel zajmie 1/3 dostępnej przestrzeni
        centerPanel.add(inputPanel, gbc);
        
        // Button do wynikow
        ResultsButton resultsButton = new ResultsButton();
        southPanel.add(resultsButton);
        resultsButton.setVisible(false);
        
        resultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResultsPanels();
				Words30Panels.this.dispose();
				
				
			}
		});
        
       

 
        // Co to robi?
        
        JTextField textField = new JTextField(20);
        inputPanel.add(textField);
        
        textField.addKeyListener(new KeyAdapter() {
            int currentWordIndex = 0;

            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                if (Character.isWhitespace(c) || c == KeyEvent.VK_ENTER) {
                	
                    String enteredWord = textField.getText().trim();
         
                    if (currentWordIndex < dataBaseTextPanel.wordsArray.length) {
                        if (enteredWord.equals(dataBaseTextPanel.wordsArray[currentWordIndex])) {
                            System.out.println("Słowa są takie same: " + currentWordIndex);
                            dataBaseTextPanel.setWordColor(currentWordIndex, Color.green);
                            
                        } else {
                            System.out.println("Słowa się różnią: " + currentWordIndex);
                            dataBaseTextPanel.setWordColor(currentWordIndex, Color.red);

                        }
                        currentWordIndex++;
                        textField.setText("");
                    }
                    if(currentWordIndex == dataBaseTextPanel.wordsArray.length) {
                    	System.out.println("Koniec testu");
                        resultsButton.setVisible(true);
                        southPanel.repaint();
                    }
                    
                      
                }
            }
        });


        // Dodanie panelu głównego do ramki
        
        add(centerPanel);

        // Menu - Artur
        
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
				Words30Panels.this.dispose();
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
