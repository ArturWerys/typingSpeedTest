package bazaDanych_wykresy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;

import javax.swing.*;
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

        try {
            // Utwórz połączenie
            conn = DriverManager.getConnection("jdbc:h2:tstData", "artur", ""); // Adjust the connection string as needed

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
        dataset.addSeries("Histogram", wpmArray, 10); // 10 bins for the histogram

        // Tworzenie histogramu
        JFreeChart histogram = ChartFactory.createHistogram(
                "Histogram WPM",
                "WPM",
                "Frequency",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        // Display the histogram in a frame
        ChartFrame frame = new ChartFrame("Histogram", histogram);
        frame.pack();

        return histogram;
    }
}
