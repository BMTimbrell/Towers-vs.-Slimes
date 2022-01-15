package com.game.enemies;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;

public class RedSlime extends Enemy {

	public RedSlime() {
		name = "Red Slime";
		health = 200;
		maxHealth = health;
		coinsAwarded = 7;
		livesLost = 4;
		image = Game.readImage("red_slime");
		speed = 1.5f;
		baseSpeed = speed;
	}
	
}
