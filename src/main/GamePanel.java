package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private float x = 100, y = 100;
    private BufferedImage img, subImage;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        setPanelSize();

        importImage();

        addKeyListener(new KeyboardInputs(this));
        addMouseMotionListener(mouseInputs);
        addMouseListener(mouseInputs);
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/res/player_sprites.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            // e.printStackTrace();
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
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

        subImage = img.getSubimage(1*64, 1*40, 64, 40);

        g.drawImage(subImage, (int) x, (int) y, 128, 80, null);
    }
}
