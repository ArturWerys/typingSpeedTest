package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ResultsPanels extends JFrame {
	private static final double countLabelScale = 0.08;
	private static final double nameLabelScale = 0.02;
	private static final double BttnTextScale = 0.016;
    
    public ResultsPanels() {
        super();
        
        SetWindowSize windowSize = new SetWindowSize(this);
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(windowWidth, windowHeight);
        getContentPane().setLayout(new MigLayout("", "[8%][grow][8%]", "[50%,grow][25%][15%,grow]"));
        
        JPanel graphPanel = new JPanel();
        getContentPane().add(graphPanel, "cell 1 0,grow");
        
        JPanel statsPanel = new JPanel();
        getContentPane().add(statsPanel, "cell 1 1,grow");
        statsPanel.setLayout(new MigLayout("", "[10%][26%][26%,grow][26%][10%]", "[46.00%][grow]"));
        
        JLabel lblWPMCount = new JLabel("60");
        lblWPMCount.setToolTipText("Słowa na minutę w tym teście. Liczone jako");
        statsPanel.add(lblWPMCount, "cell 1 0,alignx center,aligny bottom");
        
        JLabel lblAccuracyCount = new JLabel("90%");
        lblAccuracyCount.setToolTipText("Poprawność wpisywanych znaków. Najlepsza możliwa to 100%. Każdy błąd ją obniża.");
        statsPanel.add(lblAccuracyCount, "cell 2 0,alignx center,aligny bottom");
        
        JLabel lblConsistencyCount = new JLabel("85");
        lblConsistencyCount.setToolTipText("Stałość tempa pisania. Liczona jako");
        statsPanel.add(lblConsistencyCount, "cell 3 0,alignx center,aligny bottom");
        
        JLabel lblWPMName = new JLabel("WPM");
        statsPanel.add(lblWPMName, "cell 1 1,alignx center,aligny top");
        
        JLabel lblAccuracyName = new JLabel("dokładność");
        statsPanel.add(lblAccuracyName, "cell 2 1,alignx center,aligny top");
        
        JLabel lblConsistencyName = new JLabel("stałość tempa");
        statsPanel.add(lblConsistencyName, "cell 3 1,alignx center,aligny top");
        
        JPanel buttonsPanel = new JPanel();
        getContentPane().add(buttonsPanel, "cell 1 2,grow");
        buttonsPanel.setLayout(new MigLayout("", "[20%][20%][20%][20%][20%]", "[10%][100%][10%]"));
        
        JButton btnSaveResults = new JButton("Zapisz wyniki");
        buttonsPanel.add(btnSaveResults, "cell 1 1,alignx center,aligny center,grow");
        
        JButton btnDiscardResults = new JButton("Odrzuć wyniki");
        buttonsPanel.add(btnDiscardResults, "cell 3 1,alignx center,aligny center,grow");
        
        TstMenuBar menuBar = new TstMenuBar(true, this);
        setJMenuBar(menuBar);
        
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				int width = e.getComponent().getWidth();
                lblWPMName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblAccuracyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblConsistencyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblWPMCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblAccuracyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblConsistencyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                btnSaveResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * BttnTextScale)));
                btnDiscardResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * BttnTextScale)));
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				int width = e.getComponent().getWidth();
                lblWPMName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblAccuracyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblConsistencyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblWPMCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblAccuracyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblConsistencyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                btnSaveResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * BttnTextScale)));
                btnDiscardResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * BttnTextScale)));
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
		
		btnSaveResults.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultsPanels.this.dispose();
				new PreviousResultsPanels();
			}
		});
		

        
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.requestFocus();
		this.setFocusableWindowState(true);
    }
}