package mountainhuts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class Region {

	String nome;
	List<String> altitudini = new ArrayList<>();
	Map<String, Municipality> comuni = new TreeMap<>();
	Map<String, MountainHut> rifugi = new TreeMap<>();

	public Region(String name) {
		nome = name;
	}

	public String getName() {
		return nome;
	}

	public List<String> getAltitudini() {
		return altitudini;
	}

	public void setAltitudeRanges(String... ranges) {
		for (String elem : ranges) {
			altitudini.add(elem);
		}
	}

	public String getAltitudeRange(Integer altitude) {
		String vet[] = new String[2];
		String vuota = "0-INF";
		for (String elem : altitudini) {
			vet = elem.split("-");
			if (Integer.parseInt(vet[0]) <= altitude && Integer.parseInt(vet[1]) >= altitude) {
				return elem;
			}

		}
		return vuota;
	}

	public Collection<Municipality> getMunicipalities() {
		return comuni.values();
	}

	public Collection<MountainHut> getMountainHuts() {
		return rifugi.values();
	}

	public Municipality createOrGetMunicipality(String name, String province, Integer altitude) {
		for (Municipality elem : comuni.values()) {
			if (elem.getName().compareTo(name)==0) {
				return elem;
			}
		}
		Municipality comune = new Municipality(name, province, altitude);
		comuni.put(name, comune);
		return comune;
	}

	public MountainHut createOrGetMountainHut(String name, String category, Integer bedsNumber,
			Municipality municipality) {

		for (String elem : rifugi.keySet()) {
			if (elem == name) {
				return rifugi.get(elem);
			}
		}
		MountainHut rifugio = new MountainHut(name, null, category, bedsNumber, municipality);
		
		
		comuni.get(municipality.getName()).InsertRif(rifugio);
		
		rifugi.put(name, rifugio);
		return rifugio;
	}

	public MountainHut createOrGetMountainHut(String name, Integer altitude, String category, Integer bedsNumber,
			Municipality municipality) {

		for (String elem : rifugi.keySet()) {
			if (elem == name) {
				return rifugi.get(elem);
			}
		}
		MountainHut rifugio = new MountainHut(name, altitude, category, bedsNumber, municipality);
		
		comuni.get(municipality.getName()).InsertRif(rifugio);
		
		rifugi.put(name, rifugio);
		return rifugio;
	}

	public static Region fromFile(String name, String file) {
		Region regione = new Region(name);
		List<String> stringhe = new ArrayList<>();
		stringhe = readData(file);
		String vet[] = new String[7];

		int contaRighe = 0;
		for (String elem : stringhe) {
			if (contaRighe != 0) {
				vet = elem.split(";");
				Municipality comune = regione.createOrGetMunicipality(vet[1], vet[0], Integer.parseInt(vet[2]));
				if (vet[4] == "") {
					regione.createOrGetMountainHut(vet[3], vet[5], Integer.parseInt(vet[6]), comune);
				} else {
					regione.createOrGetMountainHut(vet[3], Integer.parseInt(vet[4]), vet[5], Integer.parseInt(vet[6]),
							comune);
				}
			}
			contaRighe++;
		}
		return regione;
	}

	public static List<String> readData(String file) {
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			return in.lines().collect(toList());
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return new ArrayList<>();
		}
	}

	public Map<String, Long> countMunicipalitiesPerProvince() {
		return comuni.values().stream()
				.collect(Collectors.groupingBy(Municipality::getProvince, Collectors.counting()));
	}

	public Map<String, Map<String, Long>> countMountainHutsPerMunicipalityPerProvince() {
		return rifugi.values().stream().collect(Collectors.groupingBy(MountainHut::getProvince,
				Collectors.groupingBy(MountainHut::getMunicipalityName, Collectors.counting())));
	}

	public Map<String, Long> countMountainHutsPerAltitudeRange() {
		Map<String, Long> countsByAltitudeRange = rifugi.values().stream().collect(Collectors.groupingBy(rifugio -> {
			Integer altitude = rifugio.getAltitude().orElseGet(() -> rifugio.getMunicipality().getAltitude());
			return getAltitudeRange(altitude);
		}, Collectors.counting()));

		return countsByAltitudeRange;

	}

	public Map<String, Integer> totalBedsNumberPerProvince() {
		Map<String, Integer> mappa = rifugi.values().stream()
				.collect(Collectors.toMap(MountainHut::getProvince, MountainHut::getBedsNumber, Integer::sum));
		return mappa;

	}

	public Map<String, Optional<Integer>> maximumBedsNumberPerAltitudeRange() {
		Map<String, Optional<Integer>> mappa = rifugi.values().stream().collect(Collectors.toMap(
			rifugio -> {
			Integer altitude = rifugio.getAltitude().orElseGet(() -> rifugio.getMunicipality().getAltitude());
			return getAltitudeRange(altitude);
		}, 
			rifugio -> {
			Optional<Integer> postiLetto;
			Integer posti;
			posti = rifugio.getBedsNumber();
			postiLetto = Optional.of(posti);
			return postiLetto;
		},

				(elem1, elem2) -> {
					if (elem1.isPresent() != true) {
						return elem2;
					} else if (elem2.isPresent() != true) {
						return elem1;
					} else if (elem1.get().compareTo(elem2.get()) > 0) {
						return elem1;
					} else {
						return elem2;
					}
				}));

		return mappa;

	}


	
	  public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
		  
		  for (Municipality comune : comuni.values()) {
			  System.out.println(comune.GetNumberRif());
		  }
		 
		  Map<Long, List<String>> mappa = comuni.values().stream()
		  .collect(Collectors.toMap(
		  
		  Municipality::GetNumberRif,
		  
		  
		  comune -> { 
			  List<String> lista = new ArrayList <>(); 
			  long num = comune.GetNumberRif();
			  
			  for (Municipality com : comuni.values()) { 
				  if (com.GetNumberRif() == num) 
				  	{ lista.add(com.getName()); } }
			  
			  return lista; 
			  
		  },
		  
		  (elem1,elem2) -> {
			  String comodo;
			  for (int i = 0 ; i < elem1.size() - 1; i++) {
				  for (int j = i+1; j < elem1.size(); j++) {
					  if ( elem1.get(i).compareTo(elem1.get(j)) > 0) {
						  comodo = elem1.get(i);
						  elem1.add(i, elem1.get(j));
						  elem1.add(j, comodo);
					  }
				  }
			  }
			  return elem1;
		  }
		  
		  
		  ) );
		  
		  return mappa;
	  
	  }
	 

	/*
	 * public Map<Long, List<String>> municipalityNamesPerCountOfMountainHuts() {
	 * Map<Long, List<String>> mappa = comuni.values().stream()
	 * .collect(Collectors.toMap( //Municipality::GetNumberRif,
	 * 
	 * comune -> { return comune.GetNumberRif(); },
	 * 
	 * 
	 * comune -> { return comune.GetNumberRif(); },
	 * 
	 * elem -> { List<String> lista = new ArrayList <>(); num = elem; for
	 * (Municipality comune : comuni.values()) { if (comune.GetNumberRif() == elem)
	 * { lista.add(comune.getName()); } }
	 * 
	 * return lista; }
	 * 
	 * ) );
	 * 
	 * return mappa;
	 * 
	 * }
	 */
}
