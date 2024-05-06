package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatAllIJThemes;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;

public class Tst_Main_Class {
	
	public static void main(String[] args) {
		
		FlatLightLaf.setup();
		UIManager.put( "Button.arc", 20 );


		WelcomeWindow welcomeWindow = new WelcomeWindow();
		
		welcomeWindow.requestFocus();
		welcomeWindow.setFocusableWindowState(true);
		
	}

}
