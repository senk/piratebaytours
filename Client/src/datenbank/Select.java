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

	private String url = "jdbc:postgresql://localhost:5432/piratebaytours";
	private String user = "postgres";
	private String password = "1q1q1q1q";

	public Select() {

	}

	public String[] selectRoutenNameFromRouten(int[] idRoute) {

		try {

			ArrayList<String> routenNamen = new ArrayList<String>();

			for (int idRouteSchleife : idRoute) {
				con = DriverManager.getConnection(url, user, password);
				pst = con.prepareStatement("SELECT route_name FROM routen WHERE route_id = " + idRouteSchleife,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pst.executeQuery();
				rs.next();
				routenNamen.add(rs.getString(1));
			}

			return routenNamen.toArray(new String[0]);

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

	public Float[] selectFloatFromRouten(int[] idRoute) {

		try {

			ArrayList<Float> routenNamen = new ArrayList<Float>();

			for (int idRouteSchleife : idRoute) {
				con = DriverManager.getConnection(url, user, password);
				pst = con.prepareStatement("SELECT route_dauer FROM routen WHERE route_id = " + idRouteSchleife,
						ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				rs = pst.executeQuery();
				rs.next();
				routenNamen.add(rs.getFloat(1));
			}

			return routenNamen.toArray(new Float[0]);

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

	public int selectSchiffIdFromTourWHERE(int routenId, String tourDatum, String tourStartzeit) {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(
					"Select schiff_id FROM tour WHERE route_id = " + routenId + " and tour_datum = '" + tourDatum
							+ "' and tour_startzeit = '" + tourStartzeit + "'",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			int ausgabe = 0;

			rs.beforeFirst();

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
		return 0;
	}

	public String selectSchiffNameFromSchiffWHERE(int schiffId) {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("Select schiff_name FROM schiff WHERE schiff_id = " + schiffId,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			String ausgabe = "";

			rs.beforeFirst();

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

	public int selectSumPlaetzeFromBuchungenWHERE(int routenId, String tourDatum, String tourStartzeit) {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(
					"Select SUM(plaetze) FROM buchungen WHERE route_id = " + routenId + " and tour_datum = '"
							+ tourDatum + "' and tour_startzeit = '" + tourStartzeit + "'",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			int ausgabe = 0;

			rs.beforeFirst();

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
		return 0;
	}

	public int[] selectRouteIdFromTour(int plaetze_vorhanden) {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT route_id FROM tour WHERE plaetze_vorhanden > " + (plaetze_vorhanden - 1)
					+ " GROUP BY route_id", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			int[] ausgabe = new int[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getInt(1);
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

	public String[] selectRouteDatumFromTour(int plaetze_vorhanden, int routenId) {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(
					"SELECT tour_datum FROM tour WHERE plaetze_vorhanden > " + (plaetze_vorhanden - 1)
							+ " and route_id = " + routenId + " GROUP BY tour_datum",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			String[] ausgabe = new String[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getString(1);
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

	public String[] selectRoutenStartzeitFromTour(int plaetze_vorhanden, int routen_id, String tour_datum) {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(
					"SELECT tour_startzeit FROM tour WHERE plaetze_vorhanden > " + (plaetze_vorhanden - 1)
							+ " and route_id = " + routen_id + " and tour_datum = '" + tour_datum
							+ "' GROUP BY tour_startzeit",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			String[] ausgabe = new String[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getString(1);
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

	public int selectPlaetzeFromTour(int routenId, String tourDatum, String tourStartzeit) {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(
					"SELECT plaetze_vorhanden FROM tour WHERE route_id = " + routenId + " and tour_datum = '"
							+ tourDatum + "' and tour_startzeit = '" + tourStartzeit + "'",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			int ausgabe = 0;

			rs.beforeFirst();

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
		return 0;
	}

	public String[] selectRoutenNameFromRoute() {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT route_name FROM routen", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			String[] ausgabe = new String[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getString(1);
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

	public int[] selectRoutenIdFromRoute() {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT route_id FROM routen", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			int[] ausgabe = new int[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getInt(1);
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

	public Timestamp[] selectRoutenDauerFromRoute() {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT route_dauer FROM routen", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			Timestamp[] ausgabe = new Timestamp[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getTimestamp(1);
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

	public String[] selectSchiffNameFromSchiff() {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT schiff_name FROM schiff", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();

			rs.last();
			String[] ausgabe = new String[rs.getRow()];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i++] = rs.getString(1);
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

	public String[][] selectAllesFromTour() {

		try {

			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement(
					"Select route_name, schiff_name, tour_datum, tour_startzeit, plaetze_vorhanden from tour "
					+ "JOIN routen ON tour.route_id = routen.route_id "
					+ "JOIN schiff ON tour.schiff_id = schiff.schiff_id"
					+ " ORDER BY tour_datum, tour_startzeit",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
			
			SimpleDateFormat sdfDatum = new SimpleDateFormat("d.MM.yyyy");
			SimpleDateFormat sdfZeit = new SimpleDateFormat("H:m");

			rs.last();
			String[][] ausgabe = new String[rs.getRow()][5];

			rs.beforeFirst();

			int i = 0;
			while (rs.next()) {
				ausgabe[i] [0] = rs.getString(1);
				ausgabe[i] [2] = rs.getString(2);
				ausgabe[i] [1] = sdfDatum.format(rs.getDate(3));
				ausgabe[i] [3] = sdfZeit.format(rs.getTimestamp(4));
				ausgabe[i] [4] = "" + rs.getInt(5);
				
				i++;
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

	public ArrayList<Integer> selectSchiffIdFromTour() {
	
		try {
	
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("Select schiff_id FROM tour",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
	
			rs.last();
			ArrayList<Integer> ausgabe = new ArrayList<Integer>();
	
			rs.beforeFirst();
	
			while (rs.next()) {
				ausgabe.add(rs.getInt(1));
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

	public int selectSchiffIDFromSchiff(String schiffName) {
	
		try {
	
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT schiff_id FROM schiff WHERE schiff_name = '" + schiffName + "'"
					, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
	
			rs.last();
			int ausgabe = 0;
	
			rs.beforeFirst();
	
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
		return 0;
	}

	/**
	 * Liefert eine Stringliste, die alle Datums enth�lt, in denen die �bergebenne
	 * schiff_id enthalten ist.
	 * @param schiff_id
	 * @return
	 */
	public ArrayList<String> selectTourDatumFromTour(int schiff_id) {
	
		try {
	
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("Select tour_datum FROM tour WHERE schiff_id = " + schiff_id,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
	
			ArrayList<String> ausgabe = new ArrayList<String>();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d" );
			
			while (rs.next()) {
				ausgabe.add(sdf.format(rs.getDate(1)));
				System.out.println(rs.getDate(1));
				
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

	public Timestamp selectRoutenDauer() {
	
		try {
	
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("SELECT route_dauer FROM routen", ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
	
			Timestamp ausgabe = new Timestamp(0);
	
	
			while (rs.next()) {
				ausgabe = rs.getTimestamp(1);
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

	public Timestamp selectRouteStartzeit(int schiff_id, String tour_datum) {
	
		try {
	
			con = DriverManager.getConnection(url, user, password);
			pst = con.prepareStatement("select tour_startzeit, routen.route_dauer FROM tour "
					+ "JOIN routen ON tour.route_id = routen.route_id "
					+ "WHERE schiff_id = " + schiff_id + " and tour_datum = '" + tour_datum +  "'",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = pst.executeQuery();
	
			Timestamp ausgabe = new Timestamp(0);
	
	
			while (rs.next()) {
				ausgabe = rs.getTimestamp(1);
				
				System.out.println(rs.getTimestamp(1));
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