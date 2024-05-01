package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class Words30Panels extends JFrame{
    private JTextPane textPane;
    public static String predefinedText = "Miłość to nie pluszowy miś ani kwiaty";
    public static int currentIndex = 0;
    public static int correctLetters = 0;
    public static int wrongLetters = 0;
    public static float result = 0;
    public static boolean endOfTest = false;
	
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
        
        // Ustawienie text Pane
        
        textPane = new JTextPane();
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();
       
        
        textPane.setCaretColor(Color.red);
       

        // Add predefined text with initial coloring
        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, Color.BLACK);
        
        try {
            doc.insertString(doc.getLength(), predefinedText, defaultStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
                
        
        gbc.weightx = 1;
        gbc.weighty = 4; // Ten panel zajmie 2/3 dostępnej przestrzeni
//        centerPanel.add(textPane, gbc);
        
        textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();

                if (typedChar == '\n' || typedChar == '\b') {
                    e.consume(); // Zapobiega domyślnej akcji klawisza Enter i Backspace

                    if (typedChar == '\b' && currentIndex > 0) {
                        // Cofnij się o jeden znak, jeśli możliwe
                        currentIndex--;
                        correctLetters--;
                        textPane.setCaretPosition(currentIndex);
                        applyCharacterColor(currentIndex, Color.BLACK);
                    }

                    return;
                }

                if (currentIndex < predefinedText.length() && Character.toLowerCase(typedChar) == Character.toLowerCase(predefinedText.charAt(currentIndex))) {
                    // Prawidłowy znak wpisany, koloruj na zielono
                    applyCharacterColor(currentIndex, Color.GREEN);
                    correctLetters++;
                } else {
                    // Nieprawidłowy znak wpisany, koloruj na czerwono
                    applyCharacterColor(currentIndex, Color.RED);
                    wrongLetters++;
                }
                currentIndex++;

                if (currentIndex == predefinedText.length()) {
                	endOfTest = true;
                    updateResult();
                    textPane.setCaretPosition(0);
                    resultsButton.setVisible(true);

                }

                if (currentIndex < predefinedText.length()) {
                    textPane.setCaretPosition(currentIndex);
                }
              
            }
        });




        JScrollPane scrollPane = new JScrollPane(textPane);
        centerPanel.add(scrollPane,gbc);
        
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
	// Metody do Text Pane
    private void applyCharacterColor(int index, Color color) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, color);
        doc.setCharacterAttributes(index, 1, attributes, true);
    }
 // Method to update result and print it
    public static void updateResult() {
        if (endOfTest) {
            float result = calculateResult();
            System.out.println("Result: " + result);
            
            LocalTime currentTime = LocalTime.now();
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            
            try (Connection connection = DriverManager.getConnection("jdbc:h2:tstData", "artur", "")) {
                String insertQuery = "INSERT INTO wyniki (data, hour, `Correct words`) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setDate(1, new java.sql.Date(new java.util.Date().getTime())); 
                    statement.setString(2, formattedTime);
                    statement.setFloat(3, result);
                    statement.executeUpdate();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    // Method to calculate result
    public static float calculateResult() {
        if (predefinedText.length() == 0) {
            return 0; 
        }
        float accuracy = (float) correctLetters / predefinedText.length();
        return accuracy * 100; 
    }

}

