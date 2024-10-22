package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60;

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime(), now;

        int fps = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            now = System.nanoTime();

            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                fps++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + fps);
                fps = 0;
            }

        }

    }

}