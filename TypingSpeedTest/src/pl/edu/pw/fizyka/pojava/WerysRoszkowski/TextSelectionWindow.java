package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
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
                if (WelcomeWindow.words30choosen) {
                    new Words30Panels();
                    TextSelectionWindow.this.dispose();
                } else {
                    new Seconds30Panels();
                    TextSelectionWindow.this.dispose();
                }
            }
        });
        
        // Create the root of the tree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        // Add first parent node
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("Tekst klasyczny");
        root.add(parent1);

        // Add children to the first parent node
        DefaultMutableTreeNode nadNiemnemNode = new DefaultMutableTreeNode("Nad Niemnem");
        parent1.add(nadNiemnemNode);
        parent1.add(new DefaultMutableTreeNode("Lalka"));

        // Add second parent node
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("Tekst losowy");
        root.add(parent2);

        // Add children to the second parent node
        parent2.add(new DefaultMutableTreeNode("Child 2.1"));
        parent2.add(new DefaultMutableTreeNode("Child 2.2"));

        // Create the tree model and tree
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);
        
        // Hide the root of the tree
        tree.setRootVisible(false);
        
        // Add tree to JScrollPane
        scrollPane.setViewportView(tree);
        
        // Set button and tree fonts
        btnConfirm.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(windowWidth * confirmButtonScale)));
        tree.setFont(CustomFonts.TEXT_TREE_FONT.deriveFont((float)(windowWidth * treeTextScale)));
        
        // Set the text pane attributes
        textPane.setCaretColor(defaults.getColor("Caret"));
        
        // Create and set the style
        Style textPaneStyle = textPane.addStyle("TextPaneStyle", null);
        StyleConstants.setFontFamily(textPaneStyle, CustomFonts.TEXT_PANE_FONT.getFamily());
        StyleConstants.setFontSize(textPaneStyle, (int) (windowWidth * textPaneScale));
        StyleConstants.setForeground(textPaneStyle, defaults.getColor("textText"));

        // Apply the style to the entire document
        StyledDocument doc = textPane.getStyledDocument();
        doc.setCharacterAttributes(0, doc.getLength(), textPaneStyle, false);
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (selectedNode != null) {
                    String nodeText = selectedNode.getUserObject().toString();
                    
                    // Clear the text pane before inserting new text
                    textPane.setText("");
                    
                    if (WelcomeWindow.words30choosen) {
                        loadText(textPane, nodeText, "nadNiemnem.txt", "lalka30slow.txt", doc, textPaneStyle);
                    } else {
                        loadText(textPane, nodeText, "nadNiemnem30sec.txt", "lalka30sec.txt", doc, textPaneStyle);
                    }
                }
            }
        });
        
        addComponentListener(new ComponentListener() {
            @Override
            public void componentShown(ComponentEvent e) {
                updateComponentSizes(e.getComponent().getWidth());
            }
            
            @Override
            public void componentResized(ComponentEvent e) {
                updateComponentSizes(e.getComponent().getWidth());
            }
            
            @Override
            public void componentMoved(ComponentEvent e) {
                // Do nothing
            }
            
            @Override
            public void componentHidden(ComponentEvent e) {
                // Do nothing
            }

            private void updateComponentSizes(int width) {
                btnConfirm.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * confirmButtonScale)));
                tree.setFont(CustomFonts.TEXT_TREE_FONT.deriveFont((float)(width * treeTextScale)));
                
                int fontSize = (int) (width * textPaneScale);
                textPane.setFont(CustomFonts.TEXT_PANE_FONT.deriveFont((float) fontSize));
                StyleConstants.setFontSize(textPaneStyle, fontSize);
                doc.setCharacterAttributes(0, doc.getLength(), textPaneStyle, false);
                repaint();
            }
        });
        
        setVisible(true);
    }
    
    private void loadText(JTextPane textPane, String nodeText, String nadNiemnemFile, String lalkaFile, StyledDocument doc, Style textPaneStyle) {
        if (nodeText.equals("Nad Niemnem")) {
            textToLoad = TextLoader.loadText(nadNiemnemFile);
        } else if (nodeText.equals("Lalka")) {
            textToLoad = TextLoader.loadText(lalkaFile);
        } else if (nodeText.equals("Child 2.1")) {
            textToLoad = "Placeholder text for Child 2.1";
        } else if (nodeText.equals("Child 2.2")) {
            textToLoad = "Placeholder text for Child 2.2";
        }
        
        try {
            doc.insertString(doc.getLength(), textToLoad, textPaneStyle);
            textPane.setCaretPosition(0);
            textPane.repaint();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

}
