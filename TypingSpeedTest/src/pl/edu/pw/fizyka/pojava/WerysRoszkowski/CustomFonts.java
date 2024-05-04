package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class CustomFonts {
	public static Font FALLBACK_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    public static Font TITLE_FONT;
    public static Font BUTTON_FONT;
    public static Font TEXT_PANE_FONT;
    public static Font RSLTS_STATS_NAME_FONT;
    public static Font RSLTS_STATS_COUNT_FONT;

    static {
        try {
            TITLE_FONT = CustomFontLoader.loadFont("Poppins", "Poppins-ExtraBold.ttf");
            BUTTON_FONT = CustomFontLoader.loadFont("Poppins", "Poppins-Regular.ttf");
            TEXT_PANE_FONT = CustomFontLoader.loadFont("Poppins", "Poppins-Regular.ttf");
            RSLTS_STATS_NAME_FONT = CustomFontLoader.loadFont("Poppins", "Poppins-Regular.ttf");
            RSLTS_STATS_COUNT_FONT = CustomFontLoader.loadFont("Poppins", "Poppins-ExtraBold.ttf");
            
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            
            TITLE_FONT = FALLBACK_FONT;
            BUTTON_FONT = FALLBACK_FONT;
            RSLTS_STATS_NAME_FONT = FALLBACK_FONT;
            RSLTS_STATS_COUNT_FONT = FALLBACK_FONT;
        }
    }
}