package com.game.projectiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.enemies.EnemyType;
import com.game.enemies.*;
import com.game.towers.Tower;

public class IceBall extends Projectile {

	protected int freezeDuration;
	
	public IceBall(Vector2f position, Tower tower) {
		super(position, tower);
		freezeDuration = 300;
		image = Game.readImage("Ice Ball");
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public void render(Graphics g) {
		g.drawImage(image, position.toVec2i().getX() - (width / 2), position.toVec2i().getY() - (height / 2), width, height, null);
	}

	protected void collision(Game game) {
		if (tower.getUpgrades() > 0)
			freezeDuration = 500;
		for (Enemy enemy : game.getEnemies()) {
			if (getBounds().intersects(enemy.getBounds())) {
				if (pierce > 1) {
					if (!enemies.contains(enemy)) {
						enemies.add(enemy);
						// Slow healing
						if (tower.getUpgrades() > 2) {
							if (enemy instanceof PinkSlime) enemy.setSlowHeal();	
						}
						// Freeze giant enemies only if right upgrade
						if (enemy.getType() != EnemyType.GIANT || tower.getUpgrades() > 3) {
							if (enemy.getFreezeDuration() < freezeDuration || enemy.getFireResFreezeDuration() < freezeDuration) {
								enemy.freeze(freezeDuration, tower);
							}
						}
						// Remove defense buffs if final upgrade
						if (tower.getUpgrades() > 4 && enemy.hasDefenseBuff()) {
							enemy.pierceArmour();
						}
						enemy.setDamageTaken(tower.getDamage());
						enemy.setHealth();
						if (enemies.size() == pierce) {
							game.removeEntity(this);
						}
						return;
					} else {
						return;
					}
				}
				if (enemy.getType() != EnemyType.GIANT && (!enemy.isBurning() || tower.getUpgrades() > 2)) {
					if (enemy.getFreezeDuration() < freezeDuration) enemy.freeze(freezeDuration, tower);	
				}
				enemy.setDamageTaken(tower.getDamage());
				enemy.setHealth();
				game.removeEntity(this);
				break;
			}
		}
	}

}
