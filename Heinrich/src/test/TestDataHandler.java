package test;

import data.ClampType;
import data.DataHandler;
import data.StatisticClass;
import data.StatisticClassValue;

public class TestDataHandler extends DataHandler{

	public TestDataHandler(){
		super();
		
		put(new StatisticClassValue(35,ClampType.INCLUSIVE), new StatisticClassValue(37,ClampType.EXCLUSIVE),1);
		put(new StatisticClassValue(37,ClampType.INCLUSIVE), new StatisticClassValue(40,ClampType.EXCLUSIVE),3);
		put(new StatisticClassValue(40,ClampType.INCLUSIVE), new StatisticClassValue(43,ClampType.EXCLUSIVE),2);
		put(new StatisticClassValue(43,ClampType.INCLUSIVE), new StatisticClassValue(48,ClampType.EXCLUSIVE),1);
	
	}
}
