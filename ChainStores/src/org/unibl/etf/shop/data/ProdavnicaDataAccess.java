package org.unibl.etf.shop.data;

import java.util.List;


import org.unibl.etf.shop.entity.Prodavnica;

public interface ProdavnicaDataAccess {
	
	List<Prodavnica> prodavniceSve();
	List<Prodavnica> prodavnice(String NazivP);
	boolean dodajProdavnicu(Prodavnica prodavnica);
	boolean azurirajProdavnicu(Prodavnica prodavnica);
	boolean obrisiProdavnicu(String nazivProdavnice);

}
