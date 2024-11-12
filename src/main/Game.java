package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 60;
    private final int UPS_SET = 200;
    private Player player;
    private LevelManager levelManager;

    public static final int TILES_DEFAULT_SIZE = 32, TILES_IN_WIDTH = 26, TILES_IN_HEIGHT = 14;
    public static final float SCALE = 1.5f;
    public static final int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public static final int GAME_WIDTH = TILES_IN_WIDTH * TILES_SIZE, GAME_HEIGHT = TILES_IN_HEIGHT * TILES_SIZE;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(200, 200);
        levelManager = new LevelManager(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
        levelManager.update();
    }

    public void render(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaUpdates = 0;
        double deltaFrames = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaUpdates += (currentTime - previousTime) / timePerUpdate;
            deltaFrames += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaUpdates >= 1) {
                update();
                updates++;
                deltaUpdates--;
            }

            if (deltaFrames >= 1) {
                gamePanel.repaint();
                frames++;
                deltaFrames--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " UPS: " + updates);
                frames = 0;
                updates = 0;
            }

        }

    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }

}
