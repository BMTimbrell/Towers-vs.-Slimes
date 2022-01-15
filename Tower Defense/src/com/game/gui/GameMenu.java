package com.game.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.LineMetrics;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;

import com.game.Commons;
import com.game.Difficulty;
import com.game.Game;
import com.game.Spawner;
import com.game.State;
import com.game.Vector2f;
import com.game.Vector2i;
import com.game.enemies.Enemy;
import com.game.towers.*;

public final class GameMenu extends MouseAdapter {

	private TowerButton[] towerButtons;

	private Vector2i cursor;
	private boolean itemHeld, waveReady, waveStart;
	private TowerType towerType, itemType;
	private Tower tower;
	private List<Tower> towers;
	private Game game;
	private Block[][] blocks;
	private Vector2i blockPosition;
	private int blockX, blockY, displayedWave;
	private Font text, heading;
	private Color darkGray;
	private Button startWaveButton, targetFirstButton, targetLastButton, upgradeButton, sellButton, 
		playAgainButton, mainMenuButton, startGameButton, playButton, exitButton, easyButton, normalButton, hardButton, map1Button, map2Button, map3Button;
	private InfoBox infoBox;
	private Spawner spawner;
	private double costModifier;
	private Image heart, coin, title;

	public GameMenu(Game game) {
		init(game);
	}

	public void init(Game game) {
		this.game = game;
		towers = game.getTowers();
		cursor = new Vector2i(0, 0);
		towerButtons = new TowerButton[5];
		itemHeld = false;
		waveReady = true;
		waveStart = false;
		spawner = game.getSpawner();
		blocks = game.getMap().getBlocks();
		text = new Font("Courier New", Font.PLAIN, 14);
		heading = new Font("Courier New", Font.BOLD, 15);
		darkGray = new Color(68, 69, 77);
		displayedWave = 0;
		costModifier = game.getDifficulty().getCostModifier();
		heart = Game.readImage("heart");
		coin = Game.readImage("coin");
		title = Game.readImage("Title");
		
		// Create buttons in centre of screen
		for (int i = 0; i < towerButtons.length; i++) {
			towerButtons[i] = new TowerButton(
					(((int) Commons.RESOLUTION.getWidth() / 2)
							- ((towerButtons.length * (Commons.BUTTON_WIDTH + Commons.CELL_SPACE)) / 2))
							+ (i * (Commons.BUTTON_WIDTH + Commons.CELL_SPACE)),
					(Commons.MAP_BOTTOM_POS) + Commons.MAP_BOTTOM_MARGIN, Commons.BUTTON_WIDTH, Commons.BUTTON_HEIGHT);

			// Assign tower
			switch (i) {
			case 0:
				towerButtons[i].setType(TowerType.TOWER1);
				break;
			case 1:
				towerButtons[i].setType(TowerType.TOWER2);
				break;
			case 2:
				towerButtons[i].setType(TowerType.TOWER3);
				break;
			case 3:
				towerButtons[i].setType(TowerType.TOWER4);
				break;
			case 4:
				towerButtons[i].setType(TowerType.TOWER5);
				break;
			}
		}
	}
	
	private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

	public void mousePressed(MouseEvent e) {
		click(e.getButton());
	}

	public void mouseMoved(MouseEvent e) {
		cursor = new Vector2i(e.getX(), e.getY());
	}

	public void mouseDragged(MouseEvent e) {
		cursor = new Vector2i(e.getX(), e.getY());
	}

