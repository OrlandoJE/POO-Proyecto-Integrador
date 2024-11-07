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
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {
    private final MouseInputs mouseInputs;
    private float x = 100, y = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        setPanelSize();

        importImage();
        loadAnimations();

        addKeyListener(new KeyboardInputs(this));
        addMouseMotionListener(mouseInputs);
        addMouseListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[9][6];

        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
            }
        }
    }

    private void importImage() {
        InputStream is = getClass().getResourceAsStream("/res/player_sprites.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int directon) {
        this.playerDirection = directon;
        this.moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    private void updateAnimationTick() {
        animationTick++;
        if (animationTick > animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    x -= 5;
                    break;
                case UP:
                    y -= 5;
                    break;
                case RIGHT:
                    x += 5;
                    break;
                case DOWN:
                    y += 5;
                    break;
            }
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePosition() {
        if (playerDirection == LEFT) {
            x -= 1;
        } else if (playerDirection == RIGHT) {
            x += 1;
        }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 128, 80, null);
    }
}
