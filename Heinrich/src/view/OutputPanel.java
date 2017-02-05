package view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;

import data.DataHandler;
import graphs.EmpiricDistributionPanel;
import graphs.GraphFactory;
import graphs.HistogramPanel;

import java.awt.Color;
import java.awt.GridLayout;
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

	private final static Color RED = new Color(175,22,20);


	private JButton changeValueButton;
	private JButton newCalculationButton;
	
	public OutputPanel()
	{
		super();
	
/*
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

	

		histogramContainer = new JPanel();
		histogramContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputTabbedPane.addTab("Histogramm", null, histogramContainer, "Histogramm anzeigen");
		histogramContainer.setLayout(new BorderLayout(0, 0));

		empiricContainer = new JPanel();
		empiricContainer.setBorder(new LineBorder(new Color(0, 0, 0)));
		outputTabbedPane.addTab("Empirische Verteilung", null, empiricContainer, "Empirische Verteilung anzeigen");
		empiricContainer.setLayout(new BorderLayout(0, 0));*/

	}

	public void setData(DataHandler data){
		
		this.removeAll();
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(20, 5));

		this.setLayout(new BoxLayout( this, BoxLayout.PAGE_AXIS));
		
		tabbedPane = setUpTabs(data);
		buttonContainer = setUpButtons();
		
		this.add(tabbedPane);
		this.add(buttonContainer);
	}
	
	public JTabbedPane setUpTabs(DataHandler data){
		JTabbedPane result = new JTabbedPane();

		JPanel resultsDetailContainer = setUpResultContainer(data);
		JPanel histogramDetailContainer = setUpHistogramDetailContainer(data);
		JPanel empiricDetailContainer = setUpEmpiricDetailContainer(data);
		
		result.add("Ergebnisse", resultsDetailContainer);
		result.add("Histogramm", histogramDetailContainer);
		result.add("Empirische Verteilungsfunktion", empiricDetailContainer);
		return result;
	}
	
	private JPanel setUpResultContainer(DataHandler data) {
		
		//Linke Seite: Ergebnisse
		JPanel resultOverviewPanel = new JPanel();
		
		resultOverviewPanel.setLayout(new BoxLayout(resultOverviewPanel, BoxLayout.PAGE_AXIS));
		
	    JLabel resultHeaderLabel, middleLabel1, medianLabel1, quantileLabel1, absoluteLabel1, standardLabel1,
		varianceLabel1, giniLabel1, middleLabel2, medianLabel2, quantileLabel2, absoluteLabel2, varianceLabel2,
		standardLabel2, giniLabel2, middleLabel3, medianLabel3, quantileLabel3, absoluteLabel3, varianceLabel3,
		standardLabel3, giniLabel3;
		
		
		//Rechte Seite: Graph Preview
		JPanel graphPreviewPanel = new JPanel();
		graphPreviewPanel.setLayout(new BoxLayout(graphPreviewPanel, BoxLayout.PAGE_AXIS));
		HistogramPanel histogramPreview = GraphFactory.createHistogram(MainFrame.getDataHandler(), 0, 0, 100, 100, 100, 100);
		EmpiricDistributionPanel empiricPreview = GraphFactory.createEmpiricDistribution(MainFrame.getDataHandler(),0,0,100,100,00,100);
		graphPreviewPanel.add(histogramPreview);
		graphPreviewPanel.add(empiricPreview);
		
		//Hauptpanel: Container für links und rechts
		JPanel result = new JPanel(new FlowLayout(FlowLayout.LEFT));
		result.setPreferredSize(new Dimension(200, 500));
		result.add(resultOverviewPanel);
		result.add(graphPreviewPanel);
		
		return result;
	}
	
	private HistogramPanel setUpHistogramDetailContainer(DataHandler data) {
		HistogramPanel result = GraphFactory.createHistogram(data, 25, 25, 775, 484, 725, 434);
		
		return result;
	}
	
	private JPanel setUpEmpiricDetailContainer(DataHandler data) {
		JPanel result = new JPanel();
		
		return result;
	}	

	private JPanel setUpButtons(){
		FlowLayout buttonLayout = new FlowLayout(FlowLayout.CENTER, 75, 10);
		JPanel result = new JPanel(buttonLayout);
		changeValueButton = new JButton("Werte \u00E4ndern");
		changeValueButton.addActionListener(changeValueAction);
		result.add(changeValueButton);

		newCalculationButton = new JButton("Neue Berechnung");
		newCalculationButton.addActionListener(newCalculationAction);
		result.add(newCalculationButton);

		return result;
	}

	private ActionListener newCalculationAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			String dialogString = InputDialog.startNewDialog();
			if (dialogString.equals("Ja"))
			{
				MainFrame.getDataHandler().getList().clear();
				MainFrame.getDataHandler().initialize();
				InputPanel.initialize();
				MainFrame.switchToInputPanel();
			}
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