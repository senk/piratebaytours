package datenbank;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Select {

	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private String url = "jdbc:sqlite:" + System.getProperty("user.home") + "/piratebaytours/Client/datenbank/" + "piratebaytours.db";


	public Select() {

	}

	public String[] selectRouteIdFromTour(int plaetze_vorhanden) {

		try {

			con = DriverManager.getConnection(url);
			pst = con.prepareStatement("SELECT * FROM tour WHERE reservations > " + (plaetze_vorhanden - 1)
					, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			String[] ausgabe = new String[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getString("tourName");
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