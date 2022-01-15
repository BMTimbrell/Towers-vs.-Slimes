package com.game.gui;

import java.awt.*;

public class InfoBox extends Rectangle {
	public InfoBox(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.GRAY);
		g.fill(this);
		g.setColor(Color.BLACK);
		g.draw(this);
		g.setColor(Color.WHITE);
	}
}
