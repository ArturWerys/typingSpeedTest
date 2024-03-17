package tst;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.Vector;

import javax.swing.*;

public class Tst_Main_Class {
	
	public static void main(String[] args) {

		TestWindowPanels frame = new TestWindowPanels();
		
		// Vector
		
//		Vector<String> textToWrite = new Vector<String>();
//		Vector<String> textToVector = new Vector<String>();
//		
//		textToWrite.add("Test Test Test Test");
//		
//		String a = textToWrite.elementAt(0);
//	
//		// Window
//		
//		CloseableFrame frame = new CloseableFrame();
//	    
//		frame.setSize(windowWidth, windowHeight);
//		
//		
//		JPanel jpanel_1 = new JPanel();
//		
//	    JLabel centralLabel = new JLabel(a);
//	    jpanel_1.add(centralLabel);
//	    
//	    frame.add(jpanel_1, BorderLayout.CENTER);
//		
//		
//		// TestField
//		
//		JTextField testField = new JTextField();
//		testField.setPreferredSize(new Dimension(200, 50));
//		
//		frame.add(testField,BorderLayout.PAGE_END);
//		
		frame.setResizable(false);
		frame.setVisible(true);
				

	}

}
