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

			pst = con.prepareStatement(stm_agents);
			pst.executeUpdate();

			pst = con.prepareStatement(stm_customers);
			pst.executeUpdate();

			pst = con.prepareStatement(stm_ships);
			pst.executeUpdate();

			pst = con.prepareStatement(stm_tours);
			pst.executeUpdate();

			pst = con.prepareStatement(stm_quotas);
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