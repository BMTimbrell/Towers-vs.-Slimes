package com.game.towers;

import java.awt.Image;
import com.game.Game;


public enum TowerType {
	TOWER1("Basic Tower", 1, 250, 40, 74),
	TOWER2("Cannon Tower", 2, 150, 48, 74),
	TOWER3("Ice Tower", 3, 300, 48, 82),
	TOWER4("Fire Tower", 4, 300, 48, 74),
	TOWER5("Plasma Tower", 5, 450, 50, 102);
	
	private final Image image;
	private final int towerID, towerPrice, towerRange, width, height;
	private String fileName;

	private TowerType(String fileName, int id, int range, int width, int height) {
		towerRange = range;
		towerID = id;
		this.width = width;
		this.height = height;
		this.fileName = fileName;
		
		image = Game.readImage(fileName);
		
		switch (towerID) {
		case 1 :
			towerPrice = 100;
			break;
		case 2 :
			towerPrice = 200;
			break;
		case 3 :
			towerPrice = 180;
			break;
		case 4 :
			towerPrice = 400;
			break;
		case 5 :
			towerPrice = 1200;
			break;
		default:
			towerPrice = 0;
			break;
		}
	}

	public Image getImage() {
		return image;
	}
	
	public int getPrice() {
		return towerPrice;
	}
	
	public int getDefaultRange() {
		return towerRange;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public String getName() {
		return fileName;
	}
}
