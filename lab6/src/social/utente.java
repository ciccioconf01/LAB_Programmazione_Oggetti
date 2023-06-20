package social;
import java.util.*;

public class utente {

	String codice;
	String nome;
	String cognome;
	List<utente> listaAmici = new ArrayList <>();
	
	public utente (String cod, String name, String surname) {
		codice = cod;
		nome = name;
		cognome = surname;
	}
	
	public String GetCod() {
		return codice;
	}
	
	public String GetNome() {
		return nome;
	}
	public String GetCognome() {
		return cognome;
	}
	
	public void addFriend(utente amico) {
		listaAmici.add(amico);
	}
	
	public List<utente> GetFriend() {
		return listaAmici;
	}
	
	public int GetNumberFriend() {
		return listaAmici.size();
	}

}
