package com.game.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Game;
import com.game.Vector2f;

public class PinkSlime extends GraySlime {

	protected float healRange, healTimer, slowHealTimer;
	protected Ellipse2D healRangeRadius;
	protected boolean healing, slowHeal;

	public PinkSlime() {
		name = "Pink Slime";
		healRange = 0;
		healTimer = Commons.AURA_RANGE;
		slowHealTimer = freezeDuration;
		healing = false;
		slowHeal = false;
		image = Game.readImage("pink_slime");
	}

	protected void move() {
		this.distance += speed;
		healPulse();
	}

	protected void collision(Game game) {
		// Collision with end of path
		if (position.getY() < Commons.MAP_TOP_POS || position.getX() > Commons.MAP_END_POS
				|| (distance > 100 && position.getY() > Commons.MAP_BOTTOM_POS)) {
			game.getEnemies().remove(this);
			game.removeEntity(this);
			game.loseLives(livesLost);
		}
		
		// Heal enemies in range
		applyHeal(game);
	}

	public void render(Graphics g) {
		g.drawImage(image, position.toVec2i().getX() - (width / 2), position.toVec2i().getY() - (height / 2), width, height,
				null);
		g.setColor(new Color(255, 255, 0));

		if (healRange > 0)
			g.drawOval((int) healRangeRadius.getX(), (int) healRangeRadius.getY(), (int) healRange, (int) healRange);

		drawHealthBar(g);
	}

	protected void healPulse() {
		healing = false;
		healRangeRadius = new Ellipse2D.Double(getPosition().getX() - (healRange / 2),
				getPosition().getY() - (healRange / 2), healRange, healRange);
		if (healTimer < Commons.AURA_RANGE && healTimer > 0) {
			if (!slowHeal)
				healRange++;
			else
				healRange += 0.5;
		} else {
			healRange = 0;
			healTimer = Commons.AURA_RANGE;
			healing = true;
		}
		
		if (!slowHeal) {
			healTimer--;
		} else {
			healTimer -= 0.5;
			slowHealTimer--;
		}
		
		if (slowHealTimer <= 0)
			slowHeal = false;
	}

	protected void applyHeal(Game game) {
		if (healing) {
			for (Enemy enemy : game.getEnemies()) {
				if (healRangeRadius.intersects(enemy.getBounds())) {
					enemy.heal();
				}
			}
		}
	}
	
	public void setSlowHeal() {
		slowHeal = true;
		slowHealTimer = fireResFreezeDuration;
	}
}
