package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.Proizvod;

@SuppressWarnings("serial")
public class ProizvodTableModel extends AbstractTableModel{
	private List<Proizvod> podaci;
	String[] kolone = new String[] { "Barkod", "Naziv", 
			"Cijena","Proizvodjac","Dobavljac" };

	public ProizvodTableModel(List<Proizvod> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Proizvod> podaci) {
		this.podaci = podaci;
	}

	public Proizvod getProizvodAtRow(int rowIndex) {
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
		Proizvod red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getBarkod();
		else if (columnIndex == 1)
			return red.getNaziv();
		else if (columnIndex == 2)
			return red.getCijena();
		else if (columnIndex == 3)
			return red.getProizvodjac();
		else if (columnIndex == 4)
			return red.getDobavljac();
		
		else
			return null;
	}

}
