package graphs;

import java.awt.Color;

/**
 * Class that stores the attributes of a simple rectangle
 * @author Mathias Engmann
 */
public class Rectangle{

	private int width, height;
	private Color color;
	
	/**
	 * Creates an object that stores details about a rectangle
	 * @param width the width of the rectangle
	 * @param height the height of the rectangle
	 * @param color the color of the rectangle
	 */
	public Rectangle(int width, int height, Color color){
		this.width = width;
		this.height = height;
		this.color = color;
	} 
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}


}
