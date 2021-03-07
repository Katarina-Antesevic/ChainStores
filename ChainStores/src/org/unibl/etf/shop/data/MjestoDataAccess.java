package org.unibl.etf.shop.data;

import java.util.List;
import org.unibl.etf.shop.entity.Mjesto;

public interface MjestoDataAccess {
	List<Mjesto> mjestaSva();
	List<Mjesto> mjesta(String brojPoste);
	
	boolean dodajMjesto(Mjesto mjesto);
	boolean azirurajMjesto(Mjesto mjesto);
	boolean obrisiMjesto(String brojPoste);

}
