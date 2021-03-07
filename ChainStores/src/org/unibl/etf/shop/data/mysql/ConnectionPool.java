package org.unibl.etf.shop.data.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ConnectionPool {
	/*ConnectionPool.properties: url:ako hocemo da promijenimo dbms samo url promijenimo
	urename i password za pristup mysql-u
	preconnect count je broj konekcija koje se otvaraju na pocetku,
	kad zatreba konekcija za izvrsavanje iskaza, dohvatimo jednu od tih otvorenih konekcija, iskoristimo
	i vratimo u pool
	max connections je max br konekcija, u slucaju da se desi da je svih 5 konekcija
	zauzeto i dodje zahtjev za 6. ako je npr max connesctions 25, u tom trenutku bice otvorena konekcija
	koju mozemo koristiti za izvrsavanje iskaza
	->max br konekcija u jednom trenutku je 25
	kada se vracaju u pool provjerava se koliko je max dozvoljeni br slobodnih konekcija
	da je npr maxIdleConnections=10 onda bi moglo da se desi ako u nekom trenutku koristimo 20 konekcija
	one kada se vracaju jedna po jedna u pool, vracaju se do 10, ali kada se vrati 11. ona ce biti
	ZATVORENA , jer je max br konakcija u pool-u 10*/
	
	
	// Properties file should be named as this class, and placed in the same package as this class.
	// Otherwise, set BUNDLE_NAME explicitly.
	private static final String BUNDLE_NAME = ConnectionPool.class.getName();
	
	private String jdbcURL;
	private String username;
	private String password;
	private int preconnectCount;
	private int maxIdleConnections;
	private int maxConnections;

	private int connectCount;
	private List<Connection> usedConnections;
	private List<Connection> freeConnections;

	private static ConnectionPool instance;

	public static ConnectionPool getInstance() {
		if (instance == null)
			instance = new ConnectionPool();
		return instance;
	}

	private ConnectionPool() {
		readConfiguration();
		try {
			freeConnections = new ArrayList<Connection>();
			usedConnections = new ArrayList<Connection>();

			for (int i = 0; i < preconnectCount; i++) {
				Connection conn = DriverManager.getConnection(jdbcURL, username, password);
				freeConnections.add(conn);
			}
			connectCount = preconnectCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readConfiguration() {
		ResourceBundle bundle = PropertyResourceBundle.getBundle(BUNDLE_NAME);
		jdbcURL = bundle.getString("jdbcURL");
		username = bundle.getString("username");
		password = bundle.getString("password");
		preconnectCount = 0;
		maxIdleConnections = 10;
		maxConnections = 10;
		try {
			preconnectCount = Integer.parseInt(bundle
					.getString("preconnectCount"));
			maxIdleConnections = Integer.parseInt(bundle
					.getString("maxIdleConnections"));
			maxConnections = Integer.parseInt(bundle
					.getString("maxConnections"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized Connection checkOut() throws SQLException {
		Connection conn = null;
		if (freeConnections.size() > 0) {
			conn = freeConnections.remove(0);
			usedConnections.add(conn);
		} else {
			if (connectCount < maxConnections) {
				conn = DriverManager.getConnection(jdbcURL, username, password);
				usedConnections.add(conn);
				connectCount++;
			} else {
				try {
					wait();
					conn = freeConnections.remove(0);
					usedConnections.add(conn);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return conn;
	}

	public synchronized void checkIn(Connection conn) {
		if (conn == null)
			return;
		if (usedConnections.remove(conn)) {
			freeConnections.add(conn);
			while (freeConnections.size() > maxIdleConnections) {
				int lastOne = freeConnections.size() - 1;
				Connection c = freeConnections.remove(lastOne);
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			notify();
		}
	}
	
}