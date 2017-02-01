package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Component;

/**
 * 
 * @author Jan Sauerland
 *
 */
@SuppressWarnings("serial")
public class OutputPanel extends JPanel
{
	private JPanel overviewContainer, resultContainer, resultPanel, resultHeaderPanel, resultValuePanel, histogramContainer,
			empiricContainer, empiricPanel, histogramPanel, diagramContainer;
	private ButtonContainer oButtonContainer, hButtonContainer, eButtonContainer;
	private JTabbedPane outputTabbedPane;
	private JLabel resultHeaderLabel, middleLabel1, medianLabel1, quantileLabel1, absoluteLabel1, standardLabel1,
			varianceLabel1, giniLabel1, middleLabel2, medianLabel2, quantileLabel2, absoluteLabel2, varianceLabel2,
			standardLabel2, giniLabel2, middleLabel3, medianLabel3, quantileLabel3, absoluteLabel3, varianceLabel3,
			standardLabel3, giniLabel3;
	private final static Color RED = new Color(175,22,20);

	/**
	 * Create the panel.
	 */
	public OutputPanel()
	{
		super();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(20, 5));

		outputTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		outputTabbedPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(outputTabbedPane, BorderLayout.CENTER);

		overviewContainer = new JPanel();
		outputTabbedPane.addTab("\u00DCbersicht", null, overviewContainer, "\u00DCbersicht der Ergebnisse");
		outputTabbedPane.setEnabledAt(0, true);
		overviewContainer.setLayout(new BorderLayout(0, 0));

		resultContainer = new JPanel();
		overviewContainer.add(resultContainer, BorderLayout.CENTER);
		resultContainer.setLayout(new GridLayout(1, 2, 0, 0));

		resultPanel = new JPanel();
		resultPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultContainer.add(resultPanel);
		resultPanel.setLayout(new BorderLayout(0, 0));

		resultHeaderPanel = new JPanel();
		resultHeaderPanel.setBackground(RED);
		resultHeaderPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		// FlowLayout auch als private deklarieren?
		FlowLayout fl_resultHeaderPanel = (FlowLayout) resultHeaderPanel.getLayout();
		fl_resultHeaderPanel.setVgap(0);
		fl_resultHeaderPanel.setHgap(0);
		resultPanel.add(resultHeaderPanel, BorderLayout.NORTH);

		resultHeaderLabel = new JLabel("Ergebnisse");
		resultHeaderLabel.setForeground(Color.WHITE);
		resultHeaderPanel.add(resultHeaderLabel);
		resultHeaderLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		resultHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultHeaderLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		resultHeaderLabel.setPreferredSize(new Dimension(100, 20));
		resultHeaderLabel.setMaximumSize(new Dimension(100, 20));
		resultHeaderLabel.setMinimumSize(new Dimension(100, 20));

		resultValuePanel = new JPanel();
		resultValuePanel.setBackground(Color.WHITE);
		resultValuePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		resultValuePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		resultValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultPanel.add(resultValuePanel, BorderLayout.CENTER);
		resultValuePanel.setLayout(new GridLayout(7, 3, 0, 0));

		middleLabel1 = new JLabel("Mittelwert");
		middleLabel1.setVerticalTextPosition(SwingConstants.TOP);
		middleLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(middleLabel1);

		middleLabel2 = new JLabel("");
		middleLabel2.setVerticalAlignment(SwingConstants.TOP);
		middleLabel2.setSize(new Dimension(100, 35));
		middleLabel2.setPreferredSize(new Dimension(100, 35));
		middleLabel2.setMaximumSize(new Dimension(100, 35));
		middleLabel2.setMinimumSize(new Dimension(100, 35));
		middleLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/Mittel.png")));
		resultValuePanel.add(middleLabel2);

		middleLabel3 = new JLabel("value");
		middleLabel3.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(middleLabel3);

		medianLabel1 = new JLabel("Median");
		medianLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(medianLabel1);

		medianLabel2 = new JLabel("");
		medianLabel2.setVerticalAlignment(SwingConstants.TOP);
		medianLabel2.setSize(new Dimension(100, 35));
		medianLabel2.setPreferredSize(new Dimension(100, 35));
		medianLabel2.setMinimumSize(new Dimension(100, 35));
		medianLabel2.setMaximumSize(new Dimension(100, 35));
		medianLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/Median.png")));
		resultValuePanel.add(medianLabel2);

