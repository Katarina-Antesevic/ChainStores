package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.DobavljacDataAccess;
import org.unibl.etf.shop.entity.Dobavljac;
import org.unibl.etf.shop.entity.Mjesto;

public class DobavljacDataAccessImpl implements DobavljacDataAccess{
	
	@Override
	public List<Dobavljac> dobavljaciSvi(){
		List<Dobavljac> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select JIB_Dobavljaca, NazivD, Email, Telefon, Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from dobavljac \r\n" + 
				"inner join mjesto M on M.BrojPoste=MJESTO_BrojPoste\r\n" + 
				"order by JIB_Dobavljaca asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Dobavljac(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
						,new Mjesto(rs.getString(6), rs
						.getString(7))));
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
	public List<Dobavljac> dobavljaci(String jibDobavljaca){
		List<Dobavljac> retVal = new ArrayList<Dobavljac>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select JIB_Dobavljaca, NazivD, Email, Telefon, Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from dobavljac D\r\n" + 
				"inner join mjesto M on M.BrojPoste=D.MJESTO_BrojPoste\r\n" + 
				"where JIB_Dobavljaca like ?\r\n" + 
				"order by  JIB_Dobavljaca;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(jibDobavljaca));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Dobavljac(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
						,new Mjesto(rs.getString(6), rs
						.getString(7))));
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
	public boolean dodajDobavljaca(Dobavljac dobavljac) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO dobavljac VALUES "
				+ "(?, ?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, dobavljac.getJib());
			ps.setString(2, dobavljac.getNaziv());
			ps.setString(3, dobavljac.getEmail());
			ps.setString(4, dobavljac.getTelefon());
			ps.setString(5, dobavljac.getAdresa());
			ps.setString(6, dobavljac.getMJESTO_BrojPoste().getBrojPoste());
			

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
	public boolean azurirajDobavljaca(Dobavljac dobavljac) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE dobavljac SET "
				+ "Email=?, "
				+ "Telefon=?, "
				+ "Adresa=?, "
				+ "MJESTO_BrojPoste=? "
				+ "WHERE JIB_Dobavljaca=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, dobavljac.getEmail());
			ps.setString(2, dobavljac.getTelefon());
			ps.setString(3, dobavljac.getAdresa());
			ps.setString(4, dobavljac.getMJESTO_BrojPoste().getBrojPoste());
			ps.setString(5,dobavljac.getJib());
			
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
	public boolean obrisiDobavljaca(String jib){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM dobavljac "
				+ "WHERE JIB_Dobavljaca=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jib);

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
		DobavljacDataAccessImpl d= new DobavljacDataAccessImpl();
		MjestoDataAccessImpl m=new MjestoDataAccessImpl();
		Mjesto mjesto=m.mjesto(78000);
		Dobavljac dob=new Dobavljac("00000000000", "Dobavljac Banja Luka","dbl@gmail.com","066666666","Milana Tepica 123",mjesto);
		System.out.print(d.obrisiDobavljaca("00000000000"));
		
		
	}*/
	

}
