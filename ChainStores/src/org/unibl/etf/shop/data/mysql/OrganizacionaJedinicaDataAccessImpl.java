package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.OrganizacionaJedinicaDataAccess;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.OrganizacionaJedinica;

public class OrganizacionaJedinicaDataAccessImpl implements OrganizacionaJedinicaDataAccess{
	
	@Override
	public OrganizacionaJedinica organizacionaJedinica(int idOJ) {
		OrganizacionaJedinica retVal=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select O.Id_OrganizacioneJedinice, O.Email, O.Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from organizaciona_jedinica O\r\n" + 
				"inner join mjesto M on M.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"where Id_OrganizacioneJedinice=?\r\n" + 
				"order by Id_OrganizacioneJedinice;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idOJ);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new OrganizacionaJedinica(rs.getInt(1), rs.getString(2),rs.getString(3),
						new Mjesto(rs.getString(4),rs.getString(5)));
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
	public List<OrganizacionaJedinica> organizacioneJediniceSve(){
		List<OrganizacionaJedinica> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select O.Id_OrganizacioneJedinice, O.Email, O.Adresa, M.BrojPoste, M.Naziv " + 
				" from organizaciona_jedinica O" + 
				" inner join mjesto M on M.BrojPoste=O.MJESTO_BrojPoste"
				+ " order by O.Id_OrganizacioneJedinice asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new OrganizacionaJedinica(rs.getInt(1), rs.getString(2),rs.getString(3),
						new Mjesto(rs.getString(4),rs.getString(5))));
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
	public List<OrganizacionaJedinica> organizacioneJedinice(int idOj) {
		List<OrganizacionaJedinica> retVal = new ArrayList<OrganizacionaJedinica>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select O.Id_OrganizacioneJedinice, O.Email, O.Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from organizaciona_jedinica O\r\n" + 
				"inner join mjesto M on M.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"where Id_OrganizacioneJedinice=?\r\n" + 
				"order by Id_OrganizacioneJedinice;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1,idOj);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new OrganizacionaJedinica(rs.getInt(1), rs.getString(2), rs.getString(3), 
						new Mjesto(rs.getString(4), rs.getString(5))));
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
	public List<OrganizacionaJedinica> organizacioneJedinice(int idOj, String mjesto) {
		List<OrganizacionaJedinica> retVal = new ArrayList<OrganizacionaJedinica>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select O.Id_OrganizacioneJedinice, O.Email, O.Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from organizaciona_jedinica O\r\n" + 
				"inner join mjesto M on M.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"where Id_OrganizacioneJedinice = ?\r\n" + 
				"and  O.MJESTO_BrojPoste = ?\r\n" + 
				"order by Id_OrganizacioneJedinice;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1,idOj);
			ps.setString(2, mjesto);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new OrganizacionaJedinica(rs.getInt(1), rs.getString(2), rs.getString(3), 
						new Mjesto(rs.getString(4), rs.getString(5))));
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
	public boolean dodajOrganizacionuJedinicu(OrganizacionaJedinica oj){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO organizaciona_jedinica VALUES "
				+ "(?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, oj.getId_OrganizacioneJedinice());
			ps.setString(2, oj.getEmail());
			ps.setString(3, oj.getAdresa());
			ps.setString(4, oj.getMJESTO_BrojPoste().getBrojPoste());
			

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
	public boolean azurirajOrganizacionuJedinicu(OrganizacionaJedinica oj) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE organizaciona_jedinica SET "
				+ "Email=?, "
				+ "Adresa=?, "
				+ "MJESTO_BrojPoste=? "
				+ "WHERE Id_OrganizacioneJedinice=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, oj.getEmail());
			ps.setString(2, oj.getAdresa());
			ps.setString(3, oj.getMJESTO_BrojPoste().getBrojPoste());
			ps.setInt(4, oj.getId_OrganizacioneJedinice());
			
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
	public boolean obrisiOrganizacionuJedinicu(int idOJ) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM organizaciona_jedinica "
				+ "WHERE Id_OrganizacioneJedinice=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, idOJ);

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
	public static void main(String[] args) {
		OrganizacionaJedinicaDataAccessImpl o=new OrganizacionaJedinicaDataAccessImpl();
		OrganizacionaJedinica oj=o.organizacionaJedinica(1);
		System.out.print(oj);
		
		
	}
	
}