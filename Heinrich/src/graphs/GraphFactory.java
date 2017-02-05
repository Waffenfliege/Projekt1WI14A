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


public class GraphFactory {

	private static final float GRAPH_BUFFER_FACTOR = 0.9f;
	private static int graphMaxWidth;
	private static int graphMaxHeight;
	
	private static final Color BAR_COLOR = Color.RED;
	private static final Color BORDER_COLOR = Color.BLACK;
	private static Vector2D origin;


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
	public static HistogramPanel createHistogram(DataHandler data, int positionX, int positionY, int panelWidth, int panelHeight, int chartWidth, int chartHeight) {

		
		graphMaxWidth = (int)(chartWidth*GRAPH_BUFFER_FACTOR);
		graphMaxHeight = (int)(chartHeight*GRAPH_BUFFER_FACTOR);
		
		ArrayList<HistogramTupel> histogramData = HistogramDataHandler.generateHistogramData(data);
		int totalDataWidth = HistogramDataHandler.getTotalWidth(data.getList());
		float maxDataHeight = HistogramDataHandler.getMaxHeight(data.getList(), data.getSampleSize());
		int classCount = data.getClassCount();
		ArrayList<Rectangle> rectangles = setUpRectangles(histogramData, totalDataWidth, maxDataHeight);
		ArrayList<Vector2D> positions= setUpPositions(rectangles, chartHeight);
		//Vector2D origin = new Vector2D(positionX, positionY);
		Vector2D origin = new Vector2D(positionX, panelHeight-chartHeight-positionY);
		HistogramPanel result = new HistogramPanel(rectangles, positions, origin, BORDER_COLOR, panelWidth, panelHeight, chartWidth, chartHeight);
		
		return result;
	}
	
	private static ArrayList<Rectangle> setUpRectangles(ArrayList<HistogramTupel> data, int totalDataWidth, float maxDataHeight){
		ArrayList<Rectangle> results = new ArrayList<Rectangle>();
		
		for(int i=0; i<data.size();i++){
			float currentWidth = graphMaxWidth * data.get(i).getWidth()/totalDataWidth;
			float currentHeight = graphMaxHeight*data.get(i).getHeight()/maxDataHeight;

			Rectangle currentRect = new Rectangle((int)currentWidth, (int)currentHeight, BAR_COLOR);
			results.add(currentRect);
		}
		
		return results;
		
	}
	
	private static ArrayList<Vector2D> setUpPositions(ArrayList<Rectangle> rectangles, int height){
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

	public static EmpiricDistributionPanel createEmpiricDistribution(DataHandler data, int positionX, int positionY, int panelWidth, int panelHeight, int chartWidth, int chartHeight) {

		
		graphMaxWidth = (int)(chartWidth*GRAPH_BUFFER_FACTOR);
		graphMaxHeight = (int)(chartHeight*GRAPH_BUFFER_FACTOR);
		
		ArrayList<HistogramTupel> histogramData = HistogramDataHandler.generateHistogramData(data);
		int totalDataWidth = HistogramDataHandler.getTotalWidth(data.getList());
		float maxDataHeight = HistogramDataHandler.getMaxHeight(data.getList(), data.getSampleSize());
		int classCount = data.getClassCount();
		ArrayList<Rectangle> rectangles = setUpRectangles(histogramData, totalDataWidth, maxDataHeight);
		ArrayList<Vector2D> positions= setUpPositions(rectangles, chartHeight);
		//Vector2D origin = new Vector2D(positionX, positionY);
		Vector2D origin = new Vector2D(positionX, panelHeight-chartHeight-positionY);
		EmpiricDistributionPanel result = new EmpiricDistributionPanel(rectangles, positions, origin, BORDER_COLOR, panelWidth, panelHeight, chartWidth, chartHeight);
		
		return result;
	}
}
