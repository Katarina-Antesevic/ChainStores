package org.unibl.etf.shop.gui;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.shop.util.Utilities;

@SuppressWarnings("serial")
public class GlavniProzor extends JFrame{
	
	private JFrame ovaj;

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnAplikacija;
	private JMenuItem mntmIzlaz;
	private JMenu mnSifarnici;
	private JMenu mnKatalogProizvoda;
	private JMenu mnIzvjestaji;
	private JMenuItem mntmProdavnicaMax;
	private JMenuItem mntmProdavnicaMin;
	private JMenuItem mntmZaposleni2020;
	private JMenuItem mntmZaposleni2021;
	private JMenuItem mntmMagacinMin;
	private JMenuItem mntmMagacinMax;
	private JMenuItem mntmDobavljaci;
	private JMenuItem mntmMagacini;
	private JMenuItem mntmMagacioneri;
	private JMenuItem mntmMjesta;
	private JMenuItem mntmOJ;
	private JMenuItem mntmProdavnice;
	private JMenuItem mntmProizvodi;
	private JMenuItem mntmProizvodjaci;
	private JMenuItem mntmTrgovci;
	private JMenuItem mntmZaposleni;
	
	private JMenuItem mntmKatalogProizvoda;
	/*private JMenu mnPlanIProgram;
	private JMenuItem mntmPlanIProgram;
	private JMenu mnIzvestaji;
	private JMenuItem mntmProsecneOceneStudenata;*/

	
	/**
	 * Create the frame.
	 */
	public GlavniProzor() {
		initialize();
	}

