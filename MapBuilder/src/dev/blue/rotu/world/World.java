package dev.blue.rotu.world;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import dev.blue.rotu.Game;
import dev.blue.rotu.world.tiles.Tile;

public class World {

	private int pixelWidth, pixelHeight;
	private static byte[][] tiles;

	//int i = 356_343;//????????
	//BigDecimal dec;//????????
	
	public World() {
		loadTiles();
	}

	private void loadTiles() {
		readLines();
		System.out.println("Generated the world!");
		pixelWidth = tiles.length * Tile.getWidth();
		pixelHeight = tiles[0].length * Tile.getWidth();
	}

	public static byte[][] getTiles() {
		return tiles;
	}

	private void readLines() {
		long time = System.nanoTime();
		File file = Game.getInstance().getState().map;
		if(file == null || !file.getName().endsWith(".bin")) {
			tiles = new byte[Game.getInstance().getState().worldHeight][Game.getInstance().getState().worldWidth];
			int lastPercent = 0;
			System.out.println("Loading tiles: 0%");
			
			for(int i = 0; i < Game.getInstance().getState().worldHeight; i++) {
				int percentage = (int)((double)i/(double)Game.getInstance().getState().worldHeight*100d);
				if(percentage > lastPercent+5) {
					System.out.println("Loading tiles: "+percentage+"%");
					lastPercent = percentage;
				}
				for(int j = 0; j < Game.getInstance().getState().worldWidth; j++) {
					tiles[i][j] = 0;
				}
			}
			
			System.out.println("Loading tiles: 100% ("+((System.nanoTime()-time)/1000000000d)+")");
		}else {
			if(file.exists()) {
				try {
					int width = (int)Math.sqrt((double)file.length());
					tiles = new byte[width][width];
					byte[] all = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
					for(int i = 0; i < width; i++) {
						tiles[i] = Arrays.copyOfRange(all, i*width, (i*width)+width);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getPixelWidth() {
		return pixelWidth;
	}

	public int getPixelHeight() {
		return pixelHeight;
	}
}