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

public class EmpiricDistributionPanel extends JPanel{

	ArrayList<EmpiricLine> lines;
	ArrayList<Vector2D>positions;
	Vector2D origin;
	Color labelColor;
	Color chartBackgroundColor;
	boolean isDetailed = false;

	int chartWidth;
	int chartHeight;
	public EmpiricDistributionPanel(ArrayList<EmpiricLine> lines, ArrayList<Vector2D>positions, Vector2D origin, Color labelColor, int width, int height, int chartWidth, int chartHeight, boolean detail){
		super();

		this.lines = lines;
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
	public void paintComponent(Graphics  g){
		super.paintComponent(g);
		//Filled Rects
		g.setColor(chartBackgroundColor);
		g.fillRect(origin.getPosX(), origin.getPosY()+1, chartWidth, chartHeight);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//Lines
		for(int i=0; i<lines.size();i++){
			
			g2.setStroke(new BasicStroke(3));
			g2.setColor(lines.get(i).getColor()); 
			//TODO
			int x = origin.getPosX()+positions.get(i).getPosX();
			int y = origin.getPosY()+positions.get(i).getPosY();
			int xTarget = origin.getPosX()+positions.get(i).getPosX()+lines.get(i).getLength();
			int yTarget  = origin.getPosY()+positions.get(i).getPosY();
			g2.drawLine(x, y, xTarget, yTarget);
		}
		
		//Dots
		g2.setStroke(new BasicStroke(3));
		for(int i=0; i<lines.size();i++){	
			g2.setColor(labelColor); 

			int x = origin.getPosX()+positions.get(i).getPosX()-4;
			int y = origin.getPosY()+positions.get(i).getPosY()-4;
			g2.drawOval(x, y, 8, 8);
		}
		
		//X-AXIS - LINES
		
		g2.setStroke(new BasicStroke(3));
		g2.setColor(labelColor);
		
		g2.drawLine(origin.getPosX()+chartWidth, origin.getPosY()+chartHeight, origin.getPosX()+chartWidth-3, origin.getPosY()+chartHeight+3);
		g2.drawLine(origin.getPosX()+chartWidth, origin.getPosY()+chartHeight, origin.getPosX()+chartWidth-3, origin.getPosY()+chartHeight-3);
		g2.drawLine(origin.getPosX(), origin.getPosY()+chartHeight, origin.getPosX()+chartWidth, origin.getPosY()+chartHeight);
	
		if(isDetailed){
			//########################## MARKERS ###########################
			//X-AXIS Regular Markers
			g2.setStroke(new BasicStroke(1));
			float markerStep = chartWidth*0.9f/10.0f;
			for(int i=1; i<=10;i++){
				int x = origin.getPosX()+ (int)(markerStep*i);
				int y = origin.getPosY()+chartHeight-6;
				int xTarget =   origin.getPosX()+ (int)(markerStep*i);
				int yTarget  =origin.getPosY()+chartHeight+6;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//X-AXIS Markers
			g2.setStroke(new BasicStroke(3));
			for(int i=0; i<lines.size();i++){
				int x = origin.getPosX()+positions.get(i).getPosX()+lines.get(i).getLength();
				int y = origin.getPosY()+ chartHeight -12;
				int xTarget =  origin.getPosX()+positions.get(i).getPosX()+lines.get(i).getLength();
				int yTarget  =origin.getPosY()+chartHeight+12;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//Y-AXIS Regular Markers
			g2.setStroke(new BasicStroke(1));
			markerStep = chartHeight*0.9f/10.0f ;
			for(int i=1; i<=10;i++){
				int x = origin.getPosX()-6;
				int y = origin.getPosY()+(int)(markerStep*i);
				int xTarget =   origin.getPosX()+6;
				int yTarget = origin.getPosY()+(int)(markerStep*i);
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//########################## LABELS ###########################
			//Line LABELS
			for(int i=0; i<lines.size();i++){
				String labelString = "K " + (i+1);
				int stringWidth = g2.getFontMetrics().stringWidth(labelString);
				int x = origin.getPosX()+positions.get(i).getPosX()+lines.get(i).getLength()/2-stringWidth/2;
				int y = origin.getPosY()+positions.get(i).getPosY()-20;
				
				System.out.println("Y: " + y);
				if(y<60){
					y = 75;
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
			g2.drawString("R(H) ", origin.getPosX()-35, origin.getPosY()+10);
			
			//X-AXIS- Regular Labels
			float markerValueStep = MainFrame.getDataHandler().getHighestValue()/11;
			markerStep = chartWidth*0.9f/10.0f;
			for(int i=1; i<=10;i++){
				String labelString = String.format("%.2f",MainFrame.getDataHandler().getLowestValue()+markerValueStep*i);
				int stringWidth = g2.getFontMetrics().stringWidth(labelString);
				int x = origin.getPosX()+ (int)(markerStep*i)-stringWidth/2;
				int y = origin.getPosY()+chartHeight+ 15;
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Calibri", Font.BOLD, 10));
				g2.drawString(labelString, x, y);
			}
			
			//Y-AXIS- Regular Labels
			for(int i=1; i<=10;i++){
				String labelString = String.format("%.2f", 1.0f/10*i);
				int x = origin.getPosX()-35;
				int y = origin.getPosY()+chartHeight-chartHeight/11*i;
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
