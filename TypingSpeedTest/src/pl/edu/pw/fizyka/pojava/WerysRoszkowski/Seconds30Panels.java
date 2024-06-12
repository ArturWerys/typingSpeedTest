package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTextPane;

import javax.swing.Timer;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

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
    private static final double textPaneScale = 0.016;
    public static JButton resultsButton;
    public static String predefinedText = TextSelectionWindow.textToLoad;
    public static int currentIndex = 0;
    public static int correctLetters = 0;
    public static boolean endOfTest = false;
    
    public static int endIndex = 0;
    
    Timer timer;
    private int totalTime = 30000;
    private int updateInterval = 10;
    
    
    public static long startTime = 0;

 // Zmienna do przechowywania czasu ostatniego naciśnięcia klawisza
    public static long lastKeyPressTime = System.currentTimeMillis();
    
    public static ArrayList<Long> letterTimes = new ArrayList<>();
    
    public static ArrayList<Long> fullElapsedTime = new ArrayList<>();
    
	
	public Seconds30Panels() {
		        
		UIDefaults defaults = UIManager.getDefaults();
		
		//Kod ustawiający początkowy automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize(this);

		int windowWidth = windowSize.getAutoWindowWidth();
	    int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(windowWidth, windowHeight);
        
        JPanel panel = new JPanel();
        
        resultsButton = new ResultsButton();
        resultsButton.setVisible(false);
         
        textPane = new JTextPane();
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();
       
        textPane.setCaretColor(defaults.getColor("Caret"));
        textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont( (float)(this.getWidth()*0.02)));

        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, defaults.getColor("Button.disabledText"));
        
        try {
            doc.insertString(doc.getLength(), predefinedText, defaultStyle);
            textPane.setCaretPosition(0);
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

                    if (currentIndex < predefinedText.length() && typedChar == predefinedText.charAt(currentIndex)) {
                        applyCharacterColor(currentIndex, defaults.getColor("textText"));
                        correctLetters++;
                        
                    	letterTimesCalculation();

                        
                    } else {
                        applyCharacterColor(currentIndex, new Color(199, 0, 0));
                        
                    	letterTimesCalculation();

                        
                    }
                    currentIndex++;

                    if (currentIndex < predefinedText.length()) {
                        textPane.setCaretPosition(currentIndex);
                    }
            	}
            	
                
            }
        });
        
        
        getContentPane().setLayout(new MigLayout("", "[100%]", "[94%][6%]"));

        panel.setLayout(new MigLayout("", "[10%][grow][10%]", "[18%][grow][15%]"));
    
        JScrollPane scrollPane = new JScrollPane(textPane);
        panel.add(scrollPane, "cell 1 1,grow");
        
        getContentPane().add(panel, "cell 0 0,grow");

        panel.add(resultsButton, "cell 1 2,alignx center,aligny center");
        
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
                    textPane.setCaretPosition(0);
                    resultsButton.setVisible(true);
                    
                    endIndex = currentIndex;
                }
            }
        });

        
        resultsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResultsPanels(calculateSeconds30Results());

				Seconds30Panels.this.dispose();
				resetTextPane();
			}
		});
        
        addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				int width = e.getComponent().getWidth();
		        
		        String fontFamily = CustomFonts.TEXT_PANE_FONT.getFamily();
		        int fontSize = (int) (width * textPaneScale);
		        textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont((float) fontSize));

		        StyledDocument doc = textPane.getStyledDocument();
		        int length = doc.getLength();
		        Color defaultTextColor = defaults.getColor("Button.disabledText");
		        for (int i = 0; i < length; i++) {
		            SimpleAttributeSet attrs = new SimpleAttributeSet(doc.getCharacterElement(i).getAttributes());
		            StyleConstants.setFontSize(attrs, fontSize);
		            StyleConstants.setFontFamily(attrs, fontFamily);
		            StyleConstants.setForeground(attrs, defaultTextColor);
		            doc.setCharacterAttributes(i, 1, attrs, false);
		        }
		        
		        repaint();
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				int width = e.getComponent().getWidth();
		        
		        String fontFamily = CustomFonts.TEXT_PANE_FONT.getFamily();
		        int fontSize = (int) (width * textPaneScale);
		        textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont((float) fontSize));

		        StyledDocument doc = textPane.getStyledDocument();
		        int length = doc.getLength();
		        Color defaultTextColor = defaults.getColor("Button.disabledText");
		        for (int i = 0; i < length; i++) {
		            SimpleAttributeSet attrs = new SimpleAttributeSet(doc.getCharacterElement(i).getAttributes());
		            StyleConstants.setFontSize(attrs, fontSize);
		            StyleConstants.setFontFamily(attrs, fontFamily);
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
    
		
		public static int[] calculateSeconds30Results() {
	    		    	
	        int wpm = StatsCalculationMethods.calculateSeconds30WPM(correctLetters);
	        int accuracy = StatsCalculationMethods.calculateAccuracy(correctLetters, endIndex);
	    	    	
	        return new int[] {(int) accuracy, (int) wpm};
	    }
		
        private boolean isFirstKeyPress = true;

		
		
	    public void letterTimesCalculation() {
	        // LOGIKA NOWEGO WYKRESU
	        
	        long currentTime = System.currentTimeMillis();

	        if (isFirstKeyPress) {
	            // Jeśli to pierwsze naciśnięcie klawisza, ustawiamy czas i zmieniamy flagę
	            lastKeyPressTime = currentTime;
	            isFirstKeyPress = false;
	            // Dodajemy 0 do fullElapsedTime jako początek
	            fullElapsedTime.add(0L);
	            return;
	        }
	        

	        
	        long timeDifference = currentTime - lastKeyPressTime;
	        
	        lastKeyPressTime = currentTime;

	        // Dodanie różnicy czasu do listy
	        letterTimes.add(timeDifference);
	        
	        // LOGIKA OSI X (czasu) full elapsed time
	        
	        if (fullElapsedTime.size() == 1 && fullElapsedTime.get(0) == 0) {
	        
	        	fullElapsedTime.set(0, timeDifference);
	        
	        } else {
	            long lastElapsedTime = fullElapsedTime.get(fullElapsedTime.size() - 1);
	            long newElapsedTime = lastElapsedTime + timeDifference;
	            fullElapsedTime.add(newElapsedTime);
	        }
	        
	                
	    }
	    
	   
		
	    public void resetTextPane() {
	    		    	    	
	    	correctLetters = 0;
	        currentIndex = 0;
	        endOfTest = false;
	        
	        
	        StyledDocument doc = textPane.getStyledDocument();
	        try {
				doc.remove(0, doc.getLength());
			} catch (BadLocationException e) {

				e.printStackTrace();
			} 
	        
	        String newPredefinedText = " ";
	        try {
	            doc.insertString(doc.getLength(), newPredefinedText, null);
	        } catch (BadLocationException e) {
	            e.printStackTrace();
	        }
	        
	        textPane.setCaretPosition(0); 
	        resultsButton.setVisible(false); 
	        
	        letterTimes.clear();
	        fullElapsedTime.clear();
	        
	        
	    }
}
