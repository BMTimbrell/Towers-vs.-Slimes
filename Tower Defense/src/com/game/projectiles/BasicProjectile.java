package com.game.projectiles;

import java.awt.*;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.enemies.Enemy;
import com.game.towers.Tower;

public class BasicProjectile extends Projectile {

	double angle;
	
	public BasicProjectile(Vector2f position, Tower tower, double angle) {
		super(position, tower);
		this.angle = angle;
		image = Game.readImage("bullet");
	}

	public void render(Graphics g) {
		Projectile.drawImageWithRotation((Graphics2D) g, image, position, angle);
	}
	
}
