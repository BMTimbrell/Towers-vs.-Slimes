package com.game.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.enemies.EnemyType;
import com.game.towers.Tower;

public class Explosion extends FireBall {
	
	private int timer, damageTimer;

	public Explosion(Vector2f position, Tower tower, double angle) {
		super(position, tower, angle);
		image = Game.readImage("Explosion");
		timer = 20;
		damageTimer = 1;
		width = 75;
		height = 75;
		if (tower.getUpgrades() > 3) {
			width = 130;
			height = 130;
		}
	}
	
	public void tick(Game game) {
		collision(game);
		timer --;
		damageTimer--;
		if (timer < 1) {
			game.removeEntity(this);
		}
	}
	
	protected void collision(Game game) {
		for (Enemy enemy : game.getEnemies()) {
			if (getBounds().intersects(enemy.getBounds())) {
				if (damageTimer == 1) {
					damage(game, enemy);
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(image, position.toVec2i().getX() - (width / 2), position.toVec2i().getY() - (height / 2), width, height, null);
	}
	
	public int getDrawingPriority() {
		return 2;
	}
}
