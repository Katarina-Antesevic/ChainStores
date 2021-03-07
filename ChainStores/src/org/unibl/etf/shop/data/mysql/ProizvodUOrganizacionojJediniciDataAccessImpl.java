package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.ProizvodUOrganizacionojJediniciDataAccess;
import org.unibl.etf.shop.entity.Dobavljac;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.OrganizacionaJedinica;
import org.unibl.etf.shop.entity.Proizvod;
import org.unibl.etf.shop.entity.ProizvodUOrganizacionojJedinici;
import org.unibl.etf.shop.entity.Proizvodjac;

public class ProizvodUOrganizacionojJediniciDataAccessImpl implements ProizvodUOrganizacionojJediniciDataAccess{
	
	@Override
	public List<ProizvodUOrganizacionojJedinici> proizvodiUOrganizacionojJediniciSvi(){
		List<ProizvodUOrganizacionojJedinici> retVal = new ArrayList<ProizvodUOrganizacionojJedinici>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select PR.Barkod, PR.NazivPr, PR.Cijena, O.Id_OrganizacioneJedinice,\r\n" + 
				"O.Email, O.Adresa, MJE.BrojPoste, MJE.Naziv,P.Kolicina, \r\n" + 
				"PRO.JIB_Proizvodjaca, PRO.NazivP, PRO.Email, PRO.Telefon, PRO.Adresa,M.BrojPoste,  \r\n" + 
				"M.naziv, D.JIB_Dobavljaca, D.NazivD, D.Email, D.Telefon, D.Adresa,MJ.BrojPoste, MJ.Naziv\r\n" + 
				"from proizvod_u_organizacionoj_jedinici P\r\n" + 
				"inner join proizvod PR on PR.Barkod=P.PROIZVOD_Barkod\r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice  \r\n" + 
				"inner join mjesto MJE on MJE.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"inner join proizvodjac PRO on PRO.JIB_Proizvodjaca=PR.PROIZVODJAC_JIB\r\n" + 
				"inner join dobavljac D on D.JIB_Dobavljaca=PR.DOBAVLJAC_JIB_Dobavljaca \r\n" + 
				"inner join mjesto M on M.BrojPoste=PRO.MJESTO_BrojPoste\r\n" + 
				"inner join mjesto MJ on MJ.BrojPoste=D.MJESTO_BrojPoste\r\n" + 
				"ORDER BY PROIZVOD_Barkod ASC;";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next())
				retVal.add(new ProizvodUOrganizacionojJedinici(new Proizvod(rs.getString(1),rs.getString(2),rs.getDouble(3),
						new Proizvodjac(rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
								new Mjesto(rs.getString(15),rs.getString(16))),
						new Dobavljac(rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
								new Mjesto(rs.getString(22),rs.getString(23)))),
						new OrganizacionaJedinica(rs.getInt(4),rs.getString(5),rs.getString(6),
								new Mjesto(rs.getString(7),rs.getString(8))),rs.getDouble(9)));
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
	public List<ProizvodUOrganizacionojJedinici> proizvodiUOrganizacionojJedinici(int oj){
		List<ProizvodUOrganizacionojJedinici> retVal = new ArrayList<ProizvodUOrganizacionojJedinici>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select PR.Barkod, PR.NazivPr, PR.Cijena, O.Id_OrganizacioneJedinice,\r\n" + 
				"O.Email, O.Adresa, MJE.BrojPoste, MJE.Naziv,P.Kolicina, \r\n" + 
				" PRO.JIB_Proizvodjaca, PRO.NazivP, PRO.Email, PRO.Telefon, PRO.Adresa,M.BrojPoste, \r\n" + 
				"M.naziv, D.JIB_Dobavljaca, D.NazivD, D.Email, D.Telefon, D.Adresa,MJ.BrojPoste, MJ.Naziv\r\n" + 
				" from proizvod_u_organizacionoj_jedinici P\r\n" + 
				" inner join proizvod PR on PR.Barkod=P.PROIZVOD_Barkod\r\n" + 
				" inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice \r\n" + 
				"inner join mjesto MJE on MJE.BrojPoste=O.MJESTO_BrojPoste \r\n" + 
				"inner join proizvodjac PRO on PRO.JIB_Proizvodjaca=PR.PROIZVODJAC_JIB \r\n" + 
				" inner join dobavljac D on D.JIB_Dobavljaca=PR.DOBAVLJAC_JIB_Dobavljaca\r\n" + 
				" inner join mjesto M on M.BrojPoste=PRO.MJESTO_BrojPoste\r\n" + 
				" inner join mjesto MJ on MJ.BrojPoste=D.MJESTO_BrojPoste\r\n" + 
				" WHERE ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice LIKE ?\r\n" + 
				" ORDER BY PROIZVOD_Barkod ASC";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, oj);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new ProizvodUOrganizacionojJedinici(new Proizvod(rs.getString(1),rs.getString(2),rs.getDouble(3),
						new Proizvodjac(rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),
								new Mjesto(rs.getString(15),rs.getString(16))),
						new Dobavljac(rs.getString(17),rs.getString(18),rs.getString(19),rs.getString(20),rs.getString(21),
								new Mjesto(rs.getString(22),rs.getString(23)))),
						new OrganizacionaJedinica(rs.getInt(4),rs.getString(5),rs.getString(6),
								new Mjesto(rs.getString(7),rs.getString(8))),rs.getDouble(9)));
				
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
	public boolean dodajProizvodUOrganizacionojJedinici(ProizvodUOrganizacionojJedinici p) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO proizvod_u_organizacionoj_jedinici VALUES "
				+ "(?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, p.getProizvod().getBarkod());
			ps.setInt(2, p.getOj().getId_OrganizacioneJedinice());
			ps.setDouble(3, p.getKocina());

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
	public boolean azurirajProizvodUOrganizacionojJedinici(ProizvodUOrganizacionojJedinici p){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update proizvod_u_organizacionoj_jedinici set\r\n" + 
				"Kolicina=?\r\n" + 
				"where PROIZVOD_Barkod=?\r\n" + 
				"and ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=? ;";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, p.getKocina());
			ps.setString(2, p.getProizvod().getBarkod());
			ps.setInt(3, p.getOj().getId_OrganizacioneJedinice());
			
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
	public boolean obrisiProizvodUOrganizacionojJedinici(String barkod, int idOJ){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "delete from proizvod_u_organizacionoj_jedinici\r\n" + 
				"where PROIZVOD_Barkod=?\r\n" + 
				"and ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=?;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, barkod);
			ps.setInt(2, idOJ);

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
		
		ProizvodUOrganizacionojJediniciDataAccessImpl p=new ProizvodUOrganizacionojJediniciDataAccessImpl();
		List<ProizvodUOrganizacionojJedinici> proizvodi =p.proizvodiUOrganizacionojJediniciSvi();
		
		
		for(ProizvodUOrganizacionojJedinici pr:proizvodi)
			System.out.println(pr);
		
		
	}*/
	
	
	
	
	
	
	

}
