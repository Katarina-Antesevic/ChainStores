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

import org.unibl.etf.shop.entity.Proizvodjac;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class ProizvodjacFrame extends JFrame {
	private ProizvodjacFrame ovaj;
	private List<Proizvodjac> proizvodjac;

	private Proizvodjac odabraniProizvodjac;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	private JLabel lblJIB;
	//private JLabel lblBrojPoste;
	private JTextField tfJIB;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;
	//@SuppressWarnings("rawtypes")
	//private JComboBox cbBrojPoste;

	/**
	 * Create the frame.
	 */
	public ProizvodjacFrame(boolean odabirProizvodjaca) {
		ovaj = this;
		proizvodjac = Utilities.getDataAccessFactory().getProizvodjacDataAccess()
				.proizvodjaciSvi();

		initialize();

		if (!odabirProizvodjaca)
			btnPrihvatiti.setVisible(false);
	}

	public Proizvodjac getOdabraniProizvodjac() {
		return odabraniProizvodjac;
	}

	private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfJIB.getText())) {
			proizvodjac = Utilities.getDataAccessFactory().getProizvodjacDataAccess()
					.proizvodjaci(tfJIB.getText());

			ProizvodjacTableModel ftm = (ProizvodjacTableModel) table.getModel();
			ftm.setPodaci(proizvodjac);
			ftm.fireTableDataChanged();
		}
	}

	private void osveziTabeluZaSve() {
		if (Utilities.isSearchPatternValid(tfJIB.getText())) {
			proizvodjac = Utilities.getDataAccessFactory().getProizvodjacDataAccess()
					.proizvodjaciSvi();

					ProizvodjacTableModel ftm = (ProizvodjacTableModel) table.getModel();
			ftm.setPodaci(proizvodjac);
			ftm.fireTableDataChanged();
		}
	}

	private void initialize() {
		setTitle("Proizvodjaci");
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
			panelPretraga.add(getLblJIB());
			panelPretraga.add(getTfJIB());
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
					ProizvodjacDialog pd = new ProizvodjacDialog();
					pd.setVisible(true);
					if (pd.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabeluZaSve();
						JOptionPane.showMessageDialog(ovaj,
								"Novi proizvodjac je uspe??no dodan!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(ProizvodjacFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novog proizvodjaca");
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
								"Proizvodjac nije odabran!", "Gre??ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Proizvodjac odabraniProizvodjac = ((ProizvodjacTableModel) table
								.getModel()).getProizvodjacAtRow(table
								.getSelectedRow());
						ProizvodjacDialog pd = new ProizvodjacDialog(odabraniProizvodjac);
						pd.setVisible(true);
						if (pd.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabeluZaSve();
							JOptionPane.showMessageDialog(ovaj,
									"Proizvodjac je uspe??no a??uriran!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(ProizvodjacFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmeniti odabranog proizvodjaca");
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
								"Proizvodjac nije odabran!", "Gre??ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Proizvodjac odabraniProizvodjac = ((ProizvodjacTableModel) table
								.getModel()).getProizvodjacAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da ??elite obrisati odabranog proizvodjaca?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory().getProizvodjacDataAccess()
									.obrisiProizvodjaca(
											odabraniProizvodjac.getJib())) {
								osveziTabeluZaSve();
								JOptionPane.showMessageDialog(ovaj,
										"Proizvodjac je uspe??no obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Proizvodjac nije uspe??no obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(ProizvodjacFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabranog proizvodjaca");
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
								"Proizvodjac nije odabran!", "Gre??ka",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabraniProizvodjac = ((ProizvodjacTableModel) table.getModel())
								.getProizvodjacAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(ProizvodjacFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabranog proizvodjaca");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}

	private JLabel getLblJIB() {
		if (lblJIB == null) {
			lblJIB = new JLabel("JIB:");
			lblJIB.setBounds(10, 20, 254, 14);
		}
		return lblJIB;
	}

	private JTextField getTfJIB() {
		if (tfJIB== null) {
			tfJIB = new JTextField();
			tfJIB.setText("*");
			tfJIB.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfJIB.setColumns(10);
			tfJIB.setBounds(10, 37, 254, 20);
		}
		return tfJIB;
	}

	/*private JLabel getLblBrojPoste() {
		if (lblBrojPoste == null) {
			lblBrojPoste = new JLabel("Broj poste:");
			lblBrojPoste.setBounds(274, 20, 254, 14);
		}
		return lblBrojPoste;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getCbBrojPoste() {
		if (cbBrojPoste == null) {
			cbBrojPoste = new JComboBox(Utilities.getDataAccessFactory()
					.getMjestoDataAccess().mjestaSva()
					.toArray(new Mjesto[] {}));
			cbBrojPoste.setBounds(274, 37, 254, 20);
			cbBrojPoste.setSelectedIndex(-1);
		}
		return cbBrojPoste;
	}
	*/
	

	private JButton getBtnPretraziti() {
		if (btnPretraziti == null) {
			btnPretraziti = new JButton("Pretra??iti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Utilities.isSearchPatternValid(tfJIB
							.getText())) {
						osveziTabelu();}
					else
						JOptionPane.showMessageDialog(ovaj,
								"JIB proizvodjaca nije pravilno popunjen!",
								"Gre??ka", JOptionPane.ERROR_MESSAGE);
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
			table = new JTable(new ProizvodjacTableModel(proizvodjac));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
			table.getColumnModel().getColumn(2).setPreferredWidth(250);
			table.getColumnModel().getColumn(3).setPreferredWidth(250);
			table.getColumnModel().getColumn(4).setPreferredWidth(250);
			table.getColumnModel().getColumn(5).setPreferredWidth(250);
			
			
		}
		return table;
	}


}
