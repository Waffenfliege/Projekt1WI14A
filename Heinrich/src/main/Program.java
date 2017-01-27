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
		
		float median = LogicHandler.median(TestDataHandler.getAllClasses());
		System.out.println(x);
	}

}
