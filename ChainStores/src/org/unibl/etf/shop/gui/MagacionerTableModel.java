package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.Magacioner;

@SuppressWarnings("serial")
public class MagacionerTableModel extends AbstractTableModel{
	private List<Magacioner> podaci;
	String[] kolone = new String[] { "Magacin", "JMB zaposlenog", "Ime", "Prezime", "Plata"};

	public MagacionerTableModel(List<Magacioner> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Magacioner> podaci) {
		this.podaci = podaci;
	}

	public Magacioner getMagacionerAtRow(int rowIndex) {
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
		Magacioner red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getMagacin();
		else if (columnIndex == 1)
			return red.getZaposleni().getJmb();
		else if (columnIndex == 2)
			return red.getZaposleni().getIme();
		else if (columnIndex == 3)
			return red.getZaposleni().getPrezime();
		else if (columnIndex == 4)
			return red.getZaposleni().getPlata();
		
		
		else
			return null;
	}

}
