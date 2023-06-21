package it.polito.oop.books;
import java.util.*;
import java.util.List;


public class TheoryChapter {
	String titolo;
	int numeroPagine;
	String testo;
	List<Topic> argomenti = new ArrayList <>(); 
	
	public TheoryChapter(String tit, int num, String test) {
		titolo = tit;
		numeroPagine = num;
		testo = test;
	}

    public String getText() {
		return testo;
	}

    public void setText(String newText) {
    	testo = newText;
    }
    
    public List<Topic> recursive(Topic argomento, List<Topic> lista){
    	if (argomento.getSubTopics().isEmpty()==true) {
    		return lista;
    	}
    	
    	for (Topic elem : argomento.getSubTopics()) {
    		lista = recursive(elem,lista);
    		lista.add(elem);
    	}
    	return lista;
    }

	public List<Topic> getTopics() {
		Set <Topic> set = new HashSet <>();
		
		List <Topic> lista2 = new ArrayList<>();
		
		for (Topic elem : argomenti) {
			
			List <Topic> listrec = new ArrayList<>();
			set.add(elem);
			listrec = recursive(elem,listrec);
			System.out.println(listrec);
			for (Topic elem2 : listrec) {
				set.add(elem2);
			}
			System.out.println(set);
			
		}
		
		for(Topic elem : set) {
			lista2.add(elem);
		}
		
		Collections.sort(lista2,
				(t1,t2)->{
					return t1.getKeyword().compareTo(t2.getKeyword());
				});
		
		System.out.println(lista2);
        return lista2;
	}

    public String getTitle() {
        return titolo;
    }

    public void setTitle(String newTitle) {
    	titolo = newTitle;
    }

    public int getNumPages() {
        return numeroPagine;
    }
    
    public void setNumPages(int newPages) {
    	numeroPagine = newPages;
    }
    
    public void addTopic(Topic topic) {
    	argomenti.add(topic);
    }
    
}
