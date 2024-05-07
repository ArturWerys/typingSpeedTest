package pl.edu.pw.fizyka.pojava.WerysRoszkowski;


import java.awt.Color;

import java.awt.HeadlessException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import net.miginfocom.swing.MigLayout;


public class Words30Panels extends JFrame{
    private JTextPane textPane;
    private ResultsButton resultsButton;
    public static String predefinedText = TextLoader.loadText("SampleText.txt");
    public static int currentIndex = 0;
    public static int correctLetters = 0;
    public static int wrongLetters = 0;
    public static float result = 0;
    public static boolean endOfTest = false;
    
	public Words30Panels() throws HeadlessException {
		super();
		
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
        textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont( (float)(this.getWidth()*0.02)));
        
        textPane.setCaretColor(defaults.getColor("Caret"));
        // Add predefined text with initial coloring
        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, defaults.getColor("Button.disabledText"));

        
        try {
            doc.insertString(doc.getLength(), predefinedText, defaultStyle);
            textPane.setCaretPosition(0);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        
        textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                char typedChar = e.getKeyChar();

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
                        applyCharacterColor(currentIndex, defaults.getColor("Button.disabledText"));
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
                    applyCharacterColor(currentIndex, defaults.getColor("textText"));
                    correctLetters++;
                } else {
                    applyCharacterColor(currentIndex, new Color(199, 0, 0));
                    wrongLetters++;
                }
                currentIndex++;

                if (currentIndex == (predefinedText.length()-1)) {
                    endOfTest = true;
                    updateResult();
                    resultsButton.setVisible(true);
                }

                if (currentIndex < predefinedText.length()) {
                    textPane.setCaretPosition(currentIndex);
                }
            }
        });
        

        addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				int width = e.getComponent().getWidth();
		        int fontSize = (int) (width * 0.02);
		        textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont((float) fontSize));

		        StyledDocument doc = textPane.getStyledDocument();
		        int length = doc.getLength();
		        Color defaultTextColor = defaults.getColor("Button.disabledText");
		        for (int i = 0; i < length; i++) {
		            SimpleAttributeSet attrs = new SimpleAttributeSet(doc.getCharacterElement(i).getAttributes());
		            StyleConstants.setFontSize(attrs, fontSize);
		            StyleConstants.setForeground(attrs, defaultTextColor);
		            doc.setCharacterAttributes(i, 1, attrs, false);
		        }
		        repaint();
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

        panel.setLayout(new MigLayout("", "[10%][grow][10%]", "[18%][grow][15%][20%]"));
    
        JScrollPane scrollPane = new JScrollPane(textPane);
        panel.add(scrollPane, "cell 1 1,grow");
        
        // Dodanie panelu głównego do ramki
        
        getContentPane().add(panel);

        panel.add(resultsButton, "cell 1 3,alignx center,aligny center");
        
        resultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResultsPanels();
				Words30Panels.this.dispose();
				resetTextPane();
				
			}
		});

        TstMenuBar menuBar = new TstMenuBar(true, endOfTest, this);
		setJMenuBar(menuBar);	
        
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);      
       	}
	
    private void applyCharacterColor(int index, Color color) {
        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet attributes = new SimpleAttributeSet();
        StyleConstants.setForeground(attributes, color);
        doc.setCharacterAttributes(index, 1, attributes, true);
    }
    
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


    public static float calculateResult() {
        if (predefinedText.length() == 0) {
            return 0; 
        }
        float accuracy = (float) correctLetters / predefinedText.length();
        return accuracy * 100; 
    }
    
    public void resetTextPane() {
    	
    	correctLetters = 0;
        currentIndex = 0;
        correctLetters = 0;
        wrongLetters = 0;
        result = 0;
        endOfTest = false;
        
        StyledDocument doc = textPane.getStyledDocument();
        try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e) {

			e.printStackTrace();
		} 
        
        String newPredefinedText = TextLoader.loadText("SampleText.txt");
        try {
            doc.insertString(doc.getLength(), newPredefinedText, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        textPane.setCaretPosition(0); 
        resultsButton.setVisible(false); 
    }

}

