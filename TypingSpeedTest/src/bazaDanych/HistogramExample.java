package bazaDanych;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;
import javax.swing.*;

public class HistogramExample extends JFrame {

	public static JFreeChart displayHistogram() {
		
		// Dane do wykresu
        double[] data = {90, 90, 100, 100, 100, 88, 88, 93, 54, 33};
        int bins = 15; // Liczba przedziałów

        // Tworzenie zestawu danych histogramu
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Histogram", data, bins);

        // Tworzenie histogramu
        JFreeChart histogram = ChartFactory.createHistogram(
                "Przykład histogramu",
                "WPM",
                "Kolejne testy",
                dataset,
                PlotOrientation.VERTICAL,  // Orientacja wykresu
                true,  // Czy pokazywać legendę
                false,  // Czy pokazywać tooltips
                false  // Czy pokazywać URLs
        );
        
        ChartFrame frame = new ChartFrame("Histogram", histogram);
        frame.pack();
		
		return histogram; 
	}
	
   

    
}
