package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> gamePanel.changeY(-10);
            case KeyEvent.VK_A -> gamePanel.changeX(-10);
            case KeyEvent.VK_S -> gamePanel.changeY(10);
            case KeyEvent.VK_D -> gamePanel.changeX(10);
        }
    }
    
}
