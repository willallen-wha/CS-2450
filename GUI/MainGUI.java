package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.awt.Taskbar;
import java.io.File;
import java.io.IOException;

import Common.*;
import GUI.Graphics.BackgroundPanel;

public class MainGUI {

    public static JFrame frame;
    public static BackgroundPanel background;

    public static void main(String args[]) {

        // Make sure OS is set
        AdjustOS.detectOS();

        // Set up look and feel of window
        frame = new JFrame("CS-2450 Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(AdjustOS.FRAMEWIDTH, 400);
        background = null;
        final Taskbar taskbar = Taskbar.getTaskbar();
        try {
            // Set the logo
            Image logo;
            logo = ImageIO.read(new File(AdjustOS.LOGOPATH));
            frame.setIconImage(logo);
            // Attempt to set the Apple dock as well
            if(AdjustOS.OS == AdjustOS.MAC) taskbar.setIconImage(logo);

            // Set the background
            Image backIm = ImageIO.read(new File(AdjustOS.BACKGROUNDPATH));
            backIm = backIm.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
            background = new BackgroundPanel(backIm);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't find file, using default logo.");
        } catch (SecurityException e) {
            e.printStackTrace();
            System.out.println("Security error setting the logo, using the default logo.");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
            System.out.println("Error detecting the OS, using default logo.");
        }
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
        if(p instanceof AuthGUI) {
            frame.remove(p);
            CalcGUI calc = new CalcGUI();
            // Check to see if the background is set
            if(background != null) {
                frame.add(background);
                background.add(calc);
            }
            else frame.add(calc);
        }
        // If it's a Calc GUI, logout requested, replace it with a new Auth GUI
        else if(p instanceof CalcGUI) {
            AuthGUI auth = new AuthGUI();
            //Remove the background if valid
            if(background != null) {
                background.remove(p);
                frame.remove(background);
            }
            else frame.remove(p);
            frame.add(auth);
        }
        frame.validate();
    }
}