package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;
import javax.swing.JProgressBar;

public class Seconds30Panels extends JFrame {

    private JTextPane textPane;
    public static JButton resultsButton;
    public static String predefinedText = TextLoader.loadText("sampleText30sec.txt");
    public static int currentIndex = 0;
    public static int correctLetters = 0;
    public static int wrongLetters = 0;
    public static float result = 0;
    public static boolean endOfTest = false;
    
    Timer timer;
    private int totalTime = 30000;
    private int updateInterval = 10;
    
    
	
	public Seconds30Panels() {
		
		boolean isFirstCharacterEntered = true;
        
		UIDefaults defaults = UIManager.getDefaults();
		
		//Kod ustawiający początkowy automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize(this);

		int windowWidth = windowSize.getAutoWindowWidth();
	    int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(windowWidth, windowHeight);
        
        JPanel panel = new JPanel();
        
        // Button do wynikow
        resultsButton = new ResultsButton();
        resultsButton.setVisible(false);
       
        
        // Ustawienie text Pane
        
        textPane = new JTextPane();
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();
       
        textPane.setCaretColor(defaults.getColor("Caret"));

        // Add predefined text with initial coloring
        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, defaults.getColor("textText"));
        
        try {
            doc.insertString(doc.getLength(), predefinedText, defaultStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        
        textPane.addKeyListener(new KeyAdapter() {
        	private boolean firstCharacterTyped = true;
        	
            @Override
            public void keyPressed(KeyEvent e) {
            	if(!endOfTest) {
            		int keyCode = e.getKeyCode();
                    char typedChar = e.getKeyChar();
                    
                    if(firstCharacterTyped) {
                    	firstCharacterTyped = false;
                    	timer.start();
                    }

                    if (keyCode == KeyEvent.VK_ENTER) {
                        // Obsługa klawisza Enter
                        e.consume();
                        return;
                    } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
                        // Obsługa klawisza Backspace
                        if (currentIndex > 0) {
                            currentIndex--;
                            correctLetters--;
                            textPane.setCaretPosition(currentIndex);
                            applyCharacterColor(currentIndex, defaults.getColor("textText"));
                        }
                        e.consume();
                        return;
                    } else if (keyCode == KeyEvent.VK_SHIFT || keyCode == KeyEvent.VK_CONTROL || keyCode == KeyEvent.VK_ALT || keyCode == KeyEvent.VK_CAPS_LOCK || keyCode == KeyEvent.VK_TAB) {
                        e.consume();
                        return;
                    } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_HOME || keyCode == KeyEvent.VK_END || keyCode == KeyEvent.VK_PAGE_UP || keyCode == KeyEvent.VK_PAGE_DOWN) {
                        e.consume();
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
            	
                
            }
        });
        
        
        getContentPane().setLayout(new MigLayout("", "[100%]", "[94%][6%]"));

        panel.setLayout(new MigLayout("", "[10%][grow][10%]", "[18%][grow][15%][20%]"));
    
        JScrollPane scrollPane = new JScrollPane(textPane);
        panel.add(scrollPane, "cell 1 1,grow");
        
        // Dodanie panelu głównego do ramki
        
        getContentPane().add(panel, "cell 0 0,grow");

        panel.add(resultsButton, "cell 1 3,alignx center,aligny center");
        
        JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        getContentPane().add(progressBar, "cell 0 1,alignx center,aligny center,grow");
        
        timer = new Timer(updateInterval, new ActionListener() {
            private int elapsedTime = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime += updateInterval;
                int progress = 100 - (int) ((double) elapsedTime / totalTime * 100);
                progressBar.setValue(progress);

                if (elapsedTime >= totalTime) {
                    timer.stop();
                    endOfTest = true;
                    updateResult();
                    currentIndex = 0;
                    correctLetters = 0;
                    wrongLetters = 0;
                    result = 0;
                    textPane.setCaretPosition(0);
                    resultsButton.setVisible(true);
                }
            }
        });

        
        resultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResultsPanels();
				Seconds30Panels.this.dispose();
				endOfTest = false;	
			}
		});

        TstMenuBar menuBar = new TstMenuBar(true, this);
		setJMenuBar(menuBar);	
        
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
//        	resultsButton.setVisible(true);
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
        float accuracy = (float) correctLetters / currentIndex;
        return accuracy * 100; 
    }
    
	}
