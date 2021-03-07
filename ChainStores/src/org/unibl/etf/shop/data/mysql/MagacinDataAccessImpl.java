package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.unibl.etf.shop.data.MagacinDataAccess;
import org.unibl.etf.shop.entity.Magacin;
import org.unibl.etf.shop.entity.Mjesto;
import org.unibl.etf.shop.entity.OrganizacionaJedinica;

public class MagacinDataAccessImpl implements MagacinDataAccess{
	
	@Override
	public List<Magacin> magaciniSvi(){
		List<Magacin> retVal=new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select O.Id_OrganizacioneJedinice, M.Naziv, O.Email, O.Adresa, J.BrojPoste, J.Naziv\r\n" + 
				"from magacin M\r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=M.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice\r\n" + 
				"inner join mjesto J on J.BrojPoste=O.MJESTO_BrojPoste\r\n" + 
				"order by O.Id_OrganizacioneJedinice asc;";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Magacin(new OrganizacionaJedinica(rs.getInt(1),rs.getString(3),rs.getString(4),
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
	public List<Magacin> magacini(String NazivM){
		List<Magacin> retVal = new ArrayList<Magacin>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "select O.Id_OrganizacioneJedinice, M.Naziv, O.Email, O.Adresa, J.BrojPoste, J.Naziv\r\n" + 
				"from magacin M\r\n" + 
				"inner join organizaciona_jedinica O on O.Id_OrganizacioneJedinice=M.ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice\r\n" + 
				"inner join mjesto J on J.BrojPoste=O.MJESTO_BrojPoste\r\n"
				+ " where M.Naziv like ?" + 
				"order by O.Id_OrganizacioneJedinice asc;";

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, MySQLUtilities.getInstance().preparePattern(NazivM));
			rs = ps.executeQuery();

			while (rs.next())
				retVal.add(new Magacin(new OrganizacionaJedinica(rs.getInt(1),rs.getString(3),rs.getString(4),
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
	public boolean dodajMagacin(Magacin magacin) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO magacin VALUES "
				+ "(?, ?) ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, magacin.getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			ps.setString(2, magacin.getNaziv());
			

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
	public boolean azurirajMagacin(Magacin magacin) {
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "update magacin set\r\n" + 
				"Naziv=?\r\n" + 
				"where ORGANIZACIONA_JEDINICA_Id_OrganizacioneJedinice=?; ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, magacin.getNaziv());
			ps.setInt(2, magacin.getOrganizacionaJedinica().getId_OrganizacioneJedinice());
			
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
	public boolean obrisiMagacin(String nazivMagacina){
		boolean retVal = false;
		Connection conn = null;
		PreparedStatement ps = null;

		String query = "DELETE FROM magacin "
				+ "WHERE Naziv=? ";
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, nazivMagacina);

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
		MagacinDataAccessImpl m=new MagacinDataAccessImpl();
		OrganizacionaJedinicaDataAccessImpl o=new OrganizacionaJedinicaDataAccessImpl();
		OrganizacionaJedinica oj=o.organizacionaJedinica(5);
		Magacin magacin=new Magacin(oj,"Magacin 5");
		System.out.print(m.obrisiMagacin("Magacin 5"));
		
	}*/
	
	

}
