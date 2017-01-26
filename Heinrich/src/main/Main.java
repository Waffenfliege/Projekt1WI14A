package main;

import main.data.DataHandler;
import main.logic.LogicHandler;
import main.view.ViewHandler;

public class Main
{
	private static DataHandler dataHandler;
	private static LogicHandler logicHandler;
	private static ViewHandler viewHandler;

	public static void main(String[] args)
	{
		dataHandler = new DataHandler();
		logicHandler = new LogicHandler();
		viewHandler = new ViewHandler();
	}

}
