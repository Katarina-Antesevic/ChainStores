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
//import javax.swing.JComboBox;
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

//import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.Zaposleni;
import org.unibl.etf.shop.util.Utilities;


@SuppressWarnings("serial")
public class ZaposleniFrame extends JFrame {
	
	private ZaposleniFrame ovaj;
	private List<Zaposleni> zaposleni;

	private Zaposleni odabraniZaposleni;

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panelPodaci;
	private JPanel panelOpcije;
	private JPanel panelPretraga;
	private JButton btnDodati;
	private JButton btnIzmeniti;
	private JButton btnObrisati;
	private JButton btnPrihvatiti;
	private JLabel lblJMB;
	//private JLabel lblBrojPoste;
	private JTextField tfJMB;
	private JButton btnPretraziti;
	private JButton btnPrikazatiSve;
	private JScrollPane scrollPane;
	private JTable table;
	//@SuppressWarnings("rawtypes")
	//private JComboBox cbBrojPoste;

	/**
	 * Create the frame.
	 */
	public ZaposleniFrame(boolean odabirZaposlenog) {
		ovaj = this;
		zaposleni = Utilities.getDataAccessFactory().getZaposleniDataAccess()
				.zaposleniSvi();

		initialize();

		if (!odabirZaposlenog)
			btnPrihvatiti.setVisible(false);
	}

	public Zaposleni getOdabraniZaposleni() {
		return odabraniZaposleni;
	}

	private void osveziTabelu() {
		if (Utilities.isSearchPatternValid(tfJMB.getText())) {
			zaposleni = Utilities.getDataAccessFactory().getZaposleniDataAccess()
					.zaposleni(tfJMB.getText());

			ZaposleniTableModel ftm = (ZaposleniTableModel) table.getModel();
			ftm.setPodaci(zaposleni);
			ftm.fireTableDataChanged();
		}
	}

	private void osveziTabeluZaSve() {
		if (Utilities.isSearchPatternValid(tfJMB.getText())) {
			zaposleni = Utilities.getDataAccessFactory().getZaposleniDataAccess()
					.zaposleniSvi();

			ZaposleniTableModel ftm = (ZaposleniTableModel) table.getModel();
			ftm.setPodaci(zaposleni);
			ftm.fireTableDataChanged();
		}
	}
    //velicina one prve tabele gdje su svi
	private void initialize() {
		setTitle("Zaposleni");
		setBounds(100, 100, 930, 420);
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
			panelPretraga.add(getLblJMB());
			panelPretraga.add(getTfJMB());
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
					ZaposleniDialog pd = new ZaposleniDialog();
					pd.setVisible(true);
					if (pd.getDialogResult().equalsIgnoreCase("OK")) {
						osveziTabeluZaSve();
						JOptionPane.showMessageDialog(ovaj,
								"Novi zaposleni je uspešno dodan!", "Poruka",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			btnDodati.setIcon(new ImageIcon(ZaposleniFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Add_32.png")));
			btnDodati.setToolTipText("Dodati novog zaposlenog");
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
								"Zaposleni nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Zaposleni odabraniZaposleni = ((ZaposleniTableModel) table
								.getModel()).getZaposleniAtRow(table
								.getSelectedRow());
						ZaposleniDialog pd = new ZaposleniDialog(odabraniZaposleni);
						pd.setVisible(true);
						if (pd.getDialogResult().equalsIgnoreCase("OK")) {
							osveziTabeluZaSve();
							JOptionPane.showMessageDialog(ovaj,
									"Zaposleni je uspešno ažuriran!", "Poruka",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnIzmeniti.setIcon(new ImageIcon(ZaposleniFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Edit_32.png")));
			btnIzmeniti.setToolTipText("Izmeniti odabranog zaposlenog");
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
								"Zaposleni nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Zaposleni odabraniZaposleni = ((ZaposleniTableModel) table
								.getModel()).getZaposleniAtRow(table
								.getSelectedRow());
						int odabir = JOptionPane
								.showOptionDialog(
										ovaj,
										"Da li ste sigurni da želite obrisati odabranog zaposlenog?",
										"Potvrda brisanja",
										JOptionPane.YES_NO_OPTION,
										JOptionPane.QUESTION_MESSAGE, null,
										Utilities.YES_NO_OPTIONS,
										Utilities.YES_NO_OPTIONS[1]);
						if (odabir == JOptionPane.YES_OPTION) {
							if (Utilities
									.getDataAccessFactory()
									.getZaposleniDataAccess()
									.obrisiZaposlenog(
											odabraniZaposleni.getJmb())) {
								osveziTabeluZaSve();
								JOptionPane.showMessageDialog(ovaj,
										"Zaposleni je uspešno obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
							} else
								JOptionPane.showMessageDialog(ovaj,
										"Zaposleni nije uspešno obrisan!",
										"Poruka",
										JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});
			btnObrisati.setIcon(new ImageIcon(ZaposleniFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Delete_32.png")));
			btnObrisati.setToolTipText("Obrisati odabranog zaposlenog");
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
								"Zaposleni nije odabran!", "Greška",
								JOptionPane.ERROR_MESSAGE);
					} else {
						odabraniZaposleni = ((ZaposleniTableModel) table.getModel())
								.getZaposleniAtRow(table.getSelectedRow());
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				}
			});
			btnPrihvatiti.setIcon(new ImageIcon(ZaposleniFrame.class
					.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_32.png")));
			btnPrihvatiti.setToolTipText("Prihvatiti odabranog zaposlenog");
			btnPrihvatiti.setBounds(204, 0, 58, 58);
		}
		return btnPrihvatiti;
	}

	private JLabel getLblJMB() {
		if (lblJMB == null) {
			lblJMB = new JLabel("JMB:");
			lblJMB.setBounds(10, 20, 254, 14);
		}
		return lblJMB;
	}

	private JTextField getTfJMB() {
		if (tfJMB == null) {
			tfJMB = new JTextField();
			tfJMB.setText("*");
			tfJMB.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
						btnPretraziti.doClick();
				}
			});
			tfJMB.setColumns(10);
			tfJMB.setBounds(10, 37, 254, 20);
		}
		return tfJMB;
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
			btnPretraziti = new JButton("Pretražiti");
			btnPretraziti.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (Utilities.isSearchPatternValid(tfJMB
							.getText())) {
						osveziTabelu();}
					else
						JOptionPane.showMessageDialog(ovaj,
								"JMB zaposlenog nije pravilno popunjen!",
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
	//sirina kolona u tabeli na pocetku
	private JTable getTable() {
		if (table == null) {
			table = new JTable(new ZaposleniTableModel(zaposleni));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setFillsViewportHeight(true);
			table.getColumnModel().getColumn(0).setPreferredWidth(140);
			table.getColumnModel().getColumn(1).setPreferredWidth(110);
			table.getColumnModel().getColumn(2).setPreferredWidth(110);
			table.getColumnModel().getColumn(3).setPreferredWidth(210);
			table.getColumnModel().getColumn(4).setPreferredWidth(100);
			table.getColumnModel().getColumn(5).setPreferredWidth(100);
			table.getColumnModel().getColumn(6).setPreferredWidth(100);
			table.getColumnModel().getColumn(7).setPreferredWidth(180);
			table.getColumnModel().getColumn(8).setPreferredWidth(130);
			
		}
		return table;
	}

}
