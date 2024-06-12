package bazaDanych_wykresy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import javax.swing.*;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WpmDBchart extends JFrame {

    static ArrayList<Integer> wpmList = new ArrayList<>();

    public static JFreeChart displayHistogram() {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        UIDefaults defaults = UIManager.getDefaults();
        Color textColor = defaults.getColor("textText"); 
        
        try {
            // Utwórz połączenie
            conn = DriverManager.getConnection("jdbc:h2:tstData", "artur", "");

            // Utwórz obiekt instrukcji
            stmt = conn.createStatement();

            // Wykonaj zapytanie SQL
            rs = stmt.executeQuery("SELECT `WPM` FROM wyniki");

            // Przetwórz wyniki zapytania
            while (rs.next()) {
                int wpm = rs.getInt("WPM");
                wpmList.add(wpm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Convert wpmList to array
        double[] wpmArray = new double[wpmList.size()];
        for (int i = 0; i < wpmList.size(); i++) {
            wpmArray[i] = wpmList.get(i);
        }

        // Tworzenie zestawu danych histogramu
        HistogramDataset dataset = new HistogramDataset();
        dataset.setType(HistogramType.FREQUENCY);
        
        dataset.addSeries("Histogram", wpmArray, 40); 

        // Tworzenie histogramu
        JFreeChart histogram = ChartFactory.createHistogram(
                "Histogram WPM",
                "WPM",
                "Ilość testów",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );
        
        // ZMIANA WYGLĄDU LEGENDY
        histogram.removeLegend();

        
        XYPlot plot = (XYPlot) histogram.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();

        // Ustawienie koloru na jednolity 
        renderer.setSeriesPaint(0, textColor); 
        
        // Wyłączenie gradientowego wypełnienia słupków
        renderer.setBarPainter(new StandardXYBarPainter());
        renderer.setMargin(0.25); 
        renderer.setSeriesPaint(0, defaults.getColor("textText")); 

      
        histogram.getTitle().setPaint(textColor);
        
        
        // Usunięcie tła wykresu
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        histogram.setBackgroundPaint(null);

        // Ustawienie koloru tekstu dla opisu osi X
        plot.getDomainAxis().setLabelPaint(textColor);

        // Ustawienie koloru tekstu dla opisu osi Y
        plot.getRangeAxis().setLabelPaint(textColor);

        // Ustawienie koloru tekstu dla podpisów osi X
        plot.getDomainAxis().setTickLabelPaint(textColor);

        // Ustawienie koloru tekstu dla podpisów osi Y
        plot.getRangeAxis().setTickLabelPaint(textColor);
        
        return histogram;
    }

}
