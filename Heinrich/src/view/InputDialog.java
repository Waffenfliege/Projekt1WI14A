package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.DataHandler;
import logic.LogicHandler;
import logic.Quantile;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class InputDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private static JTextField zInputField;
	private JLabel zWarningLabel;
	private static String chosenCommand = "";

	/**
	 * Launch the application.
	 */
	public static String startZDialog()
	{
		try
		{
			InputDialog dialog = new InputDialog(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			if (chosenCommand.equals("OK"))
			{
				dialog.dispose();
				System.out.println(zInputField.getText());
				return zInputField.getText();
			} else
			{
				dialog.dispose();
				System.out.println(chosenCommand);
				return chosenCommand;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}

	public static String startNewDialog()
	{
		try
		{
			InputDialog dialog = new InputDialog(false);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return chosenCommand;
	}

	/**
	 * Create the dialog.
	 */
	public InputDialog(boolean input)
	{
		super();
		// input = false;
		if (input)
		{
			setTitle("Eingabemaske Variable z");
			setResizable(false);
			setMinimumSize(new Dimension(400, 300));
			setMaximumSize(new Dimension(400, 300));
			setModal(true);
			setBounds(100, 100, 400, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
			{
				JLabel zHeaderLabel = new JLabel("Variable z festlegen:");
				zHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(zHeaderLabel);
			}
			{
				JPanel zInputPanel = new JPanel();
				FlowLayout flowLayout = (FlowLayout) zInputPanel.getLayout();
				flowLayout.setVgap(10);
				flowLayout.setHgap(10);
				contentPanel.add(zInputPanel);
				{
					JLabel zInputLabel = new JLabel("z  =  ");
					zInputPanel.add(zInputLabel);
				}
				{
					zInputField = new JTextField();
					zInputField.setMargin(new Insets(5, 5, 5, 5));
					zInputPanel.add(zInputField);
					zInputField.setColumns(6);
				}
				{
					zWarningLabel = new JLabel("");
					zWarningLabel.setForeground(Color.RED);
					zInputPanel.add(zWarningLabel);
				}
			}
			{
				JPanel buttonPane = new JPanel();
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));
				/*{
					JButton withoutZButton = new JButton("Ohne z fortfahren");
					withoutZButton.setActionCommand("Ohne z fortfahren");
					withoutZButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent actionEvent)
						{
							//setOutputPanelData(null);
							chosenCommand = "Ohne";
							dispose();
						}
					});
					buttonPane.add(withoutZButton);
					getRootPane().setDefaultButton(withoutZButton);
				}*/
				{
					JButton withZButton = new JButton("Weiter");
					withZButton.setActionCommand("Weiter");
					withZButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent actionEvent)
						{
							if (isValid(zInputField.getText()))
							{
								try {
									calculateResultsPostZ(Float.parseFloat(zInputField.getText()));
								} catch (Exception e) {
									e.printStackTrace();
								}
								chosenCommand = "OK";
								dispose();
								MainFrame.switchToOutputPanel();
							} else
							{
								zWarningLabel.setText("Fehlerhaft!");
							}
						}
					});
					buttonPane.add(withZButton);
				}
			}
		} else
		{
			setTitle("Neue Berechnung");
			setResizable(false);
			setMinimumSize(new Dimension(300, 200));
			setMaximumSize(new Dimension(300, 200));
			setModal(true);
			setBounds(100, 100, 300, 200);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			{
				JLabel zHeaderLabel = new JLabel("Wollen Sie wirklich fortfahren?");
				zHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
				contentPanel.add(zHeaderLabel);
			}
			{
				JPanel buttonPane = new JPanel();
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));
				{
					JButton okButton = new JButton("Ja");
					okButton.setActionCommand("Ja");
					okButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent actionEvent)
						{
							chosenCommand = "Ja";
							dispose();
						}
					});
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Nein");
					cancelButton.setActionCommand("Nein");
					cancelButton.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent actionEvent)
						{
							chosenCommand = "Nein";
							dispose();
						}
					});
					buttonPane.add(cancelButton);
				}
			}
		}
	}

	private boolean isValid(String input)
	{
		try
		{
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e)
		{
			return false;
		}
	}
	
	public static void calculateResultsPostZ(float z) throws IllegalArgumentException, Exception{

		MainFrame.getDataHandler().getResults().setQuantiles(LogicHandler.getQuantiles(MainFrame.getDataHandler().getList(), MainFrame.getDataHandler().getResults().getClassMiddles(), MainFrame.getDataHandler().getResults().getRelativeOccurences()));
		MainFrame.getDataHandler().getResults().setMeanAbsoluteDeviation(LogicHandler.getMeanAbsoluteDeviation(MainFrame.getDataHandler().getList(),MainFrame.getDataHandler().getResults().getClassMiddles(),
				MainFrame.getDataHandler().getResults().getRelativeOccurences(), z));
		MainFrame.getDataHandler().getResults().setVariance(LogicHandler.getVariance(MainFrame.getDataHandler().getList(), MainFrame.getDataHandler().getResults().getClassMiddles(), MainFrame.getDataHandler().getResults().getArithmeticMiddle(),
				MainFrame.getDataHandler().getSampleSize()));
		MainFrame.getDataHandler().getResults().setStandardDeviation(LogicHandler.getStandardDeviation(MainFrame.getDataHandler().getResults().getVariance()));
	}
	


}
