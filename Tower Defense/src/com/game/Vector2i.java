package com.game;

import java.awt.Point;
import java.awt.Rectangle;

public final class Vector2i {
	
	private final int x, y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Vector2i add(Vector2i v) {
		return new Vector2i(x + v.x, y + v.y);
	}
	
	public Vector2i sub(Vector2i v) {
		return new Vector2i(x - v.x, y - v.y);
	}
	
	public Rectangle generateRect(Vector2i size) {
		return new Rectangle(x, y, size.getX(), size.getY());
	}
	
	public Vector2f toVec2f() {
		return new Vector2f((float) x, (float) y);
	}
	
	public Point toPoint() {
		return new Point(x, y);
	}
	
	public Vector2i divide(int value) {
		return new Vector2i(x / value, y / value);
	}
	
	public Vector2i multiply(int value) {
		return new Vector2i(x * value, y * value);
	}
}
