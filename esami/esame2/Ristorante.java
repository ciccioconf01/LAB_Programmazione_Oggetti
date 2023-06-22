package delivery;
import java.util.*;

public class Ristorante {
	String nome;
	String categoria;
	List<Piatto> piatti = new ArrayList<>();
	List<Integer> valutazioni = new ArrayList<>();
	
	public Ristorante(String nom, String categori) {
		nome = nom;
		categoria = categori;
	}
	
	public void addValutazione(int rating) {
		valutazioni.add(rating);
	}
	
	public List<Integer> GetValutazioni(){
		return valutazioni;
	}
	
	public String GetNome() {
		return nome;
	}
	
	public String GetCategoria() {
		return categoria;
	}
	
	public List<Piatto> GetPiatti(){
		return piatti;
	}
	
	public void addPiatto(String piatto,float prezzo)throws DeliveryException {
		boolean trovato = false;
		for(Piatto elem : piatti) {
			if (elem.GetNome().compareTo(piatto)==0) {
				trovato = true;
			}
		}
		
		if (trovato == true) {
			throw new DeliveryException();
		}
		else {
			Piatto elem = new Piatto(piatto,prezzo);
			piatti.add(elem);
		}
	}

}
