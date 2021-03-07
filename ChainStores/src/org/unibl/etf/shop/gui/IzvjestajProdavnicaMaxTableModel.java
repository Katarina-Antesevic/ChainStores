package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;


import org.unibl.etf.shop.entity.Trgovac;

@SuppressWarnings("serial")
public class IzvjestajProdavnicaMaxTableModel extends AbstractTableModel {
	private List<Trgovac> podaci;
	String[] kolone = new String[] { "JMB", "Ime", "Prezime","Plata" };

	public IzvjestajProdavnicaMaxTableModel(List<Trgovac> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Trgovac> podaci) {
		this.podaci = podaci;
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
		Trgovac red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getZaposleni().getJmb();
		else if (columnIndex == 1)
			return red.getZaposleni().getIme();
		else if (columnIndex == 2)
			return red.getZaposleni().getPrezime();
		else if (columnIndex == 3)
			return red.getZaposleni().getPlata();
		
		else
			return null;
	}


}
