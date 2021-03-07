package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.Magacioner;

public interface MagacionerDataAccess {
	
	List<Magacioner> magacioneriSvi();
	List<Magacioner> magacioneri(String jmbZaposlenog);
	boolean dodajMagacionera(Magacioner magacioner);
	boolean azurirajMagacionera(Magacioner magacioner);
	boolean obrisiMagacionera(String jmbZaposlenog);
}
