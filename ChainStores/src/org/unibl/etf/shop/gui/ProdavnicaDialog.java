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
import org.unibl.etf.shop.entity.Prodavnica;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class ProdavnicaDialog extends JDialog{
	private ProdavnicaDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNaziv;
	@SuppressWarnings("rawtypes")
	private JComboBox cbOrganizacionaJedinica;

	/**
	 * Create the dialog.
	 */
	public ProdavnicaDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public ProdavnicaDialog(Prodavnica prodavnica) {
		ovaj = this;
		izmena = true;

		initialize();

		tfNaziv.setText(prodavnica.getNaziv());
		cbOrganizacionaJedinica.setSelectedItem(prodavnica.getOrganizacionaJedinica());
		cbOrganizacionaJedinica.setEnabled(false);
		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfNaziv.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Naziv nije popunjen!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfNaziv.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		
		
		else if (cbOrganizacionaJedinica.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(ovaj,
					"Organizaciona jedinica nije odabrana!", "Greška",
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
		setTitle("Prodavnica");
		setBounds(100, 100, 355, 190);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblOrganizacionaJedinica = new JLabel("OrganizacionaJedinica:");
			lblOrganizacionaJedinica.setBounds(10, 11, 300, 14);
			contentPanel.add(lblOrganizacionaJedinica);
		}
		{
			this.cbOrganizacionaJedinica = new JComboBox(Utilities
					.getDataAccessFactory().getOrganizacionaJedinicaDataAccess().organizacioneJediniceSve()
					.toArray(new OrganizacionaJedinica[] {}));
			this.cbOrganizacionaJedinica.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbOrganizacionaJedinica);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Sačuvati");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (proveriValidnostPolja()) {
							Prodavnica prodavnica = new Prodavnica(									 
									 (OrganizacionaJedinica) cbOrganizacionaJedinica.getSelectedItem(),
									 tfNaziv.getText());
									
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getProdavnicaDataAccess().azurirajProdavnicu(prodavnica);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Prodavnica nije uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getProdavnicaDataAccess().dodajProdavnicu(prodavnica);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Prodavnica nije uspešno dodan!",
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
				okButton.setIcon(new ImageIcon(ProdavnicaDialog.class
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
								ProdavnicaDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
