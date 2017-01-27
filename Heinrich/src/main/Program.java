package main;

import logic.LogicHandler;
import test.TestDataHandler;
import view.ViewHandler;

public class Program
{

	public static void main(String args[])
	{

		ViewHandler view = new ViewHandler();

		
		//######################TEST###################
		
		
		for(int i=0; i<TestDataHandler.getAllClasses().size();i++){
		
			float absoluteOccurence;
			float relativeOccurence;
			float classMiddle;	
			System.out.println("Daten für Klasse "+ (i+1) + ": ");
			
			System.out.println("--- absolute Häufigkeit: " + absoluteOccurence);
			System.out.println("--- relative Häufigkeit: " + absoluteOccurence);
			System.out.println("--- absolute Häufigkeit: " + absoluteOccurence);
		}
		
		
		float median = LogicHandler.median(TestDataHandler.getAllClasses());
	
	}

}
