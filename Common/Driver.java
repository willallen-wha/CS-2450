package Common;

import GUI.MainGUI;

public class Driver {
    
    public static void main(String[] args) {

        // Set the OS family correctly
        AdjustOS.detectOS();

        // Start the MainGUI family of actions
        MainGUI.main(args);
    }

}
