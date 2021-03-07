package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.ProizvodjacDataAccess;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.Proizvodjac;

public class ProizvodjacDataAccessImpl implements ProizvodjacDataAccess{
	
	@Override
	public List<Proizvodjac> proizvodjaciSvi(){
		List<Proizvodjac> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select JIB_Proizvodjaca, NazivP, Email, Telefon, Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from proizvodjac \r\n" + 
				"inner join mjesto M on M.BrojPoste=MJESTO_BrojPoste\r\n" + 
				"order by JIB_Proizvodjaca asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Proizvodjac(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
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
	public List<Proizvodjac> proizvodjaci(String jibProizvodjaca){
		List<Proizvodjac> retVal = new ArrayList<Proizvodjac>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select JIB_Proizvodjaca, NazivP, Email, Telefon, Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from proizvodjac \r\n" + 
				"inner join mjesto M on M.BrojPoste=MJESTO_BrojPoste\r\n" + 
				"where JIB_Proizvodjaca like ?\r\n" + 
				"order by JIB_Proizvodjaca asc;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(jibProizvodjaca));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Proizvodjac(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
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
	public boolean dodajProizvodjaca(Proizvodjac proizvodjac) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "insert into proizvodjac values"
				+ " (?, ?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, proizvodjac.getJib());
			ps.setString(2, proizvodjac.getNaziv());
			ps.setString(3, proizvodjac.getEmail());
			ps.setString(4, proizvodjac.getTelefon());
			ps.setString(5, proizvodjac.getAdresa());
			ps.setString(6, proizvodjac.getMJESTO_BrojPoste().getBrojPoste());
			

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
	public boolean azurirajProizvodjaca(Proizvodjac proizvodjac) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE proizvodjac SET "
				+ "Email=?, "
				+ "Telefon=?, "
				+ "Adresa=?, "
				+ "MJESTO_BrojPoste=? "
				+ "WHERE JIB_Proizvodjaca=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, proizvodjac.getEmail());
			ps.setString(2, proizvodjac.getTelefon());
			ps.setString(3, proizvodjac.getAdresa());
			ps.setString(4, proizvodjac.getMJESTO_BrojPoste().getBrojPoste());
			ps.setString(5,proizvodjac.getJib());
			
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
	public boolean obrisiProizvodjaca(String jib){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM proizvodjac "
				+ "WHERE JIB_Proizvodjaca=? ";
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
		ProizvodjacDataAccessImpl p=new ProizvodjacDataAccessImpl();
		MjestoDataAccessImpl m=new MjestoDataAccessImpl();
		Mjesto mjesto=m.mjesto(78000);
		Proizvodjac pr=new Proizvodjac("00000000000", "Proizvodjac61" ,"p61@gmail.com","051999999","Sargovacka 77",mjesto);
		System.out.print(p.obrisiProizvodjaca("00000000000"));
	}
	*/
	

	

}
