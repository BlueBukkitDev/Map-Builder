package dev.blue.rotu.world.tiles;

import dev.blue.rotu.Main;

public class WaterTile extends Tile {
	private static byte id = 5;

	public WaterTile() {
		super("Water", false, 1, 1);
		texture = Main.getTextures().simpleWaterTile;
	}
	
	public byte getID() {
		return id;
	}
	
	public static byte getSID() {
		return id;
	}
}
