package com.game.projectiles;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.towers.Tower;

public class Laser extends Projectile {
	
	private double angle;

	public Laser(Vector2f position, Tower tower, double angle) {
		super(position, tower);
		this.angle = angle;
		image = Game.readImage("laser");
	}

	@Override
	public void render(Graphics g) {
		Projectile.drawImageWithRotation((Graphics2D) g, image, position, angle);
	}

}
