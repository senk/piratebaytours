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
public class Create {

	private Connection con = null;
	private PreparedStatement pst = null;

	private String url = "jdbc:sqlite:" + "db.sqlite3";
	private String user = "postgres";
	private String password = "1q1q1q1q";

	public Create() {
	}

	public void CreatePlainDB() {

		try {
			con = DriverManager.getConnection(url, user, password);

			String stm_agents = 		"CREATE TABLE IF NOT EXISTS agents(id int,name varchar(255));";
			String stm_customers = 		"CREATE TABLE IF NOT EXISTS customers(id int,name varchar(255));";
			String stm_ships = 			"CREATE TABLE IF NOT EXISTS ships(id int,name varchar(255), seats int);";
			String stm_tours =	 		"CREATE TABLE IF NOT EXISTS tours(id int,name varchar(255), date varchar(255), time varchar(255), ship int,FOREIGN KEY(ship) REFERENCES ships(id));";
			String stm_quotas = 		"CREATE TABLE IF NOT EXISTS quotas(count int,tour int, agent int, FOREIGN KEY(tour) REFERENCES tours(id), FOREIGN KEY(agent) REFERENCES agents(id));";
			String stm_reservations = 	"CREATE TABLE IF NOT EXISTS reservations(id int,count int, tour int, customer int ,  FOREIGN KEY(tour) REFERENCES tours(id), FOREIGN KEY(customer) REFERENCES customers(id));";

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