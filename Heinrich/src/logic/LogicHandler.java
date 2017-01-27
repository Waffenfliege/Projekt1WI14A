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

	public static float getMedian(ArrayList<StatisticClass> classes, float[] classMiddles, float[] relativeOccurences) throws Exception{
		//Braucht: Klassenmitten, relative Häufigkeiten
		
		
		if(classes.size() <=0 || classMiddles.length <= 0 || relativeOccurences.length <=0 || classMiddles.length != relativeOccurences.length){
			throw new IllegalArgumentException("Die angegebenen Wertearrays sind nicht gefüllt oder die Anzahl ihrer Elemente ist nicht gleich.");
		}
		
		else{
			float currentRelativeShare = 0;
			float relativeShareBeforeHit = 0;
			
			int classIndexWithMedian = -1;
			
			for(int i=0 ; i<classMiddles.length; i++){
				
				currentRelativeShare += relativeOccurences[i];
				
				if(currentRelativeShare>0.5f){
					classIndexWithMedian = i;
					break;	
				}
				
				else{
					relativeShareBeforeHit = currentRelativeShare;
				}
			
			}
			
			if(classIndexWithMedian==-1){
				throw new Exception("Es konnte keine Klasse ermittelt werden, in dem der Median liegt");
			}
			
			else{
				
				float z1 =  classes.get(classIndexWithMedian).getLowerValue().value;
				float z2 =  classes.get(classIndexWithMedian).getUpperValue().value;
				
				float r1 = relativeShareBeforeHit;
				float r2 = currentRelativeShare;
				
				float result = z1 +((0.5f-r1)/(r2-r1))*(z2-z1);
				return result;
			}
		}
	}
	

	//TODO Comments
	public static float getArithmeticMiddle(float[] classMiddles, float[] relativeOccurences){
		int classCount = classMiddles.length;
		float arithmeticMiddle = 0;
		
		for (int i = 0; i < classCount; i++){
			arithmeticMiddle += (classMiddles[i] * relativeOccurences[i]);
		}
		
		return arithmeticMiddle;
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
		return  0;
	}
}
