package com.game.enemies;

import com.game.Game;

public class BlueSlime extends Enemy {

	public BlueSlime() {
		name = "Blue Slime";
		health = 100;
		maxHealth = health;
		coinsAwarded = 5;
		livesLost = 2;
		image = Game.readImage("blue_slime");
	}
	
}
