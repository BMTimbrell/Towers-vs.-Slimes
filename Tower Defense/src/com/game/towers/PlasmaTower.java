package com.game.towers;
import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.gui.Block;
import com.game.projectiles.*;


public final class PlasmaTower extends Tower {

	public PlasmaTower(Vector2f position, int width, int height, TowerType towerType, Block block) {
		super(position, width, height, towerType, block);
		attackSpeed = 20;
		damage = 75;
		finalUpgradeSprite = Game.readImage("Upgraded Plasma Tower");
		projectilePosition = projectilePosition.sub(new Vector2f(0, 67));
	}

	protected void shoot(Game game, Enemy enemy) {
		if (attackTimer == 0)
			attackTimer = attackSpeed;
		
		if (attackTimer == attackSpeed) {
			firingPosition = enemy.calculatePosition(enemy.getDistance() + 25, game.getMap());
			Projectile projectile = new PlasmaBall(projectilePosition, this);
			projectile.setVelocity(firingPosition.sub(projectile.getPosition()).getUnitVector()
					.multiply(projectileSpeed));
			game.addEntity(projectile);
		}

		attackTimer--;
	}

	@Override
	public String showUpgrade() {
		switch (upgrades) {
		case 0:
			return "Extra range.";
		case 1:
			range = 600;
			return "+1 pierce.";
		case 2:
			pierce = 2;
			return "Increase firing speed.";
		case 3:
			attackSpeed = 10;
			return "+2 pierce.";
		case 4:
			pierce = 4;
			return "Increase damage and firing speed";
		case 5:
			attackSpeed = 5;
			damage = 100;
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
			return 400;
		case 1:
			return 1000;
		case 2:
			return 1200;
		case 3:
			return 2000;
		case 4:
			return 3200;
		default:
			break;
		}

		return 0;
	}
}
