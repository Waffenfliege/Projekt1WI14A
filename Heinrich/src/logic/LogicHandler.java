package logic;

import java.util.ArrayList;

import data.StatisticClass;
import data.StatisticClassValue;
import data.ClampType;

//TODO Comments
/**
 * 
 * @author Robert, Mathias
 *
 */
public class LogicHandler
{
	/*
	 * x Klassenmitten x Median x Arithmetisches Mittel x Absolute Häufigkeit x
	 * Relative Häufigkeit Histogramm - Breite - Höhe Empirische
	 * Verteilungsfunktion Quantil Mittlere absolute Abweichung Varianz
	 * Standardabweichung Gini-Koeffizient
	 */

	// TODO ERRORHANDLING, KOMMENTARE,
	/**
	 * This method calculates the mean value of the given data per statistical class.
	 * 
	 * @param classes
	 *            ArrayList of statistical classes to calculate mean value from.
	 * @return Array of mean values
	 * @author Mathias Engmann
	 */
	public static float[] getClassMiddles(ArrayList<StatisticClass> classes)
	{
		float[] results = new float[classes.size()];

		for (int i = 0; i < classes.size(); i++)
		{
			float a = classes.get(i).getLowerValue().value;
			float b = classes.get(i).getUpperValue().value;
			float currentClassMiddle = (a + b) / 2;

			results[i] = currentClassMiddle;
		}

		return results;
	}


	/**
	 * This method calculates the relative occurrence of data points of a statistical class in relation to the other classes of the data record. 
	 * 
	 * @param classes
	 *            ArrayList of statistical classes to calculate relative occurrences from.
	 * @param sampleSize Complete number of data points in the record.
	 * @return Array of relative occurrences.
	 * @throws IllegalArgumentException Exception due to missing data points.
	 * @author Mathias Engmann
	 */
	public static float[] getRelativeOccurences(ArrayList<StatisticClass> classes, int sampleSize)
			throws IllegalArgumentException
	{
		float[] results = new float[classes.size()];

		if (sampleSize <= 0 || classes.size() <= 0)
		{

			throw new IllegalArgumentException("Die Stichprobengröße muss größer als 0 sein.");
		}

		else
		{
			for (int i = 0; i < classes.size(); i++)
			{
				results[i] = classes.get(i).getAbsoluteOccurences() / (float) sampleSize;
			}

			return results;
		}
	}

	// TODO KOMMENTARE (englisch); DOKU; ERRORHANDLING
	/**
	 * 
	 * @param classes
	 * @param classMiddles
	 * @param relativeOccurences
	 * @return
	 * @throws Exception
	 * @author Mathias Engmann
	 */
	public static float getMedian(ArrayList<StatisticClass> classes, float[] classMiddles, float[] relativeOccurences)
			throws Exception
	{
		// Braucht: Klassenmitten, relative Häufigkeiten

		if (classes.size() == 0 || classMiddles.length == 0 || relativeOccurences.length == 0)
		{
			throw new IllegalArgumentException("Die angegebenen Wertearrays sind nicht gefüllt.");
		}

		else
		{
			float currentRelativeShare = 0;
			float relativeShareBeforeHit = 0;

			int classIndexWithMedian = -1;

			for (int i = 0; i < classMiddles.length; i++)
			{

				currentRelativeShare += relativeOccurences[i];

				if (currentRelativeShare > 0.5f)
				{
					classIndexWithMedian = i;
					break;
				}

				else
				{
					relativeShareBeforeHit = currentRelativeShare;
				}

			}

			if (classIndexWithMedian == -1)
			{
				throw new Exception("Es konnte keine Klasse ermittelt werden, in dem der Median liegt");
			}

			float z1 = classes.get(classIndexWithMedian).getLowerValue().value;
			float z2 = classes.get(classIndexWithMedian).getUpperValue().value;

			float r1 = relativeShareBeforeHit;
			float r2 = currentRelativeShare;

			float result = z1 + ((0.5f - r1) / (r2 - r1)) * (z2 - z1);
			return result;

		}
	}

	// TODO Comments
	/**
	 * 
	 * @param classMiddles
	 * @param relativeOccurences
	 * @return
	 * @author Jonathan Klopfer
	 */
	public static float getArithmeticMiddle(float[] classMiddles, float[] relativeOccurences)
	{
		int classCount = classMiddles.length;
		float arithmeticMiddle = 0;

		for (int i = 0; i < classCount; i++)
		{
			arithmeticMiddle += (classMiddles[i] * relativeOccurences[i]);
		}

		return arithmeticMiddle;
	}

