package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import data.ClampType;
import data.DataHandler;
import data.IllegalOverlapException;
import data.StatisticClassValue;
import logic.LogicHandler;
import logic.Quantile;

import java.awt.Color;

@SuppressWarnings("serial")
public class ButtonContainer extends JPanel
{
	private JButton changeValueButton, newCalculationButton, lastClassButton, nextClassButton, calculateButton;
	private FlowLayout flowLayout;
	private int index = 0;

	/**
	 * Create the panel.
	 */
	public ButtonContainer(boolean output)
	{
		super();
		if (output)
		{
			setBorder(new LineBorder(new Color(0, 0, 0)));
			flowLayout = (FlowLayout) this.getLayout();
			flowLayout.setHgap(75);
			flowLayout.setVgap(10);

			changeValueButton = new JButton("Werte \u00E4ndern");
			changeValueButton.addActionListener(changeValueAction);
			add(changeValueButton);

			newCalculationButton = new JButton("Neue Berechnung");
			newCalculationButton.addActionListener(newCalculationAction);
			add(newCalculationButton);
		} else if (!output)
		{
			setBorder(null);
			setBackground(Color.WHITE);
			setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));

			lastClassButton = new JButton("Vorherige Klasse");
			lastClassButton.addActionListener(lastClassAction);
			add(lastClassButton);

			calculateButton = new JButton("Berechnen");
			calculateButton.addActionListener(calculateAction);
			add(calculateButton);

			nextClassButton = new JButton("N\u00E4chste Klasse");
			nextClassButton.addActionListener(nextClassAction);
			add(nextClassButton);
		}
	}

	private ActionListener newCalculationAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			// TODO: Action bei Button "Neue Berechnung": Input-Panel komplett
			// neu aufrufen
			String dialogString = InputDialog.startNewDialog();
			if (dialogString.equals("Ja"))
			{
				MainFrame.switchToInputPanel(true);
			}
		}
	};

	private ActionListener changeValueAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			// TODO: Action bei Button "Werte ändern": Input-Panel mit
			// bestehenden Daten aufrufen
			MainFrame.switchToInputPanel(false);
		}
	};

	private ActionListener lastClassAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
				try
				{
					processInputMasks();
					index--;
				} catch (IllegalOverlapException e)
				{
					e.printStackTrace();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			MainFrame.getInputPanel().revalidate();
			MainFrame.getInputPanel().repaint();
		}
	};

	private ActionListener nextClassAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
				try
				{
					processInputMasks();
					index++;
				} catch (IllegalOverlapException e)
				{
					e.printStackTrace();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			MainFrame.getInputPanel().revalidate();
			MainFrame.getInputPanel().repaint();
		}
	};

	
	private ActionListener calculateAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent) 
		{
				try
				{
					processInputMasks();
				} catch (IllegalOverlapException e)
				{
					e.printStackTrace();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			
			InputDialog.startZDialog(); 
		}
	};

	private void processInputMasks() throws IllegalOverlapException, Exception{
		if (isValid(InputPanel.getLeftClassBorderField()) && isValid(InputPanel.getRightClassBorderField())
				&& isValid(InputPanel.getQuantityField()))
		{
			float lowerValue = Float.parseFloat(InputPanel.getLeftClassBorderField());
			float upperValue = Float.parseFloat(InputPanel.getRightClassBorderField());
			ClampType lowerClampType;
			ClampType upperClampType;
			
			if (InputPanel.getLeftClamp().equals(" ( "))
			{
				lowerClampType = ClampType.INCLUSIVE;

			}else
			{
				lowerClampType = ClampType.EXCLUSIVE;

			}
			
			if (InputPanel.getRightClamp().equals(" ) "))
			{
				upperClampType = ClampType.INCLUSIVE;

			}else
			{
				upperClampType = ClampType.EXCLUSIVE;

			}
			int absoluteOccurence = Integer.parseInt(InputPanel.getQuantityField());

			DataHandler.receiveData(new StatisticClassValue(lowerValue, lowerClampType),
					new StatisticClassValue(upperValue, upperClampType), absoluteOccurence, index);
			InputPanel.updateTable();
			InputPanel.resetFields();	
		}
	}
	
	
	/**
	 * Validates numbers in Input Fields
	 * @param input String from Input Fields
	 * @return true if input is a valid number
	 */
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
}
