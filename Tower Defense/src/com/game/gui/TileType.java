package com.game.gui;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import com.game.Game;

public enum TileType {
	GRASS("grass"), 
	PATH("path");

	private final Image image;

	private TileType(String fileName) {
		image = Game.readImage(fileName);
	}

	public Image getImage() {
		return image;
	}
}