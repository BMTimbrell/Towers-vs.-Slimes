package com.game;

public enum Difficulty {
	EASY(0.8, 200, 500),
	NORMAL(1, 100, 250),
	HARD(1.1, 50, 250);
	
	private int lives, startingCash;
	private double costModifier;
	
	private Difficulty(double costModifier, int lives, int startingCash) {
		this.costModifier = costModifier;
		this.lives = lives;
		this.startingCash = startingCash;
	}
	
	public int getLives() {
		return lives;
	}
	
	public int getStartingCash() {
		return startingCash;
	}
	
	public double getCostModifier() {
		return costModifier;
	}
}
