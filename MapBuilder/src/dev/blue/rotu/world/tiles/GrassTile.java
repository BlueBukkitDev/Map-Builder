package dev.blue.rotu.world.tiles;

import dev.blue.rotu.Main;

public class GrassTile extends Tile {
	private static byte id = 0;

	public GrassTile() {
		super("Grass", false, 2, 4);
		texture = Main.getTextures().simpleGrassTile;
	}
	
	public byte getID() {
		return id;
	}
	
	public static byte getSID() {
		return id;
	}
}
