package it.polito.oop.books;

import java.util.*;

public class Topic {
	
	String parola_chiave;
	List <Topic> sottoArgomenti = new ArrayList<>();
	
	public Topic (String key) {
		parola_chiave = key;
	}
	public String getKeyword() {
        return parola_chiave;
	}
	
	@Override
	public String toString() {
	    return parola_chiave;
	}

	public boolean addSubTopic(Topic topic) {
		boolean trovato = false;
       for (Topic elem : sottoArgomenti) {
    	   if (elem.getKeyword().compareTo(topic.getKeyword())==0) {
    		   trovato = true;
    	   }
       }
       
       if (trovato == false) {
    	   sottoArgomenti.add(topic);
    	   return true;
       }
       else {
    	   return false;
       }
	}

	public List<Topic> getSubTopics() {
        List <Topic> lista2 = new ArrayList <>();
        
        for (Topic elem : sottoArgomenti) {
        	lista2.add(elem);
        }
        
        Collections.sort(lista2,
        		(t1,t2) -> {
        			return t1.getKeyword().compareTo(t2.getKeyword());
        		}		
        		);
        
        return lista2;
        		
	}
}
