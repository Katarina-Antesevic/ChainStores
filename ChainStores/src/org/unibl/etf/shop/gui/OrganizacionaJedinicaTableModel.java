package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.OrganizacionaJedinica;

@SuppressWarnings("serial")
public class OrganizacionaJedinicaTableModel extends AbstractTableModel {
	private List<OrganizacionaJedinica> podaci;
	String[] kolone = new String[] { "ID", "Email", "Adresa","Broj poste" };

	public OrganizacionaJedinicaTableModel(List<OrganizacionaJedinica> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<OrganizacionaJedinica> podaci) {
		this.podaci = podaci;
	}

	public OrganizacionaJedinica getOrganizacionaJedinicaAtRow(int rowIndex) {
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
		OrganizacionaJedinica red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getId_OrganizacioneJedinice();
		else if (columnIndex == 1)
			return red.getEmail();
		else if (columnIndex == 2)
			return red.getAdresa();
		else if (columnIndex == 3)
			return red.getMJESTO_BrojPoste();
		
		else
			return null;
	}

}
