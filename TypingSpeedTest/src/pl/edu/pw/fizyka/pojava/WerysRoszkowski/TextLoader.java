package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TextLoader {

	private static String predefinedText = "";

	public static String loadText(String textFilePath) {
        try {
            InputStream inputStream = Words30Panels.class.getResourceAsStream("/" + textFilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();
            String st;
            while ((st = br.readLine()) != null) {
                stringBuilder.append(st).append("\n"); 
            }

             predefinedText = stringBuilder.toString(); 

            br.close(); 

            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return predefinedText;
    }
}
