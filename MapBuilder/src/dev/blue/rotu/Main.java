package dev.blue.rotu;

import dev.blue.rotu.gfx.utils.Fonts;
import dev.blue.rotu.gfx.utils.Textures;

public class Main {
	public static Window window;
	public static Game game;
	public static final String VERSION = "0.0.03";
	private static Textures textures;
	private static Fonts fonts;

	public static void main(String[] args) {
		System.out.println("Starting Map Builder!");
		textures = new Textures();
		textures.loadTextures();
		fonts = new Fonts();
		fonts.load();
		game = new Game();
		game.start();
		window = game.getWindow();
	}
	
	public static Textures getTextures() {
		return textures;
	}
	
	public static Fonts getFonts() {
		return fonts;
	}
}
