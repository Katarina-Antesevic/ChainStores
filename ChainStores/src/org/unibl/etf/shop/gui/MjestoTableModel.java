package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.Mjesto;

@SuppressWarnings("serial")
public class MjestoTableModel extends AbstractTableModel {
	
	List<Mjesto> podaci;
	String[] kolone = new String[] { "Broj poste", "Naziv" };
	
	public MjestoTableModel(List<Mjesto> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<Mjesto> podaci) {
		this.podaci = podaci;
	}

	public Mjesto getMjestoAtRow(int rowIndex) {
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
		Mjesto red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getBrojPoste();
		else if (columnIndex == 1)
			return red.getNaziv();
		else
			return null;
	}
	

}
