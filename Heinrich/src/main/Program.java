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
		
		float relativeOccurences[] = LogicHandler.getRelativeOccurences(dataHandler.getAllClasses());
		float classMiddles[] = LogicHandler.getClassMiddles(dataHandler.getAllClasses());

		
		for(int i=0; i<dataHandler.getAllClasses().size();i++){
		
			float absoluteOccurence = dataHandler.getElement(i).getAbsoluteOccurences();
			System.out.println("Daten für Klasse "+ (i+1) + ": ");
			
			System.out.println("--- absolute Häufigkeit: " + absoluteOccurence);
			System.out.println("--- relative Häufigkeit: " + relativeOccurences[i]);
			System.out.println("--- absolute Häufigkeit: " + classMiddles[i]);
		}
		
		
		float median = LogicHandler.median(TestDataHandler.getAllClasses());
	
	}

}
