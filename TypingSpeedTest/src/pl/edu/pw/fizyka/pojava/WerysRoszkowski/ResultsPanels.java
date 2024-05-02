package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ResultsPanels extends JFrame {
    
    public ResultsPanels() {
        super();
        
        SetWindowSize windowSize = new SetWindowSize(this);
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(windowWidth, windowHeight);
        getContentPane().setLayout(new MigLayout("", "[8%][grow][8%]", "[50%,grow][25%][15%,grow]"));
        
        JPanel statsPanel = new JPanel();
        getContentPane().add(statsPanel, "cell 1 1,grow");
        statsPanel.setLayout(new MigLayout("", "[10%][26%][26%,grow][26%][10%]", "[grow][20%]"));
        
        JLabel lblWPMCount = new JLabel("60");
        lblWPMCount.setToolTipText("Słowa na minutę w tym teście. Liczone jako");
        statsPanel.add(lblWPMCount, "cell 1 0,alignx center,aligny center");
        
        JLabel lblAccuracyCount = new JLabel("90%");
        lblAccuracyCount.setToolTipText("Poprawność wpisywanych znaków. Najlepsza możliwa to 100%. Każdy błąd ją obniża.");
        statsPanel.add(lblAccuracyCount, "cell 2 0,alignx center,aligny center");
        
        JLabel lblConsistencyCount = new JLabel("85");
        lblConsistencyCount.setToolTipText("Stałość tempa pisania. Liczona jako");
        statsPanel.add(lblConsistencyCount, "cell 3 0,alignx center,aligny center");
        
        JLabel lblWPMName = new JLabel("WPM");
        statsPanel.add(lblWPMName, "cell 1 1,alignx center,aligny center");
        
        JLabel lblAccuracyName = new JLabel("dokładność");
        statsPanel.add(lblAccuracyName, "cell 2 1,alignx center,aligny center");
        
        JLabel lblConsistencyName = new JLabel("stałość tempa");
        statsPanel.add(lblConsistencyName, "cell 3 1,alignx center,aligny center");
        
        JPanel panel = new JPanel();
        getContentPane().add(panel, "cell 1 2,grow");
        
        TstMenuBar menuBar = new TstMenuBar(true, this);
        setJMenuBar(menuBar);
        
        
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}