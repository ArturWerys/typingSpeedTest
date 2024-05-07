package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

import java.awt.Color;
import java.util.Map;

public class ListFlatLafColors {

    public static void main(String[] args) {
    	FlatLightLaf.setup();
    	
        UIDefaults defaults = UIManager.getDefaults();
        for (Map.Entry<Object, Object> entry : defaults.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key instanceof String && value instanceof Color) {
                System.out.println("Key: " + key + ", Color: " + value);
            }
        }
    }
}
