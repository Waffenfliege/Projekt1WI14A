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

import view.MainFrame;

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
			//########################## MARKERS ###########################
			//X-AXIS Regular Markers
			g2.setStroke(new BasicStroke(1));
			int markerStep = chartWidth/11;
			for(int i=1; i<11;i++){
				int x = origin.getPosX()+ markerStep*i;
				int y = origin.getPosY()+chartHeight-6;
				int xTarget =   origin.getPosX()+ markerStep*i;
				int yTarget  =origin.getPosY()+chartHeight+6;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//X-AXIS Markers
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
			markerStep = chartHeight/11;
			for(int i=1; i<11;i++){
				int x = origin.getPosX()-6;
				int y = origin.getPosY()+markerStep*i;
				int xTarget =   origin.getPosX()+6;
				int yTarget = origin.getPosY()+markerStep*i;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//Y-AXIS Column Markers
			g2.setStroke(new BasicStroke(3));
			for(int i=0; i<rectangles.size();i++){
				int x = origin.getPosX()-12;
				int y = origin.getPosY()+positions.get(i).getPosY();
				int xTarget =  origin.getPosX()+12;
				int yTarget =  origin.getPosY()+ positions.get(i).getPosY();
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//########################## LABELS ###########################
			//Column LABELS
			for(int i=0; i<rectangles.size();i++){
				String labelString = "K " + (i+1);
				int stringWidth = g2.getFontMetrics().stringWidth(labelString);
				int x = origin.getPosX()+positions.get(i).getPosX()+rectangles.get(i).getWidth()/2-stringWidth/2;
				int y = origin.getPosY()+positions.get(i).getPosY()+rectangles.get(i).getHeight()/2;
				
				System.out.println("Y: " + y);
				if(	rectangles.get(i).getHeight()<30){
					y = origin.getPosY()+positions.get(i).getPosY() - 20;
				}

				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Calibri", Font.BOLD, 16));
				g2.drawString("K " + (i+1), x, y);
			}
			
			//X-AXIS - Label
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Calibri", Font.BOLD, 16));
			g2.drawString("Klassenbreite ", origin.getPosX()+chartWidth-50, origin.getPosY()+chartHeight+30);
			
			
			//Y-AXIS - Label
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Calibri", Font.BOLD, 16));
			g2.drawString("r(H) ", origin.getPosX()-35, origin.getPosY()+10);
			
			//X-AXIS- Regular Labels
			markerStep = chartWidth/11;
			for(int i=1; i<11;i++){
				String labelString = String.valueOf(markerStep*i);
				int stringWidth = g2.getFontMetrics().stringWidth(labelString);
				int x = origin.getPosX()+ markerStep*i-stringWidth/2;
				int y = origin.getPosY()+chartHeight+ 15;
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Calibri", Font.BOLD, 10));
				g2.drawString(String.valueOf(labelString), x, y);
			}
			
			//Y-AXIS- Regular Labels
			markerStep = chartHeight/11;
			for(int i=1; i<11;i++){
				String labelString = String.format("%.2f", GraphFactory.getMaxHeight(MainFrame.getDataHandler().getList(), MainFrame.getDataHandler().getSampleSize())/10 *i);
				int x = origin.getPosX()-35;
				int y = origin.getPosY()+chartHeight-markerStep*i;
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Calibri", Font.BOLD, 10));
				g2.drawString(String.valueOf(labelString), x, y);
			}
			
		}
		
		//Y-AXIS - LINES
		g2.setStroke(new BasicStroke(3));
		g2.setColor(labelColor);
		
		g2.drawLine(origin.getPosX(), origin.getPosY()+1, origin.getPosX()-3, origin.getPosY()+3);
		g2.drawLine(origin.getPosX(), origin.getPosY()+1, origin.getPosX()+3, origin.getPosY()+3);
		g2.drawLine(origin.getPosX(), origin.getPosY()+1, origin.getPosX(), origin.getPosY()+chartHeight);
		
	}
}
