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


import org.unibl.etf.shop.entity.OrganizacionaJedinica;
import org.unibl.etf.shop.entity.Proizvod;
import org.unibl.etf.shop.entity.ProizvodUOrganizacionojJedinici;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class KatalogProizvodaDialog extends JDialog {
	private KatalogProizvodaDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfKolicina;
	@SuppressWarnings("rawtypes")
	private JComboBox cbProizvod;
	@SuppressWarnings("rawtypes")
	private JComboBox cbOrganizacionaJedinica;

	/**
	 * Create the dialog.
	 */
	public KatalogProizvodaDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public KatalogProizvodaDialog(ProizvodUOrganizacionojJedinici proizvodUOJ) {
		ovaj = this;
		izmena = true;

		initialize();

		
		cbProizvod.setSelectedItem(proizvodUOJ.getProizvod());
		cbProizvod.setEnabled(false);
		cbOrganizacionaJedinica.setSelectedItem(proizvodUOJ.getOj());
		cbOrganizacionaJedinica.setEnabled(false);
		tfKolicina.setText(Double.toString(proizvodUOJ.getKocina()));
		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		
		if (cbProizvod.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(ovaj,
					"Proizvod nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		
		else if(cbOrganizacionaJedinica.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Organizaciona jedinica nije odabran!", "Greška",
					JOptionPane.ERROR_MESSAGE);
			
		}
		else if (tfKolicina.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Kolicina nije popunjena!",
					"Greška", JOptionPane.ERROR_MESSAGE);
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
		setTitle("Proizvod u organizacionoj jedinici");
		setBounds(100, 100, 355, 230);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblProizvod = new JLabel("Proizvod:");
			lblProizvod.setBounds(10, 11, 300, 14);
			contentPanel.add(lblProizvod);
		}
		{
			this.cbProizvod = new JComboBox(Utilities
					.getDataAccessFactory().getProizvodDataAccess().proizvodiSvi()
					.toArray(new Proizvod[] {}));
			this.cbProizvod.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbProizvod);
		}
		
		{
			JLabel lblOrganizacionaJedinica = new JLabel("Organizaciona jedinica:");
			lblOrganizacionaJedinica.setBounds(10, 59, 300, 14);
			contentPanel.add(lblOrganizacionaJedinica);
		}
		{
			this.cbOrganizacionaJedinica = new JComboBox(Utilities
					.getDataAccessFactory().getOrganizacionaJedinicaDataAccess().organizacioneJediniceSve()
					.toArray(new OrganizacionaJedinica[] {}));
			this.cbOrganizacionaJedinica.setBounds(10, 73, 300, 20);
			contentPanel.add(this.cbOrganizacionaJedinica);
		}
		
		{
			JLabel lblKolicina = new JLabel("Kolicina:");
			lblKolicina.setBounds(10, 107, 120, 14);
			contentPanel.add(lblKolicina);
		}
		{
			this.tfKolicina = new JTextField();
			this.tfKolicina.setColumns(10);
			this.tfKolicina.setBounds(10, 121, 120, 20);
			contentPanel.add(this.tfKolicina);
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
							ProizvodUOrganizacionojJedinici proizvodUOJ = new ProizvodUOrganizacionojJedinici(									 
									 (Proizvod) cbProizvod.getSelectedItem(),
									 (OrganizacionaJedinica) cbOrganizacionaJedinica.getSelectedItem(),
									 Double.valueOf(tfKolicina.getText()));
									
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getProizvodUOrganizacionojJediniciDataAccess().azurirajProizvodUOrganizacionojJedinici(proizvodUOJ);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Proizvod u organizacionoj jedinici nije uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getProizvodUOrganizacionojJediniciDataAccess().dodajProizvodUOrganizacionojJedinici(proizvodUOJ);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Proizvod u organizacionoj jedinici nije uspešno dodan!",
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
				okButton.setIcon(new ImageIcon(KatalogProizvodaDialog.class
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
								KatalogProizvodaDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
}
