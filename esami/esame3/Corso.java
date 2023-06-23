package it.polito.oop.elective;

public class Corso {
	String nome;
	int posti;
	
	public Corso (String name, int p) {
		nome = name;
		posti = p;
	}
	
	public String GetNomeCorso() {
		return nome;
	}
	
	public int GetNumPosti() {
		return posti;
	}
}
