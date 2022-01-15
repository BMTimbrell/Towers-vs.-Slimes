package com.game.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Game;

public class GraySlime extends BlueSlime {

	protected Ellipse2D defenseRangeRadius;
	protected int defenseRange = Commons.AURA_RANGE;

	public GraySlime() {
		name = "Gray Slime";
		speed = 1.2f;
		baseSpeed = speed;
		image = Game.readImage("gray_slime");
		coinsAwarded = 7;
		livesLost = 4;
	}

	protected void move() {
		if (!armourPierced) {
			defenseBuff = true;
		} else {
			defenseBuff = false;
		}
		this.distance += speed;
	}
}
