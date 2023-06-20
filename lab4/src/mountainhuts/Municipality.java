package mountainhuts;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a municipality that hosts a mountain hut.
 * It is a data class with getters for name, province, and altitude
 * 
 */
public class Municipality {
	String nome;
	String provincia;
	Integer altitudine;
	List <MountainHut> rifugi;
	int Nrifugi;
	
	public Municipality(String name, String province, Integer altitude) {
		nome=name;
		provincia=province;
		altitudine=altitude;
		Nrifugi = 0;
		rifugi = new ArrayList<>(); 
	}
	
	public void InsertRif(MountainHut rifugio) {
	    rifugi.add(rifugio);
	    Nrifugi++;
	}
	
	
	public long GetNumberRif() {
		return (long) Nrifugi;
	}

	public String getName() {
		return nome;
	}

	public String getProvince() {
		return provincia;
	}

	public Integer getAltitude() {
		return altitudine;
	}

}
