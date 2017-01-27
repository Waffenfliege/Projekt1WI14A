package data;

import java.util.ArrayList;

//Jonathan
public class DataHandler
{
	private final static int MAX_CLASS_COUNT = 20;
	private ArrayList<StatisticClass> classes;
	
	public DataHandler()
	{
		classes = new ArrayList<StatisticClass>();
	}
	
	public ArrayList<StatisticClass> getAllClasses(){
		return classes;
	}
	
	public StatisticClass getElement(int index){
		return classes.get(index);
		
	}
}