	private void click(int buttonClicked) {
		// Checks if left click
		if (buttonClicked == 1) {

			// Click tower button
			if (game.getState() == State.GAME) {
				for (TowerButton button : towerButtons) {
					if (button.contains(cursor.toPoint()) && game.getCoins() >= (int) (button.getType().getPrice() * costModifier)) {
						deselectTower();
						// Draw and remove tower icon at cursor coordinates
						if (itemType == button.getType() && itemHeld) {
							itemHeld = false;
						} else {
							itemHeld = true;
							itemType = button.getType();
						}
					}
				}
			}

			// Target Priority buttons
			if (towers.size() > 0) {
				if (targetFirstButton != null) {
					if (targetFirstButton.getBounds().contains(cursor.toPoint())) {
						towers.get(0).setPriority(0);
					} else if (targetLastButton.getBounds().contains(cursor.toPoint())) {
						towers.get(0).setPriority(1);
					}	
				}

				// Upgrade button
				if (upgradeButton != null && upgradeButton.getBounds().contains(cursor.toPoint())
						&& (int) (towers.get(0).getUpgradeCost() * costModifier) <= game.getCoins()) {
					game.spendCoins((int) (towers.get(0).getUpgradeCost() * costModifier));
					towers.get(0).addUpgrade();
				}

				// Sell Button
				if (sellButton != null && sellButton.getBounds().contains(cursor.toPoint())) {
					game.addCoins((int) (towers.get(0).getSellPrice() * costModifier));
					game.removeEntity(towers.get(0));
					towers.get(0).getBlock().removeTower();
					towers.remove(towers.get(0));
				}
			}

			// Click Start Wave button
			if (startWaveButton != null && startWaveButton.getBounds().contains(cursor.toPoint())) {
				waveStart = true;
				waveReady = false;
			}
			
			// Click play again button
			if (playAgainButton != null && playAgainButton.getBounds().contains(cursor.toPoint())) {
				game.resetGame();
			}
			
			// Map buttons
			if (map1Button != null && map1Button.getBounds().contains(cursor.toPoint())) {
				game.selectMap(0);
			} else if (map2Button != null && map2Button.getBounds().contains(cursor.toPoint())) {
				game.selectMap(1);
			} else if (map3Button != null && map3Button.getBounds().contains(cursor.toPoint())) {
				game.selectMap(2);
			}
			
			// Difficulty buttons
			if (easyButton != null && easyButton.getBounds().contains(cursor.toPoint())) {
				game.setDifficulty(Difficulty.EASY);
			} else if (normalButton != null && normalButton.getBounds().contains(cursor.toPoint())) {
				game.setDifficulty(Difficulty.NORMAL);
			} else if (hardButton != null && hardButton.getBounds().contains(cursor.toPoint())) {
				game.setDifficulty(Difficulty.HARD);
			}
			
			// Start Game button
			if (startGameButton != null && startGameButton.getBounds().contains(cursor.toPoint())) {
				game.setState(State.GAME);
				game.resetGame();
			}
			
			// Main Menu button
			if (mainMenuButton != null && mainMenuButton.getBounds().contains(cursor.toPoint())) {
				if (game.getState() != State.MAP_SELECT) game.resetGame();
				game.setState(State.MAIN_MENU);
			}
			
			// Play button
			if (playButton != null && playButton.getBounds().contains(cursor.toPoint())) {
				game.setState(State.MAP_SELECT);
			}
			
			// Exit button
			if (exitButton != null && exitButton.getBounds().contains(cursor.toPoint())) {
				System.exit(0);
			}
			
			blockPosition = cursor.sub(Commons.MAP_ORIGIN).divide(Commons.BLOCK_SIZE);
			blockX = blockPosition.getX();
			blockY = blockPosition.getY();

			if (blockX < 0 || blockY < 0 || blockX >= blocks[0].length || blockY >= blocks.length)
				return;

			Block block = blocks[blockY][blockX];

			// Place towers on empty grass blocks
			if (block.getTileType() == TileType.GRASS && block.contains(cursor.toPoint()) && block.getTower() == null
					&& itemHeld) {

				float towerX = block.x + Commons.BLOCK_SIZE / 2;
				float towerY = block.y + Commons.BLOCK_SIZE / 2;

				switch (towerType) {
				case TOWER1:
					tower = new BasicTower(new Vector2f(towerX, towerY), towerType.getWidth(), towerType.getHeight(),
							towerType, block);
					break;
				case TOWER2:
					tower = new CannonTower(new Vector2f(towerX, towerY), towerType.getWidth(), towerType.getHeight(),
							towerType, block);
					break;
				case TOWER3:
					tower = new IceTower(new Vector2f(towerX, towerY), towerType.getWidth(), towerType.getHeight(),
							towerType, block);
					break;
				case TOWER4:
					tower = new FireTower(new Vector2f(towerX, towerY), towerType.getWidth(), towerType.getHeight(),
							towerType, block);
					break;
				case TOWER5:
					tower = new PlasmaTower(new Vector2f(towerX, towerY), towerType.getWidth(), towerType.getHeight(),
							towerType, block);
					break;
				default:
					break;
				}

				block.setTower(tower);
				towers.add(tower);
				game.addEntity(tower);
				game.spendCoins((int) (towerType.getPrice() * costModifier));
				itemHeld = false;
				Collections.swap(towers, 0, towers.size() - 1);

				// Select and deselect when clicking on tower
			} else if (block.contains(cursor.toPoint()) && block.getTower() != null && !itemHeld) {

				Tower tower = block.getTower();

				if (!tower.isSelected())
					tower.setSelected(true);
				else
					tower.setSelected(false);
				Collections.swap(towers, 0, towers.indexOf(tower));
				for (int j = 1; j < towers.size(); j++) {
					towers.get(j).setSelected(false);
				}
			} else if (block.contains(cursor.toPoint()) && block.getTower() == null) {
				deselectTower();
			}
		} else {
			itemHeld = false;
		}
	}

