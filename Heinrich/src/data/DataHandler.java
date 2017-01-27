package data;

import java.util.ArrayList;

public class DataHandler
{
	private final static int MAX_CLASS_COUNT = 20;
	private static ArrayList<StatisticClass> classes;
	
	public DataHandler()
	{
		classes = new ArrayList<StatisticClass>();
	}
	
	//TODO Comments
	public  ArrayList<StatisticClass> getList(){
		return classes;
	}
	
	//TODO Comments
	public  StatisticClass getElement(int index){
		return classes.get(index);		
	}
	//TODO Comments
	public void updateListItem(int index, StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) {
		classes.get(index).setLowerValue(lowerValue);
		classes.get(index).setUpperValue(upperValue);
		classes.get(index).setAbsoluteOccurences(absoluteOccurence);
	}
	
	//TODO Comments & Validation w/ classCount?
	public  void put(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence){
		classes.add(new StatisticClass(lowerValue, upperValue, absoluteOccurence));	
	}
	
	//TODO Comments
	public  int getSampleSize(){
		int sampleSize = 0;
		int classCount = classes.size();
		for (int i = 0; i < classCount; i++) {
			sampleSize += getElement(i).getAbsoluteOccurences();
		}
		return sampleSize;
	}
}
