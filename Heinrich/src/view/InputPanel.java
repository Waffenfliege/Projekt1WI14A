package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridLayout;

import data.ClampType;
import data.IllegalBorderException;
import data.IllegalOverlapException;
import data.StatisticClassValue;
import logic.LogicHandler;

/**
 * Manages the InputPanel and its components.
 * 
 * Needed to type in the class values and show the data already typed in.
 * 
 * @author Jan Sauerland, Lukas Moser, Mathias Engmann.
 *
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel
{
	private static JTextField leftClassBorderField, rightClassBorderField, quantityField;
	private JPanel inputContainer, inputPanel, classPanel, classHeaderPanel, classInputPanel, quantityPanel,
			quantityHeaderPanel, quantityInputPanel, buttonContainer, leftButtonContainer, centerButtonContainer,
			rightButtonContainer;
	private static JPanel tableContainer;
	private JLabel errorLabel, classSeparatorLabel, quantityLabel, smallQuantityLabel;
	private static JLabel quantitySumLabel;
	private static JLabel leftClassBorderLabel, rightClassBorderLabel, classLabel;
	private JScrollPane tableScrollPane;
	private static JTable table;
	private static JButton lastClassButton, calculateButton, nextClassButton, resetInputButton, deleteDataButton;

	private final static Font NORMAL = new Font("Calibri", Font.BOLD, 16);
	private final static Font BIG = new Font("Calibri", Font.PLAIN, 26);
	private final static String[] TABLE_HEADER = { "j", "K(j)", "h(Kj)", "r(Kj)" };

	private static int index;

	/**
	 * Creating the panel with all its components.
	 */
	public InputPanel()
	{
		super();

		index = 0;
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(20, 5));

		inputContainer = new JPanel();
		inputContainer.setBorder(new EmptyBorder(5, 0, 5, 5));
		inputContainer.setBackground(Color.WHITE);
		inputContainer.setLayout(new BorderLayout(0, 0));
		add(inputContainer, BorderLayout.CENTER);

		inputPanel = new JPanel();
		inputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		inputPanel.setBackground(Color.WHITE);
		inputContainer.add(inputPanel, BorderLayout.CENTER);

		classPanel = new JPanel();
		classPanel.setBackground(Color.WHITE);
		classPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		classPanel.setLayout(new BorderLayout(0, 0));

		classHeaderPanel = new JPanel();
		classHeaderPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		classHeaderPanel.setBackground(MainFrame.getRed());

		classLabel = new JLabel("Klasse 1 definieren");
		classLabel.setForeground(Color.WHITE);
		classLabel.setFont(NORMAL);
		classLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classHeaderPanel.add(classLabel);

		classPanel.add(classHeaderPanel, BorderLayout.NORTH);

		classInputPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) classInputPanel.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(40);
		classInputPanel.setBackground(Color.WHITE);
		classInputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		classPanel.add(classInputPanel, BorderLayout.CENTER);

		leftClassBorderLabel = new JLabel(" ( ");
		leftClassBorderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		leftClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		leftClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftClassBorderLabel.setFont(BIG);
		leftClassBorderLabel.addMouseListener(borderClick);
		classInputPanel.add(leftClassBorderLabel);

		leftClassBorderField = new JTextField();
		leftClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		leftClassBorderField.setFont(BIG);
		leftClassBorderField.setSize(new Dimension(100, 60));
		leftClassBorderField.setHorizontalAlignment(JTextField.CENTER);
		leftClassBorderField.setColumns(5);
		classInputPanel.add(leftClassBorderField);

		classSeparatorLabel = new JLabel(" , ");
		classSeparatorLabel.setFont(BIG);
		classInputPanel.add(classSeparatorLabel);

		rightClassBorderField = new JTextField();
		rightClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		rightClassBorderField.setFont(BIG);
		rightClassBorderField.setHorizontalAlignment(JTextField.CENTER);
		rightClassBorderField.setColumns(5);
		classInputPanel.add(rightClassBorderField);

		rightClassBorderLabel = new JLabel(" ] ");
		rightClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		rightClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		rightClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightClassBorderLabel.setFont(BIG);
		rightClassBorderLabel.addMouseListener(borderClick);
		classInputPanel.add(rightClassBorderLabel);

		quantityPanel = new JPanel();
		quantityPanel.setBorder(null);
		quantityPanel.setBackground(Color.WHITE);
		quantityPanel.setLayout(new BorderLayout(0, 0));

		quantityHeaderPanel = new JPanel();
		quantityHeaderPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityHeaderPanel.setBackground(MainFrame.getRed());

		quantityLabel = new JLabel("Absolute H\u00E4ufigkeit h");
		quantityLabel.setForeground(Color.WHITE);
		quantityLabel.setFont(NORMAL);
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityHeaderPanel.add(quantityLabel);
		quantityPanel.add(quantityHeaderPanel, BorderLayout.NORTH);

		quantityInputPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) quantityInputPanel.getLayout();
		flowLayout_1.setVgap(40);
		flowLayout_1.setHgap(10);
		quantityInputPanel.setBackground(Color.WHITE);
		quantityInputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityPanel.add(quantityInputPanel, BorderLayout.CENTER);

		smallQuantityLabel = new JLabel("h (K)  = ");
		smallQuantityLabel.setFont(BIG);
		quantityInputPanel.add(smallQuantityLabel);

		quantityField = new JTextField();
		quantityField.setMargin(new Insets(10, 5, 10, 5));
		quantityField.setFont(BIG);
		quantityField.setColumns(5);
		quantityField.setHorizontalAlignment(JTextField.CENTER);
		quantityField.addActionListener(nextClassAction);
		quantityInputPanel.add(quantityField);

		quantitySumLabel = new JLabel(" n = 0");
		quantitySumLabel.setFont(BIG);
		quantityInputPanel.add(quantitySumLabel);
		inputPanel.setLayout(new GridLayout(2, 1, 10, 0));
		inputPanel.add(classPanel);
		inputPanel.add(quantityPanel);

		errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBackground(Color.WHITE);
		errorLabel.setForeground(MainFrame.getRed());
		errorLabel.setFont(NORMAL);
		errorLabel.setVisible(false);
		errorLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityPanel.add(errorLabel, BorderLayout.SOUTH);

		// #######################

		buttonContainer = new JPanel();
		buttonContainer.setBorder(null);
		buttonContainer.setBackground(Color.WHITE);
		buttonContainer.setLayout(new BorderLayout(0, 0));

		leftButtonContainer = new JPanel();
		leftButtonContainer.setLayout(new GridLayout(2, 1, 0, 10));
		centerButtonContainer = new JPanel();
		centerButtonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
		rightButtonContainer = new JPanel();
		rightButtonContainer.setLayout(new GridLayout(2, 1, 0, 10));

		deleteDataButton = new JButton("Neue Berechnung");
		deleteDataButton.addActionListener(deleteAction);
		deleteDataButton.setFont(NORMAL);

		lastClassButton = new JButton("Vorherige Klasse");
		lastClassButton.addActionListener(lastClassAction);
		lastClassButton.setFont(NORMAL);
		lastClassButton.setEnabled(false);

		resetInputButton = new JButton("X");
		resetInputButton.addActionListener(resetAction);
		resetInputButton.setMaximumSize(new Dimension(50, 50));
		resetInputButton.setForeground(MainFrame.getRed());
		resetInputButton.setFont(BIG);

		calculateButton = new JButton("Berechnen");
		calculateButton.addActionListener(calculateAction);
		calculateButton.setFont(NORMAL);
		calculateButton.setEnabled(false);

		nextClassButton = new JButton("N\u00E4chste Klasse");
		nextClassButton.addActionListener(nextClassAction);
		nextClassButton.setFont(NORMAL);

		leftButtonContainer.add(lastClassButton);
		leftButtonContainer.add(deleteDataButton);

		centerButtonContainer.add(resetInputButton);

		rightButtonContainer.add(nextClassButton);
		rightButtonContainer.add(calculateButton);

		buttonContainer.add(leftButtonContainer, BorderLayout.WEST);
		buttonContainer.add(centerButtonContainer, BorderLayout.CENTER);
		buttonContainer.add(rightButtonContainer, BorderLayout.EAST);

		inputContainer.add(buttonContainer, BorderLayout.SOUTH);

		// #######################

		tableContainer = new JPanel();
		tableContainer.setPreferredSize(new Dimension(250, 100));
		tableContainer.setMaximumSize(new Dimension(400, 500));
		tableContainer.setMinimumSize(new Dimension(250, 100));
		tableContainer.setBorder(new EmptyBorder(5, 5, 5, 0));
		tableContainer.setBackground(Color.WHITE);
		tableContainer.setLayout(new BorderLayout(5, 5));
		add(tableContainer, BorderLayout.WEST);

		table = new JTable()
		{
			public boolean getScrollableTracksViewportWidth()
			{
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		table.setBackground(UIManager.getColor("Label.background"));
		table.setIntercellSpacing(new Dimension(2, 2));

		resetTable();

		table.setFont(new Font("Calibri", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(tableClick);
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(null);
		tableScrollPane.setBackground(Color.WHITE);
		tableScrollPane.setPreferredSize(new Dimension(152, 200));
		tableContainer.add(tableScrollPane);

		leftClassBorderField.requestFocus();
	}

	/**
	 * Return the InputPanel.
	 * 
	 * @return InputPanel inputPanel
	 */
	public InputPanel get()
	{
		return this;
	}

	/**
	 * Set the values of a table row to the generated tableRowString according
	 * to the given data.
	 * 
	 * @param lowerValue
	 *            lowerValue
	 * @param upperValue
	 *            upperValue
	 * @param quantity
	 *            quantity
	 * @param index
	 *            index of table
	 */
	public static void setTableValue(StatisticClassValue lowerValue, StatisticClassValue upperValue, int quantity, int index)
	{
		String row[] = getTableRowString(lowerValue, upperValue, quantity, index);

		table.getModel().setValueAt(row[0], index, 0);
		table.getModel().setValueAt(row[1], index, 1);
		table.getModel().setValueAt(row[2], index, 2);
		table.getModel().setValueAt(row[3], index, 3);
		updateTable();

		tableContainer.revalidate();
		tableContainer.repaint();

	}

	/**
	 * Processes the given data to generate the respective String Array.
	 * 
	 * @param lowerValue
	 *            lowerValue
	 * @param upperValue
	 *            upperValue
	 * @param quantity
	 *            quantity
	 * @param index
	 *            index of data
	 * @return String[] Array of the generated data.
	 */
	public static String[] getTableRowString(StatisticClassValue lowerValue, StatisticClassValue upperValue, int quantity,
			int index)
	{
		String lowerClamp = "";
		String upperClamp = "";

		switch (lowerValue.clamp)
		{
		case INCLUSIVE:
			lowerClamp = "[";
			break;

		case EXCLUSIVE:
			lowerClamp = "(";
			break;

		default:
			break;

		}
		switch (upperValue.clamp)
		{
		case INCLUSIVE:
			upperClamp = "]";
			break;

		case EXCLUSIVE:
			upperClamp = ")";
			break;

		default:
			break;
		}
		String row[] = new String[] { String.valueOf(index + 1),
				lowerClamp + lowerValue.value + ", " + upperValue.value + upperClamp, String.valueOf(quantity), "" };

		return row;
	}

	/**
	 * Processes the current data at a given index to generate the respective
	 * String Array.
	 * 
	 * @param index
	 *            index of the data to generate
	 * @return String[] Array of the generated data.
	 */
	public static String[] getTableRowString(int index)
	{
		String lowerClamp = "";
		String upperClamp = "";

		switch (MainFrame.getDataHandler().getElement(index).getLowerValue().clamp)
		{
		case INCLUSIVE:
			lowerClamp = "[";
			break;

		case EXCLUSIVE:
			lowerClamp = "(";
			break;

		default:
			break;

		}
		switch (MainFrame.getDataHandler().getElement(index).getUpperValue().clamp)
		{
		case INCLUSIVE:
			upperClamp = "]";
			break;

		case EXCLUSIVE:
			upperClamp = ")";
			break;

		default:
			break;
		}
		String row[] = new String[] { String.valueOf(index + 1),
				lowerClamp + MainFrame.getDataHandler().getElement(index).getLowerValue().value + ", "
						+ MainFrame.getDataHandler().getElement(index).getUpperValue().value + upperClamp,
				String.valueOf(MainFrame.getDataHandler().getElement(index).getAbsoluteOccurences()), "" };

		return row;
	}

	/**
	 * Update the Table to match the current data.
	 */
	public static void updateTable()
	{
		for (int i = 0; i < MainFrame.getDataHandler().getClassCount(); i++)
		{
			// Set K(j)
			String currentClassValues[] = getTableRowString(i);
			table.getModel().setValueAt(currentClassValues[1], i, 1);

			// Set k(Kj)
			table.getModel().setValueAt(currentClassValues[2], i, 2);

			// Set r(Kj)
			table.getModel()
					.setValueAt(String.format("%.3f", LogicHandler.getRelativeOccurences(MainFrame.getDataHandler().getList(),
							MainFrame.getDataHandler().getSampleSize())[i]), i, 3);

		}
	}

	/**
	 * Return the Text of the leftClassBorderField component.
	 * 
	 * @return String Text of leftClassBorderField
	 */
	public static String getLeftClassBorderField()
	{
		return leftClassBorderField.getText();
	}

	/**
	 * Return the Text of the rightClassBorderField component.
	 * 
	 * @return String Text of rightClassBorderField
	 */
	public static String getRightClassBorderField()
	{
		return rightClassBorderField.getText();
	}

	/**
	 * Return the Text of the quantityField component.
	 * 
	 * @return String Text of quantitiyField component
	 */
	public static String getQuantityField()
	{
		return quantityField.getText();

	}

	/**
	 * Return the Text of the leftClassBorderLabel.
	 * 
	 * @return String Text of the leftClassBorderLabel
	 */
	public static String getLeftClamp()
	{
		return leftClassBorderLabel.getText();
	}

	/**
	 * Return the Text of the rightClassBorderLabel.
	 * 
	 * @return String Text of the rightClassBorderLabel
	 */
	public static String getRightClamp()
	{
		return rightClassBorderLabel.getText();
	}

	/**
	 * Reset the textFields in the InputPanel.
	 */
	public static void resetFields()
	{
		leftClassBorderField.setText("");
		rightClassBorderField.setText("");
		quantityField.setText("");
	}

	/**
	 * Reset the Table in the InputPanel.
	 */
	public static void resetTable()
	{
		String rows[][] = new String[20][4];
		for (int i = 0; i < 20; i++)
		{
			rows[i][0] = null;
			rows[i][1] = null;
			rows[i][2] = null;
			rows[i][3] = null;
		}
		table.setModel(new DefaultTableModel(rows, TABLE_HEADER)
		{
			@Override
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		});

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMaxWidth(60);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setMaxWidth(80);

		table.setEnabled(true);
	}

	private MouseListener borderClick = new MouseListener()
	{
		public void mouseClicked(MouseEvent mouseEvent)
		{
			JLabel clamp = (JLabel) mouseEvent.getComponent();
			String text = clamp.getText();
			if (text.equals(" ( "))
			{
				leftClassBorderLabel.setText(" [ ");
				// rightClassBorderLabel.setText(" ) ");
			} else if (text.equals(" [ "))
			{
				leftClassBorderLabel.setText(" ( ");
				// rightClassBorderLabel.setText(" ] ");
			} else if (text.equals(" ) "))
			{
				// leftClassBorderLabel.setText(" ( ");
				rightClassBorderLabel.setText(" ] ");
			} else if (text.equals(" ] "))
			{
				// leftClassBorderLabel.setText(" [ ");
				rightClassBorderLabel.setText(" ) ");
			} else
			{
				System.out.println("Fehler bei Klammer!");
			}
		}

		public void mouseEntered(MouseEvent mouseEvent)
		{
		}

		public void mouseExited(MouseEvent mouseEvent)
		{
		}

		public void mouseReleased(MouseEvent mouseEvent)
		{
		}

		public void mousePressed(MouseEvent mouseEvent)
		{
		}
	};

	private ActionListener lastClassAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			errorLabel.setVisible(false);
			try
			{
				if (processInputMasks())
				{
					if (index > 0)
					{
						index--;
						classLabel.setText("Klasse " + (index + 1) + " definieren");
						updateTable();
						table.setRowSelectionInterval(index, index);
						leftClassBorderField.requestFocus();
					}
					updateInputFields(index);
				}
			} catch (IllegalBorderException e)
			{
				errorLabel.setText("Grenzen sind falsch!");
				errorLabel.setVisible(true);
			} catch (IllegalOverlapException e)
			{
				errorLabel.setText("Klassen \u00fcberschneiden sich!");
				errorLabel.setVisible(true);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			revalidate();
			repaint();
		}
	};

	private ActionListener nextClassAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			errorLabel.setVisible(false);
			try
			{
				if (processInputMasks())
				{
					quantitySumLabel.setText(" n = " + MainFrame.getDataHandler().getSampleSize());
					if (index < MainFrame.getDataHandler().getList().size())
					{
						if (index < 19)
						{
							index++;
						}
						try
						{
							updateInputFields(index);
						} catch (IndexOutOfBoundsException e)
						{
							leftClassBorderField.setText("");
							rightClassBorderField.setText("");
							quantityField.setText("");
						} finally
						{

							classLabel.setText("Klasse " + (index + 1) + " definieren");
							updateTable();
							table.setRowSelectionInterval(index, index);

							leftClassBorderField.requestFocus();
						}
					}
				}
			} catch (IllegalBorderException e)
			{
				errorLabel.setText("Grenzen sind falsch!");
				errorLabel.setVisible(true);
			} catch (IllegalOverlapException e)
			{
				errorLabel.setText("Klassen \u00fcberschneiden sich!");
				errorLabel.setVisible(true);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			revalidate();
			repaint();
		}
	};

	private ActionListener calculateAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			errorLabel.setVisible(false);
			try
			{
				if (processInputMasks())
				{
					quantitySumLabel.setText(" n = " + MainFrame.getDataHandler().getSampleSize());
					if (index < MainFrame.getDataHandler().getList().size())
					{
						if (index < 19)
						{
							index++;
						}
						try
						{
							updateInputFields(index);
						} catch (IndexOutOfBoundsException e)
						{
							leftClassBorderField.setText("");
							rightClassBorderField.setText("");
							quantityField.setText("");
						} finally
						{
							classLabel.setText("Klasse " + (index + 1) + " definieren");
							updateTable();
							table.setRowSelectionInterval(index, index);
							leftClassBorderField.requestFocus();
						}
					}
					calculateResultsPreZ();
					new ZDialog();
				}
			} catch (IllegalBorderException e)
			{
				errorLabel.setText("Grenzen sind falsch!");
				errorLabel.setVisible(true);
				revalidate();
				repaint();
			} catch (IllegalOverlapException e)
			{
				errorLabel.setText("Klassen \u00fcberschneiden sich!");
				errorLabel.setVisible(true);
				revalidate();
				repaint();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	};

	private ActionListener deleteAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			new InitializeDialog();
		}
	};

	private ActionListener resetAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			leftClassBorderField.setText("");
			rightClassBorderField.setText("");
			quantityField.setText("");
			leftClassBorderField.requestFocus();
			revalidate();
			repaint();
		}
	};

	private MouseListener tableClick = new MouseListener()
	{
		public void mouseClicked(MouseEvent mouseEvent)
		{
			if (mouseEvent.getClickCount() == 2)
			{
				JTable target = (JTable) mouseEvent.getSource();
				int row = target.getSelectedRow();
				if (target.getSelectedColumn() == 0)
				{
					try
					{
						updateInputFields(row);
						index = row;
						classLabel.setText("Klasse " + (index + 1) + " definieren");
						table.setRowSelectionInterval(index, index);
						leftClassBorderField.requestFocus();
					} catch (IndexOutOfBoundsException e)
					{
					}
				}
			}
		}

		public void mouseEntered(MouseEvent mouseEvent)
		{
		}

		public void mouseExited(MouseEvent mouseEvent)
		{
		}

		public void mouseReleased(MouseEvent mouseEvent)
		{
		}

		public void mousePressed(MouseEvent mouseEvent)
		{
		}
	};

	/**
	 * Update the textFields in the InputPanel according to the Data of the
	 * given index.
	 * 
	 * @param index
	 *            index of the entry to show
	 */
	private void updateInputFields(int index)
	{
		leftClassBorderField.setText(String.valueOf(MainFrame.getDataHandler().getElement(index).getLowerValue().value));
		rightClassBorderField.setText(String.valueOf(MainFrame.getDataHandler().getElement(index).getUpperValue().value));
		quantityField.setText(String.valueOf(MainFrame.getDataHandler().getElement(index).getAbsoluteOccurences()));
	}

	/**
	 * Compare Classes and save the given user Input if it's valid
	 * 
	 * If nothing has changed, do nothing. If something has changed, change or
	 * add a new Class
	 * 
	 * @throws IllegalOverlapException
	 *             is ignored in this method
	 * @throws Exception
	 *             generic Exception is ignored
	 */
	private boolean processInputMasks() throws IllegalOverlapException, Exception
	{

		if (isValid(InputPanel.getLeftClassBorderField()) && isValid(InputPanel.getRightClassBorderField())
				&& isValidPositive(InputPanel.getQuantityField()))
		{
			float lowerValue = Float.parseFloat(InputPanel.getLeftClassBorderField());
			float upperValue = Float.parseFloat(InputPanel.getRightClassBorderField());
			ClampType lowerClampType;
			ClampType upperClampType;

			if (InputPanel.getLeftClamp().equals(" ( "))
			{
				lowerClampType = ClampType.EXCLUSIVE;
			} else
			{
				lowerClampType = ClampType.INCLUSIVE;
			}

			if (InputPanel.getRightClamp().equals(" ) "))
			{
				upperClampType = ClampType.EXCLUSIVE;
			} else
			{
				upperClampType = ClampType.INCLUSIVE;
			}

			int absoluteOccurence = Integer.parseInt(InputPanel.getQuantityField());

			MainFrame.getDataHandler().receiveData(new StatisticClassValue(lowerValue, lowerClampType),
					new StatisticClassValue(upperValue, upperClampType), absoluteOccurence, index);
			updateTable();
			resetFields();
			calculateButton.setEnabled(true);
			lastClassButton.setEnabled(true);
			return true;
		} else
		{
			if (leftClassBorderField.getText().equals("") && rightClassBorderField.getText().equals("")
					&& quantityField.getText().equals(""))
			{
				return true;
			} else
			{
				errorLabel.setText("Eingaben sind fehlerhaft!");
				errorLabel.setVisible(true);
				revalidate();
				repaint();
				return false;
			}
		}
	}

	/**
	 * Validates numbers in Input Fields.
	 * 
	 * @param input
	 *            String from Input Fields
	 * @return Boolean true if input is a valid number
	 */
	private boolean isValid(String input)
	{
		try
		{
			Float.parseFloat(input);
			return true;
		} catch (NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * Validates positive numbers in Input Fields.
	 * 
	 * @param input
	 *            String from Input Fields
	 * @return Boolean true if input is a valid positive number
	 */
	private boolean isValidPositive(String input)
	{
		try
		{
			int f = Integer.parseInt(input);
			if (f > 0)
			{
				return true;
			} else
			{
				return false;
			}
		} catch (NumberFormatException e)
		{
			return false;
		}
	}

	/**
	 * Reset the Focus of the MainFrame to the leftClassBorderField.
	 */
	public void resetFocus()
	{
		leftClassBorderField.requestFocus();
	}

	/**
	 * Initialize the InputPanel.
	 */
	public static void initialize()
	{
		index = 0;
		classLabel.setText("Klasse " + (index + 1) + " definieren");
		quantitySumLabel.setText(" n = 0");
		resetFields();
		resetTable();
		lastClassButton.setEnabled(false);
		calculateButton.setEnabled(false);
		leftClassBorderField.requestFocus();
	}

	/**
	 * Calculate part of the results to be able to show them in the ZDialog.
	 */
	public static void calculateResultsPreZ()
	{
		try
		{
			MainFrame.getDataHandler().getResults().setRelativeOccurences(LogicHandler
					.getRelativeOccurences(MainFrame.getDataHandler().getList(), MainFrame.getDataHandler().getSampleSize()));
			MainFrame.getDataHandler().getResults()
					.setClassMiddles(LogicHandler.getClassMiddles(MainFrame.getDataHandler().getList()));

			for (int i = 0; i < MainFrame.getDataHandler().getList().size(); i++)
			{
				MainFrame.getDataHandler().getResults()
						.setAbsoluteOccurences(MainFrame.getDataHandler().getElement(i).getAbsoluteOccurences());
			}

			MainFrame.getDataHandler().getResults()
					.setMedian(LogicHandler.getMedian(MainFrame.getDataHandler().getList(),
							MainFrame.getDataHandler().getResults().getClassMiddles(),
							MainFrame.getDataHandler().getResults().getRelativeOccurences()));
			MainFrame.getDataHandler().getResults().setArithmeticMiddle(
					LogicHandler.getArithmeticMiddle(MainFrame.getDataHandler().getResults().getClassMiddles(),
							MainFrame.getDataHandler().getResults().getRelativeOccurences()));
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}