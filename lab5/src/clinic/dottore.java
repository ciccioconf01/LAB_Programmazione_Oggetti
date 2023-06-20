package clinic;

public class dottore {

	String nome;
	String cognome;
	String codFiscale;
	int numBadge;
	String specializzazione;
	
	public dottore (String name, String surname, String ISSN, int num, String spec) {
		nome = name;
		cognome = surname;
		codFiscale = ISSN;
		numBadge = num;
		specializzazione = spec;
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
	
	public int getNumBadge() {
		return numBadge;
	}
	
	public String getSpecializzazione() {
		return specializzazione;
	}
}
