package data;

import java.util.ArrayList;

//Jonathan
public class DataHandler
{
	private final static int MAX_CLASS_COUNT = 20;
	private static ArrayList<StatisticClass> classes;
	
	public DataHandler()
	{
		classes = new ArrayList<StatisticClass>();
	}
	
	public  ArrayList<StatisticClass> getList(){
		return classes;
	}
	
	public  StatisticClass getElement(int index){
		return classes.get(index);
		
	}
	
	public void updateListItem(int index, StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) {
		classes.get(index).setLowerValue(lowerValue);
		classes.get(index).setUpperValue(upperValue);
		classes.get(index).setAbsoluteOccurences(absoluteOccurence);
	}
	
	public  void put(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence){
		classes.add(new StatisticClass(lowerValue, upperValue, absoluteOccurence));	
	}
	
	public  int getSampleSize(){
		int  sampleSize = 0;
		
		return sampleSize;
	}
}
