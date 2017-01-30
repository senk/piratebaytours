package datenbank;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import api_client.*;

public class Select {

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String url = "jdbc:sqlite:db.sqlite3";


	public Select() {

	}

	public String[] selectRouteNameFromTour(int plaetze_vorhanden, int agentNr) {

		try {

			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("SELECT tours.name " +
					"FROM tours " +
					"JOIN quotas on tours.id = quotas.tour " +
					"WHERE Agent = " + agentNr + 
					" AND count > " + plaetze_vorhanden + 
					" GROUP BY name"  // WHERE reservations > " + (plaetze_vorhanden - 1)
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

	public String[] selectRouteDatumFromTour(int plaetze_vorhanden, String routenName, int agentNr) {
	
		try {
	
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("SELECT date FROM tours " +
					"JOIN quotas on tours.id = quotas.tour " +
					"WHERE name = '" + routenName + "'" +
					" AND count > " + plaetze_vorhanden + 
					" AND Agent = " + agentNr +
					" GROUP BY date");
			rs = pst.executeQuery();
	
			ArrayList<String> select = new ArrayList<String>();
			
			while (rs.next()) {
				select.add(rs.getString(1));
			}
			
			String[] ausgabe = new String[select.size()+1];
			
			for(int i=0; i<select.size(); i++) {
				String[] splitter = select.get((i)).split("-");
				ausgabe[i] = splitter[2] + "." + splitter[1] + "." +  splitter[0];
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

	public String[] selectRouteZeitFromTour(int plaetze_vorhanden, String routenName, String datum, int agentNr) {
	
		try {
	
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("SELECT time " +
					"FROM tours " +
					"JOIN quotas on tours.id = quotas.tour " +
					"WHERE name = '" + routenName + "'" +
					" AND date = '" + datum + "'" + 
					" AND Agent = " + agentNr +
					" AND count > " + plaetze_vorhanden + 
					" GROUP BY time");
			rs = pst.executeQuery();
	
			ArrayList<String> select = new ArrayList<String>();
			
			while (rs.next()) {
				select.add(rs.getString(1));
			}
			
			String[] ausgabe = new String[select.size()+1];
			
			for(int i=0; i<select.size(); i++) {
				String[] splitter = select.get((i)).split(":");
				ausgabe[i] = splitter[0] + ":" + splitter[1];
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

	public String[] selectRouteSchiffFromShip(int plaetze_vorhanden, String routenName, String datum, String time, int agentNr) {
	
		try {
	
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("Select ships.name, ships.id " +
					"FROM tours " +
					"JOIN quotas on tours.id = quotas.tour " +
					"JOIN ships ON tours.ship = ships.id " +
					"WHERE tours.name = '" + routenName + "' " +
					" AND tours.date = '" + datum + "'" +
					" AND count > " + plaetze_vorhanden + 
					" AND Agent = " + agentNr +
					" AND tours.time = '" + time + "'");
			
			rs = pst.executeQuery();
			
			String ausgabe[] = new String[2];
			
			while (rs.next()) {
				ausgabe[0] = rs.getString(1);
				ausgabe[1] = "" + rs.getObject(2);
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

	public int selectIdFromLookButtton() {
	
		try {
	
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("Select id from lookButton");
			rs = pst.executeQuery();
			
			int ausgabe = -1;
			
			while (rs.next()) {
				ausgabe = rs.getInt(1);
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
		return (-1);
	}

	public int selectTourIDfromTour(String tourName, String date, String time, int shipId) {
		
		
	
		try {
			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("Select id from tours " +
					"WHERE name = '" + tourName + "' " +
					"AND date = '" + date + "' " +
					"AND time = '" + time + "' " +
					"AND ship = " + shipId);
			rs = pst.executeQuery();
			
			int ausgabe = -1;
			
			while (rs.next()) {
				ausgabe = rs.getInt(1);
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
		return (-1);
	}

    public ArrayList<customer> selectOfflineCustomersWOReservations() {
        try {
            con = DriverManager.getConnection(url);
            pst = con.prepareStatement("Select name,id from offline_customers");
            rs = pst.executeQuery();
			
            ArrayList<customer> tmp_customer_list = new ArrayList<customer>();
            while(rs.next()){
                customer tmp_customer = new customer();

                tmp_customer.id = rs.getInt(1);
                tmp_customer.name = rs.getString(1);
                tmp_customer_list.add(tmp_customer);

            }
            return tmp_customer_list;
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
