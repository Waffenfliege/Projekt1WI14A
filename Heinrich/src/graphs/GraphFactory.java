package graphs;

import java.awt.Color;
import java.util.ArrayList;

import data.DataHandler;
import data.StatisticClass;
import logic.LogicHandler;

/**
 * Class that provides static methods to generate EmpiricDistributionPanel- and HistogramPanel-Objects
 * @author Mathias Engmann
 */
public class GraphFactory {

	private static final float GRAPH_BUFFER_FACTOR = 0.9f;
	private static int graphMaxWidth;
	private static int graphMaxHeight;
	
	private static final Color BAR_COLOR = new Color(175,23, 20);
	private static final Color BORDER_COLOR = Color.BLACK;


/**
 * Instantiates and returns a HistogramPanel-Object
 * @param data DataHandler to draw the class data from
 * @param positionX the x position of the origin within the panel
 * @param positionY the y position of the origin within the panel
 * @param panelWidth the total preferred width of the panel
 * @param panelHeight the total preferred height of the panel
 * @param chartWidth the total width of the chart
 * @param chartHeight the total height of the chart
 * @param isDetailed boolean. True if axis lables are to be painted
 * @return a HistoGramPanel-Object
 * @author Mathias Engmann
 */
	public static HistogramPanel createHistogram(DataHandler data, int positionX, int positionY, int panelWidth, int panelHeight, int chartWidth, int chartHeight, boolean isDetailed) {

		
		graphMaxWidth = (int)(chartWidth*GRAPH_BUFFER_FACTOR);
		graphMaxHeight = (int)(chartHeight*GRAPH_BUFFER_FACTOR);
		
		ArrayList<HistogramTupel> histogramData = generateHistogramData(data);
		int totalDataWidth = getTotalWidth(data.getList());
		float maxDataHeight = getMaxHeight(data.getList(), data.getSampleSize());
		ArrayList<Rectangle> rectangles = setUpRectangles(histogramData, totalDataWidth, maxDataHeight);
		ArrayList<Vector2D> positions= setUpRectanglePositions(rectangles, chartHeight);
		Vector2D origin = new Vector2D(positionX, panelHeight-chartHeight-positionY);
		HistogramPanel result = new HistogramPanel(rectangles, positions, origin, BORDER_COLOR, panelWidth, panelHeight, chartWidth, chartHeight, isDetailed);
		
		return result;
	}
	
	/**
	 * Sets up the Rectangles for the HistogramPanel
	 * @param histoGramData ArrayList of HistogramTupels to generate rectangles from
	 * @param totalDataWidth the sum of the width of all classes
	 * @param maxDataHeight the highest, particular relative share of all classes
	 * @return an ArrayList of Rectangles
	 * @author Mathias Engmann
	 */
	private static ArrayList<Rectangle> setUpRectangles(ArrayList<HistogramTupel> histoGramData, int totalDataWidth, float maxDataHeight){
		ArrayList<Rectangle> results = new ArrayList<Rectangle>();
		
		for(int i=0; i<histoGramData.size();i++){
			float currentWidth = graphMaxWidth * histoGramData.get(i).getWidth()/totalDataWidth;
			float currentHeight = graphMaxHeight*histoGramData.get(i).getHeight()/maxDataHeight;

			Rectangle currentRect = new Rectangle((int)currentWidth, (int)currentHeight, BAR_COLOR);
			results.add(currentRect);
		}
		
		return results;
		
	}
	
	/**
	 * Sets up the positions of the rectangles in a HisogramPanel
	 * @param rectangles ArrayList of rectangles to calculate the positions for
	 * @param height the height of the chart
	 * @return an ArrayList of Vector2D with all rectangle positions
	 * @author Mathias Engmann
	 */
	private static ArrayList<Vector2D> setUpRectanglePositions(ArrayList<Rectangle> rectangles, int height){
		ArrayList<Vector2D> results = new ArrayList<Vector2D>();
		
		for(int i=0; i<rectangles.size(); i++){
			
			int positionX = 0;
			for(int j=0; j<i; j++){
				positionX += rectangles.get(j).getWidth();
			}
			
			int positionY = height-rectangles.get(i).getHeight(); 
			Vector2D currentVector = new Vector2D(positionX, positionY);
			
			results.add(currentVector);
		}
		
		return results;
	
	}

	/**
	 * Instantiates and returns a EmpiricDistributionPanel-Object
	 *  * @param data DataHandler to draw the class data from
	 * @param positionX the x position of the origin within the panel
	 * @param positionY the y position of the origin within the panel
	 * @param panelWidth the total preferred width of the panel
	 * @param panelHeight the total preferred height of the panel
	 * @param chartWidth the total width of the chart
	 * @param chartHeight the total height of the chart
	 * @param isDetailed boolean. True if axis lables are to be painted
	 * @return a EmpiricDistributionPanel-Object
	 * @author Mathias Engmann
	 */
	public static EmpiricDistributionPanel createEmpiricDistribution(DataHandler data, int positionX, int positionY, int panelWidth, int panelHeight, int chartWidth, int chartHeight, boolean isDetailed) {

		
		graphMaxWidth = (int)(chartWidth*GRAPH_BUFFER_FACTOR);
		graphMaxHeight = (int)(chartHeight*GRAPH_BUFFER_FACTOR);
		
		ArrayList<EmpiricTupel> empiricGraphData =generateEmpiricData(data);
		int totalDataWidth = getTotalWidth(data.getList());
		ArrayList<EmpiricLine> lines = setUpLines(empiricGraphData, totalDataWidth);
		ArrayList<Vector2D> positions= setUpLinePositions(lines, empiricGraphData, chartHeight);
		Vector2D origin = new Vector2D(positionX, panelHeight-chartHeight-positionY);
		EmpiricDistributionPanel result = new EmpiricDistributionPanel(empiricGraphData, lines, positions, origin, BORDER_COLOR, panelWidth, panelHeight, chartWidth, chartHeight, isDetailed);
		
		return result;
	}

