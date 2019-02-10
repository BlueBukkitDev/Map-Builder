package dev.blue.rotu; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

import dev.blue.rotu.managers.KeyManager;
import dev.blue.rotu.managers.MouseManager;
import dev.blue.rotu.world.tiles.Tile;

public class Game implements Runnable {
	private Thread thread;
	private Window window;
	private boolean running = false;
	private static Game instance;
	private GameState state;
	private double panSpeed = 10;
	int renderCount = 0;
	private Graphics g;
	public static int frames = 0;
	public static int ticks = 0;
	public static String data = "";
	public static String data2 = "";
	public Tile paint;

	public Game() {
		thread = new Thread(this);
		state = new GameState(GameStateType.OPTIONS_STATE);//This before Window because Window contains the mouseManager and threw NPE's. 
		window = new Window();
		paint = null;
		
		instance = this;
	}

	public static Game getInstance() {
		return instance;
	}

	public void render() {
		BufferStrategy bs = window.canvas.getBufferStrategy();
		if (bs == null) {
			try {
				window.canvas.createBufferStrategy(2);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			return;
		}
		g = bs.getDrawGraphics();
		if (g instanceof Graphics2D) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		}

		state.render(g);
		window.getMouseManager().render(g);
		
		g.setFont(Main.getFonts().custom(16, Font.BOLD));
		g.setColor(new Color(0, 100, 200));
		g.drawString(data, 300, 20);

		g.dispose();
		bs.show();
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public void update() {
		state.update();
	}

	@Override
	public void run() {
		long now;
		int frames = 0;
		int ticks = 0;
		long lastUpdate = System.nanoTime();
		long lastFrame = System.nanoTime();
		long lastSecond = System.nanoTime();
		double passed = 0.0;
		double passed2 = 0.0;
		double passed3 = 0.0;
		double FPS = 60;
		double TPS = 120;
		while (running) {
			now = System.nanoTime();
			passed = (now - lastUpdate) / 1000000;
			passed2 = (now - lastFrame) / 1000000;
			passed3 = (now - lastSecond) / 1000000;
			if (passed >= 1000d / TPS) {
				passed = 0;
				update();
				lastUpdate = now;
				ticks++;
			}
			if (passed2 >= 1000d / FPS) {
				passed2 = 0;
				render();
				lastFrame = now;
				frames++;
			}
			if (passed3 >= 1000d / 1) {
				passed3 = 0;
				Game.frames = frames;
				Game.ticks = ticks;
				frames = 0;
				ticks = 0;
				lastSecond = now;
			}
		}
	}

	public synchronized void start() {
		running = true;
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public MouseManager getMouseManager() {
		return window.getMouseManager();
	}

	public KeyManager getKeyManager() {
		return window.getKeyManager();
	}

	public Window getWindow() {
		return window;
	}

	public double getPanSpeed() {
		return panSpeed;
	}

	public void setPanSpeed(double panSpeed) {
		this.panSpeed = panSpeed;
	}

	public Graphics getGraphics() {
		return g;
	}
}
