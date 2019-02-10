package dev.blue.rotu.world.tiles;

import dev.blue.rotu.Main;

public class ForestTile extends Tile {
	private static byte id = 4;

	public ForestTile() {
		super("Forest", false, 2, 1);
		texture = Main.getTextures().simpleForestTile;
	}
	
	public byte getID() {
		return id;
	}
	
	public static byte getSID() {
		return id;
	}
}
