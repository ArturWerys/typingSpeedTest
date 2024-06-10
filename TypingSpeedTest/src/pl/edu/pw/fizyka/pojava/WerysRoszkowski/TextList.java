package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;


public class TextList extends JFrame {
    public static String predefinedText = TextLoader.loadText("nadNiemnem.txt");
;
	String[] textsToChoose = {"Nad Niemnem", "Przykładowy"};
	
	public TextList(Component component) {
		super("Wybierz tekst");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JList<String> textList = new JList<>(textsToChoose);
		textList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(textList);
		
		textList.addListSelectionListener(e -> {
            switchTheme(textList.getSelectedIndex(), component);
        });
		
		add(scrollPane, BorderLayout.CENTER	);
		pack();
		setLocationRelativeTo(this);
		setVisible(true);
	}
	
	private void switchTheme(int selectedIndex, Component component) {
		switch (selectedIndex) {
		case 0:
		    predefinedText = TextLoader.loadText("nadNiemnem.txt");
			SwingUtilities.updateComponentTreeUI(component);
			break;
		case 1:
		    predefinedText   = TextLoader.loadText("testowy.txt");

			SwingUtilities.updateComponentTreeUI(component);
			break;
//		case 2:
//			SwingUtilities.updateComponentTreeUI(component);
//			break;
//		case 3:
//			SwingUtilities.updateComponentTreeUI(component);
//			break;
//		case 4:
//			SwingUtilities.updateComponentTreeUI(component);
//			break;
//		case 5:
//			SwingUtilities.updateComponentTreeUI(component);
//			break;
		default:
			
			break;
		}
	

	}}
