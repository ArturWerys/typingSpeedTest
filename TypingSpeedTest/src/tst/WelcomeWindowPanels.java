package tst;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WelcomeWindowPanels extends JFrame{
		
	public WelcomeWindowPanels() {
		super();
		
		//Kod ustawiający automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize();
		int windowWidth = windowSize.getAutoWindowWidth();
		int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(windowWidth, windowHeight);
		
		Dimension buttDimenison = new Dimension((int)(0.4*windowWidth), (int)(0.15*windowHeight));
		
		JPanel northPanel = new JPanel();
        add(northPanel, BorderLayout.PAGE_START);
        northPanel.setBackground(ThemeColors.BACKGROUND);
		
        Dimension northPanelDim = new Dimension(windowWidth, (int)(0.18 * windowHeight));
        northPanel.setPreferredSize(northPanelDim);
        
		JPanel mainPanel = new JPanel(new GridBagLayout());

		add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBackground(ThemeColors.BACKGROUND);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(7, 0, 7, 0); // Odstęp miezy przyciskami
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
		
		JLabel title = new JLabel("Typing Speed Test");
	
		title.setFont(CustomFonts.TITLE);
		
		JButton words30Button = new JButton("30 słów");

		words30Button.setPreferredSize(buttDimenison);
		words30Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Words30Panels();
				WelcomeWindowPanels.this.dispose();
				
			}
		});
		
		JButton seconds30Button = new JButton("30 sekund");
		seconds30Button.setAlignmentX(Component.CENTER_ALIGNMENT);
		seconds30Button.setPreferredSize(buttDimenison); 
		seconds30Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Seconds30Panels();
				WelcomeWindowPanels.this.dispose();
				
			}
		});
		
		JButton previousResultButton = new JButton("Poprzednie wyniki");
		previousResultButton.setPreferredSize(buttDimenison);
		previousResultButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PreviousResultsPanels();
				WelcomeWindowPanels.this.dispose();
				
				
			}
		});
			
		mainPanel.add(title, constraints);
		constraints.gridy++;
		mainPanel.add(words30Button, constraints);
		constraints.gridy++;
		mainPanel.add(seconds30Button, constraints);
		constraints.gridy++;
		mainPanel.add(previousResultButton, constraints);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
