package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.Dobavljac;

public interface DobavljacDataAccess {
	
	List<Dobavljac> dobavljaciSvi();
	List<Dobavljac> dobavljaci(String jibDobavljaca);
	boolean dodajDobavljaca(Dobavljac dobavljac);
	boolean azurirajDobavljaca(Dobavljac dobavljac);
	boolean obrisiDobavljaca(String jib);
	

}
