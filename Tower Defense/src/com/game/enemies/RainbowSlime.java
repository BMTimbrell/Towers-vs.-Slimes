package com.game.enemies;

import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Game;

public class RainbowSlime extends PinkSlime {
	
	public RainbowSlime() {
		name = "Rainbow Slime";
		speed = 2;
		baseSpeed = speed;
		health = 200;
		maxHealth = health;
		image = Game.readImage("rainbow_slime");
		livesLost = 26;
		coinsAwarded = 8;
	}
	
	protected void move() {
		if (!armourPierced) {
			defenseBuff = true;
		} else {
			defenseBuff = false;
		}
		this.distance += speed;
		healPulse();
	}
	
	protected void collision(Game game) {
		// Collision with end of path
		if (position.getY() < Commons.MAP_TOP_POS || position.getX() > Commons.MAP_END_POS
				|| (distance > 100 && position.getY() > Commons.MAP_BOTTOM_POS)) {
			game.getEnemies().remove(this);
			game.removeEntity(this);
			game.loseLives(livesLost);
		}
		// Heal enemies in range
		applyHeal(game);
	}
	
	protected void spawnEnemies(Game game) {
		Enemy redSlime = new RedSlime();
		Enemy yellowSlime = new YellowSlime();
		Enemy greenSlime = new GreenSlime();
		Enemy graySlime = new GraySlime();
		Enemy blueSlime = new BlueSlime();
		Enemy pinkSlime = new PinkSlime();
		spawnEnemy(game, redSlime, distance - 75);
		spawnEnemy(game, yellowSlime, distance - 50);
		spawnEnemy(game, greenSlime, distance - 25);
		spawnEnemy(game, graySlime, distance);
		spawnEnemy(game, blueSlime, distance + 25);
		spawnEnemy(game, pinkSlime, distance + 50);
	}
}
