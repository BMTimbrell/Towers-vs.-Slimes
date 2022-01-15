package com.game.gui;

import java.awt.*;
import java.awt.font.LineMetrics;

public class Button {

	private int x, y, width, height;
	private Color btnColor, textColor;
	private Font font;
	private String text;

	public Button(int x, int y, int width, int height, Font font, String text) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		btnColor = new Color(68, 69, 77);
		textColor = Color.WHITE;
		this.font = font;
		this.text = text;
	}

	public void render(Graphics2D g) {
		g.setColor(btnColor);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setColor(textColor);
		g.setFont(font);
		int textWidth = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
		LineMetrics lm = g.getFontMetrics().getLineMetrics(text, g);
 		int textHeight = (int) (lm.getAscent() - lm.getDescent());
		g.drawString(text, x + (width / 2) - (textWidth / 2),
				y + textHeight + (height / 2) - (textHeight / 2));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public void setBtnColor(Color btnColor) {
		this.btnColor = btnColor;
	}
	
	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
	
	public void highlight() {
		setBtnColor(new Color(255, 255, 255, 100));
		setTextColor(new Color(0, 0, 0, 200));
	}
	
	public void highlight(Color color) {
		setBtnColor(color);
		setTextColor(new Color(0, 0, 0, 200));
	}
}
