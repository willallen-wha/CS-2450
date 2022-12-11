package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.Taskbar;
import java.io.File;
import java.io.IOException;

import Common.*;
import GUI.Graphics.BackgroundPanel;

public class MainGUI extends JFrame {

    public static BackgroundPanel background;

    public MainGUI() {

        // Make sure OS is set
        AdjustOS.detectOS();

        // Set up look and feel of window
        this.setTitle("CS-2450 Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(AdjustOS.FRAMEWIDTH, 400);
        background = null;
        final Taskbar taskbar = Taskbar.getTaskbar();
        try {
            // Set the logo
            Image logo;
            logo = ImageIO.read(new File(getClass().getResource(AdjustOS.LOGOPATH).toURI()));
            this.setIconImage(logo);
            // Attempt to set the Apple dock as well
            if(AdjustOS.OS == AdjustOS.MAC) taskbar.setIconImage(logo);

            // Set the background
            Image backIm = ImageIO.read(new File(getClass().getResource(AdjustOS.BACKGROUNDPATH).toURI()));
            backIm = backIm.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unknown error occurred, using the default logo.");
        }
        // Create a new auth window to force sign-in
        AuthGUI auth = new AuthGUI(this);
        this.add(auth);
        this.setVisible(true);
    }

    /**
     * Static method to 'exit' a GUI mode. Should only be called internally from within the panel requesting closure.
     * Checks the type of the panel, then replaces the panel with the corresponding opposite option.
     * 
     * @param p The panel requesting closure.
     * 
     * @return None.
     */
    public void exit(JPanel p) {
        // Check requested close type
        // If it's an Auth GUI, auth is complete, replace it with a new Calc GUI
        if(p instanceof AuthGUI) {
            this.remove(p);
            CalcGUI calc = new CalcGUI(this);
            // Check to see if the background is set
            if(background != null) {
                this.add(background);
                background.add(calc);
            }
            else {
                calc.setBackground(new Color(0, 120, 216));
                this.add(calc);
            }
        }
        // If it's a Calc GUI, logout requested, replace it with a new Auth GUI
        else if(p instanceof CalcGUI) {
            AuthGUI auth = new AuthGUI(this);
            //Remove the background if valid
            if(background != null) {
                background.remove(p);
                this.remove(background);
            }
            else this.remove(p);
            this.add(auth);
        }
        this.validate();
    }
}