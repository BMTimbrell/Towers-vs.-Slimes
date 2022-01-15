package com.game.enemies;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

import com.game.*;
import com.game.gui.Line;
import com.game.gui.Map;
import com.game.towers.Tower;

public abstract class Enemy extends Entity {
	protected float baseSpeed, speed, frozenSpeed, distance;
	private Line[] segments;
	protected int burnTimer, burnDuration, freezeDuration, fireResFreezeDuration, coinsAwarded, width, height, livesLost;
	protected float damageTaken, health, maxHealth, healthBarWidth;
	protected boolean burning, frozen, defenseBuff, armourPierced;
	protected Image image, flameIcon, snowflakeIcon, shieldIcon;
	protected Tower tower;
	protected EnemyType enemyType;
	protected String name;

	public Enemy() {
		init();
	}
	
	protected void init() {
		speed = 1;
		baseSpeed = speed;
		distance = 0;
		damageTaken = 0;
		livesLost = 0;
		burnTimer = 60;
		burnDuration = 360;
		burning = false;
		frozen = false;
		defenseBuff = false;
		armourPierced = false;
		freezeDuration = 0;
		fireResFreezeDuration = 0;
		frozenSpeed = 0.5f;
		width = Commons.SMALL_ENEMY_WIDTH;
		height = Commons.SMALL_ENEMY_HEIGHT;
		healthBarWidth = width;
		enemyType = EnemyType.SMALL;
		flameIcon = Game.readImage("flame");
		snowflakeIcon = Game.readImage("snowflake");
		shieldIcon = Game.readImage("shield");
	}

	protected void move() {
		this.distance += speed;
	}

	public Vector2f getPosition(Map map) {
		segments = map.getSegments();

		int segmentId = (int) (distance / Commons.SEGMENT_LENGTH);
		float weight = (distance - (Commons.SEGMENT_LENGTH * segmentId)) / Commons.SEGMENT_LENGTH;

		Line segment = segments[segmentId];

		float x = (segment.getA().getX() * (1 - weight)) + (segment.getB().getX() * weight)
				+ ((Commons.BLOCK_SIZE / 2));
		float y = (segment.getA().getY() * (1 - weight)) + (segment.getB().getY() * weight)
				+ ((Commons.BLOCK_SIZE / 2));
		if (this instanceof KingSlime) return new Vector2f(x, y - 20);
		return new Vector2f(x, y);
	}
	
	// Work out where an enemy will be based on distance
	public Vector2f calculatePosition(float distance, Map map) {
		float oldDistance = this.distance;
		this.distance = distance;
		Vector2f result = getPosition(map);
		this.distance = oldDistance;
		return result;
	}

	public void render(Graphics g) {
		g.drawImage(image, position.toVec2i().getX() - (width / 2), position.toVec2i().getY() - (height / 2), width,
				height, null);
		drawHealthBar(g);
	}

	public void tick(Game game) {
		position = getPosition(game.getMap());

		// Enemy dies
		if ((int) health <= 0) {
			spawnEnemies(game);
			game.getEnemies().remove(this);
			game.removeEntity(this);
			game.addCoins(coinsAwarded);
		}
		move();
		collision(game);
		
		// Get defense buff if in range of a defense buffing enemy
		if (!armourPierced) {
			applyDefenseBuff(game);
		} else {
			defenseBuff = false;
		}

		if (burning) {
			if (frozen && fireResFreezeDuration <= 0) frozen = false;
			if (burnTimer == 0) {
				if (maxHealth > 200) {
					health -= 20;
				} else {
					health -= maxHealth / 10;
				}
				updateHealthBar();
				burnTimer = 60;
			} else {
				burnTimer--;
			}
			if (burnDuration == 0) {
				burnDuration = 360;
				burnTimer = 60;
				burning = false;
			}
			burnDuration--;
		}

		if (frozen) {
			speed = frozenSpeed;
			freezeDuration--;
			if (fireResFreezeDuration > 0) fireResFreezeDuration--;
			if (fireResFreezeDuration <= 0 && freezeDuration <= 0) {
				frozen = false;
				speed = baseSpeed;
			}
		}
	}

