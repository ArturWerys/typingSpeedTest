package bazaDanych_wykresy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class AccuracyDBchart {
    
    static ArrayList<Integer> idList = new ArrayList<>();
    static ArrayList<Float> accuracyList = new ArrayList<>();

    public static JFreeChart displayChart() {

        // Pobranie domyślnych ustawień
        UIDefaults defaults = UIManager.getDefaults();
        Color textColor = defaults.getColor("textText"); // Get the color from UIManager

        // Połączenie z bazą danych
        try (Connection conn = DriverManager.getConnection("jdbc:h2:tstData", "artur", "");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT `ID`, `CORRECT WORDS` FROM wyniki")) {

            // Przetwarzanie wyników zapytania
            while (rs.next()) {
                int idValue = rs.getInt("ID");
                idList.add(idValue);

                float accuracy = rs.getFloat("CORRECT WORDS");
                accuracyList.add(accuracy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Tworzenie serii danych
        XYSeries series = new XYSeries("Dokładność");
        for (int x = 0; x < idList.size(); x++) {
            series.add(idList.get(x), accuracyList.get(x));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Tworzenie wykresu
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Dokładność wraz z kolejnymi testami",
                "Kolejne testy", // Opis osi X
                "Dokładność",
                dataset, // Dane
                PlotOrientation.VERTICAL, // Orientacja wykresu
                true, // Legenda
                true, // Tooltips
                false
        );

        // Dostosowanie osi X
        NumberAxis xAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        xAxis.setAutoRangeIncludesZero(false); // Ustawienie, aby oś X obejmowała 0

        // Dostosowanie wyglądu wykresu
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setOutlineVisible(false);

        // Usunięcie legendy
        chart.removeLegend();

        // Ukrycie osi Y
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setVisible(true);

        // Dostosowanie renderera
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesLinesVisible(0, true);

        // Ustawienie kształtu i koloru punktów na wykresie
        Double shape = new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0); // Larger circle
        renderer.setSeriesShape(0, shape);
        renderer.setSeriesStroke(0, new BasicStroke(5.0f)); // Set line thickness to 2.0
        renderer.setSeriesPaint(0, defaults.getColor("textText")); // Set the color of the points

        // Dostosowanie koloru tekstu i punktów na osiach X i Y oraz tytułu wykresu
        xAxis.setTickLabelPaint(textColor);
        rangeAxis.setTickLabelPaint(textColor);
        xAxis.setLabelPaint(textColor);
        rangeAxis.setLabelPaint(textColor);
        chart.getTitle().setPaint(textColor);

        plot.setRenderer(renderer);

        // Usunięcie tła wykresu
        plot.setBackgroundPaint(null);
        plot.setOutlinePaint(null);
        chart.setBackgroundPaint(null);

        return chart;
    }
}
