package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.FpsButton;
import ui.PauseButton;
import ui.UrmButton;
import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class GameOptions extends State implements Statemethods {

	private BufferedImage backgroundImg, optionsBackgroundImg;
	private int bgX, bgY, bgW, bgH;
	private UrmButton menuB;
	private FpsButton fps60Button;
	private FpsButton fps144Button;

	public GameOptions(Game game) {
		super(game);
		loadImgs();
		loadButton();
	}

	private void loadButton() {
		int menuX = (int) (387 * Game.SCALE);
		int menuY = (int) (325 * Game.SCALE);

		menuB = new UrmButton(menuX, menuY + 25, URM_SIZE, URM_SIZE, 2);
		fps60Button = new FpsButton(menuX - 100, menuY - 85, URM_SIZE, URM_SIZE, 0);
		fps144Button = new FpsButton(menuX + 100, menuY - 85, URM_SIZE, URM_SIZE, 1);
	}

	private void loadImgs() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
		optionsBackgroundImg = LoadSave.GetSpriteAtlas(LoadSave.OPTIONS_MENU);

		bgW = (int) (optionsBackgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (optionsBackgroundImg.getHeight() * Game.SCALE);
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = (int) (33 * Game.SCALE);
	}

	@Override
	public void update() {
		menuB.update();
		fps60Button.update();
		fps144Button.update();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.drawImage(optionsBackgroundImg, bgX, bgY, bgW, bgH, null);

		menuB.draw(g);
		fps60Button.draw(g);
		fps144Button.draw(g);
	}

	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (isIn(e, menuB)) {
			menuB.setMousePressed(true);
		} else if (isIn(e, fps60Button)) {
			fps60Button.setMousePressed(true);
		} else if (isIn(e, fps144Button)) {
			fps144Button.setMousePressed(true);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isIn(e, menuB)) {
			if (menuB.isMousePressed())
				Gamestate.state = Gamestate.MENU;
		} else if (isIn(e, fps60Button)) {
			if (fps60Button.isMousePressed())
				game.setFps(60);
		} else if (isIn(e, fps144Button)) {
			if (fps144Button.isMousePressed())
				game.setFps(144);
		}

		menuB.resetBools();
		fps60Button.resetBools();
		fps144Button.resetBools();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		menuB.setMouseOver(false);
		fps60Button.setMouseOver(false);
		fps144Button.setMouseOver(false);

		if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, fps60Button))
			fps60Button.setMouseOver(true);
		else if (isIn(e, fps144Button))
			fps144Button.setMouseOver(true);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			Gamestate.state = Gamestate.MENU;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private boolean isIn(MouseEvent e, PauseButton b) {
		return b.getBounds().contains(e.getX(), e.getY());
	}

}
