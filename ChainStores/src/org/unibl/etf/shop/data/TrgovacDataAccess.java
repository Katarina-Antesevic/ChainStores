package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.Trgovac;

public interface TrgovacDataAccess {
	
	List<Trgovac> trgovciSvi();
	List<Trgovac> trgovci(String jmbZaposlenog);
	boolean dodajTrgovca(Trgovac trgovac);
	boolean azurirajTrgovca(Trgovac trgovac);
	boolean obrisiTrgovca(String jmbZaposlenog);

}
