package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import data.DataHandler;
import logic.LogicHandler;

public class ZDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private static JComboBox zInputField;
	
	JLabel zHeaderLabel;
	JPanel zInputPanel;
	JLabel zInputLabel;
	float[] zValues;
	JPanel buttonContainer;
	JButton continueButton;
	private JLabel zWarningLabel;
	int chosenZValue;
	
	public ZDialog(){
		DataHandler dataHandler = MainFrame.getDataHandler();
		chosenZValue  = 0;
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		setTitle("Eingabemaske Variable z");
		setResizable(false);
		setModal(true);
		setBounds(500, 300, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		zHeaderLabel = new JLabel("Variable z (absolute Abweichung) festlegen:");
		zHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(zHeaderLabel);
		
		zInputPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) zInputPanel.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(10);
		contentPanel.add(zInputPanel);
	
		zInputLabel = new JLabel("z  =  ");
		zInputPanel.add(zInputLabel);
	
		String proposedZValues[] = {String.format("Median: %.3f", dataHandler.getResults().getMedian()), 
									String.format("Arithmetisches Mittel: %.3f", dataHandler.getResults().getArithmeticMiddle()),
									String.format("Untere Grenze: %.3f", dataHandler.getLowestValue()), 
									String.format("Obere Grenze: %.3f", dataHandler.getHighestValue())};
		zInputField = new JComboBox(proposedZValues);
		zInputPanel.add(zInputField);
	
		zWarningLabel = new JLabel("");
		zWarningLabel.setForeground(Color.RED);
		zInputPanel.add(zWarningLabel);

		buttonContainer = new JPanel();
		getContentPane().add(buttonContainer, BorderLayout.SOUTH);
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));
		
		continueButton = new JButton("Weiter");
		continueButton.setActionCommand("Weiter");
		continueButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				try
				{
					float selectedZ = dataHandler.getResults().getMedian();
					int selectedIndex = zInputField.getSelectedIndex();
					switch(selectedIndex){
					case 0:
						break;
						
					case 1:
						selectedZ = dataHandler.getResults().getArithmeticMiddle();
						break;
						
					case 2:
						selectedZ = dataHandler.getLowestValue();
						break;
						
					case 4:
						selectedZ = dataHandler.getHighestValue();
						break;
					}
					calculateResultsPostZ(selectedZ);
					dispose();
					MainFrame.switchToOutputPanel();
				} catch (NumberFormatException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		buttonContainer.add(continueButton);
	}
	
	public static void calculateResultsPostZ(float z) throws IllegalArgumentException, Exception
	{

		MainFrame.getDataHandler().getResults()
				.setQuantiles(LogicHandler.getQuantiles(MainFrame.getDataHandler().getList(),
						MainFrame.getDataHandler().getResults().getClassMiddles(),
						MainFrame.getDataHandler().getResults().getRelativeOccurences()));
		MainFrame.getDataHandler().getResults()
				.setMeanAbsoluteDeviation(LogicHandler.getMeanAbsoluteDeviation(MainFrame.getDataHandler().getList(),
						MainFrame.getDataHandler().getResults().getClassMiddles(),
						MainFrame.getDataHandler().getResults().getRelativeOccurences(), z));
		MainFrame.getDataHandler().getResults().setVariance(LogicHandler.getVariance(MainFrame.getDataHandler().getList(),
				MainFrame.getDataHandler().getResults().getClassMiddles(),
				MainFrame.getDataHandler().getResults().getArithmeticMiddle(), MainFrame.getDataHandler().getSampleSize()));
		MainFrame.getDataHandler().getResults()
				.setStandardDeviation(LogicHandler.getStandardDeviation(MainFrame.getDataHandler().getResults().getVariance()));
		float[] classMiddles = LogicHandler.getClassMiddles(MainFrame.getDataHandler().getList());
		MainFrame.getDataHandler().getResults().setGini(
														LogicHandler.getGiniCoefficient(
																classMiddles, 
																LogicHandler.getClassMiddlesAdded(classMiddles), 
																LogicHandler.getOrderedClassMiddles(classMiddles, 
																			LogicHandler.getRelativeOccurences(MainFrame.getDataHandler().getList(), 
																				MainFrame.getDataHandler().getSampleSize()))));
	}
	
}
