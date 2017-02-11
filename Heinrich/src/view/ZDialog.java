package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

/**
 * 
 * Manages a dialog to ask the user for a "variable z" to continue calculation.
 * 
 * @author Jan Sauerland, Mathias Engmann
 *
 */
@SuppressWarnings("serial")
public class ZDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private static JComboBox zInputField;
	private JLabel zHeaderLabel, zInputLabel, zWarningLabel;
	private JPanel zInputPanel, buttonContainer;
	private JButton continueButton, cancelButton;
	private final static Font NORMAL = new Font("Calibri", Font.BOLD, 16);
	private static DataHandler dataHandler;

	/**
	 * Constructor for a new Dialog that asks for a "variable z".
	 * 
	 * User can either choose from one of the predefined values or put in his
	 * own value.
	 */
	public ZDialog()
	{
		dataHandler = MainFrame.getDataHandler();
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);

		setTitle("Eingabemaske Variable z");
		setResizable(false);
		setModal(true);
		setBounds(500, 300, 350, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));

		zHeaderLabel = new JLabel("Variable Z festlegen:");
		zHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		zHeaderLabel.setFont(NORMAL);
		contentPanel.add(zHeaderLabel);

		zInputPanel = new JPanel();
		// FlowLayout flowLayout = (FlowLayout) zInputPanel.getLayout();
		// flowLayout.setVgap(10);
		// flowLayout.setHgap(15);
		zInputPanel.setLayout(new BorderLayout(15, 10));
		contentPanel.add(zInputPanel);

		zInputLabel = new JLabel("z  =");
		zInputLabel.setFont(NORMAL);
		zInputPanel.add(zInputLabel, BorderLayout.WEST);

		String proposedZValues[] = { String.format("Median: %.3f", dataHandler.getResults().getMedian()),
				String.format("Arithmetisches Mittel: %.3f", dataHandler.getResults().getArithmeticMiddle()),
				String.format("Untere Grenze: %.3f", dataHandler.getLowestValue()),
				String.format("Obere Grenze: %.3f", dataHandler.getHighestValue()) };
		zInputField = new JComboBox(proposedZValues);
		zInputField.setMaximumSize(new Dimension(200, 20));
		zInputField.setMinimumSize(new Dimension(150, 20));
		zInputField.setEditable(true);
		zInputPanel.add(zInputField, BorderLayout.CENTER);

		zWarningLabel = new JLabel(" ");
		zWarningLabel.setForeground(MainFrame.getRed());
		zWarningLabel.setFont(NORMAL);
		zWarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
		zInputPanel.add(zWarningLabel, BorderLayout.SOUTH);

		buttonContainer = new JPanel();
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));

		continueButton = new JButton("Weiter");
		continueButton.setActionCommand("Weiter");
		continueButton.addActionListener(continueAction);
		cancelButton = new JButton("Abbrechen");
		cancelButton.setActionCommand("Abbrechen");
		cancelButton.addActionListener(cancelAction);
		buttonContainer.add(continueButton);
		buttonContainer.add(cancelButton);
		getContentPane().add(buttonContainer, BorderLayout.SOUTH);
	}

	private ActionListener continueAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			zWarningLabel.setText(" ");
			try
			{
				float selectedZ = 0;
				int selectedIndex = zInputField.getSelectedIndex();
				switch (selectedIndex)
				{
				case 0:
					selectedZ = dataHandler.getResults().getMedian();
					break;
				case 1:
					selectedZ = dataHandler.getResults().getArithmeticMiddle();
					break;
				case 2:
					selectedZ = dataHandler.getLowestValue();
					break;
				case 3:
					selectedZ = dataHandler.getHighestValue();
					break;
				case -1:
					try
					{
						selectedZ = Float.parseFloat(zInputField.getEditor().getItem().toString());
					} catch (NumberFormatException e)
					{
						throw new NumberFormatException();
					}
					break;
				}
				calculateResultsPostZ(selectedZ);
				dispose();
				MainFrame.switchToOutputPanel();
			} catch (NumberFormatException e)
			{
				zWarningLabel.setText("Keine gültige Zahl eingegeben!");
				zWarningLabel.setVisible(true);
			} catch (IllegalArgumentException e)
			{
				zWarningLabel.setText("Fehlerhafte Eingabe!");
				zWarningLabel.setVisible(true);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	};

	private ActionListener cancelAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			dispose();
		}
	};

	/**
	 * Calculate the rest of the results after the variable z was given by the
	 * user.
	 * 
	 * @param z
	 *            variable z that the user chose
	 * @throws IllegalArgumentException
	 *             ignored
	 * @throws Exception
	 *             generic Exception is ignored
	 */
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
		MainFrame.getDataHandler().getResults()
				.setGini(LogicHandler.getGiniCoefficient(classMiddles, LogicHandler.getClassMiddlesAdded(classMiddles),
						LogicHandler.getOrderedClassMiddles(classMiddles, LogicHandler.getRelativeOccurences(
								MainFrame.getDataHandler().getList(), MainFrame.getDataHandler().getSampleSize()))));
	}

}
