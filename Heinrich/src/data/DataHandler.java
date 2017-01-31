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
	
	/**
	 * 
	 * @return ArrayList of all statistic classes.
	 */
	public  ArrayList<StatisticClass> getList(){
		return classes;
	}
	
	/**
	 * 
	 * @param index number specifying the element in the ArrayList. 
	 * @return Specific element of the statistic class ArrayList.
	 */
	public  StatisticClass getElement(int index){
		return classes.get(index);		
	}
	
	/**
	 * This method will update an existing element of the ArrayList. 
	 * @param index number specifying the element in the ArrayList. 
	 * @param lowerValue lower value of the statistical class. 
	 * @param upperValue upper value of the statistical class. 
	 * @param absoluteOccurence absolute occurrence of data points in this statistical class.
	 */
	public void updateListItem(int index, StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) {
		classes.get(index).setLowerValue(lowerValue);
		classes.get(index).setUpperValue(upperValue);
		classes.get(index).setAbsoluteOccurences(absoluteOccurence);
	}
	
	/**
	 * This method will add a new element to the ArrayList for the statistical classes if maximum of classes is not yet reached. 
	 * @param lowerValue lower value of the statistical class. 
	 * @param upperValue upper value of the statistical class. 
	 * @param absoluteOccurence absolute occurrence of samples in this statistical class. 
	 * @throws Exception Throws exception if the maximum number of statistical classes is reached.
	 */
	public  void put(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) throws Exception{
		if(classes.size() < 20){
			classes.add(new StatisticClass(lowerValue, upperValue, absoluteOccurence));	
		}else{
			throw new Exception("Es können keine weiteren Klassen hinzugefügt werden.");
		}
	}
	
	/**
	 * @return absolute sample size over all statistical classes
	 */
	public  int getSampleSize(){
		int sampleSize = 0;
		int classCount = classes.size();
		for (int i = 0; i < classCount; i++) {
			sampleSize += getElement(i).getAbsoluteOccurences();
		}
		return sampleSize;
	}
}
