package it.polito.oop.elective;

import java.util.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;



public class ElectiveManager {

    List<Corso> corsi = new ArrayList <>();
    List<Studente> studenti = new ArrayList<>();
    List<Classe> classi = new ArrayList<>();
    List<Notifier> notificatori = new ArrayList<>();
    
    public void addCourse(String name, int availablePositions) {
        Corso c = new Corso(name,availablePositions);
        corsi.add(c);
    }
    
    
    public SortedSet<String> getCourses(){
    	SortedSet<String> corsiSort = new TreeSet<>();
    	
    	for (Corso c : corsi) {
    		corsiSort.add(c.GetNomeCorso());
    	}
    	
    	return corsiSort;
        
    }
    
    

    public void loadStudent(String id, 
                                  double gradeAverage){
    	boolean trovato = false;
    	for (Studente s : studenti) {
    		if(s.GetId().compareTo(id)==0) {
    			s.SetMedia(gradeAverage);
    			trovato = true;
    		}
    	}
    	
    	if (trovato == false) {
    		Studente s = new Studente(id,gradeAverage);
    		studenti.add(s);
    	}
        
    }

    
    public Collection<String> getStudents(){
    	Collection<String> lista = new ArrayList<>();
    	
    	for (Studente s : studenti) {
    		lista.add(s.GetId());
    	}
    	
    	return lista;
    }
    
    
    public Collection<String> getStudents(double inf, double sup){
    	Collection<String> lista = new ArrayList<>();
    	
    	for (Studente s : studenti) {
    		if(s.GetMedia()>= inf && s.GetMedia()<=sup) {
    			lista.add(s.GetId());
    		}
    	}
    	
    	return lista;
    }


   
    public int requestEnroll(String id, List<String> courses)  throws ElectiveException {
    	
    	//controlli
        if (courses.size()<1 || courses.size()>3) {
        	throw new ElectiveException();
        }
        
        boolean trovato = false;
        for (Studente s : studenti) {
        	if (s.GetId().compareTo(id)==0) {
        		trovato = true;
        	}
        }
        
        if (trovato == false) {
        	throw new ElectiveException();
        }
        
        trovato = false;
        for (String c1 : courses) {
        	
        	for (Corso c2 : corsi) {
        		
        		if(c1.compareTo(c2.GetNomeCorso())==0) {
        			trovato = true;
        		}
        	}
        	
        	if (trovato == false) {
        		throw new ElectiveException();
        	}
        	
        	trovato = false;
        	
        }
        
        //notifica
        
        for(Notifier n : notificatori) {
        	n.requestReceived(id);
        }
        
        //aggiunta
        
        for (Studente s : studenti) {
        	if(s.GetId().compareTo(id)==0) {
        		s.SetPreferenze(courses);
        	}
        }
        
        return courses.size();
        
    }
    
    
    public Map<String,List<Long>> numberRequests(){
    	Map<String,List<Long>> mappa = new TreeMap<>();
    	
    	int sel0 = 0;
    	int sel1 = 0;
    	int sel2 = 0;
    	int conta =0;
    	
    	long acc0 = 0;
    	long acc1 = 0;
    	long acc2 = 0;
    	for (Corso c : corsi) {
    		
    		
    		for (Studente s : studenti) {
    			
    			for(String p : s.GetPreferenze()) {
    				if (c.GetNomeCorso().compareTo(p)==0) {
    					
    					if (conta == 0) {
    						sel0 = 1;
    					}
    					
    					if (conta == 1) {
    						sel1 = 1;
    					}
    					
    					if (conta == 2) {
    						sel2 = 1;
    					}
    				}
    				conta++;
    			}
    			
    			if (sel0==1) {
    				acc0++;
    			}
    			
    			if (sel1==1) {
    				acc1++;
    			}
    			
    			if (sel2==1) {
    				acc2++;
    			}
    			
    			sel0=0;
    			sel1=0;
    			sel2=0;
    			conta = 0;
    		}
    		
    		List<Long> lista = new ArrayList <>();
    		lista.add(acc0);
    		lista.add(acc1);
    		lista.add(acc2);
    		mappa.put(c.GetNomeCorso(), lista);
    		acc0 = 0;
    		acc1 = 0;
    		acc2 = 0;
    	}
    	
    	return mappa;
    }
    
    
    
