package main;

import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    public GamePanel() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Hello World", 10, 10);
        g.fillRect(100, 100, 200, 50);
    }
}
