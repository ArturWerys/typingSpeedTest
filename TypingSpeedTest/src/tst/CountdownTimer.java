package tst;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
    static class CountdownTask extends TimerTask {
        public int secondsLeft = 5;
        int sliderWidthChange = 0;

		@Override
        public void run() {
            if (secondsLeft > 0) {
            	sliderWidthChange++;
                System.out.println(secondsLeft + " * 10 miliseconds left");
                secondsLeft--;
            } else {
                System.out.println("Time's up!");
                cancel(); // Stop the timer when the countdown reaches 0
            }
        }
		
		public int getSecondsLeft() {
            return secondsLeft;
        }
		
		public int getSliderWidthChange() {
            return sliderWidthChange;
        }
    }
    
}