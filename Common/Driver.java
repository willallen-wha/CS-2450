package Common;

import GUI.MainGUI;

public class Driver {
    
    public static void main(String[] args) {

        // Set the OS family correctly
        AdjustOS.detectOS();

        // Create a new MainGUI object
        @SuppressWarnings("unused")
        MainGUI start = new MainGUI();
    }

}
