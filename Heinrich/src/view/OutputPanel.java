
package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.border.LineBorder;

import data.DataHandler;
import graphs.EmpiricDistributionPanel;
import graphs.GraphFactory;
import graphs.HistogramPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Component;

/**
 * 
 * @author Jan Sauerland
 *
 */
@SuppressWarnings("serial")
public class OutputPanel extends JPanel
{
	private JPanel buttonContainer;
	private JTabbedPane tabbedPane;

	private final static Color RED = new Color(175, 22, 20);
	private final static Font NORMAL = new Font("Calibri", Font.BOLD, 16);

	private JButton changeValueButton;
	private JButton newCalculationButton;

	public OutputPanel()
	{
		super();

		/*
		 * overviewContainer = new JPanel();
		 * outputTabbedPane.addTab("\u00DCbersicht", null, overviewContainer,
		 * "\u00DCbersicht der Ergebnisse"); outputTabbedPane.setEnabledAt(0,
		 * true); overviewContainer.setLayout(new BorderLayout(0, 0));
		 * 
		 * resultContainer = new JPanel();
		 * overviewContainer.add(resultContainer, BorderLayout.CENTER);
		 * resultContainer.setLayout(new GridLayout(1, 2, 0, 0));
		 * 
		 * resultPanel = new JPanel(); resultPanel.setBorder(new LineBorder(new
		 * Color(0, 0, 0))); resultContainer.add(resultPanel);
		 * resultPanel.setLayout(new BorderLayout(0, 0));
		 * 
		 * resultHeaderPanel = new JPanel();
		 * resultHeaderPanel.setBackground(RED); resultHeaderPanel.setBorder(new
		 * LineBorder(new Color(0, 0, 0))); // FlowLayout auch als private
		 * deklarieren? FlowLayout fl_resultHeaderPanel = (FlowLayout)
		 * resultHeaderPanel.getLayout(); fl_resultHeaderPanel.setVgap(0);
		 * fl_resultHeaderPanel.setHgap(0); resultPanel.add(resultHeaderPanel,
		 * BorderLayout.NORTH);
		 * 
		 * resultHeaderLabel = new JLabel("Ergebnisse");
		 * resultHeaderLabel.setForeground(Color.WHITE);
		 * resultHeaderPanel.add(resultHeaderLabel);
		 * resultHeaderLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		 * resultHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 * resultHeaderLabel.setFont(new Font("Calibri", Font.BOLD, 16));
		 * resultHeaderLabel.setPreferredSize(new Dimension(100, 20));
		 * resultHeaderLabel.setMaximumSize(new Dimension(100, 20));
		 * resultHeaderLabel.setMinimumSize(new Dimension(100, 20));
		 * 
		 * resultValuePanel = new JPanel();
		 * resultValuePanel.setBackground(Color.WHITE);
		 * resultValuePanel.setAlignmentY(Component.TOP_ALIGNMENT);
		 * resultValuePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		 * resultValuePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		 * resultPanel.add(resultValuePanel, BorderLayout.CENTER);
		 * resultValuePanel.setLayout(new GridLayout(7, 3, 0, 0));
		 * 
		 * middleLabel1 = new JLabel("Mittelwert");
		 * middleLabel1.setVerticalTextPosition(SwingConstants.TOP);
		 * middleLabel1.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(middleLabel1);
		 * 
		 * middleLabel2 = new JLabel("");
		 * middleLabel2.setVerticalAlignment(SwingConstants.TOP);
		 * middleLabel2.setSize(new Dimension(100, 35));
		 * middleLabel2.setPreferredSize(new Dimension(100, 35));
		 * middleLabel2.setMaximumSize(new Dimension(100, 35));
		 * middleLabel2.setMinimumSize(new Dimension(100, 35));
		 * middleLabel2.setIcon(new
		 * ImageIcon(OutputPanel.class.getResource("/view/Mittel.png")));
		 * resultValuePanel.add(middleLabel2);
		 * 
		 * middleLabel3 = new JLabel("value");
		 * middleLabel3.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(middleLabel3);
		 * 
		 * medianLabel1 = new JLabel("Median");
		 * medianLabel1.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(medianLabel1);
		 * 
		 * medianLabel2 = new JLabel("");
		 * medianLabel2.setVerticalAlignment(SwingConstants.TOP);
		 * medianLabel2.setSize(new Dimension(100, 35));
		 * medianLabel2.setPreferredSize(new Dimension(100, 35));
		 * medianLabel2.setMinimumSize(new Dimension(100, 35));
		 * medianLabel2.setMaximumSize(new Dimension(100, 35));
		 * medianLabel2.setIcon(new
		 * ImageIcon(OutputPanel.class.getResource("/view/Median.png")));
		 * resultValuePanel.add(medianLabel2);
		 * 
		 * medianLabel3 = new JLabel("value");
		 * medianLabel3.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(medianLabel3);
		 * 
		 * quantileLabel1 = new JLabel("Quantile");
		 * quantileLabel1.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(quantileLabel1);
		 * 
		 * quantileLabel2 = new JLabel("");
		 * quantileLabel2.setVerticalTextPosition(SwingConstants.TOP);
		 * quantileLabel2.setVerticalAlignment(SwingConstants.TOP);
		 * quantileLabel2.setSize(new Dimension(100, 145));
		 * quantileLabel2.setPreferredSize(new Dimension(100, 145));
		 * quantileLabel2.setMinimumSize(new Dimension(100, 145));
		 * quantileLabel2.setMaximumSize(new Dimension(100, 145));
		 * quantileLabel2.setIcon(new
		 * ImageIcon(OutputPanel.class.getResource("/view/Quantile.png")));
		 * resultValuePanel.add(quantileLabel2);
		 * 
		 * quantileLabel3 = new JLabel("value");
		 * quantileLabel3.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(quantileLabel3);
		 * 
		 * absoluteLabel1 = new JLabel("Absolut");
		 * absoluteLabel1.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(absoluteLabel1);
		 * 
		 * absoluteLabel2 = new JLabel(""); absoluteLabel2.setSize(new
		 * Dimension(100, 35)); absoluteLabel2.setPreferredSize(new
		 * Dimension(100, 35)); absoluteLabel2.setMinimumSize(new Dimension(100,
		 * 35)); absoluteLabel2.setMaximumSize(new Dimension(100, 35));
		 * absoluteLabel2.setIcon(new
		 * ImageIcon(OutputPanel.class.getResource("/view/absolute.png")));
		 * absoluteLabel2.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(absoluteLabel2);
		 * 
		 * absoluteLabel3 = new JLabel("value");
		 * absoluteLabel3.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(absoluteLabel3);
		 * 
		 * varianceLabel1 = new JLabel("Varianz");
		 * varianceLabel1.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(varianceLabel1);
		 * 
		 * varianceLabel2 = new JLabel("");
		 * varianceLabel2.setVerticalAlignment(SwingConstants.TOP);
		 * varianceLabel2.setSize(new Dimension(100, 35));
		 * varianceLabel2.setPreferredSize(new Dimension(100, 35));
		 * varianceLabel2.setMinimumSize(new Dimension(100, 35));
		 * varianceLabel2.setMaximumSize(new Dimension(100, 35));
		 * varianceLabel2.setIcon(new
		 * ImageIcon(OutputPanel.class.getResource("/view/variance.png")));
		 * resultValuePanel.add(varianceLabel2);
		 * 
		 * varianceLabel3 = new JLabel("value");
		 * varianceLabel3.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(varianceLabel3);
		 * 
		 * standardLabel1 = new JLabel("Standard");
		 * standardLabel1.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(standardLabel1);
		 * 
		 * standardLabel2 = new JLabel("");
		 * standardLabel2.setVerticalAlignment(SwingConstants.TOP);
		 * standardLabel2.setSize(new Dimension(100, 35));
		 * standardLabel2.setPreferredSize(new Dimension(100, 35));
		 * standardLabel2.setMinimumSize(new Dimension(100, 35));
		 * standardLabel2.setMaximumSize(new Dimension(100, 35));
		 * standardLabel2.setIcon(new
		 * ImageIcon(OutputPanel.class.getResource("/view/standard.png")));
		 * resultValuePanel.add(standardLabel2);
		 * 
		 * standardLabel3 = new JLabel("value");
		 * standardLabel3.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(standardLabel3);
		 * 
		 * giniLabel1 = new JLabel("Gini");
		 * giniLabel1.setVerticalAlignment(SwingConstants.TOP);
		 * resultValuePanel.add(giniLabel1);
		 * 
		 * giniLabel2 = new JLabel("");
		 * giniLabel2.setVerticalAlignment(SwingConstants.TOP);
		 * giniLabel2.setSize(new Dimension(100, 35));
		 * giniLabel2.setPreferredSize(new Dimension(100, 35));
		 * giniLabel2.setMinimumSize(new Dimension(100, 35));
		 * giniLabel2.setMaximumSize(new Dimension(100, 35));
		 * giniLabel2.setIcon(new
		 * ImageIcon(OutputPanel.class.getResource("/view/gini.png")));
		 * resultValuePanel.add(giniLabel2);
		 * 
		 * giniLabel3 = new JLabel("value"); resultValuePanel.add(giniLabel3);
		 * 
		 * 
		 * diagramContainer = new JPanel(); diagramContainer.setBorder(new
		 * LineBorder(new Color(0, 0, 0)));
		 * resultContainer.add(diagramContainer); diagramContainer.setLayout(new
		 * GridLayout(2, 1, 0, 0));
		 * 
		 * histogramPanel = new JPanel();
		 * histogramPanel.setBackground(Color.WHITE);
		 * histogramPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		 * diagramContainer.add(histogramPanel);
		 * 
		 * empiricPanel = new JPanel(); empiricPanel.setBackground(Color.WHITE);
		 * empiricPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		 * diagramContainer.add(empiricPanel);
		 * 
		 * 
		 * 
		 * histogramContainer = new JPanel(); histogramContainer.setBorder(new
		 * LineBorder(new Color(0, 0, 0)));
		 * outputTabbedPane.addTab("Histogramm", null, histogramContainer,
		 * "Histogramm anzeigen"); histogramContainer.setLayout(new
		 * BorderLayout(0, 0));
		 * 
		 * empiricContainer = new JPanel(); empiricContainer.setBorder(new
		 * LineBorder(new Color(0, 0, 0)));
		 * outputTabbedPane.addTab("Empirische Verteilung", null,
		 * empiricContainer, "Empirische Verteilung anzeigen");
		 * empiricContainer.setLayout(new BorderLayout(0, 0));
		 */

	}

