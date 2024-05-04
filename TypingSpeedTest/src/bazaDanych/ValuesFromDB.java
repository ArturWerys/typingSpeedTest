package bazaDanych;

import java.sql.*;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ValuesFromDB {
    
    static ArrayList<Integer> idList = new ArrayList<>();
    static ArrayList<Float> accuracyList = new ArrayList<>();
    
//    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//
//        try {
//            // Utwórz połączenie
//            conn = DriverManager.getConnection("jdbc:h2:tstData", "artur", "");
//
//            // Utwórz obiekt instrukcji
//            stmt = conn.createStatement();
//
//            // Wykonaj zapytanie SQL
//            rs = stmt.executeQuery("SELECT `ID`, `CORRECT WORDS` FROM wyniki");
//
//            // Przetwórz wyniki zapytania
//            while (rs.next()) {
//                int idValue = rs.getInt("ID");
//                idList.add(idValue);
//                
//                float accuracy = rs.getFloat("CORRECT WORDS");
//                accuracyList.add(accuracy);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // Zamykanie ResultSet, Statement i Connection
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        displayChart();
//
//        
//}
    
    public static void displayChart() {
    	
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
            // Zamykanie ResultSet, Statement i Connection
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
        // Tworzymy wykres XY
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Accuracy wraz z kolejnymi testami", // Tytuł
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


        // Tutaj możesz dodać kod do wyświetlenia wykresu, na przykład:
         ChartFrame frame = new ChartFrame("Wykres", chart);
         frame.pack();
         frame.setVisible(true);
         
       
    }
}
