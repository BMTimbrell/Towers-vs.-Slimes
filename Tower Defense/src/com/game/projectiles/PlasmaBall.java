package com.game.projectiles;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.game.Game;
import com.game.Vector2f;
import com.game.towers.Tower;

public class PlasmaBall extends Projectile {
	
	public PlasmaBall(Vector2f position, Tower tower) {
		super(position, tower);
		image = Game.readImage("Plasma Ball");
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, position.toVec2i().getX() - (width / 2), position.toVec2i().getY() - (height / 2), width, height, null);
	}
	
	public int getDrawingPriority() {
		return 0;
	}
}
