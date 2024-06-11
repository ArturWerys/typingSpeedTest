package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class TextSelectionWindow extends JFrame {
    
    private static final double confirmButtonScale = 0.014;
    private static final double treeTextScale = 0.012;
    private static final double textPaneScale = 0.016;
    
    public static String textToLoad;
    
    public TextSelectionWindow() {
        super();
        
        UIDefaults defaults = UIManager.getDefaults();
     
        SetWindowSize windowSize = new SetWindowSize(this);
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(new Dimension(windowWidth, windowHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        TstMenuBar menuBar = new TstMenuBar(true, true, this);
        setJMenuBar(menuBar);
        getContentPane().setLayout(new MigLayout("", "[2%][grow][2%]", "[2%][83%,grow][2%][10%,grow][2%]"));
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, "cell 1 1,grow");
        panel.setLayout(new MigLayout("", "[45%,grow][2%][45%,grow]", "[grow]"));
        
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "cell 0 0,grow");
        
        JTextPane textPane = new JTextPane();
        panel.add(textPane, "cell 2 0,grow");
        
        textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont(12));

        
        textPane.setEditable(false);
       
        JPanel panel_1 = new JPanel();
        getContentPane().add(panel_1, "cell 1 3,grow");
        panel_1.setLayout(new MigLayout("", "[40%][grow][40%]", "[grow]"));
        
        JButton btnConfirm = new JButton("Potwierdź");
        panel_1.add(btnConfirm, "cell 1 0,alignx center,aligny center,grow");
        
        btnConfirm.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(WelcomeWindow.words30choosen == true) {
                    new Words30Panels();
                    TextSelectionWindow.this.dispose();
                }
                else{
                    new Seconds30Panels();
                    TextSelectionWindow.this.dispose();
                }
            }
        });
        
        // Utwórz korzeń drzewa
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        // Dodaj pierwszą nadrzędną pozycję
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("Tekst klasyczny");
        root.add(parent1);

        // Dodaj podlistę do pierwszej nadrzędnej pozycji
        DefaultMutableTreeNode nadNiemnemNode = new DefaultMutableTreeNode("Nad Niemnem");
        parent1.add(nadNiemnemNode);
        parent1.add(new DefaultMutableTreeNode("Lalka"));

        // Dodaj drugą nadrzędną pozycję
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("Tekst losowy");
        root.add(parent2);

        // Dodaj podlistę do drugiej nadrzędnej pozycji
        parent2.add(new DefaultMutableTreeNode("Najcześciej występujące polskie słowa"));
        parent2.add(new DefaultMutableTreeNode("Najcześciej występujące angielskie słowa"));

        // Utwórz model drzewa
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);
        
        // Ukryj korzeń drzewa
        tree.setRootVisible(false);
        
        // Dodaj drzewo do JScrollPane
        scrollPane.setViewportView(tree);
        
        // Ustawienie text Pane
        textPane.setEditable(false);
        StyledDocument doc = textPane.getStyledDocument();
        textPane.setCaretColor(defaults.getColor("Caret"));
        textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont((float)(this.getWidth() * 0.02)));

        Style defaultStyle = textPane.getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setForeground(defaultStyle, defaults.getColor("Button.disabledText"));
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    String nodeText = selectedNode.getUserObject().toString();
                    
                    // Clear the text pane before inserting new text
                    textPane.setText("");
                    
                    if(WelcomeWindow.words30choosen == true) {
                    	
                        if (nodeText.equals("Nad Niemnem")) {
                            textToLoad = TextLoader.loadText("nadNiemnem.txt");
                            try {
                                doc.insertString(doc.getLength(), textToLoad, defaultStyle);
                                textPane.setCaretPosition(0);
                                textPane.repaint();
                            } catch (BadLocationException ee) {
                                ee.printStackTrace();
                            }
                        } else if (nodeText.equals("Lalka")) {
                            textToLoad = TextLoader.loadText("lalka30slow.txt");
                            try {
                                doc.insertString(doc.getLength(), textToLoad, defaultStyle);
                                textPane.setCaretPosition(0);
                                textPane.repaint();
                            } catch (BadLocationException ee) {
                                ee.printStackTrace();
                            }
                        } else if (nodeText.equals("Najcześciej występujące polskie słowa")) {
                            textToLoad = TextLoader.loadText("polish1k.txt");

                            try {
                                Path path = Paths.get("lib/Texts/polish1k.txt");
                                List<String> words = Files.readAllLines(path);

                                // Wybierz losowe 30 słów
                                String randomWords = getRandomWords(words, 30);

                                // Wyświetl wybrane słowa
                                try {
                                    doc.insertString(doc.getLength(), randomWords, defaultStyle);
                                    textPane.setCaretPosition(0);
                                    textPane.repaint();
                                } catch (BadLocationException ee) {
                                    ee.printStackTrace();
                                }
                                
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (nodeText.equals("Najcześciej występujące angielskie słowa")) {
                            textToLoad = TextLoader.loadText("english1k.txt");

                            try {
                                Path path = Paths.get("lib/Texts/english1k.txt");
                                List<String> words = Files.readAllLines(path);

                                // Wybierz losowe 30 słów
                                String randomWords = getRandomWords(words, 30);

                                // Wyświetl wybrane słowa
                                try {
                                    doc.insertString(doc.getLength(), randomWords, defaultStyle);
                                    textPane.setCaretPosition(0);
                                    textPane.repaint();
                                } catch (BadLocationException ee) {
                                    ee.printStackTrace();
                                }
                                
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    	
                    }
                    
                    else {
                        if (nodeText.equals("Nad Niemnem")) {
                            textToLoad = TextLoader.loadText("nadNiemnem30sec.txt");
                            try {
                                doc.insertString(doc.getLength(), textToLoad, defaultStyle);
                                textPane.setCaretPosition(0);
                                textPane.repaint();
                            } catch (BadLocationException ee) {
                                ee.printStackTrace();
                            }
                        } else if (nodeText.equals("Lalka")) {
                            textToLoad = TextLoader.loadText("lalka30sec.txt");
                            try {
                                doc.insertString(doc.getLength(), textToLoad, defaultStyle);
                                textPane.setCaretPosition(0);
                                textPane.repaint();
                            } catch (BadLocationException ee) {
                                ee.printStackTrace();
                            }
                        } else if (nodeText.equals("Najcześciej występujące polskie słowa")) {
                        	textToLoad = TextLoader.loadText("polish1k.txt");

                            try {
                                Path path = Paths.get("lib/Texts/polish1k.txt");
                                List<String> words = Files.readAllLines(path);

                                // Wybierz losowe 100 słów
                                String randomWords = getRandomWords(words, 100);

                                // Wyświetl wybrane słowa
                                try {
                                    doc.insertString(doc.getLength(), randomWords, defaultStyle);
                                    textPane.setCaretPosition(0);
                                    textPane.repaint();
                                } catch (BadLocationException ee) {
                                    ee.printStackTrace();
                                }
                                
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        } else if (nodeText.equals("Najcześciej występujące angielskie słowa")) {
                        	textToLoad = TextLoader.loadText("english1k.txt");

                            try {
                                Path path = Paths.get("lib/Texts/english1k.txt");
                                List<String> words = Files.readAllLines(path);

                                // Wybierz losowe 30 słów
                                String randomWords = getRandomWords(words, 100);

                                // Wyświetl wybrane słowa
                                try {
                                    doc.insertString(doc.getLength(), randomWords, defaultStyle);
                                    textPane.setCaretPosition(0);
                                    textPane.repaint();
                                } catch (BadLocationException ee) {
                                    ee.printStackTrace();
                                }
                                
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                   
                    

                }
            }
        });
        
        addComponentListener(new ComponentListener() {
            @Override
            public void componentShown(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                btnConfirm.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * confirmButtonScale)));
                tree.setFont(CustomFonts.TEXT_TREE_FONT.deriveFont((float)(width * treeTextScale)));
                textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont((float)(width * textPaneScale)));
            }
            
            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                btnConfirm.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * confirmButtonScale)));
                tree.setFont(CustomFonts.TEXT_TREE_FONT.deriveFont((float)(width * treeTextScale)));
                textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont((float)(width * textPaneScale)));
                

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
        
        setVisible(true);
    }

    private static String getRandomWords(List<String> words, int n) {
        List<String> randomWords = new ArrayList<>(words);
        Collections.shuffle(randomWords);
        return String.join(" ", randomWords.subList(0, n));
    }
}