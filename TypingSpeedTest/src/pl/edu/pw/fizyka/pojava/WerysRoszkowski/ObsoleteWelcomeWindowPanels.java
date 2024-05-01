package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;



public class ObsoleteWelcomeWindowPanels extends JFrame{
		
	public ObsoleteWelcomeWindowPanels() {
		super();
		
		try {
			UIManager.setLookAndFeel(new FlatDarculaLaf());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Kod ustawiający automatyczny rozmiar okna. - Mateusz
		SetWindowSize windowSize = new SetWindowSize(this);
		int windowWidth = windowSize.getAutoWindowWidth();
		int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(975, 485);
		
		Dimension buttDimenison = new Dimension((int)(0.4*windowWidth), (int)(0.15*windowHeight));
//        northPanel.setBackground(ThemeColors.BACKGROUND);
		
        
		JPanel mainPanel = new JPanel();

		getContentPane().add(mainPanel, BorderLayout.CENTER);
								mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
								
								JLabel title = new JLabel("Typing Speed Test");
								title.setHorizontalAlignment(SwingConstants.CENTER);
								
									title.setFont(CustomFonts.TITLE);
									mainPanel.add(title);
				
				JButton words30Button = new JButton("30 słów");
				words30Button.setAlignmentX(Component.CENTER_ALIGNMENT);
						words30Button.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								new Words30Panels();
								ObsoleteWelcomeWindowPanels.this.dispose();
								
							}
						});
						mainPanel.add(words30Button);
				
				JButton seconds30Button = new JButton("30 sekund");
				seconds30Button.setAlignmentX(Component.CENTER_ALIGNMENT);
				seconds30Button.setPreferredSize(buttDimenison); 
				seconds30Button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						new Seconds30Panels();
						ObsoleteWelcomeWindowPanels.this.dispose();
						
					}
				});
				mainPanel.add(seconds30Button);
				
				JButton previousResultButton = new JButton("Poprzednie wyniki");
				previousResultButton.setPreferredSize(buttDimenison);
				previousResultButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						new PreviousResultsPanels();
						ObsoleteWelcomeWindowPanels.this.dispose();
						
						
					}
				});
				mainPanel.add(previousResultButton);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
