package graphs;

import java.awt.Color;

/**
 * @author Mathias Engmann
 */
public class EmpiricLine
{
	int length;
	Color color;
	
	/**
	 * Returns an object that stores the main attributes of a line needed to paint an empiric distribution graph
	 * @param length The lenght of the line, in pixels
	 * @param color The color of the line.
	 * @author Mathias Engmann
	 */
	public EmpiricLine(int length, Color color){
		this.length = length;
		this.color = color;
	} 
	
	public int getLength() {
		return length;
	}
	
	public void setWidth(int width) {
		this.length = width;
	}

	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	
}
