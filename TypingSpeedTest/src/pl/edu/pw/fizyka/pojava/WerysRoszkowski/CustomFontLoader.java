package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomFontLoader {
    private static final Map<String, Font> loadedFonts = new HashMap<>();

    public static Font loadFont(String fontFamily, String fontFileName) throws IOException, FontFormatException {
        String fontPath = "lib" + File.separator + "Fonts" + File.separator + fontFamily + File.separator + fontFileName;
        if (loadedFonts.containsKey(fontPath)) {
            return loadedFonts.get(fontPath);
        } else {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            loadedFonts.put(fontPath, font);
            return font;
        }
    }
}