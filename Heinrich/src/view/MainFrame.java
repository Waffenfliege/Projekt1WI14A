package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import data.DataHandler;

@SuppressWarnings("serial")
/**
 * 
 * Manages the MainFrame of the Application.
 * 
 * MainFrame is able to switch freely between the given JPanels inputPanel and
 * outputPanel
 * 
 * @author Jan Sauerland, Mathias Engmann
 *
 */
public class MainFrame extends JFrame
{

	private static JPanel contentPanel;
	private static InputPanel inputPanel;
	private static OutputPanel outputPanel;
	private static DataHandler data;
	private final static Color RED = new Color(175, 22, 20);

	/**
	 * Launch the application.
	 */
	public void startFrame()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame and set the inputPanel as the initial contentPanel.
	 */
	public MainFrame()
	{
		super();
		data = new DataHandler();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 600);
		setTitle("Statistik-Projekt 2017");
		setResizable(false);

		inputPanel = new InputPanel();
		outputPanel = new OutputPanel();

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPanel);
		switchToInputPanel();

		this.addWindowListener(new WindowAdapter()
		{
			public void windowOpened(WindowEvent e)
			{
				inputPanel.resetFocus();
			}
		});
	}

	/**
	 * Change the current shown contentPanel of the MainFrame to the given
	 * JPanel Object.
	 * 
	 * @param panelToShow
	 *            JPanel Object that shall be shown in the MainFrame
	 */
	private static void switchPanel(JPanel panelToShow)
	{
		contentPanel.removeAll();
		contentPanel.add(panelToShow);
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	/**
	 * Change the current shown contentPanel of the MainFrame to the current
	 * outputPanel.
	 * 
	 */
	public static void switchToOutputPanel()
	{
		outputPanel.setData(data);
		switchPanel(outputPanel);
	}

	/**
	 * Change the current shown contentPanel of the MainFrame to the current
	 * inputPanel according to the given parameter.
	 * 
	 * @param resetValues
	 *            determines whether or not the values in the inputPanel shall
	 *            be reset.
	 * @see switchPanel()
	 */
	public static void switchToInputPanel()
	{

		switchPanel(inputPanel);
	}

	/**
	 * Return the inputPanel.
	 * 
	 * @return InputPanel inputPanel
	 */
	public static InputPanel getInputPanel()
	{
		return inputPanel;
	}

	/**
	 * Return the outputPanel.
	 * 
	 * @return OutputPanel outputPanel
	 */
	public static OutputPanel getOutputPanel()
	{
		return outputPanel;
	}

	/**
	 * Return the dataHandler.
	 * 
	 * @return DataHandler dataHandler
	 */
	public static DataHandler getDataHandler()
	{
		return data;
	}

	/**
	 * Return the defined Color Red.
	 * 
	 * @return Color Red
	 */
	public final static Color getRed()
	{
		return RED;
	}
}
