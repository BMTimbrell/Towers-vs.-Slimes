package com.game.gui;

import java.awt.*;

import javax.swing.*;

import com.game.towers.Tower;

public final class Block extends Rectangle {
	
	private TileType tileType;
	private Tower tower;
	
	public Block(int x, int y, int width, int height, TileType tileType) {
		super(x, y, width, height);
		this.tileType = tileType;
		
	}
	
	public void render(Graphics g) {
		if (tileType == TileType.GRASS) {
			g.drawImage(tileType.getImage(), x, y, width, height, null);
		}else if (tileType == TileType.PATH) {
			g.drawImage(tileType.getImage(), x, y, width, height, null);
		}
	}
	
	public TileType getTileType() {
		return tileType;
	}
	
	public void setTower(Tower tower) {
		this.tower = tower;
	}
	
	public Tower getTower() {
		return tower;
	}
	
	public void removeTower() {
		tower = null;
	}

}
