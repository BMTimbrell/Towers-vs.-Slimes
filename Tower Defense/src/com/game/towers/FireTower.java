package com.game.towers;
import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.gui.Block;
import com.game.projectiles.*;


public final class FireTower extends Tower {

	public FireTower(Vector2f position, int width, int height, TowerType towerType, Block block) {
		super(position, width, height, towerType, block);
		attackSpeed = 60;
		damage = 75;
		finalUpgradeSprite = Game.readImage("Upgraded Fire Tower");
		projectilePosition = projectilePosition.sub(new Vector2f(0, 40));
	}

	@Override
	protected void shoot(Game game, Enemy enemy) {
		if (attackTimer == 0)
			attackTimer = attackSpeed;
		
		if (attackTimer == attackSpeed) {
			firingPosition = enemy.calculatePosition(enemy.getDistance() + 25, game.getMap());
			double angle = Math.atan2(firingPosition.getY() - projectilePosition.getY(), firingPosition.getX() - projectilePosition.getX());
			FireBall fireball = new FireBall(projectilePosition, this, angle);
			fireball.setVelocity(firingPosition.sub(fireball.getPosition()).getUnitVector()
					.multiply(projectileSpeed));
			game.addEntity(fireball);
		}

		attackTimer--;
	}

	@Override
	public String showUpgrade() {
		switch (upgrades) {
		case 0:
			return "Faster firing.";
		case 1:
			attackSpeed = 50;
			return "Fireballs explode on impact, \ndamaging and igniting multiple \nenemies.";
		case 2:
			return "Increase damage dealt.";
		case 3:
			damage = 100;
			return "Bigger Explosions.";
		case 4:
			return "Double damage.";
		case 5:
			damage = 200;
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
			return 180;
		case 1:
			return 500;
		case 2:
			return 400;
		case 3:
			return 600;
		case 4:
			return 1350;
		default:
			break;
		}
		
		return 0;
	}

}
