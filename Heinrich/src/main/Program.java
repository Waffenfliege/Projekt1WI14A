package main;

import data.ClampType;
import data.DataHandler;
import data.StatisticClass;
import data.StatisticClassValue;
import logic.LogicHandler;
import logic.Quantile;
import test.TestDataHandler;
import view.MainFrame;

/**
 * 
 * @author Alle
 *
 */
public class Program
{
	/**
	 * (Description)
	 * 
	 * @param args
	 */
	public static void main(String args[])
	{

		DataHandler.initialize();
		MainFrame.startFrame();

		
		
		
		
		// ######################TEST###################

//		TestDataHandler dataHandler = new TestDataHandler();
/*
		try
		{
			/*DataHandler.initialize();
			data.DataHandler.receiveData(new StatisticClassValue(35,ClampType.INCLUSIVE), new StatisticClassValue(37,ClampType.EXCLUSIVE),1,0);
			data.DataHandler.receiveData(new StatisticClassValue(37,ClampType.INCLUSIVE), new StatisticClassValue(40,ClampType.EXCLUSIVE),3,1);
			data.DataHandler.receiveData(new StatisticClassValue(40,ClampType.INCLUSIVE), new StatisticClassValue(43,ClampType.EXCLUSIVE),2,2);
			data.DataHandler.receiveData(new StatisticClassValue(43,ClampType.INCLUSIVE), new StatisticClassValue(48,ClampType.EXCLUSIVE),1,3);

			float relativeOccurences[] = LogicHandler.getRelativeOccurences(DataHandler.getList(), DataHandler.getSampleSize());
			float classMiddles[] = LogicHandler.getClassMiddles(DataHandler.getList());

			for (int i = 0; i < DataHandler.getList().size(); i++)
			{

				float absoluteOccurence = DataHandler.getElement(i).getAbsoluteOccurences();
				System.out.println("Daten für Klasse " + (i + 1) + ": ");

				System.out.println("--- absolute Häufigkeit: " + absoluteOccurence);
				System.out.println("--- relative Häufigkeit: " + relativeOccurences[i]);
				System.out.println("--- Klassenmitte: " + classMiddles[i]);
			}

			float median = LogicHandler.getMedian(DataHandler.getList(), classMiddles, relativeOccurences);
			float arithmeticMiddle = LogicHandler.getArithmeticMiddle(classMiddles, relativeOccurences);

			System.out.println("Daten für Liste: ");
			System.out.println("--- arithmetisches Mittel: " + arithmeticMiddle);
			System.out.println("--- Median: " + median);
			System.out.println("--- Quantile: ");

			Quantile[] quantiles = LogicHandler.getQuantiles(DataHandler.getList(), classMiddles, relativeOccurences);

			for (int i = 0; i < quantiles.length; i++)
			{
				System.out.println("----- Quantil " + quantiles[i].getAlpha() + ": " + quantiles[i].getValue());
			}

			float z = 40;
			float meanAbsoluteDeviation = LogicHandler.getMeanAbsoluteDeviation(DataHandler.getList(), classMiddles,
					relativeOccurences, z);
			System.out.println("--- Mittlere arithmetische Abweichung für z = " + z + ": " + meanAbsoluteDeviation);

			float variance = LogicHandler.getVariance(DataHandler.getList(), classMiddles, arithmeticMiddle,
					DataHandler.getSampleSize());
			System.out.println("--- Varianz: " + variance);

			float standardDeviation = LogicHandler.getStandardDeviation(variance);
			System.out.println("--- Standardabweichung: " + standardDeviation);

		} catch (Exception e)
		{
			e.printStackTrace();
		}*/

		// ######################TEST###################

	}

}
