package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import main.MainClass;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

	private Playing playing;
	private BufferedImage[][] crabbyArr;
	private ArrayList<Crab> crabbies = new ArrayList<>();

	public EnemyManager(Playing playing) {
		this.playing = playing;
		loadEnemyImgs();
	}

	public void loadEnemies(Level level) {
		crabbies = level.getCrabs();
	}

	public void update(int[][] lvlData, Player player) {
		if (MainClass.areThereEnemies) {
			boolean isAnyActive = false;
			if (MainClass.areThereEnemies) {
				for (Crab crab : crabbies)
					if (crab.isActive()) {
						crab.update(lvlData, player);
						isAnyActive = true;
					}
			}
			if (!isAnyActive)
				playing.setLevelCompleted(true);
		}
	}

	public void draw(Graphics g, int xLvlOffset) {
		drawCrabs(g, xLvlOffset);
	}

	private void drawCrabs(Graphics g, int xLvlOffset) {
		if (MainClass.areThereEnemies) {
			for (Crab c : crabbies)
				if (c.isActive()) {
					g.drawImage(crabbyArr[c.getState()][c.getAnimationIndex()],
							(int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(),
							(int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y,
							CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
				}
		}
	}

	public void checkEnemyHit(Rectangle2D.Float attackBox) {
		if (MainClass.areThereEnemies) {
			for (Crab c : crabbies)
				if (c.isActive())
					if (attackBox.intersects(c.getHitbox())) {
						c.hurt(MainClass.playerAttackDamage);
						return;
					}
		}
	}

	private void loadEnemyImgs() {
		crabbyArr = new BufferedImage[5][9];
		BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
		for (int j = 0; j < crabbyArr.length; j++)
			for (int i = 0; i < crabbyArr[j].length; i++)
				crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT,
						CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
	}

	public void resetAllEnemies() {
		if (MainClass.areThereEnemies) {
			for (Crab c : crabbies)
				c.resetEnemy();
		}
	}

}
