package main;

import data.DataHandler;
import logic.LogicHandler;
import test.TestDataHandler;
import view.ViewHandler;

public class Program
{

	public static void main(String args[])
	{

		ViewHandler view = new ViewHandler();
		
		
		//######################TEST###################
		
		TestDataHandler dataHandler = new TestDataHandler();
	
		
		try{
			float relativeOccurences[] = LogicHandler.getRelativeOccurences(dataHandler.getList(), dataHandler.getSampleSize());
			float classMiddles[] = LogicHandler.getClassMiddles(dataHandler.getList());

			
			for(int i=0; i<dataHandler.getList().size();i++){
			
				float absoluteOccurence = dataHandler.getElement(i).getAbsoluteOccurences();
				System.out.println("Daten für Klasse "+ (i+1) + ": ");
				
				System.out.println("--- absolute Häufigkeit: " + absoluteOccurence);
				System.out.println("--- relative Häufigkeit: " + relativeOccurences[i]);
				System.out.println("--- Klassenmitte: " + classMiddles[i]);
			}
			
			float median = LogicHandler.getMedian(dataHandler.getList(), classMiddles, relativeOccurences);
			float arithmeticMiddle = LogicHandler.getArithmeticMiddle(classMiddles, relativeOccurences);
			
			System.out.println("Daten für Liste: ");
			System.out.println("--- arithmetisches Mittel: " + arithmeticMiddle);
			System.out.println("--- Median: " + median);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		//######################TEST###################
		
	
	}

}
