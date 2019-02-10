package dev.blue.rotu.managers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import dev.blue.rotu.ui.UIObject;

public class UIObjectManager {
	private List<UIObject> fields = new ArrayList<UIObject>();
	

	public void update() {
		for(UIObject each: fields) {
			each.update();
		}
	}
	
	public void render(Graphics g) {
		for(UIObject each:fields) {
			each.render(g);
		}
	}
	
	public void registerObject(UIObject field) {
		fields.add(field);
	}
	
	public void unregisterObject(UIObject field) {
		fields.remove(field);
	}

	public List<UIObject> getUIObjects() {
		return fields;
	}
	
	public void clear() {
		this.fields.clear();
	}
}
