package com.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
	
	protected Vector2f velocity, position;
	protected int width, height;
	
	public Entity(Vector2f position) {
		this.position = position;
		velocity = new Vector2f(0, 0);
	}
	
	public Entity() {
	velocity = new Vector2f(0, 0);
	position = new Vector2f(0, 0);
	}
	
	public abstract void render(Graphics g);
	public abstract void tick(Game game);
	
	public Vector2f getPosition() {
		return position;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getDrawingPriority() {
		return 0;
	}
}
