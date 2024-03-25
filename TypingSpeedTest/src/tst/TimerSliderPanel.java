package tst;


import javax.swing.*;



import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TimerSliderPanel extends JPanel {
	
//	public int getSliderWidth() {
//		return sliderWidth;
//	}
//
//
//
//	public void setSliderWidth(int sliderWidth) {
//		this.sliderWidth = sliderWidth;
//	}


	int sliderWidth;
	private int counter = 0;
	SetWindowSize windowSize = new SetWindowSize();
	
	public TimerSliderPanel() {

		Timer timer = new Timer(1000, new ActionListener() { // Timer with interval of 1000 milliseconds (1 second)
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the counter or perform any other necessary action
                counter++;
                repaint(); // Request a repaint
                System.out.println("repaint event occured");
                long elapsedTime=System.currentTimeMillis() - Tryb30SekundPanels.startTime;
                System.out.println("Czas z timera: "+elapsedTime);
                
                sliderWidth = windowSize.autoWindowWidth - 10*(int)(elapsedTime/1000);
            }
        });
        timer.start(); // Start the timer
		
		
        this.setBackground(Color.CYAN);

	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		
		
		g.setColor(ThemeColors.SLIDER);
		g.fillRect(0, 0, sliderWidth, (int)(0.1 * windowSize.getAutoWindowHeigth()));
	}
}
