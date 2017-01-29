package logic;

public class Quantile {
	
	private float value;
	private float alpha;
	
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

	public Quantile(float value, float alpha) {
		super();
		this.value = value;
		this.alpha = alpha;
	}
}
