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
	}

}
