package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

public class StatsCalculationMethods {
	
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
	
}
