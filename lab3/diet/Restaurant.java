package diet;

import diet.Order.OrderStatus;
import java.util.*;
/**
 * Represents a restaurant class with given opening times and a set of menus.
 */
public class Restaurant {
	
	String nome;
	List<String> orari = new ArrayList<>();
	List<Menu> ListaMenu = new ArrayList<>();
	List<Order> listaOrdini = new ArrayList<>();
	
	public Restaurant(String name,List<Order> lista){
		nome=name;
		
		Comparator<Order> byNameRes= Comparator.comparing(Order::getNomeR);
		Comparator<Order> byNameCli = Comparator.comparing(Order::getNomeC); 
		Comparator<Order> byOrarioC = Comparator.comparing(Order::getTempoArrivo); 
		
		Collections.sort(lista, byNameRes.thenComparing(byNameCli).thenComparing(byOrarioC));
		
		listaOrdini = lista;
	}
	public String getName() {
		return nome;
	}

	
	public void setHours(String ... hm) {
		for (String elem : hm) {
			orari.add(elem);
		}
	}

	
	public boolean isOpenAt(String time){
		int i=0;
		String ricorda="";
		for (String elem : orari) {
			if(i%2 == 1) {
				i=0;
				
				if (ricorda.compareTo(time)<0 && elem.compareTo(time)>0) {
					return true;
				}
			}
			else {
				ricorda=elem;
				i=1;
			}
			
		}
		return false;
	}

	/**
	 * Adds a menu to the list of menus offered by the restaurant
	 *
	 * @param menu	the menu
	 */
	public void addMenu(Menu menu) {
		ListaMenu.add(menu);
	}

	
	public Menu getMenu(String name) {
		for (Menu m : ListaMenu) {
			if (m.getName()==name) {
				return m;
			}
		}
		return null;
		
	}

	
	public String ordersWithStatus(OrderStatus status) {
		String s="";
		
		Comparator<Order> byNameRes= Comparator.comparing(Order::getNomeR);
		Comparator<Order> byNameCli = Comparator.comparing(Order::getNomeC); 
		Comparator<Order> byOrarioC = Comparator.comparing(Order::getTempoArrivo); 
		
		Collections.sort(listaOrdini, byNameRes.thenComparing(byNameCli).thenComparing(byOrarioC));
		
		for (Order o : listaOrdini) {
			if (o.getNomeR()==nome && o.getStatus()==status) {
				s=s+o.toString();
				s=s+"\n";
			}
			
		}
		
		System.out.println(s);
		
		return s;
	}
}
