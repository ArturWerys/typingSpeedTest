package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Dimension;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextPane;

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
		
		JList textList = new JList();
		scrollPane.setViewportView(textList);
		
		JTextPane textPane = new JTextPane();
		getContentPane().add(textPane, "cell 3 1,grow");
		
		setVisible(true);
	}

}
