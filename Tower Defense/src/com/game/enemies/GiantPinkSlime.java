package com.game.enemies;

import com.game.Game;

public class GiantPinkSlime extends PinkSlime {
	public GiantPinkSlime() {
		name = "Giant Pink Slime";
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
		Enemy pinkSlime1 = new PinkSlime();
		Enemy pinkSlime2 = new PinkSlime();
		Enemy pinkSlime3 = new PinkSlime();
		Enemy pinkSlime4 = new PinkSlime();
		Enemy pinkSlime5 = new PinkSlime();
		Enemy pinkSlime6 = new PinkSlime();
		spawnEnemy(game, pinkSlime1, distance - 75);
		spawnEnemy(game, pinkSlime2, distance - 50);
		spawnEnemy(game, pinkSlime3, distance - 25);
		spawnEnemy(game, pinkSlime4, distance);
		spawnEnemy(game, pinkSlime5, distance + 25);
		spawnEnemy(game, pinkSlime6, distance + 50);
	}
}
