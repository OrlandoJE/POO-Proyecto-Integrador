package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private float x = 100, y = 100, xDir = 1, yDir = 1;
    private Color color = new Color(255, 255, 255);
    private Random random;

    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseMotionListener(mouseInputs);
        addMouseListener(mouseInputs);
    }

    public void setRectPos(int x, int y) {
        this.x = x;
        this.y = y;    
    }

    public void changeX(int xDelta) {
        this.x += xDelta;
    }

    public void changeY(int yDelta) {
        this.y += yDelta;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateRectangle();
        g.setColor(color);
        g.fillRect((int)x, (int)y, 200, 50);
    }

    private void updateRectangle() {
        x += xDir;
        if (x > 800 || x < 0) {
            xDir *= -1;
            color = getRndColor();
        }
        y += yDir;
        if (y > 600 || y < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
