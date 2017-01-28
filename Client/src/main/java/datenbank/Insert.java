package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import api_client.agent;
import api_client.customer;
import api_client.quota;
import api_client.reservation;
import api_client.ship;
import api_client.tour;


/**
 * Hier werden die Datensätze in die Datenbanken hinzugefügt.
 * 
 * @author AgentFriz, Jonas Droste
 *
 */
public class Insert {

	private Connection con = null;
	private PreparedStatement pst = null;

	private String url = "jdbc:sqlite:" + "db.sqlite3";
	private String user = "postgres";
	private String password = "1q1q1q1q";

	public Insert() {

	}

	public void insertIntoBuchungen(int plaetze, int route_id, String tour_datum, String tour_startzeit,
			int schiff_id) {

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = "INSERT INTO buchungen(plaetze, route_id, tour_datum, tour_startzeit, schiff_id) VALUES( "
					+ plaetze + ", " + route_id + ", '" + tour_datum + "', '" + tour_startzeit + "', " + schiff_id
					+ ")";
			pst = con.prepareStatement(stm);
			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Insert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Insert.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}

	public void insertAgent(agent tmp){

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = "INSERT INTO agents(id, name) VALUES( "+ tmp.id + ", '" + tmp.name + "')";
			System.out.println(stm);
			pst = con.prepareStatement(stm);
			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Insert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Insert.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}

	public void insertCustomer(customer tmp){

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = "INSERT INTO customers(id, name) VALUES( "+ tmp.id + ", '" + tmp.name + "')";
			System.out.println(stm);
			pst = con.prepareStatement(stm);
			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Insert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Insert.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}


	public void insertQuota(quota tmp){

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = "INSERT INTO quotas(count, tour, agent) VALUES( "+ tmp.count + ", " + tmp.tour + ", " + tmp.agent + ")";
			System.out.println(stm);
			pst = con.prepareStatement(stm);
			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Insert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Insert.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}

	public void insertShip(ship tmp){

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = "INSERT INTO ships(id, name, seats) VALUES( "+ tmp.id + ", '" + tmp.name + "', " + tmp.seats + ")";
			System.out.println(stm);
			pst = con.prepareStatement(stm);
			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Insert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Insert.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}


	public void insertTour(tour tmp){

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm = "INSERT INTO tours(id, name, date, time, ship) VALUES( "+ tmp.id + ", '" + tmp.name + "', '" + tmp.date+"', '" + tmp.time+"', " + tmp.id + ")";
			System.out.println(stm);
			pst = con.prepareStatement(stm);
			pst.executeUpdate();

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Insert.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Insert.class.getName());
				lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
	}



}
