package datenbank;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Select {

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String url = "jdbc:sqlite:" + System.getProperty("user.home") + "/piratebaytours/" + "db.sqlite3";


	public Select() {

	}

	public String[] selectRouteNameFromTour(int plaetze_vorhanden) {

		try {

			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("SELECT name FROM api_tour GROUP BY name"  // WHERE reservations > " + (plaetze_vorhanden - 1)
					);
			rs = pst.executeQuery();

			ArrayList<String> select = new ArrayList<String>();
			
			while (rs.next()) {
				select.add(rs.getString(1));
			}
			
			String[] ausgabe = new String[select.size()+1];
			
			for(int i=0; i<select.size(); i++) {
				ausgabe[i] = select.get((i));
			}
			
			return ausgabe;

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Select.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Select.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return null;
	}

	public String[] selectRouteDatumFromTour(int plaetze_vorhanden, String routenName) {
	
		try {
	
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("SELECT date FROM api_tour WHERE name = '" + routenName + "' GROUP BY date");
			rs = pst.executeQuery();
	
			ArrayList<String> select = new ArrayList<String>();
			
			while (rs.next()) {
				select.add(rs.getString(1));
			}
			
			String[] ausgabe = new String[select.size()+1];
			
			for(int i=0; i<select.size(); i++) {
				ausgabe[i] = select.get((i));
			}
			
			return ausgabe;
	
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Select.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
	
		} finally {
	
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
	
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Select.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return null;
	}

	public String[] selectRouteZeitFromTour(int plaetze_vorhanden, String routenName, String datum) {
	
		try {
	
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("SELECT time FROM api_tour WHERE name = '" +
					routenName + "' and date = '" + datum + "' GROUP BY time");
			rs = pst.executeQuery();
	
			ArrayList<String> select = new ArrayList<String>();
			
			while (rs.next()) {
				select.add(rs.getString(1));
			}
			
			String[] ausgabe = new String[select.size()+1];
			
			for(int i=0; i<select.size(); i++) {
				ausgabe[i] = select.get((i));
			}
			
			return ausgabe;
	
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Select.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
	
		} finally {
	
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
	
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Select.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return null;
	}

	public String selectRouteSchiffFromShip(int plaetze_vorhanden, String routenName, String datum, String time) {
	
		try {
	
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("Select api_ship.name from api_tour JOIN api_ship " +
					"ON api_tour.ship_id = api_ship.id WHERE api_tour.name = '" +
					routenName + "' and api_tour.date = '" + datum + "' and api_tour.time = '" + time + "'");
			rs = pst.executeQuery();
			
			String ausgabe = "";
			
			System.out.println();
			
			while (rs.next()) {
				ausgabe = rs.getString(1);
			}
			
			return ausgabe;
	
		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(Select.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);
	
		} finally {
	
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				if (con != null) {
					con.close();
				}
	
			} catch (SQLException ex) {
				Logger lgr = Logger.getLogger(Select.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
		return null;
	}

	

}