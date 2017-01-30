package test;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

public class TestPanel extends JPanel {
	// private JTable table;
	private JTextField leftClassBorderField;
	private JTextField rightClassBorderField;
	private JTextField quantityField;

	/**
	 * Create the panel.
	 */
	public TestPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(20, 5));
		
		JTabbedPane outputTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(outputTabbedPane, BorderLayout.CENTER);
		
		JPanel overviewContainer = new JPanel();
		outputTabbedPane.addTab("\u00DCbersicht", null, overviewContainer, "\u00DCbersicht der Ergebnisse");
		outputTabbedPane.setEnabledAt(0, true);
		overviewContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel resultContainer = new JPanel();
		overviewContainer.add(resultContainer, BorderLayout.CENTER);
		resultContainer.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultContainer.add(resultPanel);
		resultPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel resultHeaderPanel = new JPanel();
		resultHeaderPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		FlowLayout fl_resultHeaderPanel = (FlowLayout) resultHeaderPanel.getLayout();
		fl_resultHeaderPanel.setVgap(0);
		fl_resultHeaderPanel.setHgap(0);
		resultPanel.add(resultHeaderPanel, BorderLayout.NORTH);
		
		JLabel resultHeaderLabel = new JLabel("Ergebnisse");
		resultHeaderPanel.add(resultHeaderLabel);
		resultHeaderLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		resultHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultHeaderLabel.setFont(new Font("Arial", Font.BOLD, 16));
		resultHeaderLabel.setPreferredSize(new Dimension(100, 20));
		resultHeaderLabel.setMaximumSize(new Dimension(100, 20));
		resultHeaderLabel.setMinimumSize(new Dimension(100, 20));
		
		JPanel resultValuePanel = new JPanel();
		resultValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultPanel.add(resultValuePanel, BorderLayout.CENTER);
		resultValuePanel.setLayout(new GridLayout(7, 1, 0, 0));
		
		JPanel resultMiddlePanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) resultMiddlePanel.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		resultValuePanel.add(resultMiddlePanel);
		
		JLabel resultMiddleLabel = new JLabel("Mittelwert");
		resultMiddlePanel.add(resultMiddleLabel);
		
		JLabel lblX = new JLabel("x");
		resultMiddlePanel.add(lblX);
		
		JLabel label = new JLabel("=");
		resultMiddlePanel.add(label);
		
		JLabel lblValue = new JLabel("value");
		resultMiddlePanel.add(lblValue);
		
		JPanel resultMedianPanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) resultMedianPanel.getLayout();
		flowLayout_2.setHgap(10);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		resultValuePanel.add(resultMedianPanel);
		
		JLabel lblNewLabel_1 = new JLabel("Median");
		resultMedianPanel.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("x");
		resultMedianPanel.add(label_1);
		
		JLabel label_2 = new JLabel("=");
		resultMedianPanel.add(label_2);
		
		JLabel label_6 = new JLabel("value");
		resultMedianPanel.add(label_6);
		
		JPanel resultQuantilePanel = new JPanel();
		FlowLayout fl_resultQuantilePanel = (FlowLayout) resultQuantilePanel.getLayout();
		fl_resultQuantilePanel.setHgap(10);
		fl_resultQuantilePanel.setAlignment(FlowLayout.LEFT);
		resultValuePanel.add(resultQuantilePanel);
		
		JLabel lblQuantile = new JLabel("Quantile");
		resultQuantilePanel.add(lblQuantile);
		
		JLabel label_3 = new JLabel("x");
		resultQuantilePanel.add(label_3);
		
		JLabel label_4 = new JLabel("=");
		resultQuantilePanel.add(label_4);
		
		JLabel label_5 = new JLabel("value");
		resultQuantilePanel.add(label_5);
		
		JPanel resultDeviationPanel = new JPanel();
		FlowLayout fl_resultDeviationPanel = (FlowLayout) resultDeviationPanel.getLayout();
		fl_resultDeviationPanel.setHgap(10);
		fl_resultDeviationPanel.setAlignment(FlowLayout.LEFT);
		resultValuePanel.add(resultDeviationPanel);
		
		JLabel lblAbsoluteAbweichung = new JLabel("Absolute Abweichung");
		resultDeviationPanel.add(lblAbsoluteAbweichung);
		
		JLabel label_8 = new JLabel("x");
		resultDeviationPanel.add(label_8);
		
		JLabel label_9 = new JLabel("=");
		resultDeviationPanel.add(label_9);
		
		JLabel label_10 = new JLabel("value");
		resultDeviationPanel.add(label_10);
		
		JPanel resultVariancePanel = new JPanel();
		FlowLayout fl_resultVariancePanel = (FlowLayout) resultVariancePanel.getLayout();
		fl_resultVariancePanel.setHgap(10);
		fl_resultVariancePanel.setAlignment(FlowLayout.LEFT);
		resultValuePanel.add(resultVariancePanel);
		
		JLabel lblVarianz = new JLabel("Varianz");
		resultVariancePanel.add(lblVarianz);
		
		JLabel label_11 = new JLabel("x");
		resultVariancePanel.add(label_11);
		
		JLabel label_12 = new JLabel("=");
		resultVariancePanel.add(label_12);
		
		JLabel label_13 = new JLabel("value");
		resultVariancePanel.add(label_13);
		
		JPanel resultStandardPanel = new JPanel();
		FlowLayout fl_resultStandardPanel = (FlowLayout) resultStandardPanel.getLayout();
		fl_resultStandardPanel.setHgap(10);
		fl_resultStandardPanel.setAlignment(FlowLayout.LEFT);
		resultValuePanel.add(resultStandardPanel);
		
		JLabel lblStandardabweichung_1 = new JLabel("Standardabweichung");
		resultStandardPanel.add(lblStandardabweichung_1);
		
		JLabel label_14 = new JLabel("x");
		resultStandardPanel.add(label_14);
		
		JLabel label_15 = new JLabel("=");
		resultStandardPanel.add(label_15);
		
		JLabel label_16 = new JLabel("value");
		resultStandardPanel.add(label_16);
		
		JPanel resultGiniPanel = new JPanel();
		FlowLayout fl_resultGiniPanel = (FlowLayout) resultGiniPanel.getLayout();
		fl_resultGiniPanel.setHgap(10);
		fl_resultGiniPanel.setAlignment(FlowLayout.LEFT);
		resultValuePanel.add(resultGiniPanel);
		
		JLabel lblGiniKoeffizient = new JLabel("Gini Koeffizient");
		resultGiniPanel.add(lblGiniKoeffizient);
		
		JLabel label_17 = new JLabel("x");
		resultGiniPanel.add(label_17);
		
		JLabel label_18 = new JLabel("=");
		resultGiniPanel.add(label_18);
		
		JLabel label_19 = new JLabel("value");
		resultGiniPanel.add(label_19);
		
		JPanel diagramContainer = new JPanel();
		diagramContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultContainer.add(diagramContainer);
		diagramContainer.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel histogramPanel = new JPanel();
		histogramPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		diagramContainer.add(histogramPanel);
		
		JPanel empiricPanel = new JPanel();
		empiricPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		diagramContainer.add(empiricPanel);
		
		JPanel buttonContainer = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonContainer.getLayout();
		flowLayout.setHgap(75);
		flowLayout.setVgap(10);
		overviewContainer.add(buttonContainer, BorderLayout.SOUTH);
		
		JButton changeValueButton = new JButton("Werte \u00E4ndern");
		buttonContainer.add(changeValueButton);
		
		JButton newCalculationButton = new JButton("Neue Berechnung");
		buttonContainer.add(newCalculationButton);
		
		JPanel histogramContainer = new JPanel();
		histogramContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputTabbedPane.addTab("Histogramm", null, histogramContainer, "Histogramm anzeigen");
		histogramContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel empiricContainer = new JPanel();
		empiricContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputTabbedPane.addTab("Empirische Verteilung", null, empiricContainer, "Empirische Verteilung anzeigen");
		empiricContainer.setLayout(new BorderLayout(0, 0));
	}

	/*	JPanel inputContainer = new JPanel();
		inputContainer.setBorder(new EmptyBorder(5, 0, 5, 5));
		inputContainer.setBackground(Color.WHITE);
		add(inputContainer, BorderLayout.CENTER);
		inputContainer.setLayout(new BorderLayout(0, 0));

		JPanel inputPane = new JPanel();
		inputPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		inputPane.setBackground(Color.WHITE);
		inputContainer.add(inputPane, BorderLayout.CENTER);

		JPanel classPane = new JPanel();
		classPane.setBackground(Color.WHITE);
		classPane.setAlignmentY(Component.TOP_ALIGNMENT);
		classPane.setLayout(new BorderLayout(0, 0));

		JLabel classLabel = new JLabel("Klasse K definieren");
		classLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		classLabel.setFont(new Font("Arial", Font.BOLD, 16));
		classLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classPane.add(classLabel, BorderLayout.NORTH);

		JPanel classInputPane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) classInputPane.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(40);
		classInputPane.setBackground(Color.WHITE);
		classInputPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		classPane.add(classInputPane, BorderLayout.CENTER);

		JLabel leftClassBorderLabel = new JLabel(" ( ");
		leftClassBorderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		leftClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		leftClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftClassBorderLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(leftClassBorderLabel);

		leftClassBorderField = new JTextField();
		leftClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		leftClassBorderField.setFont(new Font("Arial", Font.PLAIN, 26));
		leftClassBorderField.setSize(new Dimension(100, 60));
		classInputPane.add(leftClassBorderField);
		leftClassBorderField.setColumns(5);

		JLabel classSeparatorLabel = new JLabel(" , ");
		classSeparatorLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(classSeparatorLabel);

		rightClassBorderField = new JTextField();
		rightClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		rightClassBorderField.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(rightClassBorderField);
		rightClassBorderField.setColumns(5);

		JLabel rightClassBorderLabel = new JLabel(" ] ");
		rightClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		rightClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		rightClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightClassBorderLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(rightClassBorderLabel);

		JPanel quantityPane = new JPanel();
		quantityPane.setBorder(null);
		quantityPane.setBackground(Color.WHITE);
		quantityPane.setLayout(new BorderLayout(0, 0));

		JLabel quantityLabel = new JLabel("Absolute H\u00E4ufigkeit h");
		quantityLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityLabel.setFont(new Font("Arial", Font.BOLD, 16));
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityPane.add(quantityLabel, BorderLayout.NORTH);

		JPanel quantityInputPane = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) quantityInputPane.getLayout();
		flowLayout_1.setVgap(40);
		flowLayout_1.setHgap(10);
		quantityInputPane.setBackground(Color.WHITE);
		quantityInputPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityPane.add(quantityInputPane, BorderLayout.CENTER);

		JLabel smallQuantityLabel = new JLabel("h (K)  = ");
		smallQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPane.add(smallQuantityLabel);

		quantityField = new JTextField();
		quantityField.setMargin(new Insets(10, 5, 10, 5));
		quantityField.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPane.add(quantityField);
		quantityField.setColumns(5);

		JLabel quantitySumLabel = new JLabel(" n = 0");
		quantitySumLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPane.add(quantitySumLabel);
		inputPane.setLayout(new GridLayout(2, 1, 10, 0));
		inputPane.add(classPane);
		inputPane.add(quantityPane);

		JPanel buttonContainer = new JPanel();
		buttonContainer.setBorder(null);
		buttonContainer.setBackground(Color.WHITE);
		inputContainer.add(buttonContainer, BorderLayout.SOUTH);
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));

		JButton lastClassButton = new JButton("Vorherige Klasse");
		lastClassButton.addActionListener(lastClassAction);
		buttonContainer.add(lastClassButton);

		JButton calculateButton = new JButton("Berechnen");
		lastClassButton.addActionListener(calculateAction);
		buttonContainer.add(calculateButton);

		JButton nextClassButton = new JButton("N\u00E4chste Klasse");
		lastClassButton.addActionListener(nextClassAction);
		buttonContainer.add(nextClassButton);

		JPanel tableContainer = new JPanel();
		tableContainer.setBorder(new EmptyBorder(5, 5, 5, 0));
		tableContainer.setBackground(Color.WHITE);
		add(tableContainer, BorderLayout.WEST);
		tableContainer.setLayout(new BorderLayout(5, 5));

		final JTable table = new JTable(){
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		table.setBackground(UIManager.getColor("Label.background"));
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "(a , b]", "h", "r"},
				{"2", "", "", ""},
				{"3", "", "", ""},
				{"4", null, null, null},
				{"5", null, null, null},
				{"6", null, null, null},
				{"7", null, null, null},
				{"8", null, null, null},
				{"9", null, null, null},
				{"10", null, null, null},
				{"11", null, null, null},
				{"12", null, null, null},
				{"13", null, null, null},
				{"14", null, null, null},
				{"15", null, null, null},
				{"16", null, null, null},
				{"17", null, null, null},
				{"18", null, null, null},
				{"19", null, null, null},
				{"20", null, null, null},
			},
			new String[] {
				"j", "K(j)", "h(Kj)", "r(Kj)"
			}
		));
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
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(null);
		tableScrollPane.setBackground(Color.WHITE);
		tableScrollPane.setPreferredSize(new Dimension(152, 200));
		tableContainer.add(tableScrollPane);
	}
	
	private ActionListener lastClassAction = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO: Action bei Button "vorherige Klasse"
			
		}
	};
	private ActionListener nextClassAction = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO: Action bei Button "nächste Klasse"
		}
	};
	private ActionListener calculateAction = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO: Action bei Button "Berechnen"
		}
	};
	*/

}
