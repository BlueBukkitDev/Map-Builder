package dev.blue.rotu.ui;

import dev.blue.rotu.Game;
import dev.blue.rotu.Main;
import dev.blue.rotu.Window;
import dev.blue.rotu.gfx.Animation;
import dev.blue.rotu.managers.ButtonManager;
import dev.blue.rotu.managers.TextAreaManager;
import dev.blue.rotu.world.Location;
import dev.blue.rotu.world.tiles.DirtTile;
import dev.blue.rotu.world.tiles.ForestTile;
import dev.blue.rotu.world.tiles.GrassTile;
import dev.blue.rotu.world.tiles.SandTile;
import dev.blue.rotu.world.tiles.StoneTile;
import dev.blue.rotu.world.tiles.Tile;
import dev.blue.rotu.world.tiles.WaterTile;

public class Palette {
	
	public Palette(ButtonManager buttonManager, TextAreaManager textAreaManager) {
		int tileWidth = (int) (Tile.getWidth()*1.5);
		int gap = tileWidth+Window.tinySpace();
		Animation brushUp = new Animation(0, new Location(Window.width/2-(tileWidth*4)-Window.tinySpace()*3, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().addButton);
		Animation brushUpSelect = new Animation(0, brushUp.getLocation(), tileWidth, tileWidth, Main.getTextures().addButtonDown);
		
		Animation grassUp = new Animation(0, new Location(Window.width/2-(tileWidth*3)-Window.tinySpace()*2, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().grassTile);
		Animation grassDown = new Animation(0, grassUp.getLocation(), grassUp.getWidth(), grassUp.getHeight(), Main.getTextures().grassSelectedTile);
		Animation dirtUp = new Animation(0, new Location(grassUp.getX()+gap, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().dirtTile);
		Animation dirtDown = new Animation(0, dirtUp.getLocation(), dirtUp.getWidth(), dirtUp.getHeight(), Main.getTextures().dirtSelectedTile);
		Animation sandUp = new Animation(0, new Location(dirtUp.getX()+gap, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().sandTile);
		Animation sandDown = new Animation(0, sandUp.getLocation(), sandUp.getWidth(), sandUp.getHeight(), Main.getTextures().sandSelectedTile);
		Animation stoneUp = new Animation(0, new Location(sandUp.getX()+gap, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().stoneTile);
		Animation stoneDown = new Animation(0, stoneUp.getLocation(), stoneUp.getWidth(), stoneUp.getHeight(), Main.getTextures().stoneSelectedTile);
		Animation forestUp = new Animation(0, new Location(stoneUp.getX()+gap, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().forestTile);
		Animation forestDown = new Animation(0, forestUp.getLocation(), forestUp.getWidth(), forestUp.getHeight(), Main.getTextures().forestSelectedTile);
		Animation waterUp = new Animation(0, new Location(forestUp.getX()+gap, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().waterTile);
		Animation waterDown = new Animation(0, waterUp.getLocation(), waterUp.getWidth(), waterUp.getHeight(), Main.getTextures().waterSelectedTile);
		
		Animation brushDown = new Animation(0, new Location(waterUp.getX()+gap, Window.tinySpace()*2), tileWidth, tileWidth, Main.getTextures().subButton);
		Animation brushDownSelect = new Animation(0, brushDown.getLocation(), tileWidth, tileWidth, Main.getTextures().subButtonDown);
		
		Button brushUpButton = new Button("Brush Up", false, false, 14, (int)brushUp.getX(), (int)brushUp.getY(), brushUp.getWidth(), brushUp.getHeight(), 0, brushUpSelect, brushUp) {
			@Override
			public void runClick() {
				if(Game.getInstance().getState().brushWidth < 4) {
					Game.getInstance().getState().brushWidth++;
				}
			}
		};
		
		Button brushDownButton = new Button("Brush Down", false, false, 14, (int)brushDown.getX(), (int)brushDown.getY(), brushDown.getWidth(), brushDown.getHeight(), 0, brushDownSelect, brushDown) {
			@Override
			public void runClick() {
				if(Game.getInstance().getState().brushWidth > 1) {
					Game.getInstance().getState().brushWidth--;
				}
			}
		};
		
		Button grass = new Button("Grass", true, false, 14, (int)grassUp.getX(), (int)grassUp.getY(), grassUp.getWidth(), grassUp.getHeight(), 0, grassUp, grassUp) {
			boolean selected = false;
			@Override
			public void runClick() {
				if(!selected) {
					Game.getInstance().paint = new GrassTile();
					selected = true;
					setWhileDownAnim(grassDown);
					setWhileUpAnim(grassDown);
				}else {
					Game.getInstance().paint = null;
					selected = false;
					setWhileDownAnim(grassUp);
					setWhileUpAnim(grassUp);
				}
			}
			@Override
			public void runOnUpdate() {
				if(Game.getInstance().paint == null || Game.getInstance().paint.getID() != GrassTile.getSID()) {
					if(selected) {
						selected = false;
						setWhileDownAnim(grassUp);
						setWhileUpAnim(grassUp);
					}
				}
			}
		};
		
		Button dirt = new Button("Dirt", true, false, 14, (int)dirtUp.getX(), (int)dirtUp.getY(), dirtUp.getWidth(), dirtUp.getHeight(), 0, dirtUp, dirtUp) {
			boolean selected = false;
			@Override
			public void runClick() {
				Game.getInstance().paint = new DirtTile();
				if(!selected) {
					selected = true;
					setWhileDownAnim(dirtDown);
					setWhileUpAnim(dirtDown);
				}else {
					Game.getInstance().paint = null;
					selected = false;
					setWhileDownAnim(dirtUp);
					setWhileUpAnim(dirtUp);
				}
			}
			@Override
			public void runOnUpdate() {
				if(Game.getInstance().paint == null || Game.getInstance().paint.getID() != DirtTile.getSID()) {
					if(selected) {
						selected = false;
						setWhileDownAnim(dirtUp);
						setWhileUpAnim(dirtUp);
					}
				}
			}
		};
		
		Button sand = new Button("Sand", true, false, 14, (int)sandUp.getX(), (int)sandUp.getY(), sandUp.getWidth(), sandUp.getHeight(), 0, sandUp, sandUp) {
			boolean selected = false;
			@Override
			public void runClick() {
				Game.getInstance().paint = new SandTile();
				if(!selected) {
					selected = true;
					setWhileDownAnim(sandDown);
					setWhileUpAnim(sandDown);
				}else {
					Game.getInstance().paint = null;
					selected = false;
					setWhileDownAnim(sandUp);
					setWhileUpAnim(sandUp);
				}
			}
			@Override
			public void runOnUpdate() {
				if(Game.getInstance().paint == null || Game.getInstance().paint.getID() != SandTile.getSID()) {
					if(selected) {
						selected = false;
						setWhileDownAnim(sandUp);
						setWhileUpAnim(sandUp);
					}
				}
			}
		};
		
		Button stone = new Button("Stone", true, false, 14, (int)stoneUp.getX(), (int)stoneUp.getY(), stoneUp.getWidth(), stoneUp.getHeight(), 0, stoneUp, stoneUp) {
			boolean selected = false;
			@Override
			public void runClick() {
				Game.getInstance().paint = new StoneTile();
				if(!selected) {
					selected = true;
					setWhileDownAnim(stoneDown);
					setWhileUpAnim(stoneDown);
				}else {
					Game.getInstance().paint = null;
					selected = false;
					setWhileDownAnim(stoneUp);
					setWhileUpAnim(stoneUp);
				}
			}
			@Override
			public void runOnUpdate() {
				if(Game.getInstance().paint == null || Game.getInstance().paint.getID() != StoneTile.getSID()) {
					if(selected) {
						selected = false;
						setWhileDownAnim(stoneUp);
						setWhileUpAnim(stoneUp);
					}
				}
			}
		};
		
		Button forest = new Button("Forest", true, false, 14, (int)forestUp.getX(), (int)forestUp.getY(), forestUp.getWidth(), forestUp.getHeight(), 0, forestUp, forestUp) {
			boolean selected = false;
			@Override
			public void runClick() {
				Game.getInstance().paint = new ForestTile();
				if(!selected) {
					selected = true;
					setWhileDownAnim(forestDown);
					setWhileUpAnim(forestDown);
				}else {
					Game.getInstance().paint = null;
					selected = false;
					setWhileDownAnim(forestUp);
					setWhileUpAnim(forestUp);
				}
			}
			@Override
			public void runOnUpdate() {
				if(Game.getInstance().paint == null || Game.getInstance().paint.getID() != ForestTile.getSID()) {
					if(selected) {
						selected = false;
						setWhileDownAnim(forestUp);
						setWhileUpAnim(forestUp);
					}
				}
			}
		};
		
		Button water = new Button("Water", true, false, 14, (int)waterUp.getX(), (int)waterUp.getY(), waterUp.getWidth(), waterUp.getHeight(), 0, waterUp, waterUp) {
			boolean selected = false;
			@Override
			public void runClick() {
				Game.getInstance().paint = new WaterTile();
				if(!selected) {
					selected = true;
					setWhileDownAnim(waterDown);
					setWhileUpAnim(waterDown);
				}else {
					Game.getInstance().paint = null;
					selected = false;
					setWhileDownAnim(waterUp);
					setWhileUpAnim(waterUp);
				}
			}
			@Override
			public void runOnUpdate() {
				if(Game.getInstance().paint == null || Game.getInstance().paint.getID() != WaterTile.getSID()) {
					if(selected) {
						selected = false;
						setWhileDownAnim(waterUp);
						setWhileUpAnim(waterUp);
					}
				}
			}
		};
		
		TextArea bg = new TextArea("background", Window.width/2-gap*3, Window.tinySpace(), gap*6+Window.tinySpace(), tileWidth+Window.tinySpace()*2, 0, 0, false, Main.getTextures().palette);
		
		buttonManager.registerButton(brushUpButton);
		buttonManager.registerButton(brushDownButton);
		buttonManager.registerButton(grass);
		buttonManager.registerButton(dirt);
		buttonManager.registerButton(sand);
		buttonManager.registerButton(stone);
		buttonManager.registerButton(forest);
		buttonManager.registerButton(water);
		textAreaManager.registerField(bg);
	}
}
