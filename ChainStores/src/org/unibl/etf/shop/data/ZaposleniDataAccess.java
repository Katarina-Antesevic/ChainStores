package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.Zaposleni;

public interface ZaposleniDataAccess {
	
	List<Zaposleni> zaposleniSvi();
	//Zaposleni zaposlenik(String jmb);
	List<Zaposleni> zaposleni(String jmb);
	List<Zaposleni> zaposleni(String jmb, String mjesto);
	boolean dodajZaposlenog(Zaposleni zaposleni);
	boolean azurirajZaposlenog(Zaposleni zaposleni);
	boolean obrisiZaposlenog(String jmb);
}
