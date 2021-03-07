package org.unibl.etf.shop.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
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

import org.unibl.etf.shop.entity.Prodavnica;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class ProdavniceFrame extends JFrame {
	private ProdavniceFrame ovaj;
	private List<Prodavnica> prodavnica;

	private Prodavnica odabranaProdavnica;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	private JLabel lblNaziv;
	//private JLabel lblBrojPoste;
	private JTextField tfNaziv;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;
	//@SuppressWarnings("rawtypes")
	//private JComboBox cbBrojPoste;

	/**
	 * Create the frame.
	 */
	public ProdavniceFrame(boolean odabirProdavnica) {
		ovaj = this;
		prodavnica = Utilities.getDataAccessFactory().getProdavnicaDataAccess()
				.prodavniceSve();

		initialize();

		if (!odabirProdavnica)
			btnPrihvatiti.setVisible(false);
	}

	public Prodavnica getOdabranaProdavnica() {
		return odabranaProdavnica;
	}

	private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfNaziv.getText())) {
			prodavnica = Utilities.getDataAccessFactory().getProdavnicaDataAccess()
					.prodavnice(tfNaziv.getText());

			ProdavnicaTableModel ftm = (ProdavnicaTableModel) table.getModel();
			ftm.setPodaci(prodavnica);
			ftm.fireTableDataChanged();
		}
	}

	private void osveziTabeluZaSve() {
		if (Utilities.isSearchPatternValid(tfNaziv.getText())) {
			prodavnica = Utilities.getDataAccessFactory().getProdavnicaDataAccess()
					.prodavniceSve();

					ProdavnicaTableModel ftm = (ProdavnicaTableModel) table.getModel();
			ftm.setPodaci(prodavnica);
			ftm.fireTableDataChanged();
		}
	}

	private void initialize() {
		setTitle("Prodavnice");
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
			panelOpcije.add(getBtnPrihvatiti());
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
			panelPretraga.add(getLblNaziv());
			panelPretraga.add(getTfNaziv());
			panelPretraga.add(getBtnPretraziti());
			panelPretraga.add(getBtnPrikazatiSve());
		}
		return panelPretraga;
	}

	private JButton getBtnDodati() {
		if (btnDodati == null) {
			btnDodati = new JButton("");
			btnDodati.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ProdavnicaDialog pd = new ProdavnicaDialog();
					pd.setVisible(true);
					if (pd.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabeluZaSve();
						JOptionPane.showMessageDialog(ovaj,
								"Nova prodavnica je uspešno dodan!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(ProdavniceFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novu prodavnicu");
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
								"Prodavnica nije odabrana!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Prodavnica odabranaProdavnica = ((ProdavnicaTableModel) table
								.getModel()).getProdavnicaAtRow(table
								.getSelectedRow());
						ProdavnicaDialog pd = new ProdavnicaDialog(odabranaProdavnica);
						pd.setVisible(true);
						if (pd.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabeluZaSve();
							JOptionPane.showMessageDialog(ovaj,
									"Prodavnica je uspešno ažurirana!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(ProdavniceFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmeniti odabranu prodavnicu");
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
								"Prodavnica nije odabrana!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Prodavnica odabranaProdavnica = ((ProdavnicaTableModel) table
								.getModel()).getProdavnicaAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabranu prodavnicu?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory().getProdavnicaDataAccess()
									.obrisiProdavnicu(
											odabranaProdavnica.getNaziv())) {
								osveziTabeluZaSve();
								JOptionPane.showMessageDialog(ovaj,
										"Prodavnica je uspešno obrisana!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Prodavnica nije uspešno obrisana!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(ProdavniceFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabranu prodavnicu");
			btnObrisati.setBounds(136, 0, 58, 58);
		}
		return btnObrisati;
	}

	private JButton getBtnPrihvatiti() {
		if (btnPrihvatiti == null) {
			btnPrihvatiti = new JButton("");
			btnPrihvatiti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (table.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(ovaj,
								"Prodavnica nije odabrana!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabranaProdavnica = ((ProdavnicaTableModel) table.getModel())
								.getProdavnicaAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(ProdavniceFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabranu prodavnicu");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}

	private JLabel getLblNaziv() {
		if (lblNaziv == null) {
			lblNaziv = new JLabel("Naziv:");
			lblNaziv.setBounds(10, 20, 254, 14);
		}
		return lblNaziv;
	}

	private JTextField getTfNaziv() {
		if (tfNaziv== null) {
			tfNaziv = new JTextField();
			tfNaziv.setText("*");
			tfNaziv.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfNaziv.setColumns(10);
			tfNaziv.setBounds(10, 37, 254, 20);
		}
		return tfNaziv;
	}

	
	

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Utilities.isSearchPatternValid(tfNaziv
							.getText())) {
						osveziTabelu();}
					else
						JOptionPane.showMessageDialog(ovaj,
								"Naziv prodavnice nije pravilno popunjen!",
								"Greška", JOptionPane.ERROR_MESSAGE);
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
			table = new JTable(new ProdavnicaTableModel(prodavnica));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);	
			
		}
		return table;
	}


}
