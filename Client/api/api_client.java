
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import org.json.*;



class ship{
	public int id;
	public String name;
	public int seats;

	public List<Integer> tours = new ArrayList<Integer>();

	public ship(){}

	public void addTour(Integer x) {
		this.tours.add(x);
	};	
}

class tour{
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

class quota{
	public int count;
	public int tour;
	public int agent;

	public quota(){}
}

class agent{
	public int id;
	public String name;
	

	public List<Integer> quotas = new ArrayList<Integer>();

	public agent(){}
	public void addQuota(Integer x) {
		this.quotas.add(x);
	};	
}

class customer{
	public int id;
	public String name;
	

	public List<Integer> reservations = new ArrayList<Integer>();

	public customer(){}
	public void addReservation(Integer x) {
		this.reservations.add(x);
	};	
}

class reservation{
	public int id;
	public int count;
	public int tour;
	public int customer;

	public reservation(){}
}





class api{

	private final String USER_AGENT = "Mozilla/5.0";
	private final String API_ROOT = "http://robin-app1.fh-muenster.de/";


	public api(){


	}
	public List<ship> get_ships(){

		List<ship> tmp_ship_list = new ArrayList<ship>();
		try{
			String response = request("ships","");
			JSONArray arr = new JSONArray(response);
			for (int i = 0; i < arr.length(); i++)
			{
				ship tmp_ship = new ship();

				tmp_ship.id = arr.getJSONObject(i).getInt("id");
				tmp_ship.name = arr.getJSONObject(i).getString("name");
				tmp_ship.seats = arr.getJSONObject(i).getInt("seats");

				JSONArray tmp_tours = arr.getJSONObject(i).getJSONArray("tours");

				if (tmp_tours.length()>0) {
					for (int j = 0; j <tmp_tours.length() ;j++ ) {

						//System.out.println(tmp_tours.getInt(j));
						tmp_ship.addTour(tmp_tours.getInt(j));
					}

				}
				tmp_ship_list.add(tmp_ship);

			}
			return tmp_ship_list;
		} catch(Exception e){
			return null;
		}
	}

	public List<tour> get_tours(){

		List<tour> tmp_tour_list = new ArrayList<tour>();
		try{
			String response = request("tours","");
			JSONArray arr = new JSONArray(response);
			for (int i = 0; i < arr.length(); i++)
			{
				tour tmp_tour = new tour();

				tmp_tour.id = arr.getJSONObject(i).getInt("id");
				tmp_tour.name = arr.getJSONObject(i).getString("name");
				tmp_tour.date = arr.getJSONObject(i).getString("date");
				tmp_tour.time = arr.getJSONObject(i).getString("time");
				tmp_tour.ship_id = arr.getJSONObject(i).getInt("ship");


				JSONArray tmp_reservations = arr.getJSONObject(i).getJSONArray("reservations");

				if (tmp_reservations.length()>0) {
					for (int j = 0; j <tmp_reservations.length() ;j++ ) {

						tmp_tour.addReservation(tmp_reservations.getInt(j));
					}

				}

				JSONArray tmp_quotas = arr.getJSONObject(i).getJSONArray("quotas");

				if (tmp_quotas.length()>0) {
					for (int j = 0; j <tmp_quotas.length() ;j++ ) {

						tmp_tour.addQuota(tmp_quotas.getInt(j));
					}

				}

				tmp_tour_list.add(tmp_tour);

			}
			return tmp_tour_list;
		} catch(Exception e){
			return null;
		}
	}

	public List<quota> get_quotas(){

		List<quota> tmp_quota_list = new ArrayList<quota>();
		try{
			String response = request("quotas","");
			JSONArray arr = new JSONArray(response);
			for (int i = 0; i < arr.length(); i++)
			{
				quota tmp_quota = new quota();

				tmp_quota.count = arr.getJSONObject(i).getInt("count");
				tmp_quota.tour = arr.getJSONObject(i).getInt("tour");
				tmp_quota.agent = arr.getJSONObject(i).getInt("agent");


				tmp_quota_list.add(tmp_quota);

			}
			return tmp_quota_list;
		} catch(Exception e){
			return null;
		}
	}


