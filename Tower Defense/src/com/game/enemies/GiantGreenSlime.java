package com.game.enemies;

import com.game.Game;

public class GiantGreenSlime extends GreenSlime {
	public GiantGreenSlime() {
		name = "Giant Green Slime";
		enemyType = EnemyType.GIANT;
		health *= 10;
		maxHealth = health;
		width *= 2;
		height *= 2;
		healthBarWidth = width;
		livesLost = 16;
		coinsAwarded = 8;
	}
	
	protected void spawnEnemies(Game game) {
		Enemy greenSlime1 = new GreenSlime();
		Enemy greenSlime2 = new GreenSlime();
		Enemy greenSlime3 = new GreenSlime();
		Enemy greenSlime4 = new GreenSlime();
		Enemy greenSlime5 = new GreenSlime();
		Enemy greenSlime6 = new GreenSlime();
		spawnEnemy(game, greenSlime1, distance - 75);
		spawnEnemy(game, greenSlime2, distance - 50);
		spawnEnemy(game, greenSlime3, distance - 25);
		spawnEnemy(game, greenSlime4, distance);
		spawnEnemy(game, greenSlime5, distance + 25);
		spawnEnemy(game, greenSlime6, distance + 50);
	}
}
