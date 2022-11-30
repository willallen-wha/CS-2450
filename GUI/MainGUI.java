package GUI;

import javax.swing.*;

public class MainGUI {

    public static void main(String args[]){
        // Create a new auth window to force sign-in
        AuthGUI auth = new AuthGUI();
        JFrame frame = new JFrame("CS-2450 Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
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
        //TODO Create code which switches between GUI options
    }
}