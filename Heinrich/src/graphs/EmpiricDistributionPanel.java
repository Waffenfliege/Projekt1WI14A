package graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import view.MainFrame;

/**
 * @author Mathias Engmann
 */
public class EmpiricDistributionPanel extends JPanel{

	ArrayList<EmpiricLine> lines;
	ArrayList<EmpiricTupel> tupels;
	ArrayList<Vector2D>positions;
	Vector2D origin;
	Color labelColor;
	Color chartBackgroundColor;
	boolean isDetailed = false;

	int chartWidth;
	int chartHeight;
	
	/**
	 * Returns a JPanel extension that paints an empiric distribution graph
	 * @param tupels ArrayList of EmpiricTupels
	 * @param lines ArrayList of EmpiricLines to be painted
	 * @param positions ArrayList of Vector2D with the positions of the lines to be painted
	 * @param origin Vector2D designating the origin of the cartesian system
	 * @param labelColor AWT Color that all lables, the axis and frames are painted in
	 * @param width The total width of the JPanel
	 * @param height The total height of the JPanel
	 * @param chartWidth The width of the chart within the JPanel
	 * @param chartHeight The height of the chart within the JPanel
	 * @param detail boolean if axis lables are to be painted
	 * @author Mathias Engmann
	 */
	public EmpiricDistributionPanel(ArrayList<EmpiricTupel> tupels, ArrayList<EmpiricLine> lines, ArrayList<Vector2D>positions, Vector2D origin, Color labelColor, int width, int height, int chartWidth, int chartHeight, boolean detail){
		super();

		this.tupels = tupels;
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
	
	/**
	 * override of the paint method of the underlying JPanel
	 */
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
			
			//X-AXIS Markers
			g2.setStroke(new BasicStroke(3));
			for(int i=0; i<lines.size();i++){
				int x = origin.getPosX()+positions.get(i).getPosX()+lines.get(i).getLength();
				int y = origin.getPosY()+ chartHeight -12;
				int xTarget =  origin.getPosX()+positions.get(i).getPosX()+lines.get(i).getLength();
				int yTarget  =origin.getPosY()+chartHeight+12;
				g2.drawLine(x, y, xTarget, yTarget);
			}
			
			//Y-AXIS Markers
			g2.setStroke(new BasicStroke(3));
			for(int i=0; i<lines.size();i++){
				int x =  origin.getPosX()-12;
				int y =origin.getPosY()+positions.get(i).getPosY();
				int xTarget =   origin.getPosX()+12;
				int yTarget  =origin.getPosY()+positions.get(i).getPosY();
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
			g2.drawString("Klassenbreite ", origin.getPosX()+chartWidth-50, origin.getPosY()+chartHeight+25);
			
			
			//Y-AXIS - Label
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Calibri", Font.BOLD, 16));
			g2.drawString("R(H) ", origin.getPosX()-35, origin.getPosY()+10);
			
			//X-AXIS- Marker Labels
			String labelString  = String.format("%.2f",MainFrame.getDataHandler().getList().get(0).getLowerValue().value);
			int stringWidth = g2.getFontMetrics().stringWidth(labelString);
			int x = origin.getPosX()-stringWidth/2;
			int y = origin.getPosY()+chartHeight+ 25;
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Calibri", Font.BOLD, 16));
			g2.drawString(labelString, x, y);
			
			for(int i=0; i<lines.size();i++){
				labelString = String.format("%.2f",MainFrame.getDataHandler().getList().get(i).getUpperValue().value);
				stringWidth = g2.getFontMetrics().stringWidth(labelString);
				x = origin.getPosX()+positions.get(i).getPosX()+lines.get(i).getLength()-stringWidth/2;
				y = origin.getPosY()+chartHeight+ 25;
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Calibri", Font.BOLD, 16));
				g2.drawString(labelString, x, y);
			}

			//Y-AXIS- Marker Labels
			labelString  = "0.00";
			stringWidth = g2.getFontMetrics().stringWidth(labelString);
			x = origin.getPosX()-15-stringWidth;
			y = origin.getPosY()+chartHeight;
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Calibri", Font.BOLD, 16));
			g2.drawString(labelString, x, y);
			
			for(int i=0; i<lines.size();i++){
				labelString = String.format("%.2f",tupels.get(i).getSummedQuote());
				stringWidth = g2.getFontMetrics().stringWidth(labelString);
				x = origin.getPosX()-15-stringWidth;
				y =origin.getPosY()+positions.get(i).getPosY();
				g2.setColor(Color.BLACK);
				g2.setFont(new Font("Calibri", Font.BOLD, 16));
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
