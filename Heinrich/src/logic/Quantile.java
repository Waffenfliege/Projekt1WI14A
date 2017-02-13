package logic;

/**
 * Class that stores details about a quantile: it's alpha value and the value of the quantile
 * @author Mathias Engmann
 *
 */
public class Quantile {
	
	private float value, alpha;
	
	/**
	 * Creates an object to store details about a quantile
	 * @param value the value of the quantile
	 * @param alpha the alpha value of the quantile
	 */
	public Quantile(float value, float alpha) {
		super();
		this.value = value;
		this.alpha = alpha;
	}
	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}


}
