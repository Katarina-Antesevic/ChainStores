package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.TrgovacDataAccess;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.OrganizacionaJedinica;
import org.unibl.etf.shop.entity.Prodavnica;
import org.unibl.etf.shop.entity.Trgovac;
import org.unibl.etf.shop.entity.Zaposleni;

public class TrgovacDataAccessImpl implements TrgovacDataAccess{
	
	@Override
	public List<Trgovac> trgovciSvi(){
		List<Trgovac> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select Z.JMB_Zaposlenog, Z.Ime, Z.Prezime, Z.Email, Z.Telefon, Plata, DatumOd, Z.Adresa, \r\n" + 
				"M.BrojPoste, M.Naziv, O.Id_OrganizacioneJedinice, \r\n" + 
				"MAG.Naziv,O.Email, O.Adresa, MJ.BrojPoste, MJ.Naziv \r\n" + 
				"from Trgovac MA\r\n" + 
				"inner join zaposleni Z on Z.JMB_Zaposlenog=MA.ZAPOSLENI_JMB_Zaposlenog \r\n" + 
				"inner join mjesto M on M.BrojPoste=Z.MJESTO_BrojPoste \r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=MA.PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice\r\n" + 
				"inner join prodavnica MAG on MAG.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=O.Id_OrganizacioneJedinice  \r\n" + 
				"inner join mjesto MJ on MJ.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				" order by ZAPOSLENI_JMB_Zaposlenog asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Trgovac(new Prodavnica(new OrganizacionaJedinica(rs.getInt(11),rs.getString(13),rs.getString(14),
						new Mjesto(rs.getString(15),rs.getString(16))), rs.getString(12)),
						new Zaposleni(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
								rs.getDouble(6),rs.getDate(7),rs.getString(8),
								new Mjesto(rs.getString(9),rs.getString(10)))));
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
		
		
	}
	
	@Override
	public List<Trgovac> trgovci(String jmbZaposlenog){
		List<Trgovac> retVal = new ArrayList<Trgovac>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select Z.JMB_Zaposlenog, Z.Ime, Z.Prezime, Z.Email, Z.Telefon, Plata, DatumOd, Z.Adresa, \r\n" + 
				"M.BrojPoste, M.Naziv, O.Id_OrganizacioneJedinice, \r\n" + 
				"MAG.Naziv,O.Email, O.Adresa, MJ.BrojPoste, MJ.Naziv \r\n" + 
				"from Trgovac MA\r\n" + 
				"inner join zaposleni Z on Z.JMB_Zaposlenog=MA.ZAPOSLENI_JMB_Zaposlenog \r\n" + 
				"inner join mjesto M on M.BrojPoste=Z.MJESTO_BrojPoste \r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=MA.PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice\r\n" + 
				"inner join prodavnica MAG on MAG.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=O.Id_OrganizacioneJedinice  \r\n" + 
				"inner join mjesto MJ on MJ.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"where Z.JMB_Zaposlenog like ?\r\n" + 
				" order by ZAPOSLENI_JMB_Zaposlenog asc;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbZaposlenog);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Trgovac(new Prodavnica(new OrganizacionaJedinica(rs.getInt(11),rs.getString(13),rs.getString(14),
						new Mjesto(rs.getString(15),rs.getString(16))), rs.getString(12)),
						new Zaposleni(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
								rs.getDouble(6),rs.getDate(7),rs.getString(8),
								new Mjesto(rs.getString(9),rs.getString(10)))));
						
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps, rs);
		}
		return retVal;
	}
	
	@Override
	public boolean dodajTrgovca(Trgovac trgovac){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO trgovac VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, trgovac.getZaposleni().getJmb());
			ps.setInt(2, trgovac.getProdavnica().getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
	
	@Override
	public boolean azurirajTrgovca(Trgovac trgovac){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE trgovac SET "
				+ "PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=? "
				+ " where ZAPOSLENI_JMB_Zaposlenog=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, trgovac.getProdavnica().getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			ps.setString(2, trgovac.getZaposleni().getJmb());
			
			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
	
	@Override
	public boolean obrisiTrgovca(String jmbZaposlenog){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM trgovac "
				+ "WHERE ZAPOSLENI_JMB_Zaposlenog=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbZaposlenog);

			retVal = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(ps);
		}
		return retVal;
	}
	
	/*public static void main(String[] args) {
		
		TrgovacDataAccessImpl t=new TrgovacDataAccessImpl();
		/*ZaposleniDataAccessImpl z=new ZaposleniDataAccessImpl();
		ProdavnicaDataAccessImpl p=new ProdavnicaDataAccessImpl();
		
		List<Zaposleni> zaposleni=z.zaposleni("02365895476");
		Zaposleni za=zaposleni.get(0);
		
		List<Prodavnica> pr=p.prodavnice("Shop01");
		Prodavnica pp=pr.get(0);
		
		Trgovac trgovac=new Trgovac(pp,za);
		System.out.print(t.obrisiTrgovca("02365895476"));
		
		
		
	}*/

}
