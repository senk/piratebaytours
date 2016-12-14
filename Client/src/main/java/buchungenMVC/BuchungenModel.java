package buchungenMVC;

import java.util.Observable;
import valueObjecte.Routen;
import valueObjecte.Schiff;
import valueObjecte.Tour;

public class BuchungenModel extends Observable{
	
	private Routen routenVO = new Routen();
	private Tour tourVO = new Tour();
	private Schiff schiffVO = new Schiff();
	
	private int plaetze;
	private int routeId;
	private String datum;
	private String uhrzeit;
	private int schiffId;
	private String schiffName;
	private String routeName;
	
	// Getter und Setter
	
	
	public String getRouteName() {
		return routeName;
	}
	public Routen getRoutenVO() {
		return routenVO;
	}
	public void setRoutenVO(Routen routenVO) {
		this.routenVO = routenVO;
	}
	public Tour getTourVO() {
		return tourVO;
	}
	public void setTourVO(Tour tourVO) {
		this.tourVO = tourVO;
	}
	public Schiff getSchiffVO() {
		return schiffVO;
	}
	public void setSchiffVO(Schiff schiffVO) {
		this.schiffVO = schiffVO;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public int getPlaetze() {
		return plaetze;
	}
	public void setPlaetze(int plaetze) {
		this.plaetze = plaetze;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
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
	public String getSchiffName() {
		return schiffName;
	}
	public void setSchiffName(String schiffName) {
		this.schiffName = schiffName;
	}
	
	
}