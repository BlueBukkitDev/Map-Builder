package dev.blue.rotu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;

import dev.blue.rotu.gfx.Animation;
import dev.blue.rotu.gfx.Camera;
import dev.blue.rotu.gfx.utils.Proportions;
import dev.blue.rotu.managers.ButtonManager;
import dev.blue.rotu.managers.InputFieldManager;
import dev.blue.rotu.managers.TextAreaManager;
import dev.blue.rotu.managers.UIObjectManager;
import dev.blue.rotu.ui.Button;
import dev.blue.rotu.ui.NumberInputField;
import dev.blue.rotu.ui.Palette;
import dev.blue.rotu.ui.TextArea;
import dev.blue.rotu.ui.TextBit;
import dev.blue.rotu.ui.TextInputField;
import dev.blue.rotu.world.Location;
import dev.blue.rotu.world.World;

public class GameState {
	private boolean empty;
	private GameStateType type;
	private GameStateType bufferedType = null;
	private BufferedImage background;
	private InputFieldManager fieldManager = new InputFieldManager();
	private ButtonManager buttonManager = new ButtonManager();
	private TextAreaManager textAreaManager = new TextAreaManager();
	private UIObjectManager UIObjectManager = new UIObjectManager();
	private Camera camera = new Camera();
	public boolean isBuilding = false;
	public int worldWidth, worldHeight;
	public File map;
	public int brushWidth = 1;

	public GameState(GameStateType type) {
		changeGameState(type);
	}

