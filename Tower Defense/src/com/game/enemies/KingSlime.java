package com.game.enemies;

import java.awt.Image;

import javax.swing.ImageIcon;

import com.game.Game;

public class KingSlime extends GreenSlime {
	private Image normal, panic, crying;
	public KingSlime() {
		image = Game.readImage("King Slime");
		width = image.getWidth(null);
		height = image.getHeight(null);
		name = "King Slime";
		enemyType = EnemyType.GIANT;
		health = 150000;
		livesLost = 10000;
		coinsAwarded = 1000;
		maxHealth = health;
		healthBarWidth = width;
		speed = 0.8f;
		baseSpeed = speed;
		frozenSpeed = speed / 2;
		normal = Game.readImage("King Slime");
		panic = Game.readImage("King Slime Panic");
		crying = Game.readImage("King Slime Crying");
	}
	
	protected void move() {
		this.distance += speed;
		if (health > maxHealth / 2) image = normal;
		else if (health < maxHealth / 3) image = crying;
		else if (health < maxHealth / 2) image = panic;
	}
	
	protected void spawnEnemies(Game game) {
		Enemy giantRainbowSlime1 = new GiantRainbowSlime();
		Enemy giantRainbowSlime2 = new GiantRainbowSlime();
		Enemy giantRainbowSlime3 = new GiantRainbowSlime();
		Enemy giantRainbowSlime4 = new GiantRainbowSlime();
		Enemy giantRainbowSlime5 = new GiantRainbowSlime();
		Enemy giantRainbowSlime6 = new GiantRainbowSlime();
		spawnEnemy(game, giantRainbowSlime1, distance - 75);
		spawnEnemy(game, giantRainbowSlime2, distance - 50);
		spawnEnemy(game, giantRainbowSlime3, distance - 25);
		spawnEnemy(game, giantRainbowSlime4, distance);
		spawnEnemy(game, giantRainbowSlime5, distance + 25);
		spawnEnemy(game, giantRainbowSlime6, distance + 50);
	}
}
