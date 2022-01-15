package com.game;

import com.game.enemies.*;
import com.game.gui.GameMenu;

public class Spawner {

	private int spawnTimer, spawnRate, enemiesToSpawn;
	private boolean waveStart;
	private Enemy newEnemy;
	private GameMenu gameMenu;

	public Spawner() {
		init();
	}
	
	public void init() {
		spawnTimer = 0;
		spawnRate = 60;
		enemiesToSpawn = 0;
		waveStart = false;
		newEnemy = new GreenSlime();
	}
	
	public void tick(Game game) {
		gameMenu = game.getGameMenu();
		
		// Wave 1
		if (game.getWave() == 1) {
			if (waveStart) {
				enemiesToSpawn = 10;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GreenSlime());
			}
			// Wave 2
		} else if (game.getWave() == 2) {
			if (waveStart) {
				enemiesToSpawn = 15;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GreenSlime());
			}
			// Wave 3
			if (gameMenu.getDisplayedWave() == 3) newEnemy =  new BlueSlime();
		} else if (game.getWave() == 3) {
			if (waveStart) {
				enemiesToSpawn = 20;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn > 15) {
					spawnRate = 60;
					spawnEnemy(game, new BlueSlime());
				} else {
					spawnRate = 30;
					spawnEnemy(game, new GreenSlime());
				}
			}
			// Wave 4
		} else if (game.getWave() == 4) {
			if (waveStart) {
				enemiesToSpawn = 20;
				spawnRate = 40;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 1) {
					spawnEnemy(game, new BlueSlime());
				} else {
					spawnEnemy(game, new GreenSlime());
				}
			}
			// Wave 5
		} else if (game.getWave() == 5) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnRate = 30;
				if (enemiesToSpawn > 10) {
					spawnEnemy(game, new BlueSlime());
				} else {
					spawnRate = 10;
					spawnEnemy(game, new GreenSlime());
				}
			}
			// Wave 6
			if (gameMenu.getDisplayedWave() == 6) newEnemy =  new GiantGreenSlime();
		} else if (game.getWave() == 6) {
			if (waveStart) {
				enemiesToSpawn = 1;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantGreenSlime());
			}
			// Wave 7
			if (gameMenu.getDisplayedWave() == 7) newEnemy =  new YellowSlime();
		} else if (game.getWave() == 7) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
				spawnRate = 20;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 5 == 0) {
					spawnEnemy(game, new YellowSlime());
				} else {
					spawnEnemy(game, new BlueSlime());
				}
			}
			// Wave 8
			if (gameMenu.getDisplayedWave() == 8) newEnemy =  new GiantBlueSlime();
		} else if (game.getWave() == 8) {
			if (waveStart) {
				enemiesToSpawn = 1;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantBlueSlime());
			}
			// Wave 9
		} else if (game.getWave() == 9) {
			if (waveStart) {
				enemiesToSpawn = 30;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn > 27) {
					spawnRate = 50;
					spawnEnemy(game, new GiantGreenSlime());
				} else  if (enemiesToSpawn < 28 && enemiesToSpawn > 20) {
					spawnRate = 5;
					spawnEnemy(game, new GreenSlime());
				} else {
					spawnRate = 15;
					spawnEnemy(game, new BlueSlime());
				}
				if (enemiesToSpawn % 5 == 0) spawnEnemy(game, new YellowSlime());
			} 
			// Wave 10
			if (gameMenu.getDisplayedWave() == 10) newEnemy =  new RedSlime();
		} else if (game.getWave() == 10) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn > 30) {
					spawnRate = 50;
					spawnEnemy(game, new RedSlime());
				} else {
					spawnRate = 15;
					spawnEnemy(game, new BlueSlime());
				}
				if (enemiesToSpawn % 2 == 0 && enemiesToSpawn < 20) spawnEnemy(game, new YellowSlime());
			}
			// Wave 11
			if (gameMenu.getDisplayedWave() == 11) newEnemy =  new GiantYellowSlime();
		} else if (game.getWave() == 11) {
			if (waveStart) {
				enemiesToSpawn = 11;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 11)
					spawnEnemy(game, new GiantYellowSlime());
				else
					spawnEnemy(game, new YellowSlime());
			}
			// Wave 12
		} else if (game.getWave() == 12) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn > 30)
					spawnEnemy(game, new RedSlime());
				else
					spawnEnemy(game, new YellowSlime());
			}
			// Wave 13
			if (gameMenu.getDisplayedWave() == 13) newEnemy =  new GiantRedSlime();
		} else if (game.getWave() == 13) {
			if (waveStart) {
				enemiesToSpawn = 1;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantRedSlime());
			}
			// Wave 14
		} else if (game.getWave() == 14) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn > 15 )
					spawnEnemy(game, new YellowSlime());
				 else
					spawnEnemy(game, new RedSlime());
			}
			// Wave 15
		} else if (game.getWave() == 15) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new RedSlime());
			} 
			// Wave 16
		} else if (game.getWave() == 16) {
			if (waveStart) {
				enemiesToSpawn = 6;
				waveStart = false;
				spawnRate = 40;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn > 3) {
					spawnEnemy(game, new GiantGreenSlime());
				} else if (enemiesToSpawn > 1 && enemiesToSpawn < 3) {
					spawnEnemy(game, new GiantBlueSlime());
				} else {
					spawnEnemy(game, new GiantYellowSlime());
				}
			} 
			// Wave 17
		} else if (game.getWave() == 17) {
			if (waveStart) {
				enemiesToSpawn = 10;
				waveStart = false;
				spawnRate = 60;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn > 6) {
					spawnEnemy(game, new GiantGreenSlime());
				} else if (enemiesToSpawn > 3 && enemiesToSpawn < 7) {
					spawnEnemy(game, new GiantBlueSlime());
				} else if (enemiesToSpawn > 1 && enemiesToSpawn < 4) {
					spawnEnemy(game, new GiantYellowSlime());
				} else {
					spawnEnemy(game, new GiantRedSlime());
				}
			} 
			// Wave 18
			if (gameMenu.getDisplayedWave() == 18) newEnemy =  new PinkSlime();
		} else if (game.getWave() == 18) {
			if (waveStart) {
				enemiesToSpawn = 30;
				waveStart = false;
				spawnRate = 20;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 5 == 0) {
					spawnEnemy(game, new PinkSlime());
				} else if (enemiesToSpawn > 10) {
					spawnEnemy(game, new RedSlime());
				} else {
					spawnEnemy(game, new YellowSlime());
				}
			}
			// Wave 19
			if (gameMenu.getDisplayedWave() == 19) newEnemy =  new GraySlime();
		} else if (game.getWave() == 19) {
			if (waveStart) {
				enemiesToSpawn = 30;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 5 == 0) {
					spawnEnemy(game, new GraySlime());
				} else if (enemiesToSpawn > 10) {
					spawnEnemy(game, new RedSlime());
				} else {
					spawnEnemy(game, new YellowSlime());
				}
			}
			// Wave 20
		} else if (game.getWave() == 20) {
			if (waveStart) {
				enemiesToSpawn = 30;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 0) {
					spawnEnemy(game, new GraySlime());
				} else if (enemiesToSpawn % 2 == 1) {
					spawnEnemy(game, new PinkSlime());
				}
				if (enemiesToSpawn < 10) spawnEnemyIndependent(game, new YellowSlime());
			}
			// Wave 21
			if (gameMenu.getDisplayedWave() == 21) newEnemy =  new GiantPinkSlime();
		} else if (game.getWave() == 21) {
			if (waveStart) {
				enemiesToSpawn = 21;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 21) {
					spawnEnemy(game, new GiantPinkSlime());
				} else {
					spawnEnemy(game, new GraySlime());
				}
			}
			// Wave 22
			if (gameMenu.getDisplayedWave() == 22) newEnemy =  new GiantGraySlime();
		} else if (game.getWave() == 22) {
			if (waveStart) {
				enemiesToSpawn = 21;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 21) {
					spawnEnemy(game, new GiantGraySlime());
				} else {
					spawnEnemy(game, new PinkSlime());
				}
			}
			// Wave 23
		} else if (game.getWave() == 23) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 40) {
					spawnEnemy(game, new GiantRedSlime());
				} else if (enemiesToSpawn < 40 && enemiesToSpawn > 19) {
					spawnEnemy(game, new GraySlime());
				} else if (enemiesToSpawn < 20) {
					spawnEnemy(game, new YellowSlime());
				}
				if (enemiesToSpawn % 5 == 0) spawnEnemyIndependent(game, new PinkSlime());
				if (enemiesToSpawn == 20) spawnEnemyIndependent(game, new GiantGraySlime());
				if (enemiesToSpawn < 6) spawnEnemyIndependent(game, new RedSlime());
				if (enemiesToSpawn == 10) spawnEnemyIndependent(game, new GiantPinkSlime());
			}
			// Wave 24
			if (gameMenu.getDisplayedWave() == 24) newEnemy =  new RainbowSlime();
		} else if (game.getWave() == 24) {
			if (waveStart) {
				enemiesToSpawn = 5;
				waveStart = false;
				spawnRate = 200;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn < 5) spawnRate = 30;
				spawnEnemy(game, new RainbowSlime());

			}
			// Wave 25
		} else if (game.getWave() == 25) {
			if (waveStart) {
				enemiesToSpawn = 60;
				waveStart = false;
				spawnRate = 20;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 0)
					spawnEnemy(game, new GraySlime());
				else
					spawnEnemy(game, new RedSlime());
			}
			// Wave 26
		}  else if (game.getWave() == 26) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
				spawnRate = 20;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 0)
					spawnEnemy(game, new GraySlime());
				else if (enemiesToSpawn % 2 == 1)
					spawnEnemy(game, new PinkSlime());
				if (enemiesToSpawn < 22 && enemiesToSpawn > 1)
					spawnEnemyIndependent(game, new RedSlime());
				if (enemiesToSpawn < 12 && enemiesToSpawn > 1)
					spawnEnemyIndependent(game, new YellowSlime());
			}
			// Wave 27
		}  else if (game.getWave() == 27) {
			if (waveStart) {
				enemiesToSpawn = 6;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 6) spawnEnemy(game, new GiantPinkSlime());
				else if (enemiesToSpawn == 5) spawnEnemy(game, new GiantBlueSlime());
				else if (enemiesToSpawn == 4) spawnEnemy(game, new GiantGraySlime());
				else if (enemiesToSpawn == 3) spawnEnemy(game, new GiantGreenSlime());
				else if (enemiesToSpawn == 2) spawnEnemy(game, new GiantYellowSlime());
				else if (enemiesToSpawn == 1) spawnEnemy(game, new GiantRedSlime());
				if (enemiesToSpawn == 0) spawnEnemyIndependent(game, new RainbowSlime());
			}
			// Wave 28
			if (gameMenu.getDisplayedWave() == 28) newEnemy =  new GiantRainbowSlime();
		} else if (game.getWave() == 28) {
			if (waveStart) {
				enemiesToSpawn = 1;
				waveStart = false;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantRainbowSlime());
			}
			// Wave 29
		}  else if (game.getWave() == 29) {
			if (waveStart) {
				enemiesToSpawn = 12;
				waveStart = false;
				spawnRate = 50;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 12) spawnEnemy(game, new GiantGreenSlime());
				else if (enemiesToSpawn == 9) spawnEnemy(game, new GiantBlueSlime());
				else if (enemiesToSpawn == 6) spawnEnemy(game, new GiantYellowSlime());
				else if (enemiesToSpawn == 3) spawnEnemy(game, new GiantRedSlime());
				else if (enemiesToSpawn % 3 != 0 && (enemiesToSpawn + 1) % 3 == 0) spawnEnemy(game, new GiantGraySlime());
				else if (enemiesToSpawn < 11 && enemiesToSpawn % 3 != 0 && (enemiesToSpawn + 1) % 3 != 0) spawnEnemy(game, new GiantPinkSlime());
			}
			// Wave 30
		} else if (game.getWave() == 30) {
			if (waveStart) {
				enemiesToSpawn = 15;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new RainbowSlime());
			}
			// Wave 31
		} else if (game.getWave() == 31) {
			if (waveStart) {
				enemiesToSpawn = 5;
				waveStart = false;
				spawnRate = 25;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 5 || enemiesToSpawn == 1)
					spawnEnemy(game, new GiantRedSlime());
				else if (enemiesToSpawn == 3)
					spawnEnemy(game, new GiantRainbowSlime());
				else
					spawnEnemy(game, new GiantPinkSlime());
			}
			// Wave 32
		} else if (game.getWave() == 32) {
			if (waveStart) {
				enemiesToSpawn = 2;
				waveStart = false;
				spawnRate = 50;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantRainbowSlime());
			}
			// Wave 33
		} else if (game.getWave() == 33) {
			if (waveStart) {
				enemiesToSpawn = 7;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 7 || enemiesToSpawn == 1)
					spawnEnemy(game, new GiantGraySlime());
				else if (enemiesToSpawn == 6 || enemiesToSpawn == 2)
					spawnEnemy(game, new GiantRedSlime());
				else if (enemiesToSpawn == 5 || enemiesToSpawn == 3)
					spawnEnemy(game, new GiantPinkSlime());
				else
					spawnEnemy(game, new GiantRainbowSlime());
			}
			// Wave 34
		} else if (game.getWave() == 34) {
			if (waveStart) {
				enemiesToSpawn = 60;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 0)
					spawnEnemy(game, new GraySlime());
				else
					spawnEnemy(game, new PinkSlime());
				if ((enemiesToSpawn % 5 == 0 && enemiesToSpawn >= 20) || (enemiesToSpawn % 10 == 0 && enemiesToSpawn < 20))
					spawnEnemyIndependent(game, new GiantRedSlime());
				if ((enemiesToSpawn < 20 && enemiesToSpawn % 4 == 0))
					spawnEnemyIndependent(game, new GiantYellowSlime());
				if (enemiesToSpawn < 10 && enemiesToSpawn % 2 == 0)
					spawnEnemyIndependent(game, new GiantGreenSlime());
				if (enemiesToSpawn < 10 && enemiesToSpawn % 2 == 1)
					spawnEnemyIndependent(game, new GiantBlueSlime());
			}
			// Wave 35
		} else if (game.getWave() == 35) {
			if (waveStart) {
				enemiesToSpawn = 30;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new RainbowSlime());
			}
			// Wave 36
		} else if (game.getWave() == 36) {
			if (waveStart) {
				enemiesToSpawn = 3;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantRainbowSlime());
			}
			// Wave 37
		} else if (game.getWave() == 37) {
			if (waveStart) {
				enemiesToSpawn = 11;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 11 || enemiesToSpawn == 6 || enemiesToSpawn == 1)
					spawnEnemy(game, new GiantRainbowSlime());
				else if (enemiesToSpawn > 8 || enemiesToSpawn < 4)
					spawnEnemy(game, new GiantPinkSlime());
				else
					spawnEnemy(game, new GiantRedSlime());
			}
			// Wave 38
		} else if (game.getWave() == 38) {
			if (waveStart) {
				enemiesToSpawn = 20;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 10 == 0 || enemiesToSpawn == 1)
					spawnEnemy(game, new GiantRainbowSlime());
				else
					spawnEnemy(game, new RainbowSlime());
			}
			// Wave 39
		} else if (game.getWave() == 39) {
			if (waveStart) {
				enemiesToSpawn = 5;
				waveStart = false;
				spawnRate = 40;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantRainbowSlime());
			}
			// Wave 40
		} else if (game.getWave() == 40) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new RainbowSlime());
				if (enemiesToSpawn % 4 == 0)
					spawnEnemyIndependent(game, new GiantPinkSlime());
			}
			// Wave 41
		} else if (game.getWave() == 41) {
			if (waveStart) {
				enemiesToSpawn = 15;
				waveStart = false;
				spawnRate = 40;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 0)
					spawnEnemy(game, new GiantPinkSlime());
				else
					spawnEnemy(game, new GiantGraySlime());
				if (enemiesToSpawn % 3 == 0)
					spawnEnemyIndependent(game, new GiantRainbowSlime());
			}
			// Wave 42
		} else if (game.getWave() == 42) {
			if (waveStart) {
				enemiesToSpawn = 14;
				waveStart = false;
				spawnRate = 50;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 0)
					spawnEnemy(game, new GiantRainbowSlime());
				else
					spawnEnemy(game, new GiantRedSlime());
			}
			// Wave 43
		} else if (game.getWave() == 43) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new RainbowSlime());
				if (enemiesToSpawn % 2 == 0)
					spawnEnemyIndependent(game, new GiantPinkSlime());
			}
			// Wave 44
		} else if (game.getWave() == 44) {
			if (waveStart) {
				enemiesToSpawn = 100;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new RainbowSlime());
			}
			// Wave 45
		} else if (game.getWave() == 45) {
			if (waveStart) {
				enemiesToSpawn = 10;
				waveStart = false;
				spawnRate = 30;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantRainbowSlime());
			}
			// Wave 46
		} else if (game.getWave() == 46) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
				spawnRate = 10;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new RainbowSlime());
				if (enemiesToSpawn % 4 == 0)
					spawnEnemyIndependent(game, new GiantRainbowSlime());
			}
			// Wave 47
		} else if (game.getWave() == 47) {
			if (waveStart) {
				enemiesToSpawn = 30;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn % 2 == 0)
					spawnEnemy(game, new GiantRainbowSlime());
				else
					spawnEnemy(game, new GiantPinkSlime());
				if (enemiesToSpawn > 10) {
					spawnEnemyIndependent(game, new RainbowSlime());
				}
			}
			// Wave 48
		} else if (game.getWave() == 48) {
			if (waveStart) {
				enemiesToSpawn = 276;
				waveStart = false;
				spawnRate = 10;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn >= 267)
					spawnEnemy(game, new GreenSlime());
				else if (enemiesToSpawn == 266)
					spawnEnemy(game, new GiantGreenSlime());
				else if (enemiesToSpawn >= 256 && enemiesToSpawn <= 265)
					spawnEnemy(game, new BlueSlime());
				else if (enemiesToSpawn == 255)
					spawnEnemy(game, new GiantBlueSlime());
				else if (enemiesToSpawn >= 245 && enemiesToSpawn <= 254)
					spawnEnemy(game, new YellowSlime());
				else if (enemiesToSpawn == 244)
					spawnEnemy(game, new GiantYellowSlime());
				else if (enemiesToSpawn >= 234 && enemiesToSpawn <= 243)
					spawnEnemy(game, new RedSlime());
				else if (enemiesToSpawn == 233)
					spawnEnemy(game, new GiantRedSlime());
				else if (enemiesToSpawn >= 223 && enemiesToSpawn <= 232)
					spawnEnemy(game, new PinkSlime());
				else if (enemiesToSpawn == 222)
					spawnEnemy(game, new GiantPinkSlime());
				else if (enemiesToSpawn >= 212 && enemiesToSpawn <= 221)
					spawnEnemy(game, new GraySlime());
				else if (enemiesToSpawn == 211)
					spawnEnemy(game, new GiantGraySlime());
				else if (enemiesToSpawn <= 210 && enemiesToSpawn > 200)
					spawnEnemy(game, new GiantRainbowSlime());
				else
					spawnEnemy(game, new RainbowSlime());
			}
			// Wave 49
		} else if (game.getWave() == 49) {
			if (waveStart) {
				enemiesToSpawn = 40;
				waveStart = false;
				spawnRate = 15;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				spawnEnemy(game, new GiantRainbowSlime());
			}
			// Wave 50
			if (gameMenu.getDisplayedWave() == 50) newEnemy =  new KingSlime();
		} else if (game.getWave() == 50) {
			if (waveStart) {
				enemiesToSpawn = 31;
				waveStart = false;
				spawnRate = 10;
			}
			if (spawnTimer == 0 && enemiesToSpawn > 0) {
				if (enemiesToSpawn == 31) {
					spawnEnemy(game, new KingSlime());
				}
				if (game.getEnemies().get(0).getCurrentHealth() < game.getEnemies().get(0).getHealth() / 2) {
					spawnEnemy(game, new RainbowSlime());
					if (enemiesToSpawn % 2 == 0)
						spawnEnemyIndependent(game, new GiantRainbowSlime());
				}
			}
		}
		
		spawnTimer++;

		if (spawnTimer == spawnRate)
			spawnTimer = 0;

		// Start new level when no more enemies and start button pressed
		if (game.getEnemies().size() == 0 && enemiesToSpawn <= 0) {
			gameMenu.setWaveReady(true);
			if (gameMenu.isWaveStart()) {
				gameMenu.setWaveReady(false);
				gameMenu.setWaveStart(false);
				game.setWave(game.getWave() + 1);
				spawnTimer = 0;
				waveStart = true;
			}
		}
	}

	private void spawnEnemy(Game game, Enemy enemy) {
		enemy.spawnAtStart(game.getMap());
		game.getEnemies().add(enemy);
		game.addEntity(enemy);
		enemiesToSpawn--;
	}
	
	// Spawn independent of enemies to spawn
	private void spawnEnemyIndependent(Game game, Enemy enemy) {
		enemy.spawnAtStart(game.getMap());
		game.getEnemies().add(enemy);
		game.addEntity(enemy);
	}
	
	public Enemy getNewEnemy() {
		return newEnemy;
	}
}
