package it.polito.med;

public class Turno {
	String id;
	String data;
	String oraInizio;
	String oraFine;
	int durata;
	
	public Turno (String i, String d, String oi, String of, int dur) {
		id = i;
		data = d;
		oraInizio = oi;
		oraFine = of;
		durata = dur;
	}
	
	public String GetId() {
		return id;
	}
	
	public String GetData() {
		return data;
	}
	
	public String GetOraInizio() {
		return oraInizio;
	}
	
	public String GetOraFine() {
		return oraFine;
	}
	
	public int GetDurata() {
		return durata;
	}
	
	
}
