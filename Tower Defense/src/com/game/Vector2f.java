package com.game;


public final class Vector2f {
	
	private final float x, y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public Vector2f add(Vector2f v) {
		return new Vector2f(x + v.x, y + v.y);
	}
	
	public Vector2f sub(Vector2f v) {
		return new Vector2f(x - v.x, y - v.y);
	}
	
	public float getLength() {
		return (float) Math.sqrt((x * x) + (y * y));
	}
	
	public Vector2f divide(float value) {
		return new Vector2f(x / value, y / value);
	}
	
	public Vector2f multiply(float value) {
		return new Vector2f(x * value, y * value);
	}
	
	public Vector2f addX(float value) {
		return new Vector2f(x + value, y);
	}
	
	public Vector2f addY(float value) {
		return new Vector2f(x, y + value);
	}
	
	public Vector2f subX(float value) {
		return new Vector2f(x - value, y);
	}
	
	public Vector2f subY(float value) {
		return new Vector2f(x, y - value);
	}
	
	public Vector2f multiplyX(float value) {
		return new Vector2f(x * value, y);
	}
	
	public Vector2f multiplyY(float value) {
		return new Vector2f(x, y * value);
	}
	
	public Vector2f getUnitVector() {
		return divide(getLength());
	}
	
	public Vector2i toVec2i() {
		return new Vector2i((int) x, (int) y);
	}
}