		medianLabel3 = new JLabel("value");
		medianLabel3.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(medianLabel3);

		quantileLabel1 = new JLabel("Quantile");
		quantileLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(quantileLabel1);

		quantileLabel2 = new JLabel("");
		quantileLabel2.setVerticalTextPosition(SwingConstants.TOP);
		quantileLabel2.setVerticalAlignment(SwingConstants.TOP);
		quantileLabel2.setSize(new Dimension(100, 145));
		quantileLabel2.setPreferredSize(new Dimension(100, 145));
		quantileLabel2.setMinimumSize(new Dimension(100, 145));
		quantileLabel2.setMaximumSize(new Dimension(100, 145));
		quantileLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/Quantile.png")));
		resultValuePanel.add(quantileLabel2);

		quantileLabel3 = new JLabel("value");
		quantileLabel3.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(quantileLabel3);

		absoluteLabel1 = new JLabel("Absolut");
		absoluteLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(absoluteLabel1);

		absoluteLabel2 = new JLabel("");
		absoluteLabel2.setSize(new Dimension(100, 35));
		absoluteLabel2.setPreferredSize(new Dimension(100, 35));
		absoluteLabel2.setMinimumSize(new Dimension(100, 35));
		absoluteLabel2.setMaximumSize(new Dimension(100, 35));
		absoluteLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/absolute.png")));
		absoluteLabel2.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(absoluteLabel2);

		absoluteLabel3 = new JLabel("value");
		absoluteLabel3.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(absoluteLabel3);

		varianceLabel1 = new JLabel("Varianz");
		varianceLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(varianceLabel1);

		varianceLabel2 = new JLabel("");
		varianceLabel2.setVerticalAlignment(SwingConstants.TOP);
		varianceLabel2.setSize(new Dimension(100, 35));
		varianceLabel2.setPreferredSize(new Dimension(100, 35));
		varianceLabel2.setMinimumSize(new Dimension(100, 35));
		varianceLabel2.setMaximumSize(new Dimension(100, 35));
		varianceLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/variance.png")));
		resultValuePanel.add(varianceLabel2);

		varianceLabel3 = new JLabel("value");
		varianceLabel3.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(varianceLabel3);

		standardLabel1 = new JLabel("Standard");
		standardLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(standardLabel1);

		standardLabel2 = new JLabel("");
		standardLabel2.setVerticalAlignment(SwingConstants.TOP);
		standardLabel2.setSize(new Dimension(100, 35));
		standardLabel2.setPreferredSize(new Dimension(100, 35));
		standardLabel2.setMinimumSize(new Dimension(100, 35));
		standardLabel2.setMaximumSize(new Dimension(100, 35));
		standardLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/standard.png")));
		resultValuePanel.add(standardLabel2);

		standardLabel3 = new JLabel("value");
		standardLabel3.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(standardLabel3);

		giniLabel1 = new JLabel("Gini");
		giniLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultValuePanel.add(giniLabel1);

		giniLabel2 = new JLabel("");
		giniLabel2.setVerticalAlignment(SwingConstants.TOP);
		giniLabel2.setSize(new Dimension(100, 35));
		giniLabel2.setPreferredSize(new Dimension(100, 35));
		giniLabel2.setMinimumSize(new Dimension(100, 35));
		giniLabel2.setMaximumSize(new Dimension(100, 35));
		giniLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/gini.png")));
		resultValuePanel.add(giniLabel2);

		giniLabel3 = new JLabel("value");
		resultValuePanel.add(giniLabel3);

