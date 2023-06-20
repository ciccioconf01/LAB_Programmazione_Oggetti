package mountainhuts;

import java.util.Optional;

/**
 * Represents a mountain hut
 * 
 * It includes a name, optional altitude, category,
 * number of beds and location municipality.
 *  
 *
 */
public class MountainHut {
	String nome;
	Optional<Integer> altitudine;
	String categoria;
	Integer numeroLetti;
	Municipality comune;
	
	public MountainHut(String name, Integer altitude, String category, Integer bedsNumber, Municipality municipality) {
		nome=name;
		altitudine=Optional.ofNullable(altitude);
		categoria=category;
		numeroLetti=bedsNumber;
		comune=municipality;
	}

	public String getName() {
		return nome;
	}

	public Optional<Integer> getAltitude() {
 
		return altitudine;
	}

	public String getCategory() {
		return categoria;
	}

	public Integer getBedsNumber() {
		return numeroLetti;
	}

	public Municipality getMunicipality() {
		return comune;
	}
	
	public String getProvince() {
		return comune.getProvince();
	}
	
	public String getMunicipalityName() {
		return comune.getName();
	}
	
}
