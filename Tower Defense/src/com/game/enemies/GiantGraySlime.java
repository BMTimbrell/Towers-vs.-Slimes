package com.game.enemies;

import com.game.Game;

public class GiantGraySlime extends GraySlime {
	public GiantGraySlime() {
		name = "Giant Gray Slime";
		enemyType = EnemyType.GIANT;
		health *= 10;
		maxHealth = health;
		width *= 2;
		height *= 2;
		healthBarWidth = width;
		livesLost = 64;
		coinsAwarded = 10;
		speed = 1;
		baseSpeed = speed;
	}
	
	protected void spawnEnemies(Game game) {
		Enemy graySlime1 = new GraySlime();
		Enemy graySlime2 = new GraySlime();
		Enemy graySlime3 = new GraySlime();
		Enemy graySlime4 = new GraySlime();
		Enemy graySlime5 = new GraySlime();
		Enemy graySlime6 = new GraySlime();
		spawnEnemy(game, graySlime1, distance - 75);
		spawnEnemy(game, graySlime2, distance - 50);
		spawnEnemy(game, graySlime3, distance - 25);
		spawnEnemy(game, graySlime4, distance);
		spawnEnemy(game, graySlime5, distance + 25);
		spawnEnemy(game, graySlime6, distance + 50);
	}
}
