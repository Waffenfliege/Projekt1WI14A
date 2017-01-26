package model;

public class StatisticClass {
	
	private double lowerValue;
	private double higherValue;
	private int absoluteQuantity;
	private float relativeQuantity;
	
	private ClampType lowerClamp;
	private ClampType higherClamp;
	
	public StatisticClass(double lowerValue, double higherValue, int absoluteQuantity, float relativeQuantity,  ClampType lowerClamp, ClampType higherClamp) {
		super();
		this.lowerValue = lowerValue;
		this.higherValue = higherValue;
		this.absoluteQuantity = absoluteQuantity;
		this.relativeQuantity = relativeQuantity;
		this.lowerClamp = lowerClamp;
		this.higherClamp = higherClamp;
	}
	public double getLowerValue() {
		return lowerValue;
	}
	public void setLowerValue(double lowerValue) {
		this.lowerValue = lowerValue;
	}
	public double getHigherValue() {
		return higherValue;
	}
	public void setHigherValue(double higherValue) {
		this.higherValue = higherValue;
	}
	public int getAbsoluteQuantity() {
		return absoluteQuantity;
	}
	public void setAbsoluteQuantity(int absoluteQuantity) {
		this.absoluteQuantity = absoluteQuantity;
	}
	public float getRelativeQuantity() {
		return relativeQuantity;
	}
	public void setRelativeQuantity(float relativeQuantity) {
		this.relativeQuantity = relativeQuantity;
	}
	public ClampType getLowerClamp() {
		return lowerClamp;
	}
	public void setLowerClamp(ClampType lowerClamp) {
		this.lowerClamp = lowerClamp;
	}
	public ClampType getHigherClamp() {
		return higherClamp;
	}
	public void setHigherClamp(ClampType higherClamp) {
		this.higherClamp = higherClamp;
	}
	
	
}
