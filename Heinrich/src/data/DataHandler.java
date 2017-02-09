package data;

import java.util.ArrayList;

import view.InputPanel;

public class DataHandler
{
	private final static int MAX_CLASS_COUNT = 20;
	private static ArrayList<StatisticClass> classes;
	private static ResultSet results;

	public DataHandler()
	{
		classes = new ArrayList<StatisticClass>();
		results = new ResultSet(20);
	}
	
	

	public ResultSet getResults() {
		return results;
	}



	public void setResults(ResultSet results) {
		DataHandler.results = results;
	}



	public void initialize()
	{
		classes = new ArrayList<StatisticClass>();
	}

	public float getLowestValue(){
		float result = classes.get(0).getLowerValue().value;
		for(int i=1; i<classes.size(); i++){
			if(classes.get(i).getLowerValue().value<result){
				result = classes.get(i).getLowerValue().value;
			}
		}
		
		return result;
	}
	
	public float getHighestValue(){
		float result = classes.get(0).getUpperValue().value;
		for(int i=1; i<classes.size(); i++){
			if(classes.get(i).getUpperValue().value>result){
				result = classes.get(i).getUpperValue().value;
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @return ArrayList of all statistic classes.
	 */
	public ArrayList<StatisticClass> getList()
	{
		return classes;
	}

	/**
	 * 
	 * @param index
	 *            number specifying the element in the ArrayList.
	 * @return Specific element of the statistic class ArrayList.
	 */
	public StatisticClass getElement(int index)
	{
		return classes.get(index);
	}

	/**
	 * This method will update an existing element of the ArrayList.
	 * 
	 * @param index
	 *            number specifying the element in the ArrayList.
	 * @param lowerValue
	 *            lower value of the statistical class.
	 * @param upperValue
	 *            upper value of the statistical class.
	 * @param absoluteOccurence
	 *            absolute occurrence of data points in this statistical class.
	 */
	private void updateListItem(int index, StatisticClassValue lowerValue, StatisticClassValue upperValue,
			int absoluteOccurence)
	{
		classes.get(index).setLowerValue(lowerValue);
		classes.get(index).setUpperValue(upperValue);
		classes.get(index).setAbsoluteOccurences(absoluteOccurence);
	}

	/**
	 * This method will add a new element to the ArrayList for the statistical
	 * classes if maximum of classes is not yet reached.
	 * 
	 * @param lowerValue
	 *            lower value of the statistical class.
	 * @param upperValue
	 *            upper value of the statistical class.
	 * @param absoluteOccurence
	 *            absolute occurrence of samples in this statistical class.
	 * @throws Exception
	 *             Throws exception if the maximum number of statistical classes
	 *             is reached.
	 */
	private void putListItem(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence)
	{
		classes.add(new StatisticClass(lowerValue, upperValue, absoluteOccurence));
	}

	/**
	 * @return absolute sample size over all statistical classes
	 */
	public int getSampleSize()
	{
		int sampleSize = 0;
		int classCount = classes.size();
		for (int i = 0; i < classCount; i++)
		{
			sampleSize += getElement(i).getAbsoluteOccurences();
		}
		return sampleSize;
	}
	
	//TODO Comments
	public int getClassCount()
	{
		return classes.size();
	}
	
	//TODO Comments
	public void receiveData(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence,
			int currentViewIndex) throws IllegalOverlapException, IllegalBorderException, Exception
	{
		checkBorderOrder(lowerValue, upperValue);
		// are we handling a newly entered class or were data just changed?
		// new class
		if (isNewClass(currentViewIndex))
		{
			
			checkClassCreation(lowerValue, upperValue, absoluteOccurence);
			InputPanel.setTableValue(lowerValue, upperValue, absoluteOccurence, currentViewIndex);

		}

		// data was just changed
		else
		{
			checkClassChange(lowerValue, upperValue, absoluteOccurence, currentViewIndex);
		}
		
	}
	
	//TODO Comments
	private void checkClassCreation(StatisticClassValue lowerValue, StatisticClassValue upperValue,
			int absoluteOccurence) throws IllegalOverlapException, Exception
	{
		// no classes existing yet
		try
		{
			if (classes.size() == 0)
			{
				putListItem(lowerValue, upperValue, absoluteOccurence);
			}

			// classes already existing
			else
			{

				// class limit reached
				if (isClassLimitReached())
				{
					throw new Exception("Es wurden bereits 20 Klassen eingegeben!");
				}

				// class limit not reached
				else
				{
					checkForOverlap(-1, lowerValue, upperValue);

					putListItem(lowerValue, upperValue, absoluteOccurence);
				}
			}
		} catch (NullPointerException e)
		{
			putListItem(lowerValue, upperValue, absoluteOccurence);
		}
	}
	
	//TODO Comments
	private void checkClassChange(StatisticClassValue lowerValue, StatisticClassValue upperValue, int absoluteOccurence,
			int currentViewIndex) throws IllegalOverlapException
	{
		checkForOverlap(currentViewIndex, lowerValue, upperValue);

		updateListItem(currentViewIndex, lowerValue, upperValue, absoluteOccurence);

	}

	/**
	 * If the given view index is smaller than the number of elements in the
	 * array list, an element with that index already exists. If it is equal
	 * oder higher than the array list size, there is no element with that index
	 * in the array list.
	 * 
	 * @param currentViewIndex
	 *            Index number of the statistical class that is modified in the
	 *            view.
	 * @return True if the given index of the view is equal to the array size.
	 */
	private boolean isNewClass(int currentViewIndex)
	{
		try
		{
			if (currentViewIndex == classes.size())
			{
				return true;
			} else
			{
				return false;
			}
		} catch (NullPointerException e)
		{
			return true;
		}
	}

	/**
	 * @return True if class limit is reached. False if class limit is not
	 *         reached.
	 */
	private boolean isClassLimitReached()
	{
		if (classes.size() < MAX_CLASS_COUNT)
		{
			return false;
		} else
		{
			return true;
		}
	}
	
	//TODO Comments
	private void checkForOverlap(int currentViewIndex, StatisticClassValue lowerValue, StatisticClassValue upperValue)
			throws IllegalOverlapException
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
	
		//New class Creation 
		if(currentViewIndex==-1){
			for (int i = 0; i < classes.size(); i++)
			{
				if (classes.get(i).getUpperValue().value <= lowerValue.value)
				{
					if (classes.get(i).getUpperValue().clamp == ClampType.INCLUSIVE && lowerValue.clamp == ClampType.INCLUSIVE)
					{
						result.add(i);
					} else
					{
						continue;
					}
				} else if (classes.get(i).getLowerValue().value >= upperValue.value)
				{
					if (classes.get(i).getLowerValue().clamp == ClampType.INCLUSIVE && upperValue.clamp == ClampType.INCLUSIVE)
					{
						result.add(i);
					} else
					{
						continue;
					}
				} else
				{
					result.add(i);
				}
			}

		
		}
		
		//Class change - exclude current class from set of classes to test
		//Happens for classes to be changed
		else{
			//All classes below changed index
			for (int i = 0; i < currentViewIndex; i++)
			{
				//TODO kleiner und gleich muss getrennt behandelt werden! unterscheidung fehler. 
				//Gleiche klammern bei gleichen Werten. Klammern egal wenn obergrenze der bestehenden 
				//Klasse kleiner als untergrenze der neuen Klasse. Da ist die Frage ob da ein Abstand 
				//bestehen darf oder das ein anderer Fehler ist.  
				
				if (classes.get(i).getUpperValue().value <= lowerValue.value)
				{
					if (classes.get(i).getUpperValue().clamp == ClampType.INCLUSIVE && lowerValue.clamp == ClampType.INCLUSIVE)
					{
						result.add(i);
					} else
					{
						continue;
					}
				} else if (classes.get(i).getLowerValue().value >= upperValue.value)
				{
					if (classes.get(i).getLowerValue().clamp == ClampType.INCLUSIVE && upperValue.clamp == ClampType.INCLUSIVE)
					{
						result.add(i);
					} else
					{
						continue;
					}
				} else
				{
					result.add(i);
				}
			}
			
			//All classes above changed index
			for (int i = currentViewIndex+1; i < classes.size(); i++)
			{
				if (classes.get(i).getUpperValue().value <= lowerValue.value)
				{
					if (classes.get(i).getUpperValue().clamp == ClampType.INCLUSIVE && lowerValue.clamp == ClampType.INCLUSIVE)
					{
						result.add(i);
					} else
					{
						continue;
					}
				} else if (classes.get(i).getLowerValue().value >= upperValue.value)
				{
					if (classes.get(i).getLowerValue().clamp == ClampType.INCLUSIVE && upperValue.clamp == ClampType.INCLUSIVE)
					{
						result.add(i);
					} else
					{
						continue;
					}
				} else
				{
					result.add(i);
				}
			}
		}
		
		if (result.size() > 0)
		{
			throw new IllegalOverlapException("Klassen überschneiden sich.", result);
		}

	}
	
	//TODO Comments
	private void checkBorderOrder(StatisticClassValue lowerValue, StatisticClassValue upperValue)
		throws IllegalBorderException
	{
		if(!(upperValue.value > lowerValue.value))
		{
			throw new IllegalBorderException("Die untere Klassengrenze ist größer oder gleich der oberen Grenze.");
		}
	}
}
