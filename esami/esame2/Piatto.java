package delivery;

public class Piatto {
	String nome;
	float prezzo;
	
	public Piatto(String name, float price) {
		nome=name;
		prezzo=price;
	}
	
	public String GetNome() {
		return nome;
	}
	
	public float GetPrezzo() {
		return prezzo;
	}
}