    public long makeClasses() {
    	
    	//creazione classi
    	
    	for (Corso c : corsi) {
    		List <Studente> studenti = new ArrayList<>();
    		Classe clas = new Classe(c,studenti);
    		classi.add(clas);
    	}
    	
    	//assegnazione
    	List <Studente> s = new ArrayList<>();
        for (Studente stud : studenti) {
        	s.add(stud);
        }
        
        s.sort(
        		(s1,s2)->{
        			return Double.compare(s2.GetMedia(), s1.GetMedia());
        		});
        
        boolean assegnato = false;
        for (Studente stud : s) {
        	for (String pref : stud.preferenze) {
        		if (assegnato == false) {
	        		for(Classe c : classi) {
	        			if (pref.compareTo(c.GetNomeC())==0 && c.IsFull()==false && assegnato != true) {
	        				c.addStudent(stud);
	        				assegnato = true;
	        				stud.SetAssegnato();
	        				
	        				//notificatore
	        				for(Notifier n:notificatori) {
	        					n.assignedToCourse(stud.GetId(), c.GetNomeC());
	        				}
	        			}
	        		}
        		}
        	}
        	assegnato = false;
        }
        
        long nonAssegnati = 0;
        
        for (Studente stud : studenti) {
        	if (stud.GetAssegnato()==false) {
        		nonAssegnati++;
        	}
        }
        
        return nonAssegnati;
    }
    
    
 
    public Map<String,List<String>> getAssignments(){
    	 Map<String,List<String>> mappa = new TreeMap();
    	 List<Studente> lista1 = new ArrayList<>();
    	 
    	 String nomeCorso = "";
    	 
    	 for (Classe c : classi) {
    		 List<String> lista2 = new ArrayList<>();
    		 nomeCorso = c.GetCorso().GetNomeCorso();
    		 lista1 = c.GetStudenti();
    		 
    		 lista1.sort(
    				 (s1,s2)->{
    					 return Double.compare(s2.GetMedia(), s1.GetMedia());
    				 });
    		 
    		 for(Studente s : lista1) {
    			 lista2.add(s.GetId());
    			
    		 }
    		 
    		 mappa.put(nomeCorso, lista2);
    	 }
    	
    	 
    	 return mappa;
    }
    
    
 
    public void addNotifier(Notifier listener) {
        notificatori.add(listener);
    }
    
   
    public double successRate(int choice){
    	
    	List<Classe> lista = new ArrayList<>();
    	
    	//creazione classi
    	
    	for (Corso c : corsi) {
    		List <Studente> studenti = new ArrayList<>();
    		Classe clas = new Classe(c,studenti);
    		lista.add(clas);
    	}
    	
    	//assegnazione
    	List <Studente> s = new ArrayList<>();
        for (Studente stud : studenti) {
        	s.add(stud);
        }
        
        for(Studente elem : s ) {
        	elem.SetNonAssegnato();
        }
        
        s.sort(
        		(s1,s2)->{
        			return Double.compare(s2.GetMedia(), s1.GetMedia());
        		});
        
        boolean assegnato = false;
        double acc=0;
        int conta = 1;
        for (Studente stud : s) {
        	for (String pref : stud.preferenze) {
        		if (assegnato == false) {
	        		for(Classe c : lista) {
	        			if (pref.compareTo(c.GetNomeC())==0 && c.IsFull()==false && assegnato != true) {
	        				c.addStudent(stud);
	        				assegnato = true;
	        				stud.SetAssegnato();
	        				
	        				if(conta==choice) {
	        					acc++;
	        				}
	        			}
	        		}
        		}
        		conta++;
        	}
        	conta=1;
        	assegnato = false;
        }
        
        double perc = acc/studenti.size();
        return perc;
    }

    
   
    public List<String> getNotAssigned(){
    	List<String> lista = new ArrayList<>();
    	for(Studente s : studenti) {
    		if (s.GetAssegnato()==false) {
    			lista.add(s.GetId());
    		}
    	}
    	return lista;
    }
    
    
}
