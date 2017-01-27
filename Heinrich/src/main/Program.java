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
		TestDataHandler dataHandler = new TestDataHandler();
		
		//######################TEST###################
		
		//float relativeOccurences[] = LogicHandler.getRelativeOccurences(dataHandler.getList(), dataHandler.getSampleSize());
		try{
			float relativeOccurences[] = LogicHandler.getRelativeOccurences(dataHandler.getList(), 0);
			float classMiddles[] = LogicHandler.getClassMiddles(dataHandler.getList());

			
			for(int i=0; i<dataHandler.getList().size();i++){
			
				float absoluteOccurence = dataHandler.getElement(i).getAbsoluteOccurences();
				System.out.println("Daten für Klasse "+ (i+1) + ": ");
				
				System.out.println("--- absolute Häufigkeit: " + absoluteOccurence);
				System.out.println("--- relative Häufigkeit: " + relativeOccurences[i]);
				System.out.println("--- Klassenmitte: " + classMiddles[i]);
			}
			
			
			float median = LogicHandler.median(dataHandler.getList());
		
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}

}
