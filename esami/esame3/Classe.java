package it.polito.oop.elective;
import java.util.*;

public class Classe {
	Corso corso;
	List<Studente> studenti = new ArrayList<>();
	
	
	public Classe (Corso c, List<Studente> s) {
		corso = c;
		studenti = s;
		
	}
	
	public Corso GetCorso() {
		return corso;
	}
	
	public List<Studente> GetStudenti(){
		return studenti;
	}
	
	public boolean IsFull() {
		if (studenti.size()>=corso.GetNumPosti()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addStudent(Studente s) {
		studenti.add(s);
	}
	
	public String GetNomeC() {
		return corso.GetNomeCorso();
	}
	
	
}
