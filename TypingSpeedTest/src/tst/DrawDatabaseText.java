package tst;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawDatabaseText extends JPanel {
    public String getPaintedText() {
		return paintedText;
	}
    
	
	public String paintedText = "Lorem ipsum dolor sit amet";
    
	public String[] wordsArray = paintedText.split("\\s+");
    
    public Color[] wordsColor = new Color[wordsArray.length];
    
    Font wordsFont = new Font("Arial", Font.PLAIN, 20);


    public DrawDatabaseText() {
		// TODO Auto-generated constructor stub
    	JPanel databaseTextPanel = new JPanel();
    	Dimension TextDimension = new Dimension(10,10);
    	databaseTextPanel.setPreferredSize(TextDimension);;
    	
    	
        databaseTextPanel.setBackground(ThemeColors.TEXT_FIELD_BACKGROUND);
       
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setFont(wordsFont);
        
        int x = 20;
        int y = 50;
        for (int i = 0; i < wordsArray.length; i++) {
            g.setColor(wordsColor[i] != null ? wordsColor[i] : Color.BLACK); // Ustawienie aktualnego koloru tekstu lub domyślnie na czarny
            g.drawString(wordsArray[i], x, y); // Rysowanie słowa
            x += g.getFontMetrics().stringWidth(wordsArray[i] + " "); // Przesunięcie pozycji x
        }
    }

    
    public void setWordColor(int indeks, Color kolor) {
        if (indeks >= 0 && indeks < wordsColor.length) {
            wordsColor[indeks] = kolor;
            repaint(); //
        }
    }
}

