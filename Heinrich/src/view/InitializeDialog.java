package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
		setMinimumSize(new Dimension(300, 200));
		setMaximumSize(new Dimension(300, 200));
		setModal(true);
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel zHeaderLabel = new JLabel("Wollen Sie wirklich fortfahren?");
		zHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanel.add(zHeaderLabel);

		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 20));

		JButton okButton = new JButton("Ja");
		okButton.setActionCommand("Ja");
		okButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				MainFrame.getDataHandler().getList().clear();
				MainFrame.getDataHandler().initialize();
				InputPanel.initialize();
				MainFrame.switchToInputPanel();
				dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Nein");
		cancelButton.setActionCommand("Nein");
		cancelButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent actionEvent)
			{
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	/**
	 * Start a new InitializeDialog.
	 */
	public static void start()
	{
		new InitializeDialog();
	}
}