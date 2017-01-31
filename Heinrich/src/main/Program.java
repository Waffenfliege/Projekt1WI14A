package main;

import data.DataHandler;
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

		MainFrame.startFrame();

		
		
		
		
		// ######################TEST###################

		TestDataHandler dataHandler = new TestDataHandler();

		try
		{
			float relativeOccurences[] = LogicHandler.getRelativeOccurences(dataHandler.getList(), dataHandler.getSampleSize());
			float classMiddles[] = LogicHandler.getClassMiddles(dataHandler.getList());

			for (int i = 0; i < dataHandler.getList().size(); i++)
			{

				float absoluteOccurence = dataHandler.getElement(i).getAbsoluteOccurences();
				System.out.println("Daten für Klasse " + (i + 1) + ": ");

				System.out.println("--- absolute Häufigkeit: " + absoluteOccurence);
				System.out.println("--- relative Häufigkeit: " + relativeOccurences[i]);
				System.out.println("--- Klassenmitte: " + classMiddles[i]);
			}

			float median = LogicHandler.getMedian(dataHandler.getList(), classMiddles, relativeOccurences);
			float arithmeticMiddle = LogicHandler.getArithmeticMiddle(classMiddles, relativeOccurences);

			System.out.println("Daten für Liste: ");
			System.out.println("--- arithmetisches Mittel: " + arithmeticMiddle);
			System.out.println("--- Median: " + median);
			System.out.println("--- Quantile: ");

			Quantile[] quantiles = LogicHandler.getQuantiles(dataHandler.getList(), classMiddles, relativeOccurences);

			for (int i = 0; i < quantiles.length; i++)
			{
				System.out.println("----- Quantil " + quantiles[i].getAlpha() + ": " + quantiles[i].getValue());
			}

			float z = 48;
			float meanAbsoluteDeviation = LogicHandler.getMeanAbsoluteDeviation(dataHandler.getList(), classMiddles,
					relativeOccurences, z);
			System.out.println("--- Mittlere arithmetische Abweichung für z = " + z + ": " + meanAbsoluteDeviation);

			float variance = LogicHandler.getVariance(dataHandler.getList(), classMiddles, arithmeticMiddle,
					dataHandler.getSampleSize());
			System.out.println("--- Varianz: " + variance);

			float standardDeviation = LogicHandler.getStandardDeviation(variance);
			System.out.println("--- Standardabweichung: " + standardDeviation);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// ######################TEST###################

	}

}
