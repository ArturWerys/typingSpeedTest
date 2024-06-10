package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class WelcomeWindow extends JFrame {
	private static final double titleScale = 0.06;
	private static final double modeButtonScale = 0.02;
	private static final double prevResBttnScale = 0.016;
	
	public static boolean words30choosen = false;
	
	
	
	public WelcomeWindow() {
		super();
		
		SetWindowSize windowSize = new SetWindowSize(this);
		int windowWidth = windowSize.getAutoWindowWidth();
		int windowHeight = windowSize.getAutoWindowHeigth();
		setSize(new Dimension(windowWidth, windowHeight));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[25%][grow,center][25%]", "[][38%][4%][15%][15%][12%][grow]"));
		
		JLabel lblTitle = new JLabel("Typing Speed Test");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(CustomFonts.TITLE_FONT.deriveFont(10f));
		panel.add(lblTitle, "cell 1 1,growx,alignx center,aligny bottom");
		
		JButton btn30WordsButton = new JButton("Tryb 30 słów");
		btn30WordsButton.setToolTipText("Tryb testu, gdzie prędkość pisania określona zostanie po napisanu 30 słów.");
		btn30WordsButton.setFont(CustomFonts.BUTTON_FONT.deriveFont(10f));
		panel.add(btn30WordsButton, "cell 1 3,alignx center,aligny center,grow");
		
		JButton btn30SecondsButton = new JButton("Tryb 30 sekund");
		btn30SecondsButton.setToolTipText("Tryb testu trwający 30 sekund. Napisz jak najwięcej słów w tym czasie!");
		panel.add(btn30SecondsButton, "cell 1 4,alignx center,aligny center,grow");
		
		JButton btnPreviousResults = new JButton("Zobacz poprzednie wyniki");
		btnPreviousResults.setToolTipText("Wykresy przedstawiające Twój postęp na podstawie poprzednich wyników.");
		btnPreviousResults.setFont(CustomFonts.BUTTON_FONT.deriveFont(10f));
		panel.add(btnPreviousResults, "cell 1 5,alignx center,aligny center,grow");
		
		TstMenuBar menuBar = new TstMenuBar(false, true, this, true);
		setJMenuBar(menuBar);	
		
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				int width = e.getComponent().getWidth();
                lblTitle.setFont(CustomFonts.TITLE_FONT.deriveFont((float)(width * titleScale)));
                btn30WordsButton.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * modeButtonScale)));
                btn30SecondsButton.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * modeButtonScale)));
                btnPreviousResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * prevResBttnScale)));
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				int width = e.getComponent().getWidth();
                lblTitle.setFont(CustomFonts.TITLE_FONT.deriveFont((float)(width * titleScale)));
                btn30WordsButton.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * modeButtonScale)));
                btn30SecondsButton.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * modeButtonScale)));
                btnPreviousResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * prevResBttnScale)));
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn30WordsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Words30Panels();
				words30choosen = true;
				WelcomeWindow.this.dispose();
			}
		});
		
		btn30SecondsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Seconds30Panels();
				WelcomeWindow.this.dispose();
			}
		});
		
		btnPreviousResults.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PreviousResultsPanels();
				WelcomeWindow.this.dispose();
			}
		});
		
		setVisible(true);
	}
}
