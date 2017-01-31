package test;

import data.ClampType;
import data.DataHandler;
import data.StatisticClass;
import data.StatisticClassValue;

public class TestDataHandler extends DataHandler{

	public TestDataHandler() throws Exception{
		super();
		
		receiveData(new StatisticClassValue(35,ClampType.INCLUSIVE), new StatisticClassValue(37,ClampType.EXCLUSIVE),1,0);
		getList().add(new StatisticClass(new StatisticClassValue(37,ClampType.INCLUSIVE), new StatisticClassValue(40,ClampType.EXCLUSIVE),3));
		getList().add(new StatisticClass(new StatisticClassValue(40,ClampType.INCLUSIVE), new StatisticClassValue(43,ClampType.EXCLUSIVE),2));
		getList().add(new StatisticClass(new StatisticClassValue(43,ClampType.INCLUSIVE), new StatisticClassValue(48,ClampType.EXCLUSIVE),1));
	
	}
}
