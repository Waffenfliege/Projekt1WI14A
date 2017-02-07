
package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import data.DataHandler;
import graphs.EmpiricDistributionPanel;
import graphs.GraphFactory;
import graphs.HistogramPanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Manages the OutputPanel and its components.
 * 
 * Used to view the calculation results.
 * 
 * @author Jan Sauerland, Lukas Moser, Mathias Engmann.
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

	/**
	 * Super-constructor to initialize an OutputPanel Object.
	 */
	public OutputPanel()
	{
		super();
	}

	/**
	 * Set up and initialize the OutputPanel.
	 * 
	 * @param data
	 *            the DataHandler that contains all needed data for the
	 *            calculation.
	 */
	public void setData(DataHandler data)
	{
		this.removeAll();

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(20, 5));

		tabbedPane = setUpTabs(data);
		buttonContainer = setUpButtons(); 

		add(tabbedPane, BorderLayout.CENTER);
		add(buttonContainer, BorderLayout.SOUTH);
	}

	/**
	 * Set up and initialize the tabbedPane.
	 * 
	 * @param data
	 *            the DataHandler that contains all needed data for the
	 *            calculation.
	 * @return the JTabbedPane tabbedPane that contains the JPanels
	 *         overviewContainer, histogramContainer and empiricContainer.
	 */
	private JTabbedPane setUpTabs(DataHandler data)
	{
		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel overviewContainer = setUpOverviewContainer(data);
		JPanel histogramContainer = setUpHistogramContainer(data, 775, 465, 675, 365, true);
		JPanel empiricContainer = setUpEmpiricContainer(data, 775, 465, 675, 365, true);

		tabbedPane.add("\u00DCbersicht", overviewContainer);
		tabbedPane.add("Histogramm", histogramContainer);
		tabbedPane.add("Empirische Verteilung", empiricContainer);
		return tabbedPane;
	}

	/**
	 * Set up and initialize the overviewContainer.
	 * 
	 * Contains all the calculation results in their respective JLabels.
	 * 
	 * @param data
	 *            the DataHandler that contains all needed data for the
	 *            calculation.
	 * @return the JPanel overviewContainer
	 */
	private JPanel setUpOverviewContainer(DataHandler data)
	{
		JPanel overviewContainer = new JPanel();
		JPanel resultContainer = new JPanel();
		JPanel diagramContainer = new JPanel();
		JPanel histogramContainer = setUpHistogramContainer(data, 383, 220, 363, 180, false);
		JPanel empiricContainer = setUpEmpiricContainer(data, 383, 220, 363, 180, false);
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

	/**
	 * Set up the histogramContainer (JPanel) that contains the generated
	 * histogram.
	 * 
	 * @param data
	 *            the DataHandler that contains all needed data for the
	 *            calculation.
	 * @return the JPanel histogramContainer
	 * @see GraphFactory
	 */
	private JPanel setUpHistogramContainer(DataHandler data, int width, int height, int chartWidth, int chartHeight, boolean isDetailed)
	{
		JPanel histogramContainer = new JPanel();
		HistogramPanel histogramPanel;
		if(!isDetailed){
			histogramPanel = GraphFactory.createHistogram(data, 10, 15, width, height, chartWidth, chartHeight, isDetailed);
		}
	
		else{
			histogramPanel = GraphFactory.createHistogram(data, 50, 50, width, height, chartWidth, chartHeight, isDetailed);
		}
		histogramContainer.setLayout(new BorderLayout(0, 0));
		histogramContainer.add(setUpHeaderPanel("Histogramm"), BorderLayout.NORTH);
		histogramContainer.add(histogramPanel, BorderLayout.CENTER);

		return histogramContainer;
	}

	/**
	 * Set up the empiricContainer (JPanel) that contains the generated empiric
	 * distribution diagram.
	 * 
	 * @param data
	 *            the DataHandler that contains all needed data for the
	 *            calculation.
	 * @return the JPanel empiricContainer
	 * @see GraphFactory
	 */
	private JPanel setUpEmpiricContainer(DataHandler data, int width, int height, int chartWidth, int chartHeight, boolean isDetailed)
	{
		JPanel empiricContainer = new JPanel();
		EmpiricDistributionPanel empiricPanel;
		if(!isDetailed)
		{
			empiricPanel = GraphFactory.createEmpiricDistribution(data, 10, 15, width, height, chartWidth,
					chartHeight, isDetailed);
		}
		
		else{
			empiricPanel = GraphFactory.createEmpiricDistribution(data, 50, 50, width, height, chartWidth,
					chartHeight, isDetailed);
		}
	

		empiricContainer.setLayout(new BorderLayout(0, 0));
		empiricContainer.add(setUpHeaderPanel("Empirische Verteilung"), BorderLayout.NORTH);
		empiricContainer.add(empiricPanel, BorderLayout.CENTER);

		return empiricContainer;
	}

	/**
	 * Set up and initialize the buttonContainer.
	 * 
	 * @return the JPanel buttonPanel that contains the buttons
	 *         changeValueButton and newCalculationButton
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

	/**
	 * Set up and initialize a headerPanel that contains a JLabel with the given
	 * String title
	 * 
	 * @param title
	 *            the String for the headerLabel
	 * @return the JPanel headerPanel
	 */
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
			InitializeDialog.start();
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