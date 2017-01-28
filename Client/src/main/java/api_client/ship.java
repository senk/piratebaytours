package api_client;

import java.util.ArrayList;
import java.util.List;

public class ship{
	public int id;
	public String name;
	public int seats;

	public List<Integer> tours = new ArrayList<Integer>();

	public ship(){}

	public void addTour(Integer x) {
		this.tours.add(x);
	};	
}