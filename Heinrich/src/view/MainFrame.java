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
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private InputPanel inputPanel;
	private OutputFrame outputFrame;

	/**
	 * Launch the application.
	 */
	public static void startFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 800, 600);
		setTitle("Statistik-Projekt 2017");

		inputPanel = new InputPanel();
		outputFrame = new OutputFrame();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		setContentPane(contentPane);
		switchPane(inputPanel.get());
	}

	public void switchPane(JPanel contentPane) {
		this.contentPane.removeAll();
		this.contentPane.add(contentPane);
		this.contentPane.revalidate();
		this.contentPane.repaint();
	}

}
