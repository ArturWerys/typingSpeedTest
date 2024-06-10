package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class TstMenuBar extends JMenuBar {
	
	public TstMenuBar(boolean returnToWelcomeScreen, boolean changeTheme, JFrame componentToClose, boolean chooseText) {
		JMenu mnMainMenu = new JMenu("Menu");
		add(mnMainMenu);
		
		JMenuItem mntmReturn = new JMenuItem("Powrót do ekranu startowego");
		if (returnToWelcomeScreen) {
			mnMainMenu.add(mntmReturn);
		}
		
		JMenuItem mntmThemeChooser = new JMenuItem("Wybierz motyw");
		
		if(changeTheme) {
			mnMainMenu.add(mntmThemeChooser);
		}
		
		JMenuItem textChooser = new JMenuItem("Wybierz tekst");
		
		if(chooseText) {
			mnMainMenu.add(textChooser);
		}
		
		JMenuItem mntmExit = new JMenuItem("Zamknij program");
		mnMainMenu.add(mntmExit);
		
		mntmReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				WelcomeWindow welcomeWindow = new WelcomeWindow();
				welcomeWindow.requestFocus();
				welcomeWindow.setFocusableWindowState(true);
				((Window) componentToClose).dispose();
			}
		});
		
		mntmThemeChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(() -> {
		            new ThemeList(componentToClose);
		        });
			}
		});
		
		mntmExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		textChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(() -> {
		            new TextList(componentToClose);
		        });
				
				System.out.println("Wybierz tekst");
			}
		});
		
	}

}
