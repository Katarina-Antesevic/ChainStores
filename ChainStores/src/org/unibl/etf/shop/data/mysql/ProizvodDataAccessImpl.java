package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.ProizvodDataAccess;
import org.unibl.etf.shop.entity.Dobavljac;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.Proizvod;
import org.unibl.etf.shop.entity.Proizvodjac;

public class ProizvodDataAccessImpl implements ProizvodDataAccess {
	
	@Override
	public Proizvod proizvod(String barkod) {
		Proizvod retVal=null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select P.Barkod, NazivPr, Cijena, PR.JIB_Proizvodjaca, NazivP, PR.Email,\r\n" + 
				"PR.Telefon, PR.Adresa, M.BrojPoste, M.Naziv, D.JIB_Dobavljaca, D.NazivD, D.Email, \r\n" + 
				"D.Telefon, D.Adresa, MJ.BrojPoste, MJ.Naziv\r\n" + 
				"from proizvod P\r\n" + 
				"inner join proizvodjac PR on PR.JIB_Proizvodjaca=P.PROIZVODJAC_JIB \r\n" + 
				"inner join dobavljac D on D.JIB_Dobavljaca=P.DOBAVLJAC_JIB_Dobavljaca \r\n" + 
				"inner join mjesto M on M.BrojPoste=PR.MJESTO_BrojPoste\r\n" + 
				"inner join mjesto MJ on MJ.BrojPoste=D.MJESTO_BrojPoste\r\n" + 
				"WHERE Barkod like ?\r\n" + 
				"ORDER BY Barkod ASC;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, barkod);
			rs = ps.executeQuery();

			if (rs.next())
				retVal = new Proizvod(rs.getString(1), rs.getString(2), rs.getDouble(3), 
						
						new Proizvodjac(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
								rs.getString(8)
						,new Mjesto(rs.getString(9), rs.getString(10))) 
						
						, 
						new Dobavljac(rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), 
								rs.getString(15)
						,new Mjesto(rs.getString(16), rs
						.getString(17))));
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
	public List<Proizvod> proizvodiSvi(){
		List<Proizvod> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select P.Barkod, NazivPr, Cijena, PR.JIB_Proizvodjaca, NazivP, PR.Email,\r\n" + 
				"PR.Telefon, PR.Adresa, M.BrojPoste, M.Naziv, D.JIB_Dobavljaca, D.NazivD, D.Email, \r\n" + 
				"D.Telefon, D.Adresa, MJ.BrojPoste, MJ.Naziv\r\n" + 
				"from proizvod P\r\n" + 
				"inner join proizvodjac PR on PR.JIB_Proizvodjaca=P.PROIZVODJAC_JIB \r\n" + 
				"inner join dobavljac D on D.JIB_Dobavljaca=P.DOBAVLJAC_JIB_Dobavljaca \r\n" + 
				"inner join mjesto M on M.BrojPoste=PR.MJESTO_BrojPoste\r\n" + 
				"inner join mjesto MJ on MJ.BrojPoste=D.MJESTO_BrojPoste\r\n" + 
				"ORDER BY Barkod ASC;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Proizvod(rs.getString(1), rs.getString(2), rs.getDouble(3), 
						
						new Proizvodjac(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
								rs.getString(8)
						,new Mjesto(rs.getString(9), rs.getString(10))) 
						
						, 
						new Dobavljac(rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), 
								rs.getString(15)
						,new Mjesto(rs.getString(16), rs
						.getString(17)))));
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
	public List<Proizvod> proizvodi(String barkod){
		List<Proizvod> retVal = new ArrayList<Proizvod>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select P.Barkod, NazivPr, Cijena, PR.JIB_Proizvodjaca, NazivP, PR.Email,\r\n" + 
				"PR.Telefon, PR.Adresa, M.BrojPoste, M.Naziv, D.JIB_Dobavljaca, D.NazivD, D.Email, \r\n" + 
				"D.Telefon, D.Adresa, MJ.BrojPoste, MJ.Naziv\r\n" + 
				"from proizvod P\r\n" + 
				"inner join proizvodjac PR on PR.JIB_Proizvodjaca=P.PROIZVODJAC_JIB \r\n" + 
				"inner join dobavljac D on D.JIB_Dobavljaca=P.DOBAVLJAC_JIB_Dobavljaca \r\n" + 
				"inner join mjesto M on M.BrojPoste=PR.MJESTO_BrojPoste\r\n" + 
				"inner join mjesto MJ on MJ.BrojPoste=D.MJESTO_BrojPoste\r\n" + 
				"WHERE Barkod like ?\r\n" + 
				"ORDER BY Barkod ASC;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, MySQLUtilities.getInstance().preparePattern(barkod));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Proizvod(rs.getString(1), rs.getString(2), rs.getDouble(3), 
						
						new Proizvodjac(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
								rs.getString(8)
						,new Mjesto(rs.getString(9), rs.getString(10))) 
						
						, 
						new Dobavljac(rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), 
								rs.getString(15)
						,new Mjesto(rs.getString(16), rs
						.getString(17)))));
			
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
	public boolean dodajProizvod(Proizvod proizvod) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO proizvod VALUES "
				+ "(?, ?, ?, ?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, proizvod.getBarkod());
			ps.setString(2, proizvod.getNaziv());
			ps.setDouble(3, proizvod.getCijena());
			ps.setString(4, proizvod.getProizvodjac().getJib());
			ps.setString(5, proizvod.getDobavljac().getJib());
			

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
	public boolean azirirajProizvod(Proizvod proizvod) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE proizvod SET "
				+ "NazivPr=? ,"
				+ "Cijena=? "
				+ "WHERE Barkod=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, proizvod.getNaziv());
			ps.setDouble(2, proizvod.getCijena());
			ps.setString(3, proizvod.getBarkod());
			
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
	public boolean obrisiProizvod(String barkod){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM proizvod "
				+ "WHERE Barkod=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, barkod);

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
		ProizvodDataAccessImpl p=new ProizvodDataAccessImpl();
		
		Proizvod proizvod=p.proizvod("012598623402");
		System.out.print(proizvod);
		
		
		
		
	}*/
	
	


}
