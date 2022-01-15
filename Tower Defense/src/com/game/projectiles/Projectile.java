package com.game.projectiles;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import com.game.*;
import com.game.enemies.Enemy;
import com.game.gui.Block;
import com.game.towers.Tower;

public abstract class Projectile extends Entity {
	
	protected Tower tower;
	protected final List<Enemy> enemies;
	protected int pierce;
	protected float range;
	protected Image image;
	
	public Projectile(Vector2f position, Tower tower) {
		super(position);
		width = 10;
		height = 10;
		this.tower = tower;
		pierce = tower.getPierce();
		enemies = new ArrayList<>();
		range = tower.getRange();
	}

	public abstract void render(Graphics g);

	public void tick(Game game) {
		move();
		checkRange(game);
		collision(game);
	}
	
	protected void move() {
		position = position.add(velocity);
	}
	
	protected void collision(Game game) {
		for (Enemy enemy : game.getEnemies()) {
			if (getBounds().intersects(enemy.getBounds())) {
				if (pierce > 1) {
					if (!enemies.contains(enemy)) {
						enemies.add(enemy);
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
				enemy.setDamageTaken(tower.getDamage());
				enemy.setHealth();
				game.removeEntity(this);
				break;
			}
		}
	}
	
	protected void checkRange(Game game) {
		float distance = position.sub(tower.getPosition()).getLength();
		if (distance > range)
			game.removeEntity(this);
	}
	
	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(position.getX() - (width / 2), position.getY() - (height / 2), width, height);
	}
	
	public static void drawImageWithRotation(Graphics2D g, Image img, Vector2f position, double angle) {
	    AffineTransform state = g.getTransform();
	    g.translate(position.getX(), position.getY());
	    g.rotate(angle);
	    g.drawImage(img, -img.getWidth(null) / 2, -img.getHeight(null) / 2, null);
	    g.setTransform(state);
	}
	
	public int getDrawingPriority() {
		return 1;
	}
}
