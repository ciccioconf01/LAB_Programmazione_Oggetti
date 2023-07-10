package it.polito.med;

public class Medico {
	String id;
	String nome;
	String cognome;
	String specialita;
	
	public Medico (String i, String n, String c, String s) {
		id=i;
		nome = n;
		cognome = c;
		specialita =s;
	}
	
	public String GetId(){
		return id;
	}
	
	public String GetNome(){
		return nome;
	}
	
	public String GetCognome(){
		return cognome;
	}
	
	public String GetSpecialita(){
		return specialita;
	}
}
