package com.game.towers;

import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.gui.Block;
import com.game.projectiles.*;

public final class CannonTower extends Tower {

	public CannonTower(Vector2f position, int width, int height, TowerType towerType, Block block) {
		super(position, width, height, towerType, block);
		attackSpeed = 90;
		priority = 0;
		finalUpgradeSprite = Game.readImage("Upgraded Cannon");
	}

	private void addProjectile(double angle, Game game) {
		angle = angle * Math.PI / 180;
		CannonBall cannonball = new CannonBall(projectilePosition, this);
		Vector2f target = position.add(new Vector2f((float) Math.cos(angle), (float) Math.sin(angle)));
		cannonball.setVelocity(target.sub(cannonball.getPosition()).getUnitVector().multiply(projectileSpeed));
		game.addEntity(cannonball);
	}

	protected void shoot(Game game, Enemy enemy) {

		if (attackTimer == 0)
			attackTimer = attackSpeed;

		if (attackTimer == attackSpeed) {
			addProjectile(45, game);
			addProjectile(90, game);
			addProjectile(135, game);
			addProjectile(180, game);
			addProjectile(225, game);
			addProjectile(270, game);
			addProjectile(315, game);
			addProjectile(360, game);
			
			if (upgrades > 3) {
				addProjectile(22.5, game);
				addProjectile(67.5, game);
				addProjectile(112.5, game);
				addProjectile(157.5, game);
				addProjectile(202.5, game);
				addProjectile(247.5, game);
				addProjectile(292.5, game);
				addProjectile(337.5, game);
			}
			
			if (upgrades > 4) {
				addProjectile(11.25, game);
				addProjectile(33.75, game);
				addProjectile(56.25, game);
				addProjectile(78.75, game);
				addProjectile(101.25, game);
				addProjectile(123.75, game);
				addProjectile(146.25, game);
				addProjectile(168.75, game);
				addProjectile(191.25, game);
				addProjectile(213.75, game);
				addProjectile(236.25, game);
				addProjectile(258.75, game);
				addProjectile(281.25, game);
				addProjectile(303.75, game);
				addProjectile(326.25, game);
				addProjectile(348.75, game);
				
			}
		}

		attackTimer--;
	}

	@Override
	public String showUpgrade() {
		switch (upgrades) {
		case 0:
			return "Increase range.";
		case 1:
			range = 210;
			return "Increase firing rate.";
		case 2:
			attackSpeed = 60;
			return "Increase firing rate and +1 pierce.";
		case 3:
			attackSpeed = 40;
			pierce = 2;
			return "Fire 16 cannon balls.";
		case 4:
			return "Faster firing and shoots 32 \ncannonballs.";
		case 5:
			attackSpeed = 20;
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
			return 50;
		case 1:
			return 100;
		case 2:
			return 200;
		case 3:
			return 400;
		case 4:
			return 1200;
		default:
			break;
		}

		return 0;
	}

}
