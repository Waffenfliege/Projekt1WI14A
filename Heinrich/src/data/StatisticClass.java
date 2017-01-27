package data;

public class StatisticClass {

	private StatisticClassValue lowerValue;
	private StatisticClassValue upperValue;
	
	private int absoluteOccurences;
	private float relativeOccurences;
	
	public StatisticClass(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurences){
		this.lowerValue = lowerValue;
		this.upperValue = upperValue;
		
		this.absoluteOccurences = absoluteOccurences;
		
	}
	
	public int getAbsoluteOccurences(){
		return absoluteOccurences;
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
