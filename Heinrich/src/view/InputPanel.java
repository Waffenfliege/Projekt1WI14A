package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Cursor;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

public class InputPanel extends JPanel {
	// private JTable table;
	private JTextField leftClassBorderField;
	private JTextField rightClassBorderField;
	private JTextField quantityField;

	/**
	 * Create the panel.
	 */
	public InputPanel() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(20, 5));

		JPanel inputContainer = new JPanel();
		inputContainer.setBorder(new EmptyBorder(5, 0, 5, 5));
		inputContainer.setBackground(Color.WHITE);
		add(inputContainer, BorderLayout.CENTER);
		inputContainer.setLayout(new BorderLayout(0, 0));

		JPanel inputPane = new JPanel();
		inputPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		inputPane.setBackground(Color.WHITE);
		inputContainer.add(inputPane, BorderLayout.CENTER);

		JPanel classPane = new JPanel();
		classPane.setBackground(Color.WHITE);
		classPane.setAlignmentY(Component.TOP_ALIGNMENT);
		classPane.setLayout(new BorderLayout(0, 0));

		JLabel classLabel = new JLabel("Klasse K definieren");
		classLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		classLabel.setFont(new Font("Arial", Font.BOLD, 16));
		classLabel.setHorizontalAlignment(SwingConstants.CENTER);
		classPane.add(classLabel, BorderLayout.NORTH);

		JPanel classInputPane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) classInputPane.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(40);
		classInputPane.setBackground(Color.WHITE);
		classInputPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		classPane.add(classInputPane, BorderLayout.CENTER);

		JLabel leftClassBorderLabel = new JLabel(" ( ");
		leftClassBorderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		leftClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		leftClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		leftClassBorderLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(leftClassBorderLabel);

		leftClassBorderField = new JTextField();
		leftClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		leftClassBorderField.setFont(new Font("Arial", Font.PLAIN, 26));
		leftClassBorderField.setSize(new Dimension(100, 60));
		classInputPane.add(leftClassBorderField);
		leftClassBorderField.setColumns(5);

		JLabel classSeparatorLabel = new JLabel(" , ");
		classSeparatorLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(classSeparatorLabel);

		rightClassBorderField = new JTextField();
		rightClassBorderField.setMargin(new Insets(10, 5, 10, 5));
		rightClassBorderField.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(rightClassBorderField);
		rightClassBorderField.setColumns(5);

		JLabel rightClassBorderLabel = new JLabel(" ] ");
		rightClassBorderLabel.setVerticalTextPosition(SwingConstants.TOP);
		rightClassBorderLabel.setVerticalAlignment(SwingConstants.TOP);
		rightClassBorderLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		rightClassBorderLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rightClassBorderLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		classInputPane.add(rightClassBorderLabel);

		JPanel quantityPane = new JPanel();
		quantityPane.setBorder(null);
		quantityPane.setBackground(Color.WHITE);
		quantityPane.setLayout(new BorderLayout(0, 0));

		JLabel quantityLabel = new JLabel("Absolute H\u00E4ufigkeit h");
		quantityLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityLabel.setFont(new Font("Arial", Font.BOLD, 16));
		quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quantityPane.add(quantityLabel, BorderLayout.NORTH);

		JPanel quantityInputPane = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) quantityInputPane.getLayout();
		flowLayout_1.setVgap(40);
		flowLayout_1.setHgap(10);
		quantityInputPane.setBackground(Color.WHITE);
		quantityInputPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		quantityPane.add(quantityInputPane, BorderLayout.CENTER);

		JLabel smallQuantityLabel = new JLabel("h (K)  = ");
		smallQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPane.add(smallQuantityLabel);

		quantityField = new JTextField();
		quantityField.setMargin(new Insets(10, 5, 10, 5));
		quantityField.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPane.add(quantityField);
		quantityField.setColumns(5);

		JLabel quantitySumLabel = new JLabel(" n = 0");
		quantitySumLabel.setFont(new Font("Arial", Font.PLAIN, 26));
		quantityInputPane.add(quantitySumLabel);
		inputPane.setLayout(new GridLayout(2, 1, 10, 0));
		inputPane.add(classPane);
		inputPane.add(quantityPane);

		JPanel buttonContainer = new JPanel();
		buttonContainer.setBorder(null);
		buttonContainer.setBackground(Color.WHITE);
		inputContainer.add(buttonContainer, BorderLayout.SOUTH);
		buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 15));

		JButton lastClassButton = new JButton("Vorherige Klasse");
		lastClassButton.addActionListener(lastClassAction);
		buttonContainer.add(lastClassButton);

		JButton calculateButton = new JButton("Berechnen");
		lastClassButton.addActionListener(calculateAction);
		buttonContainer.add(calculateButton);

		JButton nextClassButton = new JButton("N\u00E4chste Klasse");
		lastClassButton.addActionListener(nextClassAction);
		buttonContainer.add(nextClassButton);

		JPanel tableContainer = new JPanel();
		tableContainer.setBorder(new EmptyBorder(5, 5, 5, 0));
		tableContainer.setBackground(Color.WHITE);
		add(tableContainer, BorderLayout.WEST);
		tableContainer.setLayout(new BorderLayout(5, 5));

		final JTable table = new JTable(){
			public boolean getScrollableTracksViewportWidth() {
				return getPreferredSize().width < getParent().getWidth();
			}
		};
		table.setBackground(UIManager.getColor("Label.background"));
		table.setIntercellSpacing(new Dimension(2, 2));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "(a , b]", "h", "r"},
				{"2", "", "", ""},
				{"3", "", "", ""},
				{"4", null, null, null},
				{"5", null, null, null},
				{"6", null, null, null},
				{"7", null, null, null},
				{"8", null, null, null},
				{"9", null, null, null},
				{"10", null, null, null},
				{"11", null, null, null},
				{"12", null, null, null},
				{"13", null, null, null},
				{"14", null, null, null},
				{"15", null, null, null},
				{"16", null, null, null},
				{"17", null, null, null},
				{"18", null, null, null},
				{"19", null, null, null},
				{"20", null, null, null},
			},
			new String[] {
				"j", "K(j)", "h(Kj)", "r(Kj)"
			}
		));
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(0).setMaxWidth(30);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(75);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.setFont(new Font("Arial", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setBorder(null);
		tableScrollPane.setBackground(Color.WHITE);
		tableScrollPane.setPreferredSize(new Dimension(152, 200));
		tableContainer.add(tableScrollPane);
	}
	
	private ActionListener lastClassAction = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO: Action bei Button "vorherige Klasse"
			
		}
	};
	private ActionListener nextClassAction = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO: Action bei Button "nächste Klasse"
		}
	};
	private ActionListener calculateAction = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			// TODO: Action bei Button "Berechnen"
		}
	};
	
	public InputPanel get() {
		return this;
	}

}

/*public void setInputFrame() // erstes Initialisieren der Anzeige
{
	mainContentPane.add(tableContentPane);
	mainContentPane.add(inputContentPane);
}

public void setInputFrame(int[] param1) // Anzeige entsprechend des
										// Input-Arrays
{
	mainContentPane.add(tableContentPane);
	mainContentPane.add(inputContentPane);
}

public JPanel getMainContentPane()
{
	return mainContentPane;
}*/
