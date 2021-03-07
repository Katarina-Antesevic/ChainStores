package org.unibl.etf.shop.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class MjestaFrame extends JFrame{
	private MjestaFrame ovaj;
	private List<Mjesto> mjesta;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JLabel lblBrojPoste;
	private JTextField tfBrojPoste;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public MjestaFrame() {
		ovaj = this;
		mjesta = Utilities.getDataAccessFactory().getMjestoDataAccess()
				.mjestaSva();

		initialize();
	}

	private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfBrojPoste.getText())) {
			mjesta = Utilities.getDataAccessFactory().getMjestoDataAccess()
					.mjesta(tfBrojPoste.getText());

			MjestoTableModel ftm = (MjestoTableModel) table.getModel();
			ftm.setPodaci(mjesta);
			ftm.fireTableDataChanged();
		}
	}

	private void osveziTabeluZaSve() {
		if (Utilities.isSearchPatternValid(tfBrojPoste.getText())) {
			mjesta = Utilities.getDataAccessFactory().getMjestoDataAccess()
					.mjestaSva();

			MjestoTableModel ftm = (MjestoTableModel) table.getModel();
			ftm.setPodaci(mjesta);
			ftm.fireTableDataChanged();
		}
	}
	

	
	private void initialize() {
		setTitle("Mjesta");
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(getPanel(), BorderLayout.NORTH);
		this.contentPane.add(getPanelPodaci(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanelOpcije(), BorderLayout.NORTH);
			panel.add(getPanelPretraga(), BorderLayout.SOUTH);
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
			panelOpcije.add(getBtnDodati());
			panelOpcije.add(getBtnIzmeniti());
			panelOpcije.add(getBtnObrisati());
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
			panelPretraga.add(getLblBrojPoste());
			panelPretraga.add(getTfBrojPoste());
			panelPretraga.add(getBtnPretraziti());
			panelPretraga.add(getBtnPrikazatiSve());
		}
		return panelPretraga;
	}

	private JButton getBtnDodati() {
		if (btnDodati == null) {
			btnDodati = new JButton("");
			btnDodati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MjestoDialog fd = new MjestoDialog();
					fd.setVisible(true);
					if (fd.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabeluZaSve();
						JOptionPane.showMessageDialog(ovaj,
								"Novo mjesto je uspešno dodano!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setToolTipText("Dodati novo mjesto");
			btnDodati.setIcon(new ImageIcon(MjestaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setBounds(0, 0, 58, 58);
		}
		return btnDodati;
	}

	private JButton getBtnIzmeniti() {
		if (btnIzmeniti == null) {
			btnIzmeniti = new JButton("");
			btnIzmeniti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Mjesto nije odabrano!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Mjesto odabranoMjesto = ((MjestoTableModel) table
								.getModel()).getMjestoAtRow(table
								.getSelectedRow());
						MjestoDialog fd = new MjestoDialog(odabranoMjesto);
						fd.setVisible(true);
						if (fd.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabeluZaSve();
							JOptionPane.showMessageDialog(ovaj,
									"Mjesto je uspešno ažurirano!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setToolTipText("Izmeniti odabrano mjesto");
			btnIzmeniti.setIcon(new ImageIcon(MjestaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setBounds(68, 0, 58, 58);
		}
		return btnIzmeniti;
	}

	private JButton getBtnObrisati() {
		if (btnObrisati == null) {
			btnObrisati = new JButton("");
			btnObrisati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Mjesto nije odabrano!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Mjesto odabranoMjesto = ((MjestoTableModel) table
								.getModel()).getMjestoAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabrano mjesto?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory()
									.getMjestoDataAccess()
									.obrisiMjesto(
											odabranoMjesto
													.getBrojPoste())) {
								osveziTabeluZaSve();
								JOptionPane.showMessageDialog(ovaj,
										"Mjesto je uspešno obrisano!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Mjesto nije uspešno obrisano!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setToolTipText("Obrisati odabrano mjesto");
			btnObrisati.setIcon(new ImageIcon(MjestaFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setBounds(136, 0, 58, 58);
		}
		return btnObrisati;
	}

	private JLabel getLblBrojPoste() {
		if (lblBrojPoste == null) {
			lblBrojPoste = new JLabel("Broj poste:");
			lblBrojPoste.setBounds(10, 21, 254, 14);
		}
		return lblBrojPoste;
	}

	private JTextField getTfBrojPoste() {
		if (tfBrojPoste == null) {
			tfBrojPoste = new JTextField();
			tfBrojPoste.setText("*");
			tfBrojPoste.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfBrojPoste.setBounds(10, 38, 254, 20);
			tfBrojPoste.setColumns(10);
		}
		return tfBrojPoste;
	}

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (Utilities.isSearchPatternValid(tfBrojPoste
							.getText()))
						osveziTabelu();
					else
						JOptionPane.showMessageDialog(ovaj,
								"Broj poste nije pravilno popunjen!",
								"Greška", JOptionPane.ERROR_MESSAGE);
				}
			});
			btnPretraziti.setBounds(274, 37, 100, 23);
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
			btnPrikazatiSve.setBounds(384, 37, 100, 23);
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
			table = new JTable(new MjestoTableModel(mjesta));
			table.setFillsViewportHeight(true);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getColumnModel().getColumn(0).setPreferredWidth(300);
			table.getColumnModel().getColumn(1).setPreferredWidth(450);
		}
		return table;
	}

}
