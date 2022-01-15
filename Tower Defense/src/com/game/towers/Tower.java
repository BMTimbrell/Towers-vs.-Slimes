package com.game.towers;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.game.Commons;
import com.game.Entity;
import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.gui.Block;
import com.game.projectiles.*;

public abstract class Tower extends Entity {

	protected TowerType towerType;
	protected boolean isSelected, enemyInRange, finalUpgrade;
	protected Ellipse2D towerRangeRadius;
	protected int range, damage, attackSpeed, attackTimer, upgrades, projectileSpeed, sellPrice, priority, pierce;
	protected Block block;
	protected Image image, finalUpgradeSprite;
	protected Vector2f projectilePosition, firingPosition;

	public Tower(Vector2f position, int width, int height, TowerType towerType, Block block) {
		super(position);
		priority = 0;
		this.towerType = towerType;
		this.block = block;
		this.width = width;
		this.height = height;
		isSelected = true;
		enemyInRange = false;
		attackTimer = attackSpeed;
		upgrades = 0;
		range = towerType.getDefaultRange();
		towerRangeRadius = new Ellipse2D.Double(block.getX() - ((range / 2) - (Commons.BLOCK_SIZE / 2)), block.getY() - ((range / 2) - ( Commons.BLOCK_SIZE / 2)), range, range);
		damage = 25;
		projectileSpeed = 7;
		pierce = 1;
		finalUpgrade = false;
		sellPrice = (int) (towerType.getPrice() * 0.8);
		image = towerType.getImage();
		finalUpgradeSprite = null;
		projectilePosition = new Vector2f(position.getX(), position.getY());
	}

	public void render(Graphics g) {
		float x = position.getX() - width / 2;
		float y = position.getY() - (height - Commons.BLOCK_SIZE / 2);
		g.drawImage(image, (int) x, (int) y, width, height, null);
		if (isSelected) {
			g.setColor(new Color(255, 255, 255, 50));
			g.fillOval((int) towerRangeRadius.getX(), (int) towerRangeRadius.getY(), range, range);
		}
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
				if (towerRangeRadius.intersects(enemy.getRangeBounds())) {
					enemyInRange = true;
					shoot(game, enemy);
					break;
				} else {
					enemyInRange = false;
				}
			}
		} else if (priority == 1) {
			// Shoot last enemy in range
			for (int i = game.getEnemies().size() - 1; i >= 0; i--) {
				if (towerRangeRadius.intersects(game.getEnemies().get(i).getRangeBounds())) {
					enemyInRange = true;
					shoot(game, game.getEnemies().get(i));
					break;
				} else {
					enemyInRange = false;
				}
			}
		}
		
		if (upgrades == 5) {
			image = finalUpgradeSprite;
			width = image.getWidth(null);
			height = image.getHeight(null);
		}
	}

	protected abstract void shoot(Game game, Enemy enemy);

	public abstract String showUpgrade();
	public abstract int getUpgradeCost();

	public TowerType getType() {
		return towerType;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean isSelected() {
		return isSelected;
	}

	public int getDamage() {
		return damage;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getPierce() {
		return pierce;
	}
	
	public int getUpgrades() {
		return upgrades;
	}
	
	public void addUpgrade() {
		sellPrice += getUpgradeCost() * 0.8;
		upgrades++;
	}
	
	public TowerType getTowerType() {
		return towerType;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public int getSellPrice() {
		return sellPrice;
	}
}
