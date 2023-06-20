package clinic;

import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.Reader;
import java.io.BufferedReader;
import java.util.LinkedHashMap;

public class Clinic {

	List <paziente> pazienti = new ArrayList <>();
	List <dottore> dottori = new ArrayList <>();
	Map <String,Integer> diz = new TreeMap <>();
	
	public void addPatient(String first, String last, String ssn) {
		paziente comodo = new paziente(first,last,ssn);
		pazienti.add(comodo);
	}


	public String getPatient(String ssn) throws NoSuchPatient {
		String stringa="";
		for (paziente elem : pazienti) {
			if(elem.getCodF().compareTo(ssn)==0) {
				stringa = elem.getSurname() + " " + elem.getName() + " ("+elem.getCodF()+")";
				return stringa;
			}
		}
		
		throw new NoSuchPatient();
	}
	
	public paziente returnPat(String ssn) throws NoSuchPatient {

		for (paziente elem : pazienti) {
			if(elem.getCodF().compareTo(ssn)==0) {
				
				return elem;
			}
		}
		
		throw new NoSuchPatient();
	}

	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		dottore comodo = new dottore(first,last,ssn,docID,specialization);
		dottori.add(comodo);
		paziente tmp = new paziente(first,last,ssn);
		pazienti.add(tmp);
	}

	public String getDoctor(int docID) throws NoSuchDoctor {
		String stringa="";
		
		for (dottore elem : dottori) {
			if (elem.getNumBadge() == docID) {
				stringa = elem.getSurname() + " " + elem.getName() + " ("+elem.getCodF()+") ["+elem.getNumBadge()+"]:"+elem.getSpecializzazione();
				return stringa;
			}
		}
		
		throw new NoSuchDoctor();
	}
	
	public dottore retDot(int docID) throws NoSuchDoctor {
		
		
		for (dottore elem : dottori) {
			if (elem.getNumBadge() == docID) {
				return elem;
			}
		}
		
		throw new NoSuchDoctor();
	}
	
	
	
	
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		boolean trovato = false;
		
		for (dottore elem : dottori) {
			if (elem.getNumBadge() == docID) {
				trovato = true;
			}
		}
		
		if (trovato == false) {
			throw new NoSuchDoctor();
			
		}
		
		trovato = false;
		for (paziente elem : pazienti) {
			if(elem.getCodF().compareTo(ssn)==0) {
				trovato = true;
			}
			
		}
		
		if (trovato == false) {
			throw new NoSuchPatient();
			
		}
		
		

		
		
		diz.put(ssn, (Integer) docID);
	}

	
	 
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		
		boolean trovato = false;
		for (paziente elem : pazienti) {
			if(elem.getCodF().compareTo(ssn)==0) {
				trovato = true;
			}
			
		}
		
		if (trovato == false) {
			throw new NoSuchPatient();
			
		}
		
		
		
		for (String elem : diz.keySet()) {
			if (elem.compareTo(ssn)==0) {
				return diz.get(elem);
			}
		}
		throw new NoSuchDoctor();
	}
	
	
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		Collection <String> lista = new ArrayList <>();
		
		boolean trovato = false;
		
		for (dottore elem : dottori) {
			if (elem.getNumBadge() == id) {
				trovato = true;
			}
		}
		
		if (trovato == false) {
			throw new NoSuchDoctor();
			
		}
		
		for (String elem : diz.keySet()) {
			
			if (diz.get(elem) == id) {
				lista.add(elem);
			}
		}
		
		return lista;
	}
	

	public int loadData(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader (reader);
		String l = "";
		String nome,cognome,codFiscale,specializzazione;
		int numBadge;
		int conta=0;
		boolean flag = false;
		String[] vet = new String[10];
		//List<String> vet = new ArrayList <>();
		while(l!=null) {
			try {
				l = br.readLine();
				if (l!=null) {
					l=l.replaceAll(" ", "");
					vet = l.split(";");
					if (vet[0].compareTo("P") == 0 || vet[0].compareTo("M")==0) {
						
						if (vet[0].compareTo("P")==0 && vet.length != 4) {
							flag=true;
						}
						
						if (vet[0].compareTo("M")==0 && vet.length != 6) {
							flag=true;
						}
						
						if (vet[0].compareTo("M")==0) {
							try {
								int tmp = Integer.parseInt(vet[1]);
							}
							catch(NumberFormatException e){
								flag=true;
							};
						}
						
						
						if (flag!=true) {
						
							if (vet[0].compareTo("P")==0) {
								nome = vet[1];
								cognome = vet[2];
								codFiscale = vet[3];
								paziente p = new paziente (nome,cognome,codFiscale);
								pazienti.add(p);
							}
							
							else {
								numBadge = (int) Integer.parseInt(vet[1]);
								nome = vet[2];
								cognome = vet[3];
								codFiscale = vet[4];
								specializzazione = vet[5];
								dottore d = new dottore(nome,cognome,codFiscale,numBadge,specializzazione);
								dottori.add(d);
							}
							conta++;
						}
					}
					
					flag=false;
					
					
				}
				
				
			}
			catch (IOException e){
				br.close();
				throw e;
				
			}
		}
		br.close();
		return conta;
	}



	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		BufferedReader br = new BufferedReader (reader);
		String l = "";
		String nome,cognome,codFiscale,specializzazione;
		int numBadge;
		int conta=0;
		boolean flag = false;
		String[] vet = new String[10];
		//List<String> vet = new ArrayList <>();
		while(l!=null) {
			try {
				l = br.readLine();
				if (l!=null) {
					l=l.replaceAll(" ", "");
					vet = l.split(";");
					
					if (vet[0].compareTo("P") == 0 || vet[0].compareTo("M")==0) {
						
						if (vet[0].compareTo("P")==0 && vet.length != 4) {
							flag=true;
							
						}
						
						if (vet[0].compareTo("M")==0 && vet.length != 6) {
							flag=true;
							
						}
						
						if (vet[0].compareTo("M")==0) {
							try {
								int tmp = Integer.parseInt(vet[1]);
							}
							catch(NumberFormatException e){
								flag=true;
							};
						}
							
						
						if (vet[0].compareTo("P")==0 && flag!=true) {
							nome = vet[1];
							cognome = vet[2];
							codFiscale = vet[3];
							paziente p = new paziente (nome,cognome,codFiscale);
							pazienti.add(p);
							conta++;
						}
						
						else {
							if (flag!=true) {
								numBadge = Integer.parseInt(vet[1]);
								nome = vet[2];
								cognome = vet[3];
								codFiscale = vet[4];
								specializzazione = vet[5];
								dottore d = new dottore(nome,cognome,codFiscale,numBadge,specializzazione);
								dottori.add(d);
								conta++;
							}
						}
						
					}
					
					else {
						listener.offending(l);
						flag = false;
						continue;
					}
					
					if (flag == true) {
						listener.offending(l);
						flag = false;
						continue;
					}
					
					
				}
				
				
			}
			catch (IOException e){
				br.close();
				throw e;
				
			}
		}
		br.close();
		return conta;
	}
	

	public Collection<Integer> idleDoctors(){
		Collection<Integer> lista = new ArrayList <>();
		List <dottore> lista_dott = new ArrayList <>();
		
		boolean trovato = false;
		
		for ( dottore elem : dottori) {
			
			for (Integer numBadge : diz.values()) {
				if (elem.getNumBadge() == (int) numBadge) {
					trovato = true;
				}
			}
			
			if (trovato == false) {
				lista_dott.add(elem);
			}
			trovato = false;
		}
		
		Comparator <dottore> comparatore = (dott1,dott2) -> {
			int comparazione = dott1.getSurname().compareTo(dott2.getSurname());
			if (comparazione != 0) {
				return comparazione;
			}
			
			comparazione = dott1.getName().compareTo(dott2.getName());
			return comparazione;
			
		};
		
		Collections.sort(lista_dott, comparatore);
		Integer num;
		for ( dottore elem : lista_dott) {
			num = elem.getNumBadge();
			lista.add(num);
		}
		
		return lista;
	}

	
	public Collection<Integer> busyDoctors(){
		Map <Integer,Integer> mappa = new TreeMap <>(); 
		Collection<Integer> lista_dott = new ArrayList <>();
		int conta=0;
		
		for (dottore d : dottori) {
			
			for (int elem : diz.values()) {
				if (d.getNumBadge() == elem) {
					conta++;
				}
			}
			
			mappa.put(d.getNumBadge(), conta);
			conta=0;
		}
		
		int accumula=0;
		
		for (int elem : mappa.values()) {
			accumula = accumula + elem;
			conta++;
		}
		
		float media = accumula / conta;
		
		for (Integer elem : mappa.keySet()) {
			if (mappa.get(elem) > media) {
				lista_dott.add(elem);
			}
		}
		
		return lista_dott;
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	
	
	
	public Collection<String> doctorsByNumPatients(){
		Map <Integer,Integer> mappa = new TreeMap<>();
		Collection <String> lista = new ArrayList <>();
		int conta=0;
		
		for (dottore d : dottori) {
			
			for (int elem : diz.values()) {
				if (d.getNumBadge() == elem) {
					conta++;
				}
			}
			
			mappa.put(d.getNumBadge(), conta);
			conta=0;
		}
		
		List <Integer> list = new ArrayList <>();
		LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
		
		for (Integer value : mappa.values()) {
            list.add(value);
        }
		
        Collections.sort(list,Collections.reverseOrder()); 
        
        boolean trovato=false;
        for (Integer num : list) {
        	 for (Map.Entry<Integer, Integer> entry : mappa.entrySet()) {
                 if (entry.getValue().equals(num)) {
                	 for (Integer elem : sortedMap.keySet()) {
                		 if (elem == entry.getKey()) {
                			 trovato = true;
                		 }
                	 }
                	 if (trovato == false) {
                		 sortedMap.put(entry.getKey(), num);
                		 
                	 }
                	 
                	 trovato = false;
                 }
             }
        }	
        
        
        
		String stringa="" ;
		String nome="";
		String cognome="";
		for(Integer numBadge : sortedMap.keySet()) {
			
			for (dottore d : dottori) {
				if (d.numBadge == numBadge) {
					nome = d.getName();
					cognome = d.getSurname();
				}
			}
			
			
			stringa =  String.format("%3d", sortedMap.get(numBadge)) + " : "+numBadge+" "+cognome+" "+nome;
			lista.add(stringa);
		}
		
		return lista;

	}
	

	public Collection<String> countPatientsPerSpecialization(){
		
		Map <Integer,Integer> mappa = new TreeMap <>(); 
		
		int conta=0;
		
		for (dottore d : dottori) {
			
			for (int elem : diz.values()) {
				if (d.getNumBadge() == elem) {
					conta++;
				}
			}
			
			mappa.put(d.getNumBadge(), conta);
			conta=0;
		}
		
		
		Map <String,Integer> diz_spec = new TreeMap <>();
		String specializzazione = "";
		int acc=0;
		
		for (Integer badge : mappa.keySet()) {
			
			for (dottore d : dottori) {
				if (badge == d.getNumBadge()) {
					specializzazione = d.getSpecializzazione();
				}
			}
			
			if (diz_spec.containsKey(specializzazione)==true) {
				acc = mappa.get(badge) + diz_spec.get(specializzazione);
				diz_spec.put(specializzazione, acc);
			}
			else {
				diz_spec.put(specializzazione, mappa.get(badge));
			}
		}
		
		SortedMap<String, Integer> sortedMap = new TreeMap<>(new Comparator<String> () {
            public int compare(String key1, String key2) {
            	int val = Integer.compare(diz_spec.get(key2), diz_spec.get(key1));
                if (val!=0) {
                	return val;
                }
                
                val = key1.compareTo(key2);
                return val;
                
            }
        });
		
		sortedMap.putAll(diz_spec);
		
		String stringa;
		
		Collection<String> list_final = new ArrayList <>();
		
		for (String key : sortedMap.keySet()) {
			if (sortedMap.get(key)!=0) {
				stringa = String.format("%3d", sortedMap.get(key)) + " - "+key;
				list_final.add(stringa);
			}
		}
		
		return list_final;

		
		
	}

}
