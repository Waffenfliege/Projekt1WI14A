package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 10.10.2012
 * @author
 */

@SuppressWarnings("serial")
public class BeispielGUI extends JFrame
{
	// Anfang Attribute
	private JLabel jLabel1 = new JLabel();
	private JLabel jLabel2 = new JLabel();
	private JTextField n_xMin = new JTextField();
	private JLabel jLabel3 = new JLabel();
	private JTextField n_sw = new JTextField();
	private JButton jButton1 = new JButton();
	private JTable tb_tab = new JTable(2, 13);
	@SuppressWarnings("unused")
	private DefaultTableModel tb_tabModel = (DefaultTableModel) tb_tab.getModel();
	private JScrollPane tb_tabScrollPane = new JScrollPane(tb_tab);
	DecimalFormat df = new DecimalFormat("0.0");
	int zaehler;
	double xMin, sw;
	// Ende Attribute

	public BeispielGUI(String title)
	{
		// Frame-Initialisierung
		super(title);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		int frameWidth = 747;
		int frameHeight = 300;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setResizable(false);
		Container cp = getContentPane();
		cp.setLayout(null);
		// Anfang Komponenten

		jLabel1.setBounds(16, 24, 288, 30);
		jLabel1.setText("Wertetabelle f(x) =x³");
		jLabel1.setFont(new Font("Arial Narrow", Font.BOLD, 22));
		jLabel1.setForeground(Color.RED);
		cp.add(jLabel1);
		jLabel2.setBounds(16, 64, 43, 19);
		jLabel2.setText("x_min:");
		cp.add(jLabel2);
		n_xMin.setBounds(72, 64, 73, 25);
		n_xMin.setText("");
		cp.add(n_xMin);
		jLabel3.setBounds(168, 64, 83, 19);
		jLabel3.setText("Schrittweite:");
		cp.add(jLabel3);
		n_sw.setBounds(264, 64, 73, 25);
		n_sw.setText("");
		cp.add(n_sw);
		jButton1.setBounds(376, 64, 337, 25);
		jButton1.setText("Programm starten");
		jButton1.setMargin(new Insets(2, 2, 2, 2));
		jButton1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				jButton1_ActionPerformed(evt);
			}
		});
		cp.add(jButton1);
		tb_tabScrollPane.setBounds(16, 112, 697, 65);
		tb_tab.setRowHeight(20);
		tb_tab.getColumnModel().getColumn(0).setHeaderValue("A");
		tb_tab.getColumnModel().getColumn(1).setHeaderValue("B");
		tb_tab.getColumnModel().getColumn(2).setHeaderValue("C");
		tb_tab.getColumnModel().getColumn(3).setHeaderValue("D");
		tb_tab.getColumnModel().getColumn(4).setHeaderValue("E");
		tb_tab.getColumnModel().getColumn(5).setHeaderValue("F");
		tb_tab.getColumnModel().getColumn(6).setHeaderValue("G");
		tb_tab.getColumnModel().getColumn(7).setHeaderValue("H");
		tb_tab.getColumnModel().getColumn(8).setHeaderValue("I");
		tb_tab.getColumnModel().getColumn(9).setHeaderValue("J");
		tb_tab.getColumnModel().getColumn(10).setHeaderValue("K");
		tb_tab.getColumnModel().getColumn(11).setHeaderValue("L");
		tb_tab.getColumnModel().getColumn(12).setHeaderValue("M");
		cp.add(tb_tabScrollPane);

		// Ende Komponenten

		setVisible(true);
	} // end of public BeispielGUI

	// Anfang Methoden

	public String f(double x)
	{
		double fwert = x * x * x;
		return df.format(fwert);// String.valueOf(fwert);
	}

	public void jButton1_ActionPerformed(ActionEvent evt)
	{
		zaehler = 0;
		tb_tab.setValueAt("x", 0, 0);
		tb_tab.setValueAt("f(x)", 1, 0);
		xMin = Double.parseDouble(n_xMin.getText());
		sw = Double.parseDouble(n_sw.getText());
		while (zaehler < 12)
		{
			tb_tab.setValueAt(df.format(xMin + zaehler * sw), 0, zaehler + 1);
			tb_tab.setValueAt(f(xMin + zaehler * sw), 1, zaehler + 1);
			zaehler = zaehler + 1;
		}
	} // end of jButton1_ActionPerformed

	// Ende Methoden

	public static void main(String[] args)
	{
		new BeispielGUI("BeispielGUI");
	} // end of main

} // end of class BeispielGUI
