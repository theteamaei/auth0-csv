package com.auth0.trf;

import com.auth0.trf.swing.ui.MainFrame;

import javax.swing.JFrame;
import java.awt.Color;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void run(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,165);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main( String[] args ) {
        run(new MainFrame());
    }
}
