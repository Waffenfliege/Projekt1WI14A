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
 * @author Robert Bilger, Jonathan Klopfer, Jan Sauerland, Lukas Moser, Mathias Engmann
 *
 */
public class Program
{
	/**
	 * 
	 * @param args arguments to start the program with. Not used
	 */
	public static void main(String args[])
	{
		MainFrame frame = new MainFrame();
		frame.startFrame();
		
		try
		{
			frame.getDataHandler().receiveData(new StatisticClassValue(35, ClampType.EXCLUSIVE), new StatisticClassValue(37, ClampType.INCLUSIVE), 1, 0);
			frame.getDataHandler().receiveData(new StatisticClassValue(37, ClampType.EXCLUSIVE), new StatisticClassValue(40, ClampType.INCLUSIVE), 3, 1);
			frame.getDataHandler().receiveData(new StatisticClassValue(40, ClampType.EXCLUSIVE), new StatisticClassValue(43, ClampType.INCLUSIVE), 2, 2);
			frame.getDataHandler().receiveData(new StatisticClassValue(43, ClampType.EXCLUSIVE), new StatisticClassValue(48, ClampType.INCLUSIVE), 1, 3);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
