package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import bazaDanych_wykresy.WpmTimeChartWords30;
import net.miginfocom.swing.MigLayout;

public class ResultsPanels extends JFrame {
	private static final double countLabelScale = 0.08;
	private static final double nameLabelScale = 0.02;
	private static final double BttnTextScale = 0.016;
    
    public ResultsPanels(int[] results) {
        super();
                
        SetWindowSize windowSize = new SetWindowSize(this);
        int windowWidth = windowSize.getAutoWindowWidth();
        int windowHeight = windowSize.getAutoWindowHeigth();
        setSize(windowWidth, windowHeight);
        
        int constancyCount;
        
        if(WelcomeWindow.words30choosen == true) {
			ArrayList<Float> smoothedWpmByTimes = StatsCalculationMethods.movingAverage(StatsCalculationMethods.discreteWpmCalculation(Words30Panels.letterTimes), 10);
			constancyCount = StatsCalculationMethods.calculateConstancy(smoothedWpmByTimes);
			
		} else {
			ArrayList<Float> smoothedWpmByTimes = StatsCalculationMethods.movingAverage(StatsCalculationMethods.discreteWpmCalculation(Seconds30Panels.letterTimes), 10);
			constancyCount = StatsCalculationMethods.calculateConstancy(smoothedWpmByTimes);
		}
        
        
        
        getContentPane().setLayout(new MigLayout("", "[8%][grow][8%]", "[50%,grow][25%][15%,grow]"));
        
        JPanel graphPanel = new JPanel();
        getContentPane().add(graphPanel, "cell 1 0,grow");
        
     // Dodanie wykresu do panelu graph1Panel
        JFreeChart chart = WpmTimeChartWords30.displayChart(); // Tworzenie wykresu
        ChartPanel chartPanel = new ChartPanel(chart); // Konwersja wykresu na panel
        
        graphPanel.setLayout(new BorderLayout());
        graphPanel.add(chartPanel, BorderLayout.CENTER);
        
        JPanel statsPanel = new JPanel();
        getContentPane().add(statsPanel, "cell 1 1,grow");
        statsPanel.setLayout(new MigLayout("", "[10%][26%][26%,grow][26%][10%]", "[46.00%][grow]"));
        
        JLabel lblWPMCount = new JLabel(""+ results[1]);
        lblWPMCount.setToolTipText("Słowa na minutę w tym teście. Liczone jako");
        statsPanel.add(lblWPMCount, "cell 1 0,alignx center,aligny bottom");
        
        JLabel lblAccuracyCount = new JLabel(results[0] + "%");
        lblAccuracyCount.setToolTipText("Poprawność wpisywanych znaków. Najlepsza możliwa to 100%. Każdy błąd ją obniża.");
        statsPanel.add(lblAccuracyCount, "cell 2 0,alignx center,aligny bottom");
        
        JLabel lblConstancyCount = new JLabel(constancyCount + "%");
        lblConstancyCount.setToolTipText("Stałość tempa pisania. Liczona jako");
        statsPanel.add(lblConstancyCount, "cell 3 0,alignx center,aligny bottom");
        
        JLabel lblWPMName = new JLabel("WPM");
        statsPanel.add(lblWPMName, "cell 1 1,alignx center,aligny top");
        
        JLabel lblAccuracyName = new JLabel("dokładność");
        statsPanel.add(lblAccuracyName, "cell 2 1,alignx center,aligny top");
        
        JLabel lblConstancyName = new JLabel("stałość tempa");
        statsPanel.add(lblConstancyName, "cell 3 1,alignx center,aligny top");
        
        JPanel buttonsPanel = new JPanel();
        getContentPane().add(buttonsPanel, "cell 1 2,grow");
        buttonsPanel.setLayout(new MigLayout("", "[20%][20%][20%][20%][20%]", "[10%][100%][10%]"));
        
        JButton btnSaveResults = new JButton("Zapisz wyniki");
        buttonsPanel.add(btnSaveResults, "cell 1 1,alignx center,aligny center,grow");
        
        JButton btnDiscardResults = new JButton("Odrzuć wyniki");
        buttonsPanel.add(btnDiscardResults, "cell 3 1,alignx center,aligny center,grow");
        
        TstMenuBar menuBar = new TstMenuBar(true, true, this);
        setJMenuBar(menuBar);
        
		addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				int width = e.getComponent().getWidth();
                lblWPMName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblAccuracyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblConstancyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblWPMCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblAccuracyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblConstancyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                btnSaveResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * BttnTextScale)));
                btnDiscardResults.setFont(CustomFonts.BUTTON_FONT.deriveFont((float)(width * BttnTextScale)));
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				int width = e.getComponent().getWidth();
                lblWPMName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblAccuracyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblConstancyName.setFont(CustomFonts.RSLTS_STATS_NAME_FONT.deriveFont((float)(width * nameLabelScale)));
                lblWPMCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblAccuracyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
                lblConstancyCount.setFont(CustomFonts.RSLTS_STATS_COUNT_FONT.deriveFont((float)(width * countLabelScale)));
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
				updateResult(results);
				ResultsPanels.this.dispose();
				new PreviousResultsPanels();
			}
		});
		
		btnDiscardResults.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

		    	int answer = JOptionPane.showConfirmDialog(null, "Czy na pewno", "Potwierdzenie",
						JOptionPane.YES_NO_OPTION);
				if (answer == JOptionPane.YES_OPTION) {
					ResultsPanels.this.dispose();
					new PreviousResultsPanels();
				}
				
			}
		});
		
		
		
		
   
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        this.requestFocus();
		this.setFocusableWindowState(true);
    }
    
    public static void updateResult(int[] results) {
    	
        LocalTime currentTime = LocalTime.now();
        String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        
        try (Connection connection = DriverManager.getConnection("jdbc:h2:tstData", "artur", "")) {
            String insertQuery = "INSERT INTO wyniki (data, hour, `Correct words`, `WPM`) VALUES (?, ?, ?,?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setDate(1, new java.sql.Date(new java.util.Date().getTime())); 
                statement.setString(2, formattedTime);
                statement.setFloat(3, results[0]);
                statement.setFloat(4, results[1]);

                statement.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

    
}