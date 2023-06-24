package it.polito.oop.futsal;

public class Socio {
	int codice;
	String nome;
	String cognome;
	String telefono;
	
	public Socio(int c,String n,String cogn,String tel) {
		codice=c;
		nome=n;
		cognome=cogn;
		telefono=tel;
	}
	
	public String GetNome() {
		return nome;
	}
	
	public String GetCognome() {
		return cognome;
	}
	
	public String GetTel() {
		return telefono;
	}
	
	public int GetCod() {
		return codice;
	}
}