	public void render(Graphics g) {// Time to build the gamestate to start truly rendering everything. :3=
		if (!empty) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, Window.width, Window.height);
			if (background != null) {
				g.drawImage(background, 0, 0, Window.width, Window.height, null);// Using HEIGHT instead of height was a problem
			}
			camera.render(g);
			fieldManager.render(g);
			textAreaManager.render(g);
			UIObjectManager.render(g);
			buttonManager.render(g);
			g.setColor(new Color(0, 120, 120));
			g.drawRect(0, 0, Window.width-1, Window.height-1);
			g.drawRect(1, 1, Window.width-3, Window.height-3);
			g.drawRect(2, 2, Window.width-5, Window.height-5);
		}
	}

	public void update() {
		if (bufferedType != null) {
			buildState(bufferedType);
			bufferedType = null;
			isBuilding = false;
			return;
		}
		if (!empty) {
			camera.update();
			fieldManager.update();
			buttonManager.update();
			textAreaManager.update();
			UIObjectManager.update();
		}
	}

	public GameStateType getType() {
		return type;
	}

	private void clearState() {
		empty = true;
		fieldManager.clear();
		buttonManager.clear();
		textAreaManager.clear();
		UIObjectManager.clear();
		background = null;
		camera.dispose();
	}

	public InputFieldManager getFieldManager() {
		return fieldManager;
	}

	public void setFieldManager(InputFieldManager manager) {
		this.fieldManager = manager;
	}

	public ButtonManager getButtonManager() {
		return buttonManager;
	}

	public void setButtonManager(ButtonManager buttonManager) {
		this.buttonManager = buttonManager;
	}

	public TextAreaManager getTextAreaManager() {
		return textAreaManager;
	}

	public void setTextAreaManager(TextAreaManager textAreaManager) {
		this.textAreaManager = textAreaManager;
	}
	
	public UIObjectManager getUIObjectManager() {
		return UIObjectManager;
	}
	
	public void setUIObjectManager(UIObjectManager UIObjectManager) {
		this.UIObjectManager = UIObjectManager;
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void buildState(GameStateType type) {
		clearState();
		this.type = type;
		if (type == GameStateType.GAME_STATE) {
			buildGameUI();
		}else if(type == GameStateType.OPTIONS_STATE) {
			buildOptionsUI();
		}
		empty = false;
	}
	
	private void buildOptionsUI() {
		Animation exitAnim = new Animation(10, new Location(Window.width-Proportions.CORNER_BUTTON_WIDTH-Window.tinySpace(), Window.tinySpace()), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, Main.getTextures().exitButton);
		Animation exitAnimHover = new Animation(10, exitAnim.getLocation(), exitAnim.getWidth(), exitAnim.getHeight(), Main.getTextures().exitButtonHover);
		Animation minimizeAnim = new Animation(10, new Location(Window.width-(Proportions.CORNER_BUTTON_WIDTH*2)-Window.smallSpace(), Window.tinySpace()), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, Main.getTextures().minimizeButton);
		Animation minimizeAnimHover = new Animation(10, minimizeAnim.getLocation(), minimizeAnim.getWidth(), minimizeAnim.getHeight(), Main.getTextures().minimizeButtonHover);
		Animation startAnim = new Animation(10, new Location(Window.width/2-60, Window.height/2-55), 120, 30, Main.getTextures().button);
		Animation startAnimHover = new Animation(10, startAnim.getLocation(), startAnim.getWidth(), startAnim.getHeight(), Main.getTextures().buttonDown);
		Animation editAnim = new Animation(10, new Location(Window.width/2-60, Window.height/2+5), 120, 30, Main.getTextures().button);
		Animation editAnimHover = new Animation(10, editAnim.getLocation(), editAnim.getWidth(), editAnim.getHeight(), Main.getTextures().buttonDown);
		
		Button exit = new Button("Exit", false, false, 12, Window.width-Proportions.CORNER_BUTTON_WIDTH-Window.tinySpace(), Window.tinySpace(), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, 0, exitAnim, exitAnim) {
			@Override
			public void runClick() {
				Game.getInstance().stop();
			}
			@Override
			public void runOnHover() {
				setWhileDownAnim(exitAnimHover);
				setWhileUpAnim(exitAnimHover);
			}
			@Override
			public void runOnStopHover() {
				setWhileDownAnim(exitAnim);
				setWhileUpAnim(exitAnim);
			}
		};
		
		Button minimize = new Button("Minimize", false, false, 12, (Window.width-Proportions.CORNER_BUTTON_WIDTH*2)-Window.tinySpace(), Window.tinySpace(), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, 0, minimizeAnim, minimizeAnim) {
			@Override
			public void runClick() {
				Game.getInstance().getWindow().setExtendedState(JFrame.ICONIFIED);
			}
			@Override
			public void runOnHover() {
				setWhileDownAnim(minimizeAnimHover);
				setWhileUpAnim(minimizeAnimHover);
			}
			@Override
			public void runOnStopHover() {
				setWhileDownAnim(minimizeAnim);
				setWhileUpAnim(minimizeAnim);
			}
		};
		
		NumberInputField widthField = new NumberInputField("Width", (int)startAnim.getX(), (int)startAnim.getY()-25, startAnim.getWidth(), 25, "Size", "200", true, false, 0, null, Main.getTextures().inputField);
		
		TextInputField fileField = new TextInputField("Map", (int)editAnim.getX(), (int)editAnim.getY()-25, editAnim.getWidth(), 25, "Map", "", true, false, 0, null, Main.getTextures().inputField);
		
		Button start = new Button("New", true, false, 18, (int)startAnim.getX(), (int)startAnim.getY(), startAnim.getWidth(), startAnim.getHeight(), 0, startAnim, startAnim) {
			@Override
			public void runClick() {
				int w = Integer.parseInt(widthField.getText());
				if(w > 90000) {
					w = 90000;
				}
				if(w < 200) {
					w = 200;
				}
				worldWidth = w;
				worldHeight = w;
				changeGameState(GameStateType.GAME_STATE);
			}
			@Override
			public void runOnHover() {
				setWhileDownAnim(startAnimHover);
				setWhileUpAnim(startAnimHover);
			}
			@Override
			public void runOnStopHover() {
				setWhileDownAnim(startAnim);
				setWhileUpAnim(startAnim);
			}
		};
		
		Button edit = new Button("Edit", true, false, 18, (int)editAnim.getX(), (int)editAnim.getY(), editAnim.getWidth(), editAnim.getHeight(), 0, editAnim, editAnim) {
			@Override
			public void runClick() {
				if(fileField.getText().length() >= 5) {
					File file = new File("C:\\Users\\Owner\\Desktop\\"+fileField.getText());
					if(file.exists()) {
						map = file;
						changeGameState(GameStateType.GAME_STATE);
					}else System.out.println("File does not exist!");
				}else System.out.println("Please enter a file name.");
			}
			@Override
			public void runOnHover() {
				setWhileDownAnim(editAnimHover);
				setWhileUpAnim(editAnimHover);
			}
			@Override
			public void runOnStopHover() {
				setWhileDownAnim(editAnim);
				setWhileUpAnim(editAnim);
			}
		};
		
		fieldManager.registerField(widthField);
		fieldManager.registerField(fileField);
		buttonManager.registerButton(start);
		buttonManager.registerButton(edit);
		buttonManager.registerButton(minimize);
		buttonManager.registerButton(exit);
	}
	
	private void buildGameUI() {
		camera.build(new World());//Doesn't matter where your point is. It places you in the top left corner. //Fixed
		background = null;
		
		Animation exitAnim = new Animation(10, new Location(Window.width-Proportions.CORNER_BUTTON_WIDTH-Window.tinySpace(), Window.tinySpace()), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, Main.getTextures().exitButton);
		Animation exitAnimHover = new Animation(10, new Location(Window.width-Proportions.CORNER_BUTTON_WIDTH-Window.tinySpace(), Window.tinySpace()), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, Main.getTextures().exitButtonHover);
		Animation minimizeAnim = new Animation(10, new Location(Window.width-(Proportions.CORNER_BUTTON_WIDTH*2)-Window.smallSpace(), Window.tinySpace()), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, Main.getTextures().minimizeButton);
		Animation minimizeAnimHover = new Animation(10, new Location(Window.width-(Proportions.CORNER_BUTTON_WIDTH*2)-Window.smallSpace(), Window.tinySpace()), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, Main.getTextures().minimizeButtonHover);
		
		Button exit = new Button("Exit", false, false, 12, Window.width-Proportions.CORNER_BUTTON_WIDTH-Window.tinySpace(), Window.tinySpace(), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, 0, exitAnim, exitAnim) {
			@Override
			public void runClick() {
				FileOutputStream stream;
				String path = "";
				try {
					System.out.println("Writing file...");
					if(map != null && map.exists()) {
						if(map.getName().endsWith(".bin")) {
							stream = new FileOutputStream(map.getAbsolutePath());
							path = map.getAbsolutePath();
						}else{
							path = System.getProperty("user.dir")+"\\map"+new Random().nextInt()+".bin";
							stream = new FileOutputStream(path);
						}
					}else{
						path = System.getProperty("user.dir")+"\\map"+new Random().nextInt()+".bin";
						stream = new FileOutputStream(path);
					}
					for(int i = 0; i < World.getTiles().length; i++) {
						stream.write(World.getTiles()[i]);
					}
					stream.flush();
					stream.close();
					System.out.println("Map saved to "+path);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				Game.getInstance().stop();
			}
			@Override
			public void runOnHover() {
				setWhileDownAnim(exitAnimHover);
				setWhileUpAnim(exitAnimHover);
			}
			@Override
			public void runOnStopHover() {
				setWhileDownAnim(exitAnim);
				setWhileUpAnim(exitAnim);
			}
		};
		Button minimize = new Button("Minimize", false, false, 12, (Window.width-Proportions.CORNER_BUTTON_WIDTH*2)-Window.tinySpace(), Window.tinySpace(), Proportions.CORNER_BUTTON_WIDTH, Proportions.CORNER_BUTTON_WIDTH, 0, minimizeAnim, minimizeAnim) {
			@Override
			public void runClick() {
				Game.getInstance().getWindow().setExtendedState(JFrame.ICONIFIED);
			}
			@Override
			public void runOnHover() {
				setWhileDownAnim(minimizeAnimHover);
				setWhileUpAnim(minimizeAnimHover);
			}
			@Override
			public void runOnStopHover() {
				setWhileDownAnim(minimizeAnim);
				setWhileUpAnim(minimizeAnim);
			}
		};
		
		TextArea fps = new TextArea("fps", Window.width-(Proportions.CORNER_BUTTON_WIDTH*4), Proportions.CORNER_BUTTON_WIDTH*2+Window.smallSpace(), (int)(Proportions.CORNER_BUTTON_WIDTH*1.5), Proportions.CORNER_BUTTON_WIDTH, 0, 0, false, Main.getTextures().blankCursor) {
			@Override
			public void runOnUpdate() {
				clear();
				addLine(new TextBit(Color.ORANGE, Main.getFonts().custom(16, Font.BOLD), "FPS: "+Game.frames, null, null));
			}
		};
		fps.addLine(new TextBit(Color.ORANGE, Main.getFonts().custom(16, Font.BOLD), "FPS: "+Game.frames, null, null));
		TextArea tps = new TextArea("tps", Window.width-(Proportions.CORNER_BUTTON_WIDTH*4), Proportions.CORNER_BUTTON_WIDTH*2+Window.smallSpace()+fps.getHeight(), (int)(Proportions.CORNER_BUTTON_WIDTH*1.5), Proportions.CORNER_BUTTON_WIDTH, 0, 0, false, Main.getTextures().blankCursor) {
			@Override
			public void runOnUpdate() {
				clear();
				addLine(new TextBit(Color.ORANGE, Main.getFonts().custom(16, Font.BOLD), "TPS: "+Game.ticks, null, null));
			}
		};
		tps.addLine(new TextBit(Color.ORANGE, Main.getFonts().custom(16, Font.BOLD), "TPS: "+Game.ticks, null, null));
		
		new Palette(buttonManager, textAreaManager);
		
		textAreaManager.registerField(fps);
		textAreaManager.registerField(tps);
		
		buttonManager.registerButton(minimize);
		buttonManager.registerButton(exit);
	}

	public void changeGameState(GameStateType type) {
		isBuilding = true;
		this.bufferedType = type;
	}
}