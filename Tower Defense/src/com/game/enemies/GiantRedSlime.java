package com.game.enemies;

import com.game.Game;

public class GiantRedSlime extends RedSlime {
	public GiantRedSlime() {
		name = "Giant Red Slime";
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
		Enemy redSlime1 = new RedSlime();
		Enemy redSlime2 = new RedSlime();
		Enemy redSlime3 = new RedSlime();
		Enemy redSlime4 = new RedSlime();
		Enemy redSlime5 = new RedSlime();
		Enemy redSlime6 = new RedSlime();
		spawnEnemy(game, redSlime1, distance - 75);
		spawnEnemy(game, redSlime2, distance - 50);
		spawnEnemy(game, redSlime3, distance - 25);
		spawnEnemy(game, redSlime4, distance);
		spawnEnemy(game, redSlime5, distance + 25);
		spawnEnemy(game, redSlime6, distance + 50);
	}

}
