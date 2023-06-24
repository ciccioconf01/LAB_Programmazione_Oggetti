package it.polito.oop.futsal;

import it.polito.oop.futsal.Fields.Features;

public class Campo {
	
	int numero;
	Features features;
	
	
	public Campo (int camp, Features fea) {
		numero = camp;
		features = fea;
	}
	
	public int GetNumero() {
		return numero;
	}
	
	public Features GetFeatures() {
		return features;
	}
	
	
}
