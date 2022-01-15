package com.game.towers;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.gui.Block;
import com.game.projectiles.*;

public final class IceTower extends Tower {
	
	private boolean shootFrozen;
	private Vector2f upgradedProjectilePosition;
	
	public IceTower(Vector2f position, int width, int height, TowerType towerType, Block block) {
		super(position, width, height, towerType, block);
		damage = 25;
		attackSpeed = 60;
		shootFrozen = true;
		finalUpgradeSprite = Game.readImage("Upgraded Ice Tower");
		projectilePosition = projectilePosition.sub(new Vector2f(0, 45));
		upgradedProjectilePosition = projectilePosition.sub(new Vector2f(0, 5));
	}
	
	public void tick(Game game) {
		towerRangeRadius = new Ellipse2D.Double(block.getX() - ((range / 2) - (Commons.BLOCK_SIZE / 2)), block.getY() - ((range / 2) - ( Commons.BLOCK_SIZE / 2)), range, range);
		showUpgrade();
		
		// Reload while no enemies in range
		if (!enemyInRange && attackTimer > 0 && attackTimer < attackSpeed)
			attackTimer--;
		
		// Shoot first enemy in range
		if (priority == 0) {
			for (Enemy enemy : game.getEnemies()) {
				shootFrozen = true;
				if (enemy.isFrozen()) continue;
				if (towerRangeRadius.intersects(enemy.getRangeBounds())) {
					enemyInRange = true;
					shoot(game, enemy);
					shootFrozen = false;
					break;
				} else {
					enemyInRange = false;
				}
			}
			
			// Shoot enemies if only frozen ones in range
			for (Enemy enemy : game.getEnemies()) {
				if (towerRangeRadius.intersects(enemy.getRangeBounds())) {
					enemyInRange = true;
					if (shootFrozen) {
						shoot(game, enemy);
						break;
					}
					break;
				} else {
					enemyInRange = false;
				}
			}
		} else if (priority == 1) {
			// Shoot last enemy in range
			for (int i = game.getEnemies().size() - 1; i >= 0; i--) {
				shootFrozen = true;
				if (game.getEnemies().get(i).isFrozen()) continue;
				if (towerRangeRadius.intersects(game.getEnemies().get(i).getRangeBounds())) {
					enemyInRange = true;
					shoot(game, game.getEnemies().get(i));
					shootFrozen = false;
					break;
				} else {
					enemyInRange = false;
				}
			}
			
			// Shoot enemies if only frozen ones in range
			for (int i = game.getEnemies().size() - 1; i >= 0; i--) {
				if (towerRangeRadius.intersects(game.getEnemies().get(i).getRangeBounds())) {
					enemyInRange = true;
					if (shootFrozen) {
						shoot(game, game.getEnemies().get(i));
						break;
					}
					break;
				} else {
					enemyInRange = false;
				}
			}
		}
		
		if (upgrades == 5) {
			image = finalUpgradeSprite;
			projectilePosition = upgradedProjectilePosition;
		}
	}

	protected void shoot(Game game, Enemy enemy) {
		if (attackTimer == 0)
			attackTimer = attackSpeed;

		if (attackTimer == attackSpeed) {
			Projectile projectile;
			firingPosition = enemy.calculatePosition(enemy.getDistance() + 25, game.getMap());
			if (upgrades < 2) {
				projectile = new IceBall(projectilePosition, this);
			} else {
				double angle = Math.atan2(firingPosition.getY() - projectilePosition.getY(), firingPosition.getX() - projectilePosition.getX());
				projectile = new Icicle(projectilePosition, this, angle);
			}

			projectile.setVelocity(
					(firingPosition.sub(projectile.getPosition()).getUnitVector().multiply(projectileSpeed)));
			game.addEntity(projectile);
		}

		attackTimer--;
	}

	@Override
	public String showUpgrade() {
		switch (upgrades) {
		case 0:
			return "Increase freeze duration.";
		case 1:
			return "Shoots icicles, which do more damage\nand have +2 pierce.";
		case 2:
			pierce = 3;
			damage = 50;
			return "Increase freezing power - ice can't\nbe melted by flames, and slows \nenemy healing.";
		case 3:
			return "Further increase freezing power,\nallowing icicles to freeze giant \nenemies.";
		case 4:
			return "Armour penetration - icicles remove \ndefense buffs, and gain +5 pierce.";
		case 5:
			pierce = 8;
			return "Fully upgraded.";
		default:
			break;
		}

		return null;
	}

	@Override
	public int getUpgradeCost() {
		switch (upgrades) {
		case 0:
			return 80;
		case 1:
			return 400;
		case 2:
			return 300;
		case 3:
			return 800;
		case 4:
			return 1500;
		default:
			break;
		}

		return 0;
	}
}
