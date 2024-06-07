package bazaDanych;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class AccuracyDBchart {
    
    static ArrayList<Integer> idList = new ArrayList<>();
    static ArrayList<Float> accuracyList = new ArrayList<>();
    

       
    public static JFreeChart displayChart() {
    	
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
    	UIDefaults defaults = UIManager.getDefaults();



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
        
        for (int x=0; x < idList.size(); x++) {
            series.add(idList.get(x),accuracyList.get(x));

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
//        Integer width = 500;


//        plot.setBackgroundPaint(defaults.getColor("Button.disabledText"));
        plot.setBackgroundPaint(Color.white);

        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setOutlineVisible(false);
        
        
     // ZMIANA WYGLĄDU LEGENDY
        LegendTitle legend = chart.getLegend();
        legend.setFrame(BlockBorder.NONE);
//        legend.setItemLabelPadding(new RectangleInsets(5.0, 2.0, 10.0, width));
        legend.setPadding(20.0, 20.0, 0.0, 0.0);
        
        // Ukrycie osi Y
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setVisible(false);
        
   
        
        ChartFrame frame = new ChartFrame("Wykres", chart);
        frame.pack();
        
        return chart; 
    }
}
