package api_client;

import java.util.ArrayList;
import java.util.List;

public class customer{
	public int id;
	public String name;
	

	public List<Integer> reservations = new ArrayList<Integer>();

	public customer(){}
	public void addReservation(Integer x) {
		this.reservations.add(x);
	};	
}