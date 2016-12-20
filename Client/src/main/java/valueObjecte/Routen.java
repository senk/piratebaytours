package valueObjecte;

import java.sql.Timestamp;

public class Routen {
	
	private int[] routen_id;
	private String[] routen_name;
	private Timestamp[] routen_dauer;
	
	public Routen() {
		// TODO Auto-generated constructor stub
	}

	public int[] getRouten_id() {
		return routen_id;
	}

	public void setRouten_id(int[] routen_id) {
		this.routen_id = routen_id;
	}

	public String[] getRouten_name() {
		return routen_name;
	}

	public void setRouten_name(String[] routen_name) {
		this.routen_name = routen_name;
	}

	public Timestamp[] getRouten_dauer() {
		return routen_dauer;
	}

	public void setRouten_dauer(Timestamp[] routen_dauer) {
		this.routen_dauer = routen_dauer;
	}

}
