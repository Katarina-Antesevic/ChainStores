package org.unibl.etf.shop.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.unibl.etf.shop.entity.Prodavnica;
import org.unibl.etf.shop.entity.Trgovac;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class IzvjestajProdavnicaMinFrame extends JFrame{
private List<Trgovac> trgovci;

	

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JLabel lblOJ;
	//private JLabel lblBrojPoste;
	//private JTextField tfNaziv;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox cbProdavnica;

	/**
	 * Create the frame.
	 */
	public IzvjestajProdavnicaMinFrame() {
		
		trgovci = Utilities.getDataAccessFactory().getTrgovacDataAccess().trgovciSvi()
				;

		initialize();

	}

	

	private void osveziTabelu() {
		
			int idOJ = 0;
			if (cbProdavnica.getSelectedItem() != null)
				idOJ = ((Prodavnica) cbProdavnica
						.getSelectedItem()).getOrganizacionaJedinica().getId_OrganizacioneJedinice();

			trgovci = Utilities.getDataAccessFactory().getIzvjestajiDataAccess().trgovciMin(idOJ);
					

			IzvjestajProdavnicaMaxTableModel ftm = (IzvjestajProdavnicaMaxTableModel) table.getModel();
			ftm.setPodaci(trgovci);
			ftm.fireTableDataChanged();
		
	}

	private void osveziTabeluZaSve() {
		
		trgovci = Utilities.getDataAccessFactory().getTrgovacDataAccess().trgovciSvi();

		IzvjestajProdavnicaMaxTableModel ftm = (IzvjestajProdavnicaMaxTableModel) table.getModel();
			ftm.setPodaci(trgovci);
			ftm.fireTableDataChanged();
		
	}

	private void initialize() {
		setTitle("Minimalna plata trgovca");
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
		this.contentPane.add(getPanel(), BorderLayout.NORTH);
		this.contentPane.add(getPanelPodaci(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanelOpcije(), BorderLayout.NORTH);
			panel.add(getPanelPretraga(), BorderLayout.NORTH);
		}
		return panel;
	}

	private JPanel getPanelPodaci() {
		if (panelPodaci == null) {
			panelPodaci = new JPanel();
			panelPodaci.setLayout(new BorderLayout(0, 0));
			panelPodaci.add(getScrollPane(), BorderLayout.CENTER);
		}
		return panelPodaci;
	}

	private JPanel getPanelOpcije() {
		if (panelOpcije == null) {
			panelOpcije = new JPanel();
			panelOpcije.setLayout(null);
			panelOpcije.setPreferredSize(new Dimension(200, 68));
		}
		return panelOpcije;
	}

	private JPanel getPanelPretraga() {
		if (panelPretraga == null) {
			panelPretraga = new JPanel();
			panelPretraga.setBorder(new TitledBorder(null, "Pretraga",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panelPretraga.setLayout(null);
			panelPretraga.setPreferredSize(new Dimension(200, 70));
			panelPretraga.add(getcbProdavnica());
			panelPretraga.add(getLblOJ());
			panelPretraga.add(getBtnPretraziti());
			panelPretraga.add(getBtnPrikazatiSve());
		}
		return panelPretraga;
	}

	
	

	

	

	private JLabel getLblOJ() {
		if (lblOJ == null) {
			lblOJ = new JLabel("Trgovina:");
			lblOJ.setBounds(10, 20, 254, 14);
		}
		return lblOJ;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getcbProdavnica() {
		if (cbProdavnica == null) {
			cbProdavnica = new JComboBox(Utilities.getDataAccessFactory()
					.getProdavnicaDataAccess().prodavniceSve()
					.toArray(new Prodavnica[] {}));
			cbProdavnica.setBounds(10, 37, 254, 20);
			cbProdavnica.setSelectedIndex(-1);
		}
		return cbProdavnica;
	}
	
	

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
						osveziTabelu();
				}
			});
			btnPretraziti.setBounds(538, 36, 100, 23);
		}
		return btnPretraziti;
	}

	private JButton getBtnPrikazatiSve() {
		if (btnPrikazatiSve == null) {
			btnPrikazatiSve = new JButton("Prikazati sve");
			btnPrikazatiSve.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					osveziTabeluZaSve();
				}
			});
			btnPrikazatiSve.setBounds(648, 36, 100, 23);
		}
		return btnPrikazatiSve;
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
			table = new JTable(new IzvjestajProdavnicaMaxTableModel(trgovci));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);	
			table.getColumnModel().getColumn(2).setPreferredWidth(250);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);	
			
		}
		return table;
	}


}