		/*
		 * JPanel resultMiddlePanel = new JPanel();
		 * resultMiddlePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 * FlowLayout flowLayout_1 = (FlowLayout) resultMiddlePanel.getLayout();
		 * flowLayout_1.setHgap(10); flowLayout_1.setAlignment(FlowLayout.LEFT);
		 * resultValuePanel.add(resultMiddlePanel);
		 * 
		 * JLabel resultMiddleLabel = new JLabel("Mittelwert");
		 * resultMiddlePanel.add(resultMiddleLabel);
		 * 
		 * JLabel lblX = new JLabel("x"); resultMiddlePanel.add(lblX);
		 * 
		 * JLabel label = new JLabel("="); resultMiddlePanel.add(label);
		 * 
		 * JLabel lblValue = new JLabel("value");
		 * resultMiddlePanel.add(lblValue);
		 * 
		 * JPanel resultMedianPanel = new JPanel();
		 * resultMedianPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 * FlowLayout flowLayout_2 = (FlowLayout) resultMedianPanel.getLayout();
		 * flowLayout_2.setHgap(10); flowLayout_2.setAlignment(FlowLayout.LEFT);
		 * resultValuePanel.add(resultMedianPanel);
		 * 
		 * JLabel lblNewLabel_1 = new JLabel("Median");
		 * resultMedianPanel.add(lblNewLabel_1);
		 * 
		 * JLabel label_1 = new JLabel("x"); resultMedianPanel.add(label_1);
		 * 
		 * JLabel label_2 = new JLabel("="); resultMedianPanel.add(label_2);
		 * 
		 * JLabel label_6 = new JLabel("value"); resultMedianPanel.add(label_6);
		 * 
		 * JPanel resultQuantilePanel = new JPanel();
		 * resultQuantilePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 * FlowLayout fl_resultQuantilePanel = (FlowLayout)
		 * resultQuantilePanel.getLayout(); fl_resultQuantilePanel.setHgap(10);
		 * fl_resultQuantilePanel.setAlignment(FlowLayout.LEFT);
		 * resultValuePanel.add(resultQuantilePanel);
		 * 
		 * JLabel lblQuantile = new JLabel("Quantile");
		 * resultQuantilePanel.add(lblQuantile);
		 * 
		 * JLabel label_3 = new JLabel("x"); resultQuantilePanel.add(label_3);
		 * 
		 * JLabel label_4 = new JLabel("="); resultQuantilePanel.add(label_4);
		 * 
		 * JLabel label_5 = new JLabel("value");
		 * resultQuantilePanel.add(label_5);
		 * 
		 * JPanel resultDeviationPanel = new JPanel();
		 * resultDeviationPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 * FlowLayout fl_resultDeviationPanel = (FlowLayout)
		 * resultDeviationPanel.getLayout();
		 * fl_resultDeviationPanel.setHgap(10);
		 * fl_resultDeviationPanel.setAlignment(FlowLayout.LEFT);
		 * resultValuePanel.add(resultDeviationPanel);
		 * 
		 * JLabel lblAbsoluteAbweichung = new JLabel("Absolute Abweichung");
		 * resultDeviationPanel.add(lblAbsoluteAbweichung);
		 * 
		 * JLabel label_8 = new JLabel("x"); resultDeviationPanel.add(label_8);
		 * 
		 * JLabel label_9 = new JLabel("="); resultDeviationPanel.add(label_9);
		 * 
		 * JLabel label_10 = new JLabel("value");
		 * resultDeviationPanel.add(label_10);
		 * 
		 * JPanel resultVariancePanel = new JPanel();
		 * resultVariancePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 * FlowLayout fl_resultVariancePanel = (FlowLayout)
		 * resultVariancePanel.getLayout(); fl_resultVariancePanel.setHgap(10);
		 * fl_resultVariancePanel.setAlignment(FlowLayout.LEFT);
		 * resultValuePanel.add(resultVariancePanel);
		 * 
		 * JLabel lblVarianz = new JLabel("Varianz");
		 * resultVariancePanel.add(lblVarianz);
		 * 
		 * JLabel label_11 = new JLabel("x"); resultVariancePanel.add(label_11);
		 * 
		 * JLabel label_12 = new JLabel("="); resultVariancePanel.add(label_12);
		 * 
		 * JLabel label_13 = new JLabel("value");
		 * resultVariancePanel.add(label_13);
		 * 
		 * JPanel resultStandardPanel = new JPanel();
		 * resultStandardPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		 * FlowLayout fl_resultStandardPanel = (FlowLayout)
		 * resultStandardPanel.getLayout(); fl_resultStandardPanel.setHgap(10);
		 * fl_resultStandardPanel.setAlignment(FlowLayout.LEFT);
		 * resultValuePanel.add(resultStandardPanel);
		 * 
		 * JLabel lblStandardabweichung_1 = new JLabel("Standardabweichung");
		 * resultStandardPanel.add(lblStandardabweichung_1);
		 * 
		 * JLabel label_14 = new JLabel("x"); resultStandardPanel.add(label_14);
		 * 
		 * JLabel label_15 = new JLabel("="); resultStandardPanel.add(label_15);
		 * 
		 * JLabel label_16 = new JLabel("value");
		 * resultStandardPanel.add(label_16);
		 * 
		 * JPanel resultGiniPanel = new JPanel(); resultGiniPanel.setBorder(new
		 * LineBorder(Color.LIGHT_GRAY)); FlowLayout fl_resultGiniPanel =
		 * (FlowLayout) resultGiniPanel.getLayout();
		 * fl_resultGiniPanel.setHgap(10);
		 * fl_resultGiniPanel.setAlignment(FlowLayout.LEFT);
		 * resultValuePanel.add(resultGiniPanel);
		 * 
		 * JLabel lblGiniKoeffizient = new JLabel("Gini Koeffizient");
		 * resultGiniPanel.add(lblGiniKoeffizient);
		 * 
		 * JLabel label_17 = new JLabel("x"); resultGiniPanel.add(label_17);
		 * 
		 * JLabel label_18 = new JLabel("="); resultGiniPanel.add(label_18);
		 * 
		 * JLabel label_19 = new JLabel("value"); resultGiniPanel.add(label_19);
		 */
		diagramContainer = new JPanel();
		diagramContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		resultContainer.add(diagramContainer);
		diagramContainer.setLayout(new GridLayout(2, 1, 0, 0));

