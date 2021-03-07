package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.Proizvodjac;

public interface ProizvodjacDataAccess {
	
	List<Proizvodjac> proizvodjaciSvi();
	List<Proizvodjac> proizvodjaci(String jibProizvodjaca);
	boolean dodajProizvodjaca(Proizvodjac proizvodjac);
	boolean azurirajProizvodjaca(Proizvodjac proizvodjac);
	boolean obrisiProizvodjaca(String jib);

}