	protected void collision(Game game) {
		// Collision with end of path
		if (position.getY() < Commons.MAP_TOP_POS || position.getX() > Commons.MAP_END_POS
				|| (distance > 100 && position.getY() > Commons.MAP_BOTTOM_POS)) {
			game.getEnemies().remove(this);
			game.removeEntity(this);
			game.loseLives(livesLost);
		}
	}

	protected void applyDefenseBuff(Game game) {
		defenseBuff = false;
		for (Enemy enemy : game.getEnemies()) {
			if (enemy instanceof RainbowSlime || (enemy instanceof GraySlime && !(enemy instanceof PinkSlime))) {
				float enemyDistance = getPosition().sub(enemy.getPosition()).getLength();
				if (enemyDistance <= Commons.AURA_RANGE / 2)
					defenseBuff = true;
			}
		}
	}

	public void setHealth() {
		if (defenseBuff)
			damageTaken /= 2;

		health -= damageTaken;
		damageTaken = 0;
		updateHealthBar();
	}

	protected void updateHealthBar() {
		healthBarWidth = width;
		healthBarWidth *= health / maxHealth;
		if ((int) healthBarWidth < 2) healthBarWidth = 2;
	}

	public void setDamageTaken(float damageTaken) {
		this.damageTaken = damageTaken;
	}

	public void freeze(int freezeDuration, Tower tower) {
		if (tower.getUpgrades() > 2)
			this.fireResFreezeDuration = freezeDuration;
		else
			this.freezeDuration = freezeDuration;
		frozen = true;
	}
	
	public int getFreezeDuration() {
		return freezeDuration;
	}
	
	public int getFireResFreezeDuration() {
		return fireResFreezeDuration;
	}
	
	public void thaw() {
		frozen = false;
		speed = baseSpeed;
		freezeDuration = 0;
	}
	
	public boolean isFrozen() {
		return frozen;
	}

	public void burn() {
		burning = true;
		burnDuration = 360;
	}
	
	public boolean isBurning() {
		return burning;
	}

	public void heal() {
		if (health < maxHealth) {
			if (maxHealth - health >= 50)
				health += 50;
			else
				health = maxHealth;

			updateHealthBar();
		}
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getDistance() {
		return distance;
	}
	
	public EnemyType getType() {
		return enemyType;
	}
	
	public boolean hasDefenseBuff() {
		return defenseBuff;
	}
	
	public void pierceArmour() {
		armourPierced = true;
	}
	
	// Spawns enemies on death
	protected void spawnEnemies(Game game) {

	}
	
	protected void spawnEnemy(Game game, Enemy enemy, float distance) {
		if (distance < 0) distance = 0;
		float eX = enemy.calculatePosition(distance, game.getMap()).getX();
		float eY = enemy.calculatePosition(distance, game.getMap()).getY();
		if (eY < Commons.MAP_TOP_POS || eX > Commons.MAP_END_POS 
				|| (distance > 100 && eY > Commons.MAP_BOTTOM_POS)) distance -= 50;
		enemy.setDistance(distance);
		enemy.setPosition(enemy.getPosition(game.getMap()));
		game.addEntity(enemy);
		game.getEnemies().add(enemy);
	}
	
	public void spawnAtStart(Map map) {
		position = getPosition(map);
	}
	
	// Intersection with projectiles
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(position.getX() - (width / 2), position.getY() - (height / 2), width, height);
	}
	
	// Intersection with tower range
	public Rectangle2D getRangeBounds() {
		return new Rectangle2D.Double(position.getX() - (Commons.SMALL_ENEMY_WIDTH / 2), position.getY() - (Commons.SMALL_ENEMY_HEIGHT / 2),
				Commons.SMALL_ENEMY_WIDTH, Commons.SMALL_ENEMY_HEIGHT);
	}
	
