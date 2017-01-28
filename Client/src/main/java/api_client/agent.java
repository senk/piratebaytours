package api_client;

import java.util.ArrayList;
import java.util.List;

public class agent{
	public int id;
	public String name;
	

	public List<Integer> quotas = new ArrayList<Integer>();

	public agent(){}
	public void addQuota(Integer x) {
		this.quotas.add(x);
	};	
}