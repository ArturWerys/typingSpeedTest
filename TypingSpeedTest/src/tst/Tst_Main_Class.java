package tst;

import java.awt.BorderLayout;

import java.util.Vector;

import javax.swing.*;

public class Tst_Main_Class {

	

	public static void main(String[] args) {

		// Vector
		
				Vector<String> textToWrite = new Vector<String>();
				Vector<String> textToVector = new Vector<String>();
				
				
				
				
				textToWrite.add("Test TEst TEst TEst ");
				
				String a = textToWrite.elementAt(0);

				// Window
				
				CloseableFrame frame = new CloseableFrame();
		        
				frame.setSize(800, 600);
				
				JPanel jpanel_1 = new JPanel();
				
		        JLabel centralLabel = new JLabel(a);
		        jpanel_1.add(centralLabel);
		        
		        frame.add(jpanel_1, BorderLayout.CENTER);
				
				
				// TextField
				
				JTextField testField = new JTextField();
				
				frame.add(testField,BorderLayout.PAGE_END);
				
				frame.setVisible(true);

	


	}

}
