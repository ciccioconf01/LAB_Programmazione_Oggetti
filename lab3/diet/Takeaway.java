package diet;

import java.util.*;


/**
 * Represents a takeaway restaurant chain.
 * It allows managing restaurants, customers, and orders.
 */
public class Takeaway {

	Food f;
	SortedMap<String,Restaurant> dizRes = new TreeMap<>();
	List<Customer> listaClienti = new ArrayList<>();
	List<Order> listaOrdini = new ArrayList<>();
	
	public Takeaway(Food food){
		f = food;
	}

	/**
	 * Creates a new restaurant with a given name
	 *
	 * @param restaurantName name of the restaurant
	 * @return the new restaurant
	 */
	public Restaurant addRestaurant(String restaurantName) {
		Restaurant r = new Restaurant(restaurantName,listaOrdini);
		dizRes.put(restaurantName, r);
		return r;
	}

	public Collection<String> restaurants() {
		return dizRes.keySet();
	}

	
	public Customer registerCustomer(String firstName, String lastName, String email, String phoneNumber) {
		Customer c = new Customer(firstName,lastName);
		c.SetEmail(email);
		c.setPhone(phoneNumber);
		listaClienti.add(c);
		return c;
	}

	
	public Collection<Customer> customers(){
		
		Comparator<Customer> bySurname = Comparator.comparing(Customer::getLastName);
		Comparator<Customer> byName = Comparator.comparing(Customer::getFirstName);
		 
		Collections.sort(listaClienti, bySurname.thenComparing(byName));
		return listaClienti;
	}


	
	public Order createOrder(Customer customer, String restaurantName, String time) {
		Order o = new Order(customer,restaurantName,time,dizRes.get(restaurantName).orari);
		listaOrdini.add(o);
		
		return o;
	}

	/**
	 * Find all restaurants that are open at a given time.
	 *
	 * @param time the time with format {@code "HH:MM"}
	 * @return the sorted collection of restaurants
	 */
	public Collection<Restaurant> openRestaurants(String time){
		int i=0;
		String ricorda="";
		List<Restaurant> listaAperti= new ArrayList<>();
		
		for (String key : dizRes.keySet()) {
			for (String orario : dizRes.get(key).orari) {
				if(i%2!=0) {
					if(ricorda.compareTo(time)<=0 && orario.compareTo(time)>=0) {
						listaAperti.add(dizRes.get(key));
					}
					i+=1;
				}
				else {
					ricorda=orario;
					i+=1;
				}
			}
		}
		return listaAperti;
	}
}
