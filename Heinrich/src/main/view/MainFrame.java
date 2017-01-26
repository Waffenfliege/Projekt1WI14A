package main.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{

	// private JPanel contentPane;
	private InputFrame inputFrame;

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
	 * Create the frame.
	 */
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 600);

		inputFrame = new InputFrame();

		/* 
		 * contentPane = new JPanel();
		 * contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		 * contentPane.setLayout(new BorderLayout(0, 0));
		 * setContentPane(contentPane);
		 */
		
		setContentPane(inputFrame.getMainContentPane());
	}

}
