package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMoonlightIJTheme;

public class ThemeList extends JFrame {
	String[] themes = {"FlatLaf light","FlatLaf Dark","Arc Dark", "Gruvbox Dark Hard", "Dracula", "Moonlight"};	
	
	public ThemeList(Component component) {
		super("Wybierz motyw");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JList<String> themeList = new JList<>(themes);
		themeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane = new JScrollPane(themeList);
		
		themeList.addListSelectionListener(e -> {
            switchTheme(themeList.getSelectedIndex(), component);
        });
		
		add(scrollPane, BorderLayout.CENTER	);
		pack();
		setLocationRelativeTo(this);
		setVisible(true);
	}
	
	private void switchTheme(int selectedIndex, Component component) {
		try {
			switch (selectedIndex) {
			case 0:
				UIManager.setLookAndFeel(new FlatLightLaf());
				SwingUtilities.updateComponentTreeUI(component);
				break;
			case 1:
				UIManager.setLookAndFeel(new FlatDarkLaf());
				SwingUtilities.updateComponentTreeUI(component);
				break;
			case 2:
				UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
				SwingUtilities.updateComponentTreeUI(component);
				break;
			case 3:
				UIManager.setLookAndFeel(new FlatGruvboxDarkHardIJTheme());
				SwingUtilities.updateComponentTreeUI(component);
				break;
			case 4:
				UIManager.setLookAndFeel(new FlatDarculaLaf());
				SwingUtilities.updateComponentTreeUI(component);
				break;
			case 5:
				UIManager.setLookAndFeel(new FlatMoonlightIJTheme());
				SwingUtilities.updateComponentTreeUI(component);
				break;
			default:
				break;
			}
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

}
