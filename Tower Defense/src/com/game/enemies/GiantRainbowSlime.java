package com.game.enemies;

import com.game.Game;

public class GiantRainbowSlime extends RainbowSlime {
	
	public GiantRainbowSlime() {
		name = "Giant Rainbow Slime";
		speed = 1;
		baseSpeed = speed;
		enemyType = EnemyType.GIANT;
		health *= 10;
		maxHealth = health;
		width *= 2;
		height *= 2;
		healthBarWidth = width;
		livesLost = 236;
		coinsAwarded = 12;
	}
	
	protected void spawnEnemies(Game game) {
		Enemy rainbowSlime1 = new RainbowSlime();
		Enemy rainbowSlime2 = new RainbowSlime();
		Enemy rainbowSlime3 = new RainbowSlime();
		Enemy rainbowSlime4 = new RainbowSlime();
		Enemy rainbowSlime5 = new RainbowSlime();
		Enemy rainbowSlime6 = new RainbowSlime();
		spawnEnemy(game, rainbowSlime1, distance - 75);
		spawnEnemy(game, rainbowSlime2, distance - 50);
		spawnEnemy(game, rainbowSlime3, distance - 25);
		spawnEnemy(game, rainbowSlime4, distance);
		spawnEnemy(game, rainbowSlime5, distance + 25);
		spawnEnemy(game, rainbowSlime6, distance + 50);
	}
}
