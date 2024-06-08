package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatsCalculationMethods {
	
//    public static ArrayList<Long> letterTimes = new ArrayList<>();
//    public static ArrayList<Float> oneWordTime = new ArrayList<>();
//    public static ArrayList<Float> oneWordWPM = new ArrayList<>();
//	public static Map<String, Float[]> wpmTimeChartData = new HashMap<>();


	
	public StatsCalculationMethods() {
		// TODO Auto-generated constructor stub
	}
	
	public static int calculateSeconds30WPM(int correctLetters) {
    	
        double numberOfWords = correctLetters / 5.0;
        
        int wpm = (int)(numberOfWords / 0.5);

        System.out.println("WPM: " + wpm);
    	
    	return wpm;
    }
	
	public static int calculateWords30WPM(long startTime, int correctLetters) {
    	
    	long totalTimeMillis = System.currentTimeMillis() - startTime;

        double totalTimeMinutes = ((totalTimeMillis / 1000.0)/60);

        double numberOfWords = correctLetters / 5.0;
        
        int wpm = (int) (numberOfWords / totalTimeMinutes);

        System.out.println("WPM: " + wpm);

    	return wpm;
    }
	
	public static int calculateAccuracy(int correctLetters, int predefinedTextLength ) {
        
		System.out.println("przekazanie cl " +correctLetters);
		System.out.println("przekazanie ptL " +predefinedTextLength);

		float accuracy;
		
		if(correctLetters == 0) {
			accuracy = 0;
		}
		else {
			accuracy = ((float) (correctLetters) / (predefinedTextLength-1)) * 100;
		}
	    
        System.out.println("Accuracy :" + accuracy);
        
        return (int) accuracy; 
	}
	
//	public static Map<String, Float[]> wpmTimeChartCalculations(long lastKeyPressTime, long currentTime) {
//	    long timeDifference = currentTime - lastKeyPressTime;
//	    lastKeyPressTime = currentTime;
//
//	    // Dodanie różnicy czasu do listy
//	    letterTimes.add(timeDifference);
//
//	    int intervalLength = 5;
//	    int range = letterTimes.size() - intervalLength + 1;
//	    
//	    oneWordTime.clear(); // Clear previous values
//	    oneWordWPM.clear();  // Clear previous values
//
//	    if (range > 0) { // Ensure range is not negative
//	        for (int i = 0; i < range; i += intervalLength) {
//	            long sum = 0;
//	            for (int j = 0; j < intervalLength; j++) {
//	                sum += letterTimes.get(i + j);
//	            }
//	            float timeInMinutes = (float) ((sum / 1000.0) / 60);
//	            oneWordTime.add(timeInMinutes);
//	        }
//
//	        for (Float time : oneWordTime) {
//	            oneWordWPM.add(1 / time);
//	        }
//	    }
//
//	    // Dodanie danych do mapy
//	    wpmTimeChartData.put("oneWordTime", oneWordTime.toArray(new Float[0]));
//	    wpmTimeChartData.put("oneWordWPM", oneWordWPM.toArray(new Float[0]));
//
//	    return wpmTimeChartData;
//	}


}
