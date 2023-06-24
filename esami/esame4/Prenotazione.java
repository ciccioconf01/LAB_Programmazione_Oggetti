package it.polito.oop.futsal;

public class Prenotazione {
	int numeroCampo;
	int codicePersona;
	String orario;
	
	public Prenotazione(int campo,int cod, String o) {
		numeroCampo = campo;
		codicePersona=cod;
		orario = o;
	}
	
	public int GetNumCampo() {
		return numeroCampo;
	}
	
	public int GetCodPers() {
		return codicePersona;
	}
	
	public String GetOrario() {
		return orario;
	}
}
