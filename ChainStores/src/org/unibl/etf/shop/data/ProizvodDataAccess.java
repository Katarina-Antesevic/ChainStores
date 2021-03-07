package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.Proizvod;

public interface ProizvodDataAccess {
	
	Proizvod proizvod(String barkod);
	List<Proizvod> proizvodiSvi();
	List<Proizvod> proizvodi(String barkod);
	boolean dodajProizvod(Proizvod proizvod);
	boolean azirirajProizvod(Proizvod proizvod);
	boolean obrisiProizvod(String barkod);
}
