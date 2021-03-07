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
import org.unibl.etf.shop.entity.Proizvodjac;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class ProizvodjacDialog extends JDialog {
	
	private ProizvodjacDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfJIB;
	private JTextField tfNaziv;
	private JTextField tfEmail;
	private JTextField tfTelefon;
	private JTextField tfAdresa;
	//private JTextField tfBrojPoste;
	//private JTextField tfDatumOd;
	@SuppressWarnings("rawtypes")
	private JComboBox cbBrojPoste;

	/**
	 * Create the dialog.
	 */
	public ProizvodjacDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public ProizvodjacDialog(Proizvodjac proizvodjac) {
		ovaj = this;
		izmena = true;

		initialize();

		tfJIB.setText(proizvodjac.getJib());
		tfJIB.setEditable(false);
		tfNaziv.setText(proizvodjac.getNaziv());
		tfNaziv.setEditable(false);
		tfEmail.setText(proizvodjac.getEmail());
		tfTelefon.setText(proizvodjac.getTelefon());
		tfAdresa.setText(proizvodjac.getAdresa());
		cbBrojPoste.setSelectedItem(proizvodjac.getMJESTO_BrojPoste());
		
		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfJIB.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"JIB nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfJIB.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"JIB nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfNaziv.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Naziv nije popunjen!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfNaziv.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv nije pravilno popunjen!", "Greška",
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
		else if (tfTelefon.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Telefon nije popunjen!",
					"Greška", JOptionPane.ERROR_MESSAGE);
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
		setTitle("Proizvodjac");
		setBounds(100, 100, 355, 370);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lbljib = new JLabel("JIB:");
			lbljib.setBounds(10, 11, 300, 14);
			contentPanel.add(lbljib);
		}
		{
			this.tfJIB = new JTextField();
			this.tfJIB.setColumns(10);
			this.tfJIB.setBounds(10, 25, 300, 20);
			contentPanel.add(this.tfJIB);
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
			JLabel lblEmail = new JLabel("Email:");
			lblEmail.setBounds(10, 107, 300, 14);
			contentPanel.add(lblEmail);
		}
		{
			this.tfEmail = new JTextField();
			this.tfEmail.setColumns(10);
			this.tfEmail.setBounds(10, 121, 300, 20);
			contentPanel.add(this.tfEmail);
		}
		
		
		
		{
			JLabel lblTelefon = new JLabel("Telefon:");
			lblTelefon.setBounds(10, 155, 300, 14);
			contentPanel.add(lblTelefon);
		}
		{
			this.tfTelefon = new JTextField();
			this.tfTelefon.setColumns(10);
			this.tfTelefon.setBounds(10, 169, 300, 20);
			contentPanel.add(this.tfTelefon);
		}
		
		
		{
			JLabel lblAdresa = new JLabel("Adresa:");
			lblAdresa.setBounds(10, 203, 300, 14);
			contentPanel.add(lblAdresa);
		}
		{
			this.tfAdresa = new JTextField();
			this.tfAdresa.setColumns(10);
			this.tfAdresa.setBounds(10, 217, 300, 20);
			contentPanel.add(this.tfAdresa);
		}
		{
			JLabel lblBrojPoste = new JLabel("BrojPoste:");
			lblBrojPoste.setBounds(10, 251, 300, 14);
			contentPanel.add(lblBrojPoste);
		}
		{
			this.cbBrojPoste = new JComboBox(Utilities
					.getDataAccessFactory().getMjestoDataAccess().mjestaSva()
					.toArray(new Mjesto[] {}));
			this.cbBrojPoste.setBounds(10, 265, 300, 20);
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
							Proizvodjac proizvodjac = new Proizvodjac(tfJIB.getText(),
									tfNaziv.getText(),tfEmail.getText(),tfTelefon.getText(),
									 tfAdresa.getText(),
													
									(Mjesto) cbBrojPoste
									.getSelectedItem());
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getProizvodjacDataAccess().azurirajProizvodjaca(proizvodjac);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Proizvodjac nije uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getProizvodjacDataAccess().dodajProizvodjaca(proizvodjac);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Proizvodjac nije uspešno dodan!",
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
				okButton.setIcon(new ImageIcon(ProizvodjacDialog.class
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
								ProizvodjacDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
