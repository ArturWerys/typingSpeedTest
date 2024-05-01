package wykresy;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Testowy {
    public static void main(String[] args) {
        XYSeries series = new XYSeries("Accuracy");
        
        series.add(1, 1);
        series.add(1, 2);
        series.add(2, 4);
        series.add(6, 10);
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

        // Tutaj możesz dodać kod do wyświetlenia wykresu, na przykład:
         ChartFrame frame = new ChartFrame("Wykres", chart);
         frame.pack();
         frame.setVisible(true);
    }
}
