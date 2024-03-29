package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.*;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Seconds30Panels extends JFrame {
	
	public static boolean isFirstCharacterEntered = false;
	public static long startTime;
	private static long elapsedTime;

	public Seconds30Panels() {
		super();		
		
		//Kod ustawiający automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize(this);
		int windowWidth = windowSize.getAutoWindowWidth();
	    int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(windowWidth, windowHeight);
        
        //
        JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.PAGE_START);
        northPanel.setBackground(ThemeColors.BACKGROUND);
        
        Dimension northPanelDim = new Dimension(windowWidth, (int)(0.18 * windowHeight));
        northPanel.setPreferredSize(northPanelDim);
        

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
        
        
        //Panel dolny i Timer - Mateusz i Artur
        
        JPanel southPanel = new JPanel(new BorderLayout());
        add(southPanel, BorderLayout.PAGE_END);
        southPanel.setBackground(ThemeColors.BACKGROUND);
        
        Dimension SouthPanelDim = new Dimension(windowWidth, (int)(0.2 * windowHeight));
        southPanel.setPreferredSize(SouthPanelDim);
        TimerSliderPanel slider = new TimerSliderPanel();
        southPanel.add(slider, BorderLayout.PAGE_END);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(ThemeColors.BACKGROUND);
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        
        ResultsButton resultsButton = new ResultsButton();
        resultsButton.setVisible(true);
        buttonPanel.add(resultsButton);
        
        resultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResultsPanels();
				Seconds30Panels.this.dispose();
				
				
			}
		});
        
             
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
                    }
                }
            }
        });
        
        
        textField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub		
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				if (!isFirstCharacterEntered) {
                    String text = textField.getText();
                    if (!text.isEmpty()) {
                        isFirstCharacterEntered = true;            
                        startTime = System.currentTimeMillis();
                        elapsedTime = System.currentTimeMillis() - startTime;
                    }
                }
				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
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
		        welcomeWindowPanel.setVisible(true);
				Seconds30Panels.this.dispose();
				isFirstCharacterEntered = false;
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
