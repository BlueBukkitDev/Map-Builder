package dev.blue.rotu.world.tiles;

import java.awt.image.BufferedImage;

import dev.blue.rotu.Window;
import dev.blue.rotu.world.Location;

public abstract class Tile {
	private static int width = Window.space();
	private String name;
	private boolean isBeingInspected;
	private boolean collides;
	protected BufferedImage texture;
	public static Tile inspected = null;
	public static Location inspectionLocation = null;
	protected int dominance;
	public int height;
	
	public Tile(String name, boolean collides, int dominance, int height) {
		this.collides = collides;
		this.name = name;
		this.dominance = dominance;
		this.height = height;
	}
	
	public String getName() {
		return name;
	}
	
	public int getDominance() {
		return dominance;
	}
	
	public static Tile getTile(int ID) {
		switch(ID) {
		case 0:return new GrassTile();
		case 1:return new DirtTile();
		case 2:return new SandTile();
		case 3:return new StoneTile();
		case 4:return new ForestTile();
		case 5:return new WaterTile();
		default:return new GrassTile();
		}
	}
	
	public abstract byte getID();
	
	public BufferedImage getTexture() {
		return texture;
	}
	
	public boolean getCollides() {
		return collides;
	}
	
	public static int getWidth() {
		return width;
	}
	
	public static void setWidth(int Width) {
		width = Width;
	}

	public boolean isBeingInspected() {
		return isBeingInspected;
	}

	public void setBeingInspected(boolean isBeingInspected, Location location) {
		this.isBeingInspected = isBeingInspected;
		if(isBeingInspected == false) {
			Tile.inspectionLocation = null;
			Tile.inspected = null;
		}else {
			Tile.inspectionLocation = location;
			Tile.inspected = this;
		}
	}
}
