package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hier werden die Datens�tze in die Datenbanken hinzugef�gt.
 * 
 * @author AgentFriz
 *
 */
public class Insert {

	private Connection con = null;
	private PreparedStatement pst = null;

	private String url = "jdbc:postgresql://localhost:5432/piratebaytours";
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
}
