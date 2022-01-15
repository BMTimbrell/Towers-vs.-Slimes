package com.game.enemies;

import com.game.Game;

public class YellowSlime extends BlueSlime {

	public YellowSlime() {
		name = "Yellow Slime";
		coinsAwarded = 6;
		speed = 2;
		baseSpeed = speed;
		livesLost = 3;
		image = Game.readImage("yellow_slime");
	}

}
