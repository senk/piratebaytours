package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Update {

	 Connection con = null;
     Statement st = null;

	private String  url = "jdbc:sqlite:db.sqlite3";

	public Update() {
		// TODO Auto-generated constructor stub
	}

	public void UpdateTourSetplaetze(int plaetze, int routen_Id, String tour_datum, String tour_startzeit) {
		try {

			con = DriverManager.getConnection(url);
            st = con.createStatement();

            con.setAutoCommit(false);
            
            st.executeUpdate("Update tour SET plaetze_vorhanden = " + plaetze + 
					" WHERE route_id = " + routen_Id + " and tour_datum = '" + tour_datum +  
					"' and  tour_startzeit = '" + tour_startzeit + "'");

            con.commit();

        } catch (SQLException ex) {

            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex1) {
                    Logger lgr = Logger.getLogger(Update.class.getName());
                    lgr.log(Level.WARNING, ex1.getMessage(), ex1);
                }
            }

            Logger lgr = Logger.getLogger(Update.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
            
        } finally {

            try {
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {

                Logger lgr = Logger.getLogger(Update.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }
	}

	public void UpdatelookButton(int look) {
		try {
	
			con = DriverManager.getConnection(url);
	        st = con.createStatement();
	
	        con.setAutoCommit(false);
	        
	        st.executeUpdate("Update lookButton SET id = " + look);
	
	        con.commit();
	
	    } catch (SQLException ex) {
	
	        if (con != null) {
	            try {
	                con.rollback();
	            } catch (SQLException ex1) {
	                Logger lgr = Logger.getLogger(Update.class.getName());
	                lgr.log(Level.WARNING, ex1.getMessage(), ex1);
	            }
	        }
	
	        Logger lgr = Logger.getLogger(Update.class.getName());
	        lgr.log(Level.SEVERE, ex.getMessage(), ex);
	        
	    } finally {
	
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	
	        } catch (SQLException ex) {
	
	            Logger lgr = Logger.getLogger(Update.class.getName());
	            lgr.log(Level.WARNING, ex.getMessage(), ex);
	        }
	    }
	}
}
