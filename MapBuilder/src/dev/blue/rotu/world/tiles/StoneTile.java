package dev.blue.rotu.world.tiles;

import dev.blue.rotu.Main;

public class StoneTile extends Tile {
	private static byte id = 3;

	public StoneTile() {
		super("Stone", false, 1, 1);
		texture = Main.getTextures().simpleStoneTile;
	}
	
	public byte getID() {
		return id;
	}
	
	public static byte getSID() {
		return id;
	}
}
