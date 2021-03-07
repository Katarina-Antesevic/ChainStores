package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.MagacionerDataAccess;
import org.unibl.etf.shop.entity.Magacin;
import org.unibl.etf.shop.entity.Magacioner;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.OrganizacionaJedinica;
import org.unibl.etf.shop.entity.Zaposleni;

public class MagacionerDataAccessImpl implements MagacionerDataAccess {
	
	@Override
	public List<Magacioner> magacioneriSvi(){
		List<Magacioner> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select Z.JMB_Zaposlenog, Z.Ime, Z.Prezime, Z.Email, Z.Telefon, Plata, DatumOd, \r\n" + 
				"Z.Adresa, M.BrojPoste, M.Naziv, O.Id_OrganizacioneJedinice, MAG.Naziv,\r\n" + 
				"O.Email, O.Adresa, MJ.BrojPoste, MJ.Naziv\r\n" + 
				"from magacioner MA\r\n" + 
				"inner join zaposleni Z on Z.JMB_Zaposlenog=MA.ZAPOSLENI_JMB_Zaposlenog \r\n" + 
				"inner join mjesto M on M.BrojPoste=Z.MJESTO_BrojPoste \r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=MA.MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice  \r\n" + 
				"inner join magacin MAG on MAG.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=O.Id_OrganizacioneJedinice \r\n" + 
				" inner join mjesto MJ on MJ.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"order by ZAPOSLENI_JMB_Zaposlenog asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Magacioner(new Magacin(new OrganizacionaJedinica(rs.getInt(11),rs.getString(13),rs.getString(14),
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
	public List<Magacioner> magacioneri(String jmbZaposlenog){
		List<Magacioner> retVal = new ArrayList<Magacioner>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select Z.JMB_Zaposlenog, Z.Ime, Z.Prezime, Z.Email, Z.Telefon, Plata, DatumOd, "
				+ "Z.Adresa, M.BrojPoste, M.Naziv, O.Id_OrganizacioneJedinice, MAG.Naziv,"
				+ " O.Email, O.Adresa, MJ.BrojPoste, MJ.Naziv"
				+ " from magacioner MA"
				+ " inner join zaposleni Z on Z.JMB_Zaposlenog=MA.ZAPOSLENI_JMB_Zaposlenog "
				+ " inner join mjesto M on M.BrojPoste=Z.MJESTO_BrojPoste "
				+ " inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=MA.MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice  "
				+ " inner join magacin MAG on MAG.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=O.Id_OrganizacioneJedinice "
				+  " inner join mjesto MJ on MJ.BrojPoste=O.MJESTO_BrojPoste"
				+ " where ZAPOSLENI_JMB_Zaposlenog like ? "
				+ " order by ZAPOSLENI_JMB_Zaposlenog asc;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, jmbZaposlenog);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Magacioner(new Magacin(new OrganizacionaJedinica(rs.getInt(11),rs.getString(13),rs.getString(14),
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
	public boolean dodajMagacionera(Magacioner magacioner){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO magacioner VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, magacioner.getZaposleni().getJmb());
			ps.setInt(2, magacioner.getMagacin().getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			

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
	public boolean azurirajMagacionera(Magacioner magacioner){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "UPDATE magacioner SET "
				+ "MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=? "
				+ "where ZAPOSLENI_JMB_Zaposlenog=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, magacioner.getMagacin().getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			ps.setString(2, magacioner.getZaposleni().getJmb());
			
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
	public boolean obrisiMagacionera(String jmbZaposlenog){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM magacioner "
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
		MagacionerDataAccessImpl m=new MagacionerDataAccessImpl();
		MagacinDataAccessImpl md=new MagacinDataAccessImpl();
		ZaposleniDataAccessImpl z=new ZaposleniDataAccessImpl();
		
		
		List<Magacin> mm=md.magacini("Magacin01");
		Magacin magacin=mm.get(0);
		List<Zaposleni> zz=z.zaposleni("02365895476");
		Zaposleni zaposleni=zz.get(0);
		Magacioner magacioner=new Magacioner(magacin, zaposleni);
		System.out.print(m.obrisiMagacionera("02365895476"));		
		
		
		}*/
		
		
		
		
		
		
		
	}


