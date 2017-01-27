package view;

import javax.swing.*;

public class OutputFrame
{
	private JPanel mainContentPane;
	private JPanel overviewPane;
	private JPanel histogramPane;
	private JPanel empiricPane;
	public static final int OVERVIEW = 1;
	public static final int HISTOGRAM = 2;
	public static final int EMPIRIC = 3;

	public OutputFrame()
	{
		mainContentPane = new JPanel();
		overviewPane = new JPanel();
		histogramPane = new JPanel();
		empiricPane = new JPanel();
		setOutputFrame();
	}

	public void setOutputFrame()
	{
		mainContentPane.add(overviewPane);
	}

	public void setOutputFrame(int slide)
	{
		switch (slide)
		{
		case OVERVIEW:
			switchPane(overviewPane);
			break;
		case HISTOGRAM:
			switchPane(histogramPane);
			break;
		case EMPIRIC:
			switchPane(empiricPane);
			break;
		default:
			switchPane(overviewPane);
			break;
		}
	}

	public void switchPane(JPanel contentPane)
	{
		this.mainContentPane.removeAll();
		this.mainContentPane.add(contentPane);
		this.mainContentPane.revalidate();
		this.mainContentPane.repaint();
	}
}
