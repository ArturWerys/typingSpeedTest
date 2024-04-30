package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Vector;
import javax.swing.UIManager;


import javax.swing.*;

public class Tst_Main_Class {
	
	public static void main(String[] args) {
		
//		WelcomeWindowPanels window1 = new WelcomeWindowPanels();
//		window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window1.setVisible(true);
		WelcomeWindowNew window2 = new WelcomeWindowNew();
		window2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window2.setVisible(true);
		
//		System.out.println("wypisane przed UI manager");
//		UIManager.LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
//		System.out.println("wypisane po UI manager");
//        System.out.println("Available Look and Feels:");
//        for (UIManager.LookAndFeelInfo info : lookAndFeelInfos) {
//            System.out.println(info.getName());
//        }
		
	}

}
