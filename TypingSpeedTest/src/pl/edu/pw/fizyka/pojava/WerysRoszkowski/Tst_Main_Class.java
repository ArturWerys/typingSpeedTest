package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Vector;

import javax.swing.*;

import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;

public class Tst_Main_Class {
	
	public static void main(String[] args) {
		
		try {
			System.setProperty( "flatlaf.menuBarEmbedded", "true" );
			UIManager.setLookAndFeel(new FlatGruvboxDarkHardIJTheme());
			UIManager.put( "Button.arc", 20 );
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		WelcomeWindowPanels window1 = new WelcomeWindowPanels();
//		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window1.setVisible(true);
		WelcomeWindow window2 = new WelcomeWindow();
		
		window2.requestFocus();
		window2.setFocusableWindowState(true);
//		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window2.setVisible(true);
//		window2.setFocusableWindowState(true);
		
		
//		System.out.println("wypisane przed UI manager");
//		UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
//		System.out.println("wypisane po UI manager");
//        System.out.println("Available Look and Feels:");
//        for (UIManager.LookAndFeelInfo info : lookAndFeelInfos) {
//            System.out.println(info.getName());
//        }
		
	}

}
