package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

public class HistogramPanel extends JPanel{

	ArrayList<Rectangle> rectangles;
	ArrayList<Vector2D>positions;
	Vector2D origin;
	Color labelColor;
	Color chartBackgroundColor;
	boolean isDetailed = false;

	int chartWidth;
	int chartHeight;
	public HistogramPanel(ArrayList<Rectangle> rectangles, ArrayList<Vector2D>positions, Vector2D origin, Color labelColor, int width, int height, int chartWidth, int chartHeight, boolean detail){
		super();
		this.rectangles = rectangles;
		this.positions = positions;	
		this.origin= origin;
		this.labelColor = labelColor;
		this.chartBackgroundColor = Color.LIGHT_GRAY;
		setPreferredSize(new Dimension(width, height));
		this.chartWidth = chartWidth ;
		this.chartHeight = chartHeight;
		this.isDetailed  = detail;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Filled Rects
		g.setColor(chartBackgroundColor);
		g.fillRect(origin.getPosX(), origin.getPosY()+1, chartWidth, chartHeight);
		
		for(int i=0; i<rectangles.size();i++){
			
			g.setColor(rectangles.get(i).getColor()); 
			int x = positions.get(i).getPosX();
			int y = positions.get(i).getPosY();
			int width = rectangles.get(i).getWidth();
			int height  = rectangles.get(i).getHeight();
			g.fillRect(x+origin.getPosX(), y+origin.getPosY(), width, height);
		}
		
		//Border rects
		g.setColor(labelColor); 
		for(int i=0; i<rectangles.size();i++){
			int x = positions.get(i).getPosX();
			int y = positions.get(i).getPosY();
			int width = rectangles.get(i).getWidth();
			int height  = rectangles.get(i).getHeight();
			g.drawRect(x+origin.getPosX(), y+origin.getPosY(), width, height);
		}
		
		System.out.println("Origin x: " + origin.getPosX() + " | y: " + origin.getPosY());
		
		//X-AXIS - LINES
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(labelColor);
		
		g2.drawLine(origin.getPosX()+chartWidth, origin.getPosY()+chartHeight, origin.getPosX()+chartWidth-3, origin.getPosY()+chartHeight+3);
		g2.drawLine(origin.getPosX()+chartWidth, origin.getPosY()+chartHeight, origin.getPosX()+chartWidth-3, origin.getPosY()+chartHeight-3);
		g2.drawLine(origin.getPosX(), origin.getPosY()+chartHeight, origin.getPosX()+chartWidth, origin.getPosY()+chartHeight);
	
		if(isDetailed){
			//X-AXIS Regular Markers
			g2.setStroke(new BasicStroke(1));
			int markerStep = chartWidth/10;
			for(int i=1; i<10;i++){
				int x = origin.getPosX()+ markerStep*i;
				int y = origin.getPosY()+chartHeight-6;
				int xTarget =   origin.getPosX()+ markerStep*i;
				int yTarget  =origin.getPosY()+chartHeight+6;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//X-AXIS Borders
			g2.setStroke(new BasicStroke(3));
			for(int i=0; i<rectangles.size();i++){
				int x = origin.getPosX()+positions.get(i).getPosX()+rectangles.get(i).getWidth();
				int y = origin.getPosY()+ positions.get(i).getPosY()+rectangles.get(i).getHeight()-12;
				int xTarget =  origin.getPosX()+positions.get(i).getPosX()+rectangles.get(i).getWidth();
				int yTarget  =origin.getPosY()+positions.get(i).getPosY()+rectangles.get(i).getHeight()+12;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//Y-AXIS Regular Markers
			g2.setStroke(new BasicStroke(1));
			markerStep = chartHeight/10;
			for(int i=1; i<10;i++){
				int x = origin.getPosX()-6;
				int y = origin.getPosY()+markerStep*i;
				int xTarget =   origin.getPosX()+6;
				int yTarget = origin.getPosY()+markerStep*i;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//Y-AXIS Column Borders
			g2.setStroke(new BasicStroke(3));
			for(int i=0; i<rectangles.size();i++){
				int x = origin.getPosX()-12;
				int y = origin.getPosY()+positions.get(i).getPosY();
				int xTarget =  origin.getPosX()+12;
				int yTarget =  origin.getPosY()+ positions.get(i).getPosY();
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//Column Labels
			for(int i=0; i<rectangles.size();i++){
				int x = positions.get(i).getPosX()+rectangles.get(i).getWidth()/2;
				int y = positions.get(i).getPosY()+rectangles.get(i).getHeight()/2;
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Calibri", Font.BOLD, 16));
				g2.drawString("Klasse " + (i+1), x, y);
			}
			
			
			//X-AXIS - Labels
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Calibri", Font.BOLD, 16));
			g2.drawString("r(H) ", origin.getPosX()-35, origin.getPosY()+10);
			
			//Regular Labels
			for(int i=0; i<rectangles.size();i++){
				int x = origin.getPosX()-12;
				int y = origin.getPosY()+positions.get(i).getPosY();
				g2.drawLine(x, y, xTarget, yTarget);
				g2.setFont(new Font("Calibri", Font.BOLD, 10));
				g2.drawString("Klasse " + (i+1), x, y);
				
			}
			//Column Labels
			
			//Y-AXIS - Labels
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Calibri", Font.BOLD, 16));
			g2.drawString("Klassenbreite ", origin.getPosX()+chartWidth-50, origin.getPosY()+chartHeight+25);
			
			//Regular Labels
			//Column Labels
		}
		
		//Y-AXIS - LINES
		g2.setStroke(new BasicStroke(3));
		g2.setColor(labelColor);
		
		g2.drawLine(origin.getPosX(), origin.getPosY()+1, origin.getPosX()-3, origin.getPosY()+3);
		g2.drawLine(origin.getPosX(), origin.getPosY()+1, origin.getPosX()+3, origin.getPosY()+3);
		g2.drawLine(origin.getPosX(), origin.getPosY()+1, origin.getPosX(), origin.getPosY()+chartHeight);
		
	}
}
