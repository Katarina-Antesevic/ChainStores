package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import org.unibl.etf.shop.data.ZaposleniDataAccess;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.Zaposleni;

public class ZaposleniDataAccessImpl implements ZaposleniDataAccess{
	
	@Override 
	public List<Zaposleni> zaposleniSvi(){
		List<Zaposleni> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select JMB_Zaposlenog, Ime, Prezime, Email, Telefon, Plata, DatumOd, Adresa, M.BrojPoste, M.Naziv\r\n" + 
				" from zaposleni Z\r\n" + 
				" inner join mjesto M on M.BrojPoste=Z.MJESTO_BrojPoste"
				+ " order by Z.JMB_Zaposlenog asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Zaposleni(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getDouble(6),rs.getDate(7),rs.getString(8),new Mjesto(rs.getString(9),rs.getString(10))));
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
	public List<Zaposleni> zaposleni(String jmb, String mjesto){
		List<Zaposleni> retVal = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query ="select JMB_Zaposlenog, Ime, Prezime, Email, Telefon, Plata, DatumOd, Adresa, M.BrojPoste, M.Naziv\r\n" + 
				"from zaposleni Z\r\n" + 
				"inner join mjesto M on M.BrojPoste=Z.MJESTO_BrojPoste\r\n" + 
				"where JMB_Zaposlenog like  ?\r\n" + 
				"and MJESTO_BrojPoste = ?;";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1,
					MySQLUtilities.getInstance().preparePattern(jmb));
			
			ps.setString(2, mjesto);
			rs = ps.executeQuery();
			while (rs.next())
				retVal.add(new Zaposleni(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getDouble(6),rs.getDate(7),rs.getString(8),new Mjesto(rs.getString(9),rs.getString(10))));
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
	public List<Zaposleni> zaposleni(String jmb) {
		List<Zaposleni> retVal = new ArrayList<Zaposleni>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select Z.JMB_Zaposlenog, Z.Ime, Z.Prezime, Z.Email, Z.Telefon, Z.Plata, Z.DatumOd, Z.Adresa, M.BrojPoste, M.Naziv \r\n" + 
				"from zaposleni Z\r\n" + 
				"inner join mjesto M on M.BrojPoste=Z.MJESTO_BrojPoste\r\n" + 
				"where JMB_Zaposlenog=?\r\n" + 
				"order by Prezime asc;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, MySQLUtilities.getInstance().preparePattern(jmb));
			rs = ps.executeQuery();
			
			while (rs.next())
				retVal.add(new Zaposleni(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)
						,rs.getDouble(6), rs.getDate(7), rs.getString(8),new Mjesto(rs.getString(9), rs
						.getString(10)))); 
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
	public boolean dodajZaposlenog(Zaposleni zaposleni) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO zaposleni (JMB_Zaposlenog , Ime, Prezime, Email, Telefon, Plata, Adresa, MJESTO_BrojPoste)"
				+ " VALUES "
				+ " (?, ?, ?, ?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, zaposleni.getJmb());
			ps.setString(2, zaposleni.getIme());
			ps.setString(3, zaposleni.getPrezime());
			ps.setString(4, zaposleni.getEmail());
			ps.setString(5, zaposleni.getTelefon());
			ps.setDouble(6, zaposleni.getPlata());
			ps.setString(7, zaposleni.getAdresa());
			ps.setString(8, zaposleni.getMJESTO_BrojPoste().getBrojPoste());
			

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
	public boolean azurirajZaposlenog(Zaposleni zaposleni) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE zaposleni SET "
				+ "Email=?, "
				+ "Telefon=?, "
				+ "Plata=?, "
				+ "Adresa=?, "
				+ "MJESTO_BrojPoste=? "
				+ "WHERE JMB_Zaposlenog=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, zaposleni.getEmail());
			ps.setString(2, zaposleni.getTelefon());
			ps.setDouble(3, zaposleni.getPlata());
			ps.setString(4, zaposleni.getAdresa());
			ps.setString(5, zaposleni.getMJESTO_BrojPoste().getBrojPoste());
			ps.setString(6,zaposleni.getJmb());
			
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
	public boolean obrisiZaposlenog(String jmb) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM zaposleni "
				+ "WHERE JMB_Zaposlenog=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmb);

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
		ZaposleniDataAccessImpl z=new ZaposleniDataAccessImpl();
		List<Zaposleni> zz=z.zaposleni("1256856895896", "79101");
		System.out.println(zz.get(0));
		
	}
	
	
		
		
		
		
		
	}

