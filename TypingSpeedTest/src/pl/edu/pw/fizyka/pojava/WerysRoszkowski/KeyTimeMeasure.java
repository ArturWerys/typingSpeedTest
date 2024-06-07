package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyTimeMeasure {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Measure Key Press Time");
        JTextPane textPane = new JTextPane();
        frame.add(textPane);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Zmienna do przechowywania czasu ostatniego naciśnięcia klawisza
        final long[] lastKeyPressTime = {System.currentTimeMillis()};

        textPane.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                long currentTime = System.currentTimeMillis();
                long timeDifference = currentTime - lastKeyPressTime[0];
                lastKeyPressTime[0] = currentTime;

                // Wyświetl różnicę czasu w milisekundach
                System.out.println("Time between key presses: " + timeDifference + " ms");
            }
        });
    }
}
