package api_client;

import java.util.ArrayList;
import java.util.List;

public class tour{
	public int id;
	public String name;
	public String date;
	public String time;
	public int ship_id;
	
	public List<Integer> reservations = new ArrayList<Integer>();
	public List<Integer> quotas = new ArrayList<Integer>();


	public tour(){}

	public void addReservation(Integer x) {
		this.reservations.add(x);
	};	

	public void addQuota(Integer x) {
		this.quotas.add(x);
	};	

}