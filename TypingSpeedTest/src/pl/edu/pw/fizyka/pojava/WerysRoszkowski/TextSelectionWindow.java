package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Dimension;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JTextPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.formdev.flatlaf.FlatLightLaf;

public class TextSelectionWindow extends JFrame {

    public TextSelectionWindow() {
        super();
        
        SetWindowSize windowSize = new SetWindowSize(this);
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(new Dimension(windowWidth, windowHeight));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        TstMenuBar menuBar = new TstMenuBar(true, true, this);
        setJMenuBar(menuBar);
        getContentPane().setLayout(new MigLayout("", "[5%][42.5%,grow][5%][42.5%,grow][5%]", "[5%][90%,grow][5%]"));
        
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(scrollPane, "cell 1 1,grow");
        
        // Utwórz korzeń drzewa
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        // Dodaj pierwszą nadrzędną pozycję
        DefaultMutableTreeNode parent1 = new DefaultMutableTreeNode("Parent 1");
        root.add(parent1);

        // Dodaj podlistę do pierwszej nadrzędnej pozycji
        parent1.add(new DefaultMutableTreeNode("Child 1.1"));
        parent1.add(new DefaultMutableTreeNode("Child 1.2"));

        // Dodaj drugą nadrzędną pozycję
        DefaultMutableTreeNode parent2 = new DefaultMutableTreeNode("Parent 2");
        root.add(parent2);

        // Dodaj podlistę do drugiej nadrzędnej pozycji
        parent2.add(new DefaultMutableTreeNode("Child 2.1"));
        parent2.add(new DefaultMutableTreeNode("Child 2.2"));

        // Utwórz model drzewa
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        JTree tree = new JTree(treeModel);
        
        // Ukryj korzeń drzewa
        tree.setRootVisible(false);

        // Dodaj drzewo do JScrollPane
        scrollPane.setViewportView(tree);
        
        JTextPane textPane = new JTextPane();
        getContentPane().add(textPane, "cell 3 1,grow");
        
        
        tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        setVisible(true);
    }

    public static void main(String[] args) {
		FlatLightLaf.setup();
		UIManager.put( "Button.arc", 20 );
        new TextSelectionWindow();
    }
}
