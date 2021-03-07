package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.Prodavnica;


@SuppressWarnings("serial")
public class ProdavnicaTableModel extends AbstractTableModel {
	
	private List<Prodavnica> podaci;
	String[] kolone = new String[] { "ID organizacione jedinice", "Naziv"};

	public ProdavnicaTableModel(List<Prodavnica> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Prodavnica> podaci) {
		this.podaci = podaci;
	}

	public Prodavnica getProdavnicaAtRow(int rowIndex) {
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
		Prodavnica red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getOrganizacionaJedinica();
		else if (columnIndex == 1)
			return red.getNaziv();
		
		else
			return null;
	}
	

}
