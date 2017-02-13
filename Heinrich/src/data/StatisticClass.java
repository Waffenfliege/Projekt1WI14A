package data;

/**
 * Class that stores details about a statistical class
 * @author Mathias Engmann
 *
 */
public class StatisticClass {

	private StatisticClassValue lowerValue;
	private StatisticClassValue upperValue;
	
	private int absoluteOccurences;
	/**
	 * Creates an object that stores information about the upper and lower value, the absolute occurence and the upper and lower clamp type of a statistical class
	 * @param lowerValue StatisticClassValue with the upper class value
	 * @param upperValue StatisticClassValue with the lower class value
	 * @param absoluteOccurences the absolute occurence
	 */
	public StatisticClass(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurences){
		this.lowerValue = lowerValue;
		this.upperValue = upperValue;
		
		this.absoluteOccurences = absoluteOccurences;
		
	}
	
	public StatisticClassValue getLowerValue() {
		return lowerValue;
	}

	public void setLowerValue(StatisticClassValue lowerValue) {
		this.lowerValue = lowerValue;
	}

	public StatisticClassValue getUpperValue() {
		return upperValue;
	}
	
	public void setUpperValue(StatisticClassValue upperValue) {
		this.upperValue = upperValue;
	}

	public int getAbsoluteOccurences(){
		return absoluteOccurences;
	}

	public void setAbsoluteOccurences(int absoluteOccurences) {
		this.absoluteOccurences = absoluteOccurences;
	}	
}
