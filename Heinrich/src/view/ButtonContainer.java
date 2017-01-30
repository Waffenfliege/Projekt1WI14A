package view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

@SuppressWarnings("serial")
public class ButtonContainer extends JPanel
{
	private JButton changeValueButton, newCalculationButton, lastClassButton, nextClassButton, calculateButton;
	private FlowLayout flowLayout;

	/**
	 * Create the panel.
	 */
	public ButtonContainer(boolean output)
	{
		if (output)
		{
			setBorder(new LineBorder(new Color(0, 0, 0)));
			flowLayout = (FlowLayout) this.getLayout();
			flowLayout.setHgap(75);
			flowLayout.setVgap(10);

			changeValueButton = new JButton("Werte \u00E4ndern");
			changeValueButton.addActionListener(changeValueAction);
			add(changeValueButton);

			newCalculationButton = new JButton("Neue Berechnung");
			newCalculationButton.addActionListener(newCalculationAction);
			add(newCalculationButton);
		} else if (!output)
		{
			setBorder(null);
			setBackground(Color.WHITE);
			setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));

			lastClassButton = new JButton("Vorherige Klasse");
			lastClassButton.addActionListener(lastClassAction);
			add(lastClassButton);

			calculateButton = new JButton("Berechnen");
			calculateButton.addActionListener(calculateAction);
			add(calculateButton);

			nextClassButton = new JButton("N\u00E4chste Klasse");
			nextClassButton.addActionListener(nextClassAction);
			add(nextClassButton);
		}
	}

	private ActionListener newCalculationAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			// TODO: Action bei Button "Neue Berechnung": Input-Panel komplett
			// neu aufrufen
			MainFrame.switchToInputPanel(true);
		}
	};

	private ActionListener changeValueAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			// TODO: Action bei Button "Werte �ndern": Input-Panel mit
			// bestehenden Daten aufrufen
			MainFrame.switchToInputPanel(false);
		}
	};

	private ActionListener lastClassAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			// TODO: Action bei Button "vorherige Klasse": Werte �berpr�fen,
			// abspeichern, Eingabefelder leeren
			MainFrame.getInputPanel().revalidate();
			MainFrame.getInputPanel().repaint();
		}
	};

	private ActionListener nextClassAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			// TODO: Action bei Button "n�chste Klasse": Werte �berpr�fen,
			// abspeichern, Eingabefelder leeren
			MainFrame.getInputPanel().revalidate();
			MainFrame.getInputPanel().repaint();
		}
	};

	private ActionListener calculateAction = new ActionListener()
	{
		public void actionPerformed(ActionEvent actionEvent)
		{
			// TODO: Action bei Button "Berechnen": Werte �berpr�fen,
			// abspeichern, OutputPanel aufrufen, Berechnete Werte anzeigen,
			// Diagramme berechnen (= Werte an LogicHandler �bergeben)
			MainFrame.switchToOutputPanel();
		}
	};
}
