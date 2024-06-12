package pl.edu.pw.fizyka.pojava.WerysRoszkowski;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCobalt2IJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkHardIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatHighContrastIJTheme;

public class TstMenuBar extends JMenuBar {

    public TstMenuBar(boolean returnToWelcomeScreen, boolean changeTheme, JFrame component) {
        JMenu mnMainMenu = new JMenu("Menu");
        add(mnMainMenu);

        JMenuItem mntmReturn = new JMenuItem("Powrót do ekranu startowego");
        if (returnToWelcomeScreen) {
            mnMainMenu.add(mntmReturn);
        }

        JMenu mnThemeChooser = new JMenu("Motywy");

        if (changeTheme) {
            add(mnThemeChooser);
            addThemeMenuItem(mnThemeChooser, "FlatLaf Light", new FlatLightLaf(), component);
            addThemeMenuItem(mnThemeChooser, "FlatLaf Dark", new FlatMacDarkLaf(), component);
            addThemeMenuItem(mnThemeChooser, "Arc Orange", new FlatArcOrangeIJTheme(), component);
            addThemeMenuItem(mnThemeChooser, "Cobalt", new FlatCobalt2IJTheme(), component);
            addThemeMenuItem(mnThemeChooser, "Dark Purple", new FlatDarkPurpleIJTheme(), component);
            addThemeMenuItem(mnThemeChooser, "Nature Green", new FlatNordIJTheme(), component);
            addThemeMenuItem(mnThemeChooser, "Gruvbox Dark Hard", new FlatGruvboxDarkHardIJTheme(), component);
            addThemeMenuItem(mnThemeChooser, "High Contrast", new FlatHighContrastIJTheme(), component);
        }

        JMenuItem mntmExit = new JMenuItem("Zamknij program");
        mnMainMenu.add(mntmExit);

        mntmReturn.addActionListener(e -> {
            WelcomeWindow welcomeWindow = new WelcomeWindow();
            welcomeWindow.requestFocus();
            welcomeWindow.setFocusableWindowState(true);
            ((Window) component).dispose();
        });

        mntmExit.addActionListener(e -> System.exit(0));
    }

    private void addThemeMenuItem(JMenu menu, String themeName, LookAndFeel laf, JFrame component) {
        JMenuItem menuItem = new JMenuItem(themeName);
        menu.add(menuItem);
        menuItem.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(laf);
                SwingUtilities.updateComponentTreeUI(component);
                component.repaint();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
        });
    }
}
