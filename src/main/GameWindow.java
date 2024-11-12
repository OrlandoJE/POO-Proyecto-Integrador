package main;

import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
    private final JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame("Game Window");

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);

        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent e) {
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
