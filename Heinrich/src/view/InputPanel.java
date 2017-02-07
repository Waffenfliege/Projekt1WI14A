package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.table.DefaultTableModel;

import data.ClampType;
import data.DataHandler;
import data.IllegalBorderException;
import data.IllegalOverlapException;
import data.StatisticClassValue;
import logic.LogicHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Jan Sauerland
 *
 */
@SuppressWarnings("serial")
public class InputPanel extends JPanel
{
	private static JTextField leftClassBorderField;
	private static JTextField rightClassBorderField;
	private static JTextField quantityField;
	private JPanel inputContainer, inputPanel, classPanel, classInputPanel, quantityPanel, quantityInputPanel;
	private static JPanel tableContainer;
	private JLabel classLabel;
	private static JLabel leftClassBorderLabel;
	private JLabel classSeparatorLabel;
	private static JLabel rightClassBorderLabel;
	private JLabel quantityLabel;
	private JLabel smallQuantityLabel;
	private JLabel quantitySumLabel;
	private JScrollPane tableScrollPane;
	private JPanel buttonContainer;
	private static JTable table;
	private final static Color RED = new Color(175, 22, 20);
	private final static String[] TABLE_HEADER = { "j", "K(j)", "h(Kj)", "r(Kj)" };

	private static int index;

	static JButton lastClassButton;
	static JButton calculateButton;
	static JButton nextClassButton;

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
		add(inputContainer, BorderLayout.CENTER);
		inputContainer.setLayout(new BorderLayout(0, 0));

		inputPanel = new JPanel();
		inputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		inputPanel.setBackground(Color.WHITE);
		inputContainer.add(inputPanel, BorderLayout.CENTER);

		classPanel = new JPanel();
		classPanel.setBackground(RED);
		classPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		classPanel.setLayout(new BorderLayout(0, 0));

		classLabel = new JLabel("Klasse 1 definieren");
		classLabel.setForeground(Color.WHITE);
		classLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		classLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		classLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classPanel.add(classLabel, BorderLayout.NORTH);

		classInputPanel = new JPanel();
		// kann man das mit dem FlowLayout unten zusammenfassen?
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
		leftClassBorderLabel.setFont(new Font("Calibri", Font.PLAIN, 26));
		leftClassBorderLabel.addMouseListener(borderClick);
		classInputPanel.add(leftClassBorderLabel);

		leftClassBorderField = new JTextField();
		leftClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		leftClassBorderField.setFont(new Font("Calibri", Font.PLAIN, 26));
		leftClassBorderField.setSize(new Dimension(100, 60));
		classInputPanel.add(leftClassBorderField);
		leftClassBorderField.setColumns(5);

		classSeparatorLabel = new JLabel(" , ");
		classSeparatorLabel.setFont(new Font("Calibri", Font.PLAIN, 26));
		classInputPanel.add(classSeparatorLabel);

		rightClassBorderField = new JTextField();
		rightClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		rightClassBorderField.setFont(new Font("Calibri", Font.PLAIN, 26));
		classInputPanel.add(rightClassBorderField);
		rightClassBorderField.setColumns(5);

		rightClassBorderLabel = new JLabel(" ] ");
		rightClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		rightClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		rightClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightClassBorderLabel.setFont(new Font("Calibri", Font.PLAIN, 26));
		rightClassBorderLabel.addMouseListener(borderClick);
		classInputPanel.add(rightClassBorderLabel);

		quantityPanel = new JPanel();
		quantityPanel.setBorder(null);
		quantityPanel.setBackground(RED);
		quantityPanel.setLayout(new BorderLayout(0, 0));

		quantityLabel = new JLabel("Absolute H\u00E4ufigkeit h");
		quantityLabel.setForeground(Color.WHITE);
		quantityLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityPanel.add(quantityLabel, BorderLayout.NORTH);

		quantityInputPanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) quantityInputPanel.getLayout();
		flowLayout_1.setVgap(40);
		flowLayout_1.setHgap(10);
		quantityInputPanel.setBackground(Color.WHITE);
		quantityInputPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityPanel.add(quantityInputPanel, BorderLayout.CENTER);

		smallQuantityLabel = new JLabel("h (K)  = ");
		smallQuantityLabel.setFont(new Font("Calibri", Font.PLAIN, 26));
		quantityInputPanel.add(smallQuantityLabel);

		quantityField = new JTextField();
		quantityField.setMargin(new Insets(10, 5, 10, 5));
		quantityField.setFont(new Font("Calibri", Font.PLAIN, 26));
		quantityInputPanel.add(quantityField);
		quantityField.setColumns(5);
		quantityField.addActionListener(nextClassAction);

		quantitySumLabel = new JLabel(" n = 0");
		quantitySumLabel.setFont(new Font("Calibri", Font.PLAIN, 26));
		quantityInputPanel.add(quantitySumLabel);
		inputPanel.setLayout(new GridLayout(2, 1, 10, 0));
		inputPanel.add(classPanel);
		inputPanel.add(quantityPanel);

		// #######################

		buttonContainer = new JPanel();
		buttonContainer.setBorder(null);
		buttonContainer.setBackground(Color.WHITE);
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));

		lastClassButton = new JButton("Vorherige Klasse");
		lastClassButton.addActionListener(lastClassAction);
		buttonContainer.add(lastClassButton);

		calculateButton = new JButton("Berechnen");
		calculateButton.addActionListener(calculateAction);
		buttonContainer.add(calculateButton);
		calculateButton.setEnabled(false);

		nextClassButton = new JButton("N\u00E4chste Klasse");
		nextClassButton.addActionListener(nextClassAction);
		buttonContainer.add(nextClassButton);

		inputContainer.add(buttonContainer, BorderLayout.SOUTH);

		// #######################

		tableContainer = new JPanel();
		tableContainer.setPreferredSize(new Dimension(250, 100));
		tableContainer.setMaximumSize(new Dimension(400, 500));
		tableContainer.setMinimumSize(new Dimension(250, 100));
		tableContainer.setBorder(new EmptyBorder(5, 5, 5, 0));
		tableContainer.setBackground(Color.WHITE);
		add(tableContainer, BorderLayout.WEST);
		tableContainer.setLayout(new BorderLayout(5, 5));

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

		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(null);
		tableScrollPane.setBackground(Color.WHITE);
		tableScrollPane.setPreferredSize(new Dimension(152, 200));
		tableContainer.add(tableScrollPane);
	}

	private MouseListener borderClick = new MouseListener()
	{
		public void mouseClicked(MouseEvent mouseEvent)
		{
			// TODO: Action bei Klick auf linke Klammer
			JLabel clamp = (JLabel) mouseEvent.getComponent();
			String text = clamp.getText();
			if (text.equals(" ( "))
			{
				leftClassBorderLabel.setText(" [ ");
				rightClassBorderLabel.setText(" ) ");
			} else if (text.equals(" [ "))
			{
				leftClassBorderLabel.setText(" ( ");
				rightClassBorderLabel.setText(" ] ");
			} else if (text.equals(" ) "))
			{
				leftClassBorderLabel.setText(" ( ");
				rightClassBorderLabel.setText(" ] ");
			} else if (text.equals(" ] "))
			{
				leftClassBorderLabel.setText(" [ ");
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

	/**
	 * Returns the InputPanel.
	 * 
	 * @return the InputPanel
	 */
	public InputPanel get()
	{
		return this;
	}

	public static void setTableValue(StatisticClassValue lowerValue, StatisticClassValue upperValue, int quantity, int index)
	{
		String lowerClamp = "";
		String upperClamp = "";

		switch (lowerValue.clamp)
		{
		case INCLUSIVE:
			lowerClamp = "(";
			break;

		case EXCLUSIVE:
			lowerClamp = "[";
			break;

		default:
			break;

		}
		switch (upperValue.clamp)
		{
		case INCLUSIVE:
			upperClamp = ")";
			break;

		case EXCLUSIVE:
			upperClamp = "]";
			break;

		default:
			break;
		}
		String row[] = new String[] { String.valueOf(index + 1),
				lowerClamp + lowerValue.value + ", " + upperValue.value + upperClamp, String.valueOf(quantity), "" };
		table.getModel().setValueAt(row[0], index, 0);
		table.getModel().setValueAt(row[1], index, 1);
		table.getModel().setValueAt(row[2], index, 2);
		table.getModel().setValueAt(row[3], index, 3);
		updateTable();

		tableContainer.revalidate();
		tableContainer.repaint();

	}

	/**
	 * Updates relative Occurrences in table
	 */
	public static void updateTable()
	{
		for (int i = 0; i < MainFrame.getDataHandler().getClassCount(); i++)
		{
			table.getModel().setValueAt(String.valueOf(LogicHandler.getRelativeOccurences(MainFrame.getDataHandler().getList(),
					MainFrame.getDataHandler().getSampleSize())[i]), i, 3);
		}
	}

	public static String getLeftClassBorderField()
	{
		return leftClassBorderField.getText();
	}

	public static String getRightClassBorderField()
	{
		return rightClassBorderField.getText();
	}

	public static String getQuantityField()
	{
		return quantityField.getText();

	}

	public static String getLeftClamp()
	{
		return leftClassBorderLabel.getText();
	}

	public static String getRightClamp()
	{
		return rightClassBorderLabel.getText();
	}

	public static void resetFields()
	{
		leftClassBorderField.setText("");
		rightClassBorderField.setText("");
		quantityField.setText("");
	}

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
		table.setModel(new DefaultTableModel(rows, TABLE_HEADER));

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
		table.setEnabled(false);
	}

	private ActionListener lastClassAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			try
			{
				processInputMasks();
				if (index > 0)
				{
					index--;
					classLabel.setText("Klasse " + (index + 1) + " definieren");
				}
				updateInputFields(index);
			} catch (IllegalOverlapException e)
			{
				e.printStackTrace();
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
			try
			{
				processInputMasks();
				quantitySumLabel.setText(" n = " + MainFrame.getDataHandler().getSampleSize());
				if (index < MainFrame.getDataHandler().getList().size())
				{
					index++;
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
					}
				}
			} catch (IllegalBorderException e)
			{
				e.printStackTrace();
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
			calculateResultsPreZ();
			ZDialog zDialog = new ZDialog();
		}
	};

	private void updateInputFields(int index)
	{
		leftClassBorderField.setText(String.valueOf(MainFrame.getDataHandler().getElement(index).getLowerValue().value));
		rightClassBorderField.setText(String.valueOf(MainFrame.getDataHandler().getElement(index).getUpperValue().value));
		quantityField.setText(String.valueOf(MainFrame.getDataHandler().getElement(index).getAbsoluteOccurences()));
	}

	private void processInputMasks() throws IllegalOverlapException, Exception
	{

		if (isValid(InputPanel.getLeftClassBorderField()) && isValid(InputPanel.getRightClassBorderField())
				&& isValid(InputPanel.getQuantityField()))
		{

			// compare Classes. If nothing has changed, do nothing. If something
			// has changed, change or add a new Class
			float lowerValue = Float.parseFloat(InputPanel.getLeftClassBorderField());
			float upperValue = Float.parseFloat(InputPanel.getRightClassBorderField());
			ClampType lowerClampType;
			ClampType upperClampType;

			if (InputPanel.getLeftClamp().equals(" ( "))
			{
				lowerClampType = ClampType.INCLUSIVE;

			} else
			{
				lowerClampType = ClampType.EXCLUSIVE;

			}

			if (InputPanel.getRightClamp().equals(" ) "))
			{
				upperClampType = ClampType.INCLUSIVE;

			} else
			{
				upperClampType = ClampType.EXCLUSIVE;

			}
			int absoluteOccurence = Integer.parseInt(InputPanel.getQuantityField());

			MainFrame.getDataHandler().receiveData(new StatisticClassValue(lowerValue, lowerClampType),
					new StatisticClassValue(upperValue, upperClampType), absoluteOccurence, index);
			updateTable();
			resetFields();
			calculateButton.setEnabled(true);
		}
	}

	/**
	 * Validates numbers in Input Fields
	 * 
	 * @param input
	 *            String from Input Fields
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

	public static void initialize()
	{
		// index
		index = 0;
		resetFields();
		resetTable();
		calculateButton.setEnabled(false);
		// Tabellen
		// Felder

	}

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