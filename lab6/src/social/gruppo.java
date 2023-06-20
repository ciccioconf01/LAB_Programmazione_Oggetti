package social;
import java.util.*;

public class gruppo {
	String nome;
	List<utente> listaUtenti = new ArrayList <>();
	
	public gruppo (String name) {
		nome = name;
	}
	
	public String GetName() {
		return nome;
	}
	
	public void addUtente(utente persona) {
		listaUtenti.add(persona);
	}
	
	public List<utente> getLista(){
		return listaUtenti;
	}
	
	public int getNumberIscritti() {
		return listaUtenti.size();
	}
	
}
