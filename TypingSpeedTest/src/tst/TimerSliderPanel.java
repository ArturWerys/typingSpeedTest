package tst;


import javax.swing.JPanel;

import tst.CountdownTimer.CountdownTask;

import java.awt.*;
import java.util.Timer;

public class TimerSliderPanel extends JPanel {
	
	public int getSliderWidth() {
		return sliderWidth;
	}



	public void setSliderWidth(int sliderWidth) {
		this.sliderWidth = sliderWidth;
	}



	private int sliderWidth;
	
	public TimerSliderPanel() {
        
		SetWindowSize windowSize = new SetWindowSize();
		
		
		
		
		
        this.setBackground(Color.CYAN);
        
        
        
        
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		
		
		g.setColor(ThemeColors.SLIDER);
		g.fillRect(0, 0, 400, sliderWidth);
	}
}
