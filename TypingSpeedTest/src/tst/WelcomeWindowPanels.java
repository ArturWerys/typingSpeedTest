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
		
		JPanel panelGorny = new JPanel();
        add(panelGorny, BorderLayout.PAGE_START);
        panelGorny.setBackground(ThemeColors.BACKGROUND);
		
        Dimension panelGornyDim = new Dimension(windowWidth, (int)(0.18 * windowHeight));
        panelGorny.setPreferredSize(panelGornyDim);
        
        
		JPanel panelGlowny = new JPanel();
		add(panelGlowny, BorderLayout.CENTER);
		panelGlowny.setBackground(ThemeColors.BACKGROUND);
		
		JLabel title = new JLabel("Typing Speed Test");
		panelGlowny.add(title);
		title.setFont(CustomFonts.TITLE);
		
		JButton tryb30Slow = new JButton("30 słów");
		tryb30Slow.setPreferredSize(new Dimension(500, 100)); 
		panelGlowny.add(tryb30Slow);
		tryb30Slow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Tryb30SlowPanels();
				WelcomeWindowPanels.this.dispose();
				
			}
		});
		
		
		JButton tryb30Sekund = new JButton("30 sekund");
		tryb30Sekund.setPreferredSize(new Dimension(500, 100)); 
		panelGlowny.add(tryb30Sekund);
		tryb30Sekund.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//new TestWindowPanels();
				WelcomeWindowPanels.this.dispose();
				
			}
		});
		
		
		JButton poprzednieWyniki = new JButton("Poprzednie wyniki");
		poprzednieWyniki.setPreferredSize(new Dimension(500, 100)); 
		panelGlowny.add(poprzednieWyniki);
		poprzednieWyniki.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//new TestWindowPanels();
				WelcomeWindowPanels.this.dispose();
				
			}
		});
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
