package com.game;

import java.awt.*;

public class Commons {
	
	// Map stuff
	public final static Dimension RESOLUTION = new Dimension(800, 700);
	public final static int BLOCK_SIZE = 52;
	public final static Vector2i BLOCK_DIMENSION = new Vector2i(BLOCK_SIZE, BLOCK_SIZE);
	public final static int MAP_WIDTH = 14;
	public final static int MAP_HEIGHT = 9;
	public final static int MAP_BOTTOM_MARGIN = 25;
	public final static int GRASS_ID = 0;
	public final static int PATH_ID = 1;
	public final static float SEGMENT_LENGTH = BLOCK_SIZE;
	public final static int MAP_START_POS = ((int) RESOLUTION.getWidth() / 2) - ((MAP_WIDTH * BLOCK_SIZE) / 2);
	public final static int MAP_END_POS = (int) RESOLUTION.getWidth() - MAP_START_POS;
	public final static int MAP_BOTTOM_POS = Commons.MAP_HEIGHT * Commons.BLOCK_SIZE;
	public final static int MAP_TOP_POS = 0;
	public final static Vector2i MAP_ORIGIN = new Vector2i(Commons.MAP_START_POS, Commons.MAP_TOP_POS);
	
	// Button stuff
	public final static int BUTTON_WIDTH = 60;
	public final static int BUTTON_HEIGHT = 50;
	public final static int TOWER_ICON_WIDTH = 20;
	public final static int TOWER_ICON_HEIGHT = 34;
	public final static int CELL_SPACE = 20;
	public final static int PBUTTON_WIDTH = 50;
	public final static int PBUTTON_HEIGHT = 20;
	public final static int UBUTTON_HEIGHT = 20;
	public final static int UBUTTON_WIDTH = 80;
	public final static int BUTTON_BORDER = 5;
	public final static int BUTTON_BOTTOM_MARGIN = 20;
	
	// Stat stuff
	public final static int STAT_ICON_SIZE = 32;
	public final static int WAVE_MARGIN_BOTTOM = 40;
	public final static int STAT_SPACE_LEFT = 10;
	public final static int STAT_ICON_SPACE = 10;
	
	// Info box stuff
	public final static int TEXT_SPACING = 18;
	public final static int BOX_PADDING = 10;
	public final static int HEADING_BOTTOM_MARGIN = 30;
	public final static int ENEMY_INFO_SPACING = 20;
	
	// Enemy stuff
	public final static int HEALTH_BAR_BOTTOM_MARGIN = 5;
	public final static int HEALTH_BAR_TOP_MARGIN = 1;
	public final static int HEALTH_BAR_HEIGHT = 5;
	public final static int STATUS_ICON_SPACING = 3;
	public static final int AURA_RANGE = 120;
	public static final int SMALL_ENEMY_WIDTH = 42;
	public static final int SMALL_ENEMY_HEIGHT = 30;
}
