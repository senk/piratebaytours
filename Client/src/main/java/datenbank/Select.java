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
			pst = con.prepareStatement("SELECT * FROM api_tour "  // WHERE reservations > " + (plaetze_vorhanden - 1)
					);
			rs = pst.executeQuery();

			ArrayList<String> select = new ArrayList<String>();

			while (rs.next()) {
				select.add(rs.getString(1));
			}
			
			String[] ausgabe = new String[select.size()];
			
			for(int i=0; i<select.size(); i++) {
				ausgabe[i] = select.get((i+1));
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