package it.polito.oop.books;

public class risposta {
	String resp;
	boolean giusto;
	
	public risposta (String rispo, boolean vero) {
		resp = rispo;
		giusto = vero;
	}
	
	public String GetRisposta() {
		return resp;
	}
	
	public boolean GetRisultato() {
		return giusto;
	}

}
