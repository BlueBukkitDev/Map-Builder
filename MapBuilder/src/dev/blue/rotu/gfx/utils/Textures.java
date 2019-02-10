package dev.blue.rotu.gfx.utils;

import java.awt.image.BufferedImage;

public class Textures {
	public BufferedImage logo;
	public BufferedImage menubg;
	public BufferedImage inputField;
	public BufferedImage button;
	public BufferedImage buttonDown;
	public BufferedImage addButton;
	public BufferedImage addButtonDown;
	public BufferedImage subButton;
	public BufferedImage subButtonDown;
	
	public BufferedImage exitButton;
	public BufferedImage exitButtonHover;
	public BufferedImage minimizeButton;
	public BufferedImage minimizeButtonHover;
	public BufferedImage settingsButton;
	public BufferedImage settingsButtonHover;
	public BufferedImage profileButton;
	public BufferedImage profileButtonHover;
	
	public BufferedImage standardCursor;
	public BufferedImage blankCursor;
	public BufferedImage penCursor;
	public BufferedImage typeCursor;
	
	public BufferedImage textArea;
	public BufferedImage textArea2;
	public BufferedImage palette;
	
	//////////////////////////////////////
	
	public BufferedImage tileGlow;
	
	public BufferedImage grassTile;
	public BufferedImage dirtTile;
	public BufferedImage sandTile;
	public BufferedImage stoneTile;
	public BufferedImage forestTile;
	public BufferedImage waterTile;
	public BufferedImage simpleGrassTile;
	public BufferedImage simpleDirtTile;
	public BufferedImage simpleSandTile;
	public BufferedImage simpleStoneTile;
	public BufferedImage simpleForestTile;
	public BufferedImage simpleWaterTile;
	public BufferedImage grassSelectedTile;
	public BufferedImage dirtSelectedTile;
	public BufferedImage sandSelectedTile;
	public BufferedImage stoneSelectedTile;
	public BufferedImage forestSelectedTile;
	public BufferedImage waterSelectedTile;
	
	public void loadTextures() {
		logo = ImageLoader.loadImage("Logo2.png");//"world/maps/"+name+".png"
		menubg = ImageLoader.loadImage("menu/menuBG.png");
		inputField = ImageLoader.loadImage("ui/TextInputField.png");
		
		exitButton = ImageLoader.loadImage("ui/cornerButtons/exitButton.png");
		exitButtonHover = ImageLoader.loadImage("ui/cornerButtons/exitButtonHover.png");
		minimizeButton = ImageLoader.loadImage("ui/cornerButtons/minimizeButton.png");
		minimizeButtonHover = ImageLoader.loadImage("ui/cornerButtons/minimizeButtonHover.png");
		settingsButton = ImageLoader.loadImage("ui/cornerButtons/settingsButton.png");
		settingsButtonHover = ImageLoader.loadImage("ui/cornerButtons/settingsButtonHover.png");
		profileButton = ImageLoader.loadImage("ui/cornerButtons/profileButton.png");
		profileButtonHover = ImageLoader.loadImage("ui/cornerButtons/profileButtonHover.png");
		
		button = ImageLoader.loadImage("ui/button.png");
		buttonDown = ImageLoader.loadImage("ui/buttonDown.png");
		addButton = ImageLoader.loadImage("ui/addButton.png");
		addButtonDown = ImageLoader.loadImage("ui/addButtonHover.png");
		subButton = ImageLoader.loadImage("ui/subtractButton.png");
		subButtonDown = ImageLoader.loadImage("ui/subtractButtonHover.png");
		
		standardCursor = ImageLoader.loadImage("default_cursor.png");
		blankCursor = ImageLoader.loadImage("blank_cursor.png");
		penCursor = ImageLoader.loadImage("pen_cursor.png");
		typeCursor = ImageLoader.loadImage("type_cursor.png");
		
		textArea = ImageLoader.loadImage("menu/textAreaBG.png");
		textArea2 = ImageLoader.loadImage("menu/textAreaBG2.png");
		palette = ImageLoader.loadImage("menu/palette.png");
		
		tileGlow = ImageLoader.loadImage("tiles/tileGlow.png");
		
		grassTile = ImageLoader.loadImage("tiles/grass/grass.png");
		dirtTile = ImageLoader.loadImage("tiles/dirt/dirt.png");
		sandTile = ImageLoader.loadImage("tiles/sand/sand.png");
		stoneTile = ImageLoader.loadImage("tiles/stone/stone.png");
		forestTile = ImageLoader.loadImage("tiles/forest/forest.png");
		waterTile = ImageLoader.loadImage("tiles/water/water.png");
		simpleGrassTile = ImageLoader.loadImage("tiles/grass/grassTile.png");
		simpleDirtTile = ImageLoader.loadImage("tiles/dirt/dirtTile.png");
		simpleSandTile = ImageLoader.loadImage("tiles/sand/sandTile.png");
		simpleStoneTile = ImageLoader.loadImage("tiles/stone/stoneTile.png");
		simpleForestTile = ImageLoader.loadImage("tiles/forest/forestTile.png");
		simpleWaterTile = ImageLoader.loadImage("tiles/water/waterTile.png");
		grassSelectedTile = ImageLoader.loadImage("tiles/grass/grassSelected.png");
		dirtSelectedTile = ImageLoader.loadImage("tiles/dirt/dirtSelected.png");
		sandSelectedTile = ImageLoader.loadImage("tiles/sand/sandSelected.png");
		stoneSelectedTile = ImageLoader.loadImage("tiles/stone/stoneSelected.png");
		forestSelectedTile = ImageLoader.loadImage("tiles/forest/forestSelected.png");
		waterSelectedTile = ImageLoader.loadImage("tiles/water/waterSelected.png");
	}
}
