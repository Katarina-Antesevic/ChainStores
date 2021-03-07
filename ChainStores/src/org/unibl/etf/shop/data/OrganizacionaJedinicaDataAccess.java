package org.unibl.etf.shop.data;

import java.util.List;

import org.unibl.etf.shop.entity.OrganizacionaJedinica;

public interface OrganizacionaJedinicaDataAccess {
	
	OrganizacionaJedinica organizacionaJedinica(int idOJ);
	List<OrganizacionaJedinica> organizacioneJedinice(int idOj);
	List<OrganizacionaJedinica> organizacioneJediniceSve();
	List<OrganizacionaJedinica> organizacioneJedinice(int idOj, String mjesto);
	boolean dodajOrganizacionuJedinicu(OrganizacionaJedinica oj);
	boolean azurirajOrganizacionuJedinicu(OrganizacionaJedinica oj);
	boolean obrisiOrganizacionuJedinicu(int idOJ);

}
