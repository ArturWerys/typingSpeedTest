package tst;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawTekstZBazyDanych extends JPanel {
    public String getPaintedText() {
		return paintedText;
	}
    
	
	public String paintedText = "Lorem ipsum dolor sit amet";
    
	public String[] tablicaSlow = paintedText.split("\\s+");
    
    public Color[] kolorySlow = new Color[tablicaSlow.length];
    
    Font czcionkaSlowDoNapisania = new Font("Arial", Font.PLAIN, 20);


    public DrawTekstZBazyDanych() {
		// TODO Auto-generated constructor stub
    	JPanel panelTekstuZbazyDanych = new JPanel();
    	Dimension panelTekstuDimension = new Dimension(10,10);
    	panelTekstuZbazyDanych.setPreferredSize(panelTekstuDimension);;
    	
    	
        panelTekstuZbazyDanych.setBackground(ThemeColors.TEXT_FIELD_BACKGROUND);
       
	}
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setFont(czcionkaSlowDoNapisania);
        
        int x = 20;
        int y = 50;
        for (int i = 0; i < tablicaSlow.length; i++) {
            g.setColor(kolorySlow[i] != null ? kolorySlow[i] : Color.BLACK); // Ustawienie aktualnego koloru tekstu lub domyślnie na czarny
            g.drawString(tablicaSlow[i], x, y); // Rysowanie słowa
            x += g.getFontMetrics().stringWidth(tablicaSlow[i] + " "); // Przesunięcie pozycji x
        }
    }

    
    public void ustawKolorSlowa(int indeks, Color kolor) {
        if (indeks >= 0 && indeks < kolorySlow.length) {
            kolorySlow[indeks] = kolor;
            repaint(); //
        }
    }
}