	// Highlight block to show if can place tower
	private void highlightBlock(Graphics2D g) {
		blockPosition = cursor.sub(Commons.MAP_ORIGIN).divide(Commons.BLOCK_SIZE);
		blockX = blockPosition.getX();
		blockY = blockPosition.getY();
		Rectangle highlightedBlock = blockPosition.multiply(Commons.BLOCK_SIZE).add(Commons.MAP_ORIGIN)
				.generateRect(Commons.BLOCK_DIMENSION);

		if (blockX < 0 || blockY < 0 || blockX >= blocks[0].length || blockY >= blocks.length)
			return;

		Block block = blocks[blockY][blockX];

		if (block.getTileType() == TileType.GRASS && block.contains(cursor.toPoint()) && block.getTower() == null) {
			g.setColor(new Color(0, 75, 255, 100));
			g.fill(highlightedBlock);
		} else if ((block.getTileType() == TileType.PATH || block.getTower() != null)
				&& block.contains(cursor.toPoint())) {
			g.setColor(new Color(255, 75, 75, 100));
			g.fill(highlightedBlock);
		}
	}

	private void showTowerInfo(Graphics g, int textX, int textY, int headingY, int boxY, int boxHeight) {
		g.setColor(Color.WHITE);
		g.setFont(heading);
		g.drawString(towerType.getName(), textX, headingY);
		g.drawString("Cost: " + (int) (towerType.getPrice() * costModifier), textX, boxY + boxHeight - Commons.BOX_PADDING);
		g.setFont(text);
		switch (towerType) {
		case TOWER1:
			g.drawString("A simple but cheap tower.", textX, textY);
			break;
		case TOWER2:
			g.drawString("Shoots cannonballs in 8 directions.", textX, textY);
			break;
		case TOWER3:
			g.drawString("Slows enemies with chilling attacks.", textX, textY);
			break;
		case TOWER4:
			g.drawString("Shoots fireballs that ignite the enemy.", textX, textY);
			break;
		case TOWER5:
			g.drawString("Has a rapid rate of fire and good range.", textX, textY);
			break;
		default:
			break;
		}
	}

	private void deselectTower() {
		if (towers.size() > 0)
			towers.get(0).setSelected(false);
	}

	public void setWaveReady(boolean waveReady) {
		this.waveReady = waveReady;
	}

	public boolean isWaveStart() {
		return waveStart;
	}

	public void setWaveStart(boolean waveStart) {
		this.waveStart = waveStart;
	}
	
	public int getDisplayedWave() {
		return displayedWave;
	}
	
	public Vector2i getCursor() {
		return cursor;
	}
	
	private void loadButtons() {
		// Play Again button
		Vector2i size = new Vector2i(100, 20);
		int buttonSpace = size.multiply(2).add(new Vector2i(30, 0)).getX();
		int bX = ((int) Commons.RESOLUTION.getWidth() - buttonSpace) / 2;
		Vector2i position = new Vector2i(bX, 300);
		Rectangle rect = position.generateRect(size);
		playAgainButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Play Again");
		if (playAgainButton.getBounds().contains(cursor.toPoint())) playAgainButton.highlight(Color.WHITE);
		// Main Menu Button
		position = position.add(new Vector2i(size.add(new Vector2i(30, 0)).getX(), 0));
		rect = position.generateRect(size);
		mainMenuButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Main Menu");
		if (mainMenuButton.getBounds().contains(cursor.toPoint())) mainMenuButton.highlight(Color.WHITE);
	}

