/*
Author: Oleksandr Danchenko
time spent: 5 minutes
version #1
*/

import frame.ApplicationFrame;
import panels.CustomPanel;
import panels.*;

import javax.swing.*;

/**
 * The Main class, acts as a driver to the program.
 *
 * @author Oleksandr Danchenko
 */
public class Main {
    /**
     * The main method, entry point to the program.
     *
     * @author Oleksandr Danchenko
     */
    public static void main(String[] args) {
        new ApplicationFrame();

        JFrame frame = new JFrame();
        frame.setSize(1400, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}