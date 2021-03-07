package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.MjestoDataAccess;
import org.unibl.etf.shop.entity.Mjesto;

public class MjestoDataAccessImpl implements MjestoDataAccess {
	
	@Override
	public List<Mjesto> mjestaSva(){
		List<Mjesto> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT * from mjesto "
				+ "ORDER BY Naziv ASC ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Mjesto(rs.getString("BrojPoste"), rs.getString("Naziv")));
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
	public List<Mjesto> mjesta(String brojPoste){
		List<Mjesto> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT BrojPoste, Naziv "
				+ "FROM mjesto "
				+ "WHERE BrojPoste LIKE ? "
				+ "ORDER BY Naziv ASC ";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, brojPoste);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Mjesto(rs.getString("BrojPoste"), rs.getString("Naziv")));
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
	public boolean dodajMjesto(Mjesto mjesto) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO mjesto VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, mjesto.getBrojPoste());
			ps.setString(2, mjesto.getNaziv());

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
	public boolean azirurajMjesto(Mjesto mjesto) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE mjesto SET "
				+ "Naziv=? "
				+ "WHERE BrojPoste=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, mjesto.getNaziv());
			ps.setString(2, mjesto.getBrojPoste());

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
	public boolean obrisiMjesto(String brojPoste) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM mjesto "
				+ "WHERE BrojPoste=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, brojPoste);

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
		MjestoDataAccessImpl m=new MjestoDataAccessImpl();
		System.out.println(m.obrisiMjesto("81000"));
		
		
	}*/
	
	
	}
		
		
	