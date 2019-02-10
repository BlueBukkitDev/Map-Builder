package dev.blue.rotu.managers;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import dev.blue.rotu.Game;
import dev.blue.rotu.Main;
import dev.blue.rotu.ui.Button;
import dev.blue.rotu.ui.InputField;
import dev.blue.rotu.ui.TextArea;
import dev.blue.rotu.world.World;
import dev.blue.rotu.world.tiles.Tile;

public class MouseManager implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	public Point point = new Point(0, 0);
	//private Point lastPoint = point;
	private BufferedImage cursor = Main.getTextures().standardCursor;
	public Object cursorController = null;
	public boolean isInFrame = false;
	public Object clickedObject = null;
	public boolean painting = false;
	
	public void render(Graphics g) {
		if(cursorController == null && isInFrame) {
			cursor = Main.getTextures().standardCursor;
		}
		g.drawImage(cursor, (int)point.getX()-17, (int)point.getY()-15, 48, 48, null);
	}
	
	public void setCursor(BufferedImage image) {
		this.cursor = image;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		point = e.getPoint();
		if(clickedObject == null) {
			if(Game.getInstance().paint != null) {
				painting = true;
				paint();
			}
		}
		/*if(clickedObject == null && isInFrame) {//for moving the window. 
			Game.getInstance().getWindow().setLocation(new Point((int)(Game.getInstance().getWindow().getLocation().getX() + point.getX() - lastPoint.getX()), (int)(Game.getInstance().getWindow().getY() + point.getY() - lastPoint.getY())));
		}*/
	}
	
	public void paint() {
		int radius = Game.getInstance().getState().brushWidth;
		String[] parts = Game.getInstance().getState().getCamera().getTileArrayLocationAt(point).split(",");
		int x = Integer.parseInt(parts[0]);
		int y = Integer.parseInt(parts[1]);
		int maxX = World.getTiles().length;
		int maxY = World.getTiles()[0].length;
		if(x < maxX && y < maxY && x >= 0 && y >= 0) {
			byte ID = Game.getInstance().paint.getID();
			World.getTiles()[x][y] = ID;
			if(radius == 2) {
				if(x-1 >=0) {
					World.getTiles()[x-1][y] = ID;
				}
				if(y+1 < maxY) {
					World.getTiles()[x][y+1] = ID;
				}
				if(x+1 < maxX) {
					World.getTiles()[x+1][y] = ID;
				}
				if(y-1 >= 0) {
					World.getTiles()[x][y-1] = ID;
				}
			}else if(radius == 3) {
				if(x-1 >= 0 && y-1 >= 0) {
					World.getTiles()[x-1][y-1] = ID;
				}
				if(x-1 >= 0) {
					World.getTiles()[x-1][y] = ID;
				}
				if(x-1 >= 0 && y+1 < maxY) {
					World.getTiles()[x-1][y+1] = ID;
				}
				if(y+1 < maxY) {
					World.getTiles()[x][y+1] = ID;
				}
				if(x+1 < maxX && y+1 < maxY) {
					World.getTiles()[x+1][y+1] = ID;
				}
				if(x+1 < maxX) {
					World.getTiles()[x+1][y] = ID;
				}
				if(x+1 < maxX && y-1 >= 0) {
					World.getTiles()[x+1][y-1] = ID;
				}
				if(y-1 >= 0) {
					World.getTiles()[x][y-1] = ID;
				}
			}else if(radius == 4) {
				if(x-1 >= 0 && y-1 >= 0) {
					World.getTiles()[x-1][y-1] = ID;
				}
				if(x-1 >= 0) {
					World.getTiles()[x-1][y] = ID;
				}
				if(x-1 >= 0 && y+1 < maxY) {
					World.getTiles()[x-1][y+1] = ID;
				}
				if(y+1 < maxY) {
					World.getTiles()[x][y+1] = ID;
				}
				if(x+1 < maxX && y+1 < maxY) {
					World.getTiles()[x+1][y+1] = ID;
				}
				if(x+1 < maxX) {
					World.getTiles()[x+1][y] = ID;
				}
				if(x+1 < maxX && y-1 >= 0) {
					World.getTiles()[x+1][y-1] = ID;
				}
				if(y-1 >= 0) {
					World.getTiles()[x][y-1] = ID;
				}
				
				if(x-2 >= 0 && y-1 >= 0) {
					World.getTiles()[x-2][y-1] = ID;
				}
				if(x-2 >= 0) {
					World.getTiles()[x-2][y] = ID;
				}
				if(x-2 >= 0 && y+1 < maxY) {
					World.getTiles()[x-2][y+1] = ID;
				}
				
				if(x-1 >= 0 && y+2 < maxY) {
					World.getTiles()[x-1][y+2] = ID;
				}
				if(y+2 < maxY) {
					World.getTiles()[x][y+2] = ID;
				}
				if(x+1 < maxX && y+2 < maxY) {
					World.getTiles()[x+1][y+2] = ID;
				}
				
				if(x+2 < maxX && y+1 < maxY) {
					World.getTiles()[x+2][y+1] = ID;
				}
				if(x+2 < maxX) {
					World.getTiles()[x+2][y] = ID;
				}
				if(x+2 < maxX && y-1 >= 0) {
					World.getTiles()[x+2][y-1] = ID;
				}
				
				if(x+1 < maxX && y-2 >= 0) {
					World.getTiles()[x+1][y-2] = ID;
				}
				if(y-2 >= 0) {
					World.getTiles()[x][y-2] = ID;
				}
				if(x-1 >= 0 && y-2 >= 0) {
					World.getTiles()[x-1][y-2] = ID;
				}
			}
			Game.getInstance().getState().getCamera().getViewField().update(Game.getInstance().getState().getCamera().getFocus());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		point = e.getPoint();
		if(!Game.getInstance().getState().isBuilding) {//NPE sometimes on Initialization
			for(InputField each:Game.getInstance().getState().getFieldManager().getInputFields()) {
				each.onMouseMove(point);
			}
			for(TextArea each:Game.getInstance().getState().getTextAreaManager().getTextAreas()) {
				each.onMouseMove(point);
			}
			for(Button each:Game.getInstance().getState().getButtonManager().getButtons()) {
				each.onMouseMove(e.getPoint());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(InputField each:Game.getInstance().getState().getFieldManager().getInputFields()) {
			if(each.onClick(e.getButton(), e.getPoint())) {
				return;
			}
		}
		if(Tile.inspected != null) {
			Tile.inspected.setBeingInspected(false, null);
			Tile.inspected = null;
		}
		if(e.getButton() == MouseEvent.BUTTON3) {
			//Do something in drag, not here. Well, here too actually. Well maybe in buttonDown. 
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		isInFrame = true;
		cursor = Main.getTextures().standardCursor;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		isInFrame = false;
		painting = false;
		cursor = Main.getTextures().blankCursor;
		for(Button each:Game.getInstance().getState().getButtonManager().getButtons()) {
			each.stopHovering();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//lastPoint = e.getPoint();
		for(Button each:Game.getInstance().getState().getButtonManager().getButtons()) {
			if(each.onMouseDown(e.getButton(), e.getPoint())) {
				break;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		painting = false;
		for(Button each:Game.getInstance().getState().getButtonManager().getButtons()) {
			if(each.onMouseUp(e.getButton(), e.getPoint())) {
				break;
			}
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getPreciseWheelRotation() < 0) {
			//Tile.setWidth(Tile.getWidth()+3);
		}else if(e.getPreciseWheelRotation() > 0){
			//Tile.setWidth(Tile.getWidth()-3);
		}
	}
}
