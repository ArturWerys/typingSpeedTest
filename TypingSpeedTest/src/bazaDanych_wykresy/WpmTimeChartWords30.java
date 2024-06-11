package bazaDanych_wykresy;

import java.awt.BasicStroke;
import java.awt.Color;

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
import pl.edu.pw.fizyka.pojava.WerysRoszkowski.Seconds30Panels;
import pl.edu.pw.fizyka.pojava.WerysRoszkowski.StatsCalculationMethods;
import pl.edu.pw.fizyka.pojava.WerysRoszkowski.WelcomeWindow;
import pl.edu.pw.fizyka.pojava.WerysRoszkowski.Words30Panels;

public class WpmTimeChartWords30 {

    public static JFreeChart displayChart() {
    	
    	UIDefaults defaults = UIManager.getDefaults();
    	Color textColor = defaults.getColor("textText"); // Get the color from UIManager
        
    	ArrayList<Float> wpmByTimes = new ArrayList<Float>();
    	ArrayList<Long> fullElapsedTimes = new ArrayList<Long>();
    	ArrayList<Float> smoothedWpmByTimes = new ArrayList<Float>();    
    	
    	int smoothing = 20;
    	
    	if(WelcomeWindow.words30choosen == true) {
    		  wpmByTimes = StatsCalculationMethods.discreteWpmCalculation(Words30Panels.letterTimes);
    		  smoothedWpmByTimes = StatsCalculationMethods.movingAverage(wpmByTimes, smoothing);
    		  fullElapsedTimes = Words30Panels.fullElapsedTime;
    	}
    	else {
    		wpmByTimes = StatsCalculationMethods.discreteWpmCalculation(Seconds30Panels.letterTimes);
    		smoothedWpmByTimes = StatsCalculationMethods.movingAverage(wpmByTimes, smoothing);
  		  	fullElapsedTimes = Seconds30Panels.fullElapsedTime;
    	}
    	
      

        int wpmSize = smoothedWpmByTimes.size();
        int timeSize = fullElapsedTimes.size();
        int numberToRemove = timeSize - wpmSize;

        for (int i = 0; i < numberToRemove; i++) {
            fullElapsedTimes.remove(fullElapsedTimes.size() - 1);
        }

        XYSeries series = new XYSeries("WPM / t");

        for (int x = 0; x < wpmSize; x++) {
            series.add((fullElapsedTimes.get(x))/100, smoothedWpmByTimes.get(x));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "WPM wraz z kolejnymi napisanymi słowami", 
                "Czas [s]", // Opis osi X
                "WPM",
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
        renderer.setSeriesPaint(0, defaults.getColor("textText")); // Set the color of the points
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f)); // Set line thickness to 2.0


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
