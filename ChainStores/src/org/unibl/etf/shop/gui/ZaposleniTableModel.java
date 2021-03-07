package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.Zaposleni;

@SuppressWarnings("serial")
public class ZaposleniTableModel extends AbstractTableModel {
	private List<Zaposleni> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime",
			"Email","Telefon","Plata","Datum od","Adresa","Broj poste" };

	public ZaposleniTableModel(List<Zaposleni> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Zaposleni> podaci) {
		this.podaci = podaci;
	}

	public Zaposleni getZaposleniAtRow(int rowIndex) {
		return podaci.get(rowIndex);
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	@Override
	public int getRowCount() {
		return podaci.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Zaposleni red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getJmb();
		else if (columnIndex == 1)
			return red.getIme();
		else if (columnIndex == 2)
			return red.getPrezime();
		else if (columnIndex == 3)
			return red.getEmail();
		else if (columnIndex == 4)
			return red.getTelefon();
		else if (columnIndex == 5)
			return red.getPlata();
		else if (columnIndex == 6)
			return red.getDatumOd();
		else if (columnIndex == 7)
			return red.getAdresa();
		else if (columnIndex == 8)
			return red.getMJESTO_BrojPoste();
		
		else
			return null;
	}
	

}