	private void initialize() {
		ovaj = this;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int rezultat = JOptionPane.showOptionDialog(ovaj,
						"Da li ste sigurni da želite zatvoriti aplikaciju?",
						"Potvrda zatvaranja", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null,
						Utilities.YES_NO_OPTIONS,
						Utilities.YES_NO_OPTIONS[1]);
				if (rezultat == JOptionPane.YES_OPTION)
					System.exit(0);//zatvori aplikaciju
			}
		});
		setTitle("Lanac prodavnica");
		
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 815, 420);
		setLocationRelativeTo(null);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setJMenuBar(getMenuBar_1());

		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);
	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnAplikacija());
			menuBar.add(getMnSifarnici());
			menuBar.add(getMnKatalogProizvoda());
			menuBar.add(getIzvjstaji());
			//menuBar.add(getMnIzvestaji());*/
		}
		return menuBar;
	}

	private JMenu getMnAplikacija() {
		if (mnAplikacija == null) {
			mnAplikacija = new JMenu("Aplikacija");
			mnAplikacija.setMnemonic('A');
			mnAplikacija.add(getMntmIzlaz());
		}
		return mnAplikacija;
	}

	@SuppressWarnings("deprecation")
	private JMenuItem getMntmIzlaz() {
		if (mntmIzlaz == null) {
			mntmIzlaz = new JMenuItem("Izlaz");
			mntmIzlaz.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ovaj.getToolkit()
							.getSystemEventQueue()
							.postEvent(
									new WindowEvent(ovaj,
											WindowEvent.WINDOW_CLOSING));
				}
			});
			mntmIzlaz.setMnemonic('I');
			mntmIzlaz.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
					InputEvent.ALT_MASK));
		}
		return mntmIzlaz;
	}

	private JMenu getMnSifarnici() {
		if (mnSifarnici == null) {
			mnSifarnici = new JMenu("Šifarnici");
			mnSifarnici.setMnemonic('F');
			mnSifarnici.add(getMntmDobavljaci());
			mnSifarnici.add(getMntmMagacioneri());
			mnSifarnici.add(getMntmMagacini());
			mnSifarnici.add(getMntmMjesta());
			mnSifarnici.add(getMntmOJ());
			mnSifarnici.add(getMntmProdavnice());
			mnSifarnici.add(getMntmProizvodi());
			mnSifarnici.add(getMntmProizvodjaci());
			mnSifarnici.add(getMntmTrgovci());
			mnSifarnici.add(getMntmZaposleni());
		
		}
		return mnSifarnici;
	}
	
	private JMenu getIzvjstaji() {
		if (mnIzvjestaji == null) {
			mnIzvjestaji = new JMenu("Izvještaji magacionera i trgovaca");
			mnIzvjestaji.setMnemonic('I');
			mnIzvjestaji.add(getMntmMagcinMax());
			mnIzvjestaji.add(getMntmMagacinMin());
			mnIzvjestaji.add(getMntmProdavnicaMax());
			mnIzvjestaji.add(getMntmProdavnicaMin());
			mnIzvjestaji.add(getMntmZaposleni2020());
			mnIzvjestaji.add(getMntmZaposleni2021());
			
	}
		return mnIzvjestaji;
	}
	
	private JMenuItem getMntmZaposleni2020() {
		if (mntmZaposleni2020 == null) {
			mntmZaposleni2020 = new JMenuItem("Zaposleni u 2020. godini");
			mntmZaposleni2020.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajiZaposleni2020Frame().setVisible(true);
				}
			});
			mntmZaposleni2020.setMnemonic('I');
		}
		return mntmZaposleni2020;
	}
	
	private JMenuItem getMntmZaposleni2021() {
		if (mntmZaposleni2021 == null) {
			mntmZaposleni2021 = new JMenuItem("Zaposleni u 2021. godini");
			mntmZaposleni2021.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajZaposleni2021Frame().setVisible(true);
				}
			});
			mntmZaposleni2021.setMnemonic('Z');
		}
		return mntmZaposleni2021;
	}
	
	private JMenuItem getMntmMagcinMax() {
		if (mntmMagacinMax == null) {
			mntmMagacinMax = new JMenuItem("Maksimalna plata magacionera");
			mntmMagacinMax.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajMagacinMaxFrame().setVisible(true);
				}
			});
			mntmMagacinMax.setMnemonic('N');
		}
		return mntmMagacinMax;
	}
	
	
	private JMenuItem getMntmMagacinMin() {
		if (mntmMagacinMin == null) {
			mntmMagacinMin = new JMenuItem("Minimalna plata magacionera");
			mntmMagacinMin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajMagacinMinFrame().setVisible(true);
				}
			});
			mntmMagacinMin.setMnemonic('C');
		}
		return mntmMagacinMin;
	}
	
	
	private JMenuItem getMntmProdavnicaMin() {
		if (mntmProdavnicaMin == null) {
			mntmProdavnicaMin = new JMenuItem("Minimalna plata trgovca");
			mntmProdavnicaMin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajProdavnicaMinFrame().setVisible(true);
				}
			});
			mntmProdavnicaMin.setMnemonic('V');
		}
		return mntmProdavnicaMin;
	}
	
	private JMenuItem getMntmProdavnicaMax() {
		if (mntmProdavnicaMax == null) {
			mntmProdavnicaMax = new JMenuItem("Maksimalna plata trgovca");
			mntmProdavnicaMax.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvjestajProdavnicaMaxFrame().setVisible(true);
				}
			});
			mntmProdavnicaMax.setMnemonic('D');
		}
		return mntmProdavnicaMax;
	}
	
	private JMenu getMnKatalogProizvoda() {
		if (mnKatalogProizvoda == null) {
			mnKatalogProizvoda = new JMenu("Katalog proizvoda");
			mnKatalogProizvoda.setMnemonic('K');
			mnKatalogProizvoda.add(getMntmKatalogProizvoda());
		}
		return mnKatalogProizvoda;
	}
	
	private JMenuItem getMntmKatalogProizvoda() {
		if (mntmKatalogProizvoda == null) {
			mntmKatalogProizvoda = new JMenuItem("Katalog proizvoda");
			mntmKatalogProizvoda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new KatalogProizvodaFrame(false).setVisible(true);
				}
			});
			mntmKatalogProizvoda.setMnemonic('T');
		}
		return mntmKatalogProizvoda;
	}
	
	
	
	private JMenuItem getMntmProdavnice(){
		if (mntmProdavnice == null) {
			mntmProdavnice = new JMenuItem("Prodavnice");
			mntmProdavnice.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ProdavniceFrame(false).setVisible(true);
				}
			});
			mntmProdavnice.setMnemonic('D');
		}
		return mntmProdavnice;
	}
	
	
	
	private JMenuItem getMntmProizvodi(){
		if (mntmProizvodi == null) {
			mntmProizvodi = new JMenuItem("Proizvodi");
			mntmProizvodi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ProizvodiFrame(false).setVisible(true);
				}
			});
			mntmProizvodi.setMnemonic('R');
		}
		return mntmProizvodi;
	}
	
	private JMenuItem getMntmDobavljaci(){
		if (mntmDobavljaci == null) {
			mntmDobavljaci = new JMenuItem("Dobavljaci");
			mntmDobavljaci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DobavljaciFrame(false).setVisible(true);
				}
			});
			mntmDobavljaci.setMnemonic('D');
		}
		return mntmDobavljaci;
	}
	
	private JMenuItem getMntmMagacini(){
		if (mntmMagacini == null) {
			mntmMagacini = new JMenuItem("Magacini");
			mntmMagacini.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MagaciniFrame(false).setVisible(true);
				}
			});
			mntmMagacini.setMnemonic('E');
		}
		return mntmMagacini;
	}

	private JMenuItem getMntmMjesta() {
		if (mntmMjesta == null) {
			mntmMjesta = new JMenuItem("Mjesta");
			mntmMjesta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new MjestaFrame().setVisible(true);
				}
			});
			mntmMjesta.setMnemonic('M');
		}
		return mntmMjesta;
	}

	
	private JMenuItem getMntmZaposleni() {
		if (mntmZaposleni == null) {
			mntmZaposleni = new JMenuItem("Zaposleni");
			mntmZaposleni.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ZaposleniFrame(false).setVisible(true);
				}
			});
			mntmZaposleni.setMnemonic('Z');
		}
		return mntmZaposleni;
	}

	private JMenuItem getMntmMagacioneri() {
		if (mntmMagacioneri == null) {
			mntmMagacioneri = new JMenuItem("Magacioneri");
			mntmMagacioneri.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new MagacioneriFrame(false).setVisible(true);
				}
			});
			mntmMagacioneri.setMnemonic('C');
		}
		return mntmMagacioneri;
	}
	
	
	private JMenuItem getMntmOJ() {
		if (mntmOJ == null) {
			mntmOJ = new JMenuItem("Organizacione jedinice");
			mntmOJ.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new OrganizacionaJedinicaFrame(false).setVisible(true);
				}
			});
			mntmOJ.setMnemonic('O');
		}
		return mntmOJ;
	}
	
	private JMenuItem getMntmProizvodjaci(){
		if (mntmProizvodjaci == null) {
			mntmProizvodjaci = new JMenuItem("Proizvodjaci");
			mntmProizvodjaci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ProizvodjacFrame(false).setVisible(true);
				}
			});
			mntmProizvodjaci.setMnemonic('P');
		}
		return mntmProizvodjaci;
	}
	
	private JMenuItem getMntmTrgovci(){
		if (mntmTrgovci == null) {
			mntmTrgovci = new JMenuItem("Trgovci");
			mntmTrgovci.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new TrgovciFrame(false).setVisible(true);
				}
			});
			mntmTrgovci.setMnemonic('T');
		}
		return mntmTrgovci;
	}
	
	
	/*private JMenuItem getMntmStudijskiProgrami() {
		if (mntmStudijskiProgrami == null) {
			mntmStudijskiProgrami = new JMenuItem("Studijski programi...");
			mntmStudijskiProgrami.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new StudijskiProgramiFrame(false).setVisible(true);
				}
			});
			mntmStudijskiProgrami.setMnemonic('S');
		}
		return mntmStudijskiProgrami;
	}

	private JMenu getMnPlanIProgram() {
		if (mnPlanIProgram == null) {
			mnPlanIProgram = new JMenu("Plan i program");
			mnPlanIProgram.setMnemonic('P');
			mnPlanIProgram.add(getMntmPlanIProgram());
		}
		return mnPlanIProgram;
	}

	private JMenuItem getMntmPlanIProgram() {
		if (mntmPlanIProgram == null) {
			mntmPlanIProgram = new JMenuItem("Plan i program");
			mntmPlanIProgram.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new PlanIProgramFrame().setVisible(true);
				}
			});
			mntmPlanIProgram.setMnemonic('P');
		}
		return mntmPlanIProgram;
	}

	private JMenu getMnIzvestaji() {
		if (mnIzvestaji == null) {
			mnIzvestaji = new JMenu("Izveštaji");
			mnIzvestaji.setMnemonic('I');
			mnIzvestaji.add(getMntmProsecneOceneStudenata());
		}
		return mnIzvestaji;
	}

	private JMenuItem getMntmProsecneOceneStudenata() {
		if (mntmProsecneOceneStudenata == null) {
			mntmProsecneOceneStudenata = new JMenuItem(
					"Prosečne ocene studenata...");
			mntmProsecneOceneStudenata.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new IzvestajiFrame().setVisible(true);
				}
			});
			mntmProsecneOceneStudenata.setMnemonic('P');
		}
		return mntmProsecneOceneStudenata;
	}*/
	
}