	public List<agent> get_agents(){

		List<agent> tmp_agent_list = new ArrayList<agent>();
		try{
			String response = request("agents","");
			JSONArray arr = new JSONArray(response);
			for (int i = 0; i < arr.length(); i++)
			{
				agent tmp_agent = new agent();

				tmp_agent.id = arr.getJSONObject(i).getInt("id");
				tmp_agent.name = arr.getJSONObject(i).getString("name");


				JSONArray tmp_quotas = arr.getJSONObject(i).getJSONArray("quotas");

				if (tmp_quotas.length()>0) {
					for (int j = 0; j <tmp_quotas.length() ;j++ ) {

						tmp_agent.addQuota(tmp_quotas.getInt(j));
					}

				}

				tmp_agent_list.add(tmp_agent);

			}
			return tmp_agent_list;
		} catch(Exception e){
			return null;
		}
	}

	public List<customer> get_customers(){

		List<customer> tmp_customer_list = new ArrayList<customer>();
		try{
			String response = request("customers","");
			JSONArray arr = new JSONArray(response);
			for (int i = 0; i < arr.length(); i++)
			{
				customer tmp_customer = new customer();

				tmp_customer.id = arr.getJSONObject(i).getInt("id");
				tmp_customer.name = arr.getJSONObject(i).getString("name");


				JSONArray tmp_reservations = arr.getJSONObject(i).getJSONArray("reservations");

				if (tmp_reservations.length()>0) {
					for (int j = 0; j <tmp_reservations.length() ;j++ ) {

						tmp_customer.addReservation(tmp_reservations.getInt(j));
					}

				}

				tmp_customer_list.add(tmp_customer);

			}
			return tmp_customer_list;
		} catch(Exception e){
			return null;
		}
	}


	public List<reservation> get_reservations(){

		List<reservation> tmp_reservation_list = new ArrayList<reservation>();
		try{
			String response = request("reservations","");
			JSONArray arr = new JSONArray(response);
			for (int i = 0; i < arr.length(); i++)
			{
				reservation tmp_reservation = new reservation();

				tmp_reservation.id = arr.getJSONObject(i).getInt("id");
				tmp_reservation.count = arr.getJSONObject(i).getInt("count");
				tmp_reservation.tour = arr.getJSONObject(i).getInt("tour");
				tmp_reservation.customer = arr.getJSONObject(i).getInt("customer");


				tmp_reservation_list.add(tmp_reservation);

			}
			return tmp_reservation_list;
		} catch(Exception e){
			return null;
		}
	}




	private String request(String api_endpoint, String api_filters) throws Exception {

		String url = API_ROOT + api_endpoint + "?format=json&" + api_filters;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
			new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();
	}



}



public class api_client 
{


	public static void main(String[] args) throws Exception {

		api_client client = new api_client();

		api http= new api();

		List<ship> ships=http.get_ships();
		List<tour> tours=http.get_tours();
		List<quota> quotas=http.get_quotas();
		List<agent> agents=http.get_agents();
		List<customer> customers=http.get_customers();
		List<reservation> reservations=http.get_reservations();


		for(ship tmp: ships) System.out.println(tmp.name);
			for(tour tmp: tours) System.out.println(tmp.name);
				for(quota tmp: quotas) System.out.println(tmp.count);
					for(agent tmp: agents) System.out.println(tmp.name);
						for(customer tmp: customers) System.out.println(tmp.name);
							for(reservation tmp: reservations) System.out.println(tmp.count);





			//tmp_ship.id = arr.getJSONObject(i).getString("name");

		    //System.out.println(post_id);

						}

		//http.request("quotas","agent=1");

				}







	// HTTP GET request


