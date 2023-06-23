package it.polito.oop.elective;
import java.util.*;

public class Studente {
	String id;
	double media;
	List<String> preferenze = new ArrayList<>();
	boolean assegnato;
	
	public Studente(String matricola,double av) {
		id = matricola;
		media = av;
		assegnato = false;
	}
	
	public String GetId() {
		return id;
	}
	
	public double GetMedia() {
		return media;
	}
	
	public void SetMedia(double av) {
		media = av;
	}
	
	public void SetPreferenze(List<String> pref) {
		preferenze = pref;
	}
	
	public List<String> GetPreferenze(){
		return preferenze;
	}
	
	public void SetAssegnato() {
		assegnato = true;
	}
	
	public void SetNonAssegnato() {
		assegnato = false;
	}
	
	public boolean GetAssegnato() {
		return assegnato;
	}
}
