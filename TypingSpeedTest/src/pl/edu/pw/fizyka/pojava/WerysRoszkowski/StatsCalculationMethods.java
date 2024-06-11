package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

public class StatsCalculationMethods {
	
	public StatsCalculationMethods() {
		// TODO Auto-generated constructor stub
	}
	
	public static int calculateSeconds30WPM(int correctLetters) {
    	
        double numberOfWords = correctLetters / 5.0;
        
        int wpm = (int)(numberOfWords / 0.5);
 	
    	return wpm;
    }
	
	public static int calculateWords30WPM(long startTime, int correctLetters) {
    	
    	long totalTimeMillis = System.currentTimeMillis() - startTime;

        double totalTimeMinutes = ((totalTimeMillis / 1000.0)/60);

        double numberOfWords = correctLetters / 5.0;
        
        int wpm = (int) (numberOfWords / totalTimeMinutes);

    	return wpm;
    }
	
	public static int calculateAccuracy(int correctLetters, int predefinedTextLength ) {
        
		float accuracy;
		
		if(correctLetters == 0) {
			accuracy = 0;
		}
		else {
			accuracy = ((float) (correctLetters) / (predefinedTextLength-1)) * 100;
		}
	    
        return (int) accuracy; 
	}
	
    public static ArrayList<Float> discreteWpmCalculation(ArrayList<Long> letterTimes) {
    	
    	ArrayList<Float> wpmByTimes = new ArrayList<>();
        
        
    	if (letterTimes.size() < 5) {
            return wpmByTimes; 
        }
        
        for (int i = 0; i <= letterTimes.size() - 5; i++) {
        
        	long sum = 0;
            
        	for (int j = i; j < i + 5; j++) {
                sum += letterTimes.get(j);
            }
                       
        	float wpm = 1 / ((float) sum / 60000);
            
            wpmByTimes.add(wpm);
        }
                
        return wpmByTimes;
    	
    }
    
    
    public static ArrayList<Float> movingAverage(ArrayList<Float> data, int windowSize) {
        ArrayList<Float> smoothedData = new ArrayList<>();
        if (data.size() < windowSize) {
            return smoothedData; 
        }

        for (int i = 0; i <= data.size() - windowSize; i++) {
            float sum = 0;
            for (int j = i; j < i + windowSize; j++) {
                sum += data.get(j);
            }
            smoothedData.add(sum / windowSize);
        }

        return smoothedData;
    }


    public static int calculateConstancy(ArrayList<Float> smoothedData) {
        if (smoothedData.size() == 0) {
            return 0;
        }

        double mean = 0.0;
        for (float value : smoothedData) {
            mean += value;
        }
        mean /= smoothedData.size();

        double variance = 0.0;
        for (float value : smoothedData) {
            variance += Math.pow(value - mean, 2);
        }
        variance /= smoothedData.size();
        double standardDeviation = Math.sqrt(variance);

        double maxStandardDeviation = mean; 
        double constancy = 100.0 * (1 - (standardDeviation / maxStandardDeviation));

        if (constancy < 0) constancy = 0;
        if (constancy > 100) constancy = 100;

        return (int)constancy;
    }
    

}
