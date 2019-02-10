package dev.blue.rotu.ui;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class NumberInputField extends TextInputField {
	
	public NumberInputField(String id, int x, int y, int width, int height, String preview, String value, boolean writable, boolean protectedDisplay, int animationSpeed, TextArea toWriteTo, BufferedImage... images) {
		super(id, x, y, width, height, preview, value, writable, protectedDisplay, animationSpeed, toWriteTo, images);
		allowed = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	}
}
