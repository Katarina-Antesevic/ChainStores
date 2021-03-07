package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.Dobavljac;

@SuppressWarnings("serial")
public class DobavljacTableModel extends AbstractTableModel{
	private List<Dobavljac> podaci;
	String[] kolone = new String[] { "JIB", "Naziv", 
			"Email","Telefon","Adresa","Broj poste" };

	public DobavljacTableModel(List<Dobavljac> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Dobavljac> podaci) {
		this.podaci = podaci;
	}

	public Dobavljac getDobavljacAtRow(int rowIndex) {
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
		Dobavljac red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getJib();
		else if (columnIndex == 1)
			return red.getNaziv();
		else if (columnIndex == 2)
			return red.getEmail();
		else if (columnIndex == 3)
			return red.getTelefon();
		else if (columnIndex == 4)
			return red.getAdresa();
		else if (columnIndex == 5)
			return red.getMJESTO_BrojPoste();
		
		else
			return null;
	}

}
