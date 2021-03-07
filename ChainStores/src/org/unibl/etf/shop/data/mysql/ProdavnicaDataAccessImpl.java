package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.ProdavnicaDataAccess;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.OrganizacionaJedinica;
import org.unibl.etf.shop.entity.Prodavnica;

public class ProdavnicaDataAccessImpl implements ProdavnicaDataAccess {
	
	@Override
	public List<Prodavnica> prodavniceSve(){
		List<Prodavnica> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select O.Id_OrganizacioneJedinice, P.Naziv, O.Email, O.Adresa, J.BrojPoste, J.Naziv\r\n" + 
				"from prodavnica P\r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=P.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice \r\n" + 
				"inner join mjesto J on J.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"order by P.Naziv asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Prodavnica(new OrganizacionaJedinica(rs.getInt(1),rs.getString(3),rs.getString(4),
						new Mjesto(rs.getString(5),rs.getString(6))),rs.getString(2)));
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
	public List<Prodavnica> prodavnice(String NazivP){
		List<Prodavnica> retVal = new ArrayList<Prodavnica>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select O.Id_OrganizacioneJedinice, P.Naziv, O.Email, O.Adresa, J.BrojPoste, J.Naziv\r\n" + 
				"from prodavnica P\r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=P.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice\r\n" + 
				"inner join mjesto J on J.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"where P.Naziv LIKE ?\r\n" + 
				"order by P.Naziv asc;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, MySQLUtilities.getInstance().preparePattern(NazivP));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Prodavnica(new OrganizacionaJedinica(rs.getInt(1),rs.getString(3),rs.getString(4),
						new Mjesto(rs.getString(5),rs.getString(6))),rs.getString(2)));
						
			
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
	public boolean dodajProdavnicu(Prodavnica prodavnica) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO prodavnica VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, prodavnica.getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			ps.setString(2, prodavnica.getNaziv());
			

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
	public boolean azurirajProdavnicu(Prodavnica prodavnica) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE prodavnica SET "
				+ "Naziv=? "
				+ "WHERE ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, prodavnica.getNaziv());
			ps.setInt(2, prodavnica.getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			
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
	public boolean obrisiProdavnicu(String nazivProdavnice){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM prodavnica "
				+ "WHERE Naziv=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, nazivProdavnice);

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
		ProdavnicaDataAccessImpl p=new ProdavnicaDataAccessImpl();
		OrganizacionaJedinicaDataAccessImpl o= new OrganizacionaJedinicaDataAccessImpl();
		OrganizacionaJedinica oj=o.organizacionaJedinica(5);
		Prodavnica pr=new Prodavnica(oj,"Prodavnica 5");
		System.out.print(p.obrisiProdavnicu("Prodavnica 5"));
	}*/
	
	
	
	
	
	

}