	/**
	 * 
	 * @param data
	 */
	public void setData(DataHandler data)
	{
		this.removeAll();

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(20, 5));

		// this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		tabbedPane = setUpTabs(data);
		buttonContainer = setUpButtons(); // OK

		add(tabbedPane, BorderLayout.CENTER);
		add(buttonContainer, BorderLayout.SOUTH);
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private JTabbedPane setUpTabs(DataHandler data)
	{
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel overviewContainer = setUpOverviewContainer(data);
		JPanel histogramContainer = setUpHistogramContainer(data, 775, 484, 725, 434);
		JPanel empiricContainer = setUpEmpiricContainer(data, 775, 484, 725, 434);

		tabbedPane.add("\u00DCbersicht", overviewContainer);
		tabbedPane.add("Histogramm", histogramContainer);
		tabbedPane.add("Empirische Verteilung", empiricContainer);
		return tabbedPane;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private JPanel setUpOverviewContainer(DataHandler data)
	{
		JPanel overviewContainer = new JPanel();
		JPanel resultContainer = new JPanel();
		JPanel diagramContainer = new JPanel();
		JPanel histogramContainer = setUpHistogramContainer(data, 383, 200, 375, 190);
		JPanel empiricContainer = setUpEmpiricContainer(data, 383, 200, 375, 190);
		JPanel resultHeaderPanel = setUpHeaderPanel("Ergebnisse");
		JPanel resultSetPanel = new JPanel();
		GridBagLayout resultLayout = new GridBagLayout();
		String quantileString = "<html><body>";

		for (int i = 0; i < data.getResults().getQuantiles().length; i++)
		{
			quantileString += "<p>" + data.getResults().getQuantiles()[i].getValue() + "</p>";
		}
		quantileString += "</body></html>";

		GridBagConstraints titleConstraints = new GridBagConstraints();
		titleConstraints.fill = GridBagConstraints.HORIZONTAL;
		titleConstraints.anchor = GridBagConstraints.NORTHWEST;
		titleConstraints.insets = new Insets(1, 1, 1, 1);
		titleConstraints.weightx = 0.0;
		titleConstraints.gridwidth = 1;

		GridBagConstraints formulaConstraints = new GridBagConstraints();
		formulaConstraints.fill = GridBagConstraints.HORIZONTAL;
		formulaConstraints.anchor = GridBagConstraints.NORTHWEST;
		formulaConstraints.gridwidth = GridBagConstraints.RELATIVE;
		formulaConstraints.insets = new Insets(1, 1, 1, 1);

		GridBagConstraints valueConstraints = new GridBagConstraints();
		valueConstraints.fill = GridBagConstraints.HORIZONTAL;
		valueConstraints.anchor = GridBagConstraints.NORTHWEST;
		valueConstraints.weightx = 1.0;
		valueConstraints.gridwidth = GridBagConstraints.REMAINDER;
		valueConstraints.insets = new Insets(1, 1, 1, 1);

		GridBagConstraints titleQuantileConstraints = new GridBagConstraints();
		titleQuantileConstraints.fill = GridBagConstraints.BOTH;
		titleQuantileConstraints.anchor = GridBagConstraints.NORTHWEST;
		titleQuantileConstraints.insets = new Insets(1, 1, 1, 1);
		titleQuantileConstraints.weightx = 0.0;
		titleQuantileConstraints.gridwidth = 1;

		GridBagConstraints formulaQuantileConstraints = new GridBagConstraints();
		formulaQuantileConstraints.fill = GridBagConstraints.BOTH;
		formulaQuantileConstraints.anchor = GridBagConstraints.NORTHWEST;
		formulaQuantileConstraints.gridwidth = GridBagConstraints.RELATIVE;
		formulaQuantileConstraints.insets = new Insets(1, 1, 1, 1);

		GridBagConstraints valueQuantileConstraints = new GridBagConstraints();
		valueQuantileConstraints.fill = GridBagConstraints.BOTH;
		valueQuantileConstraints.anchor = GridBagConstraints.NORTHWEST;
		valueQuantileConstraints.weightx = 1.0;
		valueQuantileConstraints.weighty = 1.0;
		valueQuantileConstraints.gridwidth = GridBagConstraints.REMAINDER;
		valueQuantileConstraints.insets = new Insets(1, 1, 1, 1);

		resultSetPanel.setLayout(resultLayout);

		JLabel middleLabel1 = new JLabel("Mittelwert");
		middleLabel1.setVerticalAlignment(SwingConstants.TOP);
		middleLabel1.setFont(NORMAL);
		resultLayout.setConstraints(middleLabel1, titleConstraints);
		resultSetPanel.add(middleLabel1);
		JLabel middleLabel2 = new JLabel("");
		middleLabel2.setVerticalAlignment(SwingConstants.TOP);
		middleLabel2.setPreferredSize(new Dimension(100, 35));
		middleLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/Mittel.png")));
		resultLayout.setConstraints(middleLabel2, formulaConstraints);
		resultSetPanel.add(middleLabel2);
		JLabel middleLabel3 = new JLabel("" + data.getResults().getArithmeticMiddle());
		middleLabel3.setVerticalAlignment(SwingConstants.BOTTOM);
		middleLabel3.setFont(NORMAL);
		resultLayout.setConstraints(middleLabel3, valueConstraints);
		resultSetPanel.add(middleLabel3);

		JLabel medianLabel1 = new JLabel("Median");
		medianLabel1.setVerticalAlignment(SwingConstants.TOP);
		medianLabel1.setFont(NORMAL);
		resultLayout.setConstraints(medianLabel1, titleConstraints);
		resultSetPanel.add(medianLabel1);
		JLabel medianLabel2 = new JLabel("");
		medianLabel2.setVerticalAlignment(SwingConstants.TOP);
		medianLabel2.setPreferredSize(new Dimension(100, 35));
		medianLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/Median.png")));
		resultLayout.setConstraints(medianLabel2, formulaConstraints);
		resultSetPanel.add(medianLabel2);
		JLabel medianLabel3 = new JLabel("" + data.getResults().getMedian());
		medianLabel3.setVerticalAlignment(SwingConstants.BOTTOM);
		medianLabel3.setFont(NORMAL);
		resultLayout.setConstraints(medianLabel3, valueConstraints);
		resultSetPanel.add(medianLabel3);

		JLabel quantileLabel1 = new JLabel("Quantile");
		quantileLabel1.setVerticalAlignment(SwingConstants.TOP);
		resultLayout.setConstraints(quantileLabel1, titleQuantileConstraints);
		resultSetPanel.add(quantileLabel1);
		quantileLabel1.setFont(NORMAL);
		JLabel quantileLabel2 = new JLabel("");
		quantileLabel2.setVerticalAlignment(SwingConstants.CENTER);
		quantileLabel2.setPreferredSize(new Dimension(100, 145));
		quantileLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/Quantile.png")));
		resultLayout.setConstraints(quantileLabel2, formulaQuantileConstraints);
		resultSetPanel.add(quantileLabel2);
		JLabel quantileLabel3 = new JLabel(quantileString);
		quantileLabel3.setVerticalAlignment(SwingConstants.CENTER);
		quantileLabel3.setFont(NORMAL);
		resultLayout.setConstraints(quantileLabel3, valueQuantileConstraints);
		resultSetPanel.add(quantileLabel3);

		JLabel absoluteLabel1 = new JLabel("Absolute Abweichung");
		absoluteLabel1.setVerticalAlignment(SwingConstants.TOP);
		absoluteLabel1.setFont(NORMAL);
		resultLayout.setConstraints(absoluteLabel1, titleConstraints);
		resultSetPanel.add(absoluteLabel1);
		JLabel absoluteLabel2 = new JLabel("");
		absoluteLabel2.setVerticalAlignment(SwingConstants.TOP);
		absoluteLabel2.setPreferredSize(new Dimension(100, 35));
		absoluteLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/absolute.png")));
		resultLayout.setConstraints(absoluteLabel2, formulaConstraints);
		resultSetPanel.add(absoluteLabel2);
		JLabel absoluteLabel3 = new JLabel("" + data.getResults().getMeanAbsoluteDeviation());
		absoluteLabel3.setVerticalAlignment(SwingConstants.BOTTOM);
		absoluteLabel3.setFont(NORMAL);
		resultLayout.setConstraints(absoluteLabel3, valueConstraints);
		resultSetPanel.add(absoluteLabel3);

		JLabel varianceLabel1 = new JLabel("Varianz");
		varianceLabel1.setVerticalAlignment(SwingConstants.TOP);
		varianceLabel1.setFont(NORMAL);
		resultLayout.setConstraints(varianceLabel1, titleConstraints);
		resultSetPanel.add(varianceLabel1);
		JLabel varianceLabel2 = new JLabel("");
		varianceLabel2.setVerticalAlignment(SwingConstants.TOP);
		varianceLabel2.setPreferredSize(new Dimension(100, 35));
		varianceLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/variance.png")));
		resultLayout.setConstraints(varianceLabel2, formulaConstraints);
		resultSetPanel.add(varianceLabel2);
		JLabel varianceLabel3 = new JLabel("" + data.getResults().getVariance());
		varianceLabel3.setVerticalAlignment(SwingConstants.BOTTOM);
		varianceLabel3.setFont(NORMAL);
		resultLayout.setConstraints(varianceLabel3, valueConstraints);
		resultSetPanel.add(varianceLabel3);

		JLabel standardLabel1 = new JLabel("Standardabweichung");
		standardLabel1.setVerticalAlignment(SwingConstants.TOP);
		standardLabel1.setFont(NORMAL);
		resultLayout.setConstraints(standardLabel1, titleConstraints);
		resultSetPanel.add(standardLabel1);
		JLabel standardLabel2 = new JLabel("");
		standardLabel2.setVerticalAlignment(SwingConstants.TOP);
		standardLabel2.setPreferredSize(new Dimension(100, 35));
		standardLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/standard.png")));
		resultLayout.setConstraints(standardLabel2, formulaConstraints);
		resultSetPanel.add(standardLabel2);
		JLabel standardLabel3 = new JLabel("" + data.getResults().getStandardDeviation());
		standardLabel3.setVerticalAlignment(SwingConstants.BOTTOM);
		standardLabel3.setFont(NORMAL);
		resultLayout.setConstraints(standardLabel3, valueConstraints);
		resultSetPanel.add(standardLabel3);

		JLabel giniLabel1 = new JLabel("Gini-Koeffizient");
		giniLabel1.setVerticalAlignment(SwingConstants.TOP);
		giniLabel1.setFont(NORMAL);
		resultLayout.setConstraints(giniLabel1, titleConstraints);
		resultSetPanel.add(giniLabel1);
		JLabel giniLabel2 = new JLabel("");
		giniLabel2.setVerticalAlignment(SwingConstants.TOP);
		giniLabel2.setPreferredSize(new Dimension(100, 35));
		giniLabel2.setIcon(new ImageIcon(OutputPanel.class.getResource("/view/gini.png")));
		resultLayout.setConstraints(giniLabel2, formulaConstraints);
		resultSetPanel.add(giniLabel2);
		JLabel giniLabel3 = new JLabel("[Gini]");
		giniLabel3.setVerticalAlignment(SwingConstants.BOTTOM);
		giniLabel3.setFont(NORMAL);
		resultLayout.setConstraints(giniLabel3, valueConstraints);
		resultSetPanel.add(giniLabel3);

		resultContainer.setLayout(new BorderLayout(0, 0));
		resultSetPanel.setBackground(Color.WHITE);
		resultContainer.add(resultHeaderPanel, BorderLayout.NORTH);
		resultContainer.add(resultSetPanel, BorderLayout.CENTER);

		overviewContainer.setLayout(new GridLayout(1, 2, 0, 0));
		diagramContainer.setLayout(new GridLayout(2, 1, 0, 0));

		diagramContainer.add(histogramContainer);
		diagramContainer.add(empiricContainer);

		overviewContainer.add(resultContainer);
		overviewContainer.add(diagramContainer);

		return overviewContainer;
	}

	/*
	 * private JPanel overview_old(DataHandler data) { // Linke Seite:
	 * Ergebnisse JPanel resultOverviewPanel = new JPanel();
	 * 
	 * resultOverviewPanel.setLayout(new BoxLayout(resultOverviewPanel,
	 * BoxLayout.PAGE_AXIS));
	 * 
	 * JLabel resultHeaderLabel, middleLabel1, medianLabel1, quantileLabel1,
	 * absoluteLabel1, standardLabel1, varianceLabel1, giniLabel1, middleLabel2,
	 * medianLabel2, quantileLabel2, absoluteLabel2, varianceLabel2,
	 * standardLabel2, giniLabel2, middleLabel3, medianLabel3, quantileLabel3,
	 * absoluteLabel3, varianceLabel3, standardLabel3, giniLabel3;
	 * 
	 * // Rechte Seite: Graph Preview JPanel graphPreviewPanel = new JPanel();
	 * graphPreviewPanel.setLayout(new BoxLayout(graphPreviewPanel,
	 * BoxLayout.PAGE_AXIS)); HistogramPanel histogramPreview =
	 * GraphFactory.createHistogram(data, 0, 0, 100, 100, 100, 100);
	 * EmpiricDistributionPanel empiricPreview =
	 * GraphFactory.createEmpiricDistribution(data, 0, 0, 100, 100, 100, 100);
	 * graphPreviewPanel.add(histogramPreview);
	 * graphPreviewPanel.add(empiricPreview);
	 * 
	 * // Hauptpanel: Container für links und rechts JPanel result = new
	 * JPanel(new FlowLayout(FlowLayout.LEFT)); result.setPreferredSize(new
	 * Dimension(200, 500)); result.add(resultOverviewPanel);
	 * result.add(graphPreviewPanel);
	 * 
	 * return result; }
	 */

	/**
	 * 
	 * @param data
	 * @return
	 */
	private JPanel setUpHistogramContainer(DataHandler data, int width, int height, int chartWidth, int chartHeight)
	{
		JPanel histogramContainer = new JPanel();
		HistogramPanel histogramPanel = GraphFactory.createHistogram(data, 0, 0, width, height, chartWidth, chartHeight);

		histogramContainer.setLayout(new BorderLayout(0, 0));
		histogramContainer.add(setUpHeaderPanel("Histogramm"), BorderLayout.NORTH);
		histogramContainer.add(histogramPanel, BorderLayout.CENTER);

		return histogramContainer;
	}

	/**
	 * 
	 * @param data
	 * @return
	 */
	private JPanel setUpEmpiricContainer(DataHandler data, int width, int height, int chartWidth, int chartHeight)
	{
		JPanel empiricContainer = new JPanel();
		EmpiricDistributionPanel empiricPanel = GraphFactory.createEmpiricDistribution(data, 0, 0, width, height, chartWidth,
				chartHeight);

		empiricContainer.setLayout(new BorderLayout(0, 0));
		empiricContainer.add(setUpHeaderPanel("Empirische Verteilung"), BorderLayout.NORTH);
		empiricContainer.add(empiricPanel, BorderLayout.CENTER);

		return empiricContainer;
	}

	/**
	 * Set up and initialize the buttonContainer.
	 * 
	 * @return the buttonPanel that contains the buttons changeValueButton and
	 *         newCalculationButton
	 */
	private JPanel setUpButtons()
	{
		FlowLayout buttonLayout = new FlowLayout(FlowLayout.CENTER, 250, 10);
		JPanel buttonContainer = new JPanel(buttonLayout);
		changeValueButton = new JButton("Werte \u00E4ndern");
		changeValueButton.addActionListener(changeValueAction);
		buttonContainer.add(changeValueButton);

		newCalculationButton = new JButton("Neue Berechnung");
		newCalculationButton.addActionListener(newCalculationAction);
		buttonContainer.add(newCalculationButton);

		return buttonContainer;
	}

	private JPanel setUpHeaderPanel(String title)
	{
		JPanel headerPanel = new JPanel();
		JLabel headerLabel = new JLabel(title);

		headerPanel.setBackground(RED);
		headerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerLabel.setFont(NORMAL);
		headerPanel.add(headerLabel);

		return headerPanel;
	}

	private ActionListener newCalculationAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			InitializeDialog dialog = new InitializeDialog();
		}
	};

	private ActionListener changeValueAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			MainFrame.switchToInputPanel();
		}
	};
}