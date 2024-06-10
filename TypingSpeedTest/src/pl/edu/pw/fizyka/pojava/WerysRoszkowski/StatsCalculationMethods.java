package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
//    public static void letterTimesCalculation(boolean isFirstKeyPress, long lastKeyPressTime, ArrayList<Long> fullElapsedTime, ArrayList<Long> letterTimes) {
//        // LOGIKA NOWEGO WYKRESU
//        
//        long currentTime = System.currentTimeMillis();
//
//        if (isFirstKeyPress) {
//            // Jeśli to pierwsze naciśnięcie klawisza, ustawiamy czas i zmieniamy flagę
//            lastKeyPressTime = currentTime;
//            isFirstKeyPress = false;
//            // Dodajemy 0 do fullElapsedTime jako początek
//            fullElapsedTime.add(0L);
//            return;
//        }
//        
//        long timeDifference = currentTime - lastKeyPressTime;
//        
//        lastKeyPressTime = currentTime;
//
//        // Dodanie różnicy czasu do listy
//        letterTimes.add(timeDifference);
//        
//        // LOGIKA OSI X (czasu) full elapsed time
//        
//        if (fullElapsedTime.size() == 1 && fullElapsedTime.get(0) == 0) {
//        
//        	fullElapsedTime.set(0, timeDifference);
//        
//        } else {
//            long lastElapsedTime = fullElapsedTime.get(fullElapsedTime.size() - 1);
//            long newElapsedTime = lastElapsedTime + timeDifference;
//            fullElapsedTime.add(newElapsedTime);
//        }
//        
//                
//    }
//	
	
    public static ArrayList<Float> discreteWpmCalculation(ArrayList<Long> letterTimes) {
    	ArrayList<Float> wpmByTimes = new ArrayList<>();
        
        // Sprawdzamy, czy jest wystarczająca ilość danych do przetworzenia
        
    	if (letterTimes.size() < 5) {
            return wpmByTimes; // Zwracamy pustą listę, jeśli nie ma wystarczającej ilości danych
        }
        
        // Iterujemy po letterTimes, biorąc pod uwagę 5 kolejnych elementów
        for (int i = 0; i <= letterTimes.size() - 5; i++) {
            // Bierzemy podtablicę 5 kolejnych wartości z letterTimes
        
        	long sum = 0;
            
        	for (int j = i; j < i + 5; j++) {
                sum += letterTimes.get(j);
            }
            
            // Obliczamy WPM na podstawie sumy 5 wartości z letterTimes
            
        	float wpm = 1 / ((float) sum / 60000);
            
            wpmByTimes.add(wpm);
        }
                
        return wpmByTimes;
    	
    }

	

}
