package com.game.enemies;

import com.game.Game;

public class GiantBlueSlime extends BlueSlime {
	public GiantBlueSlime() {
		name = "Giant Blue Slime";
		enemyType = EnemyType.GIANT;
		health *= 10;
		maxHealth = health;
		width *= 2;
		height *= 2;
		healthBarWidth = width;
		livesLost = 32;
		coinsAwarded = 9;
	}
	
	protected void spawnEnemies(Game game) {
		Enemy blueSlime1 = new BlueSlime();
		Enemy blueSlime2 = new BlueSlime();
		Enemy blueSlime3 = new BlueSlime();
		Enemy blueSlime4 = new BlueSlime();
		Enemy blueSlime5 = new BlueSlime();
		Enemy blueSlime6 = new BlueSlime();
		spawnEnemy(game, blueSlime1, distance - 75);
		spawnEnemy(game, blueSlime2, distance - 50);
		spawnEnemy(game, blueSlime3, distance - 25);
		spawnEnemy(game, blueSlime4, distance);
		spawnEnemy(game, blueSlime5, distance + 25);
		spawnEnemy(game, blueSlime6, distance + 50);
	}
}
