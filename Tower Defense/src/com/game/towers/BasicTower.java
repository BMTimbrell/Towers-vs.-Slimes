package com.game.towers;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.gui.Block;
import com.game.projectiles.BasicProjectile;
import com.game.projectiles.Laser;
import com.game.projectiles.Projectile;

public final class BasicTower extends Tower {

	public BasicTower(Vector2f position, int width, int height, TowerType towerType, Block block) {
		super(position, width, height, towerType, block);
		attackSpeed = 60;
		finalUpgradeSprite = Game.readImage("Tower5");
		projectilePosition = projectilePosition.sub(new Vector2f(0, 22));
	}

	protected void shoot(Game game, Enemy enemy) {
		if (attackTimer == 0)
			attackTimer = attackSpeed;

		if (attackTimer == attackSpeed) {
			firingPosition = enemy.calculatePosition(enemy.getDistance() + 25, game.getMap());
			Projectile projectile;
			double angle = Math.atan2(firingPosition.getY() - projectilePosition.getY(), firingPosition.getX() - projectilePosition.getX());
			if (upgrades < 5) {
				projectile = new BasicProjectile(projectilePosition, this, angle);
			} else {
				projectile = new Laser(projectilePosition, this, angle);
			}
			projectile.setVelocity(
					firingPosition.sub(projectile.getPosition()).getUnitVector().multiply(projectileSpeed));
			game.addEntity(projectile);
		}

		attackTimer--;

	}

	@Override
	public String showUpgrade() {
		switch (upgrades) {
		case 0:
			return "Projectiles gain pierce, \ndamaging up to 2 enemies at once.";
		case 1:
			pierce = 2;
			return "Increase range.";
		case 2:
			range = 350;
			return "Increase firing speed.";
		case 3:
			attackSpeed = 45;
			return "Increase damage dealt.";
		case 4:
			damage = 50;
			return "Basic tower becomes a laser tower, \nwhich deals more damage and pierces \nup to 10 enemies.";
		case 5:
			pierce = 10;
			damage = 75;
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
			return 60;
		case 1:
			return 30;
		case 2:
			return 50;
		case 3:
			return 70;
		case 4:
			return 500;
		default:
			break;
		}

		return 0;
	}

}
