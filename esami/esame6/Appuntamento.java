package it.polito.med;

public class Appuntamento {
	String ssn;
	String name;
	String surname;
	String code;
	String date;
	String slot;
	int id;
	boolean assegnato= false;
	boolean completato= false;
	
	public Appuntamento (String s, String n, String ss,String c,  String d, String slo) {
		ssn = s;
		name = n;
		surname = ss;
		code = c;
		date = d;
		slot = slo;
	}
	
	public void SetId(int i) {
		id =i;
	}
	
	public void SetAssegnato() {
		assegnato = true;
	}
	
	public boolean GetAssegnato () {
		return assegnato;
	}
	
	public void SetCompletato() {
		completato = true;
	}
	
	public boolean GetCompletato () {
		return completato;
	}
	
	public String GetId() {
		return String.valueOf(id);
	}
	
	public String GetCodFiscale() {
		return ssn;
	}
	

	public String GetNomePat() {
		return name;
	}
	

	public String GetCognPat() {
		return surname;
	}
	

	public String GetCodDott() {
		return code;
	}
	

	public String GetData() {
		return date;
	}
	
	public String GetSlot() {
		return slot;
	}
}
