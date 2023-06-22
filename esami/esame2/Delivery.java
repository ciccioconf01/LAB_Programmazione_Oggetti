package delivery;

import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Delivery {
	// R1
	
    List <Categoria> categorie = new ArrayList <>();
    List <Ristorante> ristoranti = new ArrayList<>();
    List <Ordine> ordini = new ArrayList<>();
    
	public void addCategory (String category) throws DeliveryException {
		Categoria cat = new Categoria(category);
		boolean trovato = false;
		for (Categoria elem : categorie) {
			if (elem.GetCategoria().compareTo(category)==0) {
				trovato = true;
			}
		}
		
		if (trovato == true) {
			throw new DeliveryException();
		}
		
		else {
			categorie.add(cat);
			return;
		}
		
	}
	
	
	public List<String> getCategories() {
		List<String> lista = new ArrayList <>();
		
		for (Categoria elem : categorie) {
			lista.add(elem.GetCategoria());
		}
		
		return lista;
		
	}
	
	
	public void addRestaurant (String name, String category) throws DeliveryException {
		boolean trovato = false;
		for (Categoria elem : categorie) {
			if (elem.GetCategoria().compareTo(category)==0) {
				trovato = true;
			}
		}
		
		if (trovato == false) {
			throw new DeliveryException();
		}
		else {
			Ristorante ris = new Ristorante(name,category);
			ristoranti.add(ris);
		}
		
	}
	
	public List<String> getRestaurantsForCategory(String category) {
		List <String> lista = new ArrayList<>();
		
        for (Ristorante ris : ristoranti) {
        	if (ris.GetCategoria().compareTo(category)==0) {
        		lista.add(ris.GetNome());
        	}
        }
        
        if (lista.size()==0) {
        	return lista;
        }
        else {
        	lista.sort(
        			(l1,l2)->{
        				return l1.compareTo(l2);
        			});
        	
        	return lista;
        }
        
	}
	
	// R2
	
	
	public void addDish(String name, String restaurantName, float price) throws DeliveryException {
		
		for (Ristorante ris : ristoranti) {
			if (ris.GetNome().compareTo(restaurantName)==0) {
				ris.addPiatto(name,price);
			}
		}
	}
	
	
	public Map<String,List<String>> getDishesByPrice(float minPrice, float maxPrice) {
		List<Piatto> piatti = new ArrayList<>();
		Map<String,List<String>> mappa = new TreeMap<>();
		
        for (Ristorante ris : ristoranti) {
        	piatti = ris.GetPiatti();
        	List<String> compresi = new ArrayList<>();
        	for (Piatto elem : piatti) {
        		if (elem.GetPrezzo() >= minPrice && elem.GetPrezzo()<=maxPrice) {
        			compresi.add(elem.GetNome());
        		}
        	}
        	
        	if (compresi.size()!=0) {
        		mappa.put(ris.GetNome(), compresi);
        	}
        }
        
        return mappa;
	}
	
	
	public List<String> getDishesForRestaurant(String restaurantName) {
		
		List<String> lista = new ArrayList <>();
		List<Piatto> piatti = new ArrayList<>();
		
		for(Ristorante ris : ristoranti) {
			if (ris.GetNome().compareTo(restaurantName)==0) {
				piatti = ris.GetPiatti();
				
				for(Piatto elem:piatti) {
					lista.add(elem.GetNome());
				}
			}
		}
		
		if (lista.size()==0) {
			return lista;
		}
		
		else {
			lista.sort(
					(l1,l2) -> {
						return l1.compareTo(l2);
					}
							);
			
			return lista;
		}
	}
	
	
	public List<String> getDishesByCategory(String category) {
		List <Piatto> piatti = null;
		List <String> lista = new ArrayList<>();
		
        for (Ristorante ris : ristoranti) {
        	if(ris.GetCategoria().compareTo(category)==0) {
        		piatti = ris.GetPiatti();
        		
        		for (Piatto elem : piatti) {
        			lista.add(elem.GetNome());
        		}
        	}
        }
        
        return lista;
	}
	
	//R3
	
	
	public int addOrder(String dishNames[], int quantities[], String customerName, String restaurantName, int deliveryTime, int deliveryDistance) {
	    Ordine o = new Ordine(dishNames,quantities,customerName,restaurantName,deliveryTime,deliveryDistance);
	    ordini.add(o);
	    return ordini.size();
	}
	
	public List<Integer> scheduleDelivery(int deliveryTime, int maxDistance, int maxOrders) {
        List<Integer> lista = new ArrayList<>();
        int conta = 1;
        for (Ordine o : ordini) {
        	
        	if (o.GetDistanza()<=maxDistance && o.GetTempoConsegna()==deliveryTime && o.GetElaborato()==false) {
        		lista.add(conta);
        		o.SetElaborato(true);
        	}
        	if (conta == maxOrders) {
        		break;
        	}
        	conta++;
        }
        
        return lista;
	}
	
	
	public int getPendingOrders() {
		int conta = 0;
        for (Ordine o : ordini) {
        	if (o.GetElaborato()==false) {
        		conta++;
        	}
        }
        
        return conta;
	}
	
	// R4
	
	public void setRatingForRestaurant(String restaurantName, int rating) {
		
		for (Ristorante ris : ristoranti) {
			if (ris.GetNome().compareTo(restaurantName)==0) {
				ris.addValutazione(rating);
			}
		}
	}
	
	/**
	 * retrieves the ordered list of restaurant. 
	 * 
	 * The restaurant must be ordered by decreasing average rating. 
	 * The average rating of a restaurant is the sum of all rating divided by the number of ratings.
	 * 
	 * @return ordered list of restaurant names
	 */
	public List<String> restaurantsAverageRating() {
        Map <String,Float> mappa = new TreeMap <>();
        List <Integer> valutazioni = null;
        float n=0,acc=0,media=0;
        
        for (Ristorante ris : ristoranti) {
        	valutazioni = ris.GetValutazioni();
        	
        	n = valutazioni.size();
        	for(Integer elem : valutazioni) {
        		acc=acc+elem;
        	}
        	
        	if (n!=0) {
        		media = acc/n;
        		mappa.put(ris.GetNome(), media);
        	}
        	acc=0;
        	
        }
        
        
        SortedMap <String,Float> sortedMap = new TreeMap<>(
        		(s1,s2) -> {
        			float m1 = mappa.get(s1);
        			float m2 = mappa.get(s2);
        			return Float.compare(m2, m1);
        		});
        
        
        sortedMap.putAll(mappa);
        
        System.out.println(mappa);
        System.out.println(sortedMap);
        
        List<String> finito = new ArrayList<>();
        for(String elem : sortedMap.keySet()) {
        	finito.add(elem);
        }
        
        return finito;
	}
	
	//R5
	
	public Map<String,Long> ordersPerCategory() {
        Map <String,Long> mappa = new TreeMap<>();
        int conta=0;
        for(Categoria cat : categorie) {
        	
        	for(Ordine o : ordini) {
        		
        		for(Ristorante r : ristoranti) {
        			if(o.GetNomeRistorante().compareTo(r.GetNome())==0 && cat.GetCategoria().compareTo(r.GetCategoria())==0) {
        				conta++;
        			}
        		}
        	}
        	
        	mappa.put(cat.GetCategoria(), (long) conta);
        	conta=0;
        }
        
        return mappa;
	}
	
	/**
	 * retrieves the name of the restaurant that has received the higher average rating.
	 * 
	 * @return restaurant name
	 */
	public String bestRestaurant() {
		
		Map <String,Float> mappa = new TreeMap <>();
        List <Integer> valutazioni = null;
        float n=0,acc=0,media=0;
        
        for (Ristorante ris : ristoranti) {
        	valutazioni = ris.GetValutazioni();
        	
        	n = valutazioni.size();
        	for(Integer elem : valutazioni) {
        		acc=acc+elem;
        	}
        	
        	if (n!=0) {
        		media = acc/n;
        		mappa.put(ris.GetNome(), media);
        	}
        	acc=0;
        	
        }
        
        SortedMap <String,Float> sortedMap = new TreeMap<>(
        		(s1,s2) -> {
        			float m1 = mappa.get(s1);
        			float m2 = mappa.get(s2);
        			return Float.compare(m2, m1);
        		});
        
        
        sortedMap.putAll(mappa);
        
        String stringa="";
        for(String elem : sortedMap.keySet()) {
        	stringa = elem;
        	break;
        }
        return stringa;
	}
}
