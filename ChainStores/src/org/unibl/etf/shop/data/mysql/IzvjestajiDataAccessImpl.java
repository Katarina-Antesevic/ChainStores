package org.unibl.etf.shop.data.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import org.unibl.etf.shop.data.IzvjestajiDataAccess;
import org.unibl.etf.shop.entity.Magacin;
import org.unibl.etf.shop.entity.Magacioner;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.OrganizacionaJedinica;
import org.unibl.etf.shop.entity.Prodavnica;
import org.unibl.etf.shop.entity.Trgovac;
import org.unibl.etf.shop.entity.Zaposleni;



public class IzvjestajiDataAccessImpl implements IzvjestajiDataAccess {
	
	@Override
	public double minimalnaPlataTrgovca(int idTrgovine) {
		
		double retVal=0.0;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{ CALL minimalna_plata_zaposlenog_u_Prodavnici(?,?) }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, idTrgovine);
			cs.registerOutParameter(2, Types.DECIMAL);
			cs.execute();
			retVal = cs.getDouble(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
	
	@Override 
	public double minimalnaPlataMagacionera(int idMagacina) {
		double retVal=0.0;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{ CALL minimalna_plata_zaposlenog_u_Magacinu(?,?) }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, idMagacina);
			cs.registerOutParameter(2, Types.DECIMAL);
			cs.execute();
			retVal = cs.getDouble(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
	
	
	@Override
	public double maksimalnePlataMagacionera(int idMagacina) {
		double retVal=0.0;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{ CALL maksimalna_plata_zaposlenog_u_Magacinu(?,?) }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, idMagacina);
			cs.registerOutParameter(2, Types.DECIMAL);
			cs.execute();
			retVal = cs.getDouble(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
	
	@Override
	public List<Trgovac> trgovciMax(int idTrgovine){
		double id=maksimalnaPlataTrgovca(idTrgovine);
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
				" where Plata = ? "
				+ " and MA.PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice= ?"
				+ " order by Z.JMB_Zaposlenog asc";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, id);
			ps.setInt(2, idTrgovine);
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
	public double maksimalnaPlataTrgovca(int idTrgovine) {
		double retVal=0.0;
		Connection conn = null;
		CallableStatement cs = null;

		String query = "{ CALL maksimalna_plata_zaposlenog_u_Prodavnici( ? , ? ) }";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);
			cs.setInt(1, idTrgovine);
			cs.registerOutParameter(2, Types.DECIMAL);
			cs.execute();
			retVal = cs.getDouble(2);
			
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs);
		}
		return retVal;
	}
	
	@Override
	public List<Trgovac> trgovciMin(int idTrgovine){
		double id=minimalnaPlataTrgovca(idTrgovine);
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
				" where Plata =? "
				+ " and MA.PRODAVNICA_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice= ?"
				+ " order by Z.JMB_Zaposlenog asc;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, id);
			ps.setInt(2, idTrgovine);
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
	public List<Magacioner> magacioneriMax(int idMagacina){
		
		double id=maksimalnePlataMagacionera(idMagacina);
		List<Magacioner> retVal = new ArrayList<Magacioner>();
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
				" where Plata=?"
				+ " and MA.MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=? "
				+ " order by Z.JMB_Zaposlenog asc; ";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, id);
			ps.setInt(2, idMagacina);
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
	public List<Magacioner> magacioneriMin(int idMagacina){
		double id=minimalnaPlataMagacionera(idMagacina);
		List<Magacioner> retVal = new ArrayList<Magacioner>();
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
				" where Plata=? "
				+ "   and MA.MAGACIN_ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=? "
				+ " order by Z.JMB_Zaposlenog asc;";
				

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setDouble(1, id);
			ps.setInt(2, idMagacina);
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
	public Vector<Vector<Object>> zaposleni2020(){
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM zaposleni_datum_2020";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1)); 
				red.add(rs.getString(2)); 
				red.add(rs.getString(3));
				red.add(rs.getDouble(4)); 
				red.add(rs.getDate(5));
				retVal.add(red);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs, rs);
		}
		return retVal;
	}
	
	@Override
	public Vector<Vector<Object>> zaposleni2021(){
		Vector<Vector<Object>> retVal = new Vector<Vector<Object>>();
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String query = "SELECT * FROM zaposleni_datum_2021";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			cs = conn.prepareCall(query);

			rs = cs.executeQuery();
			while (rs.next()) {
				Vector<Object> red = new Vector<Object>();
				red.add(rs.getString(1)); 
				red.add(rs.getString(2)); 
				red.add(rs.getString(3));
				red.add(rs.getDouble(4)); 
				red.add(rs.getDate(5));
				retVal.add(red);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MySQLUtilities.getInstance().showSQLException(e);
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			MySQLUtilities.getInstance().close(cs, rs);
		}
		return retVal;
	}
	
	}
	
	
	/*public static void main(String[] args) {
		IzvjestajiDataAccessImpl i=new IzvjestajiDataAccessImpl();
		System.out.println(i.maksimalnePlataMagacionera(4));
		
		
		
		
	}*/
			



