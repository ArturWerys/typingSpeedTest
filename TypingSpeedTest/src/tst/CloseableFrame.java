package tst;

import java.awt.*;

import javax.swing.*;

public class CloseableFrame extends JFrame {
	
	public CloseableFrame() throws HeadlessException {
		this.setSize(640,480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
  
}
