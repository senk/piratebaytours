package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Hier werden die Datensätze in die Datenbanken hinzugefügt.
 * 
 * @author Jonas Droste
 *
 */
public class Drop {
	private Boolean debug = true;
	private Connection con = null;
	private PreparedStatement pst = null;

	private String url = "jdbc:sqlite:" + "db.sqlite3";
	private String user = "postgres";
	private String password = "1q1q1q1q";

	public Drop() {
	}

	public void DropDB() {

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm_agents = 		"DROP TABLE IF EXISTS agents;";
			String stm_customers = 		"DROP TABLE IF EXISTS customers;";
			String stm_ships = 			"DROP TABLE IF EXISTS ships;";
			String stm_tours =	 		"DROP TABLE IF EXISTS tours;";
			String stm_quotas = 		"DROP TABLE IF EXISTS quotas;";
			String stm_reservations = 	"DROP TABLE IF EXISTS reservations;";

			String stm_offline_bookings = 	"DROP TABLE IF EXISTS offline_bookings;";
			String stm_offline_customers = 	"DROP TABLE IF EXISTS offline_customers;";




			pst = con.prepareStatement(stm_agents);
			pst.executeUpdate();
			if (debug) System.out.println(stm_agents);

			pst = con.prepareStatement(stm_customers);
			pst.executeUpdate();
			if (debug) System.out.println(stm_customers);

			pst = con.prepareStatement(stm_ships);
			pst.executeUpdate();
			if (debug) System.out.println(stm_ships);

			pst = con.prepareStatement(stm_tours);
			pst.executeUpdate();
			if (debug) System.out.println(stm_tours);

			pst = con.prepareStatement(stm_quotas);
			pst.executeUpdate();
			if (debug) System.out.println(stm_quotas);

			pst = con.prepareStatement(stm_offline_bookings);
			pst.executeUpdate();
			if (debug) System.out.println(stm_offline_bookings);

			pst = con.prepareStatement(stm_offline_customers);
			pst.executeUpdate();
			if (debug) System.out.println(stm_offline_customers);



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