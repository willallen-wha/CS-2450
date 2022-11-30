package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class MainGUI {

    public static void main(String args[]) {

        // Set up look and feel of window
        JFrame frame = new JFrame("CS-2450 Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            Image logo;
            logo = ImageIO.read(new File("GUI\\Graphics\\Logo.png"));
            frame.setIconImage(logo);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Couldn't find file, using default logo.");
        }
        frame.setSize(500, 400);
        frame.setVisible(true);
        // Create a new auth window to force sign-in
        AuthGUI auth = new AuthGUI();
        frame.add(auth);

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
        //TODO Create code which switches between GUI options
    }
}