package logic;

import java.util.ArrayList;

import data.StatisticClass;
import data.StatisticClassValue;
import data.ClampType;

//Robert, Mathias
public class LogicHandler 
{
	

	/**
	 * Methode zur Ermittlung der Klassenmitten eines Datensatzes
	 * @param classes Datensatz, für den die Klassenmitten ermittelt werden sollen.
	 * @return Array der Klassenmitten
	 * @author Mathias Engmann
	 */
	public static float[] getClassMiddles(ArrayList<StatisticClass> classes){
		float[]results = new float[classes.size()];
		
		for(int i=0; i<classes.size(); i++){
			float a = classes.get(i).getLowerValue().value;
			float b  = classes.get(i).getUpperValue().value;
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
	 * @param statisticClass 
	 */
	public static float[] getRelativeOccurences(ArrayList<StatisticClass> classes, int sampleSize) throws IllegalArgumentException{
		float[]results = new float[classes.size()];

		if(sampleSize<=0  || classes.size()<=0){
			
			throw new IllegalArgumentException("Die Stichprobengröße muss größer als 0 sein.");
		}
		
		else{
			for(int i=0; i<classes.size();i++){
				results[i] = classes.get(i).getAbsoluteOccurences()/(float)sampleSize;
			}
			
			
			return results;
		}
	}

	public static float getMedian(ArrayList<StatisticClass> classes){
		//Braucht: Klassenmitten, relative Häufigkeiten
		return 0;
	}
	
	public static float getArithmeticMiddle(float[] classMiddles ){
		
		
		return 0;
	}
	
	public static float getAbsoluteDeviation(){
		//Braucht: Median, Mittelwert, Randwerte ??
		return 0;
	}
	
	public static float getStandardDeviation(){
		return 0;
	}
	
	public static float getVariance(){
		return 0;
	}
	
	public static float getGiniCoefficient(){
		return 0;
	}
	
	public static float[] getQuantiles(){
		float[] a = new float[1];
		a[0]=0;
		return a;
	}
	
	public static float getHistogramElementHeight(){
		return 0;
	}
	
	public static float getHistogramElementWidth(){
		return 0;
	}
}
