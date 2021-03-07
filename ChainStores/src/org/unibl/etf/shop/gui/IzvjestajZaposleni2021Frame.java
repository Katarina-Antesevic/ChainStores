package org.unibl.etf.shop.gui;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class IzvjestajZaposleni2021Frame extends JFrame{
	private Vector<Object> kolone;
	private Vector<Vector<Object>> podaci;

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public IzvjestajZaposleni2021Frame() {
		kolone = new Vector<Object>();
		kolone.add("JMB");
		kolone.add("Prezime");
		kolone.add("Prezime");
		kolone.add("Plata");
		kolone.add("Datum zaposlenja");

		setTitle("Zaposleni u 2021. godini");

		podaci = Utilities.getDataAccessFactory().getIzvjestajiDataAccess().zaposleni2021();
			

		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		this.contentPane.add(getScrollPane(), BorderLayout.CENTER);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JTable getTable() {
		if (table == null) {
			table = new JTable(new DefaultTableModel(podaci, kolone) {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
			table.getColumnModel().getColumn(2).setPreferredWidth(250);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
			table.getColumnModel().getColumn(4).setPreferredWidth(250);
		}
		return table;
	}

}
