package dev.blue.rotu.world.tiles;

import dev.blue.rotu.Main;

public class DirtTile extends Tile{
	private static byte id = 1;

	public DirtTile() {
		super("Dirt", false, 1, 1);
		texture = Main.getTextures().simpleDirtTile;
	}
	
	public byte getID() {
		return id;
	}
	
	public static byte getSID() {
		return id;
	}
}
