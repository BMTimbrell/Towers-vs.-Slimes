package com.game.enemies;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;

public class GreenSlime extends Enemy {

	public GreenSlime() {
		name = "Green Slime";
		health = 50;
		maxHealth = health;
		coinsAwarded = 4;
		livesLost = 1;
		image = Game.readImage("slime");
	}	
}