	public void render(Graphics2D g) {
		
		if (game.getState() == State.GAME) {
			// Dimensions and coordinates for info box and text
			int boxX = towerButtons[0].x - Commons.BUTTON_BORDER;
			int boxY = towerButtons[0].y + towerButtons[0].height + Commons.BUTTON_BOTTOM_MARGIN;
			int boxWidth = (towerButtons[towerButtons.length - 1].x + towerButtons[towerButtons.length - 1].width
					+ Commons.BUTTON_BORDER) - boxX;
			int boxHeight = 120;
			int textX = boxX + Commons.BOX_PADDING;
			g.setFont(heading);
			LineMetrics lm = g.getFontMetrics().getLineMetrics("A", g);
			int textHeight = (int) (lm.getAscent() - lm.getDescent());
			int headingY = boxY + textHeight + Commons.BOX_PADDING;
			int textY = headingY + Commons.HEADING_BOTTOM_MARGIN;
			infoBox = new InfoBox(boxX, boxY, boxWidth, boxHeight);
			infoBox.render(g);

			// Enemy info
			Enemy enemy = spawner.getNewEnemy();
			String enemyNameText = "New Enemy: " + enemy.getName();
			g.drawString(enemyNameText, textX, headingY);
			int textWidth = (int) g.getFontMetrics().getStringBounds(enemyNameText, g).getWidth();
			// Health
			String enemyStatText = "Health: " + enemy.getHealth();
			g.setFont(new Font("Arial", Font.BOLD, 11));
			int enemyTextY = textY;
			g.drawString(enemyStatText, textX, enemyTextY);
			// Speed
			enemyStatText = "Speed: " + enemy.getSpeed();
			enemyTextY += Commons.ENEMY_INFO_SPACING;
			g.drawString(enemyStatText, textX, enemyTextY);
			// Coins awarded
			enemyStatText = "Coins Awarded: " + enemy.getCoinsAwarded();
			enemyTextY += Commons.ENEMY_INFO_SPACING;
			g.drawString(enemyStatText, textX, enemyTextY);
			// Lives lost
			enemyStatText = "Lives Lost Upon Reaching Exit: " + enemy.getLivesLost();
			enemyTextY += Commons.ENEMY_INFO_SPACING;
			g.drawString(enemyStatText, textX, enemyTextY);

			if (waveReady) {
				// Render start button
				g.setFont(heading);
				g.setColor(darkGray);
				Vector2i size = new Vector2i(100, 20);
				Vector2i position = new Vector2i(Commons.MAP_START_POS, (boxY + (boxHeight - (size.getY() + Commons.BOX_PADDING))));
				Rectangle rect = position.generateRect(size);
				startWaveButton = new Button(rect.x, rect.y, rect.width, rect.height, heading,
						"Start Wave");
				if (startWaveButton.getBounds().contains(cursor.toPoint())) {
					startWaveButton.highlight();
				}
				startWaveButton.render(g);
			} else {
				startWaveButton = null;
			}

			// Show selected tower
			if (towers.size() > 0 && towers.get(0).isSelected()) {
				// Tower information
				infoBox.render(g);
				g.setColor(Color.WHITE);
				g.setFont(heading);
				if (towers.get(0) instanceof BasicTower && towers.get(0).getUpgrades() > 4)
					g.drawString("Laser Tower", textX, headingY);
				else
					g.drawString(towers.get(0).getTowerType().getName(), textX, headingY);
				g.setFont(new Font("Courier New", Font.BOLD, 11));
				int stringWidth = 0;
				if (towers.get(0).getUpgrades() < 5) {
					g.drawString("Upgrade: ", textX, textY);
					stringWidth = (int) g.getFontMetrics().getStringBounds("Upgrade: ", g).getWidth();
					g.setFont(text);
				}
				drawString(g, towers.get(0).showUpgrade(), textX + stringWidth, textY - g.getFontMetrics().getHeight());

				// Target priority buttons
				Font font = new Font("Arial", Font.BOLD, 11);
				g.setColor(darkGray);
				// Priority text width
				g.setFont(new Font("Courier New", Font.BOLD, 11));
				textWidth = (int) g.getFontMetrics().getStringBounds("Priority:", g).getWidth();
				int priorityX = boxX + (boxWidth / 2);
				// Priority text height
				lm = g.getFontMetrics().getLineMetrics("P", g);
				textHeight = (int) (lm.getAscent() - lm.getDescent());
				int pButtonY = boxY + Commons.BOX_PADDING;
				Vector2i size = new Vector2i(Commons.PBUTTON_WIDTH, Commons.PBUTTON_HEIGHT);
				Vector2i position = new Vector2i(priorityX + textWidth + 10, pButtonY);
				Rectangle rect = position.generateRect(size);
				targetFirstButton = new Button(rect.x, rect.y, rect.width, rect.height, font,
						"First");
				position = new Vector2i(boxX + boxWidth - Commons.PBUTTON_WIDTH - Commons.BOX_PADDING,
						boxY + Commons.BOX_PADDING);
				rect = position.generateRect(size);
				targetLastButton = new Button(rect.x, rect.y, rect.width, rect.height, font, "Last");

				if (towers.get(0).getType() != TowerType.TOWER2) {
					// Draw priority text
					g.setColor(Color.WHITE);
					g.drawString("Priority:", priorityX, pButtonY + (Commons.PBUTTON_HEIGHT / 2) + (textHeight / 2));

					if (towers.get(0).getPriority() == 0) {
						targetFirstButton.highlight();
					}
					targetFirstButton.render(g);

					if (towers.get(0).getPriority() == 1) {
						targetLastButton.highlight();
					}
					targetLastButton.render(g);
				}

				// Upgrade button
				if (towers.get(0).getUpgrades() < 5) {
					size = new Vector2i(Commons.UBUTTON_WIDTH, Commons.UBUTTON_HEIGHT);
					position = new Vector2i(boxX + Commons.BOX_PADDING,
							(boxY + boxHeight) - (Commons.BOX_PADDING + size.getY()));
					rect = position.generateRect(size);
					upgradeButton = new Button(rect.x, rect.y, rect.width, rect.height, font,
							"Cost: " + (int) (towers.get(0).getUpgradeCost() * costModifier));
					if (upgradeButton.getBounds().contains(cursor.toPoint())) {
						upgradeButton.highlight();
					}
					upgradeButton.render(g);
				} else {
					upgradeButton = null;
				}

				// Sell Button
				size = new Vector2i(Commons.UBUTTON_WIDTH, Commons.UBUTTON_HEIGHT);
				position = new Vector2i((boxX + boxWidth) - Commons.BOX_PADDING - size.getX(),
						(boxY + boxHeight) - (Commons.BOX_PADDING + size.getY()));
				rect = position.generateRect(size);
				sellButton = new Button(rect.x, rect.y, rect.width, rect.height, font,
						"Sell: " + (int) (towers.get(0).getSellPrice() * costModifier));
				if (sellButton.getBounds().contains(cursor.toPoint())) {
					sellButton.highlight();
				}
				sellButton.render(g);

			} else {
				upgradeButton = null;
				sellButton = null;
				targetLastButton = null;
				targetFirstButton = null;
			}

			// Change button color and show tower information on mouseover
			for (int i = 0; i < towerButtons.length; i++) {
				if (towerButtons[i].contains(cursor.toPoint())) {
					towerType = towerButtons[i].getType();
					towerButtons[i].setColor(new Color(255, 255, 255, 150));
					infoBox.render(g);
					showTowerInfo(g, textX, textY, headingY, boxY, boxHeight);
				} else {
					towerButtons[i].setColor(Color.GRAY);
					towerType = itemType;
				}

				// Draw tower buttons
				Vector2i borderPosition = new Vector2i(towerButtons[i].x - 5, towerButtons[i].y - 5);
				Vector2i borderSize = new Vector2i(towerButtons[i].width + (Commons.BUTTON_BORDER * 2),
						(towerButtons[i].height + (Commons.BUTTON_BORDER * 2)));
				Rectangle border = borderPosition.generateRect(borderSize);
				g.setColor(darkGray);
				g.fill(border);
				g.setColor(towerButtons[i].getColor());
				g.fill(towerButtons[i]);
				g.drawImage(towerButtons[i].getType().getImage(),
						towerButtons[i].x + ((Commons.BUTTON_WIDTH / 2) - (Commons.TOWER_ICON_WIDTH) / 2),
						towerButtons[i].y + ((Commons.BUTTON_HEIGHT / 2) - (Commons.TOWER_ICON_HEIGHT / 2)),
						Commons.TOWER_ICON_WIDTH, Commons.TOWER_ICON_HEIGHT, null);

				if (itemHeld) {

					// Draw tower icon at cursor
					g.drawImage(itemType.getImage(), cursor.getX() - (itemType.getWidth() / 2),
							cursor.getY() - (itemType.getHeight() / 2), itemType.getWidth(), itemType.getHeight(), null);

					// Draw tower range at cursor
					g.setColor(new Color(255, 255, 255, 10));
					g.fillOval(cursor.getX() - (itemType.getDefaultRange() / 2),
							(cursor.getY() - itemType.getDefaultRange() / 2)
									+ ((itemType.getHeight() / 2) - (Commons.BLOCK_SIZE / 2)),
							itemType.getDefaultRange(), itemType.getDefaultRange());

				}
			}
			
			
			// Show wave number
			if (waveReady)
				displayedWave = game.getWave() + 1;
			else
				displayedWave = game.getWave();
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.BOLD, 20));
			lm = g.getFontMetrics().getLineMetrics("A", g);
			textHeight = (int) (lm.getAscent() - lm.getDescent());
			g.drawString("Wave: " + displayedWave, Commons.MAP_START_POS,
					Commons.MAP_BOTTOM_POS + Commons.MAP_BOTTOM_MARGIN + textHeight);
			