	protected void drawHealthBar(Graphics g) {
		int xPos = position.toVec2i().getX() - (width / 2);
		int yPos = (position.toVec2i().getY() - (height / 2))
				- (Commons.HEALTH_BAR_HEIGHT + Commons.HEALTH_BAR_BOTTOM_MARGIN + Commons.HEALTH_BAR_TOP_MARGIN);

		// Health bar
		if ((int) health > 0) {
			g.setColor(Color.GRAY);
			g.fillRect(xPos, yPos, width, Commons.HEALTH_BAR_HEIGHT);
			g.setColor(Color.GREEN);
			g.fillRect(xPos, yPos, (int) healthBarWidth, Commons.HEALTH_BAR_HEIGHT);
			g.setColor(Color.BLACK);
			g.drawRect(xPos, yPos, width, Commons.HEALTH_BAR_HEIGHT);

			// Draw status icons above health bar
			if (defenseBuff) {
				if (burning && frozen) {
					g.drawImage(shieldIcon, xPos, yPos - shieldIcon.getHeight(null), shieldIcon.getWidth(null),
							shieldIcon.getHeight(null), null);
					g.drawImage(flameIcon, xPos + shieldIcon.getWidth(null) + Commons.STATUS_ICON_SPACING,
							yPos - flameIcon.getHeight(null), flameIcon.getWidth(null), flameIcon.getHeight(null),
							null);
					g.drawImage(snowflakeIcon,
							xPos + flameIcon.getWidth(null) + shieldIcon.getWidth(null)
									+ (Commons.STATUS_ICON_SPACING * 2),
							yPos - snowflakeIcon.getHeight(null), snowflakeIcon.getWidth(null),
							snowflakeIcon.getHeight(null), null);
				} else if (burning) {
					g.drawImage(shieldIcon, xPos, yPos - shieldIcon.getHeight(null), shieldIcon.getWidth(null),
							shieldIcon.getHeight(null), null);
					g.drawImage(flameIcon, xPos + shieldIcon.getWidth(null) + Commons.STATUS_ICON_SPACING,
							yPos - flameIcon.getHeight(null), flameIcon.getWidth(null), flameIcon.getHeight(null),
							null);
				} else if (frozen) {
					g.drawImage(shieldIcon, xPos, yPos - shieldIcon.getHeight(null), shieldIcon.getWidth(null),
							shieldIcon.getHeight(null), null);
					g.drawImage(snowflakeIcon, xPos + shieldIcon.getWidth(null) + Commons.STATUS_ICON_SPACING,
							yPos - snowflakeIcon.getHeight(null), snowflakeIcon.getWidth(null),
							snowflakeIcon.getHeight(null), null);
				} else {
					g.drawImage(shieldIcon, xPos, yPos - shieldIcon.getHeight(null), shieldIcon.getWidth(null),
							shieldIcon.getHeight(null), null);
				}
			} else {
				if (burning && frozen) {
					g.drawImage(flameIcon, xPos, yPos - flameIcon.getHeight(null), flameIcon.getWidth(null),
							flameIcon.getHeight(null), null);
					g.drawImage(snowflakeIcon, xPos + flameIcon.getWidth(null) + Commons.STATUS_ICON_SPACING,
							yPos - snowflakeIcon.getHeight(null), snowflakeIcon.getWidth(null),
							snowflakeIcon.getHeight(null), null);
				} else if (burning) {
					g.drawImage(flameIcon, xPos, yPos - flameIcon.getHeight(null), flameIcon.getWidth(null),
							flameIcon.getHeight(null), null);
				} else if (frozen) {
					g.drawImage(snowflakeIcon, xPos, yPos - snowflakeIcon.getHeight(null), snowflakeIcon.getWidth(null),
							snowflakeIcon.getHeight(null), null);
				}
			}
		}
	}
	
	public void setSlowHeal() {
		
	}
	
	public int getHealth() {
		return (int) maxHealth;
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCoinsAwarded() {
		return coinsAwarded;
	}
	
	public int getLivesLost() {
		return livesLost;
	}
	
	public float getCurrentHealth() {
		return health;
	}
}
