package com.game.projectiles;

import java.awt.*;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.enemies.EnemyType;
import com.game.towers.Tower;

public class FireBall extends Projectile {
	
	private double angle;

	public FireBall(Vector2f position, Tower tower, double angle) {
		super(position, tower);
		this.angle = angle;
		image = Game.readImage("Fire Ball");
	}

	protected void collision(Game game) {
		for (Enemy enemy : game.getEnemies()) {
			if (getBounds().intersects(enemy.getBounds())) {
				if (tower.getUpgrades() < 2) {
					damage(game, enemy);
					game.removeEntity(this);
					break;
				} else {
					Explosion explosion = new Explosion(new Vector2f(
							enemy.getPosition().getX(),
							enemy.getPosition().getY()), tower, angle);
					game.addEntity(explosion);
					game.removeEntity(this);
					break;
				}
			}
		}
	}

	public void render(Graphics g) {
		Projectile.drawImageWithRotation((Graphics2D) g, image, position, angle);
	}
	
	protected void damage(Game game, Enemy enemy) {
		if (enemy.isFrozen() && enemy.getFireResFreezeDuration() <= 0) enemy.thaw();
		enemy.burn();
		enemy.setDamageTaken(tower.getDamage());
		enemy.setHealth();
	}
}