			// Show lives
			int heartY = Commons.MAP_BOTTOM_POS + Commons.MAP_BOTTOM_MARGIN + textHeight + Commons.WAVE_MARGIN_BOTTOM;
			g.drawImage(heart, Commons.MAP_START_POS,
					heartY, Commons.STAT_ICON_SIZE, Commons.STAT_ICON_SIZE,
					null);
			g.setFont(new Font("Ink Free", Font.BOLD, 14));
			lm = g.getFontMetrics().getLineMetrics("A", g);
			int statHeight = (int) (lm.getAscent() - lm.getDescent());
			int livesY = heartY + statHeight + ((Commons.STAT_ICON_SIZE - statHeight) / 2);
			g.drawString("" + game.getLives(), Commons.MAP_START_POS + Commons.STAT_ICON_SIZE + Commons.STAT_SPACE_LEFT,
					livesY);

			// Show coins
			int coinY = heartY + Commons.STAT_ICON_SIZE + Commons.STAT_ICON_SPACE;
			g.drawImage(coin, Commons.MAP_START_POS,
					coinY,
					Commons.STAT_ICON_SIZE, Commons.STAT_ICON_SIZE, null);
			int coinsY = livesY + Commons.STAT_ICON_SPACE + Commons.STAT_ICON_SIZE;
			g.drawString("" + game.getCoins(), Commons.MAP_START_POS + Commons.STAT_ICON_SIZE + Commons.STAT_SPACE_LEFT,
					coinsY);

