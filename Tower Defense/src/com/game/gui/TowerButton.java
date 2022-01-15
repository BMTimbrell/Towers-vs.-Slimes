package com.game.gui;

import java.awt.*;

import com.game.towers.TowerType;

public class TowerButton extends Rectangle {
	
	private TowerType btnType;
	private Color color;
	
	public TowerButton(int x, int y, int width, int height) {
		super(x, y, width, height);
		btnType = null;
	}
	
	public void setType(TowerType btnType) {
		this.btnType = btnType;
	}
	
	public TowerType getType() {
		return btnType;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
