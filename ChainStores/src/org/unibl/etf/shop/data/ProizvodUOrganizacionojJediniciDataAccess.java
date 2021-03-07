package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.ProizvodUOrganizacionojJedinici;

public interface ProizvodUOrganizacionojJediniciDataAccess {
	
	List<ProizvodUOrganizacionojJedinici> proizvodiUOrganizacionojJediniciSvi();
	List<ProizvodUOrganizacionojJedinici> proizvodiUOrganizacionojJedinici(int oJ);
	boolean dodajProizvodUOrganizacionojJedinici(ProizvodUOrganizacionojJedinici p);
	boolean azurirajProizvodUOrganizacionojJedinici(ProizvodUOrganizacionojJedinici p);
	boolean obrisiProizvodUOrganizacionojJedinici(String barkod, int idOJ);

}
