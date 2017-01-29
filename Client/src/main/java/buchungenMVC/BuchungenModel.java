package buchungenMVC;

import java.util.Observable;
import valueObjecte.Routen;
import valueObjecte.Schiff;
import valueObjecte.Tour;

public class BuchungenModel extends Observable{
	
	private int plaetze;
	private String datum;
	private String uhrzeit;
	private int schiffId;
	private String routeName;
	private int tourId;
	private int AgentNr;
	
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
	public int getSchiffId() {
		return schiffId;
	}
	public void setSchiffId(int schiffId) {
		this.schiffId = schiffId;
	}
	public int getTourId() {
		return tourId;
	}
	public void setTourId(int tourId) {
		this.tourId = tourId;
	}
	public int getAgentNr() {
		return AgentNr;
	}
	public void setAgentNr(int agentNr) {
		AgentNr = agentNr;
	}
	
	
}