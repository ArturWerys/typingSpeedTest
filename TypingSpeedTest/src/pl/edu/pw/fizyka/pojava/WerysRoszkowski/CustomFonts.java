package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class CustomFonts {
    public static Font TITLE_FONT;
    public static Font BUTTON_FONT;

    static {
        try {
            TITLE_FONT = CustomFontLoader.loadFont("Poppins", "Poppins-ExtraBold.ttf");
            BUTTON_FONT = CustomFontLoader.loadFont("Poppins", "Poppins-Regular.ttf");
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            TITLE_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
            BUTTON_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        }
    }
}