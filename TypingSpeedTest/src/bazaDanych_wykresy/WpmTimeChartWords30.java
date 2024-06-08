package bazaDanych_wykresy;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.UIDefaults;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import pl.edu.pw.fizyka.pojava.WerysRoszkowski.Words30Panels;

public class WpmTimeChartWords30 {

	 public static JFreeChart displayChart() {
	    	
	        // Wykres
	    	XYSeries series = new XYSeries("WPM / t");
	        
	        for (int x=0; x < Words30Panels.oneWordTime.size(); x++) {
	            series.add(Words30Panels.oneWordTime.get(x),Words30Panels.oneWordWPM.get(x));

	        }

	        XYSeriesCollection dataset = new XYSeriesCollection();
	        dataset.addSeries(series);

	        JFreeChart chart = ChartFactory.createXYLineChart(
	                "WPM wraz z kolejnymi napisanymi słowami", 
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

	        
	        // ZMIANA WYGLĄDU
	 
	        XYPlot plot = (XYPlot) chart.getPlot();
//	        Integer width = 500;


//	        plot.setBackgroundPaint(defaults.getColor("Button.disabledText"));
	        plot.setBackgroundPaint(Color.white);

	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);
	        plot.setOutlineVisible(false);
	        
	        
	     // ZMIANA WYGLĄDU LEGENDY
	        LegendTitle legend = chart.getLegend();
	        legend.setFrame(BlockBorder.NONE);
//	        legend.setItemLabelPadding(new RectangleInsets(5.0, 2.0, 10.0, width));
	        legend.setPadding(20.0, 20.0, 0.0, 0.0);
	        
	        // Ukrycie osi Y
	        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//	        rangeAxis.setVisible(false);
	        
	   
	        
	        ChartFrame frame = new ChartFrame("Wykres", chart);
	        frame.pack();
	        
	        return chart; 
	    }
}
