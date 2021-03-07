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
import javax.swing.border.EmptyBorder;

import org.unibl.etf.shop.entity.Prodavnica;
import org.unibl.etf.shop.entity.Trgovac;
import org.unibl.etf.shop.entity.Zaposleni;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class TrgovacDialog extends JDialog{
	private TrgovacDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	@SuppressWarnings("rawtypes")
	private JComboBox cbZaposleni;
	@SuppressWarnings("rawtypes")
	private JComboBox cbProdavnica;

	/**
	 * Create the dialog.
	 */
	public TrgovacDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public TrgovacDialog(Trgovac trgovac) {
		ovaj = this;
		izmena = true;

		initialize();

		
		cbProdavnica.setSelectedItem(trgovac.getProdavnica());
		cbZaposleni.setSelectedItem(trgovac.getZaposleni());
		cbZaposleni.setEnabled(false);
		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		
		if (cbProdavnica.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(ovaj,
					"Prodavnica nije odabrana!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		}
		
		else if(cbZaposleni.getSelectedIndex() == -1){
			JOptionPane.showMessageDialog(ovaj,
					"Zaposleni nije odabran!", "Greška",
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
		setTitle("Trgovac");
		setBounds(100, 100, 355, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblProdavnica = new JLabel("Prodavnica:");
			lblProdavnica.setBounds(10, 11, 300, 14);
			contentPanel.add(lblProdavnica);
		}
		{
			this.cbProdavnica = new JComboBox(Utilities
					.getDataAccessFactory().getProdavnicaDataAccess().prodavniceSve()
					.toArray(new Prodavnica[] {}));
			this.cbProdavnica.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbProdavnica);
		}
		
		{
			JLabel lblZaposleni = new JLabel("Zaposleni:");
			lblZaposleni.setBounds(10, 59, 300, 14);
			contentPanel.add(lblZaposleni);
		}
		{
			this.cbZaposleni = new JComboBox(Utilities
					.getDataAccessFactory().getZaposleniDataAccess().zaposleniSvi()
					.toArray(new Zaposleni[] {}));
			this.cbZaposleni.setBounds(10, 73, 300, 20);
			contentPanel.add(this.cbZaposleni);
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
							Trgovac trgovac = new Trgovac(									 
									 (Prodavnica) cbProdavnica.getSelectedItem(),
									 (Zaposleni) cbZaposleni.getSelectedItem());
									
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getTrgovacDataAccess().azurirajTrgovca(trgovac);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Trgovac nije uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getTrgovacDataAccess().dodajTrgovca(trgovac);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Trgovac nije uspešno dodan!",
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
				okButton.setIcon(new ImageIcon(TrgovacDialog.class
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
								TrgovacDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