		histogramPanel = new JPanel();
		histogramPanel.setBackground(Color.WHITE);
		histogramPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		diagramContainer.add(histogramPanel);

		empiricPanel = new JPanel();
		empiricPanel.setBackground(Color.WHITE);
		empiricPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		diagramContainer.add(empiricPanel);

		oButtonContainer = new ButtonContainer(true);
		overviewContainer.add(oButtonContainer, BorderLayout.SOUTH);

		histogramContainer = new JPanel();
		histogramContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputTabbedPane.addTab("Histogramm", null, histogramContainer, "Histogramm anzeigen");
		histogramContainer.setLayout(new BorderLayout(0, 0));
		hButtonContainer = new ButtonContainer(true);
		histogramContainer.add(hButtonContainer, BorderLayout.SOUTH);

		empiricContainer = new JPanel();
		empiricContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputTabbedPane.addTab("Empirische Verteilung", null, empiricContainer, "Empirische Verteilung anzeigen");
		empiricContainer.setLayout(new BorderLayout(0, 0));
		eButtonContainer = new ButtonContainer(true);
		empiricContainer.add(eButtonContainer, BorderLayout.SOUTH);
	}

	/**
	 * Return the OutputPanel.
	 * 
	 * @return the OutputPanel
	 */
	public OutputPanel get()
	{
		return this;
	}

	/**
	 * 
	 * @param median
	 */
	public void setMedian(float median)
	{
		medianLabel3.setText("" + median);
	}

	/**
	 * 
	 * @param middle
	 */
	public void setMiddle(float middle)
	{
		middleLabel3.setText("" + middle);
	}

	/**
	 * 
	 * @param variance
	 */
	public void setVariance(float variance)
	{
		varianceLabel3.setText("" + variance);
	}

	/**
	 * 
	 * @param deviation
	 */
	public void setAbsoluteDeviation(float deviation)
	{
		absoluteLabel3.setText("" + deviation);
	}

	/**
	 * 
	 * @param deviation
	 */
	public void setStandardDeviation(float deviation)
	{
		standardLabel3.setText("" + deviation);
	}

	/**
	 * 
	 * @param gini
	 */
	public void setGini(float gini)
	{
		giniLabel3.setText("" + gini);
	}

	/*
	 * private ActionListener newCalculationAction = new ActionListener() {
	 * public void actionPerformed(ActionEvent actionEvent) { // TODO: Action
	 * bei Button "Neue Berechnung": Input-Panel komplett // neu aufrufen
	 * MainFrame.switchToInputPanel(true); } };
	 * 
	 * private ActionListener changeValueAction = new ActionListener() { public
	 * void actionPerformed(ActionEvent actionEvent) { // TODO: Action bei
	 * Button "Werte ändern": Input-Panel mit // bestehenden Daten aufrufen
	 * MainFrame.switchToInputPanel(false); } };
	 */
}

/*
 * changeValueButton = new JButton("Werte \u00E4ndern");
 * changeValueButton.addActionListener(changeValueAction);
 * buttonContainer.add(changeValueButton);
 * 
 * newCalculationButton = new JButton("Neue Berechnung");
 * newCalculationButton.addActionListener(newCalculationAction);
 * buttonContainer.add(newCalculationButton);
 */