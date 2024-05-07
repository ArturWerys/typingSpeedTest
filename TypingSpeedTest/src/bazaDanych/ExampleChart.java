package bazaDanych;

import java.util.Arrays;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ExampleChart {
    public static JFreeChart displayChart() {
        // Dane przykładowe
        List<Integer> timeList = Arrays.asList(1, 2, 3, 4, 5,6,7,8,9,10);
        List<Double> wpmList = Arrays.asList(80.0, 85.0, 90.0, 92.0, 88.0, 55.0, 42.3, 67.9, 99.0, 45.8);

        // Wykres
        XYSeries series = new XYSeries("Accuracy");

        for (int x = 0; x < timeList.size(); x++) {
            series.add(timeList.get(x), wpmList.get(x));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // Tworzymy wykres XY
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Przykładowy wykres", // Tytuł
                "Czas", // Opis osi X
                "WPM",
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

        // Wyświetlenie wykresu
        ChartFrame frame = new ChartFrame("Wykres", chart);
        frame.pack();

        return chart;
    }
}
