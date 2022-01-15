package com.game.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.towers.Tower;

public class CannonBall extends Projectile {

	public CannonBall(Vector2f position, Tower tower) {
		super(position, tower);
		range = tower.getRange() / 2;
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(position.toVec2i().getX() - (width / 2), position.toVec2i().getY() - (height / 2), width, height);
	}
}
