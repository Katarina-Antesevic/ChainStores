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

import org.unibl.etf.shop.entity.Magacin;
import org.unibl.etf.shop.entity.Magacioner;
import org.unibl.etf.shop.entity.Zaposleni;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class MagacionerDialog extends JDialog {
	private MagacionerDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	
	@SuppressWarnings("rawtypes")
	private JComboBox cbZaposleni;
	@SuppressWarnings("rawtypes")
	private JComboBox cbMagacin;

	/**
	 * Create the dialog.
	 */
	public MagacionerDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public MagacionerDialog(Magacioner magacioner) {
		ovaj = this;
		izmena = true;

		initialize();

		
		cbMagacin.setSelectedItem(magacioner.getMagacin());
		cbZaposleni.setSelectedItem(magacioner.getZaposleni());
		cbZaposleni.setEnabled(false);
		
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		
		if (cbMagacin.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(ovaj,
					"Magacin nije odabran!", "Greška",
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
		setTitle("Magacioner");
		setBounds(100, 100, 355, 200);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMagacin = new JLabel("Magacin:");
			lblMagacin.setBounds(10, 11, 300, 14);
			contentPanel.add(lblMagacin);
		}
		{
			this.cbMagacin = new JComboBox(Utilities
					.getDataAccessFactory().getMagacinDataAccess().magaciniSvi()
					.toArray(new Magacin[] {}));
			this.cbMagacin.setBounds(10, 25, 300, 20);
			contentPanel.add(this.cbMagacin);
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
							Magacioner magacioner = new Magacioner(									 
									 (Magacin) cbMagacin.getSelectedItem(),
									 (Zaposleni) cbZaposleni.getSelectedItem());
									
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getMagacionerDataAccess().azurirajMagacionera(magacioner);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Magacioner nije uspešno ažuriran!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getMagacionerDataAccess().dodajMagacionera(magacioner);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Magacioner nije uspešno dodan!",
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
				okButton.setIcon(new ImageIcon(MagacionerDialog.class
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
								MagacionerDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	

}
