package adminMVC;

import java.util.Observable;
import valueObjecte.Routen;
import valueObjecte.Schiff;
import valueObjecte.Tour;

public class AdminModel extends Observable{
	
	private Routen routenVO = new Routen();
	private Tour tourVO = new Tour();
	private Schiff schiffVO = new Schiff();
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
	
	
}