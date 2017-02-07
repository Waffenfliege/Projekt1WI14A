package graphs;

import java.awt.Color;

public class EmpiricLine
{
	int length;
	Color color;
	
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