	// TODO Formel in FK (und Buch?) stimmt nicht. R2 dort ist R1+R2
	/**
	 * 
	 * @param classes
	 * @param classMiddles
	 * @param relativeOccurences
	 * @return
	 * @throws Exception
	 */
	public static Quantile[] getQuantiles(ArrayList<StatisticClass> classes, float[] classMiddles, float[] relativeOccurences)
			throws Exception
	{
		Quantile[] quantiles = new Quantile[6];
		quantiles[0] = new Quantile(-1f, 0.2f);
		quantiles[1] = new Quantile(-1f, 0.1f);
		quantiles[2] = new Quantile(-1f, 0.25f);
		quantiles[3] = new Quantile(-1f, 0.75f);
		quantiles[4] = new Quantile(-1f, 0.9f);
		quantiles[5] = new Quantile(-1f, 0.95f);

		// Für jedes gesuchte Quantil...
		for (int i = 0; i < quantiles.length; i++)
		{

			// ...zunächst die Klasse finden, in der sich das Quantil befindet
			// (analog wie beim Median)
			float currentAlpha = quantiles[i].getAlpha();

			float currentRelativeShare = 0;
			float relativeShareBeforeHit = 0;
			float threshholdRelativeShare = currentAlpha;

			int classIndexWithQuantile = -1;

			for (int j = 0; j < relativeOccurences.length; j++)
			{
				currentRelativeShare += relativeOccurences[j];

				if (currentRelativeShare >= threshholdRelativeShare)
				{
					classIndexWithQuantile = j;
					break;
				}

				else
				{
					relativeShareBeforeHit = currentRelativeShare;
				}
			}

			// Prüfung, ob eine Klasse gefunden wurde
			if (classIndexWithQuantile == -1)
			{
				throw new Exception(
						"Es konnte keine Klasse ermittelt werden, in dem das Quantil " + threshholdRelativeShare + " liegt");
			}

			// Quantil berechnen
			float z1 = classes.get(classIndexWithQuantile).getLowerValue().value;
			float z2 = classes.get(classIndexWithQuantile).getUpperValue().value;
			float r1 = relativeShareBeforeHit;
			float r2 = currentRelativeShare;
			float result = z1 + ((currentAlpha - r1) / (r2 - r1)) * (z2 - z1);

			quantiles[i].setValue(result);
		}

		return quantiles;
	}

	// TODO Fehler auf Seite 124 im Buch: 1/7 sollte in der Klasser stehen, vor
	// abs(36-35)
	/**
	 * 
	 * @param classes
	 * @param classMiddles
	 * @param relativeOccurences
	 * @param z
	 * @return
	 */
	public static float getMeanAbsoluteDeviation(ArrayList<StatisticClass> classes, float[] classMiddles,
			float[] relativeOccurences, float z)
	{
		// Braucht: Median, Mittelwert, Randwerte ??

		float sigmaResult = 0;
		float currentDeviation;

		for (int i = 0; i < classes.size(); i++)
		{
			currentDeviation = Math.abs(classMiddles[i] - z);
			sigmaResult += relativeOccurences[i] * currentDeviation;
		}

		float result = sigmaResult;
		return result;
	}

	// TODO DOKU; KOMMENTARE; FEHLERCATCHING
	/**
	 * 
	 * @param classes
	 * @param classMiddles
	 * @param arithmeticMiddle
	 * @param sampleSize
	 * @return
	 * @author Mathias Engmann
	 */
	public static float getVariance(ArrayList<StatisticClass> classes, float[] classMiddles, float arithmeticMiddle,
			int sampleSize)
	{
		float result;
		float sigmaResult = 0;

		for (int i = 0; i < classes.size(); i++)
		{

			float absOccurence = classes.get(i).getAbsoluteOccurences();
			float classMiddle = classMiddles[i];

			sigmaResult += absOccurence * Math.pow((classMiddle - arithmeticMiddle), 2);

		}

		result = sigmaResult / (sampleSize - 1);
		return result;
	}

	//TODO comments
	/**
	 * 
	 * @param variance
	 * @return
	 * @author Robert
	 */
	public static float getStandardDeviation(float variance)
	{
		float result = (float) Math.sqrt(variance);
		return result;
	}

