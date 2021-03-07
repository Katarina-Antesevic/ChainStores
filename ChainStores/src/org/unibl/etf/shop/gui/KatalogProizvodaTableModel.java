package org.unibl.etf.shop.gui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.unibl.etf.shop.entity.ProizvodUOrganizacionojJedinici;


@SuppressWarnings("serial")
public class KatalogProizvodaTableModel  extends AbstractTableModel {
	
	private List<ProizvodUOrganizacionojJedinici> podaci;
	String[] kolone = new String[] { "Barkod proizvoda","Naziv proizvoda","Cijena",
			"Organizaciona jedinica", "Kolicina"};

	public KatalogProizvodaTableModel(List<ProizvodUOrganizacionojJedinici> podaci) {
		setPodaci(podaci);
	}

	public void setPodaci(List<ProizvodUOrganizacionojJedinici> podaci) {
		this.podaci = podaci;
	}

	public ProizvodUOrganizacionojJedinici getProizvodUOrganizacionojJediniciAtRow(
			int rowIndex) {
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
		ProizvodUOrganizacionojJedinici red = podaci.get(rowIndex);
		if (columnIndex == 0)
			return red.getProizvod().getBarkod();
		else if (columnIndex == 1)
			return red.getProizvod().getNaziv();
		else if (columnIndex == 2)
			return red.getProizvod().getCijena();
		else if (columnIndex == 3)
			return red.getOj();
		else if (columnIndex == 4)
			return red.getKocina();
		
		else
			return null;
	}


}
