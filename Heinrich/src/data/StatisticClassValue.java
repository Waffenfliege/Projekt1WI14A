package data;

/**
 * Class that stores information about a single class value - its concrete value and clamp type
 * @author Mathias Engmann
 *
 */
public class StatisticClassValue {

	public float value;
	public ClampType clamp;
	
	/**
	 * Creates an object that stores information about one value of a statistical class
	 * @param value the value to store
	 * @param clamp the clamp type to store (ClampType.INCLUSIVE oder ClampType.EXCLUSIVE)
	 */
	public StatisticClassValue(float value, ClampType clamp){
		this.value = value;
		this.clamp = clamp;
	}
}
