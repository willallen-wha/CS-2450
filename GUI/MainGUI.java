package GUI;

import javax.swing.*;

public class MainGUI {

    public static void main(String args[]){
        // Create a new calc window
        CalcGUI calc = new CalcGUI();
        JFrame frame = new JFrame("CS-2450 Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(calc);
        frame.setVisible(true);
    }
}