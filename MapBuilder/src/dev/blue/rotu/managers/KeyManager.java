package dev.blue.rotu.managers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dev.blue.rotu.Game;
import dev.blue.rotu.GameStateType;
import dev.blue.rotu.ui.InputField;

public class KeyManager implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {
		/*if(e.getKeyCode() == KeyEvent.VK_G) {
			Game.getInstance().getState().changeGameState(GameStateType.GAME_STATE);
		}else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			Game.getInstance().getState().changeGameState(GameStateType.MAIN_MENU_STATE);
		}*/
		for(InputField each:Game.getInstance().getState().getFieldManager().getInputFields()) {
			if(each.isSelected()) {
				each.onKeyPressed(e);
			}
		}
		if(Game.getInstance().getState().getType() == GameStateType.GAME_STATE) {
			//m for minimap
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		for(InputField each:Game.getInstance().getState().getFieldManager().getInputFields()) {
			if(each.isSelected()) {
				each.onType(e);
			}
		}
	}
}
