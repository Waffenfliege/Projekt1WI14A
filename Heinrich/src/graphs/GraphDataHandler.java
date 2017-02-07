package graphs;

import java.util.ArrayList;

import data.DataHandler;
import data.StatisticClass;
import logic.LogicHandler;

public class GraphDataHandler {
	
	public GraphDataHandler(){
	}
	
	public static int getTotalWidth(ArrayList<StatisticClass> classes){
		int result = 0;
		
		for(int i=0; i<classes.size(); i++){
			result+=classes.get(i).getUpperValue().value-classes.get(i).getLowerValue().value;
		}
		
		return result;
	}

	public static float getMaxHeight(ArrayList<StatisticClass> classes, int sampleSize) {
		float value = 0;
		float heights[] = LogicHandler.getRelativeOccurences(classes, sampleSize);
		for(int i=0; i<classes.size(); i++){
			if(heights[i]>value){
				value = heights[i];
			}
		}
		return value;
	}

	public static ArrayList<HistogramTupel> generateHistogramData(DataHandler data) {
		ArrayList<HistogramTupel> results = new ArrayList<HistogramTupel>();
		
		for(int i=0;i<data.getList().size();i++){
			HistogramTupel currentTupel = new HistogramTupel(data.getResults().getRelativeOccurences()[i], data.getList().get(i).getUpperValue().value-data.getList().get(i).getLowerValue().value);
			results.add(currentTupel);
		}
		
		return results;
	}

	public static ArrayList<EmpiricTupel> generateEmpiricData(DataHandler data)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
