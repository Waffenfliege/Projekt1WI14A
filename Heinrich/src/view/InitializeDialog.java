package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Manages a dialog to ask the user if he wants to start an new calculation.
 * 
 * @author Jan Sauerland, Mathias Engmann.
 *
 */
@SuppressWarnings("serial")
public class InitializeDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();

	/**
	 * Constructor for a new Dialog that asks if you really want to continue.
	 * 
	 * If yes, then calls up a resetted InputPanel. If no, then calls up the
	 * already existing InputPanel.
	 */
	public InitializeDialog()
	{

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		setTitle("Neue Berechnung");
		setResizable(false);
		setModal(true);
		setBounds(500, 300, 300, 150);
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel zHeaderLabel = new JLabel("Wollen Sie wirklich fortfahren?");
		zHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(zHeaderLabel);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));

		JButton okButton = new JButton("Ja");
		okButton.setActionCommand("Ja");
		okButton.addActionListener(okAction);

		JButton cancelButton = new JButton("Nein");
		cancelButton.setActionCommand("Nein");
		cancelButton.addActionListener(cancelAction);

		buttonPane.add(okButton);
		buttonPane.add(cancelButton);

		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}

	private ActionListener okAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			MainFrame.getDataHandler().getList().clear();
			MainFrame.getDataHandler().initialize();
			MainFrame.switchToInputPanel();
			InputPanel.initialize();
			dispose();
		}
	};

	private ActionListener cancelAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			dispose();
		}
	};

	/**
	 * Start a new InitializeDialog.
	 */
	public static void start()
	{
		new InitializeDialog();
	}
}
