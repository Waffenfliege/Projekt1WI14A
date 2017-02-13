package graphs;

/**
 * 
 * Class to store details about an element of a histogram graph in
 * @author Mathias Engmann
 */
public class HistogramTupel {
	
	private float height, width;
	
	/**
	 * Creates an object that stores details about an element to be drawn in a histogram graph
	 * @param height the height of the element - the relative share of the class
	 * @param width width of the element - upper limit of the class minus lower limit of the class
	 */
	public HistogramTupel(float height, float width){
		this.width = width;
		this.height = height;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
}
