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
	private static void updateListItem(int index, StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) {
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

	private static void putListItem(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence){
		classes.add(new StatisticClass(lowerValue, upperValue, absoluteOccurence));	
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
	
	public static void receiveData(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence, int currentViewIndex) throws Exception{
		//are we handling a newly entered class or were data just changed?
		//new class
		if(isNewClass(currentViewIndex)){
			checkClassCreation(lowerValue, upperValue, absoluteOccurence);
		}
		
		//data were just changed
		else{
			checkClassChange(lowerValue, upperValue, absoluteOccurence, currentViewIndex);
		}
	}
	
	private static void checkClassCreation(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence) throws Exception{
		//no classes existing yet
		if(classes.size()==0){
			putListItem(lowerValue, upperValue, absoluteOccurence);
		}
		
		//classes already existing
		else{
			
			//class limit reached
			if(isClassLimitReached()){
				throw new Exception("Es wurden bereits 20 Klassen eingegeben!");
			}
			
			//class limit not reached
			else{
				
				int[] overlappingClasses = checkForOverlap();
				//there are no overlapping class values
				if(overlappingClasses==null){
					putListItem(lowerValue, upperValue, absoluteOccurence);
				}
				
				//there are overlapping class values
				else{
					throw new IllegalOverlapException("Einige Klassengrenzen überschneiden sich!", overlappingClasses);
				}
			}
		}
	}
	
	private static void checkClassChange(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence, int currentViewIndex){
		int[] overlappingClasses = checkForOverlap();
		
		//no overlap
		if(overlappingClasses==null){
			updateListItem(currentViewIndex, lowerValue, upperValue, absoluteOccurence);
		}
		
		//overlap
		else{
			throw new IllegalOverlapException("Einige Klassengrenzen überschneiden sich!", overlappingClasses);
		}
	}
	
	private static boolean isNewClass(int currentViewIndex){
		return false;
		
	}
	
	private static boolean isClassLimitReached(){
		return false;
		
	}
	
	private static int[] checkForOverlap(){
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
