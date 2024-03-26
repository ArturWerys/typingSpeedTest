package tst;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Interfejs do obsługi zdarzenia zakończenia czasu
interface TimerListener {
	void onTimerFinished();
}

public class TimerSliderPanel extends JPanel {

    SetWindowSize windowSize = new SetWindowSize();
    int sliderWidth = windowSize.autoWindowWidth;
    int sliderHeigth = (int) (0.03*windowSize.autoWindowHeight);
    int updateTimeMiliS = 10;
    int testDurationMilis = 5000;
    long elapsedTime;
    public static boolean isTimerStopped = false;
    Timer timer;
    
    public TimerSliderPanel() {
    	Dimension silderDim = new Dimension(windowSize.autoWindowWidth, sliderHeigth);
    	this.setPreferredSize(silderDim);
    	
    	
        timer = new Timer(updateTimeMiliS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                if (Seconds30Panels.isFirstCharacterEntered) {
                	elapsedTime = System.currentTimeMillis() - Seconds30Panels.startTime;
                } else {
                	elapsedTime = 0;
                }
                
                double sliderDivision = (double)windowSize.autoWindowWidth/testDurationMilis;
                double sliderDelta = elapsedTime * sliderDivision;
                sliderWidth = (int)(windowSize.autoWindowWidth - sliderDelta);

                if (elapsedTime >= testDurationMilis) {
                    timer.stop();
                    System.out.println("Timer stopped");
                    isTimerStopped = true;
//                    timerListener.onTimerFinished(); // Wywołanie metody interfejsu

                    
                }
            }
        });
        timer.start();

        this.setBackground(ThemeColors.BACKGROUND);
    }



	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(ThemeColors.SLIDER);
        g.fillRect(0, 0, sliderWidth, sliderHeigth);
    }
}