	//TODO comments
	/**
	 * 
	 * @param classMiddles
	 * @param classMiddlesAdded
	 * @param orderedClassMiddles
	 * @return
	 * @author Mathias Engmann
	 */
	public static float getGiniCoefficient(float[] classMiddles, float classMiddlesAdded, float[][] orderedClassMiddles)
	{
		float size = classMiddles.length + 1;// Da die erste Zeile voller nuller
												// ist
		float[][] giniTable = new float[6][(int) size];
		float classMiddlesAddUp = 0;
		float lorenz = 0;
		float giniCoefficient;

		for (int i = 0; i < 6; i++)
		{ // Die erste Zeile der Tabelle beinhaltet immer ausschließlich nuller
			giniTable[i][0] = 0;
		}
		for (int i = 1; i < size; i++)
		{ // Die erste Spalte wird durchnummerriert -> m
			giniTable[0][i] = i;
		}
		for (int i = 1; i < size; i++)
		{ // Die zweite Spalte wird mit den Aufaddierten relativen Häufigkeiten
			// gefüllt ->u (von m)
			giniTable[1][i] = orderedClassMiddles[i - 1][1] + giniTable[1][i - 1];
		}
		for (int i = 1; i < size; i++)
		{ // Die dritte Spalte wird mit den Aufaddierten relativen Klassenmitten
			// gefüllt -> v (von m)
			classMiddlesAddUp = orderedClassMiddles[i - 1][0] + classMiddlesAddUp;
			giniTable[2][i] = classMiddlesAddUp / classMiddlesAdded;
		}
		for (int i = 1; i < size; i++)
		{ // -> u (m) - u (m-1)
			giniTable[3][i] = giniTable[1][i] - giniTable[1][i - 1];
		}
		for (int i = 1; i < size; i++)
		{ // -> v (m) + v (m-1)
			giniTable[4][i] = giniTable[2][i] + giniTable[2][i - 1];
		}
		for (int i = 1; i < size; i++)
		{ // -> (u (m) - u (m-1))*(v (m) + v (m-1))
			giniTable[5][i] = giniTable[3][i] * giniTable[4][i];
		}
		for (int i = 1; i < size; i++)
		{
			lorenz = giniTable[5][i] + lorenz;
		}

		giniCoefficient = 1 - lorenz;

		return giniCoefficient;
	}

	//TODO comments
	/**
	 * 
	 * @param classMiddles
	 * @return
	 * @author Robert
	 */
	public static float getClassMiddlesAdded(float[] classMiddles)
	{
		float classMiddlesAdded = 0;

		for (int i = 0; i < classMiddles.length; i++)
		{
			classMiddlesAdded = classMiddles[i] + classMiddlesAdded;
		}
		return classMiddlesAdded; // Gibt die Klassenmitten + vorherige
									// Klassenmitten aus.
	}

	//TODO comments
	/**
	 * 
	 * @param classMiddles
	 * @param relativeOccurences
	 * @return
	 * @author Robert
	 */
	public static float[][] getOrderedClassMiddles(float[] classMiddles, float[] relativeOccurences)
	{ // Reihenfolge 123 der Klassenmitten ist gleich wie von den H�ufigkeiten
		// (davon wird ausgegangen)
		float[][] orderedClassMiddles = new float[classMiddles.length][2];
		int counter = 0;
		float minValue = classMiddles[0];
		float minValueOccurence = relativeOccurences[0];
		float maxValue = getHighestClassMiddle(classMiddles); // h�chster Wert
																// der
																// classMiddles
		// Suche minimalen wert, store in float[i = 0][0], i+1, suche minimalen
		// wert usw, bis counter bei length-1 ist

		while (counter < classMiddles.length)
		{
			for (int i = 1; i < classMiddles.length; i++)
			{
				if (classMiddles[i] < minValue && classMiddles[i] > orderedClassMiddles[i - 1][0]) // Problem
																									// wenn
																									// orderedClassMiddles
																									// noch
																									// keinen
																									// Wert
																									// hat?
				{
					minValue = classMiddles[i];
					minValueOccurence = relativeOccurences[i];
				}
			}
			orderedClassMiddles[counter][0] = minValue;
			orderedClassMiddles[counter][1] = minValueOccurence;
			minValue = maxValue + 1;
			counter++;
		}

		return orderedClassMiddles;
	}

	//TODO Comments
	/**
	 * 
	 * @param classMiddles
	 * @return
	 * @author Robert
	 */
	public static float getHighestClassMiddle(float[] classMiddles)
	{
		float maxValue = classMiddles[0];

		for (int i = 1; i < classMiddles.length; i++)
		{
			if (classMiddles[i] > maxValue)
			{
				maxValue = classMiddles[i];
			}
		}
		return maxValue;
	}
}
