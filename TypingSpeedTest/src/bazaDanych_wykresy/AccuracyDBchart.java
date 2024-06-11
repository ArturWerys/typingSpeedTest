package bazaDanych_wykresy;

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
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class AccuracyDBchart {
    
	 static ArrayList<Integer> idList = new ArrayList<>();
	    static ArrayList<Float> accuracyList = new ArrayList<>();

	    public static JFreeChart displayChart() {

	    	UIDefaults defaults = UIManager.getDefaults();
	    	
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;

	        try {
	            // Utwórz połączenie
	            conn = DriverManager.getConnection("jdbc:h2:tstData", "artur", "");

	            // Utwórz obiekt instrukcji
	            stmt = conn.createStatement();

	            // Wykonaj zapytanie SQL
	            rs = stmt.executeQuery("SELECT `ID`, `CORRECT WORDS` FROM wyniki");

	            // Przetwórz wyniki zapytania
	            while (rs.next()) {
	                int idValue = rs.getInt("ID");
	                idList.add(idValue);

	                float accuracy = rs.getFloat("CORRECT WORDS");
	                accuracyList.add(accuracy);
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

	        // Wykres
	        XYSeries series = new XYSeries("Accuracy");

	        for (int x = 0; x < idList.size(); x++) {
	            series.add(idList.get(x), accuracyList.get(x));
	        }

	        XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(series);

	        JFreeChart chart = ChartFactory.createXYLineChart(
	                "Accuracy wraz z kolejnymi testami",
	                "Kolejne testy", // Opis osi X
	                "Accuracy",
	                dataset, // Dane
	                PlotOrientation.VERTICAL, // Orientacja wykresu
	                true, // Legenda
	                true, // Tooltips
	                false
	        );

	        // Ustawienie osi X na liczby całkowite
	        NumberAxis xAxis = (NumberAxis) chart.getXYPlot().getDomainAxis();
	        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        xAxis.setAutoRangeIncludesZero(false); // Ustawienie, aby oś X obejmowała 0

	        // ZMIANA WYGLĄDU
	        XYPlot plot = (XYPlot) chart.getPlot();
	        plot.setBackgroundPaint(Color.white);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);
	        plot.setOutlineVisible(false);

	        // ZMIANA WYGLĄDU LEGENDY
	        chart.removeLegend();

	        // Ukrycie osi Y
	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setVisible(true);

	        // Customize the renderer
	        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	        renderer.setSeriesShapesVisible(0, true);
	        renderer.setSeriesLinesVisible(0, true);

	        // Create a larger shape for the points
	        Double shape = new Ellipse2D.Double(-5.0, -5.0, 10.0, 10.0); // Larger circle
	        renderer.setSeriesShape(0, shape);
	        renderer.setSeriesPaint(0,  defaults.getColor("CheckBoxMenuItem.acceleratorSelectionForeground")); // Optional: set the color of the points

	        plot.setRenderer(renderer);
	        // Remove the background color
	        plot.setBackgroundPaint(null); // Set plot background to transparent
	        plot.setOutlinePaint(null); // Remove plot outline
	        chart.setBackgroundPaint(null); // Set chart background to transparent

	        return chart;
    }
}
