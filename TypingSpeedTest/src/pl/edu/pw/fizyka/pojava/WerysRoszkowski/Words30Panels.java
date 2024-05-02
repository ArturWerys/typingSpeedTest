package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

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
    public static String predefinedText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit";
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
        ResultsButton resultsButton = new ResultsButton();
        resultsButton.setVisible(false);
        
        // Ustawienie text Pane
        
        textPane = new JTextPane();
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();
       
        
        textPane.setCaretColor(Color.red);
       

        // Add predefined text with initial coloring
        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, defaults.getColor("textText"));
        
        try {
            doc.insertString(doc.getLength(), predefinedText, defaultStyle);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
//        centerPanel.add(textPane, gbc);
        
        textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char typedChar = e.getKeyChar();

                if (currentIndex < predefinedText.length() && typedChar == KeyEvent.VK_BACK_SPACE) {
                	e.consume();
                	System.out.println("Backspace");
                	
                        currentIndex--;
                       
                        applyCharacterColor(currentIndex, defaults.getColor("textText"));
                }
                else {
                    if (currentIndex < predefinedText.length() && typedChar == predefinedText.charAt(currentIndex)) {
                        // Correct character typed, color it green
                        applyCharacterColor(currentIndex, Color.GREEN);
                        correctLetters ++;
                
                    } else {
                        // Incorrect character typed, color it red
                        applyCharacterColor(currentIndex, Color.RED);
                        wrongLetters++;
                    }
                    currentIndex++;
                }
                
                if (currentIndex == predefinedText.length()) {
                	endOfTest = true;
                    updateResult();
                	textPane.setCaretPosition(0);
                    resultsButton.setVisible(true);
                }
                
                if(currentIndex < predefinedText.length()) {
                	textPane.setCaretPosition(currentIndex);
                }
                

            }
        });
        panel.setLayout(new MigLayout("", "[10%][grow][10%]", "[18%][grow][20%]"));
        JScrollPane scrollPane = new JScrollPane(textPane);
        panel.add(scrollPane, "cell 1 1,grow");
        
        // Dodanie panelu głównego do ramki
        
        getContentPane().add(panel);
        
        

        panel.add(resultsButton, "cell 1 2,alignx center,aligny center");
        

        
        
        resultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResultsPanels();
				Words30Panels.this.dispose();
				
				
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
            float result = calculateResult();
            System.out.println("Result: " + result);
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

