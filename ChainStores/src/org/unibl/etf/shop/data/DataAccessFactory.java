package org.unibl.etf.shop.data;

import org.unibl.etf.shop.data.mysql.MySQLDataAccessFactory;

public abstract class DataAccessFactory {
	
	public abstract DobavljacDataAccess getDobavljacDataAccess();
	public abstract MagacinDataAccess getMagacinDataAccess();
	public abstract MagacionerDataAccess getMagacionerDataAccess();
	public abstract MjestoDataAccess getMjestoDataAccess();
	public abstract OrganizacionaJedinicaDataAccess getOrganizacionaJedinicaDataAccess();
	public abstract ProdavnicaDataAccess getProdavnicaDataAccess();
	public abstract ProizvodDataAccess getProizvodDataAccess();
	public abstract ProizvodjacDataAccess getProizvodjacDataAccess(); 
	public abstract ProizvodUOrganizacionojJediniciDataAccess getProizvodUOrganizacionojJediniciDataAccess();
	public abstract TrgovacDataAccess getTrgovacDataAccess();
	public abstract ZaposleniDataAccess getZaposleniDataAccess();
	public abstract IzvjestajiDataAccess getIzvjestajiDataAccess();
	
	public static DataAccessFactory getFactory(DataAccessFactoryType type) {
		if (DataAccessFactoryType.MySQL.equals(type)) {
			return new MySQLDataAccessFactory();
		}
		throw new IllegalArgumentException();
	}
}