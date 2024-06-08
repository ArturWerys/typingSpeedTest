package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import bazaDanych_wykresy.AccuracyDBchart;
import bazaDanych_wykresy.WpmDBchart;
import net.miginfocom.swing.MigLayout;

public class PreviousResultsPanels extends JFrame {
    private static final double BttnScale = 0.016;

    public PreviousResultsPanels() {
        super();
        SetWindowSize windowSize = new SetWindowSize(this);
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(windowWidth, windowHeight);

        TstMenuBar menuBar = new TstMenuBar(true, true, this);
        setJMenuBar(menuBar);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new MigLayout("", "[5%][90%,grow][5%]", "[2%][43%,grow][43%,grow][10%,grow][2%]"));

        JPanel graph1Panel = new JPanel();
        getContentPane().add(graph1Panel, "cell 1 1,grow");

        JPanel graph2Panel = new JPanel();
        getContentPane().add(graph2Panel, "cell 1 2,grow");

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, "cell 1 3,grow");
        buttonPanel.setLayout(new MigLayout("", "[grow][25%][grow]", "[]"));

        JButton btnGoToWelcomeWindow = new JButton("Powrót do ekranu początkowego");
        btnGoToWelcomeWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PreviousResultsPanels.this.dispose();
                new WelcomeWindow();
            }
        });
        buttonPanel.add(btnGoToWelcomeWindow, "cell 1 0,alignx center,aligny center,grow");
        setVisible(true);

        // Dodanie wykresu do panelu graph1Panel
        JFreeChart chart = AccuracyDBchart.displayChart(); // Tworzenie wykresu
        ChartPanel chartPanel = new ChartPanel(chart); // Konwersja wykresu na panel
        
        graph1Panel.setLayout(new BorderLayout());
        graph1Panel.add(chartPanel, BorderLayout.CENTER);
        
        // Dodanie histogram do panelu graph2Panel
        JFreeChart histogram = WpmDBchart.displayHistogram(); // Tworzenie wykresu
        ChartPanel chartPanel2 = new ChartPanel(histogram); // Konwersja wykresu na panel
        
        graph2Panel.setLayout(new BorderLayout());
        graph2Panel.add(chartPanel2, BorderLayout.CENTER);

        addComponentListener(new ComponentListener() {

            @Override
            public void componentShown(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                btnGoToWelcomeWindow.setFont(CustomFonts.BUTTON_FONT.deriveFont((float) (width * BttnScale)));
            }

            @Override
            public void componentResized(ComponentEvent e) {
                int width = e.getComponent().getWidth();
                btnGoToWelcomeWindow.setFont(CustomFonts.BUTTON_FONT.deriveFont((float) (width * BttnScale)));
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
    }
}