package com.game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import com.game.enemies.Enemy;
import com.game.gui.GameMenu;
import com.game.gui.Map;
import com.game.towers.Tower;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class Game {

	private Map map;
	private final GameMenu gameMenu;
	private final List<Entity> entities;
	private final List<Entity> entitiesToAdd;
	private final List<Entity> entitiesToRemove;
	private final List<Enemy> enemies;
	private final List<Tower> towers;
	private int lives, wave, coins, mapSelected;
	private Spawner spawner;
	public static State gameState;
	private Difficulty difficulty;

	public static void main(String[] args) {

		Game game = new Game();
		Renderer renderer = new Renderer(game);

		renderer.start();

	}

	public Game() {
		difficulty = Difficulty.NORMAL;
		spawner = new Spawner();
		mapSelected = 0;
		map = new Map(mapSelected);
		towers = new ArrayList<>();
		gameMenu = new GameMenu(this);
		entities = new ArrayList<>();
		entitiesToAdd = new ArrayList<>();
		entitiesToRemove = new ArrayList<>();
		enemies = new ArrayList<>();
		gameState = State.MAIN_MENU;
		lives = 100;
		wave = 0;
		coins = 250;
	}

	public void addEntity(Entity entity) {
		entitiesToAdd.add(entity);
	}

	public void removeEntity(Entity entity) {
		entitiesToRemove.add(entity);
	}

	public void loseLives(int amount) {
		lives -= amount;
	}

	public int getLives() {
		return lives;
	}

	public void addCoins(int amount) {
		if (wave > 34 && amount != 1000)
			amount /= 2;
		coins += amount;
	}

	public int getCoins() {
		return coins;
	}

	public void spendCoins(int amount) {
		coins -= amount;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

	public int getWave() {
		return wave;
	}
	
	public Spawner getSpawner() {
		return spawner;
	}
	
	public List<Enemy> getEnemies() {
		return enemies;
	}

	public List<Tower> getTowers() {
		return towers;
	}

	public GameMenu getGameMenu() {
		return gameMenu;
	}

	public void render(Graphics g) {
		// Set background
		if (gameState != State.MAIN_MENU && gameState != State.MAP_SELECT)
			g.setColor(new Color(81, 87, 86));
		else
			g.setColor(Color.GRAY);
		g.fillRect(0, 0, (int) Commons.RESOLUTION.getWidth(), (int) Commons.RESOLUTION.getHeight());
		
		if (gameState == State.MAP_SELECT) {
			map = new Map(mapSelected);
		}
		
		// Render map
		if (gameState != State.MAIN_MENU) {
			g.setClip(Commons.MAP_START_POS, 0, Commons.MAP_WIDTH * Commons.BLOCK_SIZE,
					Commons.MAP_HEIGHT * Commons.BLOCK_SIZE);
			map.render(g);

			// Render entities with lower drawing priority, then by lower y value first
			synchronized(entities) {
		        Collections.sort(entities, new Comparator<Entity>() {
		            public int compare(Entity a, Entity b) {
		                return Integer.compare(a.getDrawingPriority(), b.getDrawingPriority());
		            }
		        }.thenComparing(new Comparator<Entity>() {
		            public int compare(Entity a, Entity b) {
		                return (int) a.getPosition().getY() - (int) b.getPosition().getY();
		            }
		        }));
			}
	        synchronized(entities) {
	    		for (Entity entity : entities) entity.render(g);
	        }
	        
			g.setClip(0, 0, (int) Commons.RESOLUTION.getWidth(), (int) Commons.RESOLUTION.getHeight());

			// Set map border
			g.setColor(Color.BLACK);
			g.drawLine(Commons.MAP_START_POS, Commons.MAP_TOP_POS, Commons.MAP_START_POS, Commons.MAP_BOTTOM_POS);
			g.drawLine(Commons.MAP_START_POS, Commons.MAP_BOTTOM_POS, Commons.MAP_END_POS, Commons.MAP_BOTTOM_POS);
			g.drawLine(Commons.MAP_END_POS, Commons.MAP_BOTTOM_POS, Commons.MAP_END_POS, Commons.MAP_TOP_POS);
		}
		
		
		// Render game menu
		gameMenu.render((Graphics2D) g);

	}

	public void tick() {
		spawner.tick(this);
		
		// Sort enemies by distance
		Collections.sort(enemies, new Comparator<Enemy>() {
			public int compare(Enemy a, Enemy b) {
				return (int) b.getDistance() - (int) a.getDistance();
			}
		});

		synchronized(entities) {
			for (Entity entity : entities) {
				entity.tick(this);
				
			}
		}
		synchronized(entities) {
			entities.addAll(entitiesToAdd);
		}
		synchronized(entities) {
			entities.removeAll(entitiesToRemove);
		}
		synchronized(entities) {
			entitiesToAdd.clear();
		}
		synchronized(entities) {
			entitiesToRemove.clear();
		}
		
		// Game over when no more lives
		if (lives <= 0) {
			gameState = State.GAME_OVER;
		} else if (gameMenu.getDisplayedWave() > 50) {
			gameState = State.GAME_WON;
		}
	}

	public Map getMap() {
		return map;
	}
	
	public void resetGame() {
		entities.clear();
		entitiesToAdd.clear();
		entitiesToRemove.clear();
		towers.clear();
		enemies.clear();
		lives = difficulty.getLives();
		wave = 0;
		coins = difficulty.getStartingCash();
		spawner.init();
		map.init();
		gameMenu.init(this);
		gameState = State.GAME;
	}
	
	public void setState(State state) {
		gameState = state;
	}
	
	public State getState() {
		return gameState;
	}
	
	public Difficulty getDifficulty() {
		return difficulty;
	}
	
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public void selectMap(int mapSelected) {
		this.mapSelected = mapSelected;
	}
	
	public int getSelectedMap() {
		return mapSelected;
	}
	
	public static BufferedImage readImage(String fileName) {
		BufferedImage image;
		try (InputStream in = Game.class.getResourceAsStream("/resources/" + fileName + ".png")) {
			ImageIO.setUseCache(false);
			image = ImageIO.read(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		return image;
	}
}
