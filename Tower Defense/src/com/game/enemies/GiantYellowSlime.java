package com.game.enemies;

import com.game.Game;

public class GiantYellowSlime extends YellowSlime {
	public GiantYellowSlime() {
		name = "Giant Yellow Slime";
		enemyType = EnemyType.GIANT;
		health *= 10;
		maxHealth = health;
		width *= 2;
		height *= 2;
		healthBarWidth = width;
		livesLost = 38;
		coinsAwarded = 9;
		speed = 1;
		baseSpeed = speed;
	}
	
	protected void spawnEnemies(Game game) {
		Enemy yellowSlime1 = new YellowSlime();
		Enemy yellowSlime2 = new YellowSlime();
		Enemy yellowSlime3 = new YellowSlime();
		Enemy yellowSlime4 = new YellowSlime();
		Enemy yellowSlime5 = new YellowSlime();
		Enemy yellowSlime6 = new YellowSlime();
		spawnEnemy(game, yellowSlime1, distance - 75);
		spawnEnemy(game, yellowSlime2, distance - 50);
		spawnEnemy(game, yellowSlime3, distance - 25);
		spawnEnemy(game, yellowSlime4, distance);
		spawnEnemy(game, yellowSlime5, distance + 25);
		spawnEnemy(game, yellowSlime6, distance + 50);
	}
}
