package graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import data.DataHandler;
import data.ResultSet;
import data.StatisticClass;
import logic.LogicHandler;


public class GraphFactory {

	private static final float GRAPH_BUFFER_FACTOR = 0.9f;
	private static int graphMaxWidth;
	private static int graphMaxHeight;
	
	private static final Color BAR_COLOR = new Color(175,23, 20);
	private static final Color BORDER_COLOR = Color.BLACK;


	/**
	 * 
	 * @param dataHandler
	 * @param positionX 
	 * @param positionY
	 * @param width
	 * @param height
	 * @param horizontalPadding
	 * @param verticalPadding
	 * @return
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

	public static EmpiricDistributionPanel createEmpiricDistribution(DataHandler data, int positionX, int positionY, int panelWidth, int panelHeight, int chartWidth, int chartHeight, boolean isDetailed) {

		
		graphMaxWidth = (int)(chartWidth*GRAPH_BUFFER_FACTOR);
		graphMaxHeight = (int)(chartHeight*GRAPH_BUFFER_FACTOR);
		
		ArrayList<EmpiricTupel> empiricGraphData =generateEmpiricData(data);
		int totalDataWidth = getTotalWidth(data.getList());
		ArrayList<EmpiricLine> lines = setUpLines(empiricGraphData, totalDataWidth);
		ArrayList<Vector2D> positions= setUpLinePositions(lines, empiricGraphData, chartHeight);
		Vector2D origin = new Vector2D(positionX, panelHeight-chartHeight-positionY);
		EmpiricDistributionPanel result = new EmpiricDistributionPanel(lines, positions, origin, BORDER_COLOR, panelWidth, panelHeight, chartWidth, chartHeight, isDetailed);
		
		return result;
	}

	private static ArrayList<EmpiricLine> setUpLines(ArrayList<EmpiricTupel> empiricGraphData, int totalDataWidth){
		ArrayList<EmpiricLine> results = new ArrayList<EmpiricLine>();
	
		for(int i=0; i<empiricGraphData.size();i++){
			float currentWidth = graphMaxWidth * empiricGraphData.get(i).getWidth()/totalDataWidth;
	
			EmpiricLine currentLine = new EmpiricLine((int)currentWidth, BAR_COLOR);
			results.add(currentLine);
		}
	
		return results;
	}
	
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
