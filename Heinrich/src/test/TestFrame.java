package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import java.awt.Rectangle;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class TestFrame extends JFrame
{

	private JPanel mainContentPane;
	private JTable tableContent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TestFrame frame = new TestFrame();
					frame.setTitle("Haupteingabemaske");
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
	public TestFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		mainContentPane = new JPanel();
		mainContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainContentPane.setLayout(null);

		JScrollPane tableContentPane = new JScrollPane();
		tableContentPane.setBounds(5, 5, 250, 540);

		String[][] data = new String[10][4];
		for (int i = 0; i < 10; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				data[i][j] = "abc" + i;
			}
		}
		String[] columnNames = { "j", "Kj", "h(Kj)", "r(Kj)" };
		TableModel tableModel = new DefaultTableModel(data, columnNames);
		tableContent = new JTable(tableModel);
		tableContentPane.add(tableContent);

		JPanel inputContentPane = new JPanel();
		inputContentPane.setBounds(260, 5, 510, 540);
		inputContentPane.setLayout(null);

		JLabel labelTest = new JLabel("Dies ist ein kleiner Test!");
		inputContentPane.add(labelTest);

		mainContentPane.add(tableContentPane);
		mainContentPane.add(inputContentPane);
		setContentPane(mainContentPane);
	}

}
