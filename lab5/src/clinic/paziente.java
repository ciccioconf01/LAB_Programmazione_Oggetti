package clinic;

public class paziente {
	String nome;
	String cognome;
	String codFiscale;
	
	public paziente (String name, String surname,String ISSN) {
		nome=name;
		cognome=surname;
		codFiscale=ISSN;
	}
	
	public String getName() {
		return nome;
	}
	
	public String getSurname() {
		return cognome;
	}
	
	public String getCodF() {
		return codFiscale;
	}
}
