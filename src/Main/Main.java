package Main;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("The game Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Panel panel = new Panel();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.launchGame();
    }
}