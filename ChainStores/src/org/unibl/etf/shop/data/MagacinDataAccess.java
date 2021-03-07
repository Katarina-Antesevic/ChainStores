package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.Magacin;

public interface MagacinDataAccess {
	
	List<Magacin> magaciniSvi();
	List<Magacin> magacini(String NazivM);
	boolean dodajMagacin(Magacin magacin);
	boolean azurirajMagacin(Magacin magacin);
	boolean obrisiMagacin(String nazivMagacina);

}
