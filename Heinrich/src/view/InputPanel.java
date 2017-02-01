package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;
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
	private JPanel inputContainer, inputPanel, classPanel, classInputPanel, quantityPanel, quantityInputPanel, tableContainer;
	private JLabel classLabel, leftClassBorderLabel, classSeparatorLabel, rightClassBorderLabel, quantityLabel,
			smallQuantityLabel, quantitySumLabel;
	private JScrollPane tableScrollPane;
	private ButtonContainer buttonContainer;

	/**
	 * Creating the panel with all its components.
	 */
	public InputPanel()
	{
		super();
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
		classPanel.setBackground(Color.RED);
		classPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		classPanel.setLayout(new BorderLayout(0, 0));

		classLabel = new JLabel("Klasse K definieren");
		classLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		classLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
		leftClassBorderLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		leftClassBorderLabel.addMouseListener(borderClick);
		classInputPanel.add(leftClassBorderLabel);

		leftClassBorderField = new JTextField();
		leftClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		leftClassBorderField.setFont(new Font("Arial", Font.PLAIN, 26));
		leftClassBorderField.setSize(new Dimension(100, 60));
		classInputPanel.add(leftClassBorderField);
		leftClassBorderField.setColumns(5);

		classSeparatorLabel = new JLabel(" , ");
		classSeparatorLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPanel.add(classSeparatorLabel);

		rightClassBorderField = new JTextField();
		rightClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		rightClassBorderField.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPanel.add(rightClassBorderField);
		rightClassBorderField.setColumns(5);

		rightClassBorderLabel = new JLabel(" ] ");
		rightClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		rightClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		rightClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightClassBorderLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		rightClassBorderLabel.addMouseListener(borderClick);
		classInputPanel.add(rightClassBorderLabel);

		quantityPanel = new JPanel();
		quantityPanel.setBorder(null);
		quantityPanel.setBackground(Color.WHITE);
		quantityPanel.setLayout(new BorderLayout(0, 0));

		quantityLabel = new JLabel("Absolute H\u00E4ufigkeit h");
		quantityLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityLabel.setFont(new Font("Arial", Font.BOLD, 16));
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
		smallQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPanel.add(smallQuantityLabel);

		quantityField = new JTextField();
		quantityField.setMargin(new Insets(10, 5, 10, 5));
		quantityField.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPanel.add(quantityField);
		quantityField.setColumns(5);

		quantitySumLabel = new JLabel(" n = 0");
		quantitySumLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPanel.add(quantitySumLabel);
		inputPanel.setLayout(new GridLayout(2, 1, 10, 0));
		inputPanel.add(classPanel);
		inputPanel.add(quantityPanel);

		buttonContainer = new ButtonContainer(false);
		inputContainer.add(buttonContainer, BorderLayout.SOUTH);

		tableContainer = new JPanel();
		tableContainer.setBorder(new EmptyBorder(5, 5, 5, 0));
		tableContainer.setBackground(Color.WHITE);
		add(tableContainer, BorderLayout.WEST);
		tableContainer.setLayout(new BorderLayout(5, 5));

		final JTable table = new JTable()
		// ist das überhaupt nötig? -> am besten alles oben als private
		// deklarieren
		{
			public boolean getScrollableTracksViewportWidth()
			{
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		table.setBackground(UIManager.getColor("Label.background"));
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setModel(new DefaultTableModel(new Object[][] { { "1", "(a , b]", "h", "r" }, { "2", "", "", "" },
				{ "3", "", "", "" }, { "4", null, null, null }, { "5", null, null, null }, { "6", null, null, null },
				{ "7", null, null, null }, { "8", null, null, null }, { "9", null, null, null }, { "10", null, null, null },
				{ "11", null, null, null }, { "12", null, null, null }, { "13", null, null, null }, { "14", null, null, null },
				{ "15", null, null, null }, { "16", null, null, null }, { "17", null, null, null }, { "18", null, null, null },
				{ "19", null, null, null }, { "20", null, null, null }, }, new String[] { "j", "K(j)", "h(Kj)", "r(Kj)" }));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
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

	public static void resetFields()
	{
		leftClassBorderField.setText("");
		rightClassBorderField.setText("");
		quantityField.setText("");
	}


	public JTextField getLeftClassInputField(){
		return leftClassBorderField;
	}
	
	public JTextField getRightClassInputField(){
		return rightClassBorderField;
	}
}


/*
 * public void setInputFrame() // erstes Initialisieren der Anzeige {
 * mainContentPane.add(tableContentPane); mainContentPane.add(inputContentPane);
 * }
 * 
 * public void setInputFrame(int[] param1) // Anzeige entsprechend des //
 * Input-Arrays { mainContentPane.add(tableContentPane);
 * mainContentPane.add(inputContentPane); }
 * 
 * public JPanel getMainContentPane() { return mainContentPane; }
 */
