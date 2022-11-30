package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import Common.*;

public class MainGUI {

    public static JFrame frame;

    public static void main(String args[]) {

        // Set up look and feel of window
        frame = new JFrame("CS-2450 Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Image logo;
            logo = ImageIO.read(new File(AdjustOS.LOGOPATH));
            frame.setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't find file, using default logo.");
        }
        frame.setSize(AdjustOS.FRAMEWIDTH, 400);
        // Create a new auth window to force sign-in
        AuthGUI auth = new AuthGUI();
        frame.add(auth);
        frame.setVisible(true);

    }

    /**
     * Static method to 'exit' a GUI mode. Should only be called internally from within the panel requesting closure.
     * Checks the type of the panel, then replaces the panel with the corresponding opposite option.
     * 
     * @param p The panel requesting closure.
     * 
     * @return None.
     */
    public static void exit(JPanel p) {
        // Check requested close type
        // If it's an Auth GUI, auth is complete, replace it with a new Calc GUI
        frame.remove(p);
        if(p instanceof AuthGUI) {
            CalcGUI calc = new CalcGUI();
            frame.add(calc);
        }
        // If it's a Calc GUI, logout requested, replace it with a new Auth GUI
        else if(p instanceof CalcGUI) {
            AuthGUI auth = new AuthGUI();
            frame.add(auth);
        }
        frame.validate();
    }
}