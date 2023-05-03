package university;

public class punteggio_studente {
	String nome;
	String cognome;
	float punteggio;
	
	public void setPunteggio(String name, String surname, float p) {
		nome=name;
		cognome=surname;
		punteggio=p;
	}
	
	public float getPunteggio() {
		return punteggio;
	}
	
	public String getNomeStud() {
		return nome;
	}
	
	public String getCognomeStud() {
		return cognome;
	}
}
