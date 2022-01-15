package com.game.gui;

import java.awt.*;

import com.game.Commons;
import com.game.Vector2f;

public final class Map {

	private Block[][] blocks;
	private Line[] segments;
	private TileType[][] map, map1, map2, map3;
	private Vector2f enemyStartPos;
	private int mapID;

	public Map(int mapID) {
		this.mapID = mapID;
		init();
	}

	public void init() {

		// Map 1
		map1 = new TileType[][] {
				{ TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH, },
				{ TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH },
				{ TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, },
				{ TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH },
				{ TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH } };

		// Map 2

		map2 = new TileType[][] {
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.GRASS, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH, },
				{ TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.GRASS,
						TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.PATH, },
				{ TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.PATH, },
				{ TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH, TileType.GRASS,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.GRASS } };

		// Map 3

		map3 = new TileType[][] {
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH,
						TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.GRASS,
						TileType.GRASS, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS,
						TileType.PATH, TileType.PATH, },
				{ TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH, TileType.GRASS,
						TileType.PATH, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.PATH, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.PATH, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.PATH, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.GRASS, TileType.GRASS, TileType.PATH, TileType.PATH, TileType.PATH, TileType.PATH,
						TileType.PATH, TileType.GRASS, },
				{ TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS, TileType.GRASS,
						TileType.GRASS, TileType.GRASS } };

		blocks = new Block[Commons.MAP_HEIGHT][Commons.MAP_WIDTH];

		if (mapID == 0) {
			map = map1;
		} else if (mapID == 1) {
			map = map2;
		} else if (mapID == 2) {
			map = map3;
		} else {
			map = map1;
		}

		for (int y = 0; y < blocks.length; y++) {
			for (int x = 0; x < blocks[0].length; x++) {
				blocks[y][x] = new Block((x * Commons.BLOCK_SIZE) + Commons.MAP_START_POS, y * Commons.BLOCK_SIZE,
						Commons.BLOCK_SIZE, Commons.BLOCK_SIZE, map[y][x]);
			}
		}

		// Enemy path

		// Map 1
		if (map == map1) {
			enemyStartPos = new Vector2f(Commons.MAP_START_POS - Commons.BLOCK_SIZE, Commons.BLOCK_SIZE * 4);

			segments = new Line[] { new Line(enemyStartPos, enemyStartPos.addX(Commons.SEGMENT_LENGTH)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 3)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 3),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 4)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 4),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 5)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 5),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 6)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 6),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 4)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 4),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 10)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 10),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 9)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 9),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 8)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 8),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 12, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 12, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 14)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 14),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * 3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * 3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 12, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 12, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 3, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 3, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * 3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * 3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -5))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -5)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -6))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -6)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -7))) };
			// Map 2
		} else if (map == map2) {
			enemyStartPos = Commons.MAP_ORIGIN.toVec2f()
					.add(new Vector2f(Commons.BLOCK_SIZE * 2, Commons.MAP_HEIGHT * Commons.BLOCK_SIZE));

			segments = new Line[] { new Line(enemyStartPos, enemyStartPos.subY(Commons.SEGMENT_LENGTH)),
					new Line(enemyStartPos.subY(Commons.SEGMENT_LENGTH),
							enemyStartPos.subY(Commons.SEGMENT_LENGTH * 2)),
					new Line(enemyStartPos.subY(Commons.SEGMENT_LENGTH * 2),
							enemyStartPos.subY(Commons.SEGMENT_LENGTH * 3)),
					new Line(enemyStartPos.subY(Commons.SEGMENT_LENGTH * 3),
							enemyStartPos.subY(Commons.SEGMENT_LENGTH * 4)),
					new Line(enemyStartPos.subY(Commons.SEGMENT_LENGTH * 4),
							enemyStartPos.subY(Commons.SEGMENT_LENGTH * 5)),
					new Line(enemyStartPos.subY(Commons.SEGMENT_LENGTH * 5),
							enemyStartPos.subY(Commons.SEGMENT_LENGTH * 6)),
					new Line(enemyStartPos.subY(Commons.SEGMENT_LENGTH * 6),
							enemyStartPos.subY(Commons.SEGMENT_LENGTH * 7)),
					new Line(enemyStartPos.subY(Commons.SEGMENT_LENGTH * 7),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -6))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -6)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -5))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -5)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 3, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 3, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -5))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -5)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -6))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -6)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -8))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -8)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -9))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -9)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -9))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -9)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -9))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -9)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -9))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -9)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -9))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -9)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -8))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -8)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -6))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -6)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -5))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -5)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -5))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -5)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -5))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -5)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -6))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -6)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -7))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -7)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -6))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -6)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -5))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -5)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 3, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 3, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 2, Commons.SEGMENT_LENGTH * 2))) };
			// Map 3
		} else if (map == map3) {
			enemyStartPos = new Vector2f(Commons.MAP_START_POS - Commons.BLOCK_SIZE, Commons.BLOCK_SIZE * 3);

			segments = new Line[] { new Line(enemyStartPos, enemyStartPos.addX(Commons.SEGMENT_LENGTH)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 2),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 3)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 3),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 4)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 4),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 4, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 5, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 6)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 6),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 6, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 7, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 8, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 11)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 11),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 10)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 10),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 9)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 9),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 9, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 10, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 11, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 12, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 12, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 4))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 4)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 3))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 3)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 2))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * 2)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH)),
							enemyStartPos.addX(Commons.SEGMENT_LENGTH * 13)),
					new Line(enemyStartPos.addX(Commons.SEGMENT_LENGTH * 13),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 13, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 14, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 15, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 15, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 16, Commons.SEGMENT_LENGTH * -1))),
					new Line(enemyStartPos.add(new Vector2f(Commons.SEGMENT_LENGTH * 16, Commons.SEGMENT_LENGTH * -1)),
							enemyStartPos
									.add(new Vector2f(Commons.SEGMENT_LENGTH * 17, Commons.SEGMENT_LENGTH * -1))) };
		}
	}

	public Line[] getSegments() {
		return segments;
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public void render(Graphics g) {
		for (int y = 0; y < blocks.length; y++) {
			for (int x = 0; x < blocks[0].length; x++) {
				blocks[y][x].render(g);
			}
		}
	}

	public Vector2f getStartPos() {
		return enemyStartPos;
	}
}