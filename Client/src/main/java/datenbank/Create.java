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
	private Boolean debug = true;
	private Connection con = null;
	private PreparedStatement pst = null;

	private String url  = "jdbc:sqlite:" + System.getProperty("user.home") + "/piratebaytours/" + "db.sqlite3-journal";

	public Create() {
	}

	public void CreatePlainDB() {

		try {
			con = DriverManager.getConnection(url);

			//Create offline cache tables
			String stm_agents = 		"CREATE TABLE IF NOT EXISTS agents(id int,name varchar(255));";
			String stm_customers = 		"CREATE TABLE IF NOT EXISTS customers(id int,name varchar(255));";
			String stm_ships = 			"CREATE TABLE IF NOT EXISTS ships(id int,name varchar(255), seats int);";
			String stm_tours =	 		"CREATE TABLE IF NOT EXISTS tours(id int,name varchar(255), date varchar(255), time varchar(255), ship int,FOREIGN KEY(ship) REFERENCES ships(id));";
			String stm_quotas = 		"CREATE TABLE IF NOT EXISTS quotas(count int,tour int, agent int, FOREIGN KEY(tour) REFERENCES tours(id), FOREIGN KEY(agent) REFERENCES agents(id));";
			String stm_reservations = 	"CREATE TABLE IF NOT EXISTS reservations(id int,count int, tour int, customer int ,  FOREIGN KEY(tour) REFERENCES tours(id), FOREIGN KEY(customer) REFERENCES customers(id));";
			String stm_lookButton = 	"CREATE TABLE IF NOT EXISTS lookButton(id int);";
			String stm_insertValue = 		"INSERT INTO lookButton VALUES(0);";

			//Create Tables for temporary storage
			String stm_offline_bookings = 		"CREATE TABLE IF NOT EXISTS offline_bookings(count int, tour int, customer int, FOREIGN KEY(tour) REFERENCES tours(id), FOREIGN KEY(customer) REFERENCES offline_customers(id));";
			String stm_offline_customers = 		"CREATE TABLE IF NOT EXISTS offline_customers(id INTEGER PRIMARY KEY AUTOINCREMENT, name varchar(255), remote_id int);";


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
			
			pst = con.prepareStatement(stm_lookButton);
			pst.executeUpdate();
			if (debug) System.out.println(stm_lookButton);
			
			pst = con.prepareStatement(stm_insertValue);
			pst.executeUpdate();
			if (debug) System.out.println(stm_insertValue);

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