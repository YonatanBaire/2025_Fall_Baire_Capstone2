package org.example;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[] options = {"Console Interface", "GUI Interface"};
        int choice = JOptionPane.showOptionDialog(null,
            "Welcome to TACO-licious!\nChoose your interface:",
            "TACO-licious",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[1]);

        if (choice == 0) {
            // Console Interface
            UserInterface ui = new UserInterface();
            ui.run();
        } else if (choice == 1) {
            // GUI Interface
            SwingUtilities.invokeLater(() -> new UserInterfaceGUI());
        } else {
            System.out.println("No selection made. Exiting.");
        }
    }
}