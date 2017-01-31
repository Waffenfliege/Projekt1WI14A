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
	public  static ArrayList<StatisticClass> getList(){
		return classes;
	}
	
	/**
	 * 
	 * @param index number specifying the element in the ArrayList. 
	 * @return Specific element of the statistic class ArrayList.
	 */
	public static  StatisticClass getElement(int index){
		return classes.get(index);		
	}
	
	/**
	 * This method will update an existing element of the ArrayList. 
	 * @param index number specifying the element in the ArrayList. 
	 * @param lowerValue lower value of the statistical class. 
	 * @param upperValue upper value of the statistical class. 
	 * @param absoluteOccurence absolute occurrence of data points in this statistical class.
	 */
	private void updateListItem(int index, StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) {
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

	private void put(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) throws Exception{
		//validierung aus anderer Methode
		if(classes.size() < 20){
			classes.add(new StatisticClass(lowerValue, upperValue, absoluteOccurence));	
		}else{
			throw new Exception("Es können keine weiteren Klassen hinzugefügt werden.");
		}
	}
	
	/**
	 * @return absolute sample size over all statistical classes
	 */
	public static  int getSampleSize(){
		int sampleSize = 0;
		int classCount = classes.size();
		for (int i = 0; i < classCount; i++) {
			sampleSize += getElement(i).getAbsoluteOccurences();
		}
		return sampleSize;
	}
	
	
	public static void receiveData(StatisticClassValue statisticClassValue, StatisticClassValue statisticClassValue2, int absoluteOccurence){
		
	}
	
	/**
	 * If the given view index is smaller than the number of elements in the array list, an element with that index already exists. If it is equal oder higher than the array list size, there is no element with that index in the array list.  
	 * @param currentViewIndex Index number of the statistical class that is modified in the view.
	 * @return True if the given index of the view is equal to the array size. 
	 */
	private boolean isNewClass(int currentViewIndex){
		if(currentViewIndex >= classes.size()){
			return true;
		}else{
			return false;
		}
	}
	
	/** 
	 * @return True if class limit is reached. False if class limit is not reached.
	 */
	private boolean isClassLimitReached(){
		if(classes.size() < MAX_CLASS_COUNT){
			return false;
		}else{
			return true;
		}
	}
	
	private int[] checkForOverlap(){
		int[] result = null; 
		
		
		return result; //null, wenn kein Overlap, index von den betroffenen Klassen, wenn Overlap
	}
	
	
	//Vorgehen: 
		//Anschließend prüfen, ob bereits Klassen existieren.
		//Wenn bereits Klassen existieren muss geprüft werden 
			// Ist bereits eine gefüllte Klasse selektiert ? 
				// JA: Gibt es im View andere Werte als in der gespeicherten Klasse?
						//JA : Weiter mit Prüfung der überlappenden Klassengrenzen
						//NEIN : Keine Aktion
				// NEIN: Prüfung überlappender Klassengrenzen, dann DataHandler.put();	
}
