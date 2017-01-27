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
	
	public static ArrayList<StatisticClass> getAllClasses(){
		return classes;
	}
	
	public static StatisticClass getElement(int index){
		return classes.get(index);
		
	}
}
