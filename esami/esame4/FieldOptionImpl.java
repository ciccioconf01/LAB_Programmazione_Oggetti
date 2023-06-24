package it.polito.oop.futsal;

public class FieldOptionImpl implements FieldOption{
	int numeroCampo;
	int occupazione;
	
	public FieldOptionImpl(int n1,int n2) {
		numeroCampo = n1;
		occupazione = n2;
	}
	
	public int getField() {
		return numeroCampo;
	}
    
    
    public int getOccupation() {
    	return occupazione;
    }
}
