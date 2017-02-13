package graphs;

/**
 * Class that stores a position in 2 dimensional space. Has no methods to calculate the magnitude or any other mathematical operation related to "vectors"
 * @author Mathias Engmann
 */
public class Vector2D {

	private int posX, posY;

	/**
	 * Returns an object that stores the x and y position of a point in space
	 * @param posX x position of the point to store
	 * @param posY y position of the point to store
	 * @author Mathias Engmann
	 */
	public Vector2D(int posX, int posY) {
		super();
		this.posX = posX;
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
