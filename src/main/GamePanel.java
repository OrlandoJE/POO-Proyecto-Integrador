package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int x = 100, y = 100;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseMotionListener(mouseInputs);
        addMouseListener(mouseInputs);
    }

    public void setRectPos(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    public void changeX(int xDelta) {
        this.x += xDelta;
        repaint();
    }

    public void changeY(int yDelta) {
        this.y += yDelta;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect(x, y, 200, 50);
    }
}