	/**
	 * Sets up the Lines for the empiric distribution panel
	 * @param empiricGraphData ArrayList of EmpiricTupel to generate lines from
	 * @param totalDataWidth the sum of the width of all classes
	 * @return an ArrayList of EmpiricLines
	 * @author Mathias Engmann
	 */
	
	private static ArrayList<EmpiricLine> setUpLines(ArrayList<EmpiricTupel> empiricGraphData, int totalDataWidth){
		ArrayList<EmpiricLine> results = new ArrayList<EmpiricLine>();
	
		for(int i=0; i<empiricGraphData.size();i++){
			float currentWidth = graphMaxWidth * empiricGraphData.get(i).getWidth()/totalDataWidth;
	
			EmpiricLine currentLine = new EmpiricLine((int)currentWidth, BAR_COLOR);
			results.add(currentLine);
		}
	
		return results;
	}
	
	/**
	 * Sets up the positions of the rectangles in a HisogramPanel
	 * @param lines ArrayList of EmpiricLines to calculate the position for
	 * @param quotas ArrayList of EmpiricTupels to draw the relative shares from
	 * @param chartHeight the maximum height of the chart
	 * @return an ArrayList of Vector2D with all line positions
	 * @author Mathias Engmann
	 */
	private static ArrayList<Vector2D> setUpLinePositions(ArrayList<EmpiricLine> lines, ArrayList<EmpiricTupel> quotas, int chartHeight)
	{
		ArrayList<Vector2D> results = new ArrayList<Vector2D>();
		
		for(int i=0; i<lines.size(); i++){
			
			int positionX = 0;
			for(int j=0; j<i; j++){
				positionX += lines.get(j).getLength();
			}
			
			int positionY = (int)(chartHeight-chartHeight*0.9f*quotas.get(i).getSummedQuote()); 
			Vector2D currentVector = new Vector2D(positionX, positionY);
			
			results.add(currentVector);
		}
		
		return results;
	}
	
	/**
	 * Calculates the sum of the widths of given statistical classes
	 * @param classes ArrayList of StatisticClasses
	 * @return int designating the total width of all classes
	 * @author Mathias Engmann
	 */
	
	public static int getTotalWidth(ArrayList<StatisticClass> classes){
		int result = 0;
		
		for(int i=0; i<classes.size(); i++){
			result+=classes.get(i).getUpperValue().value-classes.get(i).getLowerValue().value;
		}
		
		return result;
	}

	/**
	 * Finds the class with the biggest relative share of given statistical classes
	 * @param classes ArrayList of StatisticClasses to find the target in
	 * @param sampleSize the numbers of samples that were stored in those classes
	 * @return the highest relative share of any given class
	 * @author Mathias Engmann
	 */
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
	
	/**
	 * Calcultes and stores the mathematical data needed for an element to be drawn in the HistogramPanel
	 * @param data DataHandler to draw the classes from
	 * @return ArrayList of HistogramTupels to calculate positions and rectangles from
	 * @author Mathias Engmann
	 */
	public static ArrayList<HistogramTupel> generateHistogramData(DataHandler data) {
		ArrayList<HistogramTupel> results = new ArrayList<HistogramTupel>();
		
		for(int i=0;i<data.getList().size();i++){
			HistogramTupel currentTupel = new HistogramTupel(data.getResults().getRelativeOccurences()[i], data.getList().get(i).getUpperValue().value-data.getList().get(i).getLowerValue().value);
			results.add(currentTupel);
		}
		
		return results;
	}

	/**
	 * Calcultes and stores the mathematical data needed for an element to be drawn in the EmpiricDistributionPanel
	 * @param data DataHandler to draw the classes from
	 * @return ArrayList of EmpiricTupels to calculate positions and lines from
	 * @author Mathias Engmann
	 */
	public static ArrayList<EmpiricTupel> generateEmpiricData(DataHandler data)
	{
		ArrayList<EmpiricTupel> results = new ArrayList<EmpiricTupel>();
		
		float currentQuote = 0.0f;
		for(int i=0;i<data.getList().size();i++){
			currentQuote += data.getResults().getRelativeOccurences()[i];
			EmpiricTupel currentTupel = new EmpiricTupel(currentQuote, data.getList().get(i).getUpperValue().value-data.getList().get(i).getLowerValue().value);
			results.add(currentTupel);
		}
		
		return results;
	}
}