			if (itemHeld)
				// Highlight blocks on mouseover
				highlightBlock(g);
		} else if (game.getState() == State.GAME_OVER) {		
			loadButtons();
			// Game Over text
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.BOLD, 40));
			int textWidth = (int) g.getFontMetrics().getStringBounds("Game Over", g).getWidth();
			g.drawString("Game Over", ((int) Commons.RESOLUTION.getWidth() - textWidth) / 2, 200);
			// Discouraging message
			g.setFont(new Font("Courier New", Font.BOLD, 20));
			textWidth = (int) g.getFontMetrics().getStringBounds("You Suck!", g).getWidth();
			g.drawString("You Suck!", ((int) Commons.RESOLUTION.getWidth() - textWidth) / 2, 250);
			// Play Again Button
			playAgainButton.render(g);
			// Main Menu button
			mainMenuButton.render(g);
		} else if (game.getState() == State.GAME_WON) {
			loadButtons();
			// You Win text
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.BOLD, 40));
			int textWidth = (int) g.getFontMetrics().getStringBounds("You Win!", g).getWidth();
			g.drawString("You Win!", ((int) Commons.RESOLUTION.getWidth() - textWidth) / 2, 200);
			// Play Again Button
			playAgainButton.render(g);
			// Main Menu button
			mainMenuButton.render(g);
		} else if (game.getState() == State.MAIN_MENU) {
			// Title
			int titleX = ((int) Commons.RESOLUTION.getWidth() - title.getWidth(null)) / 2;
			g.drawImage(title, titleX, 80, null);
			// Play button
			Vector2i size = new Vector2i(100, 20);
			int bX = ((int) Commons.RESOLUTION.getWidth() - size.getX()) / 2;
			int bY = Commons.MAP_BOTTOM_POS + Commons.MAP_BOTTOM_MARGIN;
			Vector2i position = new Vector2i(bX, bY);
			Rectangle rect = position.generateRect(size);
			playButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Play");
			if (playButton.getBounds().contains(cursor.toPoint())) playButton.highlight();
			playButton.render(g);
			// Exit button
			bY += size.getY() + 40;
			position = new Vector2i(bX, bY);
			rect = position.generateRect(size);
			exitButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Exit");
			if (exitButton.getBounds().contains(cursor.toPoint())) exitButton.highlight();
			exitButton.render(g);
		} else if (game.getState() == State.MAP_SELECT) {
			// Map 1 button
			Vector2i size = new Vector2i(80, 20);
			int buttonSpace = size.multiply(3).add(new Vector2i(30, 0)).getX();
			int bX = ((int) Commons.RESOLUTION.getWidth() - buttonSpace) / 2;
			int bY = Commons.MAP_BOTTOM_POS + Commons.MAP_BOTTOM_MARGIN;
			Vector2i position = new Vector2i(bX, bY);
			Rectangle rect = position.generateRect(size);
			map1Button = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Map 1");
			if (game.getSelectedMap() == 0) map1Button.highlight();
			map1Button.render(g);
			// Map 2 button
			position = position.add(new Vector2i(size.add(new Vector2i(30, 0)).getX(), 0));
			rect = position.generateRect(size);
			map2Button = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Map 2");
			if (game.getSelectedMap() == 1) map2Button.highlight();
			map2Button.render(g);
			// Map 3 button
			position = position.add(new Vector2i(size.add(new Vector2i(30, 0)).getX(), 0));
			rect = position.generateRect(size);
			map3Button = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Map 3");
			if (game.getSelectedMap() == 2) map3Button.highlight();
			map3Button.render(g);
			
			// Select Map text
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.BOLD, 20));
			LineMetrics lm = g.getFontMetrics().getLineMetrics("A", g);
			int textHeight = (int) (lm.getAscent() - lm.getDescent());
			int mapTextWidth = (int) g.getFontMetrics().getStringBounds("Select Map:", g).getWidth();
			int difficultyTextWidth = (int) g.getFontMetrics().getStringBounds("Difficulty:", g).getWidth();
			int tX = bX - 150;
			int tY = bY + (size.getY() - textHeight) + textHeight / 2;
			g.drawString("Select Map:", tX, tY);
			// Select Difficulty text
			tX -= difficultyTextWidth - mapTextWidth;
			tY += 50;
			g.drawString("Difficulty:", tX, tY);
			// Easy button
			position = new Vector2i(bX, bY + 50);
			rect = position.generateRect(size);
			easyButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Easy");
			if (game.getDifficulty() == Difficulty.EASY) easyButton.highlight();
			easyButton.render(g);
			// Normal button
			position = position.add(new Vector2i(size.add(new Vector2i(30, 0)).getX(), 0));
			rect = position.generateRect(size);
			normalButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Normal");
			if (game.getDifficulty() == Difficulty.NORMAL) normalButton.highlight();
			normalButton.render(g);
			// Hard button
			position = position.add(new Vector2i(size.add(new Vector2i(30, 0)).getX(), 0));
			rect = position.generateRect(size);
			hardButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Hard");
			if (game.getDifficulty() == Difficulty.HARD) hardButton.highlight();
			hardButton.render(g);
			// Start Game button
			size = new Vector2i(100, 20);
			position = (new Vector2i(tX, tY + 70));
			rect = position.generateRect(size);
			startGameButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Start Game");
			if (startGameButton.getBounds().contains(cursor.toPoint())) startGameButton.highlight();
			startGameButton.render(g);
			// Main Menu button
			position = position.add(new Vector2i(size.getX() + 20, 0));
			rect = position.generateRect(size);
			mainMenuButton = new Button(rect.x, rect.y, rect.width, rect.height, heading, "Main Menu");
			if (mainMenuButton.getBounds().contains(cursor.toPoint())) mainMenuButton.highlight();
			mainMenuButton.render(g);
		}
		
		if (game.getState() != State.GAME_OVER && game.getState() != State.GAME_WON) {
			playAgainButton = null;
		} 
		
		if (game.getState() != State.MAP_SELECT) {
			easyButton = null;
			normalButton = null;
			hardButton = null;
			map1Button = null;
			map2Button = null;
			map3Button = null;
			startGameButton = null;
		}
		
		if (game.getState() != State.MAIN_MENU) {
			playButton = null;
			exitButton = null;
		}
		
		if (game.getState() == State.GAME || game.getState() == State.MAIN_MENU) mainMenuButton = null;

	}
}
