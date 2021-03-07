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

import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.Zaposleni;
import org.unibl.etf.shop.util.Utilities;


@SuppressWarnings("serial")
public class ZaposleniDialog extends JDialog{
	private ZaposleniDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfJMB;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfEmail;
	private JTextField tfTelefon;
	private JTextField tfPlata;
	private JTextField tfAdresa;
	//private JTextField tfBrojPoste;
	//private JTextField tfDatumOd;
	@SuppressWarnings("rawtypes")
	private JComboBox cbBrojPoste;

	/**
	 * Create the dialog.
	 */
	public ZaposleniDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public ZaposleniDialog(Zaposleni zaposleni) {
		ovaj = this;
		izmena = true;

		initialize();

		tfJMB.setText(zaposleni.getJmb());
		tfJMB.setEditable(false);
		tfIme.setText(zaposleni.getIme());
		tfIme.setEditable(false);
		tfPrezime.setText(zaposleni.getPrezime());
		tfPrezime.setEditable(false);
		tfEmail.setText(zaposleni.getEmail());
		tfTelefon.setText(zaposleni.getTelefon());;
		tfPlata.setText(Double.toString(zaposleni.getPlata()));
		tfAdresa.setText(zaposleni.getAdresa());
		cbBrojPoste.setSelectedItem(zaposleni.getMJESTO_BrojPoste());
		
		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfJMB.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"JMB nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfJMB.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"JMB nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfIme.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Ime nije popunjeno!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfIme.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Ime nije pravilno popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		
		else if (tfPrezime.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Prezime nije popunjeno!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfPrezime.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Prezime nije pravilno popunjeno!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (tfEmail.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Email nije popunjen!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		}/* else if (!(Utilities.isTextValid(tfEmail.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Email nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}*/
		else if (tfPlata.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Plata nije popunjena!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfPlata.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Plata nije pravilno popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (tfAdresa.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Adresa nije popunjena!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfAdresa.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Adresa nije pravilno popunjena!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		else if (cbBrojPoste.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(ovaj,
					"Broj poste nije odabran!", "Greška",
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
		setTitle("Zaposleni");
		setBounds(100, 100, 355, 460);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbljmb = new JLabel("JMB:");
			lbljmb.setBounds(10, 11, 300, 14);
			contentPanel.add(lbljmb);
		}
		{
			this.tfJMB = new JTextField();
			this.tfJMB.setColumns(10);
			this.tfJMB.setBounds(10, 25, 300, 20);
			contentPanel.add(this.tfJMB);
		}
		{
			JLabel lblIme = new JLabel("Ime:");
			lblIme.setBounds(10, 59, 300, 14);
			contentPanel.add(lblIme);
		}
		{
			this.tfIme = new JTextField();
			this.tfIme.setColumns(10);
			this.tfIme.setBounds(10, 73, 300, 20);
			contentPanel.add(this.tfIme);
		}
		{
			JLabel lblPrezime = new JLabel("Prezime:");
			lblPrezime.setBounds(10, 107, 300, 14);
			contentPanel.add(lblPrezime);
		}
		{
			this.tfPrezime = new JTextField();
			this.tfPrezime.setColumns(10);
			this.tfPrezime.setBounds(10, 121, 300, 20);
			contentPanel.add(this.tfPrezime);
		}
		
		
		
		{
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(10, 155, 300, 14);
			contentPanel.add(lblEmail);
		}
		{
			this.tfEmail = new JTextField();
			this.tfEmail.setColumns(10);
			this.tfEmail.setBounds(10, 169, 300, 20);
			contentPanel.add(this.tfEmail);
		}
		{
			JLabel lblTelefon = new JLabel("Telefon:");
			lblTelefon.setBounds(10, 203, 300, 14);
			contentPanel.add(lblTelefon);
		}
		{
			this.tfTelefon = new JTextField();
			this.tfTelefon.setColumns(10);
			this.tfTelefon.setBounds(10, 217, 300, 20);
			contentPanel.add(this.tfTelefon);
		}
		
		{
			JLabel lblPlata = new JLabel("Plata:");
			lblPlata.setBounds(10, 251, 300, 14);
			contentPanel.add(lblPlata);
		}
		{
			this.tfPlata = new JTextField();
			this.tfPlata.setColumns(10);
			this.tfPlata.setBounds(10, 265, 300, 20);
			contentPanel.add(this.tfPlata);
		}
		/*{
			JLabel lblDatumOd = new JLabel("Datum od:");
			lblDatumOd.setBounds(10, 251, 327, 14);
			contentPanel.add(lblDatumOd);
		}
		{
			this.tfDatumOd = new JTextField();
			this.tfDatumOd.setColumns(10);
			this.tfDatumOd.setBounds(10, 271, 40, 20);
			contentPanel.add(this.tfDatumOd);
		}*/
		
		{
			JLabel lblAdresa = new JLabel("Adresa:");
			lblAdresa.setBounds(10, 299, 300, 14);
			contentPanel.add(lblAdresa);
		}
		{
			this.tfAdresa = new JTextField();
			this.tfAdresa.setColumns(10);
			this.tfAdresa.setBounds(10, 313, 300, 20);
			contentPanel.add(this.tfAdresa);
		}
		{
			JLabel lblBrojPoste = new JLabel("BrojPoste:");
			lblBrojPoste.setBounds(10, 347, 300, 14);
			contentPanel.add(lblBrojPoste);
		}
		{
			this.cbBrojPoste = new JComboBox(Utilities
					.getDataAccessFactory().getMjestoDataAccess().mjestaSva()
					.toArray(new Mjesto[] {}));
			this.cbBrojPoste.setBounds(10, 361, 300, 20);
			contentPanel.add(this.cbBrojPoste);
		}
		


		
		
		
		
		
		
		
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sačuvati");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (proveriValidnostPolja()) {
							Zaposleni zaposleni = new Zaposleni(tfJMB.getText(),
									tfIme.getText(), tfPrezime.getText(),tfEmail.getText(),tfTelefon.getText(),
									Double.valueOf(tfPlata.getText()), tfAdresa.getText(),
													
									(Mjesto) cbBrojPoste
									.getSelectedItem());
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getZaposleniDataAccess().azurirajZaposlenog(zaposleni);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Zaposleni nije uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getZaposleniDataAccess().dodajZaposlenog(zaposleni);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Zaposleni nije uspešno dodan!",
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
				okButton.setIcon(new ImageIcon(ZaposleniDialog.class
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
								ZaposleniDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
