package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;

public class Tst_Main_Class {
	
	public static void main(String[] args) {
		
		FlatGruvboxDarkHardIJTheme.setup();
		UIManager.put( "Button.arc", 20 );


		WelcomeWindow welcomeWindow = new WelcomeWindow();
		
		welcomeWindow.requestFocus();
		welcomeWindow.setFocusableWindowState(true);
		
	}

}
