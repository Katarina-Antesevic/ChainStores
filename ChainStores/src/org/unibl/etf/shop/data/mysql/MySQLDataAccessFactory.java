package org.unibl.etf.shop.data.mysql;

import org.unibl.etf.shop.data.DataAccessFactory;
import org.unibl.etf.shop.data.DobavljacDataAccess;
import org.unibl.etf.shop.data.IzvjestajiDataAccess;
import org.unibl.etf.shop.data.MagacinDataAccess;
import org.unibl.etf.shop.data.MagacionerDataAccess;
import org.unibl.etf.shop.data.MjestoDataAccess;
import org.unibl.etf.shop.data.OrganizacionaJedinicaDataAccess;
import org.unibl.etf.shop.data.ProdavnicaDataAccess;
import org.unibl.etf.shop.data.ProizvodDataAccess;
import org.unibl.etf.shop.data.ProizvodUOrganizacionojJediniciDataAccess;
import org.unibl.etf.shop.data.ProizvodjacDataAccess;
import org.unibl.etf.shop.data.TrgovacDataAccess;
import org.unibl.etf.shop.data.ZaposleniDataAccess;

public class MySQLDataAccessFactory extends DataAccessFactory{
	
	@Override 
	public  DobavljacDataAccess getDobavljacDataAccess() {
		return new DobavljacDataAccessImpl();
	}
	
	@Override
	public MagacinDataAccess getMagacinDataAccess() {
		return new MagacinDataAccessImpl();
	}
	
	@Override
	public MagacionerDataAccess getMagacionerDataAccess() {
		return new MagacionerDataAccessImpl();
	}
	
	@Override
	public MjestoDataAccess getMjestoDataAccess() {
		return new MjestoDataAccessImpl();
	}
	
	@Override
	public OrganizacionaJedinicaDataAccess getOrganizacionaJedinicaDataAccess() {
		return new OrganizacionaJedinicaDataAccessImpl();
	}
	
	@Override
	public ProdavnicaDataAccess getProdavnicaDataAccess() {
		return new ProdavnicaDataAccessImpl();
	}
	
	@Override
	public ProizvodDataAccess getProizvodDataAccess() {
		return new ProizvodDataAccessImpl();
	}
	
	@Override
	public ProizvodjacDataAccess getProizvodjacDataAccess() {
		return new ProizvodjacDataAccessImpl();
	}
	
	@Override
	public ProizvodUOrganizacionojJediniciDataAccess getProizvodUOrganizacionojJediniciDataAccess() {
		return new ProizvodUOrganizacionojJediniciDataAccessImpl();
	}
	
	@Override
	public TrgovacDataAccess getTrgovacDataAccess() {
		return new TrgovacDataAccessImpl();
	}
	
	@Override
	public ZaposleniDataAccess getZaposleniDataAccess() {
		return new ZaposleniDataAccessImpl();
	}
	
	@Override
	public  IzvjestajiDataAccess getIzvjestajiDataAccess() {
		return new IzvjestajiDataAccessImpl();
	}
}
