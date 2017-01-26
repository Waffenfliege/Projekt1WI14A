package main.view;

import javax.swing.*;

public class InputFrame
{
	private JPanel mainContentPane;
	private JPanel tableContentPane;
	private JPanel inputContentPane;

	public InputFrame()
	{
		mainContentPane = new JPanel();
		tableContentPane = new JPanel();
		inputContentPane = new JPanel();
		setInputFrame();
	}

	public void setInputFrame() // erstes Initialisieren der Anzeige
	{
		mainContentPane.add(tableContentPane);
		mainContentPane.add(inputContentPane);
	}

	public void setInputFrame(int[] param1) // Anzeige entsprechend des Input-Arrays
	{ 
		mainContentPane.add(tableContentPane);
		mainContentPane.add(inputContentPane);
	}

	public JPanel getMainContentPane()
	{
		return mainContentPane;
	}
}
