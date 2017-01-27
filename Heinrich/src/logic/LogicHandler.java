package logic;

import java.util.ArrayList;

import data.StatisticClass;
import data.StatisticClassValue;
import data.ClampType;

//Robert, Mathias
public class LogicHandler
{
	//#################################TEST#######################################
	ArrayList<StatisticClass> testClasses = new ArrayList<StatisticClass>();
	
	public void postTestResult(){
		testClasses.add(new StatisticClass(new StatisticClassValue(35, Clamp)))
		testClasses.add(new StatisticClass(new StatisticClassValue(35,ClampType.INCLUSIVE), new StatisticClassValue(37,ClampType.EXCLUSIVE),1));
		testClasses.add(new StatisticClass(37,40,3));
		testClasses.add(new StatisticClass(40,43,2));
		testClasses.add(new StatisticClass(43,48,1));
		
	}

	//#################################TEST#######################################
	
	/**
	 * Methode zur Ermittlung der Klassenmitten eines Datensatzes
	 * @param classes Datensatz, für den die Klassenmitten ermittelt werden sollen.
	 * @return Array der Klassenmitten
	 * @author Mathias Engmann
	 */
	public float[] classMiddle(ArrayList<StatisticClass> classes){
		float[]results = new float[classes.size()];
		
		for(int i=0; i<classes.size(); i++){
			float a = classes.get(i).getLowerValue();
			float b  = classes.get(i).getUpperValue();
			float currentClassMiddle = (a+b)/2;
		
			results[i] = currentClassMiddle;
		}
	
		return results;
	}
	
	/**
	 * Methode zur Berechnung der relativen Häufigkeiten der Klassen eines Datensatzes
	 * @param classes Datensatz, für den die relativen Häufigkeiten ermittelt werden sollen.
	 * @return Array der relativen Häufigkeiten
	 * @author Mathias Engmann
	 */
	public float[] relativeOccurence(ArrayList<StatisticClass> classes){
		float[]results = new float[classes.size()];
		
		for(int i=0; i<classes.size();i++){
			//result[i] = ;
			
		}
		
		return results;
	}

	public static float median(){
		//Braucht: Klassenmitten, relative Häufigkeiten
		return 0;
	}
	
	public static float arithmeticMiddle(){
		//Braucht: Klassenmitten
		return 0;
	}
	
	public static float absoluteDeviation(){
		//Braucht: Median, Mittelwert, Randwerte ??
		return 0;
	}
	
	public static float standardDeviation(){
		return 0;
	}
	
	public static float variance(){
		return 0;
	}
	
	public static float giniCoefficient(){
		return 0;
	}
	
	public static float[] quantiles(){
		float[] a = new float[1];
		a[0]=0;
		return a;
	}
	
	public static float histogramElementHeight(){
		return 0;
	}
	
	public static float histogramElementWidth(){
		return 0;
	}
}
