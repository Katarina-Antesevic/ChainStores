package org.unibl.etf.shop.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
//import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class MjestoDialog extends JDialog{
	private MjestoDialog ovaj;
	private boolean izmena;
	private String dialogResult = "Cancel";

	private final JPanel contentPanel = new JPanel();
	private JTextField tfBrojPoste;
	private JTextField tfNaziv;

	/**
	 * Create the dialog.
	 */
	public MjestoDialog() {
		ovaj = this;
		izmena = false;

		initialize();
	}

	public MjestoDialog(Mjesto mjesto) {
		ovaj = this;
		izmena = true;

		initialize();

		tfBrojPoste.setText(mjesto.getBrojPoste());
		tfBrojPoste.setEditable(false);
		tfNaziv.setText(mjesto.getNaziv());
	}

	public String getDialogResult() {
		return dialogResult;
	}

	private boolean proveriValidnostPolja() {
		if (tfBrojPoste.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj,
					"Broj poste nije popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfBrojPoste.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Broj poste nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else if (tfNaziv.getText().length() == 0) {
			JOptionPane.showMessageDialog(ovaj, "Naziv nije popunjen!",
					"Greška", JOptionPane.ERROR_MESSAGE);
		} else if (!(Utilities.isTextValid(tfNaziv.getText()))) {
			JOptionPane.showMessageDialog(ovaj,
					"Naziv nije pravilno popunjen!", "Greška",
					JOptionPane.ERROR_MESSAGE);
		} else
			return true;
		return false;
	}

	private void initialize() {
		setResizable(false);//da se ne moze promijeniti velicina, kada mijenjamo ili dodajemo
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Mjesto");
		setBounds(100, 100, 355, 175);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(this.contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblBrojPoste = new JLabel("BrojPoste:");
			lblBrojPoste.setBounds(10, 11, 100, 14);
			contentPanel.add(lblBrojPoste);
		}
		{
			this.tfBrojPoste = new JTextField();
			this.tfBrojPoste.setBounds(10, 25, 50, 20);
			this.tfBrojPoste.setColumns(10);
			contentPanel.add(this.tfBrojPoste);
		}
		{
			JLabel lblNaziv = new JLabel("Naziv:");
			lblNaziv.setBounds(10, 59, 300, 14);
			contentPanel.add(lblNaziv);
		}
		{
			this.tfNaziv = new JTextField();
			this.tfNaziv.setBounds(10, 73, 300, 20);
			this.tfNaziv.setColumns(10);
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
							Mjesto mjesto = new Mjesto(
									tfBrojPoste.getText(), tfNaziv
											.getText());
							boolean rezultat;
							if (izmena) {
								rezultat = Utilities.getDataAccessFactory()
										.getMjestoDataAccess()
										.azirurajMjesto(mjesto);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Mjesto nije uspešno ažurirano!",
											"Poruka",
											JOptionPane.INFORMATION_MESSAGE);
							} else {
								rezultat = Utilities.getDataAccessFactory()
										.getMjestoDataAccess()
										.dodajMjesto(mjesto);
								if (!rezultat)
									JOptionPane.showMessageDialog(ovaj,
											"Mjesto nije uspešno dodano!",
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
				okButton.setIcon(new ImageIcon(MjestoDialog.class
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
								MjestoDialog.class
										.getResource(Utilities.IMAGE_RESOURCES_PATH + "Cancel_14.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
