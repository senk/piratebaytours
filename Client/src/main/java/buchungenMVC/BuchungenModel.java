package buchungenMVC;

import java.util.Observable;
import valueObjecte.Routen;
import valueObjecte.Schiff;
import valueObjecte.Tour;

public class BuchungenModel extends Observable{
	
	private int plaetze;
	private String datum;
	private String uhrzeit;
	private String schiffName;
	private String routeName;
	
	// Getter und Setter
	
	
	public String getTourName() {
		return routeName;
	}
	public void setTourName(String routeName) {
		this.routeName = routeName;
	}
	public int getPlaetze() {
		return plaetze;
	}
	public void setPlaetze(int plaetze) {
		this.plaetze = plaetze;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getUhrzeit() {
		return uhrzeit;
	}
	public void setUhrzeit(String uhrzeit) {
		this.uhrzeit = uhrzeit;
	}
	public String getSchiffName() {
		return schiffName;
	}
	public void setSchiffName(String schiffName) {
		this.schiffName = schiffName;
	}
	
	
}