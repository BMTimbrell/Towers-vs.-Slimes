package com.game.projectiles;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.towers.Tower;

public class Icicle extends IceBall {
	
	private double angle;
	
	public Icicle(Vector2f position, Tower tower, double angle) {
		super(position, tower);
		this.angle = angle;
		image = Game.readImage("Icicle");
	}
	
	public void render(Graphics g) {
		Projectile.drawImageWithRotation((Graphics2D) g, image, position, angle);
	}
}
