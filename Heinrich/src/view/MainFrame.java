package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
/**
 * 
 * @author Jan Sauerland
 *
 */
public class MainFrame extends JFrame
{

	private static JPanel contentPanel;
	private static InputPanel inputPanel;
	private static OutputPanel outputPanel;

	/**
	 * Launch the application.
	 */
	public static void startFrame()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 600);
		setTitle("Statistik-Projekt 2017");
		setResizable(false);

		inputPanel = new InputPanel();
		outputPanel = new OutputPanel();
		// TODO: OutputFrame in OutputPanel umformen & anpassen

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPanel);
		switchPanel(inputPanel.get());
	}

	/**
	 * Change the current shown contentPanel of the MainFrame to the given
	 * JPanel Object.
	 * 
	 * @param content
	 *            JPanel Object that shall be shown in the MainFrame
	 */
	public static void switchPanel(JPanel content)
	{
		contentPanel.removeAll();
		contentPanel.add(content);
		contentPanel.revalidate();
		contentPanel.repaint();
		// TODO: Prüfen, ob switchPane() in der Form funktioniert
	}

	/**
	 * Change the current shown contentPanel of the MainFrame to the current
	 * outputPanel.
	 * 
	 * @see switchPanel()
	 */
	public static void switchToOutputPanel()
	{
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
	public static void switchToInputPanel(boolean resetValues)
	{
		// TODO: Ablaufsteuerung einfügen
		switchPanel(inputPanel);
	}

	/**
	 * Return the inputPanel.
	 * 
	 * @return the inputPanel
	 */
	public static InputPanel getInputPanel()
	{
		return inputPanel;
	}

	/**
	 * Return the inputPanel.
	 * 
	 * @return the inputPanel
	 */
	public static OutputPanel getOutputPanel()
	{
		return outputPanel;
	}
}
