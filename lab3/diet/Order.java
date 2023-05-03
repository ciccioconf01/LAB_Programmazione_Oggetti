package diet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Represents and order issued by an {@link Customer} for a {@link Restaurant}.
 *
 * When an order is printed to a string is should look like:
 * <pre>
 *  RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
 *  	MENU_NAME_1->MENU_QUANTITY_1
 *  	...
 *  	MENU_NAME_k->MENU_QUANTITY_k
 * </pre>
 */
public class Order {

	Customer cliente;
	String nomeRistorante;
	String tempoArrivo;
	SortedMap<String,Number> dizMenu = new TreeMap<>();
	OrderStatus stato=OrderStatus.ORDERED;
	PaymentMethod metodoPagamento = PaymentMethod.CASH;
	
	public Order (Customer c, String nomeR, String time, List<String> orari) {
		cliente = c;
		nomeRistorante=nomeR;
		String ricordaPrec="";
		int contaCicli=0;
		int flag=0;
		if (time.length()==4) {
			time='0'+time;
		}
		for (String elem : orari) {
			
			if (contaCicli%2 != 0) {
				if (time.compareTo(ricordaPrec)>0 && time.compareTo(elem)<0) {
					flag=1;
					tempoArrivo=time;
				}
				ricordaPrec=elem;
			}
			
			else {
				ricordaPrec=elem;
			}
			contaCicli++;
		}
		
		if (flag==0) {
			
			if (time.compareTo(orari.get(0))<0) {
				tempoArrivo=orari.get(0);
			}
			
			else if (time.compareTo(orari.get(orari.size()-1))>0) {
				tempoArrivo=orari.get(0);
			}
			else {
				contaCicli=0;
				for (String elem : orari) {
					if (contaCicli%2==0 && time.compareTo(elem)<0 && flag==0) {
						tempoArrivo=elem;
						flag=1;
						
					}
					contaCicli++;
				}
			}
			
		}
		
	}
	
	public enum OrderStatus {
		ORDERED, READY, DELIVERED
	}
	
	public String getNomeR() {
		return nomeRistorante;
	}
	
	public String getNomeC() {
		return cliente.getFirstName();
	}
	
	public String getTempoArrivo() {
		return tempoArrivo;
	}

	
	public enum PaymentMethod {
		PAID, CASH, CARD
	}

	
	public void setPaymentMethod(PaymentMethod pm) {
		metodoPagamento=pm;
	}

	public PaymentMethod getPaymentMethod() {
		return metodoPagamento;
	}

	
	public void setStatus(OrderStatus os) {
		stato=os;
	}

	
	public OrderStatus getStatus() {
		return stato;
	}

	public Order addMenus(String menu, int quantity) {
		dizMenu.put(menu, quantity);
		return this;
	}
 
 	@Override
 	public String toString() {
 		String s="";
 		if (tempoArrivo.length()==4) {
 			tempoArrivo='0'+tempoArrivo;
 		}
 		s=nomeRistorante+", "+cliente.getFirstName()+" "+cliente.getLastName()+" : ("+tempoArrivo+"):";
 		
		
 		for (String key : dizMenu.keySet()) {
 			s=s+"\n\t"+key+"->"+dizMenu.get(key);
 			
 		}
 		return s;
 	}
	
}
