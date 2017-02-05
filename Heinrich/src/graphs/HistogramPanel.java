package graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class HistogramPanel extends JPanel{

	ArrayList<Rectangle> rectangles;
	ArrayList<Vector2D>positions;
	Vector2D origin;
	Color borderColor;
	Color chartBackgroundColor;
	Rectangle panelBorder;

	int chartWidth;
	int chartHeight;
	public HistogramPanel(ArrayList<Rectangle> rectangles, ArrayList<Vector2D>positions, Vector2D origin, Color borderColor, int width, int height, int chartWidth, int chartHeight){
		super();
		this.rectangles = rectangles;
		this.positions = positions;	
		this.origin= origin;
		this.borderColor = borderColor;
		this.chartBackgroundColor = Color.GRAY;
		panelBorder = new Rectangle(width, height, Color.BLUE);
		setPreferredSize(new Dimension(width, height));
		this.chartWidth = chartWidth ;
		this.chartHeight = chartHeight;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Filled Rects
		g.setColor(panelBorder.getColor());
		g.drawRect(0, 0, panelBorder.getWidth(), panelBorder.getHeight());
		
		g.setColor(chartBackgroundColor);
		g.fillRect(origin.getPosX(), origin.getPosY(), chartWidth, chartHeight);
		
		for(int i=0; i<rectangles.size();i++){
			
			g.setColor(rectangles.get(i).getColor()); 
			int x = positions.get(i).getPosX();
			int y = positions.get(i).getPosY();
			int width = rectangles.get(i).getWidth();
			int height  = rectangles.get(i).getHeight();
			g.fillRect(x+origin.getPosX(), y+origin.getPosY(), width, height);
		}
		
		//Border rects
		g.setColor(borderColor); 
		for(int i=0; i<rectangles.size();i++){
			int x = positions.get(i).getPosX();
			int y = positions.get(i).getPosY();
			int width = rectangles.get(i).getWidth();
			int height  = rectangles.get(i).getHeight();
			g.drawRect(x+origin.getPosX(), y+origin.getPosY(), width, height);
		}
		
		//COORDINATE AXIS'
		/*g.drawLine(origin.getPosX(), origin.getPosY(), GRAPH_MAX_WIDTH+GRAPH_WIDTH_BUFFER, -(GRAPH_MAX_HEIGHT+GRAPH_HEIGHT_BUFFER));*/
		//LABELS
		
	}
}
