package org.unibl.etf.shop.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.shop.entity.Dobavljac;
//import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.Proizvod;
import org.unibl.etf.shop.entity.Proizvodjac;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class ProizvodDialog extends JDialog {
	private ProizvodDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfBarkod;
	private JTextField tfNaziv;
	private JTextField tfCijena;
	@SuppressWarnings("rawtypes")
	private JComboBox cbProizvodjac;
	@SuppressWarnings("rawtypes")
	private JComboBox cbDobavljac;

	/**
	 * Create the dialog.
	 */
	public ProizvodDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public ProizvodDialog(Proizvod proizvod) {
		ovaj = this;
		izmena = true;

		initialize();

		tfBarkod.setText(proizvod.getBarkod());
		tfBarkod.setEditable(false);
		tfNaziv.setText(proizvod.getNaziv());
		tfCijena.setText(Double.toString(proizvod.getCijena()));
		cbProizvodjac.setSelectedItem(proizvod.getProizvodjac());
		cbProizvodjac.setEnabled(false);
		cbDobavljac.setSelectedItem(proizvod.getDobavljac());
		cbDobavljac.setEnabled(false);
		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfBarkod.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Barkod nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfBarkod.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Barkod nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfNaziv.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Naziv nije popunjen!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfNaziv.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		else if (tfCijena.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Cijena nije popunjena!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfCijena.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Cijena nije pravilno popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (cbProizvodjac.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(ovaj,
					"Proizvodjac nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (cbDobavljac.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(ovaj,
					"Dobavljac nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
		else
			return true;
		return false;
	}

	//za dodavanje i azuriranje 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Proizvod");
		setBounds(100, 100, 355, 320);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbBarkod = new JLabel("Barkod:");
			lbBarkod.setBounds(10, 11, 300, 14);
			contentPanel.add(lbBarkod);
		}
		{
			this.tfBarkod = new JTextField();
			this.tfBarkod.setColumns(10);
			this.tfBarkod.setBounds(10, 25, 300, 20);
			contentPanel.add(this.tfBarkod);
		}
		{
			JLabel lblNaziv = new JLabel("Naziv:");
			lblNaziv.setBounds(10, 59, 300, 14);
			contentPanel.add(lblNaziv);
		}
		{
			this.tfNaziv = new JTextField();
			this.tfNaziv.setColumns(10);
			this.tfNaziv.setBounds(10, 73, 300, 20);
			contentPanel.add(this.tfNaziv);
		}
		{
			JLabel lblCijena = new JLabel("Cijena:");
			lblCijena.setBounds(10, 107, 300, 14);
			contentPanel.add(lblCijena);
		}
		{
			this.tfCijena = new JTextField();
			this.tfCijena.setColumns(10);
			this.tfCijena.setBounds(10, 121, 300, 20);
			contentPanel.add(this.tfCijena);
		}
		
		
		{
			JLabel lblProizvodjac = new JLabel("Proizvodjac:");
			lblProizvodjac.setBounds(10, 155, 300, 14);
			contentPanel.add(lblProizvodjac);
		}
		{
			this.cbProizvodjac = new JComboBox(Utilities
					.getDataAccessFactory().getProizvodjacDataAccess().proizvodjaciSvi()
					.toArray(new Proizvodjac[] {}));
			this.cbProizvodjac.setBounds(10, 169, 300, 20);
			contentPanel.add(this.cbProizvodjac);
		}
		
		{
			JLabel lblDobavljac = new JLabel("Dobavljac:");
			lblDobavljac.setBounds(10, 203, 300, 14);
			contentPanel.add(lblDobavljac);
		}
		{
			this.cbDobavljac = new JComboBox(Utilities
					.getDataAccessFactory().getDobavljacDataAccess().dobavljaciSvi()
					.toArray(new Dobavljac[] {}));
			this.cbDobavljac.setBounds(10, 217, 300, 20);
			contentPanel.add(this.cbDobavljac);
		}
		
		/*{JLabel lblBrojPoste = new JLabel("BrojPoste:");
			lblBrojPoste.setBounds(10, 251, 300, 14);
			contentPanel.add(lblBrojPoste);
		}
		{
			this.cbBrojPoste = new JComboBox(Utilities
					.getDataAccessFactory().getMjestoDataAccess().mjestaSva()
					.toArray(new Mjesto[] {}));
			this.cbBrojPoste.setBounds(10, 265, 300, 20);
			contentPanel.add(this.cbBrojPoste);
		}*/
		

		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sačuvati");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (proveriValidnostPolja()) {
							Proizvod proizvod = new Proizvod(tfBarkod.getText(),
									tfNaziv.getText(),Double.valueOf(tfCijena.getText()),
									 
									 (Proizvodjac) cbProizvodjac.getSelectedItem(),
									 (Dobavljac) cbDobavljac.getSelectedItem());
									
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getProizvodDataAccess().azirirajProizvod(proizvod);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Proizvod nije uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getProizvodDataAccess().dodajProizvod(proizvod);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Proizvod nije uspešno dodan!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							}
							if (rezultat) {
								dialogResult = e.getActionCommand();
								ovaj.getToolkit()
										.getSystemEventQueue()
										.postEvent(
												new WindowEvent(
														ovaj,
														WindowEvent.WINDOW_CLOSING));
							}

						}
					}
				});
				okButton.setIcon(new ImageIcon(ProizvodDialog.class
						.getResource(Utilities.IMAGE_RESOURCES_PATH + "Check_14.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Otkazati");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialogResult = e.getActionCommand();
						ovaj.getToolkit()
								.getSystemEventQueue()
								.postEvent(
										new WindowEvent(ovaj,
												WindowEvent.WINDOW_CLOSING));
					}
				});
				cancelButton
						.setIcon(new ImageIcon(
								ProizvodDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
