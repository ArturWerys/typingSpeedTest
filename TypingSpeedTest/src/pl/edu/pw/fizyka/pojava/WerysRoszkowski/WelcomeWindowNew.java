package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Component;

public class WelcomeWindowNew extends JFrame {
	public WelcomeWindowNew() {
		setSize(new Dimension(750, 447));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new MigLayout("", "[grow,center]", "[50%][3%][12.5%][12.5%][12.5%][5]"));
		
		JLabel lblTitle = new JLabel("Typing Speed Test");
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 44));
		panel.add(lblTitle, "cell 0 0,alignx center,aligny bottom");
		
		JButton btn30WordsButton = new JButton("Tryb 30 słów");
		btn30WordsButton.setToolTipText("Tryb testu, gdzie prędkość pisania określona zostanie po napisanu 30 słów.");
		panel.add(btn30WordsButton, "cell 0 2,width 50%,alignx center,growy,aligny center");
		
		JButton btn30SecondsButton = new JButton("Tryb 30 sekund");
		btn30SecondsButton.setToolTipText("Tryb testu trwający 30 sekund. Napisz jak najwięcej słów w tym czasie!");
		panel.add(btn30SecondsButton, "cell 0 3,width 50%,alignx center,growy,aligny center");
		
		JButton btnNewButton_2 = new JButton("Zobacz poprzednie wyniki");
		btnNewButton_2.setToolTipText("Wykresy przedstawiające Twój postęp na podstawie poprzednich wyników.");
		panel.add(btnNewButton_2, "cell 0 4,alignx center,growy,aligny center");
	}

}
