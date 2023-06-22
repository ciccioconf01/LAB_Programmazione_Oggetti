package delivery;

public class Ordine {
	String nomiPiatti[];
	int quantita[];
	String nomeCliente;
	String nomeRistorante;
	int tempoConsegna;
	int distanza;
	boolean elaborato;
	
	public Ordine(String nomPiat[], int quant[],String nomeC,String nomeR,int tempoC,int dis) {
		nomiPiatti = nomPiat;
		quantita = quant;
		nomeCliente = nomeC;
		nomeRistorante = nomeR;
		tempoConsegna=tempoC;
		distanza = dis;
		elaborato = false;
	}
	
	public String[] GetNomePiatti() {
		return nomiPiatti;
	}
	
	public int[] GetQuantita() {
		return quantita;
	}
	
	public String GetNomeCliente() {
		return nomeCliente;
	}
	
	public String GetNomeRistorante() {
		return nomeRistorante;
	}
	
	public int GetTempoConsegna() {
		return tempoConsegna;
	}
	
	public int GetDistanza() {
		return distanza;
	}
	
	public boolean GetElaborato() {
		return elaborato;
	}
	
	public void SetElaborato(boolean valore) {
		elaborato = valore;
	}
}