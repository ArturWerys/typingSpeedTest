package tst;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomeWindowPanels extends JFrame implements ActionListener{
		
	public WelcomeWindowPanels() {
		super();
		
		//Kod ustawiający automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize();
		int windowWidth = windowSize.getAutoWindowWidth();
		int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(windowWidth, windowHeight);
		
		//Dimension buttDimenison = new Dimension((int)0.5*windowWidth, (int)0.1*windowHeight);
		
		JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.PAGE_START);
        northPanel.setBackground(ThemeColors.BACKGROUND);
		
        Dimension northPanelDim = new Dimension(windowWidth, (int)(0.18 * windowHeight));
        northPanel.setPreferredSize(northPanelDim);
        
        
		JPanel mainPanel = new JPanel();
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBackground(ThemeColors.BACKGROUND);
		
		JLabel title = new JLabel("Typing Speed Test");
		mainPanel.add(title);
		title.setFont(CustomFonts.TITLE);
		
		JButton words30Button = new JButton("30 słów");
		words30Button.setPreferredSize(new Dimension(500, 100)); 
		mainPanel.add(words30Button);
		words30Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Words30Panels();
				WelcomeWindowPanels.this.dispose();
				
			}
		});
		
		
		JButton seconds30Button = new JButton("30 sekund");
		seconds30Button.setPreferredSize(new Dimension(500, 100)); 
		mainPanel.add(seconds30Button);
		seconds30Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Seconds30Panels();
				WelcomeWindowPanels.this.dispose();
				
			}
		});
		
		
		JButton previousResultButton = new JButton("Poprzednie wyniki");
		previousResultButton.setPreferredSize(new Dimension(500, 100)); 
		mainPanel.add(previousResultButton);
		previousResultButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PreviousResultsPanels();
				WelcomeWindowPanels.this.dispose();
				
				
			}
		});
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
